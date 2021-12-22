package net.minecraft.enchantment;

import java.util.Random;
import java.util.Map.Entry;

import com.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ThornsEnchantment extends Enchantment {
	
   public ThornsEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
      super(rarityIn, EnchantmentType.ARMOR_CHEST, slots);
   }

   /**
    * Returns the minimal value of enchantability needed on the enchantment level passed.
    */
   public int getMinEnchantability(int enchantmentLevel) {
      if(enchantmentLevel <= 5)
      {
    	  return 5 + 8 * (enchantmentLevel - 1);
      } else
      {
    	  return 10 + 12 * (enchantmentLevel - 1);
      }
   }

   public int getMaxEnchantability(int enchantmentLevel) {
      return super.getMinEnchantability(enchantmentLevel) + 20;
   }

   /**
    * Returns the maximum level that the enchantment can have.
    */
   public int getMaxLevel() {
	   if(Config.thorns.isEnabled.get() == false)
		 {
			 return 3;
		 }
		 else return Config.thorns.maxLevel.get();
   }

   /**
    * Determines if this enchantment can be applied to a specific ItemStack.
    */
   public boolean canApply(ItemStack stack) {
      return stack.getItem() instanceof ArmorItem ? true : super.canApply(stack);
   }

   /**
    * Whenever an entity that has this enchantment on one of its associated items is damaged this method will be called.
    */
   public void onUserHurt(LivingEntity user, Entity attacker, int level) {
      Random random = user.getRNG();
      Entry<EquipmentSlotType, ItemStack> entry = EnchantmentHelper.getRandomItemWithEnchantment(Enchantments.THORNS, user);
      if (shouldHit(level, random)) {
         if (attacker != null) {
            attacker.attackEntityFrom(DamageSource.causeThornsDamage(user), (float)getDamage(level, random));
         }

         if (entry != null) {
            entry.getValue().damageItem(2, user, (livingEntity) -> {
               livingEntity.sendBreakAnimation(entry.getKey());
            });
         }
      }

   }

   public static boolean shouldHit(int level, Random rnd) {
      if (level <= 0) {
         return false;
      } else {
         return rnd.nextFloat() < 0.15F * (float)level;
      }
   }

   public static int getDamage(int level, Random rnd) {
      return level > 10 ? level - 10 : 1 + rnd.nextInt(4);
   }
}
