package com.github.yeetmanlord.somanyenchants.core.events;

import java.util.Random;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class ApplyCriticalEnchant {

	@SubscribeEvent
	public static void applyCriticalEnchantment(final CriticalHitEvent event) {

		Random rand = new Random();
		Entity target = event.getTarget();
		Player player = event.getEntity();
		int criticalLevel = ModEnchantmentHelper.getCriticalLevel(player);

		if (criticalLevel > 0 && Config.critical.isEnabled.get() == true) {

			if (event.isVanillaCritical()) {
				int chance = (int) (rand.nextFloat() * 100);

				if (chance <= criticalLevel * 20 && chance <= 100) {
					event.setDamageModifier(2.0F);
					return;
				}
				else if ((chance >= criticalLevel * 20 && chance <= 100)) {
					return;
				}

				SoManyEnchants.LOGGER.error("{} is an invalid chance somehow", (Object) String.valueOf(chance));
				throw (new IllegalStateException(chance + " is an invalid percentage"));
			}
			else if (event.isVanillaCritical() == false) {
				int chance = (int) (rand.nextFloat() * 100);

				if ((chance <= criticalLevel * 20 && chance <= 100)) {
					event.setResult(Result.ALLOW);
					event.setDamageModifier(1.5F);
					return;
				}
				else if ((chance >= criticalLevel * 20 && chance <= 100) || player.getAttackStrengthScale(0.5F) < 1.0F) {
					return;
				}

				SoManyEnchants.LOGGER.error("{} is an invalid chance somehow", (Object) String.valueOf(chance));
				throw (new IllegalStateException(chance + " is an invalid percentage"));
			}

			SoManyEnchants.LOGGER.error("{} has invalid critical state", (Object) player.getName().getString());
			throw (new IllegalStateException(player.getName().getString() + " has invalid critical state"));
		}

	}

	@SubscribeEvent
	public static void applyEnchantmentToNonPlayer(final LivingHurtEvent event) {

		Random rand = new Random();
		LivingEntity target = event.getEntity();

		if (event.getSource() instanceof EntityDamageSource && Config.critical.isEnabled.get() == true) {
			Entity attacker = event.getSource().getEntity();

			if (attacker instanceof LivingEntity && !(attacker instanceof Player)) {
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
