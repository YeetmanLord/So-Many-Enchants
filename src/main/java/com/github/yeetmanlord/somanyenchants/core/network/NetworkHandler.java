package com.github.yeetmanlord.somanyenchants.core.network;

import com.github.yeetmanlord.somanyenchants.core.network.message.AttackPacket;
import com.github.yeetmanlord.somanyenchants.core.network.message.FlyingPacket;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {

	public static final String NETWORK_VERSION = "SME-MAIN-1.3.0";
	
	public static final SimpleChannel CHANNEL;
	static {
	    CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation("so_many_enchants", "packet_handler"), () -> NETWORK_VERSION, version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));
	  }
	
	public static void init()
	{
		CHANNEL.registerMessage(0, AttackPacket.class, AttackPacket::encode, AttackPacket::decode, AttackPacket::handle);
		CHANNEL.registerMessage(2, FlyingPacket.class, FlyingPacket::encode, FlyingPacket::decode, FlyingPacket::handle);
	}
	

}
