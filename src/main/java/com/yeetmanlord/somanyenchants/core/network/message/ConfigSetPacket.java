package com.yeetmanlord.somanyenchants.core.network.message;

import java.util.function.Supplier;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ConfigSetPacket {
	public int id;
	
	public ConfigSetPacket()
	{
		
	}

	public ConfigSetPacket(int id) 
	{
		this.id = id;
	}
	
	public static void encode(ConfigSetPacket msg, PacketBuffer buff) 
	{
		buff.writeInt(msg.id);
	}
	
	public static ConfigSetPacket decode(PacketBuffer buff) 
	{
		return new ConfigSetPacket(buff.readInt());
	}
	
	public static void handle(ConfigSetPacket msg, Supplier<NetworkEvent.Context> contextSup)
	{
		NetworkEvent.Context context = contextSup.get();
		
		context.enqueueWork(() -> {
			ServerPlayerEntity player = context.getSender();
			MinecraftServer server = player.getServer();
			if(server.isDedicatedServer())
			{	
				final CommentedFileConfig file = CommentedFileConfig.builder(server.getFile("world/serverconfig/so_many_enchants-server.toml")).build();
				file.load();

		    	Config.load(file);
				
				Config.SyncedServerConfig.setConfig(file);
				
				file.save();
			}
			
		});
		
		context.setPacketHandled(true);
	}

}
