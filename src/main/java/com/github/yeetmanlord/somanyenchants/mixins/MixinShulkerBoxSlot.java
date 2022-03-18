package com.github.yeetmanlord.somanyenchants.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;

import net.minecraft.world.inventory.ShulkerBoxSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;

@Mixin(ShulkerBoxSlot.class)
public class MixinShulkerBoxSlot {

	@Overwrite
	public boolean mayPlace(ItemStack stack) {
		return !(Block.byItem(stack.getItem()) instanceof ShulkerBoxBlock)
				&& !(Block.byItem(stack.getItem()) instanceof EnchantedShulkerBoxBlock);
	}

}
