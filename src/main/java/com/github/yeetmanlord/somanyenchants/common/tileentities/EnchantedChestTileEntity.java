package com.github.yeetmanlord.somanyenchants.common.tileentities;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedChestBlock;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IChestLid.class)
public class EnchantedChestTileEntity extends LockableLootTileEntity implements IChestLid, ITickableTileEntity {
	private NonNullList<ItemStack> items = NonNullList.withSize(36, ItemStack.EMPTY);
	protected float openness;
	protected float oOpenness;
	protected int openCount;
	private int tickInterval;
	private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;

	protected EnchantedChestTileEntity(TileEntityType<?> type) {
		super(type);
	}

	public EnchantedChestTileEntity() {
		this(BlockEntityTypeInit.ENCHANTED_CHEST.get());
	}

	@Override public int getContainerSize() {
		return 36;
	}

	@Override protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.enchanted_chest");
	}

	@Override public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		super.load(p_230337_1_, p_230337_2_);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(p_230337_2_)) {
			ItemStackHelper.loadAllItems(p_230337_2_, this.items);
		}

	}

	@Override public CompoundNBT save(CompoundNBT p_189515_1_) {
		super.save(p_189515_1_);
		if (!this.trySaveLootTable(p_189515_1_)) {
			ItemStackHelper.saveAllItems(p_189515_1_, this.items);
		}

		return p_189515_1_;
	}

	@Override
	public void tick() {
		int i = this.worldPosition.getX();
		int j = this.worldPosition.getY();
		int k = this.worldPosition.getZ();
		++this.tickInterval;
		this.openCount = getOpenCount(this.level, this, this.tickInterval, i, j, k, this.openCount);
		this.oOpenness = this.openness;
		float f = 0.1F;
		if (this.openCount > 0 && this.openness == 0.0F) {
			this.playSound(SoundEvents.CHEST_OPEN);
		}

		if (this.openCount == 0 && this.openness > 0.0F || this.openCount > 0 && this.openness < 1.0F) {
			float f1 = this.openness;
			if (this.openCount > 0) {
				this.openness += 0.1F;
			} else {
				this.openness -= 0.1F;
			}

			if (this.openness > 1.0F) {
				this.openness = 1.0F;
			}

			float f2 = 0.5F;
			if (this.openness < 0.5F && f1 >= 0.5F) {
				this.playSound(SoundEvents.CHEST_CLOSE);
			}

			if (this.openness < 0.0F) {
				this.openness = 0.0F;
			}
		}

	}

	public static int getOpenCount(World p_213977_0_, LockableTileEntity p_213977_1_, int p_213977_2_, int p_213977_3_,
			int p_213977_4_, int p_213977_5_, int p_213977_6_) {
		if (!p_213977_0_.isClientSide && p_213977_6_ != 0
				&& (p_213977_2_ + p_213977_3_ + p_213977_4_ + p_213977_5_) % 200 == 0) {
			p_213977_6_ = getOpenCount(p_213977_0_, p_213977_1_, p_213977_3_, p_213977_4_, p_213977_5_);
		}

		return p_213977_6_;
	}

	public static int getOpenCount(World p_213976_0_, LockableTileEntity p_213976_1_, int p_213976_2_, int p_213976_3_,
			int p_213976_4_) {
		int i = 0;
		float f = 5.0F;

		for (PlayerEntity playerentity : p_213976_0_.getEntitiesOfClass(PlayerEntity.class,
				new AxisAlignedBB((double) ((float) p_213976_2_ - 5.0F), (double) ((float) p_213976_3_ - 5.0F),
						(double) ((float) p_213976_4_ - 5.0F), (double) ((float) (p_213976_2_ + 1) + 5.0F),
						(double) ((float) (p_213976_3_ + 1) + 5.0F), (double) ((float) (p_213976_4_ + 1) + 5.0F)))) {
			if (playerentity.containerMenu instanceof EnchantedChestContainer) {
				IInventory iinventory = ((EnchantedChestContainer) playerentity.containerMenu).getContainer();
				if (iinventory == p_213976_1_ || iinventory instanceof DoubleSidedInventory
						&& ((DoubleSidedInventory) iinventory).contains(p_213976_1_)) {
					++i;
				}
			}
		}

		return i;
	}

	private void playSound(SoundEvent p_195483_1_) {
		ChestType chesttype = this.getBlockState().getValue(EnchantedChestBlock.TYPE);
		if (chesttype != ChestType.LEFT) {
			double d0 = (double) this.worldPosition.getX() + 0.5D;
			double d1 = (double) this.worldPosition.getY() + 0.5D;
			double d2 = (double) this.worldPosition.getZ() + 0.5D;
			if (chesttype == ChestType.RIGHT) {
				Direction direction = EnchantedChestBlock.getConnectedDirection(this.getBlockState());
				d0 += (double) direction.getStepX() * 0.5D;
				d2 += (double) direction.getStepZ() * 0.5D;
			}

			this.level.playSound((PlayerEntity) null, d0, d1, d2, p_195483_1_, SoundCategory.BLOCKS, 0.5F,
					this.level.random.nextFloat() * 0.1F + 0.9F);
		}
	}

	@Override public boolean triggerEvent(int p_145842_1_, int p_145842_2_) {
		if (p_145842_1_ == 1) {
			this.openCount = p_145842_2_;
			return true;
		} else {
			return super.triggerEvent(p_145842_1_, p_145842_2_);
		}
	}

	@Override public void startOpen(PlayerEntity p_174889_1_) {
		if (!p_174889_1_.isSpectator()) {
			if (this.openCount < 0) {
				this.openCount = 0;
			}

			++this.openCount;
			this.signalOpenCount();
		}

	}

	@Override public void stopOpen(PlayerEntity p_174886_1_) {
		if (!p_174886_1_.isSpectator()) {
			--this.openCount;
			this.signalOpenCount();
		}

	}

	protected void signalOpenCount() {
		Block block = this.getBlockState().getBlock();
		if (block instanceof EnchantedChestBlock) {
			this.level.blockEvent(this.worldPosition, block, 1, this.openCount);
			this.level.updateNeighborsAt(this.worldPosition, block);
		}

	}

	@Override protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override protected void setItems(NonNullList<ItemStack> p_199721_1_) {
		this.items = p_199721_1_;
	}

	@Override @OnlyIn(Dist.CLIENT)
	public float getOpenNess(float p_195480_1_) {
		return MathHelper.lerp(p_195480_1_, this.oOpenness, this.openness);
	}

	public static int getOpenCount(IBlockReader p_195481_0_, BlockPos p_195481_1_) {
		BlockState blockstate = p_195481_0_.getBlockState(p_195481_1_);
		if (blockstate.hasTileEntity()) {
			TileEntity tileentity = p_195481_0_.getBlockEntity(p_195481_1_);
			if (tileentity instanceof EnchantedChestTileEntity) {
				return ((EnchantedChestTileEntity) tileentity).openCount;
			}
		}

		return 0;
	}

	public static void swapContents(EnchantedChestTileEntity p_199722_0_, EnchantedChestTileEntity p_199722_1_) {
		NonNullList<ItemStack> nonnulllist = p_199722_0_.getItems();
		p_199722_0_.setItems(p_199722_1_.getItems());
		p_199722_1_.setItems(nonnulllist);
	}

	@Override protected Container createMenu(int p_213906_1_, PlayerInventory p_213906_2_) {
		return EnchantedChestContainer.createGeneric9X4(p_213906_1_, p_213906_2_, this);
	}

	@Override
	public void clearCache() {
		super.clearCache();
		if (this.chestHandler != null) {
			net.minecraftforge.common.util.LazyOptional<?> oldHandler = this.chestHandler;
			this.chestHandler = null;
			oldHandler.invalidate();
		}
	}

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(
			net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
		if (!this.remove && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (this.chestHandler == null)
				this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
			return this.chestHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	private net.minecraftforge.items.IItemHandlerModifiable createHandler() {
		BlockState state = this.getBlockState();
		if (!(state.getBlock() instanceof EnchantedChestBlock)) {
			return new net.minecraftforge.items.wrapper.InvWrapper(this);
		}
		IInventory inv = EnchantedChestBlock.getContainer((EnchantedChestBlock) state.getBlock(), state, getLevel(),
				getBlockPos(), true);
		return new net.minecraftforge.items.wrapper.InvWrapper(inv == null ? this : inv);
	}

	@Override
	protected void invalidateCaps() {
		super.invalidateCaps();
		if (chestHandler != null)
			chestHandler.invalidate();
	}
}