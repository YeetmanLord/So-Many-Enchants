package com.yeetmanlord.enchantsplus.core.init;

import com.yeetmanlord.enchantsplus.Main;
import com.yeetmanlord.enchantsplus.common.enchantments.FlightEnchantment;
import com.yeetmanlord.enchantsplus.common.enchantments.HealthBoostEnchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit 
{
	
	private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
	public static final DeferredRegister<Enchantment> 
		ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Main.MOD_ID);
	
	public static final RegistryObject<Enchantment> HEALTH_BOOST 
		= ENCHANTMENTS.register("health_boost", () -> new HealthBoostEnchant(Rarity.UNCOMMON, ARMOR_SLOTS));
	
	public static final RegistryObject<Enchantment> FLIGHT =
			ENCHANTMENTS.register("flight", () -> new FlightEnchantment(Rarity.COMMON, ARMOR_SLOTS));
}
