package com.github.yeetmanlord.somanyenchants.common.tileentities;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedBarrelBlock;
import com.github.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EnchantedBarrelTileEntity extends RandomizableContainerBlockEntity {
	private NonNullList<ItemStack> barrelContents = NonNullList.withSize(36, ItemStack.EMPTY);
	private ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
		@Override
		protected void onOpen(Level p_155062_, BlockPos p_155063_, BlockState p_155064_) {
			EnchantedBarrelTileEntity.this.playSound(p_155064_, SoundEvents.BARREL_OPEN);
			EnchantedBarrelTileEntity.this.updateBlockState(p_155064_, true);
		}

		@Override
		protected void onClose(Level p_155072_, BlockPos p_155073_, BlockState p_155074_) {
			EnchantedBarrelTileEntity.this.playSound(p_155074_, SoundEvents.BARREL_CLOSE);
			EnchantedBarrelTileEntity.this.updateBlockState(p_155074_, false);
		}

		@Override
		protected void openerCountChanged(Level p_155066_, BlockPos p_155067_, BlockState p_155068_, int p_155069_,
				int p_155070_) {
		}

		@Override
		protected boolean isOwnContainer(Player p_155060_) {
			if (p_155060_.containerMenu instanceof EnchantedChestContainer) {
				Container container = ((EnchantedChestContainer) p_155060_.containerMenu).getContainer();
				return container == EnchantedBarrelTileEntity.this;
			} else {
				return false;
			}
		}
	};

	private EnchantedBarrelTileEntity(BlockEntityType<?> barrelType, BlockPos pos, BlockState state) {
		super(barrelType, pos, state);
	}

	public EnchantedBarrelTileEntity(BlockPos pos, BlockState state) {
		this(BlockEntityTypeInit.ENCHANTED_BARREL.get(), pos, state);
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.barrelContents);
		}
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.barrelContents = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(nbt)) {
			ContainerHelper.loadAllItems(nbt, this.barrelContents);
		}

	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getContainerSize() {
		return 36;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.barrelContents;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) {
		this.barrelContents = itemsIn;
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("container.enchantedBarrel");
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory player) {
		return EnchantedChestContainer.createGeneric9X4(id, player, this);
	}

	@Override
	public void startOpen(Player p_58616_) {
		if (!this.remove && !p_58616_.isSpectator()) {
			this.openersCounter.incrementOpeners(p_58616_, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}

	}

	@Override
	public void stopOpen(Player p_58614_) {
		if (!this.remove && !p_58614_.isSpectator()) {
			this.openersCounter.decrementOpeners(p_58614_, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}

	}

	public void recheckOpen() {
		if (!this.remove) {
			this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
		}

	}

	private void updateBlockState(BlockState state, boolean open) {
		this.level.setBlock(this.getBlockPos(),
				state.setValue(EnchantedBarrelBlock.OPEN, Boolean.valueOf(open)), 3);
	}

	private void playSound(BlockState state, SoundEvent sound) {
		Vec3i vector3i = state.getValue(EnchantedBarrelBlock.FACING).getNormal();
		double d0 = (double) this.worldPosition.getX() + 0.5D + (double) vector3i.getX() / 2.0D;
		double d1 = (double) this.worldPosition.getY() + 0.5D + (double) vector3i.getY() / 2.0D;
		double d2 = (double) this.worldPosition.getZ() + 0.5D + (double) vector3i.getZ() / 2.0D;
		this.level.playSound((Player) null, d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F,
				this.level.random.nextFloat() * 0.1F + 0.9F);
	}
}