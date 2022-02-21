package com.yeetmanlord.somanyenchants.core.events;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.util.PlayerAttributeHandler;
import com.yeetmanlord.somanyenchants.core.util.PlayerUtilities;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class HandlePlayerJoins 
{
	@SubscribeEvent
	public static void onPlayerJoin(final PlayerLoggedInEvent event)
	{
		if(event.getEntity() instanceof Player)
		{
			Player player = (Player)event.getEntity();
			PlayerUtilities util = Main.getPlayerUtil(player);
			PlayerAttributeHandler.reset(player); 
		}
	}
}
