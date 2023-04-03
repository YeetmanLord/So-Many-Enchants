package com.github.yeetmanlord.somanyenchants.client.smelters;

import com.github.yeetmanlord.somanyenchants.client.AbstractEnchantedSmelterScreen;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.furnace.EnchantedFurnaceContainer;

import net.minecraft.client.gui.recipebook.FurnaceRecipeGui;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantedFurnaceScreen extends AbstractEnchantedSmelterScreen<EnchantedFurnaceContainer>
{
	private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation(
			"textures/gui/container/furnace.png");

	public EnchantedFurnaceScreen(EnchantedFurnaceContainer screenContainer, PlayerInventory inv,
			ITextComponent titleIn)
	{
		super(screenContainer, new FurnaceRecipeGui(), inv, titleIn, FURNACE_GUI_TEXTURES);
	}

}
