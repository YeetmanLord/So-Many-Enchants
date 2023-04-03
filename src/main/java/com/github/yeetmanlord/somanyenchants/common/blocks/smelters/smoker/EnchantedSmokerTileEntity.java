package com.github.yeetmanlord.somanyenchants.common.blocks.smelters.smoker;

import com.github.yeetmanlord.somanyenchants.common.tileentities.AbstractEnchantedSmelterTileEntity;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

public class EnchantedSmokerTileEntity extends AbstractEnchantedSmelterTileEntity
{

	public EnchantedSmokerTileEntity() {
	      super(BlockEntityTypeInit.ENCHANTED_SMOKER.get(), IRecipeType.SMOKING);
	   }

	@Override
	protected ITextComponent getDefaultName()
	{ return new TranslationTextComponent("container.enchantedSmoker"); }

	@Override
	protected int getBurnDuration(ItemStack fuel)
	{
		return super.getBurnDuration(fuel) / 2;
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player)
	{
		return new EnchantedSmokerContainer(id, player, this, this.furnaceData);
	}

}
