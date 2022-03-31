package com.github.yeetmanlord.somanyenchants.core.network.message;

import java.util.function.Supplier;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class ConfigSetPacket {

	public int id;

	public ConfigSetPacket() {

	}

	public ConfigSetPacket(int id) {

		this.id = id;

	}

	public static void encode(ConfigSetPacket msg, FriendlyByteBuf buff) {

		buff.writeInt(msg.id);

	}

	public static ConfigSetPacket decode(FriendlyByteBuf buff) {

		return new ConfigSetPacket(buff.readInt());

	}

	public static void handle(ConfigSetPacket msg, Supplier<NetworkEvent.Context> contextSup) {

		NetworkEvent.Context context = contextSup.get();
		SoManyEnchants.LOGGER.info("Synching Configs!");

		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			MinecraftServer server = player.getServer();

			if (server.isDedicatedServer()) {
				final CommentedFileConfig file = CommentedFileConfig.builder(server.getFile("world/serverconfig/so_many_enchants-server.toml")).build();
				file.load();
				Config.SyncedServerConfig.setConfig(file);
			}

		});

		context.setPacketHandled(true);

	}

}
