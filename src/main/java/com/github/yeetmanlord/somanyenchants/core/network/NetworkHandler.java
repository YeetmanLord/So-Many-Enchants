package com.github.yeetmanlord.somanyenchants.core.network;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.network.message.AttackPacket;
import com.github.yeetmanlord.somanyenchants.core.network.message.ClientConfigSyncPacket;
import com.github.yeetmanlord.somanyenchants.core.network.message.ConfigSyncPacket;
import com.github.yeetmanlord.somanyenchants.core.network.message.FlyingPacket;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {

	public static final String NETWORK_VERSION = "SME-MAIN-1.2.0";
	
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(SoManyEnchants.MOD_ID, "packet_handler"), 
			() -> NETWORK_VERSION, version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));
	
	
	public static void init()
	{
		CHANNEL.registerMessage(0, AttackPacket.class, AttackPacket::encode, AttackPacket::decode, AttackPacket::handle);
		CHANNEL.registerMessage(1, ConfigSyncPacket.class, ConfigSyncPacket::encode, ConfigSyncPacket::decode, ConfigSyncPacket::handle);
		CHANNEL.registerMessage(2, FlyingPacket.class, FlyingPacket::encode, FlyingPacket::decode, FlyingPacket::handle);
		CHANNEL.registerMessage(3, ClientConfigSyncPacket.class, ClientConfigSyncPacket::encode, ClientConfigSyncPacket::decode, ClientConfigSyncPacket::handle);
	}
	

}
