package com.yeetmanlord.somanyenchants.core.events;

import java.util.UUID;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.yeetmanlord.somanyenchants.core.util.AttributeHelper;
import com.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;
import com.yeetmanlord.somanyenchants.core.util.NBTHelper;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class WeaponAttributes
{
	public static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
	public static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

	@SubscribeEvent
	public static void mainhand(final LivingEquipmentChangeEvent event)
	{

		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();

			if (event.getSlot() == EquipmentSlotType.MAINHAND && player.getHeldItemMainhand() != null)
			{
				ItemStack stack = player.getHeldItemMainhand();
				applyAttribute(player, stack, EnchantmentInit.HEAVY_BLADE.get(), Attributes.ATTACK_SPEED, -0.2,
						AttributeHelper.getAttackSpeed(stack.getItem()), ATTACK_SPEED_MODIFIER, "Mainhand Modifier",
						false, Config.heavyBlade);
			}

		}

	}

	public static void applyAttribute(PlayerEntity player, ItemStack stack, Enchantment enchant, Attribute attrib,
			double value, double staticVal, UUID id, String name, boolean isCustom, EnchantmentConfig config)
	{
		int level = ModEnchantmentHelper.getEnchantmentLevel(enchant, stack);
		Item item = stack.getItem();

		if (level > 0 && config.isEnabled.get())
		{
			double amount = level * value + staticVal;

			if (AttributeHelper.isAttributePresentExact(attrib, name, stack, amount))
			{
				return;
			}
			else if (AttributeHelper.isAttributePresent(attrib, name, stack))
			{
				AttributeHelper.removeAttribute(stack, attrib, name, isCustom, false);
			}

			if (attrib == Attributes.ATTACK_SPEED)
			{

				if (amount <= -1)
				{
					amount = -0.9;
				}

				stack.addAttributeModifier(attrib, new AttributeModifier(id, name, amount, Operation.ADDITION),
						EquipmentSlotType.MAINHAND);
				stack.addAttributeModifier(Attributes.ATTACK_DAMAGE,
						new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Mainhand modifier",
								NBTHelper.getAttackDamage(player) - NBTHelper.getSharpnessDamage(player),
								Operation.ADDITION),
						EquipmentSlotType.MAINHAND);
				NBTHelper.renderAttributeLore(player);
			}
			else if (attrib == Attributes.ATTACK_DAMAGE)
			{
				stack.addAttributeModifier(attrib, new AttributeModifier(id, name, amount, Operation.ADDITION),
						EquipmentSlotType.MAINHAND);
				stack.addAttributeModifier(Attributes.ATTACK_SPEED,
						new AttributeModifier(ATTACK_SPEED_MODIFIER, "Mainhand modifier",
								-player.getBaseAttributeValue(Attributes.ATTACK_SPEED)
										+ NBTHelper.getAttackSpeed(player),
								Operation.ADDITION),
						EquipmentSlotType.MAINHAND);
				NBTHelper.renderAttributeLore(player);
			}
			else
			{
				stack.addAttributeModifier(attrib, new AttributeModifier(id, name, amount, Operation.ADDITION),
						EquipmentSlotType.MAINHAND);
				double atSpeed = player.getBaseAttributeValue(Attributes.ATTACK_SPEED);
				stack.addAttributeModifier(Attributes.ATTACK_DAMAGE,
						new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Mainhand modifier",
								NBTHelper.getAttackDamage(player) - NBTHelper.getSharpnessDamage(player),
								Operation.ADDITION),
						EquipmentSlotType.MAINHAND);
				stack.addAttributeModifier(Attributes.ATTACK_SPEED,
						new AttributeModifier(ATTACK_SPEED_MODIFIER, "Mainhand modifier",
								-player.getBaseAttributeValue(Attributes.ATTACK_SPEED)
										+ NBTHelper.getAttackSpeed(player),
								Operation.ADDITION),
						EquipmentSlotType.MAINHAND);
				NBTHelper.renderCustomAttributeLore(player, amount, attrib, name);
			}

		}
		else
		{
			AttributeHelper.removeAttribute(stack, attrib, name, isCustom, true);
			AttributeHelper.setBaseWeapon(stack, player);
		}

	}
}
