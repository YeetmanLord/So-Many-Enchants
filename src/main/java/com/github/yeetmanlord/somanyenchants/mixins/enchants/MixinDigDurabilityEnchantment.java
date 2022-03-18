package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.DigDurabilityEnchantment;

@Mixin(DigDurabilityEnchantment.class)
public class MixinDigDurabilityEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.unbreaking.isEnabled.get() == false) {
			return 3;
		}
		else return Config.unbreaking.maxLevel.get();

	}

}
