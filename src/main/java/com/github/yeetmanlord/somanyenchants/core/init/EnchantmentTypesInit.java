package com.github.yeetmanlord.somanyenchants.core.init;

import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.AbstractEnchantedSmelterBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class EnchantmentTypesInit {

	public static final EnchantmentCategory HOPPER = EnchantmentCategory.create("hopper", item -> item.equals(Items.HOPPER));

	public static final EnchantmentCategory TRAPPED_CHEST = EnchantmentCategory.create("trapped_chest",
			item -> item.equals(Items.TRAPPED_CHEST));

	public static final EnchantmentCategory STORAGE = EnchantmentCategory.create("storage_blocks", item -> isStorage(item));

	public static final EnchantmentCategory SMELTER = EnchantmentCategory.create("smelting_blocks", item -> isSmelter(item));

	public static boolean isStorage(Item item) {
		if (item instanceof BlockItem) {
			BlockItem blockItem = (BlockItem) item;
			Block block = blockItem.getBlock();
			if (block instanceof ShulkerBoxBlock || block instanceof EnchantedShulkerBoxBlock) {
				return true;
			} else if (item == Items.CHEST || item == Items.TRAPPED_CHEST || item == Items.BARREL) {
				return true;
			}
		}
		return false;
	};

	public static boolean isSmelter(Item item) {
		if (item instanceof BlockItem) {
			BlockItem blockItem = (BlockItem) item;
			Block block = blockItem.getBlock();
			if (block instanceof AbstractFurnaceBlock || block instanceof AbstractEnchantedSmelterBlock) {
				return true;
			}
		}
		return false;
	}

	public static boolean isModdedEnchantable(Item item) {
		return isSmelter(item) || isStorage(item) || HOPPER.canEnchant(item);
	}

}
