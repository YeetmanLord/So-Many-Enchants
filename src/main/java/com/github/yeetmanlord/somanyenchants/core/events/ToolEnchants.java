package com.github.yeetmanlord.somanyenchants.core.events;

import java.util.Random;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.level.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class ToolEnchants {
	@SubscribeEvent
	public static void replant(final BreakEvent event) {
		if (event.getState().getBlock() != Blocks.AIR && Config.replanting.isEnabled.get() == true) {
			Block block = event.getState().getBlock();
			Player player = event.getPlayer();
			if (block instanceof CropBlock) {
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
													event.getState().setValue(CocoaBlock.AGE, 0));
											event.setCanceled(true);
											return;
										}
										Block.dropResources(event.getState(), player.level, event.getPos());
										player.level.setBlockAndUpdate(event.getPos(),
												event.getState().setValue(CocoaBlock.AGE, 0));
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
		Player player = event.getPlayer();
		boolean creative = player.isCreative();
		BlockPos pos = event.getPos();
		BlockPos newPos;
		BlockState newState;
		HitResult raytrace = player.pick(player.getAttributeValue(ForgeMod.REACH_DISTANCE.get()), 0, false);
		int enchant = ModEnchantmentHelper.getDoubleBreakLevel(player);
		if (enchant > 0 && Config.doubleBreak.isEnabled.get() == true) {
			int chance = (int) (rand.nextFloat() * 100);
			if (chance <= enchant * 20) {
				// Handles raytracing
				if (raytrace.getType() == HitResult.Type.BLOCK) {
					BlockHitResult blockTrace = (BlockHitResult) raytrace;
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
