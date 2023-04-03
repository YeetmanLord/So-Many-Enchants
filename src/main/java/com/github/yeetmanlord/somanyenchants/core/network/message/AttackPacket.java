package com.github.yeetmanlord.somanyenchants.core.network.message;

import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

public class AttackPacket {
	public int attacked;

	public AttackPacket() 
	{
		
	}
	
	public AttackPacket(int attacked)
	{
		this.attacked = attacked;
	}
	
	public static void encode(AttackPacket msg, PacketBuffer buff) 
	{
		buff.writeInt(msg.attacked);
	}
	
	public static AttackPacket decode(PacketBuffer buff) 
	{
		return new AttackPacket(buff.readInt());
	}
	
	public static void handle(AttackPacket msg, Supplier<NetworkEvent.Context> contextSup)
	{
		NetworkEvent.Context context = contextSup.get();
		
		context.enqueueWork(() -> {
			ServerPlayerEntity player = context.getSender();
			World world = player.getCommandSenderWorld();
			Entity e = world.getEntity(msg.attacked);
			player.attack(e);
		});
		
		context.setPacketHandled(true);
	}

}
