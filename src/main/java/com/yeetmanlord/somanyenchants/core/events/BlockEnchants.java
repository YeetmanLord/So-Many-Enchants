package com.yeetmanlord.somanyenchants.core.events;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedHopper;
import com.yeetmanlord.somanyenchants.core.init.BlockInit;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HopperBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.EntityPlaceEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class BlockEnchants {
	
	@SubscribeEvent
	public static void fastHopper(final EntityPlaceEvent event)
	{
		Entity e = event.getEntity();
		if(e instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)e;
			ItemStack mainhand = player.getHeldItemMainhand();
			ItemStack offhand = player.getHeldItemOffhand();
			
			if(event.getPlacedBlock().getBlock() == Blocks.HOPPER) 
			{
				if(mainhand.getItem() == Items.HOPPER)
				{
					if(ModEnchantmentHelper.hasFastHopper(player))
					{
						Main.LOGGER.info("Mainhand");
						event.getWorld().setBlockState(event.getPos(), BlockInit.ENCHANTED_HOPPER.get().getDefaultState().with(EnchantedHopper.FACING, event.getState().get(HopperBlock.FACING)), 0);
					}
				} else if(!(mainhand.getItem() instanceof BlockItem))
				{
					if(offhand.getItem() == Items.HOPPER)
					{
						if(ModEnchantmentHelper.hasFastHopper(player))
						{
							event.getWorld().setBlockState(event.getPos(), BlockInit.ENCHANTED_HOPPER.get().getDefaultState().with(EnchantedHopper.FACING, event.getState().get(HopperBlock.FACING)), 0);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void breakEnchantedHopper(final BreakEvent event)
	{
		PlayerEntity player = event.getPlayer();
		Block block = event.getState().getBlock();
		if(block instanceof EnchantedHopper && !player.isCreative() && !player.isSpectator() && block.canHarvestBlock(event.getState(), player.world, event.getPos(), player))
		{
			ItemStack stack =  new ItemStack(Items.HOPPER);
			stack.addEnchantment(EnchantmentInit.FAST_HOPPER.get(), 1);
			ItemEntity item = new ItemEntity((World) event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), stack);
			item.setPickupDelay(10);
			
			event.getWorld().addEntity(item);
		}
	}

}