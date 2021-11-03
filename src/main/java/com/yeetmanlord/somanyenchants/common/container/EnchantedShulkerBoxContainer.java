package com.yeetmanlord.somanyenchants.common.container;

import com.yeetmanlord.somanyenchants.common.container.slots.EnchantedShulkerBoxSlot;
import com.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class EnchantedShulkerBoxContainer extends Container {
	private final IInventory inventory;

	public EnchantedShulkerBoxContainer(int id, PlayerInventory playerInventory) {
	      this(id, playerInventory, new Inventory(36));
	   }

	public EnchantedShulkerBoxContainer(int id, PlayerInventory playerInventory, IInventory inventory) {
	      super(ContainerTypeInit.ENCHANTED_SHULKER_BOX.get(), id);
	      assertInventorySize(inventory, 36);
	      this.inventory = inventory;
	      inventory.openInventory(playerInventory.player);

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
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.inventory.isUsableByPlayer(playerIn);
	}

	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this
	 * moves the stack between the player inventory and the other inventory(s).
	 */
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < this.inventory.getSizeInventory()) {
				if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(),
						true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	/**
	 * Called when the container is closed.
	 */
	public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);
		this.inventory.closeInventory(playerIn);
	}
}