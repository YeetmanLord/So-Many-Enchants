package com.github.yeetmanlord.somanyenchants.mixins.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.Registry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

@Mixin(Attributes.class)
public class MixinAttributes {

	@Inject(method = "register(Ljava/lang/String;Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/world/entity/ai/attributes/Attribute;", at = @At("HEAD"), cancellable = true)
	private static void register(String id, Attribute attribute, CallbackInfoReturnable<Attribute> callback) {

		if (id == "generic.armor") {
			Attribute attr = (new RangedAttribute("attribute.name.generic.armor", 0.0D, 0.0D, 1024.0D)).setSyncable(true);
			callback.setReturnValue(Registry.register(Registry.ATTRIBUTE, id, attr));
		}
		else if (id == "generic.armor_toughness") {
			Attribute attr = (new RangedAttribute("attribute.name.generic.armor_toughness", 0.0D, 0.0D, 1024.0D)).setSyncable(true);
			callback.setReturnValue(Registry.register(Registry.ATTRIBUTE, id, attr));
		}

	}

}
