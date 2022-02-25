package com.yeetmanlord.somanyenchants.data.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.yeetmanlord.somanyenchants.common.blocks.smelters.AbstractEnchantedSmelterBlock;
import com.yeetmanlord.somanyenchants.core.init.BlockInit;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.functions.SetEnchantmentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

public class LootTableProvider extends net.minecraft.data.loot.LootTableProvider {

	public LootTableProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootContextParamSet>> getTables() {
		return ImmutableList.of(Pair.of(BlockLootTables::new, LootContextParamSets.BLOCK));
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
		map.forEach((location, table) -> {
			LootTables.validate(validationtracker, location, table);
		});
	}

	public static final class BlockLootTables extends BlockLoot {
		@Override
		protected void addTables() {
			for (RegistryObject<Block> entry : BlockInit.BLOCKS.getEntries()) {
				if (entry.get() instanceof EnchantedShulkerBoxBlock) {
					this.add(entry.get(), BlockLootTables::item1);
				}
			}

			this.add(BlockInit.ENCHANTED_FURNACE.get(), BlockLootTables::item);
			this.add(BlockInit.ENCHANTED_BLAST_FURNACE.get(), BlockLootTables::item);
			this.add(BlockInit.ENCHANTED_SMOKER.get(), BlockLootTables::item);
		}

		@Override
		protected Iterable<Block> getKnownBlocks() {
			ArrayList<Block> list = new ArrayList<>();
			for (RegistryObject<Block> entry : BlockInit.BLOCKS.getEntries()) {
				Block block = entry.get();
				if (block instanceof EnchantedShulkerBoxBlock || block instanceof AbstractEnchantedSmelterBlock) {
					list.add(entry.get());
				}
			}
			return list;
		}

		public static LootTable.Builder item(Block block) {
			return LootTable.lootTable().withPool(applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block)).apply(
					CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Enchantments", "Enchantments"))));
		}

		public static LootTable.Builder item1(Block block) {
			return LootTable.lootTable().withPool(applyExplosionCondition(block,
					LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block)
							.apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
							.apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
									.copy("Lock", "BlockEntityTag.Lock").copy("LootTable", "BlockEntityTag.LootTable")
									.copy("LootTableSeed", "BlockEntityTag.LootTableSeed"))
							.apply(SetContainerContents.setContents(BlockEntityType.SHULKER_BOX)
									.withEntry(DynamicLoot.dynamicEntry(ShulkerBoxBlock.CONTENTS)))
							.apply(new SetEnchantmentsFunction.Builder().withEnchantment(
									EnchantmentInit.CAVERNOUS_STORAGE.get(), ConstantValue.exactly(1F))))));
		}
	}

}
