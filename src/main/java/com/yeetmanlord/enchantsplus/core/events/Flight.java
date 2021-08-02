package com.yeetmanlord.enchantsplus.core.events;

import com.yeetmanlord.enchantsplus.Main;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class Flight 
{
	private static int exhaustion;
	@SubscribeEvent
	public static void flyingEnchant(final PlayerTickEvent event)
	{
		PlayerEntity player = event.player;
		ItemStack boots = player.inventory.armorItemInSlot(0);
		if(!boots.isEmpty())
		{
			ListNBT enchantments = boots.getEnchantmentTagList();
			for(int x = 0; x < enchantments.size(); x ++)
			{
				CompoundNBT tag = enchantments.getCompound(x);
				String id = tag.getString("id");
				short lvl = tag.getShort("lvl");
				
				if(id.matches("enchants_plus:flight") && !player.abilities.isCreativeMode && !player.isSpectator())
				{
					if(lvl <= 1 && player.getFoodStats().getFoodLevel() > 4) 
					{
						player.abilities.allowFlying = true;
						
						if(player.abilities.isFlying)
						{
							exhaustion = exhaustion + 1;
							if(exhaustion >= 500)
							{
								exhaustion = 0;
								player.addExhaustion(1.0f);
								if(!player.isCreative())
								{
									boots.damageItem(1, player, (p_213833_1_) -> {
							               p_213833_1_.sendBreakAnimation(player.getActiveHand());
							               net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, player.getActiveItemStack(), player.getActiveHand());
							          });
								}
							}
						}
					} else if(lvl == 2 && player.getFoodStats().getFoodLevel() > 4)
					{
						player.abilities.allowFlying = true;
						if(player.abilities.isFlying)
						{
							exhaustion = exhaustion + 1;
							if(exhaustion >= 750)
							{
								exhaustion = 0;
								player.addExhaustion(1.0f);
								if(!player.isCreative())
								{
									boots.damageItem(1, player, (p_213833_1_) -> {
							               p_213833_1_.sendBreakAnimation(player.getActiveHand());
							               net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, player.getActiveItemStack(), player.getActiveHand());
							          });
								}
							}
						}
					} else if(lvl == 3 && player.getFoodStats().getFoodLevel() > 4)
					{
						player.abilities.allowFlying = true;
						if(player.abilities.isFlying)
						{
							exhaustion = exhaustion + 1;
							if(exhaustion >= 1000)
							{
								exhaustion = 0;
								player.addExhaustion(1.0f);
								if(!player.isCreative())
								{
									boots.damageItem(1, player, (p_213833_1_) -> {
							               p_213833_1_.sendBreakAnimation(player.getActiveHand());
							               net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, player.getActiveItemStack(), player.getActiveHand());
							          });
								}
							}
						}
					} else if(lvl > 3 && player.getFoodStats().getFoodLevel() > 4)
					{
						player.abilities.allowFlying = true;
						if(player.abilities.isFlying)
						{
							exhaustion = exhaustion + 1;
							if(exhaustion >= 1000 + lvl * 100)
							{
								exhaustion = 0;
								player.addExhaustion(1.0f);
								if(!player.isCreative())
								{
									boots.damageItem(1, player, (p_213833_1_) -> {
							               p_213833_1_.sendBreakAnimation(player.getActiveHand());
							               net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, player.getActiveItemStack(), player.getActiveHand());
							          });
								}
							}
						}
					} else
					{
						player.abilities.allowFlying = false;
						player.abilities.isFlying = false;
					}
				}  else if(id.matches("enchants_plus:flight") && (player.abilities.isCreativeMode || player.isSpectator()))
				{
					player.abilities.allowFlying = true;
				}
			}
		} else if(!player.abilities.isCreativeMode && !player.isSpectator())
		{
			player.abilities.allowFlying = false;
			player.abilities.isFlying = false;
		}
	}
}
