package com.github.yeetmanlord.somanyenchants.core.util;

import java.util.function.Supplier;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

public class Scheduler {
	
	public Player owner;
	private int timer;
	private Runnable runnable;
	private int scheduleTime;
	public boolean hasTask;
	
	public Scheduler(Player owner) 
	{
		this.owner = owner;
		timer = 0;
		hasTask = false;
	}
	
	public void schedule(Supplier<Runnable> runnable, int time)
	{
		this.runnable = runnable.get();
		this.timer = 0;
		this.scheduleTime = time;
		hasTask = true;
	}
	
	public void tick(ServerTickEvent event)
	{
		if(this.hasTask)
		{
			timer++;
			if(timer >= scheduleTime)
			{
				runnable.run();
				scheduleTime = 0;
				timer = 0;
				hasTask = false;
			}
		}
	}
	
	@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
	public static class SchedulerEvents
	{
		@SubscribeEvent
		public static void serverTick(final ServerTickEvent tick)
		{
			for(Scheduler sch : SoManyEnchants.playerTaskSchedulers.values())
			{
				if(sch.hasTask)
				{
					sch.tick(tick);
				}
			}
		}
	}
}
