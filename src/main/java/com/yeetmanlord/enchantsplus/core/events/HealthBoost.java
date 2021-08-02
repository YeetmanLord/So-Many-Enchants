package com.yeetmanlord.enchantsplus.core.events;

import java.util.UUID;

import com.yeetmanlord.enchantsplus.Main;
import com.yeetmanlord.enchantsplus.core.util.AttributeHelper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class HealthBoost 
{
	private static boolean enchant = false;
	private static boolean enchantChest = false;
	private static boolean enchantLegs = false;
	private static boolean enchantFeet = false;
	@SubscribeEvent
	public static void armorHPBoost(final LivingEquipmentChangeEvent event)
	{
		LivingEntity entity = event.getEntityLiving();
			if (entity instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity) entity;
				if(player.inventory.armorItemInSlot(3).isEnchanted())
				{
					EquipmentSlotType slot = EquipmentSlotType.HEAD;
					ListNBT tagList = player.inventory.armorItemInSlot(3).getEnchantmentTagList();
					for(int x = 0; x < tagList.size(); x++)
					{
							CompoundNBT idTag = tagList.getCompound(x);
							boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", player.inventory.armorItemInSlot(3)) || AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", player.inventory.armorItemInSlot(3))
									|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", player.inventory.armorItemInSlot(3));
							if(idTag.getString("id").matches("enchants_plus:health_boost") && !enchant)
								{
									short enchantLvl = idTag.getShort("lvl");
									ItemStack armor = player.inventory.armorItemInSlot(3);
									 if(enchantLvl >= 1 && enchantLvl <= 16) {
										 armor.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), slot);
										 if(armor.getItem() instanceof ArmorItem && !flag)
											{
												float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
												float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
												float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
												if(toughness > 0)
												{
													armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
												}
												if(baseArmor > 0)
												{
													armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
												}
												if(kbresistance > 0)
												{
													armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
												}
											}
										 enchant = true;
										 
								} else
								{
									armor.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", 1d * 16, AttributeModifier.Operation.ADDITION), slot);
									 if(armor.getItem() instanceof ArmorItem && !flag)
										{
											float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
											float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
											float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
											if(toughness > 0)
											{
												armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
											}
											if(baseArmor > 0)
											{
												armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
											}
											if(kbresistance > 0)
											{
												armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
											}
										}
									 enchant = true;
								}
							} else if(enchant == false)
							{
									ListNBT atList = player.inventory.armorItemInSlot(3).getTag().getList("AttributeModifiers", 10);
									for(int z = 0; z < atList.size(); z++)
									{
											CompoundNBT atTag = atList.getCompound(z);
											String n = atTag.getString("Name");
											String s = atTag.getString("AttributeName");
											if(s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier"))
											{
												ItemStack armor = player.inventory.armorItemInSlot(3);
												atList.remove(z);
												if(armor.getItem() instanceof ArmorItem && !flag)
												{
													float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
													float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
													float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
													if(toughness > 0)
													{
														armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
													}
													if(baseArmor > 0)
													{
														armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
													}
													if(kbresistance > 0)
													{
														armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
													}
												}
											}
										}
									}
								}
							}	else {

								EquipmentSlotType slot = EquipmentSlotType.HEAD;
								if(player.inventory.armorItemInSlot(3).hasTag()) {
								
									if(player.inventory.armorItemInSlot(3).getTag().contains("AttributeModifiers", 10))
									{
										ListNBT tagList = player.inventory.armorItemInSlot(3).getTag().getList("AttributeModifiers", 10);
										for(int x = 0; x < tagList.size(); x++)
										{
											CompoundNBT idTag = tagList.getCompound(x);
											boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", player.inventory.armorItemInSlot(3)) || AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", player.inventory.armorItemInSlot(3))
													|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", player.inventory.armorItemInSlot(3));
											String n = idTag.getString("Name");
											String s = idTag.getString("AttributeName");
											if(s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier"))
											{
												ItemStack armor = player.inventory.armorItemInSlot(3);
												tagList.remove(x);
												if(armor.getItem() instanceof ArmorItem && !flag)
												{
													float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
													float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
													float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
													if(toughness > 0)
													{
														armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
													}
													if(baseArmor > 0)
													{
														armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
													}
													if(kbresistance > 0)
													{
														armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(2^64, 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
													}
												}
												enchant = false;
										}
									}
									return;
							}
					}
					enchant = false;
					
				}
			
				if(player.inventory.armorItemInSlot(2).isEnchanted())
				{
					EquipmentSlotType slot = EquipmentSlotType.CHEST;
					ListNBT tagList = player.inventory.armorItemInSlot(2).getEnchantmentTagList();
					for(int x = 0; x < tagList.size(); x++)
					{
							CompoundNBT idTag = tagList.getCompound(x);
							boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", player.inventory.armorItemInSlot(2)) || AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", player.inventory.armorItemInSlot(2))
									|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", player.inventory.armorItemInSlot(2));
							if(idTag.getString("id").matches("enchants_plus:health_boost") && !enchantChest)
								{
									short enchantLvl = idTag.getShort("lvl");
									ItemStack armor = player.inventory.armorItemInSlot(2);
									 if(enchantLvl >= 1 && enchantLvl <= 16) {
										 armor.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), slot);
										 if(armor.getItem() instanceof ArmorItem && !flag)
											{
												float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
												float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
												float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
												if(toughness > 0)
												{
													armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
												}
												if(baseArmor > 0)
												{
													armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
												}
												if(kbresistance > 0)
												{
													armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
												}
											}
										 enchantChest = true;
										 
								} else if(enchantLvl > 16)
								{
									armor.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", 1d * 16, AttributeModifier.Operation.ADDITION), slot);
									 if(armor.getItem() instanceof ArmorItem && !flag)
										{
											float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
											float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
											float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
											if(toughness > 0)
											{
												armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
											}
											if(baseArmor > 0)
											{
												armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
											}
											if(kbresistance > 0)
											{
												armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
											}
										}
									 enchantChest = true;
								}
							} else if(enchantChest == false)
							{
									ListNBT atList = player.inventory.armorItemInSlot(2).getTag().getList("AttributeModifiers", 10);
									for(int z = 0; z < atList.size(); z++)
									{
											CompoundNBT atTag = atList.getCompound(z);
											String n = idTag.getString("Name");
											String s = idTag.getString("AttributeName");
											if(s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier"))
											{
												ItemStack armor = player.inventory.armorItemInSlot(2);
												atList.remove(z);
												if(armor.getItem() instanceof ArmorItem && !flag)
												{
													float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
													float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
													float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
													if(toughness > 0)
													{
														armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
													}
													if(baseArmor > 0)
													{
														armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
													}
													if(kbresistance > 0)
													{
														armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
													}
												}
											}
										}
									}
								}
							} else {
								if(player.inventory.armorItemInSlot(2).hasTag()) {
								
									if(player.inventory.armorItemInSlot(2).getTag().contains("AttributeModifiers", 10))
									{
										ListNBT tagList = player.inventory.armorItemInSlot(2).getTag().getList("AttributeModifiers", 10);
										for(int x = 0; x < tagList.size(); x++)
										{
											EquipmentSlotType slot = EquipmentSlotType.CHEST;
											CompoundNBT idTag = tagList.getCompound(x);
											boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", player.inventory.armorItemInSlot(1)) || AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", player.inventory.armorItemInSlot(1))
													|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", player.inventory.armorItemInSlot(1));
											String n = idTag.getString("Name");
											String s = idTag.getString("AttributeName");
											if(s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier"))
											{
												ItemStack armor = player.inventory.armorItemInSlot(2);
												tagList.remove(x);
												if(armor.getItem() instanceof ArmorItem && !flag)
												{
													float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
													float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
													float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
													if(toughness > 0)
													{
														armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
													}
													if(baseArmor > 0)
													{
														armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
													}
													if(kbresistance > 0)
													{
														armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
													}
												}
												enchantChest = false;
											}
										}
										return;
									}
								}
								enchantChest = false;
				}
				if(player.inventory.armorItemInSlot(1).isEnchanted())
				{
					EquipmentSlotType slot = EquipmentSlotType.LEGS;
					ListNBT tagList = player.inventory.armorItemInSlot(1).getEnchantmentTagList();
					for(int x = 0; x < tagList.size(); x++)
					{
							CompoundNBT idTag = tagList.getCompound(x);
							boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", player.inventory.armorItemInSlot(1)) || AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", player.inventory.armorItemInSlot(1))
									|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", player.inventory.armorItemInSlot(1));
								if(idTag.getString("id").matches("enchants_plus:health_boost") && !enchantLegs)
								{
									short enchantLvl = idTag.getShort("lvl");
									ItemStack armor = player.inventory.armorItemInSlot(1);
									 if(enchantLvl >= 1 && enchantLvl <= 16) {
										 armor.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(2, 2^64), "Armor Modifier", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), slot);
										 if(armor.getItem() instanceof ArmorItem && !flag)
											{
												float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
												float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
												float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
												if(toughness > 0)
												{
													armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
												}
												if(baseArmor > 0)
												{
													armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(2, 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
												}
												if(kbresistance > 0)
												{
													armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(2, 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
												}
											}
										 enchantLegs = true;
										 
								} else if(enchantLvl > 16)
								{
									armor.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(2, 2^64), "Armor Modifier", 1d * 16, AttributeModifier.Operation.ADDITION), slot);
									 if(armor.getItem() instanceof ArmorItem && !flag)
										{
											float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
											float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
											float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
											if(toughness > 0)
											{
												armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2, 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
											}
											if(baseArmor > 0)
											{
												armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(2, 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
											}
											if(kbresistance > 0)
											{
												armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(2, 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
											}
										}
									 enchantLegs = true;
								}
							} else if(enchantLegs == false)
							{
									ListNBT atList = player.inventory.armorItemInSlot(1).getTag().getList("AttributeModifiers", 10);
									for(int z = 0; z < atList.size(); z++)
									{
											CompoundNBT atTag = atList.getCompound(z);
											String n = idTag.getString("Name");
											String s = idTag.getString("AttributeName");
											if(s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier"))
											{
												ItemStack armor = player.inventory.armorItemInSlot(1);
												atList.remove(z);
												if(armor.getItem() instanceof ArmorItem && !flag)
												{
													float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
													float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
													float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
													if(toughness > 0)
													{
														armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2, 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
													}
													if(baseArmor > 0)
													{
														armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(2, 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
													}
													if(kbresistance > 0)
													{
														armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(2, 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
													}
												}
											}
										}
									}
								}
							} else 
							{
								EquipmentSlotType slot = EquipmentSlotType.LEGS;
								if(player.inventory.armorItemInSlot(1).hasTag()) {
								
									if(player.inventory.armorItemInSlot(1).getTag().contains("AttributeModifiers", 10))
									{
										ListNBT tagList = player.inventory.armorItemInSlot(0).getTag().getList("AttributeModifiers", 10);
										for(int x = 0; x < tagList.size(); x++)
										{
											boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", player.inventory.armorItemInSlot(1)) || AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", player.inventory.armorItemInSlot(1))
													|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", player.inventory.armorItemInSlot(1));
											CompoundNBT idTag = tagList.getCompound(x);
											String n = idTag.getString("Name");
											String s = idTag.getString("AttributeName");
											if(s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier"))
											{
												ItemStack armor = player.inventory.armorItemInSlot(1);
												tagList.remove(x);
												if(armor.getItem() instanceof ArmorItem && !flag)
												{
													float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
													float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
													float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
													if(toughness > 0)
													{
														armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
													}
													if(baseArmor > 0)
													{
														armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
													}
													if(kbresistance > 0)
													{
														armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(1, 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
													}
												}
												enchantLegs = false;
											}
										}
										return;
									}
								}
								enchantLegs = false;
							}
							if(player.inventory.armorItemInSlot(0).isEnchanted())
							{
								EquipmentSlotType slot = EquipmentSlotType.FEET;
								ListNBT tagList = player.inventory.armorItemInSlot(0).getEnchantmentTagList();
								for(int x = 0; x < tagList.size(); x++)
								{
										CompoundNBT idTag = tagList.getCompound(x);
										boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", player.inventory.armorItemInSlot(0)) || AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", player.inventory.armorItemInSlot(0))
												|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", player.inventory.armorItemInSlot(0));
											
											if(idTag.getString("id").matches("enchants_plus:health_boost") && !enchantFeet)
											{
												short enchantLvl = idTag.getShort("lvl");
												ItemStack armor = player.inventory.armorItemInSlot(0);
												 if(enchantLvl >= 1 && enchantLvl <= 16) {
													 
													 armor.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), slot);
													 if(armor.getItem() instanceof ArmorItem && !flag)
														{
															float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
															float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
															float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
															if(toughness > 0)
															{
																armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
															}
															if(baseArmor > 0)
															{
																armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
															}
															if(kbresistance > 0)
															{
																armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
															}
														}
													 enchantFeet = true;
													 
											} else if(enchantLvl > 16)
											{
												armor.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", 1d * 16, AttributeModifier.Operation.ADDITION), slot);
												 if(armor.getItem() instanceof ArmorItem && !flag)
													{
														float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
														float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
														float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
														if(toughness > 0)
														{
															armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
														}
														if(baseArmor > 0)
														{
															armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
														}
														if(kbresistance > 0)
														{
															armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
														}
													}
												 enchantFeet = true;
											}
										} else if(enchantFeet == false)
										{
												ListNBT atList = player.inventory.armorItemInSlot(0).getTag().getList("AttributeModifiers", 10);
												for(int z = 0; z < atList.size(); z++)
												{
														CompoundNBT atTag = atList.getCompound(z);
														if(atTag.getString("AttributeName").matches("minecraft:generic.max_health"))
														{
															ItemStack armor = player.inventory.armorItemInSlot(0);
															atList.remove(z);
															 if(armor.getItem() instanceof ArmorItem && !flag)
																{
																	float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
																	float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
																	float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
																	if(toughness > 0)
																	{
																		armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
																	}
																	if(baseArmor > 0)
																	{
																		armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
																	}
																	if(kbresistance > 0)
																	{
																		armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
																	}
																}
														}
													}
												}
											}
										} else 
										{
											EquipmentSlotType slot = EquipmentSlotType.FEET;
											if(player.inventory.armorItemInSlot(0).hasTag()) {
												if(player.inventory.armorItemInSlot(0).getTag().getList("AttributeModifiers", 10) != null)
												{
													ListNBT tagList = player.inventory.armorItemInSlot(0).getTag().getList("AttributeModifiers", 10);
													for(int x = 0; x < tagList.size(); x++)
													{
														CompoundNBT idTag = tagList.getCompound(x);
														boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", player.inventory.armorItemInSlot(0)) || AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", player.inventory.armorItemInSlot(0))
																|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", player.inventory.armorItemInSlot(0));
														String n = idTag.getString("Name");
														String s = idTag.getString("AttributeName");
														if(s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier"))
														{
															ItemStack armor = player.inventory.armorItemInSlot(0);
															tagList.remove(x);
															 if(armor.getItem() instanceof ArmorItem && !flag)
																{
																	float baseArmor = ((ArmorItem)armor.getItem()).getArmorMaterial().getDamageReductionAmount(slot);
																	float kbresistance = ((ArmorItem)armor.getItem()).getArmorMaterial().getKnockbackResistance();
																	float toughness = ((ArmorItem)armor.getItem()).getArmorMaterial().getToughness();
																	if(toughness > 0)
																	{
																		armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", toughness, AttributeModifier.Operation.ADDITION), slot); 
																	}
																	if(baseArmor > 0)
																	{
																		armor.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION), slot); 
																	}
																	if(kbresistance > 0)
																	{
																		armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(new UUID(-(2^61), 2^64), "Armor Modifier", kbresistance, AttributeModifier.Operation.ADDITION), slot);
																	}
																}
															enchantFeet = false;
													}
												}
												return;
										}
								}
								enchantFeet = false;
								
							}
			}
	}
}
	
	

