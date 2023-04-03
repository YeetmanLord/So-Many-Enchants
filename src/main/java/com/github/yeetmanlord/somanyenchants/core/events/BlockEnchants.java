package com.github.yeetmanlord.somanyenchants.core.events;

import java.util.List;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedBarrelBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedChestBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedHopper;
import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedTrappedChestBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.AbstractEnchantedSmelterBlock;
import com.github.yeetmanlord.somanyenchants.common.tileentities.AbstractEnchantedSmelterTileEntity;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedHiddenTrappedChestTileEntity;
import com.github.yeetmanlord.somanyenchants.common.tileentities.EnchantedTrappedChestTileEntity;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.BlockInit;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.selector.EntitySelector;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.TrappedChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.event.level.BlockEvent.BreakEvent;
import net.minecraftforge.event.level.BlockEvent.EntityPlaceEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class BlockEnchants {
	
	@SubscribeEvent
	public static void placeEnchantedBlocks(final EntityPlaceEvent event) {

		Entity e = event.getEntity();

		if (e instanceof Player) {
			Player player = (Player) e;
			ItemStack mainhand = player.getMainHandItem();
			ItemStack offhand = player.getOffhandItem();
			HitResult r = player.pick(player.getAttributeValue(ForgeMod.REACH_DISTANCE.get()), 0.5f, false);

			if (r.getType() != HitResult.Type.BLOCK) {
				return;
			}

			if ((event.getPlacedBlock().getBlock() instanceof AbstractEnchantedSmelterBlock || event.getPlacedBlock().getBlock() instanceof AbstractFurnaceBlock) && (Config.fastSmelt.isEnabled.get() || Config.fuelEfficient.isEnabled.get() || Config.extraExperience.isEnabled.get())) {

				if (event.getPlacedBlock().getBlock() instanceof AbstractEnchantedSmelterBlock) {
					if (Block.byItem(mainhand.getItem()) instanceof AbstractEnchantedSmelterBlock) {

						if (mainhand.isEnchanted()) {
							AbstractEnchantedSmelterTileEntity tile = (AbstractEnchantedSmelterTileEntity) event.getLevel().getBlockEntity(event.getPos());

							if (Config.fastSmelt.isEnabled.get() && ModEnchantmentHelper.hasEnchant(EnchantmentInit.FAST_SMELT.get(), mainhand)) {
								tile.addEnchantment(EnchantmentInit.FAST_SMELT.get(), (short) ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FAST_SMELT.get(), mainhand));
							}

							if (Config.fuelEfficient.isEnabled.get() && ModEnchantmentHelper.hasEnchant(EnchantmentInit.FUEL_EFFICIENT.get(), mainhand)) {
								tile.addEnchantment(EnchantmentInit.FUEL_EFFICIENT.get(), (short) ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FUEL_EFFICIENT.get(), mainhand));
							}

							if (Config.extraExperience.isEnabled.get() && ModEnchantmentHelper.hasEnchant(EnchantmentInit.EXTRA_EXPERIENCE.get(), mainhand)) {
								tile.addEnchantment(EnchantmentInit.EXTRA_EXPERIENCE.get(), (short) ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.EXTRA_EXPERIENCE.get(), mainhand));
							}

						} else {
							event.getLevel().setBlock(event.getPos(), ((AbstractEnchantedSmelterBlock) Block.byItem(mainhand.getItem())).getUnenchantedBlock().defaultBlockState().setValue(AbstractFurnaceBlock.FACING, event.getState().getValue(AbstractEnchantedSmelterBlock.FACING)), 1);
						}

					} else if (!(mainhand.getItem() instanceof BlockItem)) {

						if (Block.byItem(offhand.getItem()) instanceof AbstractEnchantedSmelterBlock) {

							if (offhand.isEnchanted()) {
								AbstractEnchantedSmelterTileEntity tile = (AbstractEnchantedSmelterTileEntity) event.getLevel().getBlockEntity(event.getPos());

								if (Config.fastSmelt.isEnabled.get() && ModEnchantmentHelper.hasEnchant(EnchantmentInit.FAST_SMELT.get(), offhand)) {
									tile.addEnchantment(EnchantmentInit.FAST_SMELT.get(), (short) ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FAST_SMELT.get(), offhand));
								}

								if (Config.fuelEfficient.isEnabled.get() && ModEnchantmentHelper.hasEnchant(EnchantmentInit.FUEL_EFFICIENT.get(), offhand)) {
									tile.addEnchantment(EnchantmentInit.FUEL_EFFICIENT.get(), (short) ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FUEL_EFFICIENT.get(), offhand));
								}

								if (Config.extraExperience.isEnabled.get() && ModEnchantmentHelper.hasEnchant(EnchantmentInit.EXTRA_EXPERIENCE.get(), offhand)) {
									tile.addEnchantment(EnchantmentInit.EXTRA_EXPERIENCE.get(), (short) ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.EXTRA_EXPERIENCE.get(), offhand));
								}

							} else {
								event.getLevel().setBlock(event.getPos(), ((AbstractEnchantedSmelterBlock) Block.byItem(mainhand.getItem())).getUnenchantedBlock().defaultBlockState().setValue(AbstractFurnaceBlock.FACING, event.getState().getValue(AbstractEnchantedSmelterBlock.FACING)), 1);
							}

						}

					}
				} else {
					if (mainhand.isEnchanted()) {
						event.getLevel().setBlock(event.getPos(), AbstractEnchantedSmelterBlock.getSmelterFromBlock(event.getPlacedBlock().getBlock())
								.defaultBlockState().setValue(AbstractEnchantedSmelterBlock.FACING,
										event.getState().getValue(AbstractFurnaceBlock.FACING)), 1);
						AbstractEnchantedSmelterTileEntity tile = (AbstractEnchantedSmelterTileEntity) event.getLevel().getBlockEntity(event.getPos());

						if (Config.fastSmelt.isEnabled.get() && ModEnchantmentHelper.hasEnchant(EnchantmentInit.FAST_SMELT.get(), mainhand)) {
							tile.addEnchantment(EnchantmentInit.FAST_SMELT.get(), (short) ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FAST_SMELT.get(), mainhand));
						}

						if (Config.fuelEfficient.isEnabled.get() && ModEnchantmentHelper.hasEnchant(EnchantmentInit.FUEL_EFFICIENT.get(), mainhand)) {
							tile.addEnchantment(EnchantmentInit.FUEL_EFFICIENT.get(), (short) ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FUEL_EFFICIENT.get(), mainhand));
						}

						if (Config.extraExperience.isEnabled.get() && ModEnchantmentHelper.hasEnchant(EnchantmentInit.EXTRA_EXPERIENCE.get(), mainhand)) {
							tile.addEnchantment(EnchantmentInit.EXTRA_EXPERIENCE.get(), (short) ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.EXTRA_EXPERIENCE.get(), mainhand));
						}

					} else if (!(mainhand.getItem() instanceof BlockItem)) {
						if (Block.byItem(offhand.getItem()) instanceof AbstractFurnaceBlock) {
							if (offhand.isEnchanted()) {
								event.getLevel().setBlock(event.getPos(), AbstractEnchantedSmelterBlock.getSmelterFromBlock(event.getPlacedBlock().getBlock())
										.defaultBlockState().setValue(AbstractEnchantedSmelterBlock.FACING,
												event.getState().getValue(AbstractFurnaceBlock.FACING)), 1);
								AbstractEnchantedSmelterTileEntity tile = (AbstractEnchantedSmelterTileEntity) event.getLevel().getBlockEntity(event.getPos());

								if (Config.fastSmelt.isEnabled.get() && ModEnchantmentHelper.hasEnchant(EnchantmentInit.FAST_SMELT.get(), offhand)) {
									tile.addEnchantment(EnchantmentInit.FAST_SMELT.get(), (short) ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FAST_SMELT.get(), offhand));
								}

								if (Config.fuelEfficient.isEnabled.get() && ModEnchantmentHelper.hasEnchant(EnchantmentInit.FUEL_EFFICIENT.get(), offhand)) {
									tile.addEnchantment(EnchantmentInit.FUEL_EFFICIENT.get(), (short) ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FUEL_EFFICIENT.get(), offhand));
								}

								if (Config.extraExperience.isEnabled.get() && ModEnchantmentHelper.hasEnchant(EnchantmentInit.EXTRA_EXPERIENCE.get(), offhand)) {
									tile.addEnchantment(EnchantmentInit.EXTRA_EXPERIENCE.get(), (short) ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.EXTRA_EXPERIENCE.get(), offhand));
								}
							}
						}
					}
				}
			}

			else if (event.getPlacedBlock().getBlock() == Blocks.HOPPER && Config.fastHopper.isEnabled.get()) {

				if (mainhand.getItem() == Items.HOPPER) {

					if (ModEnchantmentHelper.hasFastHopper(player)) {
						event.getLevel().setBlock(event.getPos(), BlockInit.ENCHANTED_HOPPER.get().defaultBlockState().setValue(EnchantedHopper.FACING, event.getState().getValue(HopperBlock.FACING)), 1);
					}

				}
				else if (!(mainhand.getItem() instanceof BlockItem)) {

					if (offhand.getItem() == Items.HOPPER) {

						if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.FAST_HOPPER.get(), offhand)) {
							event.getLevel().setBlock(event.getPos(), BlockInit.ENCHANTED_HOPPER.get().defaultBlockState().setValue(EnchantedHopper.FACING, event.getState().getValue(HopperBlock.FACING)), 1);
						}

					}

				}

			}

			else if (event.getPlacedBlock().getBlock() instanceof ShulkerBoxBlock && Config.cavernousStorage.isEnabled.get()) {

				if (mainhand.getItem() instanceof BlockItem) {

					if (ModEnchantmentHelper.isCavernousStorage(mainhand.getEnchantmentTags())) {
						BlockState state = EnchantedShulkerBoxBlock.getBlockByColor(((ShulkerBoxBlock) ((BlockItem) mainhand.getItem()).getBlock()).getColor()).defaultBlockState();

						event.getLevel().setBlock(event.getPos(), state.setValue(EnchantedShulkerBoxBlock.FACING, event.getState().getValue(ShulkerBoxBlock.FACING)), 1);
					}

				}
				else if (!(mainhand.getItem() instanceof BlockItem)) {

					if (offhand.getItem() instanceof BlockItem) {

						if (ModEnchantmentHelper.isCavernousStorage(offhand.getEnchantmentTags())) {
							BlockState state = EnchantedShulkerBoxBlock.getBlockByColor(((ShulkerBoxBlock) ((BlockItem) offhand.getItem()).getBlock()).getColor()).defaultBlockState();

							event.getLevel().setBlock(event.getPos(), state.setValue(EnchantedShulkerBoxBlock.FACING, event.getState().getValue(ShulkerBoxBlock.FACING)), 1);
						}

					}

				}

			}

			else if (event.getPlacedBlock().getBlock() instanceof BarrelBlock && Config.cavernousStorage.isEnabled.get()) {

				if (mainhand.getItem() instanceof BlockItem) {

					if (ModEnchantmentHelper.isCavernousStorage(mainhand.getEnchantmentTags())) {
						BlockState state = BlockInit.ENCHANTED_BARREL.get().defaultBlockState();

						event.getLevel().setBlock(event.getPos(), state.setValue(EnchantedBarrelBlock.FACING, event.getState().getValue(BarrelBlock.FACING)), 1);
					}

				}
				else if (!(mainhand.getItem() instanceof BlockItem)) {

					if (offhand.getItem() instanceof BlockItem) {

						if (ModEnchantmentHelper.isCavernousStorage(offhand.getEnchantmentTags())) {
							BlockState state = BlockInit.ENCHANTED_BARREL.get().defaultBlockState();

							event.getLevel().setBlock(event.getPos(), state.setValue(EnchantedBarrelBlock.FACING, event.getState().getValue(BarrelBlock.FACING)), 1);
						}

					}

				}

			}

			else if (event.getPlacedBlock().getBlock() instanceof EnchantedShulkerBoxBlock) {
				EnchantedShulkerBoxBlock shulker = (EnchantedShulkerBoxBlock) event.getPlacedBlock().getBlock();

				if (!mainhand.isEnchanted()) {
					event.getLevel().setBlock(event.getPos(), ShulkerBoxBlock.getBlockByColor(shulker.getColor()).defaultBlockState().setValue(ShulkerBoxBlock.FACING, event.getState().getValue(EnchantedShulkerBoxBlock.FACING)), 1);
				}
				else if (!((mainhand.getItem()) instanceof BlockItem)) {

					if (!offhand.isEnchanted()) {
						event.getLevel().setBlock(event.getPos(), ShulkerBoxBlock.getBlockByColor(shulker.getColor()).defaultBlockState().setValue(ShulkerBoxBlock.FACING, event.getState().getValue(EnchantedShulkerBoxBlock.FACING)), 1);
					}
					else {
						event.setCanceled(true);
						SoManyEnchants.LOGGER.error("The player " + player.getName().getString() + " just placed a block illegally. He is likely hacking!");
					}

				}
				else if (!offhand.isEnchanted() && !mainhand.isEnchanted()) {
					event.getLevel().setBlock(event.getPos(), ShulkerBoxBlock.getBlockByColor(shulker.getColor()).defaultBlockState().setValue(ShulkerBoxBlock.FACING, event.getState().getValue(EnchantedShulkerBoxBlock.FACING)), 1);
				}

			}

			else if (event.getPlacedBlock().getBlock() == Blocks.CHEST && Config.cavernousStorage.isEnabled.get()) {

				if (mainhand.getItem() == Items.CHEST) {

					if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), mainhand)) {
						event.getLevel().setBlock(event.getPos(), BlockInit.ENCHANTED_CHEST.get().defaultBlockState().setValue(EnchantedChestBlock.FACING, event.getState().getValue(ChestBlock.FACING)), 1);

						Level world = (Level) event.getLevel();
						BlockState state = BlockInit.ENCHANTED_CHEST.get().defaultBlockState().setValue(EnchantedChestBlock.FACING, event.getState().getValue(ChestBlock.FACING));

						Direction direction = state.getValue(EnchantedChestBlock.FACING);
						BlockPos initial = event.getPos();
						BlockState newstate = world.getBlockState(getOffset(initial, direction, true));

						if (newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.getValue(EnchantedChestBlock.FACING) == state.getValue(EnchantedChestBlock.FACING) && newstate.getValue(EnchantedChestBlock.TYPE) == ChestType.SINGLE) {
							state = state.setValue(EnchantedChestBlock.TYPE, ChestType.RIGHT);
						}

						newstate = world.getBlockState(getOffset(initial, direction, false));

						if (newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.getValue(EnchantedChestBlock.FACING) == state.getValue(EnchantedChestBlock.FACING) && newstate.getValue(EnchantedChestBlock.TYPE) == ChestType.SINGLE) {
							state = state.setValue(EnchantedChestBlock.TYPE, ChestType.LEFT);
						}

						world.setBlockAndUpdate(event.getPos(), state);

					}

				}
				else if (!(mainhand.getItem() instanceof BlockItem)) {

					if (offhand.getItem() == Items.CHEST) {

						if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), offhand)) {
							event.getLevel().setBlock(event.getPos(), BlockInit.ENCHANTED_CHEST.get().defaultBlockState().setValue(EnchantedChestBlock.FACING, event.getState().getValue(ChestBlock.FACING)), 1);

							Level world = (Level) event.getLevel();
							BlockState state = BlockInit.ENCHANTED_CHEST.get().defaultBlockState().setValue(EnchantedChestBlock.FACING, event.getState().getValue(ChestBlock.FACING));

							Direction direction = state.getValue(EnchantedChestBlock.FACING);
							BlockPos initial = event.getPos();
							BlockState newstate = world.getBlockState(getOffset(initial, direction, true));

							if (newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.getValue(EnchantedChestBlock.FACING) == state.getValue(EnchantedChestBlock.FACING) && newstate.getValue(EnchantedChestBlock.TYPE) == ChestType.SINGLE) {
								state = state.setValue(EnchantedChestBlock.TYPE, ChestType.RIGHT);
							}

							newstate = world.getBlockState(getOffset(initial, direction, false));

							if (newstate.getBlock() == BlockInit.ENCHANTED_CHEST.get() && newstate.getValue(EnchantedChestBlock.FACING) == state.getValue(EnchantedChestBlock.FACING) && newstate.getValue(EnchantedChestBlock.TYPE) == ChestType.SINGLE) {
								state = state.setValue(EnchantedChestBlock.TYPE, ChestType.LEFT);
							}

							world.setBlockAndUpdate(event.getPos(), state);

						}

					}

				}

			}

			else if (event.getPlacedBlock().getBlock() == Blocks.TRAPPED_CHEST && (Config.cavernousStorage.isEnabled.get() || Config.camouflage.isEnabled.get())) {
				boolean placed = false;
				boolean hidden = false;

				if (mainhand.getItem() == Items.TRAPPED_CHEST) {

					if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), mainhand) && !ModEnchantmentHelper.hasEnchant(EnchantmentInit.CAMOUFLAGE.get(), mainhand) && Config.cavernousStorage.isEnabled.get()) {
						event.getLevel().setBlock(event.getPos(), BlockInit.TRAPPED_ENCHANTED_CHEST.get().defaultBlockState().setValue(EnchantedTrappedChestBlock.FACING, event.getState().getValue(TrappedChestBlock.FACING)), 1);

						BlockEntity t = event.getLevel().getBlockEntity(event.getPos());

						if (t != null && t instanceof EnchantedTrappedChestTileEntity) {
							EnchantedTrappedChestTileEntity tile = (EnchantedTrappedChestTileEntity) t;
							tile.addEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), (short) 1);
						}

						placed = true;
						hidden = false;
					}

					if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.CAMOUFLAGE.get(), mainhand) && Config.camouflage.isEnabled.get()) {
						event.getLevel().setBlock(event.getPos(), BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get().defaultBlockState().setValue(EnchantedTrappedChestBlock.FACING, event.getState().getValue(TrappedChestBlock.FACING)), 1);

						BlockEntity t = event.getLevel().getBlockEntity(event.getPos());

						if (t != null && t instanceof EnchantedHiddenTrappedChestTileEntity) {
							EnchantedHiddenTrappedChestTileEntity tile = (EnchantedHiddenTrappedChestTileEntity) t;
							tile.addEnchant(EnchantmentInit.CAMOUFLAGE.get(), (short) 1);

							if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), mainhand) && Config.cavernousStorage.isEnabled.get()) {
								tile.addEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), (short) 1);
							}

						}

						placed = true;
						hidden = true;
					}

					Level world = (Level) event.getLevel();
					BlockState state = world.getBlockState(event.getPos());

					Direction initDirection = state.getValue(EnchantedChestBlock.FACING);
					BlockPos newPos = getOffset(event.getPos(), initDirection, true);
					BlockState newState = world.getBlockState(newPos);
					Direction newDirection = null;
					ChestType newType = null;

					if (newState.getBlock() instanceof EnchantedChestBlock) {
						newDirection = newState.getValue(EnchantedChestBlock.FACING);
						newType = newState.getValue(EnchantedChestBlock.TYPE);

						if (newState.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newDirection == initDirection && newType == ChestType.SINGLE) {
							state = state.setValue(EnchantedChestBlock.TYPE, ChestType.RIGHT);
						}
						else if (newState.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newDirection == initDirection && newType == ChestType.SINGLE) {
							EnchantedHiddenTrappedChestTileEntity tile1 = (EnchantedHiddenTrappedChestTileEntity) world.getBlockEntity(event.getPos());
							EnchantedHiddenTrappedChestTileEntity tile2 = (EnchantedHiddenTrappedChestTileEntity) world.getBlockEntity(newPos);

							if (tile1.getEnchants().equals(tile2.getEnchants())) {
								state = state.setValue(EnchantedChestBlock.TYPE, ChestType.RIGHT);
							}

						}

					}

					newPos = getOffset(event.getPos(), initDirection, false);
					newState = world.getBlockState(newPos);

					if (newState.getBlock() instanceof EnchantedChestBlock) {
						newDirection = newState.getValue(EnchantedChestBlock.FACING);
						newType = newState.getValue(EnchantedChestBlock.TYPE);

						if (newState.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newDirection == initDirection && newType == ChestType.SINGLE) {
							state = state.setValue(EnchantedChestBlock.TYPE, ChestType.LEFT);
						}
						else if (newState.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newDirection == initDirection && newType == ChestType.SINGLE) {
							EnchantedHiddenTrappedChestTileEntity tile1 = (EnchantedHiddenTrappedChestTileEntity) world.getBlockEntity(event.getPos());
							EnchantedHiddenTrappedChestTileEntity tile2 = (EnchantedHiddenTrappedChestTileEntity) world.getBlockEntity(newPos);

							if (tile1.getEnchants().equals(tile2.getEnchants())) {
								state = state.setValue(EnchantedChestBlock.TYPE, ChestType.LEFT);
							}

						}

					}

					world.setBlockAndUpdate(event.getPos(), state);
				}

				else if (!(mainhand.getItem() instanceof BlockItem)) {
					placed = false;
					hidden = false;

					if (offhand.getItem() == Items.TRAPPED_CHEST) {

						if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), offhand) && !ModEnchantmentHelper.hasEnchant(EnchantmentInit.CAMOUFLAGE.get(), offhand) && Config.cavernousStorage.isEnabled.get()) {
							event.getLevel().setBlock(event.getPos(), BlockInit.TRAPPED_ENCHANTED_CHEST.get().defaultBlockState().setValue(EnchantedTrappedChestBlock.FACING, event.getState().getValue(TrappedChestBlock.FACING)), 1);

							BlockEntity t = event.getLevel().getBlockEntity(event.getPos());

							if (t != null && t instanceof EnchantedTrappedChestTileEntity) {
								EnchantedTrappedChestTileEntity tile = (EnchantedTrappedChestTileEntity) t;
								tile.addEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), (short) 1);
							}

							placed = true;
							hidden = false;
						}

						if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.CAMOUFLAGE.get(), offhand) && Config.camouflage.isEnabled.get()) {
							event.getLevel().setBlock(event.getPos(), BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get().defaultBlockState().setValue(EnchantedTrappedChestBlock.FACING, event.getState().getValue(TrappedChestBlock.FACING)), 1);

							BlockEntity t = event.getLevel().getBlockEntity(event.getPos());

							if (t != null && t instanceof EnchantedHiddenTrappedChestTileEntity) {
								EnchantedHiddenTrappedChestTileEntity tile = (EnchantedHiddenTrappedChestTileEntity) t;
								tile.addEnchant(EnchantmentInit.CAMOUFLAGE.get(), (short) 1);

								if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), offhand) && Config.cavernousStorage.isEnabled.get()) {
									tile.addEnchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), (short) 1);
								}

							}

							placed = true;
							hidden = true;
						}

						Level world = (Level) event.getLevel();
						BlockState state = world.getBlockState(event.getPos());

						Direction initDirection = state.getValue(EnchantedChestBlock.FACING);
						BlockPos newPos = getOffset(event.getPos(), initDirection, true);
						BlockState newState = world.getBlockState(newPos);
						Direction newDirection = null;
						ChestType newType = null;

						if (newState.getBlock() instanceof EnchantedChestBlock) {
							newDirection = newState.getValue(EnchantedChestBlock.FACING);
							newType = newState.getValue(EnchantedChestBlock.TYPE);

							if (newState.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newDirection == initDirection && newType == ChestType.SINGLE) {
								state = state.setValue(EnchantedChestBlock.TYPE, ChestType.RIGHT);
							}
							else if (newState.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newDirection == initDirection && newType == ChestType.SINGLE) {
								EnchantedHiddenTrappedChestTileEntity tile1 = (EnchantedHiddenTrappedChestTileEntity) world.getBlockEntity(event.getPos());
								EnchantedHiddenTrappedChestTileEntity tile2 = (EnchantedHiddenTrappedChestTileEntity) world.getBlockEntity(newPos);

								if (tile1.getEnchants().equals(tile2.getEnchants())) {
									state = state.setValue(EnchantedChestBlock.TYPE, ChestType.RIGHT);
								}

							}

						}

						newPos = getOffset(event.getPos(), initDirection, false);
						newState = world.getBlockState(newPos);

						if (newState.getBlock() instanceof EnchantedChestBlock) {
							newDirection = newState.getValue(EnchantedChestBlock.FACING);
							newType = newState.getValue(EnchantedChestBlock.TYPE);

							if (newState.getBlock() == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && newDirection == initDirection && newType == ChestType.SINGLE) {
								state = state.setValue(EnchantedChestBlock.TYPE, ChestType.LEFT);
							}
							else if (newState.getBlock() == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && newDirection == initDirection && newType == ChestType.SINGLE) {
								EnchantedHiddenTrappedChestTileEntity tile1 = (EnchantedHiddenTrappedChestTileEntity) world.getBlockEntity(event.getPos());
								EnchantedHiddenTrappedChestTileEntity tile2 = (EnchantedHiddenTrappedChestTileEntity) world.getBlockEntity(newPos);

								if (tile1.getEnchants().equals(tile2.getEnchants())) {
									state = state.setValue(EnchantedChestBlock.TYPE, ChestType.LEFT);
								}

							}

						}

						world.setBlockAndUpdate(event.getPos(), state);
					}

				}

			}

		}

	}

	@SubscribeEvent
	public static void breakEnchantedBlocks(final BreakEvent event) {

		Player player = event.getPlayer();
		Block block = event.getState().getBlock();
		Level world = (Level) event.getLevel();
		BlockState state = event.getState();
		BlockPos pos = event.getPos();
		breakEnchantedBlock(block, BlockInit.ENCHANTED_HOPPER.get(), state, pos, Items.HOPPER, world, player, EnchantmentInit.FAST_HOPPER.get());
		breakEnchantedBlock(block, BlockInit.ENCHANTED_CHEST.get(), state, pos, Items.CHEST, world, player, EnchantmentInit.CAVERNOUS_STORAGE.get());
		breakTrappedEnchantedChest(block, state, pos, world, player);

	}

	public static void breakEnchantedBlock(Block block, Block checkBlock, BlockState state, BlockPos pos, Item drop, Level world, Player player, Enchantment ench) {

		if (block == checkBlock && !player.isCreative() && !player.isSpectator() && block.canHarvestBlock(state, player.level, pos, player)) {
			ItemStack stack = new ItemStack(drop);
			stack.enchant(ench, 1);
			ItemEntity item = new ItemEntity((Level) world, pos.getX(), pos.getY(), pos.getZ(), stack);
			item.setPickUpDelay(10);

			world.addFreshEntity(item);
		}

	}

	public static void breakTrappedEnchantedChest(Block block, BlockState state, BlockPos pos, Level world, Player player) {

		if (block == BlockInit.TRAPPED_ENCHANTED_CHEST.get() && !player.isCreative() && !player.isSpectator() && block.canHarvestBlock(state, player.level, pos, player)) {
			ItemStack stack = new ItemStack(Items.TRAPPED_CHEST);
			BlockEntity tile = world.getBlockEntity(pos);

			if (tile instanceof EnchantedTrappedChestTileEntity) {
				EnchantedTrappedChestTileEntity eTile = (EnchantedTrappedChestTileEntity) tile;
				ListTag nbt = eTile.getEnchants();

				if (ModEnchantmentHelper.hasCamouflage(nbt)) {
					stack.enchant(EnchantmentInit.CAMOUFLAGE.get(), 1);
				}

				if (ModEnchantmentHelper.isCavernousStorage(nbt)) {
					stack.enchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), 1);
				}

			}

		}

		if (block == BlockInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get() && !player.isCreative() && !player.isSpectator() && block.canHarvestBlock(state, player.level, pos, player)) {
			ItemStack stack = new ItemStack(Items.TRAPPED_CHEST);
			BlockEntity tile = world.getBlockEntity(pos);

			if (tile instanceof EnchantedHiddenTrappedChestTileEntity) {
				EnchantedHiddenTrappedChestTileEntity eTile = (EnchantedHiddenTrappedChestTileEntity) tile;
				ListTag nbt = eTile.getEnchants();

				if (ModEnchantmentHelper.hasCamouflage(nbt)) {
					stack.enchant(EnchantmentInit.CAMOUFLAGE.get(), 1);
				}

				if (ModEnchantmentHelper.isCavernousStorage(nbt)) {
					stack.enchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), 1);
				}

			}

			ItemEntity item = new ItemEntity((Level) world, pos.getX(), pos.getY(), pos.getZ(), stack);

			item.setPickUpDelay(10);

			world.addFreshEntity(item);
		}

	}

	@SubscribeEvent
	public static void onEnchant(final CommandEvent event) {

		CommandContext<CommandSourceStack> command = event.getParseResults().getContext().build(null);

		if (command.getCommand() != null) {

			if (command.getCommand().toString().contains("net.minecraft.server.commands.EnchantCommand$$Lambda$")) {
				Enchantment enchant = command.getArgument("enchantment", Enchantment.class);
				EntitySelector e = command.getArgument("targets", EntitySelector.class);
				List<? extends Entity> entities;

				try {
					entities = e.findEntities(command.getSource());

					for (int x = 0; x < entities.size(); x++) {
						Entity entity = entities.get(x);

						if (entity instanceof LivingEntity) {
							LivingEntity living = (LivingEntity) entity;

							if (EnchantmentTypesInit.STORAGE.canEnchant(living.getMainHandItem().getItem()) && enchant == EnchantmentInit.CAVERNOUS_STORAGE.get() && Config.cavernousStorage.isEnabled.get()) {
								ItemStack stack = living.getMainHandItem();
								Item item = stack.getItem();

								if (item instanceof BlockItem) {
									BlockItem blockItem = (BlockItem) item;
									Block block = blockItem.getBlock();

									if (block instanceof ShulkerBoxBlock) {
										ItemStack newStack = new ItemStack(EnchantedShulkerBoxBlock.getBlockByColor(((ShulkerBoxBlock) block).getColor()));
										newStack.setTag(stack.getTag());
										newStack.enchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), 1);

										if (living instanceof Player) {
											Player player = (Player) living;
											player.inventory.setItem(getSlotFor(stack, player), newStack);
										}
										else {
											living.setItemSlot(EquipmentSlot.MAINHAND, newStack);
										}

										CommandSourceStack source = command.getSource();

										if (entities.size() == 1) {
											source.sendSuccess(Component.translatable("commands.enchant.success.single", enchant.getFullname(1), entities.iterator().next().getDisplayName()), true);
										}
										else {
											source.sendSuccess(Component.translatable("commands.enchant.success.multiple", enchant.getFullname(1), entities.size()), true);
										}

										event.setCanceled(true);
									}

								}

							}

							else if (EnchantmentTypesInit.SMELTER.canEnchant(living.getMainHandItem().getItem())) {
								ItemStack stack = living.getMainHandItem();
								Item item = stack.getItem();
								int level = 1;

								try {
									level = command.getArgument("level", Integer.class);
								}
								catch (IllegalArgumentException exception) {
									level = 1;
								}

								if (item instanceof BlockItem) {
									BlockItem blockItem = (BlockItem) item;
									Block block = blockItem.getBlock();

									if (block instanceof AbstractFurnaceBlock) {
										ItemStack newStack = new ItemStack(AbstractEnchantedSmelterBlock.getSmelterFromBlock(block));
										newStack.setTag(stack.getTag());

										if (enchant == EnchantmentInit.FAST_SMELT.get() && Config.fastSmelt.isEnabled.get()) {
											newStack.enchant(EnchantmentInit.FAST_SMELT.get(), level);
										}

										if (enchant == EnchantmentInit.FUEL_EFFICIENT.get() && Config.fuelEfficient.isEnabled.get()) {
											newStack.enchant(EnchantmentInit.FUEL_EFFICIENT.get(), level);
										}

										if (enchant == EnchantmentInit.EXTRA_EXPERIENCE.get() && Config.extraExperience.isEnabled.get()) {
											newStack.enchant(EnchantmentInit.EXTRA_EXPERIENCE.get(), level);
										}

										if (newStack.isEnchanted()) {

											if (living instanceof Player) {
												Player player = (Player) living;
												player.inventory.setItem(getSlotFor(stack, player), newStack);
											}
											else {
												living.setItemSlot(EquipmentSlot.MAINHAND, newStack);
											}

											CommandSourceStack source = command.getSource();

											if (entities.size() == 1) {
												source.sendSuccess(Component.translatable("commands.enchant.success.single", enchant.getFullname(level), entities.iterator().next().getDisplayName()), true);
											}
											else {
												source.sendSuccess(Component.translatable("commands.enchant.success.multiple", enchant.getFullname(level), entities.size()), true);
											}

											event.setCanceled(true);
										}

									}

									else if (block instanceof AbstractEnchantedSmelterBlock) {
										ItemStack newStack = new ItemStack(stack.getItem());
										newStack.setCount(stack.getCount());
										newStack.setTag(stack.getTag());

										if (enchant == EnchantmentInit.FAST_SMELT.get() && Config.fastSmelt.isEnabled.get()) {
											newStack.enchant(EnchantmentInit.FAST_SMELT.get(), level);
										}

										if (enchant == EnchantmentInit.FUEL_EFFICIENT.get() && Config.fuelEfficient.isEnabled.get()) {
											newStack.enchant(EnchantmentInit.FUEL_EFFICIENT.get(), level);
										}

										if (enchant == EnchantmentInit.EXTRA_EXPERIENCE.get() && Config.extraExperience.isEnabled.get()) {
											newStack.enchant(EnchantmentInit.EXTRA_EXPERIENCE.get(), level);
										}

										if (newStack.isEnchanted()) {

											if (living instanceof Player) {
												Player player = (Player) living;
												player.inventory.setItem(getSlotFor(stack, player), newStack);
											}
											else {
												living.setItemSlot(EquipmentSlot.MAINHAND, newStack);
											}

											CommandSourceStack source = command.getSource();

											if (entities.size() == 1) {
												source.sendSuccess(Component.translatable("commands.enchant.success.single", enchant.getFullname(level), entities.iterator().next().getDisplayName()), true);
											}
											else {
												source.sendSuccess(Component.translatable("commands.enchant.success.multiple", enchant.getFullname(level), entities.size()), true);
											}

											event.setCanceled(true);
										}

									}

								}

							}

						}

					}

				}
				catch (CommandSyntaxException e1) {
					e1.printStackTrace();
				}

			}

		}

	}

	@SubscribeEvent
	public static void onBookApply(final AnvilRepairEvent event) {

		Player player = event.getEntity();
		ItemStack initial = event.getLeft();
		ItemStack ingredient = event.getRight();
		ItemStack stack = event.getEntity().inventory.getSelected();

		if (ItemStack.isSame(initial, stack)) {

			if (ModEnchantmentHelper.isCavernousStorage(stack.getEnchantmentTags()) && stack.getItem() instanceof BlockItem && Config.cavernousStorage.isEnabled.get()) {
				Block block = Block.byItem(stack.getItem());

				if (block instanceof ShulkerBoxBlock) {
					ItemStack newStack = new ItemStack(EnchantedShulkerBoxBlock.getBlockByColor(((ShulkerBoxBlock) block).getColor()).asItem());
					newStack.enchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), 1);
					newStack.setTag(stack.getTag());
					player.inventory.setPickedItem(newStack);
				}

			}

			if (stack.getItem() instanceof BlockItem) {
				Block block = Block.byItem(stack.getItem());

				if (block instanceof AbstractFurnaceBlock && stack.isEnchanted()) {
					ItemStack newStack = new ItemStack(AbstractEnchantedSmelterBlock.getSmelterFromBlock(block));
					newStack.setTag(stack.getTag());
					int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FAST_SMELT.get(), stack);

					if (level > 0 && stack.getItem() instanceof BlockItem && Config.fastSmelt.isEnabled.get()) {
						newStack.enchant(EnchantmentInit.FAST_SMELT.get(), level);
					}

					level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FUEL_EFFICIENT.get(), stack);

					if (level > 0 && stack.getItem() instanceof BlockItem && Config.fuelEfficient.isEnabled.get()) {
						newStack.enchant(EnchantmentInit.FUEL_EFFICIENT.get(), level);
					}

					level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.EXTRA_EXPERIENCE.get(), stack);

					if (level > 0 && stack.getItem() instanceof BlockItem && Config.extraExperience.isEnabled.get()) {
						newStack.enchant(EnchantmentInit.EXTRA_EXPERIENCE.get(), level);
					}

					if (newStack.isEnchanted()) {
						player.inventory.setPickedItem(newStack);
					}

				}

			}

		}
		else {

			for (int x = 0; x < player.inventory.getContainerSize(); x++) {

				if (ItemStack.isSame(initial, player.inventory.getItem(x))) {
					ItemStack stack1 = player.inventory.getItem(x);

					if (ModEnchantmentHelper.isCavernousStorage(stack1.getEnchantmentTags()) && stack1.getItem() instanceof BlockItem && Config.cavernousStorage.isEnabled.get()) {
						Block block = ((BlockItem) stack1.getItem()).getBlock();

						if (block instanceof ShulkerBoxBlock) {
							ItemStack newStack = new ItemStack(EnchantedShulkerBoxBlock.getBlockByColor(((ShulkerBoxBlock) block).getColor()).asItem());
							newStack.enchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), 1);
							newStack.setTag(stack1.getTag());
							player.inventory.setItem(x, newStack);
						}

					}

					if (stack1.getItem() instanceof BlockItem) {
						Block block = Block.byItem(stack1.getItem());

						if (block instanceof AbstractFurnaceBlock && stack1.isEnchanted()) {
							ItemStack newStack = new ItemStack(AbstractEnchantedSmelterBlock.getSmelterFromBlock(block));
							newStack.setTag(stack1.getTag());
							int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FAST_SMELT.get(), stack1);

							if (level > 0 && stack1.getItem() instanceof BlockItem && Config.fastSmelt.isEnabled.get()) {
								newStack.enchant(EnchantmentInit.FAST_SMELT.get(), level);
							}

							level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FUEL_EFFICIENT.get(), stack1);

							if (level > 0 && stack1.getItem() instanceof BlockItem && Config.fuelEfficient.isEnabled.get()) {
								newStack.enchant(EnchantmentInit.FUEL_EFFICIENT.get(), level);
							}

							level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.EXTRA_EXPERIENCE.get(), stack1);

							if (level > 0 && stack1.getItem() instanceof BlockItem && Config.extraExperience.isEnabled.get()) {
								newStack.enchant(EnchantmentInit.EXTRA_EXPERIENCE.get(), level);
							}

							if (newStack.isEnchanted()) {
								player.inventory.setItem(x, newStack);
							}

						}

					}

				}

			}

		}

	}

	@SubscribeEvent
	public static void onPickup(final ItemPickupEvent event) {

		Player player = event.getEntity();
		ItemStack stack = event.getStack();
		int x = getSlotFor(stack, player);

		if (x >= 0) {

			if (ModEnchantmentHelper.isCavernousStorage(stack.getEnchantmentTags()) && stack.getItem() instanceof BlockItem && Config.cavernousStorage.isEnabled.get()) {
				Block block = Block.byItem(stack.getItem());

				if (block instanceof ShulkerBoxBlock) {
					ItemStack newStack = new ItemStack(EnchantedShulkerBoxBlock.getBlockByColor(((ShulkerBoxBlock) block).getColor()).asItem());
					newStack.setTag(stack.getTag());
					newStack.enchant(EnchantmentInit.CAVERNOUS_STORAGE.get(), 1);
					player.inventory.setItem(x, newStack);
				}

			}

			if (stack.getItem() instanceof BlockItem) {
				Block block = Block.byItem(stack.getItem());

				if (block instanceof AbstractFurnaceBlock && stack.isEnchanted()) {
					ItemStack newStack = new ItemStack(AbstractEnchantedSmelterBlock.getSmelterFromBlock(block));
					newStack.setTag(stack.getTag());
					int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FAST_SMELT.get(), stack);

					if (level > 0 && stack.getItem() instanceof BlockItem && Config.fastSmelt.isEnabled.get()) {
						newStack.enchant(EnchantmentInit.FAST_SMELT.get(), level);
					}

					level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FUEL_EFFICIENT.get(), stack);

					if (level > 0 && stack.getItem() instanceof BlockItem && Config.fuelEfficient.isEnabled.get()) {
						newStack.enchant(EnchantmentInit.FUEL_EFFICIENT.get(), level);
					}

					level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.EXTRA_EXPERIENCE.get(), stack);

					if (level > 0 && stack.getItem() instanceof BlockItem && Config.extraExperience.isEnabled.get()) {
						newStack.enchant(EnchantmentInit.EXTRA_EXPERIENCE.get(), level);
					}

					if (newStack.isEnchanted()) {
						player.inventory.setItem(x, newStack);
					}

				}

			}

		}

	}

	public static int getSlotFor(ItemStack stack, Player player) {

		for (int i = 0; i < player.inventory.getContainerSize(); ++i) {

			if (!player.inventory.getItem(i).isEmpty() && stackEqualExact(stack, player.inventory.getItem(i))) {
				return i;
			}

		}

		return -1;

	}

	private static boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {

		return stack1.getItem() == stack2.getItem() && ItemStack.tagMatches(stack1, stack2);

	}

	public static BlockPos getOffset(BlockPos initial, Direction direction, boolean right) {

		switch (direction) {
		case WEST:
			if (right) {
				return initial.offset(0, 0, 1);
			}
			return initial.offset(0, 0, -1);

		case EAST:
			if (right) {
				return initial.offset(0, 0, -1);
			}
			return initial.offset(0, 0, 1);

		case NORTH:
			if (right) {
				return initial.offset(-1, 0, 0);
			}
			return initial.offset(1, 0, 0);

		case SOUTH:
			if (right) {
				return initial.offset(1, 0, 0);
			}
			return initial.offset(-1, 0, 0);

		default:
			return initial;

		}

	}

}
