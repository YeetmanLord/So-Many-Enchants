package com.github.yeetmanlord.somanyenchants.core.util;


import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.client.EnchantedChestScreen;
import com.github.yeetmanlord.somanyenchants.client.EnchantedHopperScreen;
import com.github.yeetmanlord.somanyenchants.client.EnchantedShulkerBoxScreen;
import com.github.yeetmanlord.somanyenchants.client.smelters.EnchantedBlastFurnaceScreen;
import com.github.yeetmanlord.somanyenchants.client.smelters.EnchantedFurnaceScreen;
import com.github.yeetmanlord.somanyenchants.client.smelters.EnchantedSmokerScreen;
import com.github.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ScreenHandler
{
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		MenuScreens.register(ContainerTypeInit.ENCHANTED_HOPPER.get(), EnchantedHopperScreen::new);
		MenuScreens.register(ContainerTypeInit.GENERIC_9X8.get(), EnchantedChestScreen::new);
		MenuScreens.register(ContainerTypeInit.GENERIC_9X4.get(), EnchantedChestScreen::new);
		MenuScreens.register(ContainerTypeInit.GENERIC_9X3.get(), EnchantedChestScreen::new);
		MenuScreens.register(ContainerTypeInit.GENERIC_9X6.get(), EnchantedChestScreen::new);
		MenuScreens.register(ContainerTypeInit.ENCHANTED_SHULKER_BOX.get(), EnchantedShulkerBoxScreen::new);
		MenuScreens.register(ContainerTypeInit.ENCHANTED_FURNACE.get(), EnchantedFurnaceScreen::new);
		MenuScreens.register(ContainerTypeInit.ENCHANTED_BLAST_FURNACE.get(),
				EnchantedBlastFurnaceScreen::new);
		MenuScreens.register(ContainerTypeInit.ENCHANTED_SMOKER.get(), EnchantedSmokerScreen::new);
	}

}

