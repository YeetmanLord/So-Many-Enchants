package com.github.yeetmanlord.somanyenchants.common.container;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;

import com.github.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EnchantedChestContainer extends AbstractContainerMenu {
	private final Container lowerChestInventory;
	private final int numRows;

	private EnchantedChestContainer(MenuType<?> type, int id, Inventory player, int rows) {
		this(type, id, player, new SimpleContainer(9 * rows), rows);
	}
	
	public static EnchantedChestContainer createGeneric9X3(int id, Inventory player) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X3.get(), id, player, 3);
	}

	public static EnchantedChestContainer createGeneric9X4(int id, Inventory player) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X4.get(), id, player, 4);
	}
	
	public static EnchantedChestContainer createGeneric9X6(int id, Inventory player) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X6.get(), id, player, 6);
	}

	public static EnchantedChestContainer createGeneric9X8(int id, Inventory player) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X8.get(), id, player, 8);
	}
	
	public static EnchantedChestContainer createGeneric9X3(int id, Inventory player, Container blockEntity) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X3.get(), id, player, blockEntity, 3);
	}

	public static EnchantedChestContainer createGeneric9X4(int id, Inventory player, Container blockEntity) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X4.get(), id, player, blockEntity, 4);
	}
	
	public static EnchantedChestContainer createGeneric9X6(int id, Inventory player, Container blockEntity) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X6.get(), id, player, blockEntity, 6);
	}

	public static EnchantedChestContainer createGeneric9X8(int id, Inventory player, Container blockEntity) {
		return new EnchantedChestContainer(ContainerTypeInit.GENERIC_9X8.get(), id, player, blockEntity, 8);
	}

	public EnchantedChestContainer(MenuType<?> type, int id, Inventory playerInventoryIn,
			Container tileInventory, int rows) {
		super(type, id);
		checkContainerSize(tileInventory, rows * 9);
		this.lowerChestInventory = tileInventory;
		this.numRows = rows;
		tileInventory.startOpen(playerInventoryIn.player);
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
	@Override
	public boolean stillValid(Player playerIn) {
		return this.lowerChestInventory.stillValid(playerIn);
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
			if (index < this.numRows * 9) {
				if (!this.moveItemStackTo(itemstack1, this.numRows * 9, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, this.numRows * 9, false)) {
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
		this.lowerChestInventory.stopOpen(playerIn);
	}

	/**
	 * Gets the inventory associated with this chest container.
	 * 
	 * @see #container
	 */
	public Container getContainer() {
		return this.lowerChestInventory;
	}

	@OnlyIn(Dist.CLIENT)
	public int getNumRows() {
		return this.numRows;
	}
}