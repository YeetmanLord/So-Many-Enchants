package com.github.yeetmanlord.somanyenchants.core.events;

import java.util.Random;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class ToolEnchants {
	@SubscribeEvent
	public static void replant(final BreakEvent event) {
		if (event.getState().getBlock() != Blocks.AIR && Config.replanting.isEnabled.get() == true) {
			Block block = event.getState().getBlock();
			PlayerEntity player = event.getPlayer();
			if (block instanceof CropsBlock) {
				ItemStack cropSeed = new ItemStack(block.asItem());
				ItemStack stack = player.getMainHandItem();
				if (stack != ItemStack.EMPTY) {
					if (stack.getItem() instanceof Item && stack.isEnchanted()) {
						boolean hasEnchant = ModEnchantmentHelper.getReplantEnchant(player) > 0;
						if (hasEnchant) {
							if (player.inventory.contains(cropSeed)) {
								for (int x = 0; x < player.inventory.getContainerSize(); x++) {
									cropSeed.setCount(player.inventory.getItem(x).getCount());
									if (ItemStack.isSame(player.inventory.getItem(x), cropSeed)
											|| player.isCreative()) {
										player.level.setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), 16);
										if (player.isCreative()) {
											player.level.setBlockAndUpdate(event.getPos(), block.defaultBlockState());
											event.setCanceled(true);
											return;
										}
										Block.dropResources(event.getState(), player.level, event.getPos());
										player.level.setBlockAndUpdate(event.getPos(), block.defaultBlockState());
										player.inventory.removeItem(x, 1);
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
				ItemStack stack = player.getMainHandItem();
				if (stack != ItemStack.EMPTY) {
					if (stack.getItem() instanceof Item && stack.isEnchanted()) {
						boolean hasEnchant = ModEnchantmentHelper.getReplantEnchant(player) > 0;
						if (hasEnchant) {
							if (player.inventory.contains(cropSeed)) {
								for (int x = 0; x < player.inventory.getContainerSize(); x++) {
									cropSeed.setCount(player.inventory.getItem(x).getCount());
									if (ItemStack.isSame(player.inventory.getItem(x), cropSeed)
											|| player.isCreative()) {
										player.level.setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), 16);
										if (player.isCreative()) {
											player.level.setBlockAndUpdate(event.getPos(),
													event.getState().setValue(((CocoaBlock) block).AGE, 0));
											event.setCanceled(true);
											return;
										}
										block.dropResources(event.getState(), player.level, event.getPos());
										player.level.setBlockAndUpdate(event.getPos(),
												event.getState().setValue(((CocoaBlock) block).AGE, 0));
										player.inventory.removeItem(x, 1);
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
					Direction dir = blockTrace.getDirection();
					if (dir == Direction.DOWN) {
						newPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
						newState = player.level.getBlockState(newPos);
						if ((newState.getBlock().canHarvestBlock(newState, player.level, newPos, player)
								|| newState.requiresCorrectToolForDrops() == false)
								&& newState.getDestroySpeed(player.level, newPos) >= 0.0F) {
							if (!creative) {
								Block.dropResources(newState, player.level, newPos);
							}
							player.level.setBlockAndUpdate(newPos, Blocks.AIR.defaultBlockState());
						}
					} else if (dir == Direction.UP) {
						newPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
						newState = player.level.getBlockState(newPos);
						if ((newState.getBlock().canHarvestBlock(newState, player.level, newPos, player)
								|| newState.requiresCorrectToolForDrops() == false)
								&& newState.getDestroySpeed(player.level, newPos) >= 0.0F) {
							if (!creative) {
								Block.dropResources(newState, player.level, newPos);
							}
							player.level.setBlockAndUpdate(newPos, Blocks.AIR.defaultBlockState());
						}
					} else if (dir == Direction.SOUTH) {
						newPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
						newState = player.level.getBlockState(newPos);
						if ((newState.getBlock().canHarvestBlock(newState, player.level, newPos, player)
								|| newState.requiresCorrectToolForDrops() == false)
								&& newState.getDestroySpeed(player.level, newPos) >= 0.0F) {
							if (!creative) {
								Block.dropResources(newState, player.level, newPos);
							}
							player.level.setBlockAndUpdate(newPos, Blocks.AIR.defaultBlockState());
						}
					} else if (dir == Direction.NORTH) {
						newPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
						newState = player.level.getBlockState(newPos);
						if ((newState.getBlock().canHarvestBlock(newState, player.level, newPos, player)
								|| newState.requiresCorrectToolForDrops() == false)
								&& newState.getDestroySpeed(player.level, newPos) >= 0.0F) {
							if (!creative) {
								Block.dropResources(newState, player.level, newPos);
							}
							player.level.setBlockAndUpdate(newPos, Blocks.AIR.defaultBlockState());
						}
					} else if (dir == Direction.EAST) {
						newPos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
						newState = player.level.getBlockState(newPos);
						if ((newState.getBlock().canHarvestBlock(newState, player.level, newPos, player)
								|| newState.requiresCorrectToolForDrops() == false)
								&& newState.getDestroySpeed(player.level, newPos) >= 0.0F) {
							if (!creative) {
								Block.dropResources(newState, player.level, newPos);
							}
							player.level.setBlockAndUpdate(newPos, Blocks.AIR.defaultBlockState());
						}
					} else if (dir == Direction.WEST) {
						newPos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
						newState = player.level.getBlockState(newPos);
						if ((newState.getBlock().canHarvestBlock(newState, player.level, newPos, player)
								|| newState.requiresCorrectToolForDrops() == false)
								&& newState.getDestroySpeed(player.level, newPos) >= 0.0F) {
							if (!creative) {
								Block.dropResources(newState, player.level, newPos);
							}
							player.level.setBlockAndUpdate(newPos, Blocks.AIR.defaultBlockState());
						}
					}
				}
			}
		}
	}
}
