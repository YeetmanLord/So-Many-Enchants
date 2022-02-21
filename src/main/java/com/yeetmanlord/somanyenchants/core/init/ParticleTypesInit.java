package com.yeetmanlord.somanyenchants.core.init;

import com.yeetmanlord.somanyenchants.Main;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleTypesInit 
{
	public static final DeferredRegister<ParticleType<?>> 
	PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Main.MOD_ID);
	
	public static final RegistryObject<SimpleParticleType> FREEZE_PARTICLE = PARTICLES.register("freeze", () -> new SimpleParticleType(false));

}
