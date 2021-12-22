package com.yeetmanlord.somanyenchants.common.blocks.smelters.furnace;

import com.yeetmanlord.somanyenchants.common.tileentities.AbstractEnchantedSmelterTileEntity;
import com.yeetmanlord.somanyenchants.core.init.TileEntityTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class EnchantedFurnaceTileEntity extends AbstractEnchantedSmelterTileEntity {

	public EnchantedFurnaceTileEntity() 
	{
		super(TileEntityTypeInit.ENCHANTED_FURNACE.get(), IRecipeType.SMELTING);
	}

	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.enchantedFurnace");
	}

	protected Container createMenu(int id, PlayerInventory player) {
		return new EnchantedFurnaceContainer(id, player, this, this.furnaceData);
	}

}
