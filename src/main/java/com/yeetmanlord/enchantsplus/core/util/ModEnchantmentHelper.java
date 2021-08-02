package com.yeetmanlord.enchantsplus.core.util;

import com.yeetmanlord.enchantsplus.core.init.EnchantmentInit;

import net.minecraft.enchantment.Enchantment;
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
	public static int getFreezingEnchant(LivingEntity player) {
	      return getMaxEnchantmentLevel(EnchantmentInit.FREEZING.get(), player);
	}
	
	public static int getReplantEnchant(LivingEntity player)
	{
		return getMaxEnchantmentLevel(EnchantmentInit.REPLANTING.get(), player);
	}
	
	
	public static int getReinforcementValue(LivingEntity player, int armorSlot)
	{
		return getMaxEnchantmentLevelArmor(EnchantmentInit.REINFORCEMENT.get(), player, armorSlot);
	}
	
	public static int getTemperValue(LivingEntity player, int armorSlot)
	{
		return getMaxEnchantmentLevelArmor(EnchantmentInit.TEMPER.get(), player, armorSlot);
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
	    	  ItemStack stack = ((PlayerEntity)entityIn).inventory.armorItemInSlot(armorSlot);
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
}
