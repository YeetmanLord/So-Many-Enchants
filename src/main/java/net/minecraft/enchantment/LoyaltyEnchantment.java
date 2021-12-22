package net.minecraft.enchantment;

import com.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.inventory.EquipmentSlotType;

public class LoyaltyEnchantment extends Enchantment {
   public LoyaltyEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
      super(rarityIn, EnchantmentType.TRIDENT, slots);
   }

   /**
    * Returns the minimal value of enchantability needed on the enchantment level passed.
    */
   public int getMinEnchantability(int enchantmentLevel) {
      return 5 + enchantmentLevel * 5;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return 75;
   }

   /**
    * Returns the maximum level that the enchantment can have.
    */
   public int getMaxLevel() {
	   if(Config.loyalty.isEnabled.get() == false)
		 {
			 return 3;
		 }
		 else return Config.loyalty.maxLevel.get();
   }

   /**
    * Determines if the enchantment passed can be applyied together with this enchantment.
    */
   public boolean canApplyTogether(Enchantment ench) {
      return super.canApplyTogether(ench);
   }
}