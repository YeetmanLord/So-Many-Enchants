package com.yeetmanlord.somanyenchants.common.container;

import com.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EnchantedChestContainer extends Container {
	private final IInventory lowerChestInventory;
	private final int numRows;

	private EnchantedChestContainer(ContainerType<?> type, int id, PlayerInventory player, int rows) {
		this(type, id, player, new Inventory(9 * rows), rows);
	}
	
	public static EnchantedChestContainer createGeneric9X3(int id, PlayerInventory player) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X3.get(), id, player, 3);
	}

	public static EnchantedChestContainer createGeneric9X4(int id, PlayerInventory player) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X4.get(), id, player, 4);
	}
	
	public static EnchantedChestContainer createGeneric9X6(int id, PlayerInventory player) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X6.get(), id, player, 6);
	}

	public static EnchantedChestContainer createGeneric9X8(int id, PlayerInventory player) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X8.get(), id, player, 8);
	}
	
	public static EnchantedChestContainer createGeneric9X3(int id, PlayerInventory player, IInventory blockEntity) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X3.get(), id, player, blockEntity, 3);
	}

	public static EnchantedChestContainer createGeneric9X4(int id, PlayerInventory player, IInventory blockEntity) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X4.get(), id, player, blockEntity, 4);
	}
	
	public static EnchantedChestContainer createGeneric9X6(int id, PlayerInventory player, IInventory blockEntity) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X6.get(), id, player, blockEntity, 6);
	}

	public static EnchantedChestContainer createGeneric9X8(int id, PlayerInventory player, IInventory blockEntity) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X8.get(), id, player, blockEntity, 8);
	}

	public EnchantedChestContainer(ContainerType<?> type, int id, PlayerInventory playerInventoryIn,
			IInventory tileInventory, int rows) {
		super(type, id);
		assertInventorySize(tileInventory, rows * 9);
		this.lowerChestInventory = tileInventory;
		this.numRows = rows;
		tileInventory.openInventory(playerInventoryIn.player);
		int i = (this.numRows - 4) * 18;
		for (int j = 0; j < this.numRows; j++) {
			for (int k = 0; k < 9; k++) {
				Slot slot = new Slot(tileInventory, k + j * 9, 8 + k * 18, 18 + j * 18);
				this.addSlot(slot);
			}
		}

		for (int l = 0; l < 3; ++l) {
			for (int j1 = 0; j1 < 9; ++j1) {
				this.addSlot(new Slot(playerInventoryIn, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
			}
		}

		for (int i1 = 0; i1 < 9; ++i1) {
			this.addSlot(new Slot(playerInventoryIn, i1, 8 + i1 * 18, 161 + i));
		}
		
	}

	/**
	 * Determines whether supplied player can use this container
	 */
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.lowerChestInventory.isUsableByPlayer(playerIn);
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
			if (index < this.numRows * 9) {
				if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false)) {
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
		this.lowerChestInventory.closeInventory(playerIn);
	}

	/**
	 * Gets the inventory associated with this chest container.
	 * 
	 * @see #field_75155_e
	 */
	public IInventory getLowerChestInventory() {
		return this.lowerChestInventory;
	}

	@OnlyIn(Dist.CLIENT)
	public int getNumRows() {
		return this.numRows;
	}
}