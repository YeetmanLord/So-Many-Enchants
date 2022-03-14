package com.github.yeetmanlord.somanyenchants.common.container;

import com.github.yeetmanlord.somanyenchants.common.container.slots.EnchantedSmelterFuelSlot;
import com.github.yeetmanlord.somanyenchants.common.container.slots.EnchantedSmelterResultSlot;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

@SuppressWarnings( "unchecked" )
public class AbstractEnchantedSmelterContainer extends RecipeBookMenu<Container> {
	private final Container container;
	private final ContainerData data;
	protected final Level level;
	private final RecipeType<? extends AbstractCookingRecipe> recipeType;
	private final RecipeBookType recipeBookType;

	protected AbstractEnchantedSmelterContainer(MenuType<?> type,
			RecipeType<? extends AbstractCookingRecipe> recipeType, RecipeBookType bookCat, int id,
			Inventory playerInv) {
		this(type, recipeType, bookCat, id, playerInv, new SimpleContainer(3), new SimpleContainerData(4));
	}

	protected AbstractEnchantedSmelterContainer(MenuType<?> type,
			RecipeType<? extends AbstractCookingRecipe> recipeType, RecipeBookType bookCat, int id, Inventory playerInv,
			Container furnaceInv, ContainerData furnaceData) {
		super(type, id);
		this.recipeType = recipeType;
		this.recipeBookType = bookCat;
		checkContainerSize(furnaceInv, 3);
		checkContainerDataCount(furnaceData, 4);
		this.container = furnaceInv;
		this.data = furnaceData;
		this.level = playerInv.player.level;
		this.addSlot(new Slot(furnaceInv, 0, 56, 17));
		this.addSlot(new EnchantedSmelterFuelSlot(this, furnaceInv, 1, 56, 53));
		this.addSlot(new EnchantedSmelterResultSlot(playerInv.player, furnaceInv, 2, 116, 35));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(playerInv, k, 8 + k * 18, 142));
		}

		this.addDataSlots(furnaceData);
	}

	@Override
	public void fillCraftSlotsStackedContents(StackedContents p_38976_) {
		if (this.container instanceof StackedContentsCompatible) {
			((StackedContentsCompatible) this.container).fillStackedContents(p_38976_);
		}

	}

	@Override
	public void clearCraftingContent() {
		this.getSlot(0).set(ItemStack.EMPTY);
		this.getSlot(2).set(ItemStack.EMPTY);
	}

	@Override
	public boolean recipeMatches(Recipe<? super Container> p_38980_) {
		return p_38980_.matches(this.container, this.level);
	}

	@Override
	public int getResultSlotIndex() {
		return 2;
	}

	@Override
	public int getGridWidth() {
		return 1;
	}

	@Override
	public int getGridHeight() {
		return 1;
	}

	@Override
	public int getSize() {
		return 3;
	}

	@Override
	public boolean stillValid(Player p_38974_) {
		return this.container.stillValid(p_38974_);
	}

	@Override
	public ItemStack quickMoveStack(Player p_38986_, int p_38987_) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(p_38987_);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (p_38987_ == 2) {
				if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (p_38987_ != 1 && p_38987_ != 0) {
				if (this.canSmelt(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (this.isFuel(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (p_38987_ >= 3 && p_38987_ < 30) {
					if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
						return ItemStack.EMPTY;
					}
				} else if (p_38987_ >= 30 && p_38987_ < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(p_38986_, itemstack1);
		}

		return itemstack;
	}

	protected boolean canSmelt(ItemStack p_38978_) {
		return this.level.getRecipeManager().getRecipeFor((RecipeType<AbstractCookingRecipe>) this.recipeType,
				new SimpleContainer(p_38978_), this.level).isPresent();
	}

	public boolean isFuel(ItemStack p_38989_) {
		return net.minecraftforge.common.ForgeHooks.getBurnTime(p_38989_, this.recipeType) > 0;
	}

	public int getBurnProgress() {
		int i = this.data.get(2);
		int j = this.data.get(3);
		return j != 0 && i != 0 ? i * 24 / j : 0;
	}

	public int getLitProgress() {
		int i = this.data.get(1);
		if (i == 0) {
			i = 200;
		}

		return this.data.get(0) * 13 / i;
	}

	public boolean isLit() {
		return this.data.get(0) > 0;
	}

	@Override
	public RecipeBookType getRecipeBookType() {
		return this.recipeBookType;
	}

	@Override
	public boolean shouldMoveToInventory(int p_150463_) {
		return p_150463_ != 1;
	}
}
