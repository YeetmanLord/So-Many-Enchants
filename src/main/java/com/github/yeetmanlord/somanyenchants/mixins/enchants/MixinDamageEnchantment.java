package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;

@Mixin(DamageEnchantment.class)
public abstract class MixinDamageEnchantment {

	@Inject(at = @At("HEAD"), method = "getMaxLevel()I", cancellable = true)
	private void getMaxLevel(CallbackInfoReturnable<Integer> callback) {

		if (Config.damageEnchantments.isEnabled.get()) {
			callback.setReturnValue(Config.damageEnchantments.maxLevel.get());
		}

	}

	@Inject(at = @At("HEAD"), method = "checkCompatibility(Lnet/minecraft/enchantment/Enchantment;)Z", cancellable = true)
	private void checkCompatibility(Enchantment ench, CallbackInfoReturnable<Boolean> callback) {

		if (Config.damageEnchantments.isEnabled.get()) {
			callback.setReturnValue(!(ench instanceof DamageEnchantment) && ench != EnchantmentInit.HEAVY_BLADE.get() && ench != EnchantmentInit.LIGHT_BLADE.get());
		}

	}

	@Inject(at = @At("HEAD"), method = "canEnchant(Lnet/minecraft/item/ItemStack;)Z", cancellable = true)
	private void canEnchant(ItemStack stack, CallbackInfoReturnable<Boolean> callback) {

		if (Config.damageEnchantments.isEnabled.get()) {
			callback.setReturnValue(stack.getItem() instanceof TridentItem ? true : stack.getItem() instanceof AxeItem ? true : ((DamageEnchantment) (Object) this).canApplyAtEnchantingTable(stack));
		}

	}

}
