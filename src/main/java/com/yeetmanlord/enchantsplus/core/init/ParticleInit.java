package com.yeetmanlord.enchantsplus.core.init;

import java.util.Random;

import com.yeetmanlord.enchantsplus.Main;
import com.yeetmanlord.enchantsplus.common.particles.FreezeParticle;
import com.yeetmanlord.enchantsplus.common.particles.FreezeParticle.Factory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class ParticleInit 
{
	@SubscribeEvent
	public static void initializeParticles(final ParticleFactoryRegisterEvent event)
	{
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.FREEZE_PARTICLE.get(), FreezeParticle.Factory::new);
	}
}
