package com.github.yeetmanlord.somanyenchants.common.blocks.smelters.furnace;

import net.minecraft.world.entity.player.Inventory;

import com.github.yeetmanlord.somanyenchants.common.container.AbstractEnchantedSmelterContainer;
import com.github.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.ContainerData;

public class EnchantedFurnaceContainer extends AbstractEnchantedSmelterContainer {

	public EnchantedFurnaceContainer(int id, Inventory playerInventoryIn) {
		super(ContainerTypeInit.ENCHANTED_FURNACE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, id, playerInventoryIn);
	}

	public EnchantedFurnaceContainer(int id, Inventory playerInventoryIn, Container furnaceInventoryIn,
			ContainerData furnaceData) {
		super(ContainerTypeInit.ENCHANTED_FURNACE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, id, playerInventoryIn,
				furnaceInventoryIn, furnaceData);
	}

}
