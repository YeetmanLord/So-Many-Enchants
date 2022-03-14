package com.github.yeetmanlord.somanyenchants.common.blocks.smelters.blast_furnace;

import net.minecraft.world.entity.player.Inventory;

import com.github.yeetmanlord.somanyenchants.common.container.AbstractEnchantedSmelterContainer;
import com.github.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.ContainerData;

public class EnchantedBlastFurnaceContainer extends AbstractEnchantedSmelterContainer
{

	public EnchantedBlastFurnaceContainer(int id, Inventory playerInv)
	{
		super(ContainerTypeInit.ENCHANTED_BLAST_FURNACE.get(), RecipeType.BLASTING, RecipeBookType.BLAST_FURNACE, id,
				playerInv);
	}

	public EnchantedBlastFurnaceContainer(int id, Inventory playerInv, Container inventory,
			ContainerData data)
	{
		super(ContainerTypeInit.ENCHANTED_BLAST_FURNACE.get(), RecipeType.BLASTING, RecipeBookType.BLAST_FURNACE, id,
				playerInv, inventory, data);
	}

}
