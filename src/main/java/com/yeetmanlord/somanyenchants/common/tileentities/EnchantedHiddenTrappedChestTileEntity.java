package com.yeetmanlord.somanyenchants.common.tileentities;

import com.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.yeetmanlord.somanyenchants.core.init.TileEntityTypeInit;
import com.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class EnchantedHiddenTrappedChestTileEntity extends EnchantedChestTileEntity {
	
	private ListNBT enchants; 

	public EnchantedHiddenTrappedChestTileEntity() {
		super(TileEntityTypeInit.HIDDEN_TRAPPED_ENCHANTED_CHEST.get());
		enchants = new ListNBT();
	}

	protected void onOpenOrClose() {
		super.onOpenOrClose();
		this.world.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockState().getBlock());
	}
	
	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		
		
		this.enchants = nbt.getList("Enchantments", 10);

	}
	
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);

		compound.put("Enchantments", this.getEnchants());
		
		return compound;
	}

	public ListNBT getEnchants() {
		return this.enchants;
	}

	public void addEnchant(Enchantment ench, short lvl)
	{
		CompoundNBT nbt = new CompoundNBT();
		nbt.putString("id", String.valueOf(ench.getRegistryName()));
		nbt.putShort("lvl", lvl);
		this.enchants.add(nbt);
	}
	
	@Override
	public int getSizeInventory()
	{
		if(ModEnchantmentHelper.isCavernousStorage(this.enchants)) {
			return super.getSizeInventory();
		} else {
			return 27;
		}
	}
	
	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		if(ModEnchantmentHelper.isCavernousStorage(this.enchants)) {
			return EnchantedChestContainer.createGeneric9X4(id, player, this);
		} else {
			return EnchantedChestContainer.createGeneric9X3(id, player, this);
		}
	}
	
	

}
