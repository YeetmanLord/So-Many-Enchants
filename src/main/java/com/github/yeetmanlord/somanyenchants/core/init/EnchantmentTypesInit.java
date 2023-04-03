package com.github.yeetmanlord.somanyenchants.core.init;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.enchantment.EnchantmentType;

import com.github.yeetmanlord.somanyenchants.common.blocks.EnchantedShulkerBoxBlock;
import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.AbstractEnchantedSmelterBlock;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class EnchantmentTypesInit {

	public static final EnchantmentType HOPPER = EnchantmentType.create("hopper", item -> item.equals(Items.HOPPER));

	public static final EnchantmentType TRAPPED_CHEST = EnchantmentType.create("trapped_chest",
			item -> item.equals(Items.TRAPPED_CHEST));

	public static final EnchantmentType STORAGE = EnchantmentType.create("storage_blocks", item -> isStorage(item));

	public static final EnchantmentType SMELTER = EnchantmentType.create("smelting_blocks", item -> isSmelter(item));

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
