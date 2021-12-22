package com.yeetmanlord.somanyenchants.core.events;

import java.util.Random;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class ApplyCriticalEnchant {
	@SubscribeEvent
	public static void applyCriticalEnchantment(final CriticalHitEvent event) {
		Random rand = new Random();
		Entity target = event.getTarget();
		PlayerEntity player = event.getPlayer();
		int criticalLevel = ModEnchantmentHelper.getCriticalLevel(player);

		if (criticalLevel > 0 && Config.critical.isEnabled.get() == true) {
			if (event.isVanillaCritical()) {
				int chance = (int) (rand.nextFloat() * 100);
				if (chance <= criticalLevel * 20 && chance <= 100) {
					event.setDamageModifier(2.0F);
					return;
				} else if ((chance >= criticalLevel * 20 && chance <= 100)) {
					return;
				}
				Main.LOGGER.error("{} is an invalid chance somehow", (Object) String.valueOf(chance));
				throw (new IllegalStateException(chance + " is an invalid percentage"));
			} else if (event.isVanillaCritical() == false) {
				int chance = (int) (rand.nextFloat() * 100);
				if ((chance <= criticalLevel * 20 && chance <= 100)) {
					event.setResult(Result.ALLOW);
					event.setDamageModifier(1.5F);
					return;
				} else if ((chance >= criticalLevel * 20 && chance <= 100)
						|| player.getCooledAttackStrength(0.5F) < 1.0F) {
					return;
				}
				Main.LOGGER.error("{} is an invalid chance somehow", (Object) String.valueOf(chance));
				throw (new IllegalStateException(chance + " is an invalid percentage"));
			}
			Main.LOGGER.error("{} has invalid critical state", (Object) player.getName().getString());
			throw (new IllegalStateException(player.getName().getString() + " has invalid critical state"));
		}
	}

	@SubscribeEvent
	public static void applyEnchantmentToNonPlayer(final LivingHurtEvent event) {
		Random rand = new Random();
		LivingEntity target = event.getEntityLiving();
		if (event.getSource() instanceof EntityDamageSource && Config.critical.isEnabled.get() == true) {
			Entity attacker = event.getSource().getTrueSource();
			if (attacker instanceof LivingEntity && !(attacker instanceof PlayerEntity)) {
				int critEnchant = ModEnchantmentHelper.getCriticalLevel((LivingEntity) attacker);
				if (critEnchant > 0) {
					int chance = (int) (rand.nextFloat() * 100);
					if (chance <= critEnchant * 20) {
						event.setAmount(event.getAmount() * 1.5f);
					}
				}
			}
		}
	}

}
