package com.yeetmanlord.somanyenchants.common.container.slots;

import com.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.yeetmanlord.somanyenchants.common.blocks.override.OverridenShulkerBoxBlock;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class EnchantedShulkerBoxSlot extends Slot {
   public EnchantedShulkerBoxSlot(IInventory inventoryIn, int slotIndexIn, int xPosition, int yPosition) {
      super(inventoryIn, slotIndexIn, xPosition, yPosition);
   }

   /**
    * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
    */
   public boolean isItemValid(ItemStack stack) {
      return !(Block.getBlockFromItem(stack.getItem()) instanceof OverridenShulkerBoxBlock || Block.getBlockFromItem(stack.getItem()) instanceof EnchantedShulkerBoxBlock);
   }
}