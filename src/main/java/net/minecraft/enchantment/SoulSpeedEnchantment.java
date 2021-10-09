package net.minecraft.enchantment;

import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.inventory.EquipmentSlotType;

public class SoulSpeedEnchantment extends Enchantment {
   public SoulSpeedEnchantment(Enchantment.Rarity rarity, EquipmentSlotType... slots) {
      super(rarity, EnchantmentType.ARMOR_FEET, slots);
   }

   /**
    * Returns the minimal value of enchantability needed on the enchantment level passed.
    */
   public int getMinEnchantability(int enchantmentLevel) {
      return enchantmentLevel * 8;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + 15;
   }

   public boolean isTreasureEnchantment() {
      return true;
   }

   /**
    * Checks if the enchantment can be sold by villagers in their trades.
    */
   public boolean canVillagerTrade() {
      return false;
   }

   /**
    * Checks if the enchantment can be applied to loot table drops.
    */
   public boolean canGenerateInLoot() {
      return false;
   }

   /**
    * Returns the maximum level that the enchantment can have.
    */
   public int getMaxLevel() {
	   if(Config.ss.isEnabled.get() == false)
		 {
			 return 3;
		 }
		 else return Config.ss.maxLevel.get();
   }
   
   public boolean canApplyTogether(Enchantment ench)
   {
	      return super.canApplyTogether(ench) && ench != EnchantmentInit.FLIGHT.get();
   }
}
