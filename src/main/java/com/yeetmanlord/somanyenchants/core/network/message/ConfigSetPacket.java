package com.yeetmanlord.somanyenchants.core.network.message;

import java.util.function.Supplier;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ConfigSetPacket {
	public int id;
	
	public ConfigSetPacket()
	{
		
	}

	public ConfigSetPacket(int id) 
	{
		this.id = id;
	}
	
	public static void encode(ConfigSetPacket msg, PacketBuffer buff) 
	{
		buff.writeInt(msg.id);
	}
	
	public static ConfigSetPacket decode(PacketBuffer buff) 
	{
		return new ConfigSetPacket(buff.readInt());
	}
	
	public static void handle(ConfigSetPacket msg, Supplier<NetworkEvent.Context> contextSup)
	{
		NetworkEvent.Context context = contextSup.get();
		
		context.enqueueWork(() -> {
			ServerPlayerEntity player = context.getSender();
			MinecraftServer server = player.getServer();
			if(server.isDedicatedServer())
			{
				final CommentedFileConfig file = CommentedFileConfig.builder(server.getFile("world/serverconfig/so_many_enchants-server.toml")).build();
				file.load();

		    	Config.de.maxLevel.set(file.get("Damage Enchantments" + ".maxLevel"));
		    	Config.de.isEnabled.set(file.get("Damage Enchantments" + ".isEnabled"));
		    	
		    	Config.e.maxLevel.set(file.get("Efficiency" + ".maxLevel"));
		    	Config.e.isEnabled.set(file.get("Efficiency" + ".isEnabled"));
		    	
		    	Config.fa.maxLevel.set(file.get("Fire Aspect" + ".maxLevel"));
		    	Config.fa.isEnabled.set(file.get("Fire Aspect" + ".isEnabled"));
		    	
		    	Config.i.maxLevel.set(file.get("Impaling" + ".maxLevel"));
		    	Config.i.isEnabled.set(file.get("Impaling" + ".isEnabled"));
		    	
		    	Config.k.maxLevel.set(file.get("Knockback" + ".maxLevel"));
		    	Config.k.isEnabled.set(file.get("Knockback" + ".isEnabled"));
		    	
		    	Config.lbe.maxLevel.set(file.get("Loot Bonus Enchantments" + ".maxLevel"));
		    	Config.lbe.isEnabled.set(file.get("Loot Bonus Enchantments" + ".isEnabled"));
		    	
		    	Config.lo.maxLevel.set(file.get("Loyalty" + ".maxLevel"));
		    	Config.lo.isEnabled.set(file.get("Loyalty" + ".isEnabled"));
		    	
		    	Config.l.maxLevel.set(file.get("Lure" + ".maxLevel"));
		    	Config.l.isEnabled.set(file.get("Lure" + ".isEnabled"));
		    	
		    	Config.p.maxLevel.set(file.get("Piercing" + ".maxLevel"));
		    	Config.p.isEnabled.set(file.get("Piercing" + ".isEnabled"));
		    	
		    	Config.po.maxLevel.set(file.get("Power" + ".maxLevel"));
		    	Config.po.isEnabled.set(file.get("Power" + ".isEnabled"));
		    	
		    	Config.pr.maxLevel.set(file.get("Protection Enchantments" + ".maxLevel"));
		    	Config.pr.isEnabled.set(file.get("Protection Enchantments" + ".isEnabled"));
		    	
		    	Config.pu.maxLevel.set(file.get("Punch" + ".maxLevel"));
		    	Config.pu.isEnabled.set(file.get("Punch" + ".isEnabled"));
		    	
		    	Config.q.maxLevel.set(file.get("Quick Charge" + ".maxLevel"));
		    	Config.q.isEnabled.set(file.get("Quick Charge" + ".isEnabled"));
		    	
		    	Config.r.maxLevel.set(file.get("Respiration" + ".maxLevel"));
		    	Config.r.isEnabled.set(file.get("Respiration" + ".isEnabled"));
		    	
		    	Config.ri.maxLevel.set(file.get("Riptide" + ".maxLevel"));
		    	Config.ri.isEnabled.set(file.get("Riptide" + ".isEnabled"));
		    	
		    	Config.ss.maxLevel.set(file.get("Soul Speed" + ".maxLevel"));
		    	Config.ss.isEnabled.set(file.get("Soul Speed" + ".isEnabled"));
		    	
		    	Config.s.maxLevel.set(file.get("Sweeping Edge" + ".maxLevel"));
		    	Config.s.isEnabled.set(file.get("Sweeping Edge" + ".isEnabled"));
		    	
		    	Config.t.maxLevel.set(file.get("Thorns" + ".maxLevel"));
		    	Config.t.isEnabled.set(file.get("Thorns" + ".isEnabled"));
		    	
		    	Config.u.maxLevel.set(file.get("Unbreaking" + ".maxLevel"));
		    	Config.u.isEnabled.set(file.get("Unbreaking" + ".isEnabled"));
		    	
		    	Config.he.maxLevel.set(file.get("Heavy" + ".maxLevel"));
		    	Config.he.isEnabled.set(file.get("Heavy" + ".isEnabled"));
		    	
		    	Config.rei.maxLevel.set(file.get("Reinforcement" + ".maxLevel"));
		    	Config.rei.isEnabled.set(file.get("Reinforcement" + ".isEnabled"));
		    	
		    	Config.te.maxLevel.set(file.get("Temper" + ".maxLevel"));
		    	Config.te.isEnabled.set(file.get("Temper" + ".isEnabled"));
		    	
		    	Config.c.maxLevel.set(file.get("Cat Vision" + ".maxLevel"));
		    	Config.c.isEnabled.set(file.get("Cat Vision" + ".isEnabled"));
		    	
		    	Config.fl.maxLevel.set(file.get("Flight" + ".maxLevel"));
		    	Config.fl.isEnabled.set(file.get("Flight" + ".isEnabled"));
		    	
		    	Config.h.maxLevel.set(file.get("Health Boost" + ".maxLevel"));
		    	Config.h.isEnabled.set(file.get("Health Boost" + ".isEnabled"));
		    	
		    	Config.sa.maxLevel.set(file.get("Step Assist" + ".maxLevel"));
		    	Config.sa.isEnabled.set(file.get("Step Assist" + ".isEnabled"));
		
		    	Config.f.isEnabled.set(file.get("Fast Hopper" + ".isEnabled"));
		    	
		    	Config.cs.isEnabled.set(file.get("Cavernous Storage" + ".isEnabled"));
		    	
		    	Config.cf.isEnabled.set(file.get("Camouflage" + ".isEnabled"));
		
		    	Config.b.maxLevel.set(file.get("Block Reach" + ".maxLevel"));
		    	Config.b.isEnabled.set(file.get("Block Reach" + ".isEnabled"));
		    	
		    	Config.d.maxLevel.set(file.get("Double Break" + ".maxLevel"));
		    	Config.d.isEnabled.set(file.get("Double Break" + ".isEnabled"));
		    	
		    	Config.re.maxLevel.set(file.get("Replanting" + ".maxLevel"));
		    	Config.re.isEnabled.set(file.get("Replanting" + ".isEnabled"));
		    	
		    	
		    	Config.a.maxLevel.set(file.get("Attack Reach" + ".maxLevel"));
		    	Config.a.isEnabled.set(file.get("Attack Reach" + ".isEnabled"));
		    	
		    	Config.cr.maxLevel.set(file.get("Critical" + ".maxLevel"));
		    	Config.cr.isEnabled.set(file.get("Critical" + ".isEnabled"));
		    	
		    	Config.fr.maxLevel.set(file.get("Freezing" + ".maxLevel"));
		    	Config.fr.isEnabled.set(file.get("Freezing" + ".isEnabled"));

		    	Config.v.isEnabled.set(file.get("Enchanter Villager" + ".isEnabled"));
		    	
		    	Config.cs.isEnabled.set(file.get("Cavernous Storage.isEnabled"));
				
				Config.cf.isEnabled.set(file.get("Camouflage.isEnabled"));
				
				Config.SyncedServerConfig.setConfig(file);
				
				file.save();
			}
			
		});
		
		context.setPacketHandled(true);
	}

}
