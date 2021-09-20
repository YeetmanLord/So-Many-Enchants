package com.yeetmanlord.somanyenchants.core.init;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.common.blocks.EnchantedHopper;
import com.yeetmanlord.somanyenchants.common.items.GlintedBlockItem;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
	
	public static final RegistryObject<Block> ENCHANTED_HOPPER = BLOCKS.register("enchanted_hopper", () -> new EnchantedHopper(Block.Properties.from(Blocks.HOPPER).setOpaque(BlockInit::isntSolid).setBlocksVision(BlockInit::isntSolid).setSuffocates(BlockInit::isntSolid)));
	
	public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
	
	public static final RegistryObject<Item> ENCHANTED_HOPPER_ITEM = BLOCK_ITEMS.register("enchanted_hopper", () -> new GlintedBlockItem(BlockInit.ENCHANTED_HOPPER.get(), (new Item.Properties()).group(ItemGroup.REDSTONE)));
	
	private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
	      return false;
	}

}
