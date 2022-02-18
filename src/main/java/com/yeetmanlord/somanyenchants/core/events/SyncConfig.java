package com.yeetmanlord.somanyenchants.core.events;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.yeetmanlord.somanyenchants.core.network.message.ConfigSetPacket;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.loading.FMLPaths;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class SyncConfig {
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void syncConfig(final PlayerLoggedInEvent event) {
		if (event.getEntity() instanceof ClientPlayerEntity) {
			NetworkHandler.CHANNEL.sendToServer(new ConfigSetPacket(0));
		}
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void syncConfig(final PlayerLoggedOutEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			if (!player.getShouldBeDead()) {
				final CommentedFileConfig file = CommentedFileConfig
						.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString()))
						.sync().autosave().writingMode(WritingMode.REPLACE).build();
				file.load();

				Config.load(file);

				Config.SyncedServerConfig.setConfig(file);

				file.save();
			}
		}
	}
}
