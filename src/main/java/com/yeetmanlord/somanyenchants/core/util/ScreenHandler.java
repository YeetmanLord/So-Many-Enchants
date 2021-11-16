package com.yeetmanlord.somanyenchants.core.util;


import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.client.EnchantedChestScreen;
import com.yeetmanlord.somanyenchants.client.EnchantedHopperScreen;
import com.yeetmanlord.somanyenchants.client.EnchantedShulkerBoxScreen;
import com.yeetmanlord.somanyenchants.core.init.ContainerTypeInit;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ScreenHandler
{
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		ScreenManager.registerFactory(ContainerTypeInit.ENCHANTED_HOPPER.get(), EnchantedHopperScreen::new);
		
		ScreenManager.registerFactory(ContainerTypeInit.GENERIC_9X8.get(), EnchantedChestScreen::new);
		ScreenManager.registerFactory(ContainerTypeInit.GENERIC_9X4.get(), EnchantedChestScreen::new);
		
		ScreenManager.registerFactory(ContainerTypeInit.GENERIC_9X3.get(), EnchantedChestScreen::new);
		ScreenManager.registerFactory(ContainerTypeInit.GENERIC_9X6.get(), EnchantedChestScreen::new);
		
		ScreenManager.registerFactory(ContainerTypeInit.ENCHANTED_SHULKER_BOX.get(), EnchantedShulkerBoxScreen::new);
	}

}

