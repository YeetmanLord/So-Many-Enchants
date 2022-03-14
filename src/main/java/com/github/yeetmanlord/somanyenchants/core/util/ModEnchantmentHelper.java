package com.github.yeetmanlord.somanyenchants.core.util;

import java.util.HashMap;

import com.github.yeetmanlord.somanyenchants.common.tileentities.AbstractEnchantedSmelterTileEntity;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.core.Registry;

public class ModEnchantmentHelper 
{
	public static boolean hasEnchant(Enchantment enchant, ItemStack stack)
	{
		return getEnchantmentLevel(enchant, stack) > 0;
	}
	
	public static boolean hasCamouflage(ListTag nbt) {
		return getEnchantmentLevel(nbt, EnchantmentInit.CAMOUFLAGE.get()) > 0;
	}
	
	public static boolean isCavernousStorage(ListTag nbt) {
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
		return getMaxEnchantmentLevelArmor(EnchantmentInit.REINFORCED_ARMOR.get(), player, armorSlot);
	}
	
	
	
	public static int getTemperLevel(LivingEntity player, int armorSlot)
	{
		return getMaxEnchantmentLevelArmor(EnchantmentInit.TEMPERED_ARMOR.get(), player, armorSlot);
	}
	
	public static int getHeavyArmorLevel(LivingEntity player, int armorSlot)
	{
		return getMaxEnchantmentLevelArmor(EnchantmentInit.HEAVY_ARMOR.get(), player, armorSlot);
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
	      Iterable<ItemStack> iterable = enchantmentIn.getSlotItems(entityIn).values();
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
	      if(entityIn instanceof Player)
	      {
	    	  if(armorSlot >= 4)
	    	  {
	    		  armorSlot = 3;
	    	  }
	    	  ItemStack stack = ((Player)entityIn).inventory.armor.get(armorSlot);
	    	  if(stack.isEnchanted())
	    	  {
	    		  return getEnchantmentLevel(enchantmentIn, stack);
	    	  }
	      } 
	      return 0;
	}
	
	public static int getEnchantmentLevel(Enchantment enchID, ItemStack stack)
	{
	      if (stack.isEmpty()) {
	         return 0;
	      } else {
	         ResourceLocation resourcelocation = Registry.ENCHANTMENT.getKey(enchID);
	         ListTag listnbt = stack.getEnchantmentTags();

	         for(int i = 0; i < listnbt.size(); ++i) {
	            CompoundTag compoundnbt = listnbt.getCompound(i);
	            ResourceLocation resourcelocation1 = ResourceLocation.tryParse(compoundnbt.getString("id"));
	            if (resourcelocation1 != null && resourcelocation1.equals(resourcelocation)) {
	               return Mth.clamp(compoundnbt.getInt("lvl"), 0, 255);
	            }
	         }

	         return 0;
	      }
	}
	
	public static int getEnchantmentLevel(ListTag nbt, Enchantment ench)
	{
		ResourceLocation resourcelocation = Registry.ENCHANTMENT.getKey(ench);
		for(int i = 0; i < nbt.size(); ++i) {
            CompoundTag compoundnbt = nbt.getCompound(i);
            ResourceLocation resourcelocation1 = ResourceLocation.tryParse(compoundnbt.getString("id"));
            if (resourcelocation1 != null && resourcelocation1.equals(resourcelocation)) {
               return Mth.clamp(compoundnbt.getInt("lvl"), 0, 255);
            }
         }
		return 0;
	}
	
	public static HashMap<Enchantment, Integer> currentSmelterEnchantments(AbstractEnchantedSmelterTileEntity smelter)
	{
		HashMap<Enchantment, Integer> enchants = new HashMap<>();
		int i = getEnchantmentLevel(smelter.getEnchantments(), EnchantmentInit.FAST_SMELT.get());
		if(i > 0)
		{
			enchants.put(EnchantmentInit.FAST_SMELT.get(), i);
		}
		i = getEnchantmentLevel(smelter.getEnchantments(), EnchantmentInit.FUEL_EFFICIENT.get());
		if(i > 0)
		{
			enchants.put(EnchantmentInit.FUEL_EFFICIENT.get(), i);
		}
		i = getEnchantmentLevel(smelter.getEnchantments(), EnchantmentInit.EXTRA_EXPERIENCE.get());
		if(i > 0)
		{
			enchants.put(EnchantmentInit.EXTRA_EXPERIENCE.get(), i);
		}
		return enchants;
	}
}
