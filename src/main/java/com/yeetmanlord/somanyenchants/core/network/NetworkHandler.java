package com.yeetmanlord.somanyenchants.core.network;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.network.message.AttackPacket;
import com.yeetmanlord.somanyenchants.core.network.message.ConfigSetPacket;
import com.yeetmanlord.somanyenchants.core.network.message.FlyingPacket;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {

	public static final String NETWORK_VERSION = "1.0.1";
	
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(Main.MOD_ID, "main_network"), 
			() -> NETWORK_VERSION, version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));
	
	
	public static void init()
	{
		CHANNEL.registerMessage(0, AttackPacket.class, AttackPacket::encode, AttackPacket::decode, AttackPacket::handle);
		CHANNEL.registerMessage(1, ConfigSetPacket.class, ConfigSetPacket::encode, ConfigSetPacket::decode, ConfigSetPacket::handle);
		CHANNEL.registerMessage(2, FlyingPacket.class, FlyingPacket::encode, FlyingPacket::decode, FlyingPacket::handle);
	}
	

}
