package net.minecraft.enchantment;

import com.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.inventory.EquipmentSlotType;

public class PiercingEnchantment extends Enchantment {
   public PiercingEnchantment(Enchantment.Rarity rarity, EquipmentSlotType... slots) {
      super(rarity, EnchantmentType.CROSSBOW, slots);
   }

   /**
    * Returns the minimal value of enchantability needed on the enchantment level passed.
    */
   public int getMinEnchantability(int enchantmentLevel) {
      return 1 + (enchantmentLevel - 1) * 7;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return getMinEnchantability(enchantmentLevel) + 20;
   }

   /**
    * Returns the maximum level that the enchantment can have.
    */
   public int getMaxLevel() {
	   if(Config.piercing.isEnabled.get() == false)
		 {
			 return 4;
		 }
		 else return Config.piercing.maxLevel.get();
   }

   /**
    * Determines if the enchantment passed can be applyied together with this enchantment.
    */
   public boolean canApplyTogether(Enchantment ench) {
      return super.canApplyTogether(ench) && ench != Enchantments.MULTISHOT;
   }
}