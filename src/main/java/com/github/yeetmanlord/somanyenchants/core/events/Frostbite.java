package com.github.yeetmanlord.somanyenchants.core.events;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.ParticleTypesInit;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class Frostbite {

	@SubscribeEvent
	public static void frostBite(final LivingAttackEvent event) {
		DamageSource source = event.getSource();
		Entity e = event.getEntity();
		if (e instanceof LivingEntity && Config.freezing.isEnabled.get() == true) {
			LivingEntity attacked = (LivingEntity) e;
			if (source instanceof EntityDamageSource) {
				Entity attackerEntity = source.getEntity();
				if (attackerEntity instanceof LivingEntity) {
					LivingEntity livingAttacker = (LivingEntity) attackerEntity;
					int frostBite = ModEnchantmentHelper.getFreezingEnchant(livingAttacker);
					if (frostBite > 1) {
						if (attacked.level instanceof ServerLevel) {
							((ServerLevel) attacked.level).sendParticles(ParticleTypesInit.FREEZE_PARTICLE.get(),
									attacked.getX(), attacked.getY(0.5D), attacked.getZ(),
									50 * frostBite, 0.1D, 0.0D, 0.1D, 0.2D);
						}
						if (attacked instanceof Player) {
							attacked.addEffect(
									new MobEffectInstance(MobEffects.JUMP, 15 * frostBite, 250, false, false, false));
						} else {
							attacked.addEffect(
									new MobEffectInstance(MobEffects.WEAKNESS, 15 * frostBite, 255, false, false, false));
						}
						attacked.addEffect(
								new MobEffectInstance(MobEffects.UNLUCK, 15 * frostBite, 17, false, false, false));
						attacked.addEffect(
								new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 15 * frostBite, 1, false, false, false));
						attacked.addEffect(
								new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 15 * frostBite, 250, false, false, false));
					}
				}
			}
		} else {
			return;
		}

	}
}
