package com.github.yeetmanlord.somanyenchants.common.container;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;

import com.github.yeetmanlord.somanyenchants.common.container.slots.EnchantedShulkerBoxSlot;
import com.github.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class EnchantedShulkerBoxContainer extends AbstractContainerMenu {
	private final Container inventory;

	public EnchantedShulkerBoxContainer(int id, Inventory playerInventory) {
	      this(id, playerInventory, new SimpleContainer(36));
	   }

	public EnchantedShulkerBoxContainer(int id, Inventory playerInventory, Container inventory) {
	      super(ContainerTypeInit.ENCHANTED_SHULKER_BOX.get(), id);
	      checkContainerSize(inventory, 36);
	      this.inventory = inventory;
	      inventory.startOpen(playerInventory.player);

	      for(int k = 0; k < 4; ++k) {
	         for(int l = 0; l < 9; ++l) {
	            this.addSlot(new EnchantedShulkerBoxSlot	(inventory, l + k * 9, 8 + l * 18, 18 + k * 18));
	         }
	      }

	      for(int i1 = 0; i1 < 3; ++i1) {
	         for(int k1 = 0; k1 < 9; ++k1) {
	            this.addSlot(new Slot(playerInventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 104 + i1 * 18));
	         }
	      }

	      for(int j1 = 0; j1 < 9; ++j1) {
	         this.addSlot(new Slot(playerInventory, j1, 8 + j1 * 18, 162));
	      }

	   }

	/**
	 * Determines whether supplied player can use this container
	 */
	@Override
	public boolean stillValid(Player playerIn) {
		return this.inventory.stillValid(playerIn);
	}

	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this
	 * moves the stack between the player inventory and the other inventory(s).
	 */
	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < this.inventory.getContainerSize()) {
				if (!this.moveItemStackTo(itemstack1, this.inventory.getContainerSize(), this.slots.size(),
						true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, this.inventory.getContainerSize(), false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}

		return itemstack;
	}

	/**
	 * Called when the container is closed.
	 */
	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		this.inventory.stopOpen(playerIn);
	}
}