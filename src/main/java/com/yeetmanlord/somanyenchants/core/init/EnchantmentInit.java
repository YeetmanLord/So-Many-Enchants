package com.yeetmanlord.somanyenchants.core.init;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.ArmorHeavyEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.ArmorReinforcementEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.ArmorTemperEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.CatVisionEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.FlightEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.HealthBoostEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.StepAssistEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.tools.BlockReachEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.tools.DoubleBreakEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.tools.ReplantingEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.weapons.AttackReachEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.weapons.CriticalEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.weapons.FreezingEnchant;

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
	
	//Can enchant at table
	public static final RegistryObject<Enchantment> HEALTH_BOOST 
		= ENCHANTMENTS.register("health_boost", () -> new HealthBoostEnchant(Rarity.UNCOMMON, ARMOR_SLOTS));
	
	public static final RegistryObject<Enchantment> REINFORCEMENT = ENCHANTMENTS.register("reinforcement", () -> new ArmorReinforcementEnchant(Rarity.RARE, ARMOR_SLOTS));
	
	public static final RegistryObject<Enchantment> TEMPER = ENCHANTMENTS.register("temper", () -> new ArmorTemperEnchant(Rarity.RARE, ARMOR_SLOTS));
	
	public static final RegistryObject<Enchantment> HEAVY = ENCHANTMENTS.register("heavy", () -> new ArmorHeavyEnchant(Rarity.VERY_RARE, ARMOR_SLOTS));
	
	public static final RegistryObject<Enchantment> CAT_VISION = ENCHANTMENTS.register("cat_vision", () -> new CatVisionEnchant(Rarity.UNCOMMON, ARMOR_SLOTS));
	
	public static final RegistryObject<Enchantment> BLOCK_REACH = ENCHANTMENTS.register("block_reach", () -> new BlockReachEnchant(Rarity.VERY_RARE, EquipmentSlotType.MAINHAND));
	
	public static final RegistryObject<Enchantment> DOUBLE_BREAK = ENCHANTMENTS.register("double_break", () -> new DoubleBreakEnchant(Rarity.VERY_RARE, EquipmentSlotType.MAINHAND));
	
	public static final RegistryObject<Enchantment> REPLANTING = ENCHANTMENTS.register("replanting", () -> new ReplantingEnchant(Rarity.UNCOMMON, EquipmentSlotType.MAINHAND));
	
	public static final RegistryObject<Enchantment> FREEZING = ENCHANTMENTS.register("freezing_attack", () -> new FreezingEnchant(Rarity.UNCOMMON, EquipmentSlotType.MAINHAND));
	
	public static final RegistryObject<Enchantment> STEP_ASSIST = ENCHANTMENTS.register("step_assist", () -> new StepAssistEnchant(Rarity.RARE, ARMOR_SLOTS));
	
	
	
	
	
	//Cannot enchant at table
	public static final RegistryObject<Enchantment> FLIGHT =
			ENCHANTMENTS.register("flight", () -> new FlightEnchant(Rarity.VERY_RARE, EquipmentSlotType.FEET));
	
	
	public static final RegistryObject<Enchantment> ATTACK_REACH = ENCHANTMENTS.register("attack_reach", () -> new AttackReachEnchant(Rarity.VERY_RARE, EquipmentSlotType.MAINHAND));
	
	public static final RegistryObject<Enchantment> CRITICAL = ENCHANTMENTS.register("critical", () -> new CriticalEnchant(Rarity.VERY_RARE, EquipmentSlotType.MAINHAND));
}
