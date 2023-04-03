package com.github.yeetmanlord.somanyenchants.core.events;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.ParticleTypesInit;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
						if (attacked.level instanceof ServerWorld) {
							((ServerWorld) attacked.level).sendParticles(ParticleTypesInit.FREEZE_PARTICLE.get(),
									attacked.getX(), attacked.getY(0.5D), attacked.getZ(),
									50 * frostBite, 0.1D, 0.0D, 0.1D, 0.2D);
						}
						if (attacked instanceof PlayerEntity) {
							attacked.addEffect(
									new EffectInstance(Effects.JUMP, 15 * frostBite, 250, false, false, false));
						} else {
							attacked.addEffect(
									new EffectInstance(Effects.WEAKNESS, 15 * frostBite, 255, false, false, false));
						}
						attacked.addEffect(
								new EffectInstance(Effects.UNLUCK, 15 * frostBite, 17, false, false, false));
						attacked.addEffect(
								new EffectInstance(Effects.DIG_SLOWDOWN, 15 * frostBite, 1, false, false, false));
						attacked.addEffect(
								new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 15 * frostBite, 250, false, false, false));
					}
				}
			}
		} else {
			return;
		}

	}
}
