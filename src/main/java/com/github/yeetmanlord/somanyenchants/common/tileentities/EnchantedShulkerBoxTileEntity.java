package com.github.yeetmanlord.somanyenchants.common.tileentities;

import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.Nullable;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedShulkerBoxContainer;
import com.github.yeetmanlord.somanyenchants.core.enums.EnchantedShulkerAnimationStatuses;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EnchantedShulkerBoxTileEntity extends LockableLootTileEntity implements ISidedInventory, ITickableTileEntity {

	private static final int[] SLOTS = IntStream.range(0, 36).toArray();

	private NonNullList<ItemStack> items = NonNullList.withSize(36, ItemStack.EMPTY);

	private int openCount;

	private EnchantedShulkerAnimationStatuses animationStatus = EnchantedShulkerAnimationStatuses.CLOSED;

	private float progress;

	private float progressOld;

	@Nullable
	private DyeColor color;

	private boolean needsColorFromWorld;

	public EnchantedShulkerBoxTileEntity(@Nullable DyeColor colorIn) {

		super(BlockEntityTypeInit.ENCHANTED_SHULKER_BOX.get());
		this.color = colorIn;

	}

	public EnchantedShulkerBoxTileEntity() {

		this((DyeColor) null);
		this.needsColorFromWorld = true;

	}

	@Override
	public void tick() {

		this.updateAnimation();

		if (this.animationStatus == EnchantedShulkerAnimationStatuses.OPENING || this.animationStatus == EnchantedShulkerAnimationStatuses.CLOSING) {
			this.moveCollidedEntities();
		}

	}

	private void updateAnimation() {

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
					doNeighborUpdates();
				}

				this.moveCollidedEntities();
				break;

			case CLOSING:
				this.progress -= 0.1F;
				if (this.progress <= 0.0F) {
					this.animationStatus = EnchantedShulkerAnimationStatuses.CLOSED;
					this.progress = 0.0F;
					doNeighborUpdates();
				}
				break;

			case OPENED:
				this.progress = 1.0F;
		}

	}

	public EnchantedShulkerAnimationStatuses getAnimationStatus() {

		return this.animationStatus;

	}

	public AxisAlignedBB getBoundingBox(BlockState p_190584_1_) {

		return this.getBoundingBox(p_190584_1_.getValue(ShulkerBoxBlock.FACING));

	}

	public AxisAlignedBB getBoundingBox(Direction p_190587_1_) {

		float f = this.getProgress(1.0F);
		return VoxelShapes.block().bounds().expandTowards((double) (0.5F * f * (float) p_190587_1_.getStepX()),
				(double) (0.5F * f * (float) p_190587_1_.getStepY()),
				(double) (0.5F * f * (float) p_190587_1_.getStepZ()));

	}

	private AxisAlignedBB getTopBoundingBox(Direction p_190588_1_) {

		Direction direction = p_190588_1_.getOpposite();
		return this.getBoundingBox(p_190588_1_).contract((double) direction.getStepX(), (double) direction.getStepY(),
				(double) direction.getStepZ());

	}

	private void moveCollidedEntities() {

		BlockState state = this.level.getBlockState(this.getBlockPos());
		BlockPos pos = this.getBlockPos();
		World world = this.getLevel();

		if (state.getBlock() instanceof EnchantedShulkerBoxBlock) {
			Direction direction = state.getValue(EnchantedShulkerBoxBlock.FACING);
			AxisAlignedBB aabb = this.getTopBoundingBox(direction).move(this.worldPosition);
			List<Entity> list = world.getEntities((Entity) null, aabb);

			if (!list.isEmpty()) {

				for (int i = 0; i < list.size(); ++i) {
					Entity entity = list.get(i);

					if (entity.getPistonPushReaction() != PushReaction.IGNORE) {
						entity.move(MoverType.SHULKER_BOX,
								new Vector3d((aabb.getXsize() + 0.01D) * (double) direction.getStepX(), (aabb.getYsize() + 0.01D) * (double) direction.getStepY(), (aabb.getZsize() + 0.01D) * (double) direction.getStepZ()));
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

	@Override
	public boolean triggerEvent(int p_59678_, int p_59679_) {

		if (p_59678_ == 1) {
			this.openCount = p_59679_;

			if (p_59679_ == 0) {
				this.animationStatus = EnchantedShulkerAnimationStatuses.CLOSING;
				doNeighborUpdates();
			}

			if (p_59679_ == 1) {
				this.animationStatus = EnchantedShulkerAnimationStatuses.OPENING;
				doNeighborUpdates();
			}

			return true;
		}
		else {
			return super.triggerEvent(p_59678_, p_59679_);
		}

	}

	private void doNeighborUpdates() {

		this.getBlockState().updateNeighbourShapes(this.getLevel(), this.getBlockPos(), 3);

	}

	@Override
	public void startOpen(PlayerEntity player) {

		if (!player.isSpectator()) {

			if (this.openCount < 0) {
				this.openCount = 0;
			}

			++this.openCount;
			this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);

			if (this.openCount == 1) {
				this.level.playSound((PlayerEntity) null, this.worldPosition, SoundEvents.SHULKER_BOX_OPEN,
						SoundCategory.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
			}

		}

	}

	@Override
	public void stopOpen(PlayerEntity player) {

		if (!player.isSpectator()) {
			--this.openCount;
			this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);

			if (this.openCount <= 0) {
				this.level.playSound((PlayerEntity) null, this.worldPosition, SoundEvents.SHULKER_BOX_CLOSE,
						SoundCategory.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
			}

		}

	}

	@Override
	protected ITextComponent getDefaultName() {

		return new TranslationTextComponent("container.enchantedShulkerBox");

	}

	@Override
	public void load(BlockState blockState, CompoundNBT nbt) {

		super.load(blockState, nbt);
		this.loadFromNbt(nbt);

	}

	@Override public CompoundNBT save(CompoundNBT p_189515_1_) {

		super.save(p_189515_1_);
		return this.saveToNbt(p_189515_1_);

	}

	public void loadFromNbt(CompoundNBT compound) {

		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

		if (!this.tryLoadLootTable(compound) && compound.contains("Items", 9)) {
			ItemStackHelper.loadAllItems(compound, this.items);
		}

	}

	public CompoundNBT saveToNbt(CompoundNBT compound) {

		if (!this.trySaveLootTable(compound)) {
			ItemStackHelper.saveAllItems(compound, this.items, false);
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

		return MathHelper.lerp(progress, this.progressOld, this.progress);

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
	protected Container createMenu(int id, PlayerInventory player) {

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
