package com.github.yeetmanlord.somanyenchants.core.init;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VillagerProfessionInit {

	public static final DeferredRegister<VillagerProfession> JOBS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, SoManyEnchants.MOD_ID);

	public static final DeferredRegister<PoiType> POI_TYPE = DeferredRegister.create(ForgeRegistries.POI_TYPES, SoManyEnchants.MOD_ID);

	public static final RegistryObject<PoiType> ENCHANTMENT_TABLE = registerPOI();

	public static final RegistryObject<VillagerProfession> ENCHANTER = registerVillager();

	private static RegistryObject<PoiType> registerPOI() {

		return POI_TYPE.register("enchantment_table", () -> new PoiType(getAllStates(Blocks.ENCHANTING_TABLE), 1, 1));

	}

	private static RegistryObject<VillagerProfession> registerVillager() {

		return JOBS.register("enchanter", () -> new VillagerProfession("enchanter", (holder) -> {
			return holder.is(VillagerProfessionInit.ENCHANTMENT_TABLE.getKey());
		}, (holder) -> {
			return holder.is(VillagerProfessionInit.ENCHANTMENT_TABLE.getKey());
		}, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENCHANTMENT_TABLE_USE));

	}

	public static Set<BlockState> getAllStates(Block blockIn) {

		return ImmutableSet.copyOf(blockIn.getStateDefinition().getPossibleStates());

	}

	public static void fillTradeData() {

		if (Config.villager.isEnabled.get() == false) {
			return;
		}
		else {
			VillagerTrades.ItemListing[] level1 = new VillagerTrades.ItemListing[] { new EnchantedBookForEmeraldsTrade(5), new ExperienceBottleTrade(), new VillagerTrades.ItemsForEmeralds(Items.BOOKSHELF, new Random().nextInt(3) + 9, 3, 5), new EnchantedBookForEmeraldsTrade(5) };
			VillagerTrades.ItemListing[] level2 = new VillagerTrades.ItemListing[] { new EnchantedBookForEmeraldsTrade(10), new EnchantedBookForEmeraldsTrade(10), new VillagerTrades.ItemsForEmeralds(Blocks.ENCHANTING_TABLE, 16, 1, 1, 10) };
			VillagerTrades.ItemListing[] level3 = new VillagerTrades.ItemListing[] { new EnchantedBookForEmeraldsTrade(15), new EnchantedBookForEmeraldsTrade(15) };
			VillagerTrades.ItemListing[] level4 = new VillagerTrades.ItemListing[] { new EnchantedBookForEmeraldsTrade(20), new EnchantedBookForEmeraldsTrade(20) };
			VillagerTrades.ItemListing[] level5 = new VillagerTrades.ItemListing[] { new EnchantedBookForEmeraldsTrade(25), new EnchantedBookForEmeraldsTrade(25), new VillagerTrades.ItemsAndEmeraldsToItems(Items.GOLD_BLOCK, new Random().nextInt(8) + 16, Items.ENCHANTED_GOLDEN_APPLE, 64, 1, 24) };

			VillagerTrades.TRADES.put(VillagerProfessionInit.ENCHANTER.get(), gatAsIntMap(ImmutableMap.of(1, level1, 2, level2, 3, level3, 4, level4, 5, level5)));
		}

	}

	public static class EnchantedBookForEmeraldsTrade implements VillagerTrades.ItemListing {

		private final int xpValue;

		public EnchantedBookForEmeraldsTrade(int xpValueIn) {

			this.xpValue = xpValueIn;

		}

		@Override
		public MerchantOffer getOffer(Entity trader, RandomSource rand) {

			List<Enchantment> list = Registry.ENCHANTMENT.stream().collect(Collectors.toList());
			Enchantment enchantment = list.get(rand.nextInt(list.size()));
			int i = Mth.nextInt(rand, enchantment.getMaxLevel(), enchantment.getMaxLevel());

			if (enchantment.getMaxLevel() > 5) {
				i = Mth.nextInt(rand, enchantment.getMinLevel() + 5, enchantment.getMaxLevel());
			}
			else if (enchantment.getMaxLevel() == 0) {
				return getOffer(trader, rand);
			}

			ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i));
			int j = 0;

			if (enchantment.getMaxLevel() > 5) {
				j = 2 + rand.nextInt(5 + i * 10) + 3 * i - 5;
			}
			else {
				j = 2 + rand.nextInt(5 + i * 10) + 3 * i;
			}

			if (enchantment.isTreasureOnly()) {
				j *= 2;
			}

			if (j > 64) {
				j = 64;
			}

			return new MerchantOffer(new ItemStack(Items.EMERALD, j), new ItemStack(Items.BOOK), itemstack, 12, this.xpValue, 0.2F);

		}

	}

	public static class ExperienceBottleTrade implements VillagerTrades.ItemListing {

		private final Item tradeItem = new ItemStack(Items.EXPERIENCE_BOTTLE).getItem();

		private final int maxUses = 12;

		private final int xpValue = 1;

		private final float priceMultiplier;

		public ExperienceBottleTrade() {

			this.priceMultiplier = 0.05F;

		}

		@Override
		public MerchantOffer getOffer(Entity trader, RandomSource rand) {

			ItemStack itemstack = new ItemStack(this.tradeItem, 1 + rand.nextInt(12));
			return new MerchantOffer(new ItemStack(Items.EMERALD, 3), itemstack, this.maxUses, this.xpValue, this.priceMultiplier);

		}

	}

	private static Int2ObjectMap<VillagerTrades.ItemListing[]> gatAsIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_221238_0_) {

		return new Int2ObjectOpenHashMap<>(p_221238_0_);

	}

}
