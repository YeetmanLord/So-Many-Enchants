package com.github.yeetmanlord.somanyenchants.core.network.message;

import java.util.function.Supplier;

import com.github.yeetmanlord.somanyenchants.core.events.ArmorEnchantments;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

public class FlyingPacket {
	private boolean setFly;
	
	public FlyingPacket() {}
	public FlyingPacket(boolean setFly)
	{
		this.setFly = setFly;
	}
	
	public static void encode(FlyingPacket msg, PacketBuffer buff) 
	{
		buff.writeBoolean(msg.setFly);
	}
	
	public static FlyingPacket decode(PacketBuffer buff) 
	{
		return new FlyingPacket(buff.readBoolean());
	}
	
	public static void handle(FlyingPacket msg, Supplier<NetworkEvent.Context> contextSup) 
	{
		NetworkEvent.Context context = contextSup.get();
		
		context.enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ArmorEnchantments.ClientAccess.updatePlayerFlying(msg.setFly));
		});
		
		context.setPacketHandled(true);
	}
}
