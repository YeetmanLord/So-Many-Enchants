package com.yeetmanlord.somanyenchants.core.events;

import java.util.Random;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class ToolEnchants {
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public static void replant(final BreakEvent event) {
		if (event.getState().getBlock() != Blocks.AIR && Config.replanting.isEnabled.get() == true) {
			Block block = event.getState().getBlock();
			PlayerEntity player = event.getPlayer();
			if (block instanceof CropsBlock) {
				ItemStack cropSeed = new ItemStack(block.asItem());
				ItemStack stack = player.getHeldItemMainhand();
				if (stack != ItemStack.EMPTY) {
					if (stack.getItem() instanceof Item && stack.isEnchanted()) {
						boolean hasEnchant = ModEnchantmentHelper.getReplantEnchant(player) > 0;
						if (hasEnchant) {
							if (player.inventory.hasItemStack(cropSeed)) {
								for (int x = 0; x < player.inventory.getSizeInventory(); x++) {
									cropSeed.setCount(player.inventory.getStackInSlot(x).getCount());
									if (ItemStack.areItemsEqual(player.inventory.getStackInSlot(x), cropSeed)
											|| player.isCreative()) {
										player.world.setBlockState(event.getPos(), Blocks.AIR.getDefaultState(), 16);
										if (player.isCreative()) {
											player.world.setBlockState(event.getPos(), block.getDefaultState());
											event.setCanceled(true);
											return;
										}
										block.spawnDrops(event.getState(), player.world, event.getPos());
										player.world.setBlockState(event.getPos(), block.getDefaultState());
										player.inventory.decrStackSize(x, 1);
										event.setCanceled(true);
										return;
									}
								}
							}
						}
					}
				}
			} else if (block instanceof CocoaBlock) {
				ItemStack cropSeed = new ItemStack(block.asItem());
				ItemStack stack = player.getHeldItemMainhand();
				if (stack != ItemStack.EMPTY) {
					if (stack.getItem() instanceof Item && stack.isEnchanted()) {
						boolean hasEnchant = ModEnchantmentHelper.getReplantEnchant(player) > 0;
						if (hasEnchant) {
							if (player.inventory.hasItemStack(cropSeed)) {
								for (int x = 0; x < player.inventory.getSizeInventory(); x++) {
									cropSeed.setCount(player.inventory.getStackInSlot(x).getCount());
									if (ItemStack.areItemsEqual(player.inventory.getStackInSlot(x), cropSeed)
											|| player.isCreative()) {
										player.world.setBlockState(event.getPos(), Blocks.AIR.getDefaultState(), 16);
										if (player.isCreative()) {
											player.world.setBlockState(event.getPos(),
													event.getState().with(((CocoaBlock) block).AGE, 0));
											event.setCanceled(true);
											return;
										}
										block.spawnDrops(event.getState(), player.world, event.getPos());
										player.world.setBlockState(event.getPos(),
												event.getState().with(((CocoaBlock) block).AGE, 0));
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

	@SubscribeEvent
	public static void doubleBreak(final BreakEvent event) {
		Random rand = new Random();
		PlayerEntity player = event.getPlayer();
		boolean creative = player.isCreative();
		BlockPos pos = event.getPos();
		BlockPos newPos;
		BlockState newState;
		RayTraceResult raytrace = player.pick(player.getAttributeValue(ForgeMod.REACH_DISTANCE.get()), 0, false);
		int enchant = ModEnchantmentHelper.getDoubleBreakLevel(player);
		if (enchant > 0 && Config.doubleBreak.isEnabled.get() == true) {
			int chance = (int) (rand.nextFloat() * 100);
			if (chance <= enchant * 20) {
				// Handles raytracing
				if (raytrace.getType() == RayTraceResult.Type.BLOCK) {
					BlockRayTraceResult blockTrace = (BlockRayTraceResult) raytrace;
					Direction dir = blockTrace.getFace();
					if (dir == Direction.DOWN) {
						newPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
						newState = player.world.getBlockState(newPos);
						if ((newState.getBlock().canHarvestBlock(newState, player.world, newPos, player)
								|| newState.getRequiresTool() == false)
								&& newState.getBlockHardness(
										player.world.getBlockReader((int) player.chunkCoordX, (int) player.chunkCoordZ),
										newPos) >= 0.0F) {
							if (!creative) {
								Block.spawnDrops(newState, player.world, newPos);
							}
							player.world.setBlockState(newPos, Blocks.AIR.getDefaultState());
						}
					} else if (dir == Direction.UP) {
						newPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
						newState = player.world.getBlockState(newPos);
						if ((newState.getBlock().canHarvestBlock(newState, player.world, newPos, player)
								|| newState.getRequiresTool() == false)
								&& newState.getBlockHardness(
										player.world.getBlockReader((int) player.chunkCoordX, (int) player.chunkCoordZ),
										newPos) >= 0.0F) {
							if (!creative) {
								Block.spawnDrops(newState, player.world, newPos);
							}
							player.world.setBlockState(newPos, Blocks.AIR.getDefaultState());
						}
					} else if (dir == Direction.SOUTH) {
						newPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
						newState = player.world.getBlockState(newPos);
						if ((newState.getBlock().canHarvestBlock(newState, player.world, newPos, player)
								|| newState.getRequiresTool() == false)
								&& newState.getBlockHardness(
										player.world.getBlockReader((int) player.chunkCoordX, (int) player.chunkCoordZ),
										newPos) >= 0.0F) {
							if (!creative) {
								Block.spawnDrops(newState, player.world, newPos);
							}
							player.world.setBlockState(newPos, Blocks.AIR.getDefaultState());
						}
					} else if (dir == Direction.NORTH) {
						newPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
						newState = player.world.getBlockState(newPos);
						if ((newState.getBlock().canHarvestBlock(newState, player.world, newPos, player)
								|| newState.getRequiresTool() == false)
								&& newState.getBlockHardness(
										player.world.getBlockReader((int) player.chunkCoordX, (int) player.chunkCoordZ),
										newPos) >= 0.0F) {
							if (!creative) {
								Block.spawnDrops(newState, player.world, newPos);
							}
							player.world.setBlockState(newPos, Blocks.AIR.getDefaultState());
						}
					} else if (dir == Direction.EAST) {
						newPos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
						newState = player.world.getBlockState(newPos);
						if ((newState.getBlock().canHarvestBlock(newState, player.world, newPos, player)
								|| newState.getRequiresTool() == false)
								&& newState.getBlockHardness(
										player.world.getBlockReader((int) player.chunkCoordX, (int) player.chunkCoordZ),
										newPos) >= 0.0F) {
							if (!creative) {
								Block.spawnDrops(newState, player.world, newPos);
							}
							player.world.setBlockState(newPos, Blocks.AIR.getDefaultState());
						}
					} else if (dir == Direction.WEST) {
						newPos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
						newState = player.world.getBlockState(newPos);
						if ((newState.getBlock().canHarvestBlock(newState, player.world, newPos, player)
								|| newState.getRequiresTool() == false)
								&& newState.getBlockHardness(
										player.world.getBlockReader((int) player.chunkCoordX, (int) player.chunkCoordZ),
										newPos) >= 0.0F) {
							if (!creative) {
								Block.spawnDrops(newState, player.world, newPos);
							}
							player.world.setBlockState(newPos, Blocks.AIR.getDefaultState());
						}
					}
				}
			}
		}
	}
}
