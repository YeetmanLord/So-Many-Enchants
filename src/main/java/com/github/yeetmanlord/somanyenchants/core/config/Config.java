package com.github.yeetmanlord.somanyenchants.core.config;

import java.util.HashMap;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

	public static boolean hasInit = false;

	public static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

	public static final ForgeConfigSpec config;

	public static final ForgeConfigSpec SyncedServerConfig;

	public static final HashMap<String, EnchantmentConfig> configSections = new HashMap<>();

	public static VanillaEnchantmentConfig damageEnchantments;

	public static VanillaEnchantmentConfig efficiency;

	public static VanillaEnchantmentConfig fireAspect;

	public static VanillaEnchantmentConfig impaling;

	public static VanillaEnchantmentConfig knockback;

	public static VanillaEnchantmentConfig lootBonusEnchantments;

	public static VanillaEnchantmentConfig loyalty;

	public static VanillaEnchantmentConfig lure;

	public static VanillaEnchantmentConfig piercing;

	public static VanillaEnchantmentConfig power;

	public static VanillaEnchantmentConfig protectionEnchantments;

	public static VanillaEnchantmentConfig punch;

	public static VanillaEnchantmentConfig quickCharge;

	public static VanillaEnchantmentConfig respiration;

	public static VanillaEnchantmentConfig riptide;

	public static VanillaEnchantmentConfig soulSpeed;

	public static VanillaEnchantmentConfig sweeping;

	public static VanillaEnchantmentConfig thorns;

	public static VanillaEnchantmentConfig unbreaking;

	public static VillagerConfig villager;

	public static EnchantmentConfig attackReach;

	public static EnchantmentConfig blockReach;

	public static EnchantmentConfig catVision;

	public static EnchantmentConfig camouflage;

	public static EnchantmentConfig cavernousStorage;

	public static EnchantmentConfig critical;

	public static EnchantmentConfig doubleBreak;

	public static EnchantmentConfig fastHopper;

	public static EnchantmentConfig flight;

	public static EnchantmentConfig freezing;

	public static EnchantmentConfig healthBoost;

	public static EnchantmentConfig reinforcement;

	public static EnchantmentConfig heavyArmor;

	public static EnchantmentConfig stepAssist;

	public static EnchantmentConfig replanting;

	public static EnchantmentConfig temper;

	public static EnchantmentConfig fastSmelt;

	public static EnchantmentConfig heavyBlade;

	public static EnchantmentConfig lightBlade;

	public static EnchantmentConfig fuelEfficient;

	public static EnchantmentConfig extraExperience;

	// Effect Enchantments
	public static EnchantmentConfig blindness;

	public static EnchantmentConfig fireResistance;

	public static EnchantmentConfig haste;

	public static EnchantmentConfig hunger;

	public static EnchantmentConfig invisibility;

	public static EnchantmentConfig jumpBoost;

	public static EnchantmentConfig miningFatigue;

	public static EnchantmentConfig nausea;

	public static EnchantmentConfig poison;

	public static EnchantmentConfig regeneration;

	public static EnchantmentConfig resistance;

	public static EnchantmentConfig saturation;

	public static EnchantmentConfig slowFalling;

	public static EnchantmentConfig slowness;

	public static EnchantmentConfig speed;

	public static EnchantmentConfig strength;

	public static EnchantmentConfig waterBreathing;

	public static EnchantmentConfig weakness;

	static {

		if (!hasInit) {
			damageEnchantments = new VanillaEnchantmentConfig(10, "Damage Enchantments", 10, 5);
			efficiency = new VanillaEnchantmentConfig(10, "Efficiency", 10, 5);
			fireAspect = new VanillaEnchantmentConfig(10, "Fire Aspect", 10, 2);
			impaling = new VanillaEnchantmentConfig(10, "Impaling", 10, 5);
			knockback = new VanillaEnchantmentConfig(10, "Knockback", 10, 2);
			lootBonusEnchantments = new VanillaEnchantmentConfig(10, "Loot Bonus Enchantments", 10, 3);
			loyalty = new VanillaEnchantmentConfig(5, "Loyalty", 5, 3);
			lure = new VanillaEnchantmentConfig(5, "Lure", 5, 3);
			piercing = new VanillaEnchantmentConfig(10, "Piercing", 10, 4);
			power = new VanillaEnchantmentConfig(10, "Power", 10, 5);
			protectionEnchantments = new VanillaEnchantmentConfig(10, "Protection Enchantments", 10, 4);
			punch = new VanillaEnchantmentConfig(10, "Punch", 10, 2);
			quickCharge = new VanillaEnchantmentConfig(5, "Quick Charge", 5, 3);
			respiration = new VanillaEnchantmentConfig(5, "Respiration", 5, 3);
			riptide = new VanillaEnchantmentConfig(5, "Riptide", 5, 3);
			soulSpeed = new VanillaEnchantmentConfig(5, "Soul Speed", 5, 3);
			sweeping = new VanillaEnchantmentConfig(10, "Sweeping Edge", 10, 3);
			thorns = new VanillaEnchantmentConfig(10, "Thorns", 10, 3);
			unbreaking = new VanillaEnchantmentConfig(10, "Unbreaking", 10, 3);
			villager = new VillagerConfig();

			attackReach = new EnchantmentConfig(10, "Attack Reach", 1, false);
			blockReach = new EnchantmentConfig(10, "Block Reach", 3, false);
			catVision = new EnchantmentConfig(1, "Cat Vision", 1, true);
			camouflage = new EnchantmentConfig(1, "Camouflage", 1, true);
			critical = new EnchantmentConfig(5, "Critical", 5, false);
			cavernousStorage = new EnchantmentConfig(1, "Cavernous Storage", 1, false);
			doubleBreak = new EnchantmentConfig(5, "Double Break", 5, false);
			fastHopper = new EnchantmentConfig(1, "Fast Hopper", 1, true);
			flight = new EnchantmentConfig(3, "Flight", 3, false);
			freezing = new EnchantmentConfig(10, "Freezing", 3, true);
			healthBoost = new EnchantmentConfig(10, "Health Boost", 5, true);
			heavyArmor = new EnchantmentConfig(10, "Heavy", 5, true);
			reinforcement = new EnchantmentConfig(10, "Reinforcement", 5, true);
			replanting = new EnchantmentConfig(1, "Replanting", 1, true);
			stepAssist = new EnchantmentConfig(3, "Step Assist", 3, false);
			temper = new EnchantmentConfig(10, "Temper", 5, true);
			fastSmelt = new EnchantmentConfig(5, "Fast Smelt", 1, true);
			fuelEfficient = new EnchantmentConfig(5, "Fuel Efficient", 1, true);
			extraExperience = new EnchantmentConfig(5, "Extra Experience", 1, true);
			heavyBlade = new EnchantmentConfig(5, "Heavy Blade", 5, true);
			lightBlade = new EnchantmentConfig(5, "Light Blade", 3, true);

			blindness = new EnchantmentConfig(1, "Blindness", 1, true);
			fireResistance = new EnchantmentConfig(1, "Fire Resistance", 1, true);
			haste = new EnchantmentConfig(5, "Haste", 3, true);
			healthBoost = new EnchantmentConfig(5, "Health Boost", 3, true);
			hunger = new EnchantmentConfig(5, "Hunger", 3, true);
			invisibility = new EnchantmentConfig(1, "Invisibility", 1, true);
			jumpBoost = new EnchantmentConfig(5, "Jump Boost", 3, true);
			miningFatigue = new EnchantmentConfig(5, "Mining Fatigue", 3, true);
			nausea = new EnchantmentConfig(1, "Nausea", 1, true);
			poison = new EnchantmentConfig(5, "Poison", 3, true);
			regeneration = new EnchantmentConfig(5, "Regeneration", 3, true);
			resistance = new EnchantmentConfig(4, "Resistance", 3, true);
			saturation = new EnchantmentConfig(5, "Saturation", 3, true);
			slowFalling = new EnchantmentConfig(1, "Slow Falling", 1, true);
			slowness = new EnchantmentConfig(3, "Slowness", 3, true);
			speed = new EnchantmentConfig(5, "Speed", 3, true);
			strength = new EnchantmentConfig(5, "Strength", 3, true);
			waterBreathing = new EnchantmentConfig(1, "Water Breathing", 1, true);
			weakness = new EnchantmentConfig(3, "Weakness", 3, true);

			Config.hasInit = true;
		}

		config = builder.build();

		SyncedServerConfig = builder.build();

	}


}
