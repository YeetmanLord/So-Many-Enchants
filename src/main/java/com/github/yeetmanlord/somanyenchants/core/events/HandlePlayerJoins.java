package com.github.yeetmanlord.somanyenchants.core.events;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.util.PlayerAttributeHandler;
import com.github.yeetmanlord.somanyenchants.core.util.PlayerUtilities;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class HandlePlayerJoins 
{
	@SubscribeEvent
	public static void onPlayerJoin(final PlayerLoggedInEvent event)
	{
		if(event.getEntity() instanceof Player)
		{
			Player player = (Player)event.getEntity();
			PlayerUtilities util = SoManyEnchants.getPlayerUtil(player);
			PlayerAttributeHandler.reset(player); 
		}
	}
}
