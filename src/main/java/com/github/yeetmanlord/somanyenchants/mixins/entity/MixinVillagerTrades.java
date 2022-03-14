package com.github.yeetmanlord.somanyenchants.mixins.entity;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.core.Registry;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;

@Mixin(VillagerTrades.EnchantBookForEmeralds.class)
public class MixinVillagerTrades {
	@Shadow
	private int villagerXp;
	
	@Overwrite
	/**
	 * This overwrite disallows librarian villagers from offering higher level trades
	 * @param entity An unused parameter but refers to the villager 
	 * @return Returns a merchant offer for this villager aka a random trade
	 */
	public MerchantOffer getOffer(Entity entity, Random rand) {
		List<Enchantment> list = Registry.ENCHANTMENT.stream().filter(Enchantment::isTradeable).collect(Collectors.toList());
        Enchantment enchantment = list.get(rand.nextInt(list.size()));
        int i = Mth.nextInt(rand, enchantment.getMinLevel(), enchantment.getMaxLevel());
        if(enchantment.getMaxLevel() > 5)
        {
       	 i = Mth.nextInt(rand, enchantment.getMinLevel(), 5);
        }
        ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i));
        int j = 2 + rand.nextInt(5 + i * 10) + 3 * i;
        if (enchantment.isTreasureOnly()) {
           j *= 2;
        }

        if (j > 64) {
           j = 64;
        }

        return new MerchantOffer(new ItemStack(Items.EMERALD, j), new ItemStack(Items.BOOK), itemstack, 12, this.villagerXp, 0.2F);
     }
	
	
}
