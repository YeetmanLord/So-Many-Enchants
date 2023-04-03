package com.github.yeetmanlord.somanyenchants.common.blocks.smelters.furnace;

import com.github.yeetmanlord.somanyenchants.common.tileentities.AbstractEnchantedSmelterTileEntity;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class EnchantedFurnaceTileEntity extends AbstractEnchantedSmelterTileEntity {

	public EnchantedFurnaceTileEntity() 
	{
		super(BlockEntityTypeInit.ENCHANTED_FURNACE.get(), IRecipeType.SMELTING);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.enchantedFurnace");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new EnchantedFurnaceContainer(id, player, this, this.furnaceData);
	}

}
