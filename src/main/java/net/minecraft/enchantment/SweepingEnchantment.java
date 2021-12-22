package net.minecraft.enchantment;

import com.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.inventory.EquipmentSlotType;

public class SweepingEnchantment extends Enchantment {
   public SweepingEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
      super(rarityIn, EnchantmentType.WEAPON, slots);
   }

   /**
    * Returns the minimal value of enchantability needed on the enchantment level passed.
    */
   public int getMinEnchantability(int enchantmentLevel) {
      return 5 + (enchantmentLevel - 1) * 5;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + 10;
   }

   /**
    * Returns the maximum level that the enchantment can have.
    */
   public int getMaxLevel() {
	   if(Config.sweeping.isEnabled.get() == false)
		 {
			 return 3;
		 }
		 else return Config.sweeping.maxLevel.get();
   }

   public static float getSweepingDamageRatio(int level) {
      return 1.0F - 1.0F / (float)(level + 1);
   }
}