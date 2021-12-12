package com.yeetmanlord.somanyenchants.core.config;

import java.util.HashMap;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config
{
	
	public static boolean hasInit = false;
	
	public static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
	
	public static final ForgeConfigSpec config;
	
	public static final ForgeConfigSpec SyncedServerConfig;
	
	public static final HashMap<String, EnchantmentConfig> configSections = new HashMap<>();
	
	
	public static VanillaEnchantmentConfig de;
	public static VanillaEnchantmentConfig e;
	public static VanillaEnchantmentConfig fa;
	public static VanillaEnchantmentConfig i;
	public static VanillaEnchantmentConfig k;
	public static VanillaEnchantmentConfig lbe;
	public static VanillaEnchantmentConfig lo;
	public static VanillaEnchantmentConfig l;
	public static VanillaEnchantmentConfig p;
	public static VanillaEnchantmentConfig po;
	public static VanillaEnchantmentConfig pr;
	public static VanillaEnchantmentConfig pu;
	public static VanillaEnchantmentConfig q;
	public static VanillaEnchantmentConfig r;
	public static VanillaEnchantmentConfig ri;
	public static VanillaEnchantmentConfig ss;
	public static VanillaEnchantmentConfig s;
	public static VanillaEnchantmentConfig t;
	public static VanillaEnchantmentConfig u;
	public static VillagerConfig v;
	
	public static EnchantmentConfig a;
	public static EnchantmentConfig b;
	public static EnchantmentConfig c;
	public static EnchantmentConfig cf;
	public static EnchantmentConfig cs;
	public static EnchantmentConfig cr;
	public static EnchantmentConfig d;
	public static EnchantmentConfig f;
	public static EnchantmentConfig fl;
	public static EnchantmentConfig fr;
	public static EnchantmentConfig h;
	public static EnchantmentConfig rei;
	public static EnchantmentConfig he;
	public static EnchantmentConfig sa;
	public static EnchantmentConfig re;
	public static EnchantmentConfig te;
	public static EnchantmentConfig fs;
	
	static
	{
		
		if(!hasInit)
		{
			de = new VanillaEnchantmentConfig(10, "Damage Enchantments", 10, 5);
			e = new VanillaEnchantmentConfig(10, "Efficiency", 10, 5);
			fa = new VanillaEnchantmentConfig(10, "Fire Aspect", 10, 2);
			i = new VanillaEnchantmentConfig(10, "Impaling", 10, 5);
			k = new VanillaEnchantmentConfig(10, "Knockback", 10, 2);
			lbe = new VanillaEnchantmentConfig(10, "Loot Bonus Enchantments", 10, 3);
			lo = new VanillaEnchantmentConfig(5, "Loyalty", 5, 3);
			l = new VanillaEnchantmentConfig(5, "Lure", 5, 3);
			p = new VanillaEnchantmentConfig(10, "Piercing", 10, 4);
			po = new VanillaEnchantmentConfig(10, "Power", 10, 5);
			pr = new VanillaEnchantmentConfig(10, "Protection Enchantments", 10, 4);
			pu = new VanillaEnchantmentConfig(10, "Punch", 10, 2);
			q = new VanillaEnchantmentConfig(5, "Quick Charge", 5, 3);
			r = new VanillaEnchantmentConfig(5, "Respiration", 5, 3);
			ri = new VanillaEnchantmentConfig(5, "Riptide", 5, 3);
			ss = new VanillaEnchantmentConfig(5, "Soul Speed", 5, 3);
			s = new VanillaEnchantmentConfig(10, "Sweeping Edge", 10, 3);
			t = new VanillaEnchantmentConfig(10, "Thorns", 10, 3);
			u = new VanillaEnchantmentConfig(10, "Unbreaking", 10, 3);
			v = new VillagerConfig();
			
			a = new EnchantmentConfig(10, "Attack Reach", 1, false);
			b = new EnchantmentConfig(10, "Block Reach", 3, false);
			c = new EnchantmentConfig(1, "Cat Vision", 1, true);
			cf = new EnchantmentConfig(1, "Camouflage", 1, true);
			cr = new EnchantmentConfig(5, "Critical", 5, false);
			cs = new EnchantmentConfig(1, "Cavernous Storage", 1, false);
			d = new EnchantmentConfig(5, "Double Break", 5, false);
			f = new EnchantmentConfig(1, "Fast Hopper", 1, true);
			fl = new EnchantmentConfig(3, "Flight", 3, false);
			fr = new EnchantmentConfig(10, "Freezing", 3, true);
			h = new EnchantmentConfig(10, "Health Boost", 5, true);
			he = new EnchantmentConfig(10, "Heavy", 5, true);
			rei = new EnchantmentConfig(10, "Reinforcement", 5, true);
			re = new EnchantmentConfig(1, "Replanting", 1, true);
			sa = new EnchantmentConfig(3, "Step Assist", 3, false);
			te = new EnchantmentConfig(10, "Temper", 5, true);
			fs = new EnchantmentConfig(1, "Fast Smelt", 1, true);
			Config.hasInit = true;
		}
		config = builder.build();
		
		SyncedServerConfig = builder.build();
		
	}
	
	public static void load(CommentedFileConfig file)
	{
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

    	Config.c.isEnabled.set(file.get("Cat Vision" + ".isEnabled"));
    	
    	Config.fl.maxLevel.set(file.get("Flight" + ".maxLevel"));
    	Config.fl.isEnabled.set(file.get("Flight" + ".isEnabled"));
    	
    	Config.h.maxLevel.set(file.get("Health Boost" + ".maxLevel"));
    	Config.h.isEnabled.set(file.get("Health Boost" + ".isEnabled"));
    	
    	Config.sa.maxLevel.set(file.get("Step Assist" + ".maxLevel"));
    	Config.sa.isEnabled.set(file.get("Step Assist" + ".isEnabled"));

    	Config.f.isEnabled.set(file.get("Fast Hopper" + ".isEnabled"));

    	
    	Config.b.maxLevel.set(file.get("Block Reach" + ".maxLevel"));
    	Config.b.isEnabled.set(file.get("Block Reach" + ".isEnabled"));
    	
    	Config.d.maxLevel.set(file.get("Double Break" + ".maxLevel"));
    	Config.d.isEnabled.set(file.get("Double Break" + ".isEnabled"));

    	Config.re.isEnabled.set(file.get("Replanting" + ".isEnabled"));
    	
    	
    	Config.a.maxLevel.set(file.get("Attack Reach" + ".maxLevel"));
    	Config.a.isEnabled.set(file.get("Attack Reach" + ".isEnabled"));
    	
    	Config.cr.maxLevel.set(file.get("Critical" + ".maxLevel"));
    	Config.cr.isEnabled.set(file.get("Critical" + ".isEnabled"));
    	
    	Config.fr.maxLevel.set(file.get("Freezing" + ".maxLevel"));
    	Config.fr.isEnabled.set(file.get("Freezing" + ".isEnabled"));

    	Config.v.isEnabled.set(file.get("Enchanter Villager" + ".isEnabled"));
    	
    	Config.cs.isEnabled.set(file.get("Cavernous Storage" + ".isEnabled"));
    	
    	Config.cf.isEnabled.set(file.get("Camouflage" + ".isEnabled"));
    	
    	Config.fs.isEnabled.set(file.get(fs.name + ".isEnabled"));
	}
	
	public static void save(CommentedFileConfig file)
	{
		file.load();
		for(EnchantmentConfig c : configSections.values())
		{
			file.set(c.name + ".maxLevel", c.maxLevel.get());
			file.set(c.name + ".isEnabled", c.isEnabled.get());
		}
	}
}
