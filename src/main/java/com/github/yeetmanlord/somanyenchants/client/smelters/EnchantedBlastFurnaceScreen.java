package com.github.yeetmanlord.somanyenchants.client.smelters;

import com.github.yeetmanlord.somanyenchants.client.AbstractEnchantedSmelterScreen;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.blast_furnace.EnchantedBlastFurnaceContainer;

import net.minecraft.client.gui.recipebook.BlastFurnaceRecipeGui;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class EnchantedBlastFurnaceScreen extends AbstractEnchantedSmelterScreen<EnchantedBlastFurnaceContainer>
{
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(
			"textures/gui/container/blast_furnace.png");

	public EnchantedBlastFurnaceScreen(EnchantedBlastFurnaceContainer screenContainer, PlayerInventory inv,
			ITextComponent titleIn)
	{
		super(screenContainer, new BlastFurnaceRecipeGui(), inv, titleIn, GUI_TEXTURE);
	}
}
