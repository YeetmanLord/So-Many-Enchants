package com.github.yeetmanlord.somanyenchants.core.events;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class ServerStart {

	@SubscribeEvent
	public static void onServerStart(ServerAboutToStartEvent event) {

		if (event.getServer() instanceof DedicatedServer) {
			System.out.println("testasdfadsfasdfasdf");
			File file = event.getServer().getFile("world/serverconfig/so_many_enchants-server.json");

			if (!file.exists()) {

				Config.setDefaults(file);

			}

			Config.validate(file);

			try {
				Config.loadConfig(new String(Files.readAllBytes(Paths.get(file.getPath()))));
			}
			catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
