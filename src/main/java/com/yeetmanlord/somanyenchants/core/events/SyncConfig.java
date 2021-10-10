package com.yeetmanlord.somanyenchants.core.events;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.yeetmanlord.somanyenchants.core.network.message.ConfigSetPacket;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.loading.FMLPaths;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class SyncConfig {
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void syncConfig(final EntityJoinWorldEvent event)
	{
		if(event.getEntity() instanceof ClientPlayerEntity)
		{
			((ClientPlayerEntity)event.getEntity()).sendStatusMessage(new StringTextComponent("\u00a7a[So Many Enchants] \u00a74\u00a7lWarning! \u00a7fThis mod is in beta and not fully tested so please report bugs on the issue tracker so I can fix them."), false);
			NetworkHandler.CHANNEL.sendToServer(new ConfigSetPacket(0));
		}
	}
		
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void syncConfig(final EntityLeaveWorldEvent event)
	{
		if(event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();
			if(!player.getShouldBeDead())
			{
				final CommentedFileConfig file = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().resolve("so_many_enchants-common.toml").toString())).sync().autosave().writingMode(WritingMode.REPLACE).build();
				file.load();
				
		    	Config.de.maxLevel.set(file.get("Damage Enchantments.maxLevel"));
		    	Config.de.isEnabled.set(file.get("Damage Enchantments.isEnabled"));
		    	
		    	Config.e.maxLevel.set(file.get("Efficiency.maxLevel"));
		    	Config.e.isEnabled.set(file.get("Efficiency.isEnabled"));
		    	
		    	Config.fa.maxLevel.set(file.get("Fire Aspect.maxLevel"));
		    	Config.fa.isEnabled.set(file.get("Fire Aspect.isEnabled"));
		    	
		    	Config.i.maxLevel.set(file.get("Impaling.maxLevel"));
		    	Config.i.isEnabled.set(file.get("Impaling.isEnabled"));
		    	
		    	Config.k.maxLevel.set(file.get("Knockback.maxLevel"));
		    	Config.k.isEnabled.set(file.get("Knockback.isEnabled"));
		    	
		    	Config.lbe.maxLevel.set(file.get("Loot Bonus Enchantments.maxLevel"));
		    	Config.lbe.isEnabled.set(file.get("Loot Bonus Enchantments.isEnabled"));
		    	
		    	Config.lo.maxLevel.set(file.get("Loyalty.maxLevel"));
		    	Config.lo.isEnabled.set(file.get("Loyalty.isEnabled"));
		    	
		    	Config.l.maxLevel.set(file.get("Lure.maxLevel"));
		    	Config.l.isEnabled.set(file.get("Lure.isEnabled"));
		    	
		    	Config.p.maxLevel.set(file.get("Piercing.maxLevel"));
		    	Config.p.isEnabled.set(file.get("Piercing.isEnabled"));
		    	
		    	Config.po.maxLevel.set(file.get("Power.maxLevel"));
		    	Config.po.isEnabled.set(file.get("Power.isEnabled"));
		    	
		    	Config.pr.maxLevel.set(file.get("Protection Enchantments.maxLevel"));
		    	Config.pr.isEnabled.set(file.get("Protection Enchantments.isEnabled"));
		    	
		    	Config.pu.maxLevel.set(file.get("Punch.maxLevel"));
		    	Config.pu.isEnabled.set(file.get("Punch.isEnabled"));
		    	
		    	Config.q.maxLevel.set(file.get("Quick Charge.maxLevel"));
		    	Config.q.isEnabled.set(file.get("Quick Charge.isEnabled"));
		    	
		    	Config.r.maxLevel.set(file.get("Respiration.maxLevel"));
		    	Config.r.isEnabled.set(file.get("Respiration.isEnabled"));
		    	
		    	Config.ri.maxLevel.set(file.get("Riptide.maxLevel"));
		    	Config.ri.isEnabled.set(file.get("Riptide.isEnabled"));
		    	
		    	Config.ss.maxLevel.set(file.get("Soul Speed.maxLevel"));
		    	Config.ss.isEnabled.set(file.get("Soul Speed.isEnabled"));
		    	
		    	Config.s.maxLevel.set(file.get("Sweeping Edge.maxLevel"));
		    	Config.s.isEnabled.set(file.get("Sweeping Edge.isEnabled"));
		    	
		    	Config.t.maxLevel.set(file.get("Thorns.maxLevel"));
		    	Config.t.isEnabled.set(file.get("Thorns.isEnabled"));
		    	
		    	Config.u.maxLevel.set(file.get("Unbreaking.maxLevel"));
		    	Config.u.isEnabled.set(file.get("Unbreaking.isEnabled"));
		    	
		    	Config.he.maxLevel.set(file.get("Heavy.maxLevel"));
		    	Config.he.isEnabled.set(file.get("Heavy.isEnabled"));
		    	
		    	Config.rei.maxLevel.set(file.get("Reinforcement.maxLevel"));
		    	Config.rei.isEnabled.set(file.get("Reinforcement.isEnabled"));
	
		    	Config.te.maxLevel.set(file.get("Temper.maxLevel"));
		    	Config.te.isEnabled.set(file.get("Temper.isEnabled"));
		    	
		    	Config.c.isEnabled.set(file.get("Cat Vision.isEnabled"));
		    	
		    	Config.fl.maxLevel.set(file.get("Flight.maxLevel"));
		    	Config.fl.isEnabled.set(file.get("Flight.isEnabled"));
		    	
		    	Config.h.maxLevel.set(file.get("Health Boost.maxLevel"));
		    	Config.h.isEnabled.set(file.get("Health Boost.isEnabled"));
		    	
		    	Config.sa.maxLevel.set(file.get("Step Assist.maxLevel"));
		    	Config.sa.isEnabled.set(file.get("Step Assist.isEnabled"));
	
		//    	Config.f.isEnabled.set(file.get("Fast Hopper.isEnabled"));
		
		    	
		    	Config.b.maxLevel.set(file.get("Block Reach.maxLevel"));
		    	Config.b.isEnabled.set(file.get("Block Reach.isEnabled"));
		    	
		    	Config.d.maxLevel.set(file.get("Double Break.maxLevel"));
		    	Config.d.isEnabled.set(file.get("Double Break.isEnabled"));
		    	
		    	Config.re.isEnabled.set(file.get("Replanting.isEnabled"));
		    	
		    	
		    	Config.a.maxLevel.set(file.get("Attack Reach.maxLevel"));
		    	Config.a.isEnabled.set(file.get("Attack Reach.isEnabled"));
		    	
		    	Config.cr.maxLevel.set(file.get("Critical.maxLevel"));
		    	Config.cr.isEnabled.set(file.get("Critical.isEnabled"));
		    	
		    	Config.fr.maxLevel.set(file.get("Freezing.maxLevel"));
		    	Config.fr.isEnabled.set(file.get("Freezing.isEnabled"));
	
		    	Config.v.isEnabled.set(file.get("Enchanter Villager.isEnabled"));
				
				Config.SyncedServerConfig.setConfig(file);
				
				file.save();
			}
		}
	}
}
