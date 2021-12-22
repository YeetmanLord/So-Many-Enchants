package com.yeetmanlord.somanyenchants.common.blocks.smelters.smoker;

import com.yeetmanlord.somanyenchants.common.container.AbstractEnchantedSmelterContainer;
import com.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.util.IIntArray;

public class EnchantedSmokerContainer extends AbstractEnchantedSmelterContainer
{

	public EnchantedSmokerContainer(int id, PlayerInventory playerInventory)
	{
		super(ContainerTypeInit.ENCHANTED_SMOKER.get(), IRecipeType.SMOKING, RecipeBookCategory.SMOKER, id, playerInventory);
	}

	public EnchantedSmokerContainer(int id, PlayerInventory playerInventory, IInventory inventory,
			IIntArray data)
	{
		super(ContainerTypeInit.ENCHANTED_SMOKER.get(), IRecipeType.SMOKING, RecipeBookCategory.SMOKER, id, playerInventory, inventory,
				data);
	}

}
