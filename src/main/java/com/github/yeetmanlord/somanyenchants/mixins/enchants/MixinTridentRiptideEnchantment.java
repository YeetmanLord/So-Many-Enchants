package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.item.enchantment.TridentRiptideEnchantment;

@Mixin(TridentRiptideEnchantment.class)
public class MixinTridentRiptideEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.riptide.isEnabled.get() == false) {
			return 3;
		}
		else return Config.riptide.maxLevel.get();

	}

}
