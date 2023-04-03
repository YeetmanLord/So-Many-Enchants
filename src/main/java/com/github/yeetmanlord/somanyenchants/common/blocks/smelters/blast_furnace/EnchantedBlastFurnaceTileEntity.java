package com.github.yeetmanlord.somanyenchants.common.blocks.smelters.blast_furnace;

import com.github.yeetmanlord.somanyenchants.common.tileentities.AbstractEnchantedSmelterTileEntity;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;

public class EnchantedBlastFurnaceTileEntity extends AbstractEnchantedSmelterTileEntity
{
	public EnchantedBlastFurnaceTileEntity(BlockPos pos, BlockState state)
	{
		super(BlockEntityTypeInit.ENCHANTED_BLAST_FURNACE.get(), RecipeType.BLASTING, pos, state);
	}

	@Override
	protected Component getDefaultName()
	{ return Component.translatable("container.enchantedBlastFurnace"); }

	@Override
	protected int getBurnDuration(ItemStack fuel)
	{
		return super.getBurnDuration(fuel) / 2;
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory player)
	{
		return new EnchantedBlastFurnaceContainer(id, player, this, this.furnaceData);
	}
}
