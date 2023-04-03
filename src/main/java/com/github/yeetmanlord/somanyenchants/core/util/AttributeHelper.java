package com.github.yeetmanlord.somanyenchants.core.util;

import java.util.UUID;

import javax.annotation.Nullable;

import com.github.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import net.minecraft.item.TridentItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;

public class AttributeHelper {

	public static void changeAttribute(ItemStack stack, Attribute attribute, double newAmount, String name, PlayerEntity player, @Nullable EquipmentSlotType slot) {

		if (stack.hasTag()) {

			if (stack.getTag().contains("AttributeModifiers")) {
				ListNBT attributes = stack.getTag().getList("AttributeModifiers", 10);

				for (int x = 0; x < attributes.size(); x++) {
					CompoundNBT nbt = attributes.getCompound(x);
					String s = nbt.getString("AttributeName");
					String n = nbt.getString("Name");

					if (s.matches(String.valueOf(attribute.getRegistryName())) && n.matches(name)) {
						nbt.putDouble("Amount", newAmount);
						stack.getTag().remove("AttributeModifiers");
						stack.getTag().put("AttributeModifiers", attributes);
						return;
					}

				}

			}

		}

		return;

	}

	public static void removeAttribute(ItemStack stack, Attribute attribute, String name) {

		if (stack.hasTag()) {

			if (stack.getTag().contains("AttributeModifiers")) {
				ListNBT attributes = stack.getTag().getList("AttributeModifiers", 10);

				for (int x = 0; x < attributes.size(); x++) {
					CompoundNBT nbt = attributes.getCompound(x);
					String s = nbt.getString("AttributeName");
					String n = nbt.getString("Name");

					if (s.matches(String.valueOf(attribute.getRegistryName())) && n.matches(name)) {
						attributes.remove(x);
					}

				}

			}

		}

		return;

	}

	public static void newAttribute(ItemStack stack, Attribute attribute, String name, double amount, boolean isBaseAttribute, PlayerEntity player, @Nullable EquipmentSlotType slot, UUID uuid) {

		if (stack.hasTag()) {

			if (stack.getTag().getList("AttributeModifiers", 10) != null) {
				ListNBT attributes = stack.getTag().getList("AttributeModifiers", 10);

				for (int x = 0; x < attributes.size(); x++) {
					CompoundNBT nbt = attributes.getCompound(x);
					String s = nbt.getString("AttributeName");
					String n = nbt.getString("Name");
					boolean flag = (isAttributePresent(Attributes.ARMOR, "Armor Modifier", stack) || isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", stack) || isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", stack) || isAttributePresent(Attributes.ATTACK_DAMAGE, "Mainhand Modifier", stack) || isAttributePresent(Attributes.ATTACK_SPEED, "Mainhand Modifier", stack));

					if (s.matches(String.valueOf(attribute.getRegistryName())) && n.matches(name)) {
						changeAttribute(stack, attribute, amount, name, player, slot);
						return;
					}

					if (flag) {

						if (s.matches(String.valueOf(Attributes.ARMOR.getRegistryName())) && n.matches("Armor Modifier")) {
							attributes.remove(x);
						}
						else if (s.matches(String.valueOf(Attributes.ARMOR_TOUGHNESS.getRegistryName())) && n.matches("Armor Modifier")) {
							attributes.remove(x);
						}
						else if (s.matches(String.valueOf(Attributes.ARMOR.getRegistryName())) && n.matches("Armor Modifier")) {
							attributes.remove(x);
						}
						else if (s.matches(String.valueOf(Attributes.KNOCKBACK_RESISTANCE.getRegistryName())) && n.matches("Armor Modifier")) {
							attributes.remove(x);
						}
						else if (s.matches(String.valueOf(Attributes.ATTACK_DAMAGE.getRegistryName())) && n.matches("Mainhand Modifier")) {
							attributes.remove(x);
						}
						else if (s.matches(String.valueOf(Attributes.ATTACK_SPEED.getRegistryName())) && n.matches("Mainhand Modifier")) {
							attributes.remove(x);
						}

						flag = (isAttributePresent(Attributes.ARMOR, "Armor Modifier", stack) || isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", stack) || isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", stack) || isAttributePresent(Attributes.ATTACK_DAMAGE, "Mainhand Modifier", stack) || isAttributePresent(Attributes.ATTACK_SPEED, "Mainhand Modifier", stack));
					}

				}

			}

		}

		if (!isBaseAttribute) {
			setBaseArmor(stack, stack.getEquipmentSlot(), uuid);
			stack.addAttributeModifier(attribute, new AttributeModifier(uuid, "Armor Modifier", amount, Operation.ADDITION), slot);
		}
		else {
			setBaseArmor(stack, slot, uuid);
			changeAttribute(stack, attribute, amount, name, player, slot);
		}

	}

	public static void setBaseArmor(ItemStack stack, EquipmentSlotType slotIn, UUID uuid) {

		if (stack.getItem() instanceof ArmorItem) {
			ArmorItem armor = (ArmorItem) stack.getItem();
			float baseArmor = armor.getMaterial().getDefenseForSlot(slotIn);
			float kbresistance = armor.getMaterial().getKnockbackResistance();
			float toughness = armor.getMaterial().getToughness();

			if (toughness > 0) {
				stack.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor Modifier", toughness, Operation.ADDITION), slotIn);
			}

			if (kbresistance > 0) {
				stack.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor Modifier", kbresistance, Operation.ADDITION), slotIn);
			}

			if (baseArmor > 0) {
				stack.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(uuid, "Armor Modifier", baseArmor, Operation.ADDITION), slotIn);
			}

		}

	}

	public static double getAttackDamage(Item item) {

		double attackDamage = 0.0D;

		if (item instanceof TieredItem || item instanceof TridentItem) {

			if (item instanceof HoeItem) {
				attackDamage = 0.0D;
			}
			else if (item instanceof PickaxeItem) {
				attackDamage = ((TieredItem) item).getTier().getAttackDamageBonus() + 1.0d;
			}
			else if (item instanceof ShovelItem) {
				attackDamage = ((TieredItem) item).getTier().getAttackDamageBonus() + 1.5d;
			}
			else if (item instanceof SwordItem) {
				attackDamage = ((SwordItem) item).getDamage();
			}
			else if (item instanceof AxeItem) {
				attackDamage = ((AxeItem) item).getAttackDamage();
			}
			else if (item instanceof TridentItem) {
				attackDamage = 8.0D;
			}
			else {
				return attackDamage;
			}

			return attackDamage;
		}

		return attackDamage;

	}

	public static double getAttackSpeed(Item item) {

		double attackSpeed = 0.0D;

		if (item instanceof TieredItem || item instanceof TridentItem) {

			if (item instanceof HoeItem) {
				attackSpeed = 0.0D;
			}
			else if (item instanceof PickaxeItem) {
				attackSpeed = -2.8D;
			}
			else if (item instanceof ShovelItem) {
				attackSpeed = -3.0D;
			}
			else if (item instanceof SwordItem) {
				attackSpeed = -2.4D;
			}
			else if (item instanceof AxeItem) {
				attackSpeed = -3.0D;
			}
			else if (item instanceof TridentItem) {
				attackSpeed = -2.9D;
			}
			else {
				return attackSpeed;
			}

			return attackSpeed;
		}
		else {
			return attackSpeed;
		}

	}

	public static boolean isAttributePresent(Attribute at, String name, ItemStack stack) {

		if (stack.hasTag()) {

			if (stack.getTag().contains("AttributeModifiers")) {
				ListNBT attributes = stack.getTag().getList("AttributeModifiers", 10);

				for (int x = 0; x < attributes.size(); x++) {
					CompoundNBT nbt = attributes.getCompound(x);
					String s = nbt.getString("AttributeName");
					String n = nbt.getString("Name");

					if (s.matches(String.valueOf(at.getRegistryName())) && n.matches(name)) {
						return true;
					}

				}

			}

		}

		return false;

	}

	public static boolean isAttributePresentExact(Attribute at, String name, ItemStack stack, double amount) {

		if (stack.hasTag()) {

			if (stack.getTag().contains("AttributeModifiers")) {
				ListNBT attributes = stack.getTag().getList("AttributeModifiers", 10);

				for (int x = 0; x < attributes.size(); x++) {
					CompoundNBT nbt = attributes.getCompound(x);
					String s = nbt.getString("AttributeName");
					String n = nbt.getString("Name");
					double value = nbt.getDouble("Amount");

					if (s.matches(String.valueOf(at.getRegistryName())) && n.matches(name) && MathUtils.roundNearestPlace(value, -1) == MathUtils.roundNearestPlace(amount, -1)) {
						return true;
					}

				}

			}

		}

		return false;

	}

	public static double getStrengthEffect(PlayerEntity player) {

		EffectInstance effect = player.getEffect(Effects.DAMAGE_BOOST);

		if (effect != null) {
			return effect.getAmplifier() * 3 + 3;
		}

		return 0;

	}

	public static void apply(Enchantment ench, Attribute attr, EnchantmentConfig config, final LivingEquipmentChangeEvent event, double multiplier) {

		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		ItemStack to = event.getTo();
		ItemStack from = event.getFrom();

		if (config.isEnabled.get()) {
			boolean flag1 = ModEnchantmentHelper.hasEnchant(ench, to);
			boolean flag2 = ModEnchantmentHelper.hasEnchant(ench, from);
			int level = ModEnchantmentHelper.getEnchantmentLevel(ench, to);
			int level1 = ModEnchantmentHelper.getEnchantmentLevel(ench, from);

			if (flag1 && level1 != level) {
				if (attr == Attributes.ATTACK_SPEED) {
					double value = level * multiplier;
					if (value <= -1) {
						value = -0.9d;
					}
					PlayerAttributeHandler.addToAttributeBase(player, attr, value, to);
				}
				else {
					PlayerAttributeHandler.addToAttributeBase(player, attr, level * multiplier, to);
				}
			}
			else if (flag2 && level1 != level) {
				PlayerAttributeHandler.removeAttribute(player, attr, from);
			}

		}

	}

}
