package com.github.yeetmanlord.somanyenchants.core.network.message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.github.yeetmanlord.somanyenchants.core.util.Scheduler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class ConfigSyncPacket {

	public int id;

	public ConfigSyncPacket() {

	}

	public ConfigSyncPacket(int id) {

		this.id = id;

	}

	public static void encode(ConfigSyncPacket msg, PacketBuffer buff) {

		buff.writeInt(msg.id);

	}

	public static ConfigSyncPacket decode(PacketBuffer buff) {

		return new ConfigSyncPacket(buff.readInt());

	}

	public static void handle(ConfigSyncPacket msg, Supplier<NetworkEvent.Context> contextSup) {

		NetworkEvent.Context context = contextSup.get();

		context.enqueueWork(() -> {
			ServerPlayerEntity player = context.getSender();
			MinecraftServer server = player.getServer();

			if (server.isDedicatedServer()) {
				SoManyEnchants.LOGGER.info("Synching Configs!");

				try {
					String file = new String(Files.readAllBytes(Paths.get("world/serverconfig/so_many_enchants-server.json")));
					Scheduler sch = SoManyEnchants.getScheduler(player);
					sch.schedule(() -> new Runnable() {

						@Override
						public void run() {

							NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
								return player;
							}), new ClientConfigSyncPacket(file));

						}

					}, 0);
				}
				catch (IOException e) {
					e.printStackTrace();
				}

			}

		});

		context.setPacketHandled(true);

	}

}
