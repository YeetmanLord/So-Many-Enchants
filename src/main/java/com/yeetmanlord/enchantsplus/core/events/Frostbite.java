package com.yeetmanlord.enchantsplus.core.events;

import com.yeetmanlord.enchantsplus.Main;
import com.yeetmanlord.enchantsplus.core.util.ModEnchantmentHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class Frostbite {

	@SubscribeEvent
	public static void frostBite(final LivingAttackEvent event)
	{
		LivingEntity attacked = event.getEntityLiving();
		DamageSource source = event.getSource();
		if(source instanceof EntityDamageSource)
		{
			Entity attackerEntity = source.getTrueSource();
			if(attackerEntity instanceof LivingEntity)
			{
				LivingEntity livingAttacker = (LivingEntity)attackerEntity;
				int frostBite = ModEnchantmentHelper.getFreezingEnchant(livingAttacker);
				if(frostBite > 1)
				{
					if(attacked instanceof PlayerEntity)
		      	   	{
		      		   
		      	   	} else
		      	   	{
		      	   	attacked.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 15 * frostBite, 255, false, false, false));
		      	   	}
					attacked.addPotionEffect(new EffectInstance(Effects.UNLUCK, 15 * frostBite, 17, false, false, false));
		        	attacked.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 15 * frostBite, 1, false, false, false));
		       	  	attacked.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 15 * frostBite, 250, false, false, false));
				}
			}
		}
	}

}
