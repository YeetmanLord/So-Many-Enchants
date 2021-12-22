package com.yeetmanlord.somanyenchants.common.container;

import com.yeetmanlord.somanyenchants.common.container.slots.EnchantedSmelterFuelSlot;
import com.yeetmanlord.somanyenchants.common.container.slots.EnchantedSmelterResultSlot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.item.crafting.ServerRecipePlacerFurnace;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings({"unchecked", "rawtypes"})
public class AbstractEnchantedSmelterContainer extends RecipeBookContainer<IInventory> {
	private final IInventory furnaceInventory;
	private final IIntArray furnaceData;
	protected final World world;
	private final IRecipeType<? extends AbstractCookingRecipe> recipeType;
	private final RecipeBookCategory bookCategory;

	protected AbstractEnchantedSmelterContainer(ContainerType<?> type, IRecipeType<? extends AbstractCookingRecipe> recipeType, RecipeBookCategory bookCat, int id, PlayerInventory playerInv) {
	      this(type, recipeType, bookCat, id, playerInv, new Inventory(3), new IntArray(4));
	   }

	protected AbstractEnchantedSmelterContainer(ContainerType<?> type, IRecipeType<? extends AbstractCookingRecipe> recipeType, RecipeBookCategory bookCat, int id, PlayerInventory playerInv, IInventory furnaceInv, IIntArray furnaceData) {
	      super(type, id);
	      this.recipeType = recipeType;
	      this.bookCategory = bookCat;
	      assertInventorySize(furnaceInv, 3);
	      assertIntArraySize(furnaceData, 4);
	      this.furnaceInventory = furnaceInv;
	      this.furnaceData = furnaceData;
	      this.world = playerInv.player.world;
	      this.addSlot(new Slot(furnaceInv, 0, 56, 17));
	      this.addSlot(new EnchantedSmelterFuelSlot(this, furnaceInv, 1, 56, 53));
	      this.addSlot(new EnchantedSmelterResultSlot(playerInv.player, furnaceInv, 2, 116, 35));

	      for(int i = 0; i < 3; ++i) {
	         for(int j = 0; j < 9; ++j) {
	            this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
	         }
	      }

	      for(int k = 0; k < 9; ++k) {
	         this.addSlot(new Slot(playerInv, k, 8 + k * 18, 142));
	      }

	      this.trackIntArray(furnaceData);
	   }

	public void fillStackedContents(RecipeItemHelper itemHelperIn) {
		if (this.furnaceInventory instanceof IRecipeHelperPopulator) {
			((IRecipeHelperPopulator) this.furnaceInventory).fillStackedContents(itemHelperIn);
		}

	}

	public void clear() {
		this.furnaceInventory.clear();
	}

	public void func_217056_a(boolean p_217056_1_, IRecipe<?> p_217056_2_, ServerPlayerEntity player) {
		(new ServerRecipePlacerFurnace<>(this)).place(player, (IRecipe<IInventory>) p_217056_2_, p_217056_1_);
	}

	public boolean matches(IRecipe<? super IInventory> recipeIn) {
		return recipeIn.matches(this.furnaceInventory, this.world);
	}

	public int getOutputSlot() {
		return 2;
	}

	public int getWidth() {
		return 1;
	}

	public int getHeight() {
		return 1;
	}

	@OnlyIn(Dist.CLIENT)
	public int getSize() {
		return 3;
	}

	/**
	 * Determines whether supplied player can use this container
	 */
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.furnaceInventory.isUsableByPlayer(playerIn);
	}

	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this
	 * moves the stack between the player inventory and the other inventory(s).
	 */
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index != 1 && index != 0) {
				if (this.hasRecipe(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (this.isFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 3 && index < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, itemstack1);
		}

		return itemstack;
	}
	
	
	protected boolean hasRecipe(ItemStack stack) {
		return this.world.getRecipeManager().getRecipe((IRecipeType) this.recipeType, new Inventory(stack), this.world)
				.isPresent();
	}

	public boolean isFuel(ItemStack stack) {
		return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, this.recipeType) > 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int getCookProgressionScaled() {
		int i = this.furnaceData.get(2);
		int j = this.furnaceData.get(3);
		return j != 0 && i != 0 ? i * 24 / j : 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int getBurnLeftScaled() {
		int i = this.furnaceData.get(1);
		if (i == 0) {
			i = 200;
		}

		return this.furnaceData.get(0) * 13 / i;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isBurning() {
		return this.furnaceData.get(0) > 0;
	}

	@OnlyIn(Dist.CLIENT)
	public RecipeBookCategory func_241850_m() {
		return this.bookCategory;
	}
}
