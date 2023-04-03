package com.github.yeetmanlord.somanyenchants.common.tileentities;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nullable;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedChestBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedHopper;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedHopperContainer;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ISidedInventoryProvider;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class EnchantedHopperTileEntity extends LockableLootTileEntity implements IHopper, ITickableTileEntity {
	public static final int MOVE_ITEM_SPEED = 2;
	public static final int HOPPER_CONTAINER_SIZE = 5;
	private NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);
	private int cooldownTime = -1;
	private long tickedGameTime;

	public EnchantedHopperTileEntity() {
		super(BlockEntityTypeInit.ENCHANTED_HOPPER.get());
	}

	@Override
	public void load(BlockState blockState, CompoundNBT nbt) {
		super.load(blockState, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(nbt)) {
			ItemStackHelper.loadAllItems(nbt, this.items);
		}

		this.cooldownTime = nbt.getInt("TransferCooldown");
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		super.save(nbt);
		if (!this.trySaveLootTable(nbt)) {
			ItemStackHelper.saveAllItems(nbt, this.items);
		}

		nbt.putInt("TransferCooldown", this.cooldownTime);
		return nbt;
	}

	@Override
	public int getContainerSize() {
		return this.items.size();
	}

	@Override
	public ItemStack removeItem(int p_59309_, int p_59310_) {
		this.unpackLootTable((PlayerEntity) null);
		return ItemStackHelper.removeItem(this.getItems(), p_59309_, p_59310_);
	}

	@Override
	public void setItem(int p_59315_, ItemStack p_59316_) {
		this.unpackLootTable((PlayerEntity) null);
		this.getItems().set(p_59315_, p_59316_);
		if (p_59316_.getCount() > this.getMaxStackSize()) {
			p_59316_.setCount(this.getMaxStackSize());
		}

	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.enchanted_hopper");
	}

	@Override
	public void tick() {
		--this.cooldownTime;
		this.tickedGameTime = this.level.getGameTime();
		if (!this.isOnCooldown()) {
			this.setCooldown(0);
			this.tryMoveItems(() -> {
				return suckInItems();
			});
		}

	}

	private boolean tryMoveItems(Supplier<Boolean> sup) {
		BlockState state = this.level.getBlockState(this.getBlockPos());
		if (this.level.isClientSide) {
			return false;
		} else {
			if (!this.isOnCooldown() && state.getValue(EnchantedHopper.ENABLED)) {
				boolean flag = false;
				if (!this.isEmpty()) {
					flag = ejectItems(this.level, this.getBlockPos(), state, this);
				}

				if (!this.inventoryFull()) {
					flag |= sup.get();
				}

				if (flag) {
					this.setCooldown(2);
					this.setChanged();
					return true;
				}
			}

			return false;
		}
	}

	private boolean inventoryFull() {
		for (ItemStack itemstack : this.items) {
			if (itemstack.isEmpty() || itemstack.getCount() != itemstack.getMaxStackSize()) {
				return false;
			}
		}

		return true;
	}

	private static boolean ejectItems(World p_155563_, BlockPos p_155564_, BlockState p_155565_,
			EnchantedHopperTileEntity p_155566_) {
		if (Capabilities.insertHook(p_155566_))
			return true;
		IInventory container = getAttachedContainer(p_155563_, p_155564_, p_155565_);
		if (container == null) {
			return false;
		} else {
			Direction direction = p_155565_.getValue(EnchantedHopper.FACING).getOpposite();
			if (isFullContainer(container, direction)) {
				return false;
			} else {
				for (int i = 0; i < p_155566_.getContainerSize(); ++i) {
					if (!p_155566_.getItem(i).isEmpty()) {
						ItemStack itemstack = p_155566_.getItem(i).copy();
						ItemStack itemstack1 = addItem(p_155566_, container, p_155566_.removeItem(i, 1), direction);
						if (itemstack1.isEmpty()) {
							container.setChanged();
							return true;
						}

						p_155566_.setItem(i, itemstack);
					}
				}

				return false;
			}
		}
	}

	private static IntStream getSlots(IInventory p_59340_, Direction p_59341_) {
		return p_59340_ instanceof ISidedInventory
				? IntStream.of(((ISidedInventory) p_59340_).getSlotsForFace(p_59341_))
				: IntStream.range(0, p_59340_.getContainerSize());
	}

	private static boolean isFullContainer(IInventory p_59386_, Direction p_59387_) {
		return getSlots(p_59386_, p_59387_).allMatch((p_59379_) -> {
			ItemStack itemstack = p_59386_.getItem(p_59379_);
			return itemstack.getCount() >= itemstack.getMaxStackSize();
		});
	}

	private static boolean isEmptyContainer(IInventory p_59398_, Direction p_59399_) {
		return getSlots(p_59398_, p_59399_).allMatch((p_59319_) -> {
			return p_59398_.getItem(p_59319_).isEmpty();
		});
	}

	public boolean suckInItems() {
		Boolean ret = net.minecraftforge.items.VanillaInventoryCodeHooks.extractHook(this);
		if (ret != null)
			return ret;
		IInventory container = getSourceContainer(this.level, this);
		if (container != null) {
			Direction direction = Direction.DOWN;
			return isEmptyContainer(container, direction) ? false
					: getSlots(container, direction).anyMatch((p_59363_) -> {
						return tryTakeInItemFromSlot(this, container, p_59363_, direction);
					});
		} else {
			for (ItemEntity itementity : getItemsAtAndAbove(this.level, this)) {
				if (addItem(this, itementity)) {
					return true;
				}
			}

			return false;
		}
	}

	private static boolean tryTakeInItemFromSlot(IHopper p_59355_, IInventory p_59356_, int p_59357_,
			Direction p_59358_) {
		ItemStack itemstack = p_59356_.getItem(p_59357_);
		if (!itemstack.isEmpty() && canTakeItemFromContainer(p_59356_, itemstack, p_59357_, p_59358_)) {
			ItemStack itemstack1 = itemstack.copy();
			ItemStack itemstack2 = addItem(p_59356_, p_59355_, p_59356_.removeItem(p_59357_, 1), (Direction) null);
			if (itemstack2.isEmpty()) {
				p_59356_.setChanged();
				return true;
			}

			p_59356_.setItem(p_59357_, itemstack1);
		}

		return false;
	}

	public static boolean addItem(IInventory inv, ItemEntity item) {
		boolean flag = false;
		ItemStack itemstack = item.getItem().copy();
		ItemStack itemstack1 = addItem((IInventory) null, inv, itemstack, (Direction) null);
		if (itemstack1.isEmpty()) {
			flag = true;
			item.remove();
		} else {
			item.setItem(itemstack1);
		}

		return flag;
	}

	public static ItemStack addItem(@Nullable IInventory p_59327_, IInventory p_59328_, ItemStack p_59329_,
			@Nullable Direction p_59330_) {
		if (p_59328_ instanceof ISidedInventory && p_59330_ != null) {
			ISidedInventory worldlycontainer = (ISidedInventory) p_59328_;
			int[] aint = worldlycontainer.getSlotsForFace(p_59330_);

			for (int k = 0; k < aint.length && !p_59329_.isEmpty(); ++k) {
				p_59329_ = tryMoveInItem(p_59327_, p_59328_, p_59329_, aint[k], p_59330_);
			}
		} else {
			int i = p_59328_.getContainerSize();

			for (int j = 0; j < i && !p_59329_.isEmpty(); ++j) {
				p_59329_ = tryMoveInItem(p_59327_, p_59328_, p_59329_, j, p_59330_);
			}
		}

		return p_59329_;
	}

	private static boolean canPlaceItemInContainer(IInventory p_59335_, ItemStack p_59336_, int p_59337_,
			@Nullable Direction p_59338_) {
		if (!p_59335_.canPlaceItem(p_59337_, p_59336_)) {
			return false;
		} else {
			return !(p_59335_ instanceof ISidedInventory)
					|| ((ISidedInventory) p_59335_).canPlaceItemThroughFace(p_59337_, p_59336_, p_59338_);
		}
	}

	private static boolean canTakeItemFromContainer(IInventory p_59381_, ItemStack p_59382_, int p_59383_,
			Direction p_59384_) {
		return !(p_59381_ instanceof ISidedInventory)
				|| ((ISidedInventory) p_59381_).canTakeItemThroughFace(p_59383_, p_59382_, p_59384_);
	}

	private static ItemStack tryMoveInItem(@Nullable IInventory p_59321_, IInventory p_59322_, ItemStack p_59323_,
			int p_59324_, @Nullable Direction p_59325_) {
		ItemStack itemstack = p_59322_.getItem(p_59324_);
		if (canPlaceItemInContainer(p_59322_, p_59323_, p_59324_, p_59325_)) {
			boolean flag = false;
			boolean flag1 = p_59322_.isEmpty();
			if (itemstack.isEmpty()) {
				p_59322_.setItem(p_59324_, p_59323_);
				p_59323_ = ItemStack.EMPTY;
				flag = true;
			} else if (canMergeItems(itemstack, p_59323_)) {
				int i = p_59323_.getMaxStackSize() - itemstack.getCount();
				int j = Math.min(p_59323_.getCount(), i);
				p_59323_.shrink(j);
				itemstack.grow(j);
				flag = j > 0;
			}

			if (flag) {
				if (flag1 && p_59322_ instanceof EnchantedHopperTileEntity) {
					EnchantedHopperTileEntity EnchantedHopperTileEntity1 = (EnchantedHopperTileEntity) p_59322_;
					if (!EnchantedHopperTileEntity1.isOnCustomCooldown()) {
						int k = 0;
						if (p_59321_ instanceof EnchantedHopperTileEntity) {
							EnchantedHopperTileEntity EnchantedHopperTileEntity = (EnchantedHopperTileEntity) p_59321_;
							if (EnchantedHopperTileEntity1.tickedGameTime >= EnchantedHopperTileEntity.tickedGameTime) {
								k = 1;
							}
						}

						EnchantedHopperTileEntity1.setCooldown(2 - k);
					}
				}

				p_59322_.setChanged();
			}
		}

		return p_59323_;
	}

	@Nullable
	private static IInventory getAttachedContainer(World p_155593_, BlockPos p_155594_, BlockState p_155595_) {
		Direction direction = p_155595_.getValue(EnchantedHopper.FACING);
		return getContainerAt(p_155593_, p_155594_.relative(direction));
	}

	@Nullable
	private static IInventory getSourceContainer(World p_155597_, IHopper p_155598_) {
		return getContainerAt(p_155597_, p_155598_.getLevelX(), p_155598_.getLevelY() + 1.0D, p_155598_.getLevelZ());
	}

	public static List<ItemEntity> getItemsAtAndAbove(World p_155590_, IHopper p_155591_) {
		return p_155591_.getSuckShape().toAabbs().stream().flatMap((p_155558_) -> {
			return p_155590_.getEntitiesOfClass(ItemEntity.class, p_155558_.move(p_155591_.getLevelX() - 0.5D,
					p_155591_.getLevelY() - 0.5D, p_155591_.getLevelZ() - 0.5D), EntityPredicates.ENTITY_STILL_ALIVE)
					.stream();
		}).collect(Collectors.toList());
	}

	@Nullable
	public static IInventory getContainerAt(World p_59391_, BlockPos p_59392_) {
		return getContainerAt(p_59391_, (double) p_59392_.getX() + 0.5D, (double) p_59392_.getY() + 0.5D,
				(double) p_59392_.getZ() + 0.5D);
	}

	@Nullable
	private static IInventory getContainerAt(World p_59348_, double p_59349_, double p_59350_, double p_59351_) {
		IInventory container = null;
		BlockPos blockpos = new BlockPos(p_59349_, p_59350_, p_59351_);
		BlockState blockstate = p_59348_.getBlockState(blockpos);
		Block block = blockstate.getBlock();
		if (block instanceof ISidedInventoryProvider) {
			container = ((ISidedInventoryProvider) block).getContainer(blockstate, p_59348_, blockpos);
		} else if (blockstate.hasTileEntity()) {
			TileEntity blockentity = p_59348_.getBlockEntity(blockpos);
			if (blockentity instanceof IInventory) {
				container = (IInventory) blockentity;
				if (container instanceof ChestTileEntity && block instanceof ChestBlock) {
					container = ChestBlock.getContainer((ChestBlock) block, blockstate, p_59348_, blockpos, true);
				} else if (container instanceof EnchantedChestTileEntity && block instanceof EnchantedChestBlock) {
					container = EnchantedChestBlock.getContainer((EnchantedChestBlock) block, blockstate, p_59348_,
							blockpos, true);
				}
			}
		}

		if (container == null) {
			List<Entity> list = p_59348_
					.getEntities(
							(Entity) null, new AxisAlignedBB(p_59349_ - 0.5D, p_59350_ - 0.5D, p_59351_ - 0.5D,
									p_59349_ + 0.5D, p_59350_ + 0.5D, p_59351_ + 0.5D),
							EntityPredicates.CONTAINER_ENTITY_SELECTOR);
			if (!list.isEmpty()) {
				container = (IInventory) list.get(p_59348_.random.nextInt(list.size()));
			}
		}

		return container;
	}

	private static boolean canMergeItems(ItemStack p_59345_, ItemStack p_59346_) {
		if (!p_59345_.getItem().equals(p_59346_.getItem())) {
			return false;
		} else if (p_59345_.getDamageValue() != p_59346_.getDamageValue()) {
			return false;
		} else if (p_59345_.getCount() > p_59345_.getMaxStackSize()) {
			return false;
		} else {
			return ItemStack.tagMatches(p_59345_, p_59346_);
		}
	}

	@Override
	public double getLevelX() {
		return (double) this.worldPosition.getX() + 0.5D;
	}

	@Override
	public double getLevelY() {
		return (double) this.worldPosition.getY() + 0.5D;
	}

	@Override
	public double getLevelZ() {
		return (double) this.worldPosition.getZ() + 0.5D;
	}

	public void setCooldown(int p_59396_) {
		this.cooldownTime = p_59396_;
	}

	private boolean isOnCooldown() {
		return this.cooldownTime > 0;
	}

	public boolean isOnCustomCooldown() {
		return this.cooldownTime > 2;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> p_59371_) {
		this.items = p_59371_;
	}

	public void entityInside(Entity entity) {
		if (entity instanceof ItemEntity && VoxelShapes.joinIsNotEmpty(VoxelShapes.create(
				entity.getBoundingBox().move((double) (-this.getBlockPos().getX()), (double) (-this.getBlockPos().getY()), (double) (-this.getBlockPos().getZ()))),
				this.getSuckShape(), IBooleanFunction.AND)) {
			tryMoveItems(() -> {
				return addItem(this, (ItemEntity) entity);
			});
		}

	}

	@Override
	protected Container createMenu(int p_59312_, PlayerInventory p_59313_) {
		return new EnchantedHopperContainer(p_59312_, p_59313_, this);
	}

	@Override
	protected net.minecraftforge.items.IItemHandler createUnSidedHandler() {
		return new EnchantedHopperItemHandler(this);
	}

	public long getLastUpdateTime() {
		return this.tickedGameTime;
	}
}
