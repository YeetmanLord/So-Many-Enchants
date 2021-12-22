package com.yeetmanlord.somanyenchants.common.blocks.smelters.blast_furnace;

import com.yeetmanlord.somanyenchants.common.tileentities.AbstractEnchantedSmelterTileEntity;
import com.yeetmanlord.somanyenchants.core.init.TileEntityTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class EnchantedBlastFurnaceTileEntity extends AbstractEnchantedSmelterTileEntity
{
	public EnchantedBlastFurnaceTileEntity()
	{
		super(TileEntityTypeInit.ENCHANTED_BLAST_FURNACE.get(), IRecipeType.BLASTING);
	}

	protected ITextComponent getDefaultName()
	{ return new TranslationTextComponent("container.enchantedBlastFurnace"); }

	protected int getBurnTime(ItemStack fuel)
	{
		return super.getBurnTime(fuel) / 2;
	}

	protected Container createMenu(int id, PlayerInventory player)
	{
		return new EnchantedBlastFurnaceContainer(id, player, this, this.furnaceData);
	}
}
