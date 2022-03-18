package com.github.yeetmanlord.somanyenchants.mixins.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.core.Registry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

@Mixin(Attributes.class)
public class MixinAttributes {

	@Overwrite
	private static Attribute register(String id, Attribute attribute) {

		if (id == "generic.armor") {
			Attribute attr = (new RangedAttribute("attribute.name.generic.armor", 0.0D, 0.0D, 1024.0D)).setSyncable(true);
			return Registry.register(Registry.ATTRIBUTE, id, attr);
		}
		else if (id == "generic.armor_toughness") {
			Attribute attr = (new RangedAttribute("attribute.name.generic.armor_toughness", 0.0D, 0.0D, 1024.0D)).setSyncable(true);
			return Registry.register(Registry.ATTRIBUTE, id, attr);
		}

		return Registry.register(Registry.ATTRIBUTE, id, attribute);

	}

}
