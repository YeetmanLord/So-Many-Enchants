package com.github.yeetmanlord.somanyenchants.common.container.slots;

import com.github.yeetmanlord.somanyenchants.common.tileentities.AbstractEnchantedSmelterTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.hooks.BasicEventHooks;

public class EnchantedSmelterResultSlot extends Slot {

	private final PlayerEntity player;
	private int removeCount;

	public EnchantedSmelterResultSlot(PlayerEntity player, IInventory inventoryIn, int slotIndex, int xPosition,
			int yPosition) {
		super(inventoryIn, slotIndex, xPosition, yPosition);
		this.player = player;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}

	@Override
	public ItemStack remove(int amount) {
		if (this.hasItem()) {
			this.removeCount += Math.min(amount, this.getItem().getCount());
		}

		return super.remove(amount);
	}

	@Override
	public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
		this.checkTakeAchievements(stack);
		super.onTake(thePlayer, stack);
		return stack;
	}

	@Override
	protected void onQuickCraft(ItemStack stack, int amount) {
		this.removeCount += amount;
		this.checkTakeAchievements(stack);
	}

	@Override
	protected void checkTakeAchievements(ItemStack stack) {
		stack.onCraftedBy(this.player.level, this.player, this.removeCount);
		if (!this.player.level.isClientSide && this.container instanceof AbstractEnchantedSmelterTileEntity) {
			((AbstractEnchantedSmelterTileEntity) this.container).unlockRecipes(this.player);
		}

		this.removeCount = 0;
		BasicEventHooks.firePlayerSmeltedEvent(this.player, stack);
	}

}
