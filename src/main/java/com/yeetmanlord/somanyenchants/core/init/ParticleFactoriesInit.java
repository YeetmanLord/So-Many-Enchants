package com.yeetmanlord.somanyenchants.core.init;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.common.particles.FreezeParticle;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD)
public class ParticleFactoriesInit
{
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void registerParticleFactories(final ParticleFactoryRegisterEvent event)
	{
		Minecraft.getInstance().particleEngine.register(ParticleTypesInit.FREEZE_PARTICLE.get(), FreezeParticle.Factory::new);
	}
}
