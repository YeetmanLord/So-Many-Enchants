package com.github.yeetmanlord.somanyenchants.common.container.slots;

import com.github.yeetmanlord.somanyenchants.common.container.AbstractEnchantedSmelterContainer;

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
	@Override
	public boolean mayPlace(ItemStack stack) {
		return this.smelterContainer.isFuel(stack) || isBucket(stack);
	}

	@Override
	public int getMaxStackSize(ItemStack stack) {
		return isBucket(stack) ? 1 : super.getMaxStackSize(stack);
	}

	public static boolean isBucket(ItemStack stack) {
		return stack.getItem() == Items.BUCKET;
	}
}