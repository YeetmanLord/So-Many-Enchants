package com.yeetmanlord.somanyenchants.core.init;

import com.yeetmanlord.somanyenchants.Main;

import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD)
public class AddAttributesToEntities {
	
	@SubscribeEvent
	public static void addAttribute(final EntityAttributeModificationEvent event)
	{
		event.add(EntityType.PLAYER, AttributeInit.ATTACK_DISTANCE.get());
	}

}
