package com.yeetmanlord.somanyenchants.core.util;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
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
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class AttributeHelper
{
	public static void changeAttribute(ItemStack stack, Attribute attribute, double newAmount, String name, boolean updateLore, boolean isCustom, PlayerEntity player, @Nullable EquipmentSlotType slot)
	{
		if(stack.hasTag())
		{
			if(stack.getTag().contains("AttributeModifiers"))
			{
				ListNBT attributes = stack.getTag().getList("AttributeModifiers", 10);
				for(int x = 0; x < attributes.size(); x++)
				{
					CompoundNBT nbt = attributes.getCompound(x);
					String s = nbt.getString("AttributeName");
					String n = nbt.getString("Name");
					
					if(s.matches(String.valueOf(attribute.getRegistryName())) && n.matches(name))
					{
						nbt.putDouble("Amount", newAmount);
						stack.getTag().remove("AttributeModifiers");
						stack.getTag().put("AttributeModifiers", attributes);
						if(updateLore && !isCustom)
						{
							NBTHelper.renderAttributeLore(player);
						} else if(updateLore && isCustom)
						{
							NBTHelper.renderCustomAttributeLore(player, newAmount, attribute, name);
						}
						return;
					}
				}
			}
		}
		return;
	}
	
	public static void removeAttribute(ItemStack stack, Attribute attribute, String name, boolean isCustom, boolean updateLore)
	{
		if(stack.hasTag())
		{
			if(stack.getTag().contains("AttributeModifiers"))
			{
				ListNBT attributes = stack.getTag().getList("AttributeModifiers", 10);
				for(int x = 0; x < attributes.size(); x++)
				{
					CompoundNBT nbt = attributes.getCompound(x);
					String s = nbt.getString("AttributeName");
					String n = nbt.getString("Name");
					
					if(s.matches(String.valueOf(attribute.getRegistryName())) && n.matches(name))
					{
						if(isCustom & updateLore)
						{
							NBTHelper.removeCustomAttributeLore(stack, name);
						}
						attributes.remove(x);
					}
				}
			}
		}
		return;
	}
	
	public static void newAttribute(ItemStack stack, Attribute attribute, String name, double amount, boolean isArmor, boolean isWeapon, boolean isBaseAttribute, PlayerEntity player, @Nullable EquipmentSlotType slot, UUID uuid)
	{
		if(stack.hasTag())
		{
			if(stack.getTag().getList("AttributeModifiers", 10) != null)
			{
				ListNBT attributes = stack.getTag().getList("AttributeModifiers", 10);
				for(int x = 0; x < attributes.size(); x++)
				{
					CompoundNBT nbt = attributes.getCompound(x);
					String s = nbt.getString("AttributeName");
					String n = nbt.getString("Name");
					boolean flag = (isAttributePresent(Attributes.ARMOR, "Armor Modifier", stack) || isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", stack) 
							|| isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", stack) || isAttributePresent(Attributes.ATTACK_DAMAGE, "Mainhand Modifier", stack) || isAttributePresent(Attributes.ATTACK_SPEED, "Mainhand Modifier", stack));
					if(s.matches(String.valueOf(attribute.getRegistryName())) && n.matches(name))
					{
						changeAttribute(stack, attribute, amount, name, isWeapon && !isArmor, isBaseAttribute == false, player, slot);
						return;
					}
					
					if(flag)
					{
						if(s.matches(String.valueOf(Attributes.ARMOR.getRegistryName())) && n.matches("Armor Modifier"))
						{
							attributes.remove(x);
						}
						else if(s.matches(String.valueOf(Attributes.ARMOR_TOUGHNESS.getRegistryName())) && n.matches("Armor Modifier"))
						{
							attributes.remove(x);
						} else if(s.matches(String.valueOf(Attributes.ARMOR.getRegistryName())) && n.matches("Armor Modifier"))
						{
							attributes.remove(x);
						} else if(s.matches(String.valueOf(Attributes.KNOCKBACK_RESISTANCE.getRegistryName())) && n.matches("Armor Modifier"))
						{
							attributes.remove(x);
						} else if(s.matches(String.valueOf(Attributes.ATTACK_DAMAGE.getRegistryName())) && n.matches("Mainhand Modifier"))
						{
							attributes.remove(x);
						} else if(s.matches(String.valueOf(Attributes.ATTACK_SPEED.getRegistryName())) && n.matches("Mainhand Modifier"))
						{
							attributes.remove(x);
						}
						flag = (isAttributePresent(Attributes.ARMOR, "Armor Modifier", stack) || isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", stack) 
								|| isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", stack) || isAttributePresent(Attributes.ATTACK_DAMAGE, "Mainhand Modifier", stack) || isAttributePresent(Attributes.ATTACK_SPEED, "Mainhand Modifier", stack));
					}
				}
			}
		}
			if(isArmor && !isBaseAttribute && !isWeapon)
			{
				setBaseArmor(stack, stack.getEquipmentSlot(), uuid);
				stack.addAttributeModifier(attribute, new AttributeModifier(uuid, "Armor Modifier", amount, Operation.ADDITION), slot);
			} else if(isWeapon && !isBaseAttribute && !isArmor)
			{
				setBaseWeapon(stack, player);
				stack.addAttributeModifier(attribute, new AttributeModifier(uuid, "Mainhand Modifier", amount, Operation.ADDITION), EquipmentSlotType.MAINHAND);
				NBTHelper.renderCustomAttributeLore(player, amount, attribute, name);
			} else if(isArmor && isWeapon)
			{
				return;
			} if(isArmor && isBaseAttribute && !isWeapon)
			{
				setBaseArmor(stack, slot, uuid);
				changeAttribute(stack, attribute, amount, name, false, false, player, slot);
			} else if(isWeapon && isBaseAttribute && !isArmor)
			{
				setBaseWeapon(stack, player);
				changeAttribute(stack, attribute, amount, name, true, false, player, slot);
			}
	}

	public static void setBaseArmor(ItemStack stack, EquipmentSlotType slotIn, UUID uuid) 
	{
		if(stack.getItem() instanceof ArmorItem)
		{
			ArmorItem armor = (ArmorItem)stack.getItem();
			float baseArmor = armor.getArmorMaterial().getDamageReductionAmount(slotIn);
			float kbresistance = armor.getArmorMaterial().getKnockbackResistance();
			float toughness = armor.getArmorMaterial().getToughness();
			if(toughness > 0)
			{
				stack.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor Modifier", toughness, Operation.ADDITION), slotIn);
			}
			if(kbresistance > 0)
			{
				stack.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor Modifier", kbresistance, Operation.ADDITION), slotIn);
			}
			if(baseArmor > 0)
			{
				stack.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(uuid, "Armor Modifier", baseArmor, Operation.ADDITION), slotIn);
			}
		}
	}
	
	public static void setBaseWeapon(ItemStack stack, PlayerEntity player)
	{
		final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
		final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
		if(stack.getItem() instanceof TieredItem || stack.getItem() instanceof TridentItem)
		{
			double damage = getAttackDamage(stack.getItem());
			double speed = getAttackSpeed(stack.getItem());
			if(damage > 0)
			{
				stack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Mainhand Modifier", damage, Operation.ADDITION), EquipmentSlotType.MAINHAND);
			}
			
			stack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Mainhand Modifier", speed, Operation.ADDITION), EquipmentSlotType.MAINHAND);
			
			NBTHelper.renderAttributeLore(player);
		}
	}
	
	public static double getAttackDamage(Item item)
	{
		double attackDamage = 0.0D;
		if(item instanceof TieredItem || item instanceof TridentItem)
		{
			if(item instanceof HoeItem)
			{
				attackDamage = 0.0D;
			} else if(item instanceof PickaxeItem)
			{
				attackDamage = ((TieredItem) item).getTier().getAttackDamage() + 1.0d;
			} else if(item instanceof ShovelItem)
			{
				attackDamage = ((TieredItem) item).getTier().getAttackDamage() + 1.5d;
			} else if(item instanceof SwordItem)
			{
				attackDamage = ((SwordItem) item).getAttackDamage();
			} else if(item instanceof AxeItem)
			{
				attackDamage = ((AxeItem)item).getAttackDamage();
			} else if(item instanceof TridentItem)
			{
				attackDamage = 8.0D;
			} else 
			{
				return attackDamage;
			}
			return attackDamage;
		} 
		return attackDamage;
	}
	
	public static double getAttackSpeed(Item item)
	{
		double attackSpeed = 0.0D;
		if(item instanceof TieredItem || item instanceof TridentItem)
		{
			if(item instanceof HoeItem)
			{
				attackSpeed = 0.0D;
			} else if(item instanceof PickaxeItem)
			{
				attackSpeed = -2.8D;
			} else if(item instanceof ShovelItem)
			{
				attackSpeed = -3.0D;
			} else if(item instanceof SwordItem)
			{
				attackSpeed = -2.4D;
			} else if(item instanceof AxeItem)
			{
				attackSpeed = -3.0D;
			} else if(item instanceof TridentItem)
			{
				attackSpeed = -2.9D;
			} else 
			{
				return attackSpeed;
			}
			return attackSpeed;
		} else
		{
			return attackSpeed;
		}
	}
	
	public static boolean isAttributePresent(Attribute at, String name, ItemStack stack)
	{
		if(stack.hasTag())
		{
			if(stack.getTag().contains("AttributeModifiers"))
			{
				ListNBT attributes = stack.getTag().getList("AttributeModifiers", 10);
				for(int x = 0; x < attributes.size(); x++)
				{
					CompoundNBT nbt = attributes.getCompound(x);
					String s = nbt.getString("AttributeName");
					String n = nbt.getString("Name");
					
					if(s.matches(String.valueOf(at.getRegistryName())) && n.matches(name))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean isAttributePresentExact(Attribute at, String name, ItemStack stack, double amount)
	{
		if(stack.hasTag())
		{
			if(stack.getTag().contains("AttributeModifiers"))
			{
				ListNBT attributes = stack.getTag().getList("AttributeModifiers", 10);
				for(int x = 0; x < attributes.size(); x++)
				{
					CompoundNBT nbt = attributes.getCompound(x);
					String s = nbt.getString("AttributeName");
					String n = nbt.getString("Name");
					double value = nbt.getDouble("Amount");
					
					if(s.matches(String.valueOf(at.getRegistryName())) && n.matches(name) && MathUtils.roundNearestPlace(value, -1) == MathUtils.roundNearestPlace(amount, -1))
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	public static double getStrengthEffect(PlayerEntity player)
	{
		EffectInstance effect = player.getActivePotionEffect(Effects.STRENGTH);
		if(effect != null)
		{
			return effect.getAmplifier() * 3 + 3;
		}
		return 0;
	}
}
