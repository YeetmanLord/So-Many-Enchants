package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.TridentImpalerEnchantment;

@Mixin(TridentImpalerEnchantment.class)
public class MixinTridentImpalerEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.impaling.isEnabled.get() == false) {
			return 5;
		}
		else return Config.impaling.maxLevel.get();

	}

}
