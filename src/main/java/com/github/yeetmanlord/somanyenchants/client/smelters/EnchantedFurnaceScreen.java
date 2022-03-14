package com.github.yeetmanlord.somanyenchants.client.smelters;

import com.github.yeetmanlord.somanyenchants.client.AbstractEnchantedSmelterScreen;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.furnace.EnchantedFurnaceContainer;

import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantedFurnaceScreen extends AbstractEnchantedSmelterScreen<EnchantedFurnaceContainer>
{
	private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation(
			"textures/gui/container/furnace.png");

	public EnchantedFurnaceScreen(EnchantedFurnaceContainer screenContainer, Inventory inv,
			Component titleIn)
	{
		super(screenContainer, new SmeltingRecipeBookComponent(), inv, titleIn, FURNACE_GUI_TEXTURES);
	}

}
