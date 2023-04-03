package com.github.yeetmanlord.somanyenchants.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.init.AttributeInit;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

public class PlayerAttributeHandler {

	public static final UUID ATTACK_REACH_UUID = UUID.fromString("0ed05f3b-bb77-42e5-9dee-16c9a2e909f9");

	public static final UUID FORGE_REACH_UUID = UUID.fromString("5d445e83-f619-4e3b-8013-236ff0e4154c");

	protected static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

	protected static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

	public static void addToAttributeBase(Player player, Attribute attr, double addon, ItemStack stack) {

		NBTHelper.writeAttributeValue(stack, addon, attr);
		player.getAttributes().getInstance(attr).setBaseValue(player.getAttributeBaseValue(attr) + addon);

	}

	public static void removeAttribute(Player player, Attribute attr, ItemStack stack) {

		double value = NBTHelper.getAttributeValue(stack, attr);
		player.getAttributes().getInstance(attr).setBaseValue(player.getAttributeBaseValue(attr) - value);

		if (attr == Attributes.MAX_HEALTH) {

			if (player.getHealth() > player.getAttributeBaseValue(attr)) {
				player.setHealth((float) (player.getHealth() - player.getAttributeBaseValue(attr)));
			}

		}

	}

	public static void reset(Player player) {

		Attribute[] attr = new Attribute[] { Attributes.ARMOR, Attributes.ARMOR_TOUGHNESS, Attributes.ATTACK_SPEED, Attributes.MAX_HEALTH, Attributes.KNOCKBACK_RESISTANCE, AttributeInit.ATTACK_DISTANCE.get(), ForgeMod.REACH_DISTANCE.get() };

		for (ItemStack armorPiece : player.inventory.armor) {

			for (Attribute x : attr) {
				removeAttribute(player, x, armorPiece);
			}

		}

		for (Attribute x : attr) {
			removeAttribute(player, x, player.getMainHandItem());
		}

	}

	@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
	public static class TooltipUpdate {

		@SubscribeEvent(priority = EventPriority.HIGHEST)
		public static void updateTooltip(final ItemTooltipEvent event) {

			ItemStack stack = event.getItemStack();
			Player playerIn = event.getEntity();
			List<Component> tooltips = event.getToolTip();
			HashMap<Component, Integer> map = new HashMap<>();

			for (int x = 0; x < tooltips.size(); x++) {
				map.put(tooltips.get(x), x);
			}

			List<Component> list = new ArrayList<>();
			List<Component> list1 = new ArrayList<>();
			List<Component> list2 = new ArrayList<>();

			int i = getHideFlags(stack);

			if (shouldShowInTooltip(i, ItemStack.TooltipPart.MODIFIERS)) {

				for (EquipmentSlot equipmentslottype : EquipmentSlot.values()) {
					Multimap<Attribute, AttributeModifier> multimap = stack.getAttributeModifiers(equipmentslottype);

					if (!multimap.isEmpty()) {

						for (Entry<Attribute, AttributeModifier> entry : multimap.entries()) {
							AttributeModifier attributemodifier = entry.getValue();
							double d0 = attributemodifier.getAmount();
							boolean flag = false;

							if (playerIn != null) {

								if (attributemodifier.getId().equals(ATTACK_DAMAGE_MODIFIER)) {
									d0 = d0 + playerIn.getAttributeBaseValue(Attributes.ATTACK_DAMAGE);
									d0 = d0 + (double) EnchantmentHelper.getDamageBonus(stack, MobType.UNDEFINED);
									flag = true;
								}
								else if (attributemodifier.getId().equals(ATTACK_SPEED_MODIFIER)) {
									d0 += playerIn.getAttributeBaseValue(Attributes.ATTACK_SPEED);
									flag = true;
								}

							}

							double d1;

							if (attributemodifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {

								if (entry.getKey().equals(Attributes.KNOCKBACK_RESISTANCE)) {
									d1 = d0 * 10.0D;
								}
								else {
									d1 = d0;
								}

							}
							else {
								d1 = d0 * 100.0D;
							}

							if (flag) {
								list.add((Component.literal(" ")).append(Component.translatable("attribute.modifier.equals." + attributemodifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), Component.translatable(entry.getKey().getDescriptionId()))).withStyle(ChatFormatting.DARK_GREEN));
							}
							else if (d0 > 0.0D) {
								list1.add((Component.translatable("attribute.modifier.plus." + attributemodifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), Component.translatable(entry.getKey().getDescriptionId()))).withStyle(ChatFormatting.BLUE));
							}
							else if (d0 < 0.0D) {
								d1 = d1 * -1.0D;
								list1.add((Component.translatable("attribute.modifier.take." + attributemodifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), Component.translatable(entry.getKey().getDescriptionId()))).withStyle(ChatFormatting.RED));
							}

						}

					}

				}

				if (list.size() > 0) {
					Integer x = map.get(list.get(list.size() - 1));

					if (x != null) {

						if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.ATTACK_REACH.get(), stack)) {
							event.getToolTip().add(x, (Component.literal(" " + String.valueOf(ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(playerIn.getAttributeBaseValue(AttributeInit.ATTACK_DISTANCE.get()))) + " ").append(Component.translatable(AttributeInit.ATTACK_DISTANCE.get().getDescriptionId())).withStyle(ChatFormatting.DARK_GREEN)));
						}

						if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.BLOCK_REACH.get(), stack)) {
							event.getToolTip().add(x, (Component.literal(" " + String.valueOf(ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(playerIn.getAttributeBaseValue(ForgeMod.REACH_DISTANCE.get()))) + " Block reach distance")).withStyle(ChatFormatting.DARK_GREEN));
						}

					}

				}

				else if (list1.size() > 0) {

					if (stack.getItem() instanceof ArmorItem) {

						for (EquipmentSlot equipmentslottype : EquipmentSlot.values()) {
							Multimap<Attribute, AttributeModifier> multimap = stack.getAttributeModifiers(equipmentslottype);

							if (!multimap.isEmpty()) {

								for (Entry<Attribute, AttributeModifier> entry : multimap.entries()) {
									AttributeModifier attributemodifier = entry.getValue();
									double d0 = attributemodifier.getAmount();
									boolean flag = false;

									if (playerIn != null) {

										if (attributemodifier.getId().equals(ATTACK_DAMAGE_MODIFIER)) {
											d0 = d0 + playerIn.getAttributeBaseValue(Attributes.ATTACK_DAMAGE);
											d0 = d0 + (double) EnchantmentHelper.getDamageBonus(stack, MobType.UNDEFINED);
											flag = true;
										}
										else if (attributemodifier.getId().equals(ATTACK_SPEED_MODIFIER)) {
											d0 += playerIn.getAttributeBaseValue(Attributes.ATTACK_SPEED);
											flag = true;
										}

									}

									double d1;

									if (attributemodifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {

										if (entry.getKey().equals(Attributes.KNOCKBACK_RESISTANCE)) {
											d1 = d0 * 10.0D;
										}
										else {
											d1 = d0;
										}

									}
									else {
										d1 = d0 * 100.0D;
									}

									double addon = 0d;

									if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.HEAVY_ARMOR.get(), stack) && entry.getKey().getDescriptionId().equals(Attributes.KNOCKBACK_RESISTANCE.getDescriptionId())) {
										addon += ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HEAVY_ARMOR.get(), stack);
									}
									else if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.TEMPERED_ARMOR.get(), stack) && entry.getKey().getDescriptionId().equals(Attributes.ARMOR_TOUGHNESS.getDescriptionId())) {
										addon += ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.TEMPERED_ARMOR.get(), stack);
									}
									else if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.REINFORCED_ARMOR.get(), stack) && entry.getKey().getDescriptionId().equals(Attributes.ARMOR.getDescriptionId())) {
										addon += ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REINFORCED_ARMOR.get(), stack) * 2d;
									}

									if (d0 > 0.0D && !flag) {
										list2.add((Component.translatable("attribute.modifier.plus." + attributemodifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1 + addon), Component.translatable(entry.getKey().getDescriptionId()))).withStyle(ChatFormatting.BLUE));
									}
									else if (d0 < 0.0D && !flag) {
										d1 = d1 * -1.0D;
										list2.add((Component.translatable("attribute.modifier.take." + attributemodifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1 + addon), Component.translatable(entry.getKey().getDescriptionId()))).withStyle(ChatFormatting.RED));
									}

								}

							}

						}

						int y = 0;

						for (Component x : list1) {
							Integer z = map.get(x);

							if (z != null) {
								event.getToolTip().set(z, list2.get(y));
							}

							y++;
						}

						Integer z = map.get(list1.get(list1.size() - 1));
						int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HEALTH_BOOST.get(), stack);

						if (level > 0 && z != null) {
							event.getToolTip().add(z, (Component.translatable("attribute.modifier.plus.0", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(level * 2d), Component.translatable(Attributes.MAX_HEALTH.getDescriptionId()))).withStyle(ChatFormatting.BLUE));
						}

					}

				}

			}

		}

	}

	@OnlyIn(Dist.CLIENT)
	private static boolean shouldShowInTooltip(int p_242394_0_, ItemStack.TooltipPart p_242394_1_) {

		return (p_242394_0_ & p_242394_1_.getMask()) == 0;

	}

	@OnlyIn(Dist.CLIENT)
	private static int getHideFlags(ItemStack stack) {

		return stack.hasTag() && stack.getTag().contains("HideFlags", 99) ? stack.getTag().getInt("HideFlags") : 0;

	}

}
