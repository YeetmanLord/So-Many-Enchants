package com.github.yeetmanlord.somanyenchants.common.container.slots;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class EnchantedShulkerBoxSlot extends Slot {
   public EnchantedShulkerBoxSlot(Container inventoryIn, int slotIndexIn, int xPosition, int yPosition) {
      super(inventoryIn, slotIndexIn, xPosition, yPosition);
   }

   /**
    * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
    */
   @Override
public boolean mayPlace(ItemStack stack) {
      return !(Block.byItem(stack.getItem()) instanceof ShulkerBoxBlock || Block.byItem(stack.getItem()) instanceof EnchantedShulkerBoxBlock);
   }
}