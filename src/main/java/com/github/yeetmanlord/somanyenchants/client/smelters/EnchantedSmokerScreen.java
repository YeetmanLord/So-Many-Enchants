package com.github.yeetmanlord.somanyenchants.client.smelters;

import com.github.yeetmanlord.somanyenchants.client.AbstractEnchantedSmelterScreen;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.smoker.EnchantedSmokerContainer;

import net.minecraft.client.gui.recipebook.SmokerRecipeGui;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantedSmokerScreen extends AbstractEnchantedSmelterScreen<EnchantedSmokerContainer>
{
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("textures/gui/container/smoker.png");

	public EnchantedSmokerScreen(EnchantedSmokerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn)
	{
		super(screenContainer, new SmokerRecipeGui(), inv, titleIn, GUI_TEXTURE);
	}
}
