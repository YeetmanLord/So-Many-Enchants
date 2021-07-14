package com.yeetmanlord.enchantsplus.core.events;

import java.util.UUID;

import com.yeetmanlord.enchantsplus.Main;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class HealthBoost 
{
	@SuppressWarnings({ "static-access" })
	@SubscribeEvent
	public static void armorHPBoost(final LivingEquipmentChangeEvent event)
	{
		LivingEntity entity = event.getEntityLiving();
			if (entity instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity) entity;
				boolean enchant = false;
				boolean enchantChest = false;
				boolean enchantLegs = false;
				boolean enchantFeet = false;
				if(player.inventory.armorItemInSlot(3).isEnchanted())
				{
					ListNBT tagList = player.inventory.armorItemInSlot(3).getEnchantmentTagList();
					for(int x = 0; x < tagList.size(); x++)
					{
							CompoundNBT idTag = tagList.getCompound(x);
								if(idTag.getString("id").matches("enchants_plus:health_boost"))
								{
									short enchantLvl = idTag.getShort("lvl");
									ItemStack armor = player.inventory.armorItemInSlot(3);
									 if(enchantLvl >= 1 && enchantLvl <= 6) {
										 armor.addAttributeModifier(new Attributes().MAX_HEALTH, new AttributeModifier(new UUID(3, 2^64), "generic.max_health", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD);
										 if(armor.getItem() == Items.NETHERITE_HELMET) {
											 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(3, 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
											 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(3, 2^64), "generic.kb_resistance", 0.1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
		
										 } else if(armor.getItem() == Items.DIAMOND_HELMET)
										 {
											 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(3, 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
										 } else if(armor.getItem() == Items.GOLDEN_HELMET || armor.getItem() == Items.IRON_HELMET || armor.getItem() == Items.TURTLE_HELMET 
												 || armor.getItem() == Items.CHAINMAIL_HELMET)
										 {
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
										 } else if(armor.getItem() == Items.LEATHER_HELMET)
										 {
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
										 }
										 enchant = true;
										 
								} else if (enchantLvl > 6) {
									player.inventory.armorItemInSlot(3).addAttributeModifier(new Attributes().MAX_HEALTH, new AttributeModifier(new UUID(3, 2^64), "generic.max_health", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD);
									if(armor.getItem() == Items.NETHERITE_HELMET) {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(3, 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
										 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(3, 2^64), "generic.kb_resistance", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
		
									 } else if(armor.getItem() == Items.DIAMOND_HELMET)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(3, 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
									 } else if(armor.getItem() == Items.GOLDEN_HELMET || armor.getItem() == Items.IRON_HELMET || armor.getItem() == Items.TURTLE_HELMET 
											 || armor.getItem() == Items.CHAINMAIL_HELMET)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
									 } else if(armor.getItem() == Items.LEATHER_HELMET)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
									 }
									enchant = true;
								}
							} else if(enchant == false)
							{
									ListNBT atList = player.inventory.armorItemInSlot(3).getTag().getList("AttributeModifiers", 10);
									for(int z = 0; z < atList.size(); z++)
									{
											CompoundNBT atTag = atList.getCompound(z);
											if(atTag.getString("AttributeName") == "minecraft:generic.max_health" && atTag.getString("AttributeName") != "minecraft:generic.armor" && atTag.getString("AttributeName") != "minecraft:generic.armor_toughness" && atTag.getString("AttributeName") != "minecraft:generic.knockback_resistance");
											{
												ItemStack armor = player.inventory.armorItemInSlot(3);
												atList.clear();
												if(armor.getItem() == Items.NETHERITE_HELMET) {
													 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(3, 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
													 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(3, 2^64), "generic.kb_resistance", 0.1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
			
												 } else if(armor.getItem() == Items.DIAMOND_HELMET)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(3, 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
												 } else if(armor.getItem() == Items.GOLDEN_HELMET || armor.getItem() == Items.IRON_HELMET || armor.getItem() == Items.TURTLE_HELMET 
														 || armor.getItem() == Items.CHAINMAIL_HELMET)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
												 } else if(armor.getItem() == Items.LEATHER_HELMET)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
												 }
											}
										}
									}
								}
							}	else {
							 player.inventory.armorItemInSlot(3).addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 0d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
							 boolean init = player.inventory.armorItemInSlot(3).getTag().getList("AttributeModifiers", 10).isEmpty();
							 if(!init) {
								 player.inventory.armorItemInSlot(3).addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 0d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
								 init = true;
							 }
							ListNBT tagList = player.inventory.armorItemInSlot(3).getTag().getList("AttributeModifiers", 10);
							for(int x = 0; x < tagList.size(); x++)
							{
								CompoundNBT idTag = tagList.getCompound(x);
								if(idTag.getString("Name") == "generic.max_health"&& idTag.getString("AttributeName") != "minecraft:generic.armor" && idTag.getString("AttributeName") != "minecraft:generic.armor_toughness" && idTag.getString("AttributeName") != "minecraft:generic.knockback_resistance");
								{
									ItemStack armor = player.inventory.armorItemInSlot(3);
									tagList.clear();
									if(armor.getItem() == Items.NETHERITE_HELMET) {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(3, 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
										 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(3, 2^64), "generic.kb_resistance", 0.1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
			
									 } else if(armor.getItem() == Items.DIAMOND_HELMET)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(3, 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
									 } else if(armor.getItem() == Items.GOLDEN_HELMET || armor.getItem() == Items.IRON_HELMET || armor.getItem() == Items.TURTLE_HELMET 
											 || armor.getItem() == Items.CHAINMAIL_HELMET)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
									 } else if(armor.getItem() == Items.LEATHER_HELMET)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(3, 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.HEAD); 
									 }
									enchant = false;
							}
						}
					}
			
				if(player.inventory.armorItemInSlot(2).isEnchanted())
				{
					ListNBT tagList = player.inventory.armorItemInSlot(2).getEnchantmentTagList();
					for(int x = 0; x < tagList.size(); x++)
					{
							CompoundNBT idTag = tagList.getCompound(x);
								if(idTag.getString("id").matches("enchants_plus:health_boost"))
								{
									short enchantLvl = idTag.getShort("lvl");
									ItemStack armor = player.inventory.armorItemInSlot(2);
									 if(enchantLvl >= 1 && enchantLvl <= 6) {
										 armor.addAttributeModifier(new Attributes().MAX_HEALTH, new AttributeModifier(new UUID(2, 2^64), "generic.max_health", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST);
										 if(armor.getItem() == Items.NETHERITE_CHESTPLATE) {
											 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2, 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 8d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
											 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(2, 2^64), "generic.kb_resistance", 0.1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
										 } else if(armor.getItem() == Items.DIAMOND_CHESTPLATE)
										 {
											 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2, 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 8d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
										 } else if(armor.getItem() == Items.GOLDEN_CHESTPLATE || armor.getItem() == Items.CHAINMAIL_CHESTPLATE)
										 {
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 5d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
										 } else if(armor.getItem() == Items.LEATHER_CHESTPLATE)
										 {
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
										 } else if (armor.getItem() == Items.IRON_CHESTPLATE)
										 {
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 6d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
										 }
										 enchant = true;
										 
								} else if (enchantLvl > 6) {
									player.inventory.armorItemInSlot(2).addAttributeModifier(new Attributes().MAX_HEALTH, new AttributeModifier(new UUID(2, 2^64), "generic.max_health", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST);
									if(armor.getItem() == Items.NETHERITE_CHESTPLATE) {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2, 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 8d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
										 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(2, 2^64), "generic.kb_resistance", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
		
									 } else if(armor.getItem() == Items.DIAMOND_CHESTPLATE)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2, 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 8d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
									 } else if(armor.getItem() == Items.GOLDEN_CHESTPLATE || armor.getItem() == Items.CHAINMAIL_CHESTPLATE)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 5d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
									 } else if(armor.getItem() == Items.LEATHER_CHESTPLATE)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
									 } else if (armor.getItem() == Items.IRON_CHESTPLATE)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 6d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
									 }
									enchant = true;
								}
							} else if(enchant == false)
							{
									ListNBT atList = player.inventory.armorItemInSlot(2).getTag().getList("AttributeModifiers", 10);
									for(int z = 0; z < atList.size(); z++)
									{
											CompoundNBT atTag = atList.getCompound(z);
											if(atTag.getString("AttributeName") == "minecraft:generic.max_health" && atTag.getString("AttributeName") != "minecraft:generic.armor" && atTag.getString("AttributeName") != "minecraft:generic.armor_toughness" && atTag.getString("AttributeName") != "minecraft:generic.knockback_resistance");
											{
												ItemStack armor = player.inventory.armorItemInSlot(2);
												atList.clear();
												if(armor.getItem() == Items.NETHERITE_CHESTPLATE) {
													 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2, 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 8d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
													 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(2, 2^64), "generic.kb_resistance", 0.1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
			
												 } else if(armor.getItem() == Items.DIAMOND_CHESTPLATE)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2, 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 8d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
												 } else if(armor.getItem() == Items.GOLDEN_CHESTPLATE || armor.getItem() == Items.CHAINMAIL_CHESTPLATE)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 5d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
												 } else if(armor.getItem() == Items.LEATHER_CHESTPLATE)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
												 } else if (armor.getItem() == Items.IRON_CHESTPLATE)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 6d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
												 }
											}
										}
									}
								}
							}	else {
							 player.inventory.armorItemInSlot(2).addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 0d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 

							 boolean init = player.inventory.armorItemInSlot(2).getTag().getList("AttributeModifiers", 10).isEmpty();
							 if(!init) {
								 player.inventory.armorItemInSlot(2).addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 0d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
								 init = true;
							 }
							ListNBT tagList = player.inventory.armorItemInSlot(2).getTag().getList("AttributeModifiers", 10);
							for(int x = 0; x < tagList.size(); x++)
							{
								CompoundNBT idTag = tagList.getCompound(x);
								if(idTag.getString("Name") == "generic.max_health"&& idTag.getString("AttributeName") != "minecraft:generic.armor" && idTag.getString("AttributeName") != "minecraft:generic.armor_toughness" && idTag.getString("AttributeName") != "minecraft:generic.knockback_resistance");
								{
									ItemStack armor = player.inventory.armorItemInSlot(2);
									tagList.clear();
									if(armor.getItem() == Items.NETHERITE_CHESTPLATE) {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2, 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 8d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
										 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(2, 2^64), "generic.kb_resistance", 0.1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
			
									 } else if(armor.getItem() == Items.DIAMOND_CHESTPLATE)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2, 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 8d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
									 } else if(armor.getItem() == Items.GOLDEN_CHESTPLATE || armor.getItem() == Items.CHAINMAIL_CHESTPLATE)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 5d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
									 } else if(armor.getItem() == Items.LEATHER_CHESTPLATE)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
									 } else if (armor.getItem() == Items.IRON_CHESTPLATE)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(2, 2^64), "generic.armor", 6d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.CHEST); 
									 }
									enchant = false;
							}
						}
					}
				if(player.inventory.armorItemInSlot(1).isEnchanted())
				{
					ListNBT tagList = player.inventory.armorItemInSlot(1).getEnchantmentTagList();
					for(int x = 0; x < tagList.size(); x++)
					{
							CompoundNBT idTag = tagList.getCompound(x);
								
								if(idTag.getString("id").matches("enchants_plus:health_boost"))
								{
									short enchantLvl = idTag.getShort("lvl");
									ItemStack armor = player.inventory.armorItemInSlot(1);
									 if(enchantLvl >= 1 && enchantLvl <= 6) {
										 
										 armor.addAttributeModifier(new Attributes().MAX_HEALTH, new AttributeModifier(new UUID(1, 2^64), "generic.max_health", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS);
										 if(armor.getItem() == Items.NETHERITE_LEGGINGS) {
											 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 6d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
											 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(1, 2^64), "generic.kb_resistance", 0.1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
		
										 } else if(armor.getItem() == Items.DIAMOND_LEGGINGS)
										 {
											 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 6d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
										 } else if(armor.getItem() == Items.GOLDEN_LEGGINGS)
										 {
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
										 } else if(armor.getItem() == Items.LEATHER_LEGGINGS)
										 {
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
										 } else if (armor.getItem() == Items.IRON_LEGGINGS)
										 {
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 5d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
										 } else if (armor.getItem() == Items.CHAINMAIL_LEGGINGS)
										 {
											 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 4d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
										 }
										 enchant = true;
										 
								} else if (enchantLvl > 6) {
									player.inventory.armorItemInSlot(1).addAttributeModifier(new Attributes().MAX_HEALTH, new AttributeModifier(new UUID(1, 2^64), "generic.max_health", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS);
									if(armor.getItem() == Items.NETHERITE_LEGGINGS) {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 6d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
										 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(1, 2^64), "generic.kb_resistance", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
		
									 } else if(armor.getItem() == Items.DIAMOND_LEGGINGS)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 6d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
									 } else if(armor.getItem() == Items.GOLDEN_LEGGINGS)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
									 } else if(armor.getItem() == Items.LEATHER_LEGGINGS)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
									 } else if (armor.getItem() == Items.IRON_LEGGINGS)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 5d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
									 } else if (armor.getItem() == Items.CHAINMAIL_LEGGINGS)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 4d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
									 }
									enchant = true;
								}
							} else if(enchant == false)
							{
									ListNBT atList = player.inventory.armorItemInSlot(1).getTag().getList("AttributeModifiers", 10);
									for(int z = 0; z < atList.size(); z++)
									{
											CompoundNBT atTag = atList.getCompound(z);
											if(atTag.getString("AttributeName") == "minecraft:generic.max_health" && atTag.getString("AttributeName") != "minecraft:generic.armor" && atTag.getString("AttributeName") != "minecraft:generic.armor_toughness" && atTag.getString("AttributeName") != "minecraft:generic.knockback_resistance");
											{
												ItemStack armor = player.inventory.armorItemInSlot(1);
												atList.clear();
												if(armor.getItem() == Items.NETHERITE_LEGGINGS) {
													 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 6d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
													 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(1, 2^64), "generic.kb_resistance", 0.1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
			
												 } else if(armor.getItem() == Items.DIAMOND_LEGGINGS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 6d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
												 } else if(armor.getItem() == Items.GOLDEN_LEGGINGS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
												 } else if(armor.getItem() == Items.LEATHER_LEGGINGS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
												 } else if (armor.getItem() == Items.IRON_LEGGINGS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 5d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
												 } else if (armor.getItem() == Items.CHAINMAIL_LEGGINGS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 4d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
												 }
											}
										}
									}
								}
							}	else {
							 player.inventory.armorItemInSlot(1).addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 0d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
							 boolean init = player.inventory.armorItemInSlot(1).getTag().getList("AttributeModifiers", 10).isEmpty();
							 if(!init) {
								 player.inventory.armorItemInSlot(1).addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 0d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
								 init = true;
							 }
							ListNBT tagList = player.inventory.armorItemInSlot(1).getTag().getList("AttributeModifiers", 10);
							for(int x = 0; x < tagList.size(); x++)
							{
								CompoundNBT idTag = tagList.getCompound(x);
								if(idTag.getString("Name") == "generic.max_health"&& idTag.getString("AttributeName") != "minecraft:generic.armor" && idTag.getString("AttributeName") != "minecraft:generic.armor_toughness" && idTag.getString("AttributeName") != "minecraft:generic.knockback_resistance");
								{
									ItemStack armor = player.inventory.armorItemInSlot(1);
									tagList.clear();
									if(armor.getItem() == Items.NETHERITE_LEGGINGS) {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 6d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
										 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(1, 2^64), "generic.kb_resistance", 0.1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
			
									 } else if(armor.getItem() == Items.DIAMOND_LEGGINGS)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 6d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
									 } else if(armor.getItem() == Items.GOLDEN_LEGGINGS)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
									 } else if(armor.getItem() == Items.LEATHER_LEGGINGS)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
									 } else if (armor.getItem() == Items.IRON_LEGGINGS)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 5d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
									 } else if (armor.getItem() == Items.CHAINMAIL_LEGGINGS)
									 {
										 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(1, 2^64), "generic.armor", 4d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.LEGS); 
									 }
									enchant = false;
							}
					}
				}
							if(player.inventory.armorItemInSlot(0).isEnchanted())
							{
								ListNBT tagList = player.inventory.armorItemInSlot(0).getEnchantmentTagList();
								for(int x = 0; x < tagList.size(); x++)
								{
										CompoundNBT idTag = tagList.getCompound(x);
											
											if(idTag.getString("id").matches("enchants_plus:health_boost"))
											{
												short enchantLvl = idTag.getShort("lvl");
												ItemStack armor = player.inventory.armorItemInSlot(0);
												 if(enchantLvl >= 1 && enchantLvl <= 6) {
													 
													 armor.addAttributeModifier(new Attributes().MAX_HEALTH, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.max_health", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET);
													 if(armor.getItem() == Items.NETHERITE_BOOTS) {
														 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
														 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
														 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.kb_resistance", 0.1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
					
													 } else if(armor.getItem() == Items.DIAMOND_BOOTS)
													 {
														 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
														 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
													 } else if(armor.getItem() == Items.GOLDEN_BOOTS)
													 {
														 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
													 } else if(armor.getItem() == Items.LEATHER_BOOTS)
													 {
														 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
													 } else if (armor.getItem() == Items.IRON_BOOTS)
													 {
														 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
													 } else if (armor.getItem() == Items.CHAINMAIL_BOOTS)
													 {
														 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
													 }
													 enchant = true;
													 
											} else if (enchantLvl > 6) {
												player.inventory.armorItemInSlot(0).addAttributeModifier(new Attributes().MAX_HEALTH, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.max_health", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET);
												if(armor.getItem() == Items.NETHERITE_BOOTS) {
													 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
													 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.kb_resistance", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
					
												 } else if(armor.getItem() == Items.DIAMOND_BOOTS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
												 } else if(armor.getItem() == Items.GOLDEN_BOOTS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
												 } else if(armor.getItem() == Items.LEATHER_BOOTS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
												 } else if (armor.getItem() == Items.IRON_BOOTS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
												 } else if (armor.getItem() == Items.CHAINMAIL_BOOTS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
												 }
												enchant = true;
											}
										} else if(enchant == false)
										{
												ListNBT atList = player.inventory.armorItemInSlot(0).getTag().getList("AttributeModifiers", 10);
												for(int z = 0; z < atList.size(); z++)
												{
														CompoundNBT atTag = atList.getCompound(z);
														if(atTag.getString("AttributeName") == "minecraft:generic.max_health" && atTag.getString("AttributeName") != "minecraft:generic.armor" && atTag.getString("AttributeName") != "minecraft:generic.armor_toughness" && atTag.getString("AttributeName") != "minecraft:generic.knockback_resistance");
														{
															ItemStack armor = player.inventory.armorItemInSlot(0);
															atList.clear();
															if(armor.getItem() == Items.NETHERITE_BOOTS) {
																 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
																 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
																 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.kb_resistance", 0.1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
						
															 } else if(armor.getItem() == Items.DIAMOND_BOOTS)
															 {
																 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
																 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
															 } else if(armor.getItem() == Items.GOLDEN_BOOTS || armor.getItem() == Items.CHAINMAIL_BOOTS)
															 {
																 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
															 } else if(armor.getItem() == Items.LEATHER_BOOTS)
															 {
																 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
															 } else if (armor.getItem() == Items.IRON_BOOTS)
															 {
																 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
															 } else if (armor.getItem() == Items.CHAINMAIL_BOOTS)
															 {
																 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
															 }
														}
													}
												}
											}
										}	else {
										 player.inventory.armorItemInSlot(0).addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 0d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
										 boolean init = player.inventory.armorItemInSlot(0).getTag().getList("AttributeModifiers", 10).isEmpty();
										 if(!init) {
											 player.inventory.armorItemInSlot(0).addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 0d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
											 init = true;
										 }
										ListNBT tagList = player.inventory.armorItemInSlot(0).getTag().getList("AttributeModifiers", 10);
										for(int x = 0; x < tagList.size(); x++)
										{
											CompoundNBT idTag = tagList.getCompound(x);
											if(idTag.getString("Name") == "generic.max_health"&& idTag.getString("AttributeName") != "minecraft:generic.armor" && idTag.getString("AttributeName") != "minecraft:generic.armor_toughness" && idTag.getString("AttributeName") != "minecraft:generic.knockback_resistance");
											{
												ItemStack armor = player.inventory.armorItemInSlot(0);
												tagList.clear();
												if(armor.getItem() == Items.NETHERITE_BOOTS) {
													 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor_toughness", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
													 armor.addAttributeModifier(new Attributes().KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.kb_resistance", 0.1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
						
												 } else if(armor.getItem() == Items.DIAMOND_BOOTS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR_TOUGHNESS, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor_toughness", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 3d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
												 } else if(armor.getItem() == Items.GOLDEN_BOOTS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
												 } else if(armor.getItem() == Items.LEATHER_BOOTS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
												 } else if (armor.getItem() == Items.IRON_BOOTS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 2d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
												 } else if (armor.getItem() == Items.CHAINMAIL_BOOTS)
												 {
													 armor.addAttributeModifier(new Attributes().ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "generic.armor", 1d, AttributeModifier.Operation.ADDITION), EquipmentSlotType.FEET); 
												 }
												enchant = false;
										}
								}
							}
			}
	}
}
	
	

