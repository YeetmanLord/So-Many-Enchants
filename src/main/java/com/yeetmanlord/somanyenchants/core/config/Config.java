package com.yeetmanlord.somanyenchants.core.config;

import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.AttackReach;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.BlockReach;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.CatVision;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.Critical;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.DoubleBreak;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.FastHopper;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.Flight;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.Freezing;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.HealthBoost;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.Heavy;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.Reinforcment;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.Replanting;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.StepAssist;
import com.yeetmanlord.somanyenchants.core.config.so_many_enchants.Temper;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.DamageEnchantments;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Efficiency;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.FireAspect;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Impaling;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Knockback;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.LootBonusEnchantments;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Loyalty;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Lure;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Piercing;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Power;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.ProtectionEnchantments;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Punch;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.QuickCharge;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Respiration;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Riptide;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.SoulSpeed;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Sweeping;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Thorns;
import com.yeetmanlord.somanyenchants.core.config.vanilla_enchantments.Unbreaking;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config
{
	
	public static boolean hasInit = false;
	
	public static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
	
	public static final ForgeConfigSpec config;
	
	public static final ForgeConfigSpec SyncedServerConfig;
	
	
	public static DamageEnchantments de;
	public static Efficiency e;
	public static FireAspect fa;
	public static Impaling i;
	public static Knockback k;
	public static LootBonusEnchantments lbe;
	public static Loyalty lo;
	public static Lure l;
	public static Piercing p;
	public static Power po;
	public static ProtectionEnchantments pr;
	public static Punch pu;
	public static QuickCharge q;
	public static Respiration r;
	public static Riptide ri;
	public static SoulSpeed ss;
	public static Sweeping s;
	public static Thorns t;
	public static Unbreaking u;
	public static VillagerConfig v;
	
	public static AttackReach a;
	public static BlockReach b;
	public static CatVision c;
	public static Critical cr;
	public static DoubleBreak d;
	public static FastHopper f;
	public static Flight fl;
	public static Freezing fr;
	public static HealthBoost h;
	public static Reinforcment rei;
	public static Heavy he;
	public static StepAssist sa;
	public static Replanting re;
	public static Temper te;
	
	static
	{
		
		if(!hasInit)
		{
			de = new DamageEnchantments(10);
			e = new Efficiency(10);
			fa = new FireAspect(10);
			i = new Impaling(10);
			k = new Knockback(10);
			lbe = new LootBonusEnchantments(10);
			lo = new Loyalty(5);
			l = new Lure(5);
			p = new Piercing(10);
			po = new Power(10);
			pr = new ProtectionEnchantments(10);
			pu = new Punch(10);
			q = new QuickCharge(5);
			r = new Respiration(5);
			ri = new Riptide(5);
			ss = new SoulSpeed(5);
			s = new Sweeping(10);
			t = new Thorns(10);
			u = new Unbreaking(10);
			v = new VillagerConfig();
			
			a = new AttackReach(3);
			b = new BlockReach(5);
			c = new CatVision(1);
			cr = new Critical(5);
			d = new DoubleBreak(5);
			f = new FastHopper(1);
			fl = new Flight(3);
			fr = new Freezing(10);
			h = new HealthBoost(10);
			he = new Heavy(10);
			rei = new Reinforcment(10);
			re = new Replanting(1);
			sa = new StepAssist(10);
			te = new Temper(10);
			Config.hasInit = true;
		}

		
		config = builder.build();
		
		SyncedServerConfig = builder.build();
		
	}
	
}
