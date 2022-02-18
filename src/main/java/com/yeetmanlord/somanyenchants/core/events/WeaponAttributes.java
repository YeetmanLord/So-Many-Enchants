package com.yeetmanlord.somanyenchants.core.events;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;
import com.yeetmanlord.somanyenchants.core.util.PlayerAttributeHandler;

import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class WeaponAttributes
{

	@SubscribeEvent
	public static void mainhand(final LivingEquipmentChangeEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)event.getEntityLiving();
			ItemStack to = event.getTo();
			ItemStack from = event.getFrom();
			EquipmentSlotType slot = event.getSlot();
			if(slot == EquipmentSlotType.MAINHAND) {
				int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HEAVY_BLADE.get(), to);
				if (level > 0)
				{
					double value = level * -0.1;
					if (value == -1) {
						value = -0.9;
					}
					PlayerAttributeHandler.addToAttributeBase(player, Attributes.ATTACK_SPEED, value, to);
					return;
				}
				level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HEAVY_BLADE.get(), from);
				if (level > 0) {
					PlayerAttributeHandler.removeAttribute(player, Attributes.ATTACK_SPEED, from);
					return;
				}

				level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.LIGHT_BLADE.get(), to);
				if (level > 0)
				{
					double value = level * 0.5;
					PlayerAttributeHandler.addToAttributeBase(player, Attributes.ATTACK_SPEED, value, to);
					return;
				}
				level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.LIGHT_BLADE.get(), from);
				if (level > 0) {
					PlayerAttributeHandler.removeAttribute(player, Attributes.ATTACK_SPEED, from);
					return;
				}
			}
		}
	}
}
