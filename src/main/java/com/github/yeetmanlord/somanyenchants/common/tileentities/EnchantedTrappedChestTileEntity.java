package com.github.yeetmanlord.somanyenchants.common.tileentities;

import com.github.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;

public class EnchantedTrappedChestTileEntity extends EnchantedChestTileEntity {

	protected NonNullList<ItemStack> chestContents = NonNullList.withSize(36, ItemStack.EMPTY);

	private ListNBT enchants;

	public EnchantedTrappedChestTileEntity() {
		super(BlockEntityTypeInit.TRAPPED_ENCHANTED_CHEST.get());
		enchants = new ListNBT();
	}

	@Override
	protected void signalOpenCount() {
		super.signalOpenCount();
		this.level.updateNeighborsAt(this.worldPosition.below(), this.getBlockState().getBlock());
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		this.enchants = nbt.getList("Enchantments", 10);
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);

		compound.put("Enchantments", enchants);
		return compound;
	}

	public ListNBT getEnchants() {
		return this.enchants;
	}

	public void addEnchant(Enchantment ench, short lvl) {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putString("id", String.valueOf(ench.getRegistryName()));
		nbt.putShort("lvl", lvl);
		this.enchants.add(nbt);
	}

	@Override
	public int getContainerSize() {
		if (ModEnchantmentHelper.isCavernousStorage(this.enchants)) {
			return super.getContainerSize();
		} else {
			return 27;
		}
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		if (ModEnchantmentHelper.isCavernousStorage(this.enchants)) {
			return EnchantedChestContainer.createGeneric9X4(id, player, this);
		} else {
			return EnchantedChestContainer.createGeneric9X3(id, player, this);
		}
	}

}
