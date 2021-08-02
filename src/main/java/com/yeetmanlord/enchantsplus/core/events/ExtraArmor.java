package com.yeetmanlord.enchantsplus.core.events;

import java.util.UUID;

import com.yeetmanlord.enchantsplus.Main;
import com.yeetmanlord.enchantsplus.core.util.AttributeHelper;
import com.yeetmanlord.enchantsplus.core.util.ModEnchantmentHelper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class ExtraArmor {
	
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
				int ench = ModEnchantmentHelper.getReinforcementValue(player, 3);
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
				int ench = ModEnchantmentHelper.getReinforcementValue(player, 2);
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
				int ench = ModEnchantmentHelper.getReinforcementValue(player, 1);
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
				int ench = ModEnchantmentHelper.getReinforcementValue(player, 0);
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

}
