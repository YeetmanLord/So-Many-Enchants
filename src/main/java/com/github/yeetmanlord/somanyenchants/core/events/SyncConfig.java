package com.github.yeetmanlord.somanyenchants.core.events;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.github.yeetmanlord.somanyenchants.core.network.message.ConfigSyncPacket;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class SyncConfig {

	@SubscribeEvent
	public static void syncConfig(final ClientPlayerNetworkEvent.LoggingIn event) {

		NetworkHandler.CHANNEL.sendToServer(new ConfigSyncPacket(0));

	}

	@SubscribeEvent
	public static void syncConfig(final ClientPlayerNetworkEvent.LoggingOut event) {

		Config.unload();

	}

}
