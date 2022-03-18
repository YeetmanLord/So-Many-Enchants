package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.SweepingEdgeEnchantment;

@Mixin(Enchantment.class)
public class MixinEnchantment {

	@Overwrite
	public boolean checkCompatibility(Enchantment ench) {

		if (((Enchantment) (Object) this) instanceof SweepingEdgeEnchantment) {
			return ench != EnchantmentInit.CRITICAL.get();
		}
		else {
			return ((Enchantment) (Object) this) != ench;
		}

	}

}
