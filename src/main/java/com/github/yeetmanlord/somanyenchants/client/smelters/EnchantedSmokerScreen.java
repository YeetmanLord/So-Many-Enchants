package com.github.yeetmanlord.somanyenchants.client.smelters;

import com.github.yeetmanlord.somanyenchants.client.AbstractEnchantedSmelterScreen;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.smoker.EnchantedSmokerContainer;

import net.minecraft.client.gui.screens.recipebook.SmokingRecipeBookComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantedSmokerScreen extends AbstractEnchantedSmelterScreen<EnchantedSmokerContainer>
{
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("textures/gui/container/smoker.png");

	public EnchantedSmokerScreen(EnchantedSmokerContainer screenContainer, Inventory inv, Component titleIn)
	{
		super(screenContainer, new SmokingRecipeBookComponent(), inv, titleIn, GUI_TEXTURE);
	}
}
