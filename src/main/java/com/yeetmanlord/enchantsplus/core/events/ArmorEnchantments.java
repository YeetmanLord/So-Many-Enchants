package com.yeetmanlord.enchantsplus.core.events;

import java.util.UUID;

import com.yeetmanlord.enchantsplus.Main;
import com.yeetmanlord.enchantsplus.core.util.AttributeHelper;
import com.yeetmanlord.enchantsplus.core.util.ModEnchantmentHelper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class ArmorEnchantments {
	
	@SubscribeEvent
	public static void extraArmor(final LivingEquipmentChangeEvent event) 
	{
		LivingEntity living = event.getEntityLiving();
		if(living instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)living;
			ItemStack head = player.inventory.armorItemInSlot(3);
			ItemStack chest = player.inventory.armorItemInSlot(2);
			ItemStack legs = player.inventory.armorItemInSlot(1);
			ItemStack feet = player.inventory.armorItemInSlot(0);
			UUID HeadSlot = new UUID(1234, 4321);
			UUID ChestSlot = new UUID(4321, 1234);
			UUID LegSlot = new UUID(-1234, -4321);
			UUID FootSlot = new UUID(-4321, -1234);
			if(head.isEnchanted())
			{
				int ench = ModEnchantmentHelper.getReinforcementLevel(player, 3);
				if(ench >= 16)
				{
					ench = 16;
				}
				if(ench > 0)
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", head);
					if(!flag) 
					{
						AttributeHelper.newAttribute(head, Attributes.ARMOR, "Armor Modifier", getArmorVal(head, EquipmentSlotType.HEAD) + (1.0D * ench), true, false, true, player, EquipmentSlotType.HEAD, HeadSlot);
					}
					else 
					{
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", head);
						if(flag1)
						{
							AttributeHelper.changeAttribute(head, Attributes.ARMOR, getArmorVal(head, EquipmentSlotType.HEAD) + ench * 1.0D, "Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
						}
					}
				}
				else 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", head);
					if(flag)
					{
						AttributeHelper.changeAttribute(head, Attributes.ARMOR, getArmorVal(head, EquipmentSlotType.HEAD), "Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
					}
				}
			}
			else 
			{
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", head);
				if(flag)
				{
					AttributeHelper.changeAttribute(head, Attributes.ARMOR, getArmorVal(head, EquipmentSlotType.HEAD), "Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
				}
			}
			if(chest.isEnchanted())
			{
				int ench = ModEnchantmentHelper.getReinforcementLevel(player, 2);
				if(ench >= 16)
				{
					ench = 16;
				}
				if(ench > 0)
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", chest);
					if(!flag) 
					{
						AttributeHelper.newAttribute(chest, Attributes.ARMOR, "Armor Modifier", getArmorVal(chest, EquipmentSlotType.CHEST) + (1.0D * ench), true, false, true, player, EquipmentSlotType.CHEST, ChestSlot);
					}
					else 
					{
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", chest);
						if(flag1)
						{
							AttributeHelper.changeAttribute(chest, Attributes.ARMOR, getArmorVal(chest, EquipmentSlotType.CHEST) + ench * 1.0D, "Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
						}
					}
				}
				else 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", chest);
					if(flag)
					{
						AttributeHelper.changeAttribute(chest, Attributes.ARMOR, getArmorVal(chest, EquipmentSlotType.CHEST), "Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
					}
				}
			}
			else 
			{
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", chest);
				if(flag)
				{
					AttributeHelper.changeAttribute(chest, Attributes.ARMOR, getArmorVal(chest, EquipmentSlotType.CHEST), "Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
				}
			}
			if(legs.isEnchanted())
			{
				int ench = ModEnchantmentHelper.getReinforcementLevel(player, 1);
				if(ench >= 16)
				{
					ench = 16;
				}
				if(ench > 0) 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", legs);
					if(!flag) 
					{
						AttributeHelper.newAttribute(legs, Attributes.ARMOR, "Armor Modifier", getArmorVal(legs, EquipmentSlotType.LEGS) + (1.0D * ench), true, false, true, player, EquipmentSlotType.LEGS, LegSlot);
					} 
					else 
					{
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", legs);
						if(flag1)
						{
							AttributeHelper.changeAttribute(legs, Attributes.ARMOR, getArmorVal(legs, EquipmentSlotType.LEGS) + ench * 1.0D, "Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
						}
					}
				}
				else 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", legs);
					if(flag)
					{
						AttributeHelper.changeAttribute(legs, Attributes.ARMOR, getArmorVal(legs, EquipmentSlotType.LEGS), "Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
					}
				}
			}
			else 
			{
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", legs);
				if(flag)
				{
					AttributeHelper.changeAttribute(legs, Attributes.ARMOR, getArmorVal(legs, EquipmentSlotType.LEGS), "Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
				}
			}
			if(feet.isEnchanted())
			{
				int ench = ModEnchantmentHelper.getReinforcementLevel(player, 0);
				if(ench >= 16)
				{
					ench = 16;
				}
				if(ench > 0)
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", feet);
					if(!flag) 
					{
						AttributeHelper.newAttribute(feet, Attributes.ARMOR, "Armor Modifier", getArmorVal(feet, EquipmentSlotType.FEET) + (1.0D * (double) ench), true, false, true, player, EquipmentSlotType.FEET, FootSlot);
					} 
					else 
					{
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", feet);
						if(flag1)
						{
							AttributeHelper.changeAttribute(feet, Attributes.ARMOR, getArmorVal(feet, EquipmentSlotType.FEET) + ench * 1.0D, "Armor Modifier", false, false, player, EquipmentSlotType.FEET);
						}
					}
				}
				else 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", feet);
					if(flag)
					{
						AttributeHelper.changeAttribute(feet, Attributes.ARMOR, getArmorVal(feet, EquipmentSlotType.FEET), "Armor Modifier", false, false, player, EquipmentSlotType.FEET);
					}
				}
			}
			else 
			{
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", feet);
				if(flag)
				{
					AttributeHelper.changeAttribute(feet, Attributes.ARMOR, getArmorVal(feet, EquipmentSlotType.FEET), "Armor Modifier", false, false, player, EquipmentSlotType.FEET);
				}
			}
		}
	}
	
	private static double getArmorVal(ItemStack stack, EquipmentSlotType slotIn)
	{
		if(stack.getItem() instanceof ArmorItem)
		{
			ArmorItem armor = (ArmorItem)stack.getItem();
			float baseArmor = armor.getArmorMaterial().getDamageReductionAmount(slotIn);
			float kbresistance = armor.getArmorMaterial().getKnockbackResistance();
			float toughness = armor.getArmorMaterial().getToughness();
			return baseArmor;
		}
		return 0;
	}
	
	@SubscribeEvent
	public static void extraToughness(final LivingEquipmentChangeEvent event) 
	{
		LivingEntity living = event.getEntityLiving();
		if(living instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)living;
			ItemStack head = player.inventory.armorItemInSlot(3);
			ItemStack chest = player.inventory.armorItemInSlot(2);
			ItemStack legs = player.inventory.armorItemInSlot(1);
			ItemStack feet = player.inventory.armorItemInSlot(0);
			UUID HeadSlot = new UUID(1234, 4321);
			UUID ChestSlot = new UUID(4321, 1234);
			UUID LegSlot = new UUID(-1234, -4321);
			UUID FootSlot = new UUID(-4321, -1234);
			if(head.isEnchanted())
			{
				int ench = ModEnchantmentHelper.getTemperLevel(player, 3);
				if(ench >= 16)
				{
					ench = 16;
				}
				if(ench > 0)
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", head);
					if(!flag) 
					{
						AttributeHelper.newAttribute(head, Attributes.ARMOR_TOUGHNESS, "Armor Modifier", getArmorVal(head) + (0.5D * ench), true, false, true, player, EquipmentSlotType.HEAD, HeadSlot);
					}
					else 
					{
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", head);
						if(flag1)
						{
							AttributeHelper.changeAttribute(head, Attributes.ARMOR_TOUGHNESS, getArmorVal(head) + ench * 0.5D, "Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
						}
					}
				}
				else 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", head);
					if(flag)
					{
						AttributeHelper.changeAttribute(head, Attributes.ARMOR_TOUGHNESS, getArmorVal(head), "Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
					}
				}
			}
			else 
			{
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", head);
				if(flag)
				{
					AttributeHelper.changeAttribute(head, Attributes.ARMOR_TOUGHNESS, getArmorVal(head), "Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
				}
			}
			if(chest.isEnchanted())
			{
				int ench = ModEnchantmentHelper.getTemperLevel(player, 2);
				if(ench >= 16)
				{
					ench = 16;
				}
				if(ench > 0)
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", chest);
					if(!flag) 
					{
						AttributeHelper.newAttribute(chest, Attributes.ARMOR_TOUGHNESS, "Armor Modifier", getArmorVal(chest) + (0.5D * ench), true, false, true, player, EquipmentSlotType.CHEST, ChestSlot);
					}
					else 
					{
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", chest);
						if(flag1)
						{
							AttributeHelper.changeAttribute(chest, Attributes.ARMOR_TOUGHNESS, getArmorVal(chest) + ench * 0.5D, "Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
						}
					}
				}
				else 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", chest);
					if(flag)
					{
						AttributeHelper.changeAttribute(chest, Attributes.ARMOR_TOUGHNESS, getArmorVal(chest), "Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
					}
				}
			}
			else 
			{
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", chest);
				if(flag)
				{
					AttributeHelper.changeAttribute(chest, Attributes.ARMOR_TOUGHNESS, getArmorVal(chest), "Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
				}
			}
			if(legs.isEnchanted())
			{
				int ench = ModEnchantmentHelper.getTemperLevel(player, 1);
				if(ench >= 16)
				{
					ench = 16;
				}
				if(ench > 0)
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", legs);
					if(!flag) 
					{
						AttributeHelper.newAttribute(legs, Attributes.ARMOR_TOUGHNESS, "Armor Modifier", getArmorVal(legs) + (0.5D * ench), true, false, true, player, EquipmentSlotType.LEGS, LegSlot);
					} 
					else 
					{
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", legs);
						if(flag1)
						{
							AttributeHelper.changeAttribute(legs, Attributes.ARMOR_TOUGHNESS, getArmorVal(legs) + ench * 0.5D, "Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
						}
					}
				}
				else 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", legs);
					if(flag)
					{
						AttributeHelper.changeAttribute(legs, Attributes.ARMOR_TOUGHNESS, getArmorVal(legs), "Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
					}
				}
			}
			else 
			{
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", legs);
				if(flag)
				{
					AttributeHelper.changeAttribute(legs, Attributes.ARMOR_TOUGHNESS, getArmorVal(legs), "Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
				}
			}
			if(feet.isEnchanted())
			{
				int ench = ModEnchantmentHelper.getTemperLevel(player, 0);
				if(ench >= 16)
				{
					ench = 16;
				}
				if(ench > 0)
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", feet);
					if(!flag) 
					{
						AttributeHelper.newAttribute(feet, Attributes.ARMOR_TOUGHNESS, "Armor Modifier", getArmorVal(feet) + (0.5D * (double) ench), true, false, true, player, EquipmentSlotType.FEET, FootSlot);
					} 
					else 
					{
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", feet);
						if(flag1)
						{
							AttributeHelper.changeAttribute(feet, Attributes.ARMOR_TOUGHNESS, getArmorVal(feet) + ench * 0.5D, "Armor Modifier", false, false, player, EquipmentSlotType.FEET);
						}
					}
				}
				else 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", feet);
					if(flag)
					{
						AttributeHelper.changeAttribute(feet, Attributes.ARMOR_TOUGHNESS, getArmorVal(feet), "Armor Modifier", false, false, player, EquipmentSlotType.FEET);
					}
				}
			}
			else 
			{
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", feet);
				if(flag)
				{
					AttributeHelper.changeAttribute(feet, Attributes.ARMOR_TOUGHNESS, getArmorVal(feet), "Armor Modifier", false, false, player, EquipmentSlotType.FEET);
				}
			}
		}
	}
	
	private static double getArmorVal(ItemStack stack)
	{
		if(stack.getItem() instanceof ArmorItem)
		{
			ArmorItem armor = (ArmorItem)stack.getItem();
			float toughness = armor.getArmorMaterial().getToughness();
			
			return toughness;
		}
		return 0;
	}
	
	private static int exhaustion;
	@SubscribeEvent
	public static void flyingEnchant(final PlayerTickEvent event)
	{
		PlayerEntity player = event.player;
		ItemStack boots = player.inventory.armorItemInSlot(0);
		if(!boots.isEmpty())
		{
			ListNBT enchantments = boots.getEnchantmentTagList();
			for(int x = 0; x < enchantments.size(); x ++)
			{
				CompoundNBT tag = enchantments.getCompound(x);
				String id = tag.getString("id");
				short lvl = tag.getShort("lvl");
				
				if(id.matches("enchants_plus:flight") && !player.abilities.isCreativeMode && !player.isSpectator())
				{
					if(lvl <= 1 && player.getFoodStats().getFoodLevel() > 4) 
					{
						player.abilities.allowFlying = true;
						
						if(player.abilities.isFlying)
						{
							exhaustion = exhaustion + 1;
							if(exhaustion >= 500)
							{
								exhaustion = 0;
								player.addExhaustion(1.0f);
								if(!player.isCreative())
								{
									boots.damageItem(1, player, (p_213833_1_) -> {
							               p_213833_1_.sendBreakAnimation(player.getActiveHand());
							               net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, player.getActiveItemStack(), player.getActiveHand());
							          });
								}
							}
						}
					} else if(lvl == 2 && player.getFoodStats().getFoodLevel() > 4)
					{
						player.abilities.allowFlying = true;
						if(player.abilities.isFlying)
						{
							exhaustion = exhaustion + 1;
							if(exhaustion >= 750)
							{
								exhaustion = 0;
								player.addExhaustion(1.0f);
								if(!player.isCreative())
								{
									boots.damageItem(1, player, (p_213833_1_) -> {
							               p_213833_1_.sendBreakAnimation(player.getActiveHand());
							               net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, player.getActiveItemStack(), player.getActiveHand());
							          });
								}
							}
						}
					} else if(lvl == 3 && player.getFoodStats().getFoodLevel() > 4)
					{
						player.abilities.allowFlying = true;
						if(player.abilities.isFlying)
						{
							exhaustion = exhaustion + 1;
							if(exhaustion >= 1000)
							{
								exhaustion = 0;
								player.addExhaustion(1.0f);
								if(!player.isCreative())
								{
									boots.damageItem(1, player, (p_213833_1_) -> {
							               p_213833_1_.sendBreakAnimation(player.getActiveHand());
							               net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, player.getActiveItemStack(), player.getActiveHand());
							          });
								}
							}
						}
					} else if(lvl > 3 && player.getFoodStats().getFoodLevel() > 4)
					{
						player.abilities.allowFlying = true;
						if(player.abilities.isFlying)
						{
							exhaustion = exhaustion + 1;
							if(exhaustion >= 1000 + lvl * 100)
							{
								exhaustion = 0;
								player.addExhaustion(1.0f);
								if(!player.isCreative())
								{
									boots.damageItem(1, player, (p_213833_1_) -> {
							               p_213833_1_.sendBreakAnimation(player.getActiveHand());
							               net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, player.getActiveItemStack(), player.getActiveHand());
							          });
								}
							}
						}
					} else
					{
						player.abilities.allowFlying = false;
						player.abilities.isFlying = false;
					}
				}  else if(id.matches("enchants_plus:flight") && (player.abilities.isCreativeMode || player.isSpectator()))
				{
					player.abilities.allowFlying = true;
				}
			}
		} else if(!player.abilities.isCreativeMode && !player.isSpectator())
		{
			player.abilities.allowFlying = false;
			player.abilities.isFlying = false;
		}
	}

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
	
	@SubscribeEvent
	public static void catVision(final PlayerTickEvent event)
	{
		PlayerEntity player = event.player;
		if(ModEnchantmentHelper.hasCatVision(player))
		{
			player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 220, 0, false, false, false));
		}
	}
	
	@SubscribeEvent
	public static void extraKBResistance(final LivingEquipmentChangeEvent event) 
	{
		LivingEntity living = event.getEntityLiving();
		if(living instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)living;
			ItemStack head = player.inventory.armorItemInSlot(3);
			ItemStack chest = player.inventory.armorItemInSlot(2);
			ItemStack legs = player.inventory.armorItemInSlot(1);
			ItemStack feet = player.inventory.armorItemInSlot(0);
			UUID HeadSlot = new UUID(1234, 4321);
			UUID ChestSlot = new UUID(4321, 1234);
			UUID LegSlot = new UUID(-1234, -4321);
			UUID FootSlot = new UUID(-4321, -1234);
			if(head.isEnchanted())
			{
				int ench = ModEnchantmentHelper.getHeavyArmorLevel(player, 3);
				if(ench >= 16)
				{
					ench = 16;
				}
				if(ench > 0)
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", head);
					if(!flag) 
					{
						AttributeHelper.newAttribute(head, Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", getKBVal(head) + (0.05D * ench), true, false, true, player, EquipmentSlotType.HEAD, HeadSlot);
					}
					else 
					{
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", head);
						if(flag1)
						{
							AttributeHelper.changeAttribute(head, Attributes.KNOCKBACK_RESISTANCE, getKBVal(head) + ench * 0.05D, "Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
						}
					}
				}
				else 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", head);
					if(flag)
					{
						AttributeHelper.changeAttribute(head, Attributes.KNOCKBACK_RESISTANCE, getKBVal(head), "Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
					}
				}
			}
			else 
			{
				boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", head);
				if(flag)
				{
					AttributeHelper.changeAttribute(head, Attributes.KNOCKBACK_RESISTANCE, getKBVal(head), "Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
				}
			}
			if(chest.isEnchanted())
			{
				int ench = ModEnchantmentHelper.getHeavyArmorLevel(player, 2);
				if(ench >= 16)
				{
					ench = 16;
				}
				if(ench > 0)
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", chest);
					if(!flag) 
					{
						AttributeHelper.newAttribute(chest, Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", getKBVal(chest) + (0.05D * ench), true, false, true, player, EquipmentSlotType.CHEST, ChestSlot);
					}
					else 
					{
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", chest);
						if(flag1)
						{
							AttributeHelper.changeAttribute(chest, Attributes.KNOCKBACK_RESISTANCE, getKBVal(chest) + ench * 0.05D, "Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
						}
					}
				}
				else 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", chest);
					if(flag)
					{
						AttributeHelper.changeAttribute(chest, Attributes.KNOCKBACK_RESISTANCE, getKBVal(chest), "Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
					}
				}
			}
			else 
			{
				boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", chest);
				if(flag)
				{
					AttributeHelper.changeAttribute(chest, Attributes.KNOCKBACK_RESISTANCE, getKBVal(chest), "Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
				}
			}
			if(legs.isEnchanted())
			{
				int ench = ModEnchantmentHelper.getHeavyArmorLevel(player, 1);
				if(ench >= 16)
				{
					ench = 16;
				}
				if(ench > 0) 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", legs);
					if(!flag) 
					{
						AttributeHelper.newAttribute(legs, Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", getKBVal(legs) + (0.05D * ench), true, false, true, player, EquipmentSlotType.LEGS, LegSlot);
					} 
					else 
					{
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", legs);
						if(flag1)
						{
							AttributeHelper.changeAttribute(legs, Attributes.KNOCKBACK_RESISTANCE, getKBVal(legs) + ench * 0.05D, "Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
						}
					}
				}
				else 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", legs);
					if(flag)
					{
						AttributeHelper.changeAttribute(legs, Attributes.KNOCKBACK_RESISTANCE, getKBVal(legs), "Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
					}
				}
			}
			else 
			{
				boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", legs);
				if(flag)
				{
					AttributeHelper.changeAttribute(legs, Attributes.KNOCKBACK_RESISTANCE, getKBVal(legs), "Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
				}
			}
			if(feet.isEnchanted())
			{
				int ench = ModEnchantmentHelper.getHeavyArmorLevel(player, 0);
				if(ench >= 16)
				{
					ench = 16;
				}
				if(ench > 0)
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", feet);
					if(!flag) 
					{
						AttributeHelper.newAttribute(feet, Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", getKBVal(feet) + (0.05D * (double) ench), true, false, true, player, EquipmentSlotType.FEET, FootSlot);
					} 
					else 
					{
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", feet);
						if(flag1)
						{
							AttributeHelper.changeAttribute(feet, Attributes.KNOCKBACK_RESISTANCE, getKBVal(feet) + ench * 0.05D, "Armor Modifier", false, false, player, EquipmentSlotType.FEET);
						}
					}
				}
				else 
				{
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", feet);
					if(flag)
					{
						AttributeHelper.changeAttribute(feet, Attributes.KNOCKBACK_RESISTANCE, getKBVal(feet), "Armor Modifier", false, false, player, EquipmentSlotType.FEET);
					}
				}
			}
			else 
			{
				boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier", feet);
				if(flag)
				{
					AttributeHelper.changeAttribute(feet, Attributes.KNOCKBACK_RESISTANCE, getKBVal(feet), "Armor Modifier", false, false, player, EquipmentSlotType.FEET);
				}
			}
		}
	}
	
	private static double getKBVal(ItemStack stack)
	{
		if(stack.getItem() instanceof ArmorItem)
		{
			ArmorItem armor = (ArmorItem)stack.getItem();
			float kbresistance = armor.getArmorMaterial().getKnockbackResistance();
			return kbresistance;
		}
		return 0;
	}
	
	@SubscribeEvent
	public static void stepAssist(final PlayerTickEvent event)
	{
		PlayerEntity player = event.player;
		if(ModEnchantmentHelper.getStepAssistLevel(player) > 0)
		{
			player.stepHeight = 0.6F + ModEnchantmentHelper.getStepAssistLevel(player) * 0.5F;
		}
		else
		{
			player.stepHeight = 0.6F;
		}
	}
}