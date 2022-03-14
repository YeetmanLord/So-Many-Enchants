package com.github.yeetmanlord.somanyenchants.client.smelters;

import com.github.yeetmanlord.somanyenchants.client.AbstractEnchantedSmelterScreen;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.blast_furnace.EnchantedBlastFurnaceContainer;

import net.minecraft.client.gui.screens.recipebook.BlastingRecipeBookComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class EnchantedBlastFurnaceScreen extends AbstractEnchantedSmelterScreen<EnchantedBlastFurnaceContainer>
{
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(
			"textures/gui/container/blast_furnace.png");

	public EnchantedBlastFurnaceScreen(EnchantedBlastFurnaceContainer screenContainer, Inventory inv,
			Component titleIn)
	{
		super(screenContainer, new BlastingRecipeBookComponent(), inv, titleIn, GUI_TEXTURE);
	}
}
