package com.github.yeetmanlord.somanyenchants.core.events;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.github.yeetmanlord.somanyenchants.core.network.message.ConfigSetPacket;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggedInEvent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.loading.FMLPaths;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class SyncConfig {

	@SubscribeEvent
	public static void syncConfig(final LoggedInEvent event) {

		NetworkHandler.CHANNEL.sendToServer(new ConfigSetPacket(0));

	}

	@SubscribeEvent
	public static void syncConfig(final LoggedOutEvent event) {

		if (event.getPlayer() != null) {
			final CommentedFileConfig file = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync().autosave().writingMode(WritingMode.REPLACE).build();
			file.load();
			Config.load(file);
			Config.SyncedServerConfig.setConfig(file);
		}

	}

}
