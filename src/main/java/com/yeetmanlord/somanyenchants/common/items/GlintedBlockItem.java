package com.yeetmanlord.somanyenchants.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class GlintedBlockItem extends BlockItem {

	public GlintedBlockItem(Block blockIn, Properties builder)
	{
		super(blockIn, builder);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}

}
