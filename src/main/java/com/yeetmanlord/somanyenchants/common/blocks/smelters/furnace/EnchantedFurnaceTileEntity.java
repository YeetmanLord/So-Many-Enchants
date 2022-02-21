package com.yeetmanlord.somanyenchants.common.blocks.smelters.furnace;

import com.yeetmanlord.somanyenchants.common.tileentities.AbstractEnchantedSmelterTileEntity;
import com.yeetmanlord.somanyenchants.core.init.TileEntityTypeInit;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;

public class EnchantedFurnaceTileEntity extends AbstractEnchantedSmelterTileEntity {

	public EnchantedFurnaceTileEntity(BlockPos pos, BlockState state) 
	{
		super(TileEntityTypeInit.ENCHANTED_FURNACE.get(), RecipeType.SMELTING, pos, state);
	}

	@Override
	protected Component getDefaultName() {
		return new TranslatableComponent("container.enchantedFurnace");
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory player) {
		return new EnchantedFurnaceContainer(id, player, this, this.furnaceData);
	}

}
