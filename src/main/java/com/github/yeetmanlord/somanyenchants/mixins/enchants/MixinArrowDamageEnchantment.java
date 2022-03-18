package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.ArrowDamageEnchantment;

@Mixin(ArrowDamageEnchantment.class)
public class MixinArrowDamageEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.power.isEnabled.get() == false) {
			return 3;
		}
		else return Config.power.maxLevel.get();

	}

}
