package net.minecraft.enchantment;

import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.inventory.EquipmentSlotType;

public class SweepingEnchantment extends Enchantment {
   public SweepingEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
      super(rarityIn, EnchantmentType.WEAPON, slots);
   }

   /**
    * Returns the minimal value of enchantability needed on the enchantment level passed.
    */
   public int getMinEnchantability(int enchantmentLevel) {
      return 5 + (enchantmentLevel - 1) * 9;
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return this.getMinEnchantability(enchantmentLevel) + 15;
   }

   /**
    * Returns the maximum level that the enchantment can have.
    */
   public int getMaxLevel() {
      return 10;
   }

   public static float getSweepingDamageRatio(int level) {
      return 1.0F - 1.0F / (float)(level + 1);
   }
   
   @Override
	protected boolean canApplyTogether(Enchantment ench) 
   {
	   return super.canApplyTogether(ench) && ench != EnchantmentInit.CRITICAL.get() && ench != EnchantmentInit.FREEZING.get();
   }
}