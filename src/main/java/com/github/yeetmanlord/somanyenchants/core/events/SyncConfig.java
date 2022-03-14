package com.github.yeetmanlord.somanyenchants.core.events;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.github.yeetmanlord.somanyenchants.core.network.message.ConfigSetPacket;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.loading.FMLPaths;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class SyncConfig {

	@SubscribeEvent @OnlyIn(Dist.CLIENT)
	public static void syncConfig(final EntityJoinWorldEvent event) {

		if (event.getEntity() instanceof LocalPlayer) {
			NetworkHandler.CHANNEL.sendToServer(new ConfigSetPacket(0));
		}

	}

	@SubscribeEvent @OnlyIn(Dist.CLIENT)
	public static void syncConfig(final EntityLeaveWorldEvent event) {

		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();

			if (!player.isDeadOrDying()) {
				final CommentedFileConfig file = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync().autosave().writingMode(WritingMode.REPLACE).build();
				file.load();

				Config.load(file);

				Config.SyncedServerConfig.setConfig(file);

				file.save();
			}

		}

	}

}
