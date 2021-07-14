package com.yeetmanlord.enchantsplus.core.init;

import com.yeetmanlord.enchantsplus.Main;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AttributeInit 
{
	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Main.MOD_ID);
	
	public static final RegistryObject<Attribute> ATTACK_DISTANCE = ATTRIBUTES.register("attack_distance", () -> new RangedAttribute("player.attack_distance", 4.0D, 4.0D, 1024.0D).setShouldWatch(true));
}
