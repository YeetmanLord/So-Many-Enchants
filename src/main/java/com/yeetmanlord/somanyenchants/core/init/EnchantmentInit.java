package com.yeetmanlord.somanyenchants.core.init;

import com.yeetmanlord.somanyenchants.SoManyEnchants;
import com.yeetmanlord.somanyenchants.common.enchantments.EffectEnchantment;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.ArmorHeavyEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.ArmorReinforcementEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.ArmorTemperEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.CatVisionEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.FlightEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.HealthBoostEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.armor.StepAssistEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.blocks.CamouflageEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.blocks.CavernousStorageEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.blocks.ExtraExperienceSmelterEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.blocks.FastHopperEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.blocks.FastSmeltEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.blocks.FuelEfficientSmelterEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.tools.BlockReachEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.tools.DoubleBreakEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.tools.ReplantingEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.weapons.AttackReachEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.weapons.CriticalEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.weapons.FreezingEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.weapons.HeavyBladeEnchant;
import com.yeetmanlord.somanyenchants.common.enchantments.weapons.LightBladeEnchant;
import com.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentInit {
	private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[] { EquipmentSlot.HEAD, EquipmentSlot.CHEST,
			EquipmentSlot.LEGS, EquipmentSlot.FEET };

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister
			.create(ForgeRegistries.ENCHANTMENTS, SoManyEnchants.MOD_ID);

	// Can enchant at table
	public static final RegistryObject<Enchantment> HEALTH_BOOST = ENCHANTMENTS.register("health_boost",
			() -> new HealthBoostEnchant(Rarity.UNCOMMON, ARMOR_SLOTS));

	public static final RegistryObject<Enchantment> REINFORCED_ARMOR = ENCHANTMENTS.register("reinforcement",
			() -> new ArmorReinforcementEnchant(Rarity.RARE, ARMOR_SLOTS));

	public static final RegistryObject<Enchantment> TEMPERED_ARMOR = ENCHANTMENTS.register("temper",
			() -> new ArmorTemperEnchant(Rarity.RARE, ARMOR_SLOTS));

	public static final RegistryObject<Enchantment> HEAVY_ARMOR = ENCHANTMENTS.register("heavy",
			() -> new ArmorHeavyEnchant(Rarity.VERY_RARE, ARMOR_SLOTS));

	public static final RegistryObject<Enchantment> CAT_VISION = ENCHANTMENTS.register("cat_vision",
			() -> new CatVisionEnchant(Rarity.UNCOMMON, ARMOR_SLOTS));

	public static final RegistryObject<Enchantment> REPLANTING = ENCHANTMENTS.register("replanting",
			() -> new ReplantingEnchant(Rarity.UNCOMMON, EquipmentSlot.MAINHAND));

	public static final RegistryObject<Enchantment> FREEZING = ENCHANTMENTS.register("freezing_attack",
			() -> new FreezingEnchant(Rarity.UNCOMMON, EquipmentSlot.MAINHAND));

	public static final RegistryObject<Enchantment> FAST_HOPPER = ENCHANTMENTS.register("fast_hopper",
			() -> new FastHopperEnchant(Rarity.UNCOMMON, EquipmentSlot.MAINHAND));

	public static final RegistryObject<Enchantment> CAMOUFLAGE = ENCHANTMENTS.register("camouflage",
			() -> new CamouflageEnchant(Rarity.RARE, EquipmentSlot.MAINHAND));

	public static final RegistryObject<Enchantment> FAST_SMELT = ENCHANTMENTS.register("fast_smelt",
			() -> new FastSmeltEnchant(Rarity.RARE, EquipmentSlot.MAINHAND));

	public static final RegistryObject<Enchantment> FUEL_EFFICIENT = ENCHANTMENTS.register("fuel_efficient",
			() -> new FuelEfficientSmelterEnchant(Rarity.UNCOMMON, EquipmentSlot.MAINHAND));

	public static final RegistryObject<Enchantment> EXTRA_EXPERIENCE = ENCHANTMENTS.register("extra_experience",
			() -> new ExtraExperienceSmelterEnchant(Rarity.UNCOMMON, EquipmentSlot.MAINHAND));

	public static final RegistryObject<Enchantment> HEAVY_BLADE = ENCHANTMENTS.register("heavy_blade",
			() -> new HeavyBladeEnchant(Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

	public static final RegistryObject<Enchantment> LIGHT_BLADE = ENCHANTMENTS.register("light_blade",
			() -> new LightBladeEnchant(Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

	public static final RegistryObject<EffectEnchantment> BLINDNESS_ENCHANTMENT = ENCHANTMENTS.register(
			"blindness_enchantment",
			() -> new EffectEnchantment(MobEffects.BLINDNESS, Config.blindness, Rarity.RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> FIRE_RESISTANCE_ENCHANTMENT = ENCHANTMENTS
			.register("fire_resistance_enchantment", () -> new EffectEnchantment(MobEffects.FIRE_RESISTANCE,
					Config.fireResistance, Rarity.UNCOMMON, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> HASTE_ENCHANTMENT = ENCHANTMENTS.register("haste_enchantment",
			() -> new EffectEnchantment(MobEffects.DIG_SPEED, Config.haste, Rarity.VERY_RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> HUNGER_ENCHANTMENT = ENCHANTMENTS.register(
			"hunger_enchantment",
			() -> new EffectEnchantment(MobEffects.HUNGER, Config.hunger, Rarity.RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> INVISIBILITY_ENCHANTMENT = ENCHANTMENTS.register(
			"invisibility_enchantment",
			() -> new EffectEnchantment(MobEffects.INVISIBILITY, Config.invisibility, Rarity.VERY_RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> JUMP_BOOST_ENCHANTMENT = ENCHANTMENTS.register(
			"jump_boost_enchantment",
			() -> new EffectEnchantment(MobEffects.JUMP, Config.jumpBoost, Rarity.UNCOMMON, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> MINING_FATIGUE_ENCHANTMENT = ENCHANTMENTS.register(
			"mining_fatigue_enchantment",
			() -> new EffectEnchantment(MobEffects.DIG_SLOWDOWN, Config.miningFatigue, Rarity.RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> NAUSEA_ENCHANTMENT = ENCHANTMENTS.register(
			"nausea_enchantment",
			() -> new EffectEnchantment(MobEffects.CONFUSION, Config.nausea, Rarity.VERY_RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> POISON_ENCHANTMENT = ENCHANTMENTS.register(
			"poison_enchantment",
			() -> new EffectEnchantment(MobEffects.POISON, Config.poison, Rarity.RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> REGENERATION_ENCHANTMENT = ENCHANTMENTS.register(
			"regeneration_enchantment",
			() -> new EffectEnchantment(MobEffects.REGENERATION, Config.regeneration, Rarity.VERY_RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> RESISTANCE_ENCHANTMENT = ENCHANTMENTS
			.register("resistance_enchantment", () -> new EffectEnchantment(MobEffects.DAMAGE_RESISTANCE,
					Config.resistance, false, Rarity.VERY_RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> SATURATION_ENCHANTMENT = ENCHANTMENTS
			.register("saturation_enchantment", () -> new EffectEnchantment(MobEffects.SATURATION, Config.saturation,
					false, Rarity.VERY_RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> SLOW_FALLING_ENCHANTMENT = ENCHANTMENTS.register(
			"slow_falling_enchantment",
			() -> new EffectEnchantment(MobEffects.SLOW_FALLING, Config.slowFalling, Rarity.VERY_RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> SLOWNESS_ENCHANTMENT = ENCHANTMENTS.register(
			"slowness_enchantment",
			() -> new EffectEnchantment(MobEffects.MOVEMENT_SLOWDOWN, Config.slowness, Rarity.RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> SPEED_ENCHANTMENT = ENCHANTMENTS.register("speed_enchantment",
			() -> new EffectEnchantment(MobEffects.MOVEMENT_SPEED, Config.speed, Rarity.UNCOMMON, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> STRENGTH_ENCHANTMENT = ENCHANTMENTS
			.register("strength_enchantment", () -> new EffectEnchantment(MobEffects.DAMAGE_BOOST, Config.strength,
					false, Rarity.VERY_RARE, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> WATER_BREATHING_ENCHANTMENT = ENCHANTMENTS.register(
			"water_breathing_enchantment",
			() -> new EffectEnchantment(MobEffects.WATER_BREATHING, Config.waterBreathing, Rarity.COMMON, ARMOR_SLOTS));
	public static final RegistryObject<EffectEnchantment> WEAKNESS_ENCHANTMENT = ENCHANTMENTS.register(
			"weakness_enchantment",
			() -> new EffectEnchantment(MobEffects.WEAKNESS, Config.weakness, Rarity.UNCOMMON, ARMOR_SLOTS));

	// Cannot enchant at table
	public static final RegistryObject<Enchantment> FLIGHT = ENCHANTMENTS.register("flight",
			() -> new FlightEnchant(Rarity.VERY_RARE, EquipmentSlot.FEET));

	public static final RegistryObject<Enchantment> ATTACK_REACH = ENCHANTMENTS.register("attack_reach",
			() -> new AttackReachEnchant(Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

	public static final RegistryObject<Enchantment> CRITICAL = ENCHANTMENTS.register("critical",
			() -> new CriticalEnchant(Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

	public static final RegistryObject<Enchantment> CAVERNOUS_STORAGE = ENCHANTMENTS.register("cavernous_storage",
			() -> new CavernousStorageEnchant(Rarity.RARE, EquipmentSlot.MAINHAND));

	public static final RegistryObject<Enchantment> STEP_ASSIST = ENCHANTMENTS.register("step_assist",
			() -> new StepAssistEnchant(Rarity.RARE, ARMOR_SLOTS));

	public static final RegistryObject<Enchantment> BLOCK_REACH = ENCHANTMENTS.register("block_reach",
			() -> new BlockReachEnchant(Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

	public static final RegistryObject<Enchantment> DOUBLE_BREAK = ENCHANTMENTS.register("double_break",
			() -> new DoubleBreakEnchant(Rarity.VERY_RARE, EquipmentSlot.MAINHAND));
}
