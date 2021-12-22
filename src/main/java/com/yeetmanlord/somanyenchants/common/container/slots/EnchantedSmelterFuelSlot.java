package com.yeetmanlord.somanyenchants.common.container.slots;

import com.yeetmanlord.somanyenchants.common.container.AbstractEnchantedSmelterContainer;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class EnchantedSmelterFuelSlot extends Slot {
	private final AbstractEnchantedSmelterContainer smelterContainer;

	public EnchantedSmelterFuelSlot(AbstractEnchantedSmelterContainer smelterContainer, IInventory smelterInventory,
			int index, int xPos, int yPos) {
		super(smelterInventory, index, xPos, yPos);
		this.smelterContainer = smelterContainer;
	}

	/**
	 * Check if the stack is allowed to be placed in this slot, used for armor slots
	 * as well as furnace fuel.
	 */
	public boolean isItemValid(ItemStack stack) {
		return this.smelterContainer.isFuel(stack) || isBucket(stack);
	}

	public int getItemStackLimit(ItemStack stack) {
		return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
	}

	public static boolean isBucket(ItemStack stack) {
		return stack.getItem() == Items.BUCKET;
	}
}