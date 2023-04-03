package com.github.yeetmanlord.somanyenchants.common.tileentities;

import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.Nullable;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedShulkerBoxContainer;
import com.github.yeetmanlord.somanyenchants.core.enums.EnchantedShulkerAnimationStatuses;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EnchantedShulkerBoxTileEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
	private static final int[] SLOTS = IntStream.range(0, 36).toArray();
	private NonNullList<ItemStack> items = NonNullList.withSize(36, ItemStack.EMPTY);
	private int openCount;
	private EnchantedShulkerAnimationStatuses animationStatus = EnchantedShulkerAnimationStatuses.CLOSED;
	private float progress;
	private float progressOld;
	@Nullable
	private DyeColor color;
	private boolean needsColorFromWorld;

	public EnchantedShulkerBoxTileEntity(@Nullable DyeColor colorIn, BlockPos pos, BlockState state) {
		super(BlockEntityTypeInit.ENCHANTED_SHULKER_BOX.get(), pos, state);
		this.color = colorIn;
	}

	public EnchantedShulkerBoxTileEntity(BlockPos pos, BlockState state) {
		this((DyeColor) null, pos, state);
		this.needsColorFromWorld = true;
	}

	public static void tick(Level world, BlockPos pos, BlockState state, EnchantedShulkerBoxTileEntity tile) {
		tile.updateAnimation(world, pos, state);
	}

	private void updateAnimation(Level world, BlockPos pos, BlockState state) {
		this.progressOld = this.progress;
		switch (this.animationStatus) {
		case CLOSED:
			this.progress = 0.0F;
			break;
		case OPENING:
			this.progress += 0.1F;
			if (this.progress >= 1.0F) {
				this.animationStatus = EnchantedShulkerAnimationStatuses.OPENED;
				this.progress = 1.0F;
				doNeighborUpdates(world, pos, state);
			}

			this.moveCollidedEntities(world, pos, state);
			break;
		case CLOSING:
			this.progress -= 0.1F;
			if (this.progress <= 0.0F) {
				this.animationStatus = EnchantedShulkerAnimationStatuses.CLOSED;
				this.progress = 0.0F;
				doNeighborUpdates(world, pos, state);
			}
			break;
		case OPENED:
			this.progress = 1.0F;
		}
	}

	public EnchantedShulkerAnimationStatuses getAnimationStatus() {
		return this.animationStatus;
	}

	public AABB getBoundingBox(BlockState state) {
		return Shulker.getProgressAabb(state.getValue(EnchantedShulkerBoxBlock.FACING), 0.5F * this.getProgress(1.0F));
	}

	private void moveCollidedEntities(Level world, BlockPos pos, BlockState state) {
		if (state.getBlock() instanceof EnchantedShulkerBoxBlock) {
			Direction direction = state.getValue(EnchantedShulkerBoxBlock.FACING);
			AABB aabb = Shulker.getProgressDeltaAabb(direction, this.progressOld, this.progress).move(pos);
			List<Entity> list = world.getEntities((Entity) null, aabb);
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); ++i) {
					Entity entity = list.get(i);
					if (entity.getPistonPushReaction() != PushReaction.IGNORE) {
						entity.move(MoverType.SHULKER_BOX,
								new Vec3((aabb.getXsize() + 0.01D) * (double) direction.getStepX(),
										(aabb.getYsize() + 0.01D) * (double) direction.getStepY(),
										(aabb.getZsize() + 0.01D) * (double) direction.getStepZ()));
					}
				}

			}
		}
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getContainerSize() {
		return this.items.size();
	}

	/**
	 * See {@link Block#eventReceived} for more information. This must return true
	 * serverside before it is called clientside.
	 */
	@Override
	public boolean triggerEvent(int p_59678_, int p_59679_) {
		if (p_59678_ == 1) {
			this.openCount = p_59679_;
			if (p_59679_ == 0) {
				this.animationStatus = EnchantedShulkerAnimationStatuses.CLOSING;
				doNeighborUpdates(this.getLevel(), this.worldPosition, this.getBlockState());
			}

			if (p_59679_ == 1) {
				this.animationStatus = EnchantedShulkerAnimationStatuses.OPENING;
				doNeighborUpdates(this.getLevel(), this.worldPosition, this.getBlockState());
			}

			return true;
		} else {
			return super.triggerEvent(p_59678_, p_59679_);
		}
	}

	private static void doNeighborUpdates(Level world, BlockPos pos, BlockState state) {
		state.updateNeighbourShapes(world, pos, 3);
	}

	@Override
	public void startOpen(Player player) {
		if (!player.isSpectator()) {
			if (this.openCount < 0) {
				this.openCount = 0;
			}

			++this.openCount;
			this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);
			if (this.openCount == 1) {
				this.level.playSound((Player) null, this.worldPosition, SoundEvents.SHULKER_BOX_OPEN,
						SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
			}
		}

	}

	@Override
	public void stopOpen(Player player) {
		if (!player.isSpectator()) {
			--this.openCount;
			this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);
			if (this.openCount <= 0) {
				this.level.playSound((Player) null, this.worldPosition, SoundEvents.SHULKER_BOX_CLOSE,
						SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
			}
		}

	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("container.enchantedShulkerBox");
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.loadFromNbt(nbt);
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		this.saveToNbt(compound);
	}

	public void loadFromNbt(CompoundTag compound) {
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(compound) && compound.contains("Items", 9)) {
			ContainerHelper.loadAllItems(compound, this.items);
		}

	}

	public CompoundTag saveToNbt(CompoundTag compound) {
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.items, false);
		}
		return compound;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) {
		this.items = itemsIn;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return SLOTS;
	}

	/**
	 * Returns true if automation can insert the given item in the given slot from
	 * the given side.
	 */
	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
		return !(Block.byItem(itemStackIn.getItem()) instanceof EnchantedShulkerBoxBlock);
	}

	/**
	 * Returns true if automation can extract the given item in the given slot from
	 * the given side.
	 */
	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return true;
	}

	public float getProgress(float progress) {
		return Mth.lerp(progress, this.progressOld, this.progress);
	}

	@Nullable
	@OnlyIn(Dist.CLIENT)
	public DyeColor getColor() {
		if (this.needsColorFromWorld) {
			this.color = EnchantedShulkerBoxBlock.getColorFromBlock(this.getBlockState().getBlock());
			this.needsColorFromWorld = false;
		}

		return this.color;
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory player) {
		return new EnchantedShulkerBoxContainer(id, player, this);
	}

	public boolean isVisuallyClosed() {
		return this.animationStatus == EnchantedShulkerAnimationStatuses.CLOSED;
	}

	@Override
	protected net.minecraftforge.items.IItemHandler createUnSidedHandler() {
		return new net.minecraftforge.items.wrapper.SidedInvWrapper(this, Direction.UP);
	}
}
