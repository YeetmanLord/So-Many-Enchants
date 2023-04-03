package com.github.yeetmanlord.somanyenchants.core.init;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.common.particles.FreezeParticle;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ParticleFactoriesInit {

	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {

		Minecraft.getInstance().particleEngine.register(ParticleTypesInit.FREEZE_PARTICLE.get(), FreezeParticle.Factory::new);

	}

}
