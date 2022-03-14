package com.github.yeetmanlord.somanyenchants.core.init;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AttributeInit 
{
	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, SoManyEnchants.MOD_ID);
	
	public static final RegistryObject<Attribute> ATTACK_DISTANCE = ATTRIBUTES.register("player.attack_distance", () -> new RangedAttribute("attribute.name.player.attack_distance", 3.0D, 3.0D, 1024.0D).setSyncable(true));
	
}
