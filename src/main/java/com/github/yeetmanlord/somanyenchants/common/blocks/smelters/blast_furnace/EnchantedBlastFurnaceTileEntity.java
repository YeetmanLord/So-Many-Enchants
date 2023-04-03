package com.github.yeetmanlord.somanyenchants.common.blocks.smelters.blast_furnace;

import com.github.yeetmanlord.somanyenchants.common.tileentities.AbstractEnchantedSmelterTileEntity;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;

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
		super(BlockEntityTypeInit.ENCHANTED_BLAST_FURNACE.get(), IRecipeType.BLASTING);
	}

	@Override
	protected ITextComponent getDefaultName()
	{ return new TranslationTextComponent("container.enchantedBlastFurnace"); }

	@Override
	protected int getBurnDuration(ItemStack fuel)
	{
		return super.getBurnDuration(fuel) / 2;
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player)
	{
		return new EnchantedBlastFurnaceContainer(id, player, this, this.furnaceData);
	}
}
