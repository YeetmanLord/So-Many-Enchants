package com.github.yeetmanlord.somanyenchants.common.tileentities;

import com.github.yeetmanlord.somanyenchants.common.container.EnchantedChestContainer;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class EnchantedTrappedChestTileEntity extends EnchantedChestTileEntity {
	
	protected NonNullList<ItemStack> chestContents = NonNullList.withSize(36, ItemStack.EMPTY);
	
	private ListTag enchants;

	public EnchantedTrappedChestTileEntity(BlockPos pos, BlockState state) {
		super(BlockEntityTypeInit.TRAPPED_ENCHANTED_CHEST.get(), pos, state);
		enchants = new ListTag();
	}

	@Override
	protected void signalOpenCount(Level p_155865_, BlockPos p_155866_, BlockState p_155867_, int p_155868_,
			int p_155869_) {
		super.signalOpenCount(p_155865_, p_155866_, p_155867_, p_155868_, p_155869_);
		if (p_155868_ != p_155869_) {
			Block block = p_155867_.getBlock();
			p_155865_.updateNeighborsAt(p_155866_, block);
			p_155865_.updateNeighborsAt(p_155866_.below(), block);
		}

	}
	
	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.enchants = nbt.getList("Enchantments", 10);
	}
	
	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);

		compound.put("Enchantments", enchants);
	}

	public ListTag getEnchants() {
		return this.enchants;
	}

	public void addEnchant(Enchantment ench, short lvl)
	{
		CompoundTag nbt = new CompoundTag();
		nbt.putString("id", Registry.ENCHANTMENT.getKey(ench).toString());
		nbt.putShort("lvl", lvl);
		this.enchants.add(nbt);
	}
	
	@Override
	public int getContainerSize()
	{
		if(ModEnchantmentHelper.isCavernousStorage(this.enchants)) {
			return super.getContainerSize();
		} else {
			return 27;
		}
	}
	
	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory player) {
		if(ModEnchantmentHelper.isCavernousStorage(this.enchants)) {
			return EnchantedChestContainer.createGeneric9X4(id, player, this);
		} else {
			return EnchantedChestContainer.createGeneric9X3(id, player, this);
		}
	}
	
	

}
