package net.minecraft.enchantment;

import com.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.inventory.EquipmentSlotType;

public class QuickChargeEnchantment extends Enchantment {
   public QuickChargeEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slotTypes) {
      super(rarityIn, EnchantmentType.CROSSBOW, slotTypes);
   }

   /**
    * Returns the minimal value of enchantability needed on the enchantment level passed.
    */
   public int getMinEnchantability(int enchantmentLevel) {
      return 12 + (enchantmentLevel - 1) * 7;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return getMinEnchantability(enchantmentLevel) + 20;
   }

   /**
    * Returns the maximum level that the enchantment can have.
    */
   public int getMaxLevel() {
	   if(Config.q.isEnabled.get() == false)
		 {
			 return 3;
		 }
		 else return Config.q.maxLevel.get();
   }
}