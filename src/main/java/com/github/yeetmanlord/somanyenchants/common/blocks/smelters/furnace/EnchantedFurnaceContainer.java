package com.github.yeetmanlord.somanyenchants.common.blocks.smelters.furnace;

import net.minecraft.entity.player.PlayerInventory;

import com.github.yeetmanlord.somanyenchants.common.container.AbstractEnchantedSmelterContainer;
import com.github.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.util.IIntArray;

public class EnchantedFurnaceContainer extends AbstractEnchantedSmelterContainer {

	public EnchantedFurnaceContainer(int id, PlayerInventory playerInventoryIn) {
		super(ContainerTypeInit.ENCHANTED_FURNACE.get(), IRecipeType.SMELTING, RecipeBookCategory.FURNACE, id, playerInventoryIn);
	}

	public EnchantedFurnaceContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn,
			IIntArray furnaceData) {
		super(ContainerTypeInit.ENCHANTED_FURNACE.get(), IRecipeType.SMELTING, RecipeBookCategory.FURNACE, id, playerInventoryIn,
				furnaceInventoryIn, furnaceData);
	}

}
