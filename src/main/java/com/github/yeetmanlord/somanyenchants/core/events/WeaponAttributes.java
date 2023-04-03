package com.github.yeetmanlord.somanyenchants.core.events;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.github.yeetmanlord.somanyenchants.core.util.AttributeHelper;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class WeaponAttributes {

	@SubscribeEvent
	public static void mainhand(final LivingEquipmentChangeEvent event) {

		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			EquipmentSlot slot = event.getSlot();

			if (slot == EquipmentSlot.MAINHAND) {

				AttributeHelper.apply(EnchantmentInit.HEAVY_BLADE.get(), Attributes.ATTACK_SPEED, Config.heavyBlade, event, -0.1d);
				AttributeHelper.apply(EnchantmentInit.LIGHT_BLADE.get(), Attributes.ATTACK_SPEED, Config.lightBlade, event, 0.5d);

			}

		}

	}

}
