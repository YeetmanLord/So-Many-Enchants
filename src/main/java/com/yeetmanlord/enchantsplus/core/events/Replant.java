package com.yeetmanlord.enchantsplus.core.events;

import com.yeetmanlord.enchantsplus.Main;
import com.yeetmanlord.enchantsplus.core.util.ModEnchantmentHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class Replant
{
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public static void replant(final BreakEvent event)
	{
		if(event.getState().getBlock() != Blocks.AIR)
		{
			Block block = event.getState().getBlock();
			PlayerEntity player = event.getPlayer();
			if(block instanceof CropsBlock)
			{
				ItemStack cropSeed = new ItemStack(block.asItem());
				ItemStack stack = player.getHeldItemMainhand();
				if(stack != ItemStack.EMPTY)
				{
					if(stack.getItem() instanceof Item && stack.isEnchanted())
					{
						boolean hasEnchant = ModEnchantmentHelper.getReplantEnchant(player) > 0;
						if(hasEnchant)
						{
							if(player.inventory.hasItemStack(cropSeed))
							{
								for(int x = 0; x < player.inventory.getSizeInventory(); x++)
								{
									cropSeed.setCount(player.inventory.getStackInSlot(x).getCount()); 
									if(ItemStack.areItemsEqual(player.inventory.getStackInSlot(x), cropSeed) || player.isCreative())
									{
										player.world.setBlockState(event.getPos(), Blocks.AIR.getDefaultState(), 16);
										if(player.isCreative())
										{
											player.world.setBlockState(event.getPos(), block.getDefaultState(), 1, 2);
											event.setCanceled(true);
											return;
										}
										block.spawnDrops(event.getState(), player.world, event.getPos());
										player.world.setBlockState(event.getPos(), block.getDefaultState(), 1, 2);
										player.inventory.decrStackSize(x, 1);
										event.setCanceled(true);
										return;
									}
								}
							}
						}
					}
				}
			} else if(block instanceof CocoaBlock)
			{
				ItemStack cropSeed = new ItemStack(block.asItem());
				ItemStack stack = player.getHeldItemMainhand();
				if(stack != ItemStack.EMPTY)
				{
					if(stack.getItem() instanceof Item && stack.isEnchanted())
					{
						boolean hasEnchant = ModEnchantmentHelper.getReplantEnchant(player) > 0;
						if(hasEnchant)
						{
							if(player.inventory.hasItemStack(cropSeed))
							{
								for(int x = 0; x < player.inventory.getSizeInventory(); x++)
								{
									cropSeed.setCount(player.inventory.getStackInSlot(x).getCount()); 
									if(ItemStack.areItemsEqual(player.inventory.getStackInSlot(x), cropSeed) || player.isCreative())
									{
										player.world.setBlockState(event.getPos(), Blocks.AIR.getDefaultState(), 16);
										if(player.isCreative())
										{
											player.world.setBlockState(event.getPos(), event.getState().with(((CocoaBlock) block).AGE, 0), 1, 2);
											event.setCanceled(true);
											return;
										}
										block.spawnDrops(event.getState(), player.world, event.getPos());
										player.world.setBlockState(event.getPos(), event.getState().with(((CocoaBlock) block).AGE, 0), 1, 2);
										player.inventory.decrStackSize(x, 1);
										event.setCanceled(true);
										return;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
