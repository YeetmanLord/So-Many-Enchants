package com.github.yeetmanlord.somanyenchants.common.blocks.smelters.smoker;

import net.minecraft.world.entity.player.Inventory;

import com.github.yeetmanlord.somanyenchants.common.container.AbstractEnchantedSmelterContainer;
import com.github.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.ContainerData;

public class EnchantedSmokerContainer extends AbstractEnchantedSmelterContainer
{

	public EnchantedSmokerContainer(int id, Inventory playerInventory)
	{
		super(ContainerTypeInit.ENCHANTED_SMOKER.get(), RecipeType.SMOKING, RecipeBookType.SMOKER, id, playerInventory);
	}

	public EnchantedSmokerContainer(int id, Inventory playerInventory, Container inventory,
			ContainerData data)
	{
		super(ContainerTypeInit.ENCHANTED_SMOKER.get(), RecipeType.SMOKING, RecipeBookType.SMOKER, id, playerInventory, inventory,
				data);
	}

}
