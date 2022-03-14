package com.github.yeetmanlord.somanyenchants.core.network.message;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

public class AttackPacket {
	public int attacked;

	public AttackPacket() 
	{
		
	}
	
	public AttackPacket(int attacked)
	{
		this.attacked = attacked;
	}
	
	public static void encode(AttackPacket msg, FriendlyByteBuf buff) 
	{
		buff.writeInt(msg.attacked);
	}
	
	public static AttackPacket decode(FriendlyByteBuf buff) 
	{
		return new AttackPacket(buff.readInt());
	}
	
	public static void handle(AttackPacket msg, Supplier<NetworkEvent.Context> contextSup)
	{
		NetworkEvent.Context context = contextSup.get();
		
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			Level world = player.getCommandSenderWorld();
			Entity e = world.getEntity(msg.attacked);
			player.attack(e);
		});
		
		context.setPacketHandled(true);
	}

}
