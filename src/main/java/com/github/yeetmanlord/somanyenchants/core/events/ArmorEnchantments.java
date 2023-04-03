package com.github.yeetmanlord.somanyenchants.core.events;

import java.util.HashMap;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.common.enchantments.EffectEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.github.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.github.yeetmanlord.somanyenchants.core.network.message.FlyingPacket;
import com.github.yeetmanlord.somanyenchants.core.util.AttributeHelper;
import com.github.yeetmanlord.somanyenchants.core.util.MathUtils;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;
import com.github.yeetmanlord.somanyenchants.core.util.PlayerUtilities;
import com.github.yeetmanlord.somanyenchants.core.util.Scheduler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangeGameModeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.network.PacketDistributor;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class ArmorEnchantments {

	@SubscribeEvent
	public static void armorEnchantments(final LivingEquipmentChangeEvent event) {

		LivingEntity living = event.getEntity();

		if (living instanceof Player player) {
			boolean flag = event.getSlot() != EquipmentSlot.MAINHAND && event.getSlot() != EquipmentSlot.OFFHAND;
			ItemStack to = event.getTo();
			ItemStack from = event.getFrom();

			if (flag) {
				AttributeHelper.apply(EnchantmentInit.HEALTH_BOOST.get(), Attributes.MAX_HEALTH, Config.healthBoost, event, 2d);
				AttributeHelper.apply(EnchantmentInit.TEMPERED_ARMOR.get(), Attributes.ARMOR_TOUGHNESS, Config.temper, event, 1d);
				AttributeHelper.apply(EnchantmentInit.REINFORCED_ARMOR.get(), Attributes.ARMOR, Config.reinforcement, event, 2d);
				AttributeHelper.apply(EnchantmentInit.HEAVY_ARMOR.get(), Attributes.KNOCKBACK_RESISTANCE, Config.heavyArmor, event, 0.1d);

			}

		}

	}

	@SubscribeEvent
	public static void applyFlight(final LivingEquipmentChangeEvent event) {

		LivingEntity living = event.getEntity();

		if (living instanceof Player && Config.flight.isEnabled.get() == true) {
			Player player = (Player) living;

			if (event.getSlot() == EquipmentSlot.FEET && !player.isCreative() && !player.isSpectator()) {
				ItemStack newSlot = event.getTo();
				ItemStack oldSlot = event.getFrom();

				if (newSlot != ItemStack.EMPTY) {
					int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FLIGHT.get(), newSlot);

					if (level > 0 && !player.isDeadOrDying()) {
						player.abilities.mayfly = true;
						player.onUpdateAbilities();
						NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
							return (ServerPlayer) player;
						}), new FlyingPacket(true));

					}

				}
				else if (oldSlot != ItemStack.EMPTY) {
					int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FLIGHT.get(), oldSlot);

					if (level > 0 && !player.isDeadOrDying()) {
						player.abilities.mayfly = false;
						player.abilities.flying = false;
						player.onUpdateAbilities();
						NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
							return (ServerPlayer) player;
						}), new FlyingPacket(false));
					}

				}

			}

		}

	}

	@SubscribeEvent
	public static void catVision(final PlayerTickEvent event) {

		Player player = event.player;

		if (ModEnchantmentHelper.hasCatVision(player) && Config.catVision.isEnabled.get() == true) {
			player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false, false));
		}

	}

	@SubscribeEvent
	public static void stepAssist(final LivingEquipmentChangeEvent event) {

		LivingEntity e = event.getEntity();

		if (e instanceof Player && Config.stepAssist.isEnabled.get() == true) {
			Player player = (Player) e;
			ItemStack a = event.getFrom();
			ItemStack b = event.getTo();
			PlayerUtilities util = SoManyEnchants.getPlayerUtil(player);

			if (event.getSlot() == EquipmentSlot.FEET) {

				if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) > 0 && ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) <= 3) {
					player.maxUpStep = player.maxUpStep - ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) * 0.5f;
					util.setLastModifiedStepHeight(0.6f + ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) * 0.5f);
					util.setStepHeight(player.maxUpStep);
				}
				else if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) > 3) {
					player.maxUpStep = player.maxUpStep - 3 * 0.5f;
					util.setLastModifiedStepHeight(0.6f + 3 * 0.5f);
					util.setStepHeight(player.maxUpStep);
				}

				if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), b) > 0 && ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), b) <= 3) {
					util.setLastModifiedStepHeight(player.maxUpStep - ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) * 0.5f);
					player.maxUpStep = 0.6f + ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), b) * 0.5f;
					util.setStepHeight(player.maxUpStep);
				}
				else if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) > 3) {
					util.setLastModifiedStepHeight(player.maxUpStep - 3 * 0.5f);
					player.maxUpStep = 0.6f + 3 * 0.5f;
					util.setStepHeight(player.maxUpStep);
				}

			}

		}

	}

	@SubscribeEvent
	public static void updateStepAssist(final PlayerTickEvent event) {

		Player player = event.player;
		PlayerUtilities util = SoManyEnchants.getPlayerUtil(player);

		if (ModEnchantmentHelper.getStepAssistLevel(player) > 0 && Config.stepAssist.isEnabled.get() == true) {
			player.maxUpStep = util.getStepHeight();
		}
		else if (MathUtils.roundNearestPlace(util.getStepHeight(), -1) == 0.6f && MathUtils.roundNearestPlace(util.getLastModifiedStepHeight(), -1) != MathUtils.roundNearestPlace(util.getStepHeight(), -1) && ModEnchantmentHelper.getStepAssistLevel(player) == 0 && MathUtils.roundNearestPlace(player.maxUpStep, -1) == MathUtils.roundNearestPlace(util.getLastModifiedStepHeight(), -1) && Config.stepAssist.isEnabled.get() == true) {
			player.maxUpStep = 0.6f;
		}

	}

	@OnlyIn(Dist.CLIENT)
	public static class ClientAccess {

		public static void updatePlayerFlying(boolean flying) {

			@SuppressWarnings("resource")
			LocalPlayer player = Minecraft.getInstance().player;
			player.abilities.mayfly = flying;
			player.onUpdateAbilities();

		}

	}

	@SubscribeEvent
	public static void switchGM(final PlayerChangeGameModeEvent event) {

		Player player = event.getEntity();
		Scheduler sch = SoManyEnchants.getScheduler(player);
		sch.schedule(() -> new Runnable() {

			@Override
			public void run() {

				ItemStack stack = player.inventory.armor.get(0);
				int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FLIGHT.get(), stack);

				if (level > 0) {
					player.abilities.mayfly = true;
					player.onUpdateAbilities();
					NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
						return (ServerPlayer) player;
					}), new FlyingPacket(true));
				}

			}

		}, 0);

	}

	public static int wait = 0;

	@SubscribeEvent
	public static void effectEnchants(final PlayerTickEvent event) {

		if (wait < 400) {
			wait++;
			return;
		}

		wait = 0;
		Player player = event.player;
		HashMap<EffectEnchantment, Short> effectEnchs = new HashMap<>();

		for (ItemStack stack : player.inventory.armor) {
			stack.getEnchantmentTags().forEach(tag -> {

				if (tag instanceof CompoundTag nbt) {
					String name = nbt.getString("id");

					if (Registry.ENCHANTMENT.get(new ResourceLocation(name)) instanceof EffectEnchantment ench) {
						short level = nbt.getShort("lvl");

						if (effectEnchs.containsKey(ench)) {

							if (effectEnchs.get(ench) < level) {
								effectEnchs.put(ench, level);
							}

						}
						else {
							effectEnchs.put(ench, level);
						}

					}

				}

			});
		}

		for (EffectEnchantment ench : effectEnchs.keySet()) {
			ench.applyEffect(player, effectEnchs.get(ench));
		}

	}

}
