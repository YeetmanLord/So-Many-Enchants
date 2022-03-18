package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;

@Mixin(DamageEnchantment.class)
public abstract class MixinDamageEnchantment {

	@Overwrite
	public int getMaxLevel() {

		if (Config.damageEnchantments.isEnabled.get() == false) {
			return 5;
		}
		else {

			return Config.damageEnchantments.maxLevel.get();
		}

	}

	@Overwrite
	public boolean checkCompatibility(Enchantment ench) {

		return !(ench instanceof DamageEnchantment) && ench != EnchantmentInit.HEAVY_BLADE.get() && ench != EnchantmentInit.LIGHT_BLADE.get();

	}

	@Overwrite
	public boolean canEnchant(ItemStack stack) {

		return stack.getItem() instanceof TridentItem ? true : stack.getItem() instanceof AxeItem ? true : ((DamageEnchantment) (Object) this).canApplyAtEnchantingTable(stack);

	}

}
