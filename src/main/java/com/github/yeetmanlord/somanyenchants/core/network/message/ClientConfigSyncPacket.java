package com.github.yeetmanlord.somanyenchants.core.network.message;

import java.util.function.Supplier;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public class ClientConfigSyncPacket {

	public String file;

	public ClientConfigSyncPacket() {

	}

	public ClientConfigSyncPacket(String file) {

		this.file = file;

	}

	public static void encode(ClientConfigSyncPacket msg, FriendlyByteBuf buff) {

		buff.writeUtf(msg.file);

	}

	public static ClientConfigSyncPacket decode(FriendlyByteBuf buff) {

		return new ClientConfigSyncPacket(buff.readUtf());

	}

	public static void handle(ClientConfigSyncPacket msg, Supplier<NetworkEvent.Context> contextSup) {

		NetworkEvent.Context context = contextSup.get();
		context.enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientAccess.run(msg.file));
		});

		context.setPacketHandled(true);

	}

	static class ClientAccess {

		static void run(String file) {

			SoManyEnchants.LOGGER.info("Config sync complete");
			Config.loadConfig(file);

		}

	}

}
