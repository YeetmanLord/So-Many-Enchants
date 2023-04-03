package com.github.yeetmanlord.somanyenchants.core.init;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleTypesInit 
{
	public static final DeferredRegister<ParticleType<?>> 
	PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SoManyEnchants.MOD_ID);
	
	public static final RegistryObject<BasicParticleType> FREEZE_PARTICLE = PARTICLES.register("freeze", () -> new BasicParticleType(false));

}
