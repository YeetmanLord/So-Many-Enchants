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
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VillagerProfessionInit {

	public static final DeferredRegister<VillagerProfession> JOBS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, SoManyEnchants.MOD_ID);

	public static final DeferredRegister<PointOfInterestType> POI_TYPE = DeferredRegister.create(ForgeRegistries.POI_TYPES, SoManyEnchants.MOD_ID);

	public static final RegistryObject<PointOfInterestType> ENCHANTMENT_TABLE = registerPOI();

	public static final RegistryObject<VillagerProfession> ENCHANTER = registerVillager();

	private static RegistryObject<PointOfInterestType> registerPOI() {

		if (Config.villager.isEnabled.get()) {
			return POI_TYPE.register("enchantment_table", () -> new PointOfInterestType("enchantment_table", getAllStates(Blocks.ENCHANTING_TABLE), 1, 1));
		}

		return null;

	}

	private static RegistryObject<VillagerProfession> registerVillager() {

		if (Config.villager.isEnabled.get()) {
			return JOBS.register("enchanter", () -> new VillagerProfession("enchanter", VillagerProfessionInit.ENCHANTMENT_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENCHANTMENT_TABLE_USE));
		}

		return null;

	}

	public static Set<BlockState> getAllStates(Block blockIn) {

		return ImmutableSet.copyOf(blockIn.getStateDefinition().getPossibleStates());

	}

	public static void fillTradeData() {

		if (Config.villager.isEnabled.get() == false) {
			return;
		}
		else {
			VillagerTrades.ITrade[] level1 = new VillagerTrades.ITrade[] { new EnchantedBookForEmeraldsTrade(5), new ExperienceBottleTrade(), new VillagerTrades.ItemsForEmeraldsTrade(Items.BOOKSHELF, new Random().nextInt(3) + 9, 3, 5), new EnchantedBookForEmeraldsTrade(5) };
			VillagerTrades.ITrade[] level2 = new VillagerTrades.ITrade[] { new EnchantedBookForEmeraldsTrade(10), new EnchantedBookForEmeraldsTrade(10), new VillagerTrades.ItemsForEmeraldsTrade(Blocks.ENCHANTING_TABLE, 16, 1, 1, 10) };
			VillagerTrades.ITrade[] level3 = new VillagerTrades.ITrade[] { new EnchantedBookForEmeraldsTrade(15), new EnchantedBookForEmeraldsTrade(15) };
			VillagerTrades.ITrade[] level4 = new VillagerTrades.ITrade[] { new EnchantedBookForEmeraldsTrade(20), new EnchantedBookForEmeraldsTrade(20) };
			VillagerTrades.ITrade[] level5 = new VillagerTrades.ITrade[] { new EnchantedBookForEmeraldsTrade(25), new EnchantedBookForEmeraldsTrade(25), new VillagerTrades.ItemsForEmeraldsAndItemsTrade(Items.GOLD_BLOCK, new Random().nextInt(8) + 16, Items.ENCHANTED_GOLDEN_APPLE, 64, 1, 24) };

			VillagerTrades.TRADES.put(VillagerProfessionInit.ENCHANTER.get(), gatAsIntMap(ImmutableMap.of(1, level1, 2, level2, 3, level3, 4, level4, 5, level5)));
		}

	}

	public static class EnchantedBookForEmeraldsTrade implements VillagerTrades.ITrade {

		private final int xpValue;

		public EnchantedBookForEmeraldsTrade(int xpValueIn) {

			this.xpValue = xpValueIn;

		}

		@Override
		public MerchantOffer getOffer(Entity trader, Random rand) {

			List<Enchantment> list = Registry.ENCHANTMENT.stream().collect(Collectors.toList());
			Enchantment enchantment = list.get(rand.nextInt(list.size()));
			int i = MathHelper.nextInt(rand, enchantment.getMaxLevel(), enchantment.getMaxLevel());

			if (enchantment.getMaxLevel() > 5) {
				i = MathHelper.nextInt(rand, enchantment.getMinLevel() + 5, enchantment.getMaxLevel());
			}
			else if (enchantment.getMaxLevel() == 0) {
				return getOffer(trader, rand);
			}

			ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentData(enchantment, i));
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

	public static class ExperienceBottleTrade implements VillagerTrades.ITrade {

		private final Item tradeItem = new ItemStack(Items.EXPERIENCE_BOTTLE).getItem();

		private final int maxUses = 12;

		private final int xpValue = 1;

		private final float priceMultiplier;

		public ExperienceBottleTrade() {

			this.priceMultiplier = 0.05F;

		}

		@Override
		public MerchantOffer getOffer(Entity trader, Random rand) {

			ItemStack itemstack = new ItemStack(this.tradeItem, 1 + rand.nextInt(12));
			return new MerchantOffer(new ItemStack(Items.EMERALD, 3), itemstack, this.maxUses, this.xpValue, this.priceMultiplier);

		}

	}

	private static Int2ObjectMap<VillagerTrades.ITrade[]> gatAsIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {

		return new Int2ObjectOpenHashMap<>(p_221238_0_);

	}

}
