package com.github.yeetmanlord.somanyenchants.common.tileentities;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nullable;

import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.AbstractEnchantedSmelterBlock;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;
import com.github.yeetmanlord.somanyenchants.core.util.interfaces.IEnchantableBlock;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("unchecked")
public abstract class AbstractEnchantedSmelterTileEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible, IEnchantableBlock {

	private static final int[] SLOTS_UP = new int[] { 0 };

	private static final int[] SLOTS_DOWN = new int[] { 2, 1 };

	private static final int[] SLOTS_HORIZONTAL = new int[] { 1 };

	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

	protected ListTag enchantmentNBT;

	private float burnTimeMult;

	private float cookTimeMult;

	private float experienceMult;

	private int litTime;

	private int litDuration;

	private int cookingProgress;

	private int cookingTotalTime;

	protected final ContainerData furnaceData = new ContainerData() {

		@Override
		public int get(int index) {

			switch (index) {
				case 0:
					return AbstractEnchantedSmelterTileEntity.this.litTime;

				case 1:
					return AbstractEnchantedSmelterTileEntity.this.litDuration;

				case 2:
					return AbstractEnchantedSmelterTileEntity.this.cookingProgress;

				case 3:
					return AbstractEnchantedSmelterTileEntity.this.cookingTotalTime;

				default:
					return 0;
			}

		}

		@Override
		public void set(int index, int value) {

			switch (index) {
				case 0:
					AbstractEnchantedSmelterTileEntity.this.litTime = value;
					break;

				case 1:
					AbstractEnchantedSmelterTileEntity.this.litDuration = value;
					break;

				case 2:
					AbstractEnchantedSmelterTileEntity.this.cookingProgress = value;
					break;

				case 3:
					AbstractEnchantedSmelterTileEntity.this.cookingTotalTime = value;
			}

		}

		@Override
		public int getCount() {

			return 4;

		}

	};

	private final Object2IntOpenHashMap<ResourceLocation> recipes = new Object2IntOpenHashMap<>();

	protected final RecipeType<? extends AbstractCookingRecipe> recipeType;

	protected AbstractEnchantedSmelterTileEntity(BlockEntityType<?> tileTypeIn, RecipeType<? extends AbstractCookingRecipe> recipeTypeIn, BlockPos pos, BlockState state) {

		super(tileTypeIn, pos, state);
		this.recipeType = recipeTypeIn;
		this.enchantmentNBT = new ListTag();
		this.burnTimeMult = 1;
		this.cookTimeMult = 1;
		this.experienceMult = 1;

	}

	public static Map<Item, Integer> getFuel() {

		Map<Item, Integer> map = Maps.newLinkedHashMap();
		add(map, Items.LAVA_BUCKET, 20000);
		add(map, Blocks.COAL_BLOCK, 16000);
		add(map, Items.BLAZE_ROD, 2400);
		add(map, Items.COAL, 1600);
		add(map, Items.CHARCOAL, 1600);
		add(map, ItemTags.LOGS, 300);
		add(map, ItemTags.PLANKS, 300);
		add(map, ItemTags.WOODEN_STAIRS, 300);
		add(map, ItemTags.WOODEN_SLABS, 150);
		add(map, ItemTags.WOODEN_TRAPDOORS, 300);
		add(map, ItemTags.WOODEN_PRESSURE_PLATES, 300);
		add(map, Blocks.OAK_FENCE, 300);
		add(map, Blocks.BIRCH_FENCE, 300);
		add(map, Blocks.SPRUCE_FENCE, 300);
		add(map, Blocks.JUNGLE_FENCE, 300);
		add(map, Blocks.DARK_OAK_FENCE, 300);
		add(map, Blocks.ACACIA_FENCE, 300);
		add(map, Blocks.OAK_FENCE_GATE, 300);
		add(map, Blocks.BIRCH_FENCE_GATE, 300);
		add(map, Blocks.SPRUCE_FENCE_GATE, 300);
		add(map, Blocks.JUNGLE_FENCE_GATE, 300);
		add(map, Blocks.DARK_OAK_FENCE_GATE, 300);
		add(map, Blocks.ACACIA_FENCE_GATE, 300);
		add(map, Blocks.NOTE_BLOCK, 300);
		add(map, Blocks.BOOKSHELF, 300);
		add(map, Blocks.LECTERN, 300);
		add(map, Blocks.JUKEBOX, 300);
		add(map, Blocks.CHEST, 300);
		add(map, Blocks.TRAPPED_CHEST, 300);
		add(map, Blocks.CRAFTING_TABLE, 300);
		add(map, Blocks.DAYLIGHT_DETECTOR, 300);
		add(map, ItemTags.BANNERS, 300);
		add(map, Items.BOW, 300);
		add(map, Items.FISHING_ROD, 300);
		add(map, Blocks.LADDER, 300);
		add(map, ItemTags.SIGNS, 200);
		add(map, Items.WOODEN_SHOVEL, 200);
		add(map, Items.WOODEN_SWORD, 200);
		add(map, Items.WOODEN_HOE, 200);
		add(map, Items.WOODEN_AXE, 200);
		add(map, Items.WOODEN_PICKAXE, 200);
		add(map, ItemTags.WOODEN_DOORS, 200);
		add(map, ItemTags.BOATS, 1200);
		add(map, ItemTags.WOOL, 100);
		add(map, ItemTags.WOODEN_BUTTONS, 100);
		add(map, Items.STICK, 100);
		add(map, ItemTags.SAPLINGS, 100);
		add(map, Items.BOWL, 100);
		add(map, ItemTags.WOOL_CARPETS, 67);
		add(map, Blocks.DRIED_KELP_BLOCK, 4001);
		add(map, Items.CROSSBOW, 300);
		add(map, Blocks.BAMBOO, 50);
		add(map, Blocks.DEAD_BUSH, 100);
		add(map, Blocks.SCAFFOLDING, 400);
		add(map, Blocks.LOOM, 300);
		add(map, Blocks.BARREL, 300);
		add(map, Blocks.CARTOGRAPHY_TABLE, 300);
		add(map, Blocks.FLETCHING_TABLE, 300);
		add(map, Blocks.SMITHING_TABLE, 300);
		add(map, Blocks.COMPOSTER, 300);
		return map;

	}

	private static boolean isNeverAFurnaceFuel(Item item) {

		return item.builtInRegistryHolder().is(ItemTags.NON_FLAMMABLE_WOOD);

	}

	private static void add(Map<Item, Integer> p_204303_, TagKey<Item> p_204304_, int p_204305_) {

		for (Holder<Item> holder : Registry.ITEM.getTagOrEmpty(p_204304_)) {

			if (!isNeverAFurnaceFuel(holder.value())) {
				p_204303_.put(holder.value(), p_204305_);
			}

		}

	}

	private static void add(Map<Item, Integer> p_58375_, ItemLike p_58376_, int p_58377_) {

		Item item = p_58376_.asItem();

		if (isNeverAFurnaceFuel(item)) {

			if (SharedConstants.IS_RUNNING_IN_IDE) {
				throw (IllegalStateException) Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName((ItemStack) null).getString() + " a furnace fuel. That will not work!"));
			}

		}
		else {
			p_58375_.put(item, p_58377_);
		}

	}

	private boolean isLit() {

		return this.litTime > 0;

	}

	@Override
	public void load(CompoundTag nbt) {

		super.load(nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(nbt, this.items);
		this.litTime = nbt.getInt("BurnTime");
		this.cookingProgress = nbt.getInt("CookTime");
		this.cookingTotalTime = nbt.getInt("CookTimeTotal");
		this.litDuration = this.getBurnDuration(this.items.get(1));
		CompoundTag compoundnbt = nbt.getCompound("RecipesUsed");

		for (String s : compoundnbt.getAllKeys()) {
			this.recipes.put(new ResourceLocation(s), compoundnbt.getInt(s));
		}

		this.enchantmentNBT = nbt.getList("Enchantments", 10);

	}

	@Override
	public void saveAdditional(CompoundTag compound) {

		super.saveAdditional(compound);
		compound.putInt("BurnTime", this.litTime);
		compound.putInt("CookTime", this.cookingProgress);
		compound.putInt("CookTimeTotal", this.cookingTotalTime);
		ContainerHelper.saveAllItems(compound, this.items);
		CompoundTag compoundnbt = new CompoundTag();
		this.recipes.forEach((recipeId, craftedAmount) -> {
			compoundnbt.putInt(recipeId.toString(), craftedAmount);
		});
		compound.put("RecipesUsed", compoundnbt);
		compound.put("Enchantments", enchantmentNBT);

	}

	public static void serverTick(Level world, BlockPos pos, BlockState state, AbstractEnchantedSmelterTileEntity smelter) {

		boolean flag = smelter.isLit();
		boolean flag1 = false;

		if (smelter.isLit()) {
			--smelter.litTime;
		}

		ItemStack itemstack = smelter.items.get(1);

		if (smelter.isLit() || !itemstack.isEmpty() && !smelter.items.get(0).isEmpty()) {
			Recipe<?> recipe = world.getRecipeManager().getRecipeFor((RecipeType<AbstractCookingRecipe>) smelter.recipeType, smelter, world).orElse(null);
			int i = smelter.getMaxStackSize();

			if (!smelter.isLit() && smelter.canBurn(recipe, smelter.items, i)) {
				smelter.litTime = smelter.getBurnDuration(itemstack);
				smelter.litDuration = smelter.litTime;

				if (smelter.isLit()) {
					flag1 = true;
					if (itemstack.hasCraftingRemainingItem()) smelter.items.set(1, itemstack.getCraftingRemainingItem());
					else if (!itemstack.isEmpty()) {
						Item item = itemstack.getItem();
						itemstack.shrink(1);

						if (itemstack.isEmpty()) {
							smelter.items.set(1, itemstack.getCraftingRemainingItem());
						}

					}
				}

			}

			if (smelter.isLit() && smelter.canBurn(recipe, smelter.items, i)) {
				++smelter.cookingProgress;

				if (smelter.cookingProgress == smelter.cookingTotalTime) {
					smelter.cookingProgress = 0;
					smelter.cookingTotalTime = smelter.getTotalCookTime();

					if (smelter.burn(recipe, smelter.items, i)) {
						smelter.setRecipeUsed(recipe);
					}

				}

			}
			else {
				smelter.cookingProgress = 0;
			}

		}
		else if (!smelter.isLit() && smelter.cookingProgress > 0) {
			smelter.cookingProgress = Mth.clamp(smelter.cookingProgress - 2, 0, smelter.cookingTotalTime);
		}

		if (flag != smelter.isLit()) {
			flag1 = true;
			state = state.setValue(AbstractEnchantedSmelterBlock.LIT, Boolean.valueOf(smelter.isLit()));
			world.setBlock(pos, state, 3);
		}

		if (flag1) {
			setChanged(world, pos, state);
		}

	}

	private boolean canBurn(@Nullable Recipe<?> recipe, NonNullList<ItemStack> items, int maxStackSize) {

		if (!items.get(0).isEmpty() && recipe != null) {
			ItemStack itemstack = ((Recipe<WorldlyContainer>) recipe).assemble(this);

			if (itemstack.isEmpty()) {
				return false;
			}
			else {
				ItemStack itemstack1 = items.get(2);

				if (itemstack1.isEmpty()) {
					return true;
				}
				else if (!itemstack1.sameItem(itemstack)) {
					return false;
				}
				else if (itemstack1.getCount() + itemstack.getCount() <= maxStackSize && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge
																																											// fix:
																																											// make
																																											// furnace
																																											// respect
																																											// stack
																																											// sizes
																																											// in
																																											// furnace
																																											// recipes
					return true;
				}
				else {
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix:
																										// make furnace
																										// respect stack
																										// sizes in
																										// furnace
																										// recipes
				}

			}

		}
		else {
			return false;
		}

	}

	private boolean burn(@Nullable Recipe<?> recipe, NonNullList<ItemStack> items, int stackSize) {

		if (recipe != null && this.canBurn(recipe, items, stackSize)) {
			ItemStack itemstack = items.get(0);
			ItemStack itemstack1 = ((Recipe<WorldlyContainer>) recipe).assemble(this);
			ItemStack itemstack2 = items.get(2);

			if (itemstack2.isEmpty()) {
				items.set(2, itemstack1.copy());
			}
			else if (itemstack2.is(itemstack1.getItem())) {
				itemstack2.grow(itemstack1.getCount());
			}

			if (itemstack.is(Blocks.WET_SPONGE.asItem()) && !items.get(1).isEmpty() && items.get(1).is(Items.BUCKET)) {
				items.set(1, new ItemStack(Items.WATER_BUCKET));
			}

			itemstack.shrink(1);
			return true;
		}
		else {
			return false;
		}

	}

	protected int getBurnDuration(ItemStack fuel) {

		if (fuel.isEmpty()) {
			return 0;
		}
		else {
			Item item = fuel.getItem();
			this.burnTimeMult = makeNonNegative(ModEnchantmentHelper.getEnchantmentLevel(this.enchantmentNBT, EnchantmentInit.FUEL_EFFICIENT.get())) + 1;
			return (int) (net.minecraftforge.common.ForgeHooks.getBurnTime(fuel, this.recipeType) * burnTimeMult);
		}

	}

	protected int getTotalCookTime() {

		this.cookTimeMult = (makeNonNegative(ModEnchantmentHelper.getEnchantmentLevel(enchantmentNBT, EnchantmentInit.FAST_SMELT.get())) + 1);
		return getCookTimeWithMult();

	}

	public static boolean isFuel(ItemStack stack) {

		return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, null) > 0;

	}

	@Override
	public int[] getSlotsForFace(Direction side) {

		if (side == Direction.DOWN) {
			return SLOTS_DOWN;
		}
		else {
			return side == Direction.UP ? SLOTS_UP : SLOTS_HORIZONTAL;
		}

	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {

		return this.canPlaceItem(index, itemStackIn);

	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {

		if (direction == Direction.DOWN && index == 1) {
			Item item = stack.getItem();

			if (item != Items.WATER_BUCKET && item != Items.BUCKET) {
				return false;
			}

		}

		return true;

	}

	@Override
	public int getContainerSize() {

		return this.items.size();

	}

	@Override
	public boolean isEmpty() {

		for (ItemStack itemstack : this.items) {

			if (!itemstack.isEmpty()) {
				return false;
			}

		}

		return true;

	}

	/**
	 * Returns the stack in the given slot.
	 */
	@Override
	public ItemStack getItem(int index) {

		return this.items.get(index);

	}

	/**
	 * Removes up to a specified number of items from an inventory slot and returns
	 * them in a new stack.
	 */
	@Override
	public ItemStack removeItem(int index, int count) {

		return ContainerHelper.removeItem(this.items, index, count);

	}

	/**
	 * Removes a stack from the given slot and returns it.
	 */
	@Override
	public ItemStack removeItemNoUpdate(int index) {

		return ContainerHelper.takeItem(this.items, index);

	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	@Override
	public void setItem(int index, ItemStack stack) {

		ItemStack itemstack = this.items.get(index);
		boolean flag = !stack.isEmpty() && stack.sameItem(itemstack) && ItemStack.tagMatches(stack, itemstack);
		this.items.set(index, stack);

		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}

		if (index == 0 && !flag) {
			this.cookingTotalTime = this.getTotalCookTime();
			this.cookingProgress = 0;
			this.setChanged();
		}

	}

	/**
	 * Don't rename this method to canInteractWith due to conflicts with Container
	 */
	@Override
	public boolean stillValid(Player player) {

		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		}
		else {
			return player.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}

	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot. For guis use Slot.isItemValid
	 */
	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {

		if (index == 2) {
			return false;
		}
		else if (index != 1) {
			return true;
		}
		else {
			ItemStack itemstack = this.items.get(1);
			return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, this.recipeType) > 0 || stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET;
		}

	}

	@Override
	public void clearContent() {

		this.items.clear();

	}

	@Override
	public void setRecipeUsed(@Nullable Recipe<?> recipe) {

		if (recipe != null) {
			ResourceLocation resourcelocation = recipe.getId();
			this.recipes.addTo(resourcelocation, 1);
		}

	}

	@Override
	@Nullable
	public Recipe<?> getRecipeUsed() {

		return null;

	}

	@Override
	public void awardUsedRecipes(Player player) {

	}

	public void unlockRecipes(Player player) {

		List<Recipe<?>> list = this.grantStoredRecipeExperience(player.level, player.position());
		player.awardRecipes(list);
		this.recipes.clear();

	}

	public List<Recipe<?>> grantStoredRecipeExperience(Level world, Vec3 pos) {

		List<Recipe<?>> list = Lists.newArrayList();
		this.experienceMult = makeNonNegative(ModEnchantmentHelper.getEnchantmentLevel(enchantmentNBT, EnchantmentInit.EXTRA_EXPERIENCE.get()) + 1);

		for (Entry<ResourceLocation> entry : this.recipes.object2IntEntrySet()) {
			world.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe) -> {
				list.add(recipe);
				splitAndSpawnExperience(world, pos, entry.getIntValue(), ((AbstractCookingRecipe) recipe).getExperience() * experienceMult);
			});
		}

		return list;

	}

	private static void splitAndSpawnExperience(Level world, Vec3 pos, int craftedAmount, float experience) {

		int i = Mth.floor((float) craftedAmount * experience);
		float f = Mth.frac((float) craftedAmount * experience);

		if (f != 0.0F && Math.random() < (double) f) {
			++i;
		}

		while (i > 0) {
			int j = ExperienceOrb.getExperienceValue(i);
			i -= j;
			world.addFreshEntity(new ExperienceOrb(world, pos.x, pos.y, pos.z, j));
		}

	}

	@Override
	public void fillStackedContents(StackedContents helper) {

		for (ItemStack itemstack : this.items) {
			helper.accountStack(itemstack);
		}

	}

	net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers = net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {

		if (!this.remove && facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing == Direction.UP) return handlers[0].cast();
			else if (facing == Direction.DOWN) return handlers[1].cast();
			else return handlers[2].cast();
		}

		return super.getCapability(capability, facing);

	}

	@Override
	public void invalidateCaps() {

		super.invalidateCaps();
		for (int x = 0; x < handlers.length; x++)
			handlers[x].invalidate();

	}

	@Override
	public void addEnchantment(Enchantment ench, short lvl) {

		CompoundTag nbt = new CompoundTag();
		nbt.putString("id", Registry.ENCHANTMENT.getKey(ench).toString());
		nbt.putShort("lvl", lvl);
		this.enchantmentNBT.add(nbt);
		CompoundTag writeNBT = new CompoundTag();
		this.saveAdditional(writeNBT);

	}

	@Override
	public ListTag getEnchantments() {

		return enchantmentNBT;

	}

	private float makeNonNegative(float value) {

		if (value < 0) {
			return 0;
		}

		return value;

	}

	private int getCookTimeWithMult() {

		Optional<AbstractCookingRecipe> recipe = this.level.getRecipeManager().getRecipeFor((RecipeType<AbstractCookingRecipe>) this.recipeType, this, this.level);

		if (recipe != null) {
			AbstractCookingRecipe recipeObject = recipe.get();

			if (recipeObject != null) {
				return (int) (recipeObject.getCookingTime() / cookTimeMult);
			}

		}

		return (int) (200 / cookTimeMult);

	}

}
