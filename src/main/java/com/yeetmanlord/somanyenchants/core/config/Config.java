package com.yeetmanlord.somanyenchants.core.config;

import java.util.HashMap;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config
{
	
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
	
	static
	{
		
		if(!hasInit)
		{
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
	
	public static void load(CommentedFileConfig file)
	{
		file.load();
    	Config.damageEnchantments.maxLevel.set(file.get("Damage Enchantments" + ".maxLevel"));
    	Config.damageEnchantments.isEnabled.set(file.get("Damage Enchantments" + ".isEnabled"));
    	
    	Config.efficiency.maxLevel.set(file.get("Efficiency" + ".maxLevel"));
    	Config.efficiency.isEnabled.set(file.get("Efficiency" + ".isEnabled"));
    	
    	Config.fireAspect.maxLevel.set(file.get("Fire Aspect" + ".maxLevel"));
    	Config.fireAspect.isEnabled.set(file.get("Fire Aspect" + ".isEnabled"));
    	
    	Config.impaling.maxLevel.set(file.get("Impaling" + ".maxLevel"));
    	Config.impaling.isEnabled.set(file.get("Impaling" + ".isEnabled"));
    	
    	Config.knockback.maxLevel.set(file.get("Knockback" + ".maxLevel"));
    	Config.knockback.isEnabled.set(file.get("Knockback" + ".isEnabled"));
    	
    	Config.lootBonusEnchantments.maxLevel.set(file.get("Loot Bonus Enchantments" + ".maxLevel"));
    	Config.lootBonusEnchantments.isEnabled.set(file.get("Loot Bonus Enchantments" + ".isEnabled"));
    	
    	Config.loyalty.maxLevel.set(file.get("Loyalty" + ".maxLevel"));
    	Config.loyalty.isEnabled.set(file.get("Loyalty" + ".isEnabled"));
    	
    	Config.lure.maxLevel.set(file.get("Lure" + ".maxLevel"));
    	Config.lure.isEnabled.set(file.get("Lure" + ".isEnabled"));
    	
    	Config.piercing.maxLevel.set(file.get("Piercing" + ".maxLevel"));
    	Config.piercing.isEnabled.set(file.get("Piercing" + ".isEnabled"));
    	
    	Config.power.maxLevel.set(file.get("Power" + ".maxLevel"));
    	Config.power.isEnabled.set(file.get("Power" + ".isEnabled"));
    	
    	Config.protectionEnchantments.maxLevel.set(file.get("Protection Enchantments" + ".maxLevel"));
    	Config.protectionEnchantments.isEnabled.set(file.get("Protection Enchantments" + ".isEnabled"));
    	
    	Config.punch.maxLevel.set(file.get("Punch" + ".maxLevel"));
    	Config.punch.isEnabled.set(file.get("Punch" + ".isEnabled"));
    	
    	Config.quickCharge.maxLevel.set(file.get("Quick Charge" + ".maxLevel"));
    	Config.quickCharge.isEnabled.set(file.get("Quick Charge" + ".isEnabled"));
    	
    	Config.respiration.maxLevel.set(file.get("Respiration" + ".maxLevel"));
    	Config.respiration.isEnabled.set(file.get("Respiration" + ".isEnabled"));
    	
    	Config.riptide.maxLevel.set(file.get("Riptide" + ".maxLevel"));
    	Config.riptide.isEnabled.set(file.get("Riptide" + ".isEnabled"));
    	
    	Config.soulSpeed.maxLevel.set(file.get("Soul Speed" + ".maxLevel"));
    	Config.soulSpeed.isEnabled.set(file.get("Soul Speed" + ".isEnabled"));
    	
    	Config.sweeping.maxLevel.set(file.get("Sweeping Edge" + ".maxLevel"));
    	Config.sweeping.isEnabled.set(file.get("Sweeping Edge" + ".isEnabled"));
    	
    	Config.thorns.maxLevel.set(file.get("Thorns" + ".maxLevel"));
    	Config.thorns.isEnabled.set(file.get("Thorns" + ".isEnabled"));
    	
    	Config.unbreaking.maxLevel.set(file.get("Unbreaking" + ".maxLevel"));
    	Config.unbreaking.isEnabled.set(file.get("Unbreaking" + ".isEnabled"));
    	
    	Config.heavyArmor.maxLevel.set(file.get("Heavy" + ".maxLevel"));
    	Config.heavyArmor.isEnabled.set(file.get("Heavy" + ".isEnabled"));
    	
    	Config.reinforcement.maxLevel.set(file.get("Reinforcement" + ".maxLevel"));
    	Config.reinforcement.isEnabled.set(file.get("Reinforcement" + ".isEnabled"));
    	
    	Config.temper.maxLevel.set(file.get("Temper" + ".maxLevel"));
    	Config.temper.isEnabled.set(file.get("Temper" + ".isEnabled"));

    	Config.catVision.isEnabled.set(file.get("Cat Vision" + ".isEnabled"));
    	
    	Config.flight.maxLevel.set(file.get("Flight" + ".maxLevel"));
    	Config.flight.isEnabled.set(file.get("Flight" + ".isEnabled"));
    	
    	Config.healthBoost.maxLevel.set(file.get("Health Boost" + ".maxLevel"));
    	Config.healthBoost.isEnabled.set(file.get("Health Boost" + ".isEnabled"));
    	
    	Config.stepAssist.maxLevel.set(file.get("Step Assist" + ".maxLevel"));
    	Config.stepAssist.isEnabled.set(file.get("Step Assist" + ".isEnabled"));

    	Config.fastHopper.isEnabled.set(file.get("Fast Hopper" + ".isEnabled"));

    	
    	Config.blockReach.maxLevel.set(file.get("Block Reach" + ".maxLevel"));
    	Config.blockReach.isEnabled.set(file.get("Block Reach" + ".isEnabled"));
    	
    	Config.doubleBreak.maxLevel.set(file.get("Double Break" + ".maxLevel"));
    	Config.doubleBreak.isEnabled.set(file.get("Double Break" + ".isEnabled"));

    	Config.replanting.isEnabled.set(file.get("Replanting" + ".isEnabled"));
    	
    	
    	Config.attackReach.maxLevel.set(file.get("Attack Reach" + ".maxLevel"));
    	Config.attackReach.isEnabled.set(file.get("Attack Reach" + ".isEnabled"));
    	
    	Config.critical.maxLevel.set(file.get("Critical" + ".maxLevel"));
    	Config.critical.isEnabled.set(file.get("Critical" + ".isEnabled"));
    	
    	Config.freezing.maxLevel.set(file.get("Freezing" + ".maxLevel"));
    	Config.freezing.isEnabled.set(file.get("Freezing" + ".isEnabled"));

    	Config.villager.isEnabled.set(file.get("Enchanter Villager" + ".isEnabled"));
    	
    	Config.cavernousStorage.isEnabled.set(file.get("Cavernous Storage" + ".isEnabled"));
    	
    	Config.camouflage.isEnabled.set(file.get("Camouflage" + ".isEnabled"));
    	
    	Config.fastSmelt.maxLevel.set(file.getInt(fastSmelt.name + ".maxLevel"));
    	Config.fastSmelt.isEnabled.set(file.get(fastSmelt.name + ".isEnabled"));
    	
    	Config.heavyBlade.maxLevel.set(file.get(heavyBlade.name + ".maxLevel"));
    	Config.heavyBlade.isEnabled.set(file.get(heavyBlade.name + ".isEnabled"));
    	
    	Config.fuelEfficient.maxLevel.set(file.getInt(fuelEfficient.name + ".maxLevel"));
    	Config.fuelEfficient.isEnabled.set(file.get(fuelEfficient.name + ".isEnabled"));
    	
    	Config.extraExperience.maxLevel.set(file.getInt(extraExperience.name + ".maxLevel"));
    	Config.extraExperience.isEnabled.set(file.get(extraExperience.name + ".isEnabled"));
    	
    	Config.lightBlade.maxLevel.set(file.get(lightBlade.name + ".maxLevel"));
    	Config.lightBlade.isEnabled.set(file.get(lightBlade.name + ".isEnabled"));
    	
    	Config.blindness.maxLevel.set(file.get(blindness.name + ".maxLevel"));
    	Config.blindness.isEnabled.set(file.get(blindness.name + ".isEnabled"));

    	Config.fireResistance.maxLevel.set(file.get(fireResistance.name + ".maxLevel"));
    	Config.fireResistance.isEnabled.set(file.get(fireResistance.name + ".isEnabled"));

    	Config.haste.maxLevel.set(file.get(haste.name + ".maxLevel"));
    	Config.haste.isEnabled.set(file.get(haste.name + ".isEnabled"));

    	Config.hunger.maxLevel.set(file.get(hunger.name + ".maxLevel"));
    	Config.hunger.isEnabled.set(file.get(hunger.name + ".isEnabled"));

    	Config.invisibility.maxLevel.set(file.get(invisibility.name + ".maxLevel"));
    	Config.invisibility.isEnabled.set(file.get(invisibility.name + ".isEnabled"));

    	Config.jumpBoost.maxLevel.set(file.get(jumpBoost.name + ".maxLevel"));
    	Config.jumpBoost.isEnabled.set(file.get(jumpBoost.name + ".isEnabled"));

    	Config.miningFatigue.maxLevel.set(file.get(miningFatigue.name + ".maxLevel"));
    	Config.miningFatigue.isEnabled.set(file.get(miningFatigue.name + ".isEnabled"));

    	Config.nausea.maxLevel.set(file.get(nausea.name + ".maxLevel"));
    	Config.nausea.isEnabled.set(file.get(nausea.name + ".isEnabled"));

    	Config.poison.maxLevel.set(file.get(poison.name + ".maxLevel"));
    	Config.poison.isEnabled.set(file.get(poison.name + ".isEnabled"));

    	Config.regeneration.maxLevel.set(file.get(regeneration.name + ".maxLevel"));
    	Config.regeneration.isEnabled.set(file.get(regeneration.name + ".isEnabled"));

    	Config.resistance.maxLevel.set(file.get(resistance.name + ".maxLevel"));
    	Config.resistance.isEnabled.set(file.get(resistance.name + ".isEnabled"));

    	Config.saturation.maxLevel.set(file.get(saturation.name + ".maxLevel"));
    	Config.saturation.isEnabled.set(file.get(saturation.name + ".isEnabled"));

    	Config.slowFalling.maxLevel.set(file.get(slowFalling.name + ".maxLevel"));
    	Config.slowFalling.isEnabled.set(file.get(slowFalling.name + ".isEnabled"));

    	Config.slowness.maxLevel.set(file.get(slowness.name + ".maxLevel"));
    	Config.slowness.isEnabled.set(file.get(slowness.name + ".isEnabled"));

    	Config.speed.maxLevel.set(file.get(speed.name + ".maxLevel"));
    	Config.speed.isEnabled.set(file.get(speed.name + ".isEnabled"));

    	Config.strength.maxLevel.set(file.get(strength.name + ".maxLevel"));
    	Config.strength.isEnabled.set(file.get(strength.name + ".isEnabled"));

    	Config.waterBreathing.maxLevel.set(file.get(waterBreathing.name + ".maxLevel"));
    	Config.waterBreathing.isEnabled.set(file.get(waterBreathing.name + ".isEnabled"));

    	Config.weakness.maxLevel.set(file.get(weakness.name + ".maxLevel"));
    	Config.weakness.isEnabled.set(file.get(weakness.name + ".isEnabled"));
	}
	
	public static void save(CommentedFileConfig file)
	{
		file.load();
		for(EnchantmentConfig c : configSections.values())
		{
			file.set(c.name + ".maxLevel", c.maxLevel.get());
			file.set(c.name + ".isEnabled", c.isEnabled.get());
		}
		file.set("Enchanter Villager", Config.villager.isEnabled.get());
	}
}
