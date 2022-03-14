package com.github.yeetmanlord.somanyenchants.core.events;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.github.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;
import com.github.yeetmanlord.somanyenchants.core.util.PlayerAttributeHandler;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class WeaponAttributes
{

	@SubscribeEvent
	public static void mainhand(final LivingEquipmentChangeEvent event)
	{
		if (event.getEntityLiving() instanceof Player) {
			Player player = (Player)event.getEntityLiving();
			ItemStack to = event.getTo();
			ItemStack from = event.getFrom();
			EquipmentSlot slot = event.getSlot();
			if(slot == EquipmentSlot.MAINHAND) {
				int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HEAVY_BLADE.get(), to);
				if (level > 0)
				{
					double value = level * -0.1;
					if (value == -1) {
						value = -0.9;
					}
					PlayerAttributeHandler.addToAttributeBase(player, Attributes.ATTACK_SPEED, value, to);
				}
				level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HEAVY_BLADE.get(), from);
				if (level > 0) {
					PlayerAttributeHandler.removeAttribute(player, Attributes.ATTACK_SPEED, from);
				}

				level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.LIGHT_BLADE.get(), to);
				if (level > 0)
				{
					double value = level * 0.5;
					PlayerAttributeHandler.addToAttributeBase(player, Attributes.ATTACK_SPEED, value, to);
				}
				level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.LIGHT_BLADE.get(), from);
				if (level > 0) {
					PlayerAttributeHandler.removeAttribute(player, Attributes.ATTACK_SPEED, from);
				}
			}
		}
	}
}
