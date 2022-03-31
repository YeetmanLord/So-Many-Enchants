package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;

@Mixin(DamageEnchantment.class)
public abstract class MixinDamageEnchantment {

	@Inject(at = @At("HEAD"), method = "getMaxLevel()I", cancellable = true)
	private void getMaxLevel(CallbackInfoReturnable<Integer> callback) {

		if (Config.damageEnchantments.isEnabled.get()) {
			callback.setReturnValue(Config.damageEnchantments.maxLevel.get());
		}

	}

	@Inject(at = @At("HEAD"), method = "checkCompatibility(Lnet/minecraft/world/item/enchantment/Enchantment;)Z", cancellable = true)
	private void checkCompatibility(Enchantment ench, CallbackInfoReturnable<Boolean> callback) {

		if (Config.damageEnchantments.isEnabled.get()) {
			callback.setReturnValue(!(ench instanceof DamageEnchantment) && ench != EnchantmentInit.HEAVY_BLADE.get() && ench != EnchantmentInit.LIGHT_BLADE.get());
		}

	}

	@Inject(at = @At("HEAD"), method = "canEnchant(Lnet/minecraft/world/item/ItemStack;)Z", cancellable = true)
	private void canEnchant(ItemStack stack, CallbackInfoReturnable<Boolean> callback) {

		if (Config.damageEnchantments.isEnabled.get()) {
			callback.setReturnValue(stack.getItem() instanceof TridentItem ? true : stack.getItem() instanceof AxeItem ? true : ((DamageEnchantment) (Object) this).canApplyAtEnchantingTable(stack));
		}

	}

}
