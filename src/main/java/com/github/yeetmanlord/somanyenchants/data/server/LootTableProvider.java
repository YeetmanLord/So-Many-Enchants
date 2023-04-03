package com.github.yeetmanlord.somanyenchants.data.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.AbstractEnchantedSmelterBlock;
import com.github.yeetmanlord.somanyenchants.core.init.BlockInit;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.DynamicLootEntry;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.loot.functions.CopyName;
import net.minecraft.loot.functions.CopyNbt;
import net.minecraft.loot.functions.SetContents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class LootTableProvider extends net.minecraft.data.LootTableProvider {

	public LootTableProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables() {
		return ImmutableList.of(Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK));
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
		map.forEach((location, table) -> {
			LootTableManager.validate(validationtracker, location, table);
		});
	}

	public static final class ModBlockLootTables extends BlockLootTables {
		@Override
		protected void addTables() {
			for (RegistryObject<Block> entry : BlockInit.BLOCKS.getEntries()) {
				if (entry.get() instanceof EnchantedShulkerBoxBlock) {
					this.add(entry.get(), ModBlockLootTables::item1);
				}
			}

			this.add(BlockInit.ENCHANTED_FURNACE.get(), ModBlockLootTables::item);
			this.add(BlockInit.ENCHANTED_BLAST_FURNACE.get(), ModBlockLootTables::item);
			this.add(BlockInit.ENCHANTED_SMOKER.get(), ModBlockLootTables::item);
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
			return LootTable.lootTable().withPool(applyExplosionCondition(block, LootPool.lootPool()
					.setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(block))
					.apply(CopyNbt.copyData(CopyNbt.Source.BLOCK_ENTITY).copy("Enchantments", "Enchantments"))));
		}

		public static LootTable.Builder item1(Block block) {
			return LootTable.lootTable().withPool(applyExplosionCondition(block, LootPool.lootPool()
					.setRolls(ConstantRange.exactly(1))
					.add(ItemLootEntry.lootTableItem(block).apply(CopyName.copyName(CopyName.Source.BLOCK_ENTITY))
							.apply(CopyNbt.copyData(CopyNbt.Source.BLOCK_ENTITY).copy("Lock", "BlockEntityTag.Lock")
									.copy("LootTable", "BlockEntityTag.LootTable")
									.copy("LootTableSeed", "BlockEntityTag.LootTableSeed"))
							.apply(SetContents.setContents()
									.withEntry(DynamicLootEntry.dynamicEntry(ShulkerBoxBlock.CONTENTS)))
							.apply(CopyNbt.copyData(CopyNbt.Source.BLOCK_ENTITY).copy("Enchantments",
									"Enchantments")))));
		}
	}

}
