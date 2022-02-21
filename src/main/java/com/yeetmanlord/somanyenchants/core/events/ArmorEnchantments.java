package com.yeetmanlord.somanyenchants.core.events;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.yeetmanlord.somanyenchants.core.network.message.FlyingPacket;
import com.yeetmanlord.somanyenchants.core.util.MathUtils;
import com.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;
import com.yeetmanlord.somanyenchants.core.util.PlayerAttributeHandler;
import com.yeetmanlord.somanyenchants.core.util.PlayerUtilities;
import com.yeetmanlord.somanyenchants.core.util.Scheduler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangeGameModeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.network.PacketDistributor;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class ArmorEnchantments {
	@SubscribeEvent
	public static void armorEnchantments(final LivingEquipmentChangeEvent event) {
		LivingEntity living = event.getEntityLiving();
		if (living instanceof Player) {
			Player player = (Player) living;
			boolean flag = event.getSlot() != EquipmentSlot.MAINHAND && event.getSlot() != EquipmentSlot.OFFHAND;
			ItemStack to = event.getTo();
			ItemStack from = event.getFrom();
			if (flag) {
				boolean hEnabled = Config.healthBoost.isEnabled.get();
				if (hEnabled) {
					boolean flag1 = ModEnchantmentHelper.hasEnchant(EnchantmentInit.HEALTH_BOOST.get(), to);
					boolean flag2 = ModEnchantmentHelper.hasEnchant(EnchantmentInit.HEALTH_BOOST.get(), from);
					if (flag1) {
						int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HEALTH_BOOST.get(), to);
						PlayerAttributeHandler.addToAttributeBase(player, Attributes.MAX_HEALTH, level * 2d, to);
					} else if (flag2) {
						PlayerAttributeHandler.removeAttribute(player, Attributes.MAX_HEALTH, from);
					}
				}
				
				boolean tEnabled = Config.temper.isEnabled.get();
				if (tEnabled) {
					boolean flag1 = ModEnchantmentHelper.hasEnchant(EnchantmentInit.TEMPER.get(), to);
					boolean flag2 = ModEnchantmentHelper.hasEnchant(EnchantmentInit.TEMPER.get(), from);
					if (flag1) {
						int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.TEMPER.get(), to);
						PlayerAttributeHandler.addToAttributeBase(player, Attributes.ARMOR_TOUGHNESS, level, to);
					} else if (flag2) {
						PlayerAttributeHandler.removeAttribute(player, Attributes.ARMOR_TOUGHNESS, from);
					}
				}
				
				boolean rEnabled = Config.reinforcement.isEnabled.get();
				if (rEnabled) {
					boolean flag1 = ModEnchantmentHelper.hasEnchant(EnchantmentInit.REINFORCEMENT.get(), to);
					boolean flag2 = ModEnchantmentHelper.hasEnchant(EnchantmentInit.REINFORCEMENT.get(), from);
					if (flag1) {
						int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REINFORCEMENT.get(), to);
						PlayerAttributeHandler.addToAttributeBase(player, Attributes.ARMOR, level * 2, to);
					} else if (flag2) {
						PlayerAttributeHandler.removeAttribute(player, Attributes.ARMOR, from);
					}
				}
				
				boolean heEnabled = Config.heavyArmor.isEnabled.get();
				if (heEnabled) {
					boolean flag1 = ModEnchantmentHelper.hasEnchant(EnchantmentInit.HEAVY.get(), to);
					boolean flag2 = ModEnchantmentHelper.hasEnchant(EnchantmentInit.HEAVY.get(), from);
					if (flag1) {
						int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HEAVY.get(), to);
						PlayerAttributeHandler.addToAttributeBase(player, Attributes.KNOCKBACK_RESISTANCE, level * 0.1, to);
					} else if (flag2) {
						PlayerAttributeHandler.removeAttribute(player, Attributes.KNOCKBACK_RESISTANCE, from);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void applyFlight(final LivingEquipmentChangeEvent event) {
		LivingEntity living = event.getEntityLiving();
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
						NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {return (ServerPlayer) player;}), new FlyingPacket(true));
						
					}
				} else if (oldSlot != ItemStack.EMPTY) {
					int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FLIGHT.get(), oldSlot); 
					if (level > 0 && !player.isDeadOrDying()) {
						player.abilities.mayfly = false;
						player.abilities.flying = false;
						player.onUpdateAbilities();
						NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {return (ServerPlayer) player;}), new FlyingPacket(false));
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
		LivingEntity e = event.getEntityLiving();
		if (e instanceof Player && Config.stepAssist.isEnabled.get() == true) {
			Player player = (Player) e;
			ItemStack a = event.getFrom();
			ItemStack b = event.getTo();
			PlayerUtilities util = Main.getPlayerUtil(player);
			if (event.getSlot() == EquipmentSlot.FEET) {
				if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) > 0 && ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) <= 3) {
					player.maxUpStep = player.maxUpStep
							- ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) * 0.5f;
					util.setLastModifiedStepHeight(0.6f
							+ ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) * 0.5f);
					util.setStepHeight(player.maxUpStep);
				}
				else if(ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) > 3)
				{
					player.maxUpStep = player.maxUpStep
							- 3 * 0.5f;
					util.setLastModifiedStepHeight(0.6f
							+ 3 * 0.5f);
					util.setStepHeight(player.maxUpStep);
				}

				if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), b) > 0 && ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), b) <= 3) {
					util.setLastModifiedStepHeight(player.maxUpStep
							- ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) * 0.5f);
					player.maxUpStep = 0.6f
							+ ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), b) * 0.5f;
					util.setStepHeight(player.maxUpStep);
				}
				else if(ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) > 3)
				{
					util.setLastModifiedStepHeight(player.maxUpStep
							- 3 * 0.5f);
					player.maxUpStep = 0.6f
							+ 3 * 0.5f;
					util.setStepHeight(player.maxUpStep);
				}
			}
		}
	}

	@SubscribeEvent
	public static void updateStepAssist(final PlayerTickEvent event) {

		Player player = event.player;
		PlayerUtilities util = Main.getPlayerUtil(player);
		if (ModEnchantmentHelper.getStepAssistLevel(player) > 0 && Config.stepAssist.isEnabled.get() == true) {
			player.maxUpStep = util.getStepHeight();
		} else if (MathUtils.roundNearestPlace(util.getStepHeight(), -1) == 0.6f
				&& MathUtils.roundNearestPlace(util.getLastModifiedStepHeight(), -1) != MathUtils
						.roundNearestPlace(util.getStepHeight(), -1)
				&& ModEnchantmentHelper.getStepAssistLevel(player) == 0
				&& MathUtils.roundNearestPlace(player.maxUpStep, -1) == MathUtils.roundNearestPlace(util.getLastModifiedStepHeight(), -1) && Config.stepAssist.isEnabled.get() == true) {
			player.maxUpStep = 0.6f;
		}
	}
	
	public static class ClientAccess
	{
		public static void updatePlayerFlying(boolean flying) 
		{
			@SuppressWarnings("resource")
			LocalPlayer player = Minecraft.getInstance().player;
			player.abilities.mayfly = flying;
			player.onUpdateAbilities();
		}
	}

	@SubscribeEvent
	public static void switchGM(final PlayerChangeGameModeEvent event)
	{
		Player player = event.getPlayer();
		Scheduler sch = Main.getScheduler(player);
		sch.schedule(() -> new Runnable() {
			@Override
			public void run() 
			{
				ItemStack stack = player.inventory.armor.get(0);
				int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FLIGHT.get(), stack);
				if(level > 0)
				{
					player.abilities.mayfly = true;
					player.onUpdateAbilities();
					NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {return (ServerPlayer) player;}), new FlyingPacket(true));
				}
			}
		}, 0);
	}
}
