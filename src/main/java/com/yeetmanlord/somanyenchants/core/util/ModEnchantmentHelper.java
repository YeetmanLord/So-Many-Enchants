package com.yeetmanlord.somanyenchants.core.util;

import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

public class ModEnchantmentHelper 
{
	public static boolean hasCamouflage(ListNBT nbt) {
		return getEnchantmentLevel(nbt, EnchantmentInit.CAMOUFLAGE.get()) > 0;
	}
	
	public static boolean isCavernousStorage(ListNBT nbt) {
		return getEnchantmentLevel(nbt, EnchantmentInit.CAVERNOUS_STORAGE.get()) > 0;
	}
	
	public static int getFreezingEnchant(LivingEntity player) {
	      return getMaxEnchantmentLevel(EnchantmentInit.FREEZING.get(), player);
	}
	
	public static int getStepAssistLevel(LivingEntity player) {
		return getMaxEnchantmentLevelArmor(EnchantmentInit.STEP_ASSIST.get(), player, 0);
	}
	
	public static int getSharpnessLevel(LivingEntity player) {
	      return getMaxEnchantmentLevel(Enchantments.SHARPNESS, player);
	}
	
	public static int getReplantEnchant(LivingEntity player)
	{
		return getMaxEnchantmentLevel(EnchantmentInit.REPLANTING.get(), player);
	}
	
	
	public static int getReinforcementLevel(LivingEntity player, int armorSlot)
	{
		return getMaxEnchantmentLevelArmor(EnchantmentInit.REINFORCEMENT.get(), player, armorSlot);
	}
	
	
	
	public static int getTemperLevel(LivingEntity player, int armorSlot)
	{
		return getMaxEnchantmentLevelArmor(EnchantmentInit.TEMPER.get(), player, armorSlot);
	}
	
	public static int getHeavyArmorLevel(LivingEntity player, int armorSlot)
	{
		return getMaxEnchantmentLevelArmor(EnchantmentInit.HEAVY.get(), player, armorSlot);
	}
	
	public static int getCriticalLevel(LivingEntity player)
	{
		return getMaxEnchantmentLevel(EnchantmentInit.CRITICAL.get(), player);
	}
	
	public static int getDoubleBreakLevel(LivingEntity player)
	{
		return getMaxEnchantmentLevel(EnchantmentInit.DOUBLE_BREAK.get(), player);
	}
	
	public static boolean hasCatVision(LivingEntity player)
	{
		return getMaxEnchantmentLevelArmor(EnchantmentInit.CAT_VISION.get(), player, 3) > 0;
	}
	
	public static boolean hasFastHopper(LivingEntity player)
	{
		return getMaxEnchantmentLevel(EnchantmentInit.FAST_HOPPER.get(), player) > 0;
	}
	
	public static int getMaxEnchantmentLevel(Enchantment enchantmentIn, LivingEntity entityIn) 
	{
	      Iterable<ItemStack> iterable = enchantmentIn.getEntityEquipment(entityIn).values();
	      if (iterable == null) {
	         return 0;
	      } else {
	         int i = 0;

	         for(ItemStack itemstack : iterable) {
	            int j = getEnchantmentLevel(enchantmentIn, itemstack);
	            if (j > i) {
	               i = j;
	            }
	         }

	         return i;
	      }
	}
	
	public static int getMaxEnchantmentLevelArmor(Enchantment enchantmentIn, LivingEntity entityIn, int armorSlot) 
	{
	      if(entityIn instanceof PlayerEntity)
	      {
	    	  if(armorSlot >= 4)
	    	  {
	    		  armorSlot = 3;
	    	  }
	    	  ItemStack stack = ((PlayerEntity)entityIn).inventory.armorInventory.get(armorSlot);
	    	  if(stack.isEnchanted())
	    	  {
	    		  return getEnchantmentLevel(enchantmentIn, stack);
	    	  }
	      } 
	      return 0;
	}
	
	@SuppressWarnings("deprecation")
	public static int getEnchantmentLevel(Enchantment enchID, ItemStack stack)
	{
	      if (stack.isEmpty()) {
	         return 0;
	      } else {
	         ResourceLocation resourcelocation = Registry.ENCHANTMENT.getKey(enchID);
	         ListNBT listnbt = stack.getEnchantmentTagList();

	         for(int i = 0; i < listnbt.size(); ++i) {
	            CompoundNBT compoundnbt = listnbt.getCompound(i);
	            ResourceLocation resourcelocation1 = ResourceLocation.tryCreate(compoundnbt.getString("id"));
	            if (resourcelocation1 != null && resourcelocation1.equals(resourcelocation)) {
	               return MathHelper.clamp(compoundnbt.getInt("lvl"), 0, 255);
	            }
	         }

	         return 0;
	      }
	}
	
	public static int getEnchantmentLevel(ListNBT nbt, Enchantment ench)
	{
		ResourceLocation resourcelocation = Registry.ENCHANTMENT.getKey(ench);
		for(int i = 0; i < nbt.size(); ++i) {
            CompoundNBT compoundnbt = nbt.getCompound(i);
            ResourceLocation resourcelocation1 = ResourceLocation.tryCreate(compoundnbt.getString("id"));
            if (resourcelocation1 != null && resourcelocation1.equals(resourcelocation)) {
               return MathHelper.clamp(compoundnbt.getInt("lvl"), 0, 255);
            }
         }
		return 0;
	}
}
