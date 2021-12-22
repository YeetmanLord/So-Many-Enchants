package com.yeetmanlord.somanyenchants.common.blocks.smelters.blast_furnace;

import com.yeetmanlord.somanyenchants.common.container.AbstractEnchantedSmelterContainer;
import com.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.util.IIntArray;

public class EnchantedBlastFurnaceContainer extends AbstractEnchantedSmelterContainer
{

	public EnchantedBlastFurnaceContainer(int id, PlayerInventory playerInv)
	{
		super(ContainerTypeInit.ENCHANTED_BLAST_FURNACE.get(), IRecipeType.BLASTING, RecipeBookCategory.BLAST_FURNACE, id,
				playerInv);
	}

	public EnchantedBlastFurnaceContainer(int id, PlayerInventory playerInv, IInventory inventory,
			IIntArray data)
	{
		super(ContainerTypeInit.ENCHANTED_BLAST_FURNACE.get(), IRecipeType.BLASTING, RecipeBookCategory.BLAST_FURNACE, id,
				playerInv, inventory, data);
	}

}
