package com.yeetmanlord.somanyenchants.core.events;

import java.util.List;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedChestBlock;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedHopper;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedTrappedChestBlock;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedHiddenTrappedChestTileEntity;
import com.yeetmanlord.somanyenchants.common.tileentities.EnchantedTrappedChestTileEntity;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.BlockInit;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;
import com.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.TrappedChestBlock;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntitySelector;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.ListNBT;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.EntityPlaceEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class BlockEnchants {

	@SubscribeEvent
	public static void placeEnchantedBlocks(final EntityPlaceEvent event) {
		Entity e = event.getEntity();
		if (e instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) e;
			ItemStack mainhand = player.getHeldItemMainhand();
			ItemStack offhand = player.getHeldItemOffhand();
			RayTraceResult r = player.pick(player.getAttributeValue(ForgeMod.REACH_DISTANCE.get()), 0.5f, false);
			if (r.getType() != RayTraceResult.Type.BLOCK) {
				return;
			}
			BlockRayTraceResult b = (BlockRayTraceResult) r;

			if (event.getPlacedBlock().getBlock() == Blocks.HOPPER && Config.f.isEnabled.get() == true) {
				if (mainhand.getItem() == Items.HOPPER) {
					if (ModEnchantmentHelper.hasFastHopper(player)) {
						event.getWorld().setBlockState(event.getPos(),
								BlockInit.ENCHANTED_HOPPER.get().getDefaultState().with(EnchantedHopper.FACING,
										event.getState().get(HopperBlock.FACING)),
								1);
					}
				} else if (!(mainhand.getItem() instanceof BlockItem)) {
					if (offhand.getItem() == Items.HOPPER) {
						if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FAST_HOPPER.get(), offhand) > 0) {
							event.getWorld()
									.setBlockState(event.getPos(),
											BlockInit.ENCHANTED_HOPPER.get().getDefaultState().with(
													EnchantedHopper.FACING, event.getState().get(HopperBlock.FACING)),
											1);
						}
					}
				}
			}
			
			if (event.getPlacedBlock().getBlock() instanceof ShulkerBoxBlock && Config.cs.isEnabled.get() == true) {
				if (mainhand.getItem() instanceof BlockItem) {
					
					
					if (ModEnchantmentHelper.isCavernousStorage(mainhand.getEnchantmentTagList())) {
						BlockState state = EnchantedShulkerBoxBlock.getBlockByColor(((ShulkerBoxBlock)((BlockItem)mainhand.getItem()).getBlock()).getColor()).getDefaultState();
						
						event.getWorld().setBlockState(event.getPos(),
								state.with(EnchantedShulkerBoxBlock.FACING,
										event.getState().get(ShulkerBoxBlock.FACING)),
								1);
					}
				} else if (!(mainhand.getItem() instanceof BlockItem)) {
					if (offhand.getItem() instanceof BlockItem) {
						if (ModEnchantmentHelper.isCavernousStorage(offhand.getEnchantmentTagList())) {
							BlockState state = EnchantedShulkerBoxBlock.getBlockByColor(((ShulkerBoxBlock)((BlockItem)offhand.getItem()).getBlock()).getColor()).getDefaultState();
							
							event.getWorld()
									.setBlockState(event.getPos(),
											state.with(
													EnchantedShulkerBoxBlock.FACING, event.getState().get(ShulkerBoxBlock.FACING)),
											1);
						}
					}
				}
			}

			if (event.getPlacedBlock().getBlock() == Blocks.CHEST && Config.cs.isEnabled.get() == true) {
				if (mainhand.getItem() == Items.CHEST) {
					if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CAVERNOUS_STORAGE.get(),
							mainhand) > 0) {
						event.getWorld().setBlockState(event.getPos(), BlockInit.ENCHANTED_CHEST.get().getDefaultState()
								.with(EnchantedChestBlock.FACING, event.getState().get(ChestBlock.FACING)), 1);

						World world = (World) event.getWorld();
						BlockState state = BlockInit.ENCHANTED_CHEST.get().getDefaultState()
								.with(EnchantedChestBlock.FACING, event.getState().get(ChestBlock.FACING));
						if(state.get(EnchantedChestBlock.FACING) == Direction.WEST)
						{
							BlockState newstate = world.getBlockState(event.getPos().add(0, 0, -1));
							if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedChestBlock.TYPE, ChestType.LEFT);
							}
							newstate = world.getBlockState(event.getPos().add(0, 0, 1));
							if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedChestBlock.TYPE, ChestType.RIGHT);
							}
						}
						if(state.get(EnchantedChestBlock.FACING) == Direction.EAST)
						{
							BlockState newstate = world.getBlockState(event.getPos().add(0, 0, 1));
							if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedChestBlock.TYPE, ChestType.LEFT);
							}
							newstate = world.getBlockState(event.getPos().add(0, 0, -1));
							if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedChestBlock.TYPE, ChestType.RIGHT);
							}
						}
						if(state.get(EnchantedChestBlock.FACING) == Direction.SOUTH)
						{
							BlockState newstate = world.getBlockState(event.getPos().add(-1, 0, 0));
							if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedChestBlock.TYPE, ChestType.LEFT);
							}
							newstate = world.getBlockState(event.getPos().add(1, 0, 0));
							if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedChestBlock.TYPE, ChestType.RIGHT);
							}
						}
						if(state.get(EnchantedChestBlock.FACING) == Direction.NORTH)
						{
							BlockState newstate = world.getBlockState(event.getPos().add(1, 0, 0));
							if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedChestBlock.TYPE, ChestType.LEFT);
							}
							newstate = world.getBlockState(event.getPos().add(-1, 0, 0));
							if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedChestBlock.TYPE, ChestType.RIGHT);
							}
						}
						world.setBlockState(event.getPos(), state);

					}
				} else if (!(mainhand.getItem() instanceof BlockItem)) {
					if (offhand.getItem() == Items.CHEST) {
						if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CAVERNOUS_STORAGE.get(),
								offhand) > 0) {
							event.getWorld()
									.setBlockState(event.getPos(), BlockInit.ENCHANTED_CHEST.get().getDefaultState()
											.with(EnchantedChestBlock.FACING, event.getState().get(ChestBlock.FACING)),
											1);

							World world = (World) event.getWorld();
							BlockState state = BlockInit.ENCHANTED_CHEST.get().getDefaultState()
									.with(EnchantedChestBlock.FACING, event.getState().get(ChestBlock.FACING));
							if(state.get(EnchantedChestBlock.FACING) == Direction.WEST)
							{
								BlockState newstate = world.getBlockState(event.getPos().add(0, 0, -1));
								if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedChestBlock.TYPE, ChestType.LEFT);
								}
								newstate = world.getBlockState(event.getPos().add(0, 0, 1));
								if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedChestBlock.TYPE, ChestType.RIGHT);
								}
							}
							if(state.get(EnchantedChestBlock.FACING) == Direction.EAST)
							{
								BlockState newstate = world.getBlockState(event.getPos().add(0, 0, 1));
								if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedChestBlock.TYPE, ChestType.LEFT);
								}
								newstate = world.getBlockState(event.getPos().add(0, 0, -1));
								if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedChestBlock.TYPE, ChestType.RIGHT);
								}
							}
							if(state.get(EnchantedChestBlock.FACING) == Direction.SOUTH)
							{
								BlockState newstate = world.getBlockState(event.getPos().add(-1, 0, 0));
								if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedChestBlock.TYPE, ChestType.LEFT);
								}
								newstate = world.getBlockState(event.getPos().add(1, 0, 0));
								if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedChestBlock.TYPE, ChestType.RIGHT);
								}
							}
							if(state.get(EnchantedChestBlock.FACING) == Direction.NORTH)
							{
								BlockState newstate = world.getBlockState(event.getPos().add(1, 0, 0));
								if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedChestBlock.TYPE, ChestType.LEFT);
								}
								newstate = world.getBlockState(event.getPos().add(-1, 0, 0));
								if(newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.get(EnchantedChestBlock.FACING) == state.get(EnchantedChestBlock.FACING) && newstate.get(EnchantedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedChestBlock.TYPE, ChestType.RIGHT);
								}
							}
							world.setBlockState(event.getPos(), state);

						}
					}
				}
			}

			if (event.getPlacedBlock().getBlock() == Blocks.TRAPPED_CHEST && (Config.cs.isEnabled.get() == true || Config.cf.isEnabled.get() == true)) {
				boolean placed = false;
				boolean hidden = false;
				if (mainhand.getItem() == Items.TRAPPED_CHEST) {
					if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CAVERNOUS_STORAGE.get(), mainhand) > 0
							&& ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CAMOUFLAGE.get(),
									mainhand) <= 0 && Config.cs.isEnabled.get() == true) {
						event.getWorld().setBlockState(event.getPos(),
								BlockInit.TRAPPED_ENCHANTED_CHEST.get().getDefaultState().with(
										EnchantedTrappedChestBlock.FACING,
										event.getState().get(TrappedChestBlock.FACING)),
								1);

						TileEntity t = event.getWorld().getTileEntity(event.getPos());
						if (t != null && t instanceof EnchantedTrappedChestTileEntity) {
							EnchantedTrappedChestTileEntity tile = (EnchantedTrappedChestTileEntity) t;
							tile.addEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), (short) 1);
						}
						
						placed = true;
						hidden = false;
					}
					
					if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CAMOUFLAGE.get(), mainhand) > 0 && Config.cf.isEnabled.get() == true) {
						event.getWorld().setBlockState(event.getPos(),
								BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get().getDefaultState().with(
										EnchantedTrappedChestBlock.FACING,
										event.getState().get(TrappedChestBlock.FACING)),
								1);

						TileEntity t = event.getWorld().getTileEntity(event.getPos());
						if (t != null && t instanceof EnchantedHiddenTrappedChestTileEntity) {
							EnchantedHiddenTrappedChestTileEntity tile = (EnchantedHiddenTrappedChestTileEntity) t;
							tile.addEnchant(EnchantmentInit.CAMOUFLAGE.get(), (short) 1);
						}
						
						placed = true;
						hidden = true;
					}

					if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CAVERNOUS_STORAGE.get(), mainhand) > 0
							&& ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CAMOUFLAGE.get(),
									mainhand) > 0 && Config.cs.isEnabled.get() == true && Config.cf.isEnabled.get() == true) {

						TileEntity t = event.getWorld().getTileEntity(event.getPos());
						if (t != null && t instanceof EnchantedHiddenTrappedChestTileEntity) {
							EnchantedHiddenTrappedChestTileEntity tile = (EnchantedHiddenTrappedChestTileEntity) t;
							tile.addEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), (short) 1);
						}

						placed = true;
						hidden = true;
					}

					if (placed && !hidden && Config.cs.isEnabled.get() == true) {
						World world = (World) event.getWorld();
						BlockState state = BlockInit.TRAPPED_ENCHANTED_CHEST.get().getDefaultState()
								.with(EnchantedChestBlock.FACING, event.getState().get(ChestBlock.FACING));
						if(state.get(EnchantedChestBlock.FACING) == Direction.WEST)
						{
							BlockState newstate = world.getBlockState(event.getPos().add(0, 0, -1));
							if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
							}
							newstate = world.getBlockState(event.getPos().add(0, 0, 1));
							if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
							}
						}
						if(state.get(EnchantedTrappedChestBlock.FACING) == Direction.EAST)
						{
							BlockState newstate = world.getBlockState(event.getPos().add(0, 0, 1));
							if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
							}
							newstate = world.getBlockState(event.getPos().add(0, 0, -1));
							if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
							}
						}
						if(state.get(EnchantedTrappedChestBlock.FACING) == Direction.SOUTH)
						{
							BlockState newstate = world.getBlockState(event.getPos().add(-1, 0, 0));
							if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
							}
							newstate = world.getBlockState(event.getPos().add(1, 0, 0));
							if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
							}
						}
						if(state.get(EnchantedTrappedChestBlock.FACING) == Direction.NORTH)
						{
							BlockState newstate = world.getBlockState(event.getPos().add(1, 0, 0));
							if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
							}
							newstate = world.getBlockState(event.getPos().add(-1, 0, 0));
							if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
							}
						}
						world.setBlockState(event.getPos(), state);
					}

					if (placed && hidden && Config.cf.isEnabled.get() == true) {
						World world = (World) event.getWorld();
						BlockState state = BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get().getDefaultState()
								.with(EnchantedChestBlock.FACING, event.getState().get(ChestBlock.FACING));
						if(state.get(EnchantedChestBlock.FACING) == Direction.WEST)
						{
							BlockState newstate = world.getBlockState(event.getPos().add(0, 0, -1));
							if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								if(world.getTileEntity(event.getPos().add(0, 0, -1)) instanceof EnchantedHiddenTrappedChestTileEntity)
								{
									if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(0, 0, -1))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
									{
										state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
									}
								}
							}
							newstate = world.getBlockState(event.getPos().add(0, 0, 1));
							if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								if(world.getTileEntity(event.getPos().add(0, 0, 1)) instanceof EnchantedHiddenTrappedChestTileEntity)
								{
									if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(0, 0, 1))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
									{
										state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
									}
								}
							}
						}
						if(state.get(EnchantedTrappedChestBlock.FACING) == Direction.EAST)
						{
							BlockState newstate = world.getBlockState(event.getPos().add(0, 0, 1));
							if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								if(world.getTileEntity(event.getPos().add(0, 0, 1)) instanceof EnchantedHiddenTrappedChestTileEntity)
								{
									if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(0, 0, 1))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
									{
										state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
									}
								}
							}
							newstate = world.getBlockState(event.getPos().add(0, 0, -1));
							if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								if(world.getTileEntity(event.getPos().add(0, 0, -1)) instanceof EnchantedHiddenTrappedChestTileEntity)
								{
									if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(0, 0, -1))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
									{
										state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
									}
								}
							}
						}
						if(state.get(EnchantedTrappedChestBlock.FACING) == Direction.SOUTH)
						{
							BlockState newstate = world.getBlockState(event.getPos().add(-1, 0, 0));
							if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								if(world.getTileEntity(event.getPos().add(-1, 0, 0)) instanceof EnchantedHiddenTrappedChestTileEntity)
								{
									if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(-1, 0, 0))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
									{
										state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
									}
								}
							}
							newstate = world.getBlockState(event.getPos().add(1, 0, 0));
							if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								if(world.getTileEntity(event.getPos().add(1, 0, 0)) instanceof EnchantedHiddenTrappedChestTileEntity)
								{
									if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(1, 0, 0))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
									{
										state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
									}
								}
							}
						}
						if(state.get(EnchantedTrappedChestBlock.FACING) == Direction.NORTH)
						{
							BlockState newstate = world.getBlockState(event.getPos().add(1, 0, 0));
							if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								if(world.getTileEntity(event.getPos().add(1, 0, 0)) instanceof EnchantedHiddenTrappedChestTileEntity)
								{
									if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(1, 0, 0))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
									{
										state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
									}
								}
							}
							newstate = world.getBlockState(event.getPos().add(-1, 0, 0));
							if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
							{
								if(world.getTileEntity(event.getPos().add(-1, 0, 0)) instanceof EnchantedHiddenTrappedChestTileEntity)
								{
									if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(-1, 0, 0))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
									{
										state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
									}
								}
							}
						}
						world.setBlockState(event.getPos(), state);
					}

				} else if (!(mainhand.getItem() instanceof BlockItem)) {
					placed = false;
					hidden = false;
					if (offhand.getItem() == Items.TRAPPED_CHEST) {
						if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CAVERNOUS_STORAGE.get(),
								offhand) > 0
								&& ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CAMOUFLAGE.get(),
										offhand) <= 0 && Config.cs.isEnabled.get() == true) {
							event.getWorld().setBlockState(event.getPos(),
									BlockInit.TRAPPED_ENCHANTED_CHEST.get().getDefaultState().with(
											EnchantedTrappedChestBlock.FACING,
											event.getState().get(TrappedChestBlock.FACING)),
									1);

							TileEntity t = event.getWorld().getTileEntity(event.getPos());
							if (t != null && t instanceof EnchantedTrappedChestTileEntity) {
								EnchantedTrappedChestTileEntity tile = (EnchantedTrappedChestTileEntity) t;
								tile.addEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), (short) 1);
							}

							placed = true;
							hidden = false;
						}
						if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CAMOUFLAGE.get(), offhand) > 0 && Config.cf.isEnabled.get() == true) {
							event.getWorld().setBlockState(event.getPos(),
									BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get().getDefaultState().with(
											EnchantedTrappedChestBlock.FACING,
											event.getState().get(TrappedChestBlock.FACING)),
									1);

							TileEntity t = event.getWorld().getTileEntity(event.getPos());
							if (t != null && t instanceof EnchantedHiddenTrappedChestTileEntity) {
								EnchantedHiddenTrappedChestTileEntity tile = (EnchantedHiddenTrappedChestTileEntity) t;
								tile.addEnchant(EnchantmentInit.CAMOUFLAGE.get(), (short) 1);
							}

							placed = true;
							hidden = true;
						}

						if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CAVERNOUS_STORAGE.get(),
								offhand) > 0
								&& ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.CAMOUFLAGE.get(),
										offhand) > 0 && Config.cs.isEnabled.get() == true && Config.cf.isEnabled.get() == true) {

							TileEntity t = event.getWorld().getTileEntity(event.getPos());
							if (t != null && t instanceof EnchantedHiddenTrappedChestTileEntity) {
								EnchantedHiddenTrappedChestTileEntity tile = (EnchantedHiddenTrappedChestTileEntity) t;
								tile.addEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), (short) 1);
							}

							placed = true;
							hidden = true;
						}

						if (placed && !hidden && Config.cs.isEnabled.get() == true) {
							World world = (World) event.getWorld();
							BlockState state = BlockInit.TRAPPED_ENCHANTED_CHEST.get().getDefaultState()
									.with(EnchantedChestBlock.FACING, event.getState().get(ChestBlock.FACING));
							if(state.get(EnchantedChestBlock.FACING) == Direction.WEST)
							{
								BlockState newstate = world.getBlockState(event.getPos().add(0, 0, -1));
								if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
								}
								newstate = world.getBlockState(event.getPos().add(0, 0, 1));
								if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
								}
							}
							if(state.get(EnchantedTrappedChestBlock.FACING) == Direction.EAST)
							{
								BlockState newstate = world.getBlockState(event.getPos().add(0, 0, 1));
								if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
								}
								newstate = world.getBlockState(event.getPos().add(0, 0, -1));
								if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
								}
							}
							if(state.get(EnchantedTrappedChestBlock.FACING) == Direction.SOUTH)
							{
								BlockState newstate = world.getBlockState(event.getPos().add(-1, 0, 0));
								if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
								}
								newstate = world.getBlockState(event.getPos().add(1, 0, 0));
								if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
								}
							}
							if(state.get(EnchantedTrappedChestBlock.FACING) == Direction.NORTH)
							{
								BlockState newstate = world.getBlockState(event.getPos().add(1, 0, 0));
								if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
								}
								newstate = world.getBlockState(event.getPos().add(-1, 0, 0));
								if(newstate.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
								}
							}
							world.setBlockState(event.getPos(), state);
						}

						if (placed && hidden && Config.cf.isEnabled.get() == true) {
							World world = (World) event.getWorld();
							BlockState state = BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get().getDefaultState()
									.with(EnchantedChestBlock.FACING, event.getState().get(ChestBlock.FACING));
							if(state.get(EnchantedChestBlock.FACING) == Direction.WEST)
							{
								BlockState newstate = world.getBlockState(event.getPos().add(0, 0, -1));
								if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									if(world.getTileEntity(event.getPos().add(0, 0, -1)) instanceof EnchantedHiddenTrappedChestTileEntity)
									{
										if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(0, 0, -1))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
										{
											state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
										}
									}
								}
								newstate = world.getBlockState(event.getPos().add(0, 0, 1));
								if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									if(world.getTileEntity(event.getPos().add(0, 0, 1)) instanceof EnchantedHiddenTrappedChestTileEntity)
									{
										if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(0, 0, 1))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
										{
											state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
										}
									}
								}
							}
							if(state.get(EnchantedTrappedChestBlock.FACING) == Direction.EAST)
							{
								BlockState newstate = world.getBlockState(event.getPos().add(0, 0, 1));
								if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									if(world.getTileEntity(event.getPos().add(0, 0, 1)) instanceof EnchantedHiddenTrappedChestTileEntity)
									{
										if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(0, 0, 1))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
										{
											state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
										}
									}
								}
								newstate = world.getBlockState(event.getPos().add(0, 0, -1));
								if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									if(world.getTileEntity(event.getPos().add(0, 0, -1)) instanceof EnchantedHiddenTrappedChestTileEntity)
									{
										if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(0, 0, -1))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
										{
											state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
										}
									}
								}
							}
							if(state.get(EnchantedTrappedChestBlock.FACING) == Direction.SOUTH)
							{
								BlockState newstate = world.getBlockState(event.getPos().add(-1, 0, 0));
								if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									if(world.getTileEntity(event.getPos().add(-1, 0, 0)) instanceof EnchantedHiddenTrappedChestTileEntity)
									{
										if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(-1, 0, 0))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
										{
											state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
										}
									}
								}
								newstate = world.getBlockState(event.getPos().add(1, 0, 0));
								if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									if(world.getTileEntity(event.getPos().add(1, 0, 0)) instanceof EnchantedHiddenTrappedChestTileEntity)
									{
										if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(1, 0, 0))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
										{
											state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
										}
									}
								}
							}
							if(state.get(EnchantedTrappedChestBlock.FACING) == Direction.NORTH)
							{
								BlockState newstate = world.getBlockState(event.getPos().add(1, 0, 0));
								if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									if(world.getTileEntity(event.getPos().add(1, 0, 0)) instanceof EnchantedHiddenTrappedChestTileEntity)
									{
										if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(1, 0, 0))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
										{
											state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.LEFT);
										}
									}
								}
								newstate = world.getBlockState(event.getPos().add(-1, 0, 0));
								if(newstate.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newstate.get(EnchantedTrappedChestBlock.FACING) == state.get(EnchantedTrappedChestBlock.FACING) && newstate.get(EnchantedTrappedChestBlock.TYPE) == ChestType.SINGLE)
								{
									if(world.getTileEntity(event.getPos().add(-1, 0, 0)) instanceof EnchantedHiddenTrappedChestTileEntity)
									{
										if(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos().add(-1, 0, 0))).getEnchants().equals(((EnchantedHiddenTrappedChestTileEntity)world.getTileEntity(event.getPos())).getEnchants()))
										{
											state = state.with(EnchantedTrappedChestBlock.TYPE, ChestType.RIGHT);
										}
									}
								}
							}
							world.setBlockState(event.getPos(), state);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void breakEnchantedBlocks(final BreakEvent event) {
		PlayerEntity player = event.getPlayer();
		Block block = event.getState().getBlock();
		World world = (World) event.getWorld();
		BlockState state = event.getState();
		BlockPos pos = event.getPos();
		breakEnchantedBlock(block, BlockInit.ENCHANTED_HOPPER.get(), state, pos, Items.HOPPER, world, player,
				EnchantmentInit.FAST_HOPPER.get());
		breakEnchantedBlock(block, BlockInit.ENCHANTED_CHEST.get(), state, pos, Items.CHEST, world, player,
				EnchantmentInit.CAVERNOUS_STORAGE.get());
		breakTrappedEnchantedChest(block, state, pos, world, player);
	}

	public static void breakEnchantedBlock(Block block, Block checkBlock, BlockState state, BlockPos pos, Item drop,
			World world, PlayerEntity player, Enchantment ench) {
		if (block == checkBlock && !player.isCreative() && !player.isSpectator()
				&& block.canHarvestBlock(state, player.world, pos, player)) {
			ItemStack stack = new ItemStack(drop);
			stack.addEnchantment(ench, 1);
			ItemEntity item = new ItemEntity((World) world, pos.getX(), pos.getY(), pos.getZ(), stack);
			item.setPickupDelay(10);

			world.addEntity(item);
		}
	}

	public static void breakTrappedEnchantedChest(Block block, BlockState state, BlockPos pos, World world,
			PlayerEntity player) {
		if (block == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && !player.isCreative() && !player.isSpectator()
				&& block.canHarvestBlock(state, player.world, pos, player)) {
			ItemStack stack = new ItemStack(Items.TRAPPED_CHEST);
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof EnchantedTrappedChestTileEntity) {
				EnchantedTrappedChestTileEntity eTile = (EnchantedTrappedChestTileEntity) tile;
				ListNBT nbt = eTile.getEnchants();
				if (ModEnchantmentHelper.hasCamouflage(nbt)) {
					stack.addEnchantment(EnchantmentInit.CAMOUFLAGE.get(), 1);
				}
				if (ModEnchantmentHelper.isCavernousStorage(nbt)) {
					stack.addEnchantment(EnchantmentInit.CAVERNOUS_STORAGE.get(), 1);
				}
			}
		}
			
			if (block == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && !player.isCreative() && !player.isSpectator()
					&& block.canHarvestBlock(state, player.world, pos, player)) {
				ItemStack stack = new ItemStack(Items.TRAPPED_CHEST);
				TileEntity tile = world.getTileEntity(pos);
				if (tile instanceof EnchantedHiddenTrappedChestTileEntity) {
					EnchantedHiddenTrappedChestTileEntity eTile = (EnchantedHiddenTrappedChestTileEntity) tile;
					ListNBT nbt = eTile.getEnchants();
					if (ModEnchantmentHelper.hasCamouflage(nbt)) {
						stack.addEnchantment(EnchantmentInit.CAMOUFLAGE.get(), 1);
					}
					if (ModEnchantmentHelper.isCavernousStorage(nbt)) {
						stack.addEnchantment(EnchantmentInit.CAVERNOUS_STORAGE.get(), 1);
					}
				}
			ItemEntity item = new ItemEntity((World) world, pos.getX(), pos.getY(), pos.getZ(), stack);

			item.setPickupDelay(10);

			world.addEntity(item);
		}
	}
	
	@SubscribeEvent
	public static void onEnchant(final CommandEvent event)
	{
		CommandContext<CommandSource> command = event.getParseResults().getContext().build(null);
		if(command.getCommand() != null)
		{
			if(command.getCommand().toString().contains("net.minecraft.command.impl.EnchantCommand"))
			{
				Enchantment enchant = command.getArgument("enchantment", Enchantment.class);
				EntitySelector e = command.getArgument("targets", EntitySelector.class);
				List<? extends Entity> entities;
				try {
					entities = e.select(command.getSource());
					for(int x = 0; x < entities.size(); x++)
					{
						Entity entity = entities.get(x);
						if(entity instanceof LivingEntity)
						{
							LivingEntity living = (LivingEntity) entity;
							if(EnchantmentTypesInit.STORAGE.canEnchantItem(living.getHeldItemMainhand().getItem()) && enchant == EnchantmentInit.CAVERNOUS_STORAGE.get() && Config.cs.isEnabled.get() == true)
							{
								ItemStack stack = living.getHeldItemMainhand();
								Item item = stack.getItem();
								if(item instanceof BlockItem)
								{
									BlockItem blockItem = (BlockItem) item;
									Block block = blockItem.getBlock();
									if(block instanceof ShulkerBoxBlock)
									{
										ItemStack newStack = new ItemStack(EnchantedShulkerBoxBlock.getBlockByColor(((ShulkerBoxBlock)block).getColor()));
										newStack.addEnchantment(EnchantmentInit.CAVERNOUS_STORAGE.get(), 1);
										newStack.setTag(stack.getTag());
										if(living instanceof PlayerEntity)
										{
											PlayerEntity player = (PlayerEntity) living;
											player.replaceItemInInventory(getSlotFor(stack, player), newStack);
										}
										else
										{
											living.replaceItemInInventory(EquipmentSlotType.MAINHAND.getSlotIndex(), newStack);
										}
										CommandSource source = command.getSource();
										if (entities.size() == 1) {
											source.sendFeedback(
													new TranslationTextComponent("commands.enchant.success.single",
															enchant.getDisplayName(1),
															entities.iterator().next().getDisplayName()),
													true);
										} else {
											source.sendFeedback(
													new TranslationTextComponent("commands.enchant.success.multiple",
															enchant.getDisplayName(1), entities.size()),
													true);
										}
										event.setCanceled(true);
									}
								}
							}
						}
					}
				} catch (CommandSyntaxException e1) {
					e1.printStackTrace();
				}
				
			}
			
		}
	}
	
	
	@SubscribeEvent
	public static void onBookApply(final AnvilRepairEvent event)
	{
		PlayerEntity player = event.getPlayer();
		ItemStack initial = event.getItemInput();
		ItemStack ingredient = event.getIngredientInput();
		ItemStack stack = event.getPlayer().inventory.getItemStack();
		if(ItemStack.areItemsEqual(initial, stack))
		{
			if(ModEnchantmentHelper.isCavernousStorage(stack.getEnchantmentTagList()) && stack.getItem() instanceof BlockItem && Config.cs.isEnabled.get() == true) 
			{
				Block block = Block.getBlockFromItem(stack.getItem());
				if(block instanceof ShulkerBoxBlock)
				{
					ItemStack newStack = new ItemStack(EnchantedShulkerBoxBlock.getBlockByColor(((ShulkerBoxBlock)block).getColor()).asItem());
					newStack.addEnchantment(EnchantmentInit.CAVERNOUS_STORAGE.get(), 1);
					newStack.setTag(stack.getTag());
					player.inventory.setItemStack(newStack);
				}
			}
		}
		else
		{
			for(int x = 0; x < player.inventory.getSizeInventory(); x++)
			{
				if(ItemStack.areItemsEqual(initial, player.inventory.getStackInSlot(x)))
				{
					ItemStack stack1 = player.inventory.getStackInSlot(x);
					if(ModEnchantmentHelper.isCavernousStorage(stack1.getEnchantmentTagList()) && stack1.getItem() instanceof BlockItem && Config.cs.isEnabled.get() == true) 
					{
						Block block = ((BlockItem)stack1.getItem()).getBlock();
						if(block instanceof ShulkerBoxBlock)
						{
							ItemStack newStack = new ItemStack(EnchantedShulkerBoxBlock.getBlockByColor(((ShulkerBoxBlock)block).getColor()).asItem());
							newStack.addEnchantment(EnchantmentInit.CAVERNOUS_STORAGE.get(), 1);
							newStack.setTag(stack1.getTag());
							player.replaceItemInInventory(x, newStack);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onPickup(final ItemPickupEvent event)
	{
		PlayerEntity player = event.getPlayer();
		ItemStack stack = event.getStack();
		int x = getSlotFor(stack, player);
		if(x >= 0)
		{
			if(ModEnchantmentHelper.isCavernousStorage(stack.getEnchantmentTagList()) && stack.getItem() instanceof BlockItem && Config.cs.isEnabled.get() == true)
			{
				Block block = Block.getBlockFromItem(stack.getItem());
				if(block instanceof ShulkerBoxBlock)
				{
					ItemStack newStack = new ItemStack(EnchantedShulkerBoxBlock.getBlockByColor(((ShulkerBoxBlock)block).getColor()).asItem());
					newStack.addEnchantment(EnchantmentInit.CAVERNOUS_STORAGE.get(), 1);
					newStack.setTag(stack.getTag());
					player.replaceItemInInventory(x, newStack);
				}	
			}
		}
	}
	
	
	public static int getSlotFor(ItemStack stack, PlayerEntity player) {
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
			if (!player.inventory.getStackInSlot(i).isEmpty() && stackEqualExact(stack, player.inventory.getStackInSlot(i))) {
				return i;
			}
		}

		return -1;
	}
	
	private static boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
		return stack1.getItem() == stack2.getItem() && ItemStack.areItemStackTagsEqual(stack1, stack2);
	}

}