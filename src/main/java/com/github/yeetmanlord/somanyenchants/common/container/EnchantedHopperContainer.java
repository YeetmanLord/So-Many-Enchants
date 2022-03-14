package com.github.yeetmanlord.somanyenchants.common.container;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;

import com.github.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class EnchantedHopperContainer extends AbstractContainerMenu {
   private final Container enchantedHopperInventory;

   public EnchantedHopperContainer(int id, Inventory playerInventory) {
      this(id, playerInventory, new SimpleContainer(5));
   }

   public EnchantedHopperContainer(int id, Inventory playerInventory, Container inventory) {
      super(ContainerTypeInit.ENCHANTED_HOPPER.get(), id);
      this.enchantedHopperInventory = inventory;
      checkContainerSize(inventory, 5);
      inventory.startOpen(playerInventory.player);
      int i = 51;

      for(int j = 0; j < 5; ++j) {
         this.addSlot(new Slot(inventory, j, 44 + j * 18, 20));
      }

      for(int l = 0; l < 3; ++l) {
         for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
         }
      }

      for(int i1 = 0; i1 < 9; ++i1) {
         this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 109));
      }

   }

   /**
    * Determines whether supplied player can use this container
    */
   @Override
public boolean stillValid(Player playerIn) {
      return this.enchantedHopperInventory.stillValid(playerIn);
   }

   /**
    * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
    * inventory and the other inventory(s).
    */
   @Override
public ItemStack quickMoveStack(Player playerIn, int index) {
      ItemStack itemstack = ItemStack.EMPTY;
      Slot slot = this.slots.get(index);
      if (slot != null && slot.hasItem()) {
         ItemStack itemstack1 = slot.getItem();
         itemstack = itemstack1.copy();
         if (index < this.enchantedHopperInventory.getContainerSize()) {
            if (!this.moveItemStackTo(itemstack1, this.enchantedHopperInventory.getContainerSize(), this.slots.size(), true)) {
               return ItemStack.EMPTY;
            }
         } else if (!this.moveItemStackTo(itemstack1, 0, this.enchantedHopperInventory.getContainerSize(), false)) {
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
      this.enchantedHopperInventory.stopOpen(playerIn);
   }
}