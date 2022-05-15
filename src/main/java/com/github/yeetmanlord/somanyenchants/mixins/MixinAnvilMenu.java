package com.github.yeetmanlord.somanyenchants.mixins;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;

import net.minecraft.world.inventory.AnvilMenu;

@Mixin(AnvilMenu.class)
public class MixinAnvilMenu {

	@Inject(at = @At("HEAD"), cancellable = true, method = "calculateIncreasedRepairCost(I)I")
	private static void calculateIncreasedRepairCost(int p_39026_, CallbackInfoReturnable<Integer> callback) {

		if (SoManyEnchants.instance != null) {
			callback.setReturnValue((int) Math.floor(p_39026_ * 1.5 + 1));
		}

	}

	@Inject(
			at = @At(value = "JUMP", opcode = Opcodes.IFGT, shift = Shift.BEFORE, ordinal = 1),
			cancellable = true,
			method = "createResult()V")
	private void createResult(CallbackInfo callback) {

		if (SoManyEnchants.instance != null) {
			(((AnvilMenu) (Object) this)).setMaximumCost((int) Math.floor((((((AnvilMenu) (Object) this)).getCost() / 2) - 1) * 0.75));
		}

	}

}
