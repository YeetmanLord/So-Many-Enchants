package com.yeetmanlord.somanyenchants.core.util;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.nbt.StringNBT;

public class NBTHelper 
{
	public static void renderCustomAttributeLore(PlayerEntity player, double extraVal, Attribute at, String name)
	{
		renderAttributeLore(player);
		ItemStack stack = player.getHeldItemMainhand();
		CompoundNBT tag = stack.getTag();
		CompoundNBT displayList = tag.getCompound("display");
		ListNBT loreList = displayList.getList("Lore", 8);
		StringNBT extraValNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf((float) (Math.round((extraVal + at.getDefaultValue()) * 10) / 10)) + " " + name + "\"}");
		if(extraVal + at.getDefaultValue() != ((int) Math.round((extraVal + at.getDefaultValue()) * 10) / 10))
		{
			extraValNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(extraVal + at.getDefaultValue() + " " + name + "\"}"));
		} else
		{
			 extraValNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf((int) (Math.round((extraVal + at.getDefaultValue()) * 10) / 10)) + " " + name + "\"}");
		}
		
		if(loreList.contains(extraValNBT))
		{
			return;
		}
		if(loreList.contains(extraValNBT)) 
		{
			return;
		} else
		{
			loreList.add(extraValNBT);
		}
		displayList.remove("Lore");
		displayList.put("Lore", loreList);
	}
	
	public static void removeCustomAttributeLore(ItemStack stack, String name)
	{
		CompoundNBT tag = stack.getTag();
		if(tag.contains("display", 10))
		{
			CompoundNBT display = tag.getCompound("display");
			if(display.contains("Lore", 9))
			{
				ListNBT lore = display.getList("Lore", 8);
				for(int x = 0; x < lore.size(); x++)
				{
					String s = lore.getString(x);
					if(s.contains(name))
					{
						lore.remove(x);
					}
				}
			}
		}
		return;
	}
	
	public static void renderAttributeLore(PlayerEntity player)
	{
		ItemStack stack = player.getHeldItemMainhand();
		CompoundNBT tag = stack.getTag();
		if(tag.contains("display", 10))
		{
			CompoundNBT displayList = tag.getCompound("display");
			if(displayList.contains("Lore", 9))
			{
				ListNBT loreList = displayList.getList("Lore", 8);
				
				double attackDamage = NBTHelper.getAttackDamage(stack.getItem());
				double sharpnessDamage = NBTHelper.getSharpnessDamage(player);
				double attackSpeed = NBTHelper.getAttackSpeed(stack.getItem());
				
				double atSpeed = player.getBaseAttributeValue(Attributes.ATTACK_SPEED);
				
				StringNBT space = StringNBT.valueOf("{\"text\": \"\"}");
				StringNBT extra = StringNBT.valueOf("{\"text\": \"\u00a77When in Main Hand\"}");
				StringNBT attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf((float) Math.round((attackDamage + 1 + sharpnessDamage) * 10) / 10) + " Attack Damage\"}");
				StringNBT attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf((float) Math.round((atSpeed + attackSpeed) * 10) / 10) + " Attack Speed\"}");
				if((Math.round((attackDamage + 1 + sharpnessDamage) * 10) / 10) != (attackDamage + 1 + sharpnessDamage))
				{
					attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(((float)Math.round((attackDamage + 1 + sharpnessDamage) * 10)) / 10) + " Attack Damage\"}");
				} else
				{
					attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(Math.round((attackDamage + 1 + sharpnessDamage) * 10) / 10) + " Attack Damage\"}");
				}
				
				if(((Math.round(atSpeed + attackSpeed) * 10) / 10) != atSpeed + attackSpeed || stack.getItem() instanceof PickaxeItem)
				{
					attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(((float) Math.round((atSpeed + attackSpeed) * 10)) / 10) + " Attack Speed\"}");
				} else
				{
					attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(Math.round((atSpeed + attackSpeed) * 10) / 10) + " Attack Speed\"}");
				}
				
				if(loreList.contains(space))
				{
					
				} else
				{
					loreList.add(space);
				}
				if(loreList.contains(extra))
				{
					
				} else
				{
					loreList.add(extra);
				}
				if(loreList.contains(attackDamageNBT)) 
				{
					
				} else
				{
					loreList.add(attackDamageNBT);
				}
				
				if(loreList.contains(attackSpeedNBT))
				{
					
				} else
				{
					loreList.add(attackSpeedNBT);
				}
				displayList.put("Lore", loreList);
				return;
			}
			ListNBT loreList = displayList.getList("Lore", 8);
			
			double attackDamage = NBTHelper.getAttackDamage(stack.getItem());
			double sharpnessDamage = NBTHelper.getSharpnessDamage(player);
			double attackSpeed = NBTHelper.getAttackSpeed(stack.getItem());
			
			double atSpeed = player.getBaseAttributeValue(Attributes.ATTACK_SPEED);
			
			StringNBT space = StringNBT.valueOf("{\"text\": \"\"}");
			StringNBT extra = StringNBT.valueOf("{\"text\": \"\u00a77When in Main Hand\"}");
			StringNBT attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf((float) Math.round((attackDamage + 1 + sharpnessDamage) * 10) / 10) + " Attack Damage\"}");
			StringNBT attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf((float) Math.round((atSpeed + attackSpeed) * 10) / 10) + " Attack Speed\"}");
			if((Math.round((attackDamage + 1 + sharpnessDamage) * 10) / 10) != (attackDamage + 1 + sharpnessDamage))
			{
				attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(((float) Math.round((attackDamage + 1 + sharpnessDamage) * 10)) / 10) + " Attack Damage\"}");
			} else
			{
				attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(Math.round((attackDamage + 1 + sharpnessDamage) * 10) / 10) + " Attack Damage\"}");
			}
			
			if((Math.round(atSpeed + attackSpeed) * 10) / 10 != atSpeed + attackSpeed || stack.getItem() instanceof PickaxeItem)
			{
				attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(((float) Math.round((atSpeed + attackSpeed) * 10)) / 10) + " Attack Speed\"}");
			} else
			{
				attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(Math.round((atSpeed + attackSpeed) * 10) / 10) + " Attack Speed\"}");
			}
			
			if(loreList.contains(space))
			{
				
			} else
			{
				loreList.add(space);
			}
			if(loreList.contains(extra))
			{
				
			} else
			{
				loreList.add(extra);
			}
			if(loreList.contains(attackDamageNBT)) 
			{
				
			} else
			{
				loreList.add(attackDamageNBT);
			}
			
			if(loreList.contains(attackSpeedNBT))
			{
				
			} else
			{
				loreList.add(attackSpeedNBT);
			}
			displayList.put("Lore", loreList);
			return;
		}
		CompoundNBT loreTag = new CompoundNBT();
		ListNBT loreList = new ListNBT();
		
		double attackDamage = NBTHelper.getAttackDamage(stack.getItem());
		double sharpnessDamage = NBTHelper.getSharpnessDamage(player);
		double attackSpeed = NBTHelper.getAttackSpeed(stack.getItem());
		
		double atSpeed = player.getBaseAttributeValue(Attributes.ATTACK_SPEED);
		
		StringNBT space = StringNBT.valueOf("{\"text\": \"\"}");
		StringNBT extra = StringNBT.valueOf("{\"text\": \"\u00a77When in Main Hand\"}");
		
		StringNBT attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(((float) Math.round((attackDamage + 1 + sharpnessDamage) * 10)) / 10) + " Attack Damage\"}");
		StringNBT attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(((float) Math.round((atSpeed + attackSpeed) * 10)) / 10) + " Attack Speed\"}");
		if((Math.round((attackDamage + 1 + sharpnessDamage) * 10) / 10) != (attackDamage + 1 + sharpnessDamage))
		{
			attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(((float) Math.round((attackDamage + 1 + sharpnessDamage) * 10)) / 10) + " Attack Damage\"}");
		} else
		{
			attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(Math.round((attackDamage + 1 + sharpnessDamage) * 10) / 10) + " Attack Damage\"}");
		}
		
		if((Math.round(atSpeed + attackSpeed) * 10) / 10 != atSpeed + attackSpeed || stack.getItem() instanceof PickaxeItem)
		{
			attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(((float) Math.round((atSpeed + attackSpeed) * 10)) / 10) + " Attack Speed\"}");
		} else
		{
			attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(Math.round((atSpeed + attackSpeed) * 10) / 10) + " Attack Speed\"}");
		}
		
		if(loreList.contains(space))
		{
		} else
		{
			loreList.add(space);
		}
		if(loreList.contains(extra))
		{
		} else
		{
			loreList.add(extra);
		}
		if(loreList.contains(attackDamageNBT)) 
		{
		} else
		{
			loreList.add(attackDamageNBT);
		}
		
		if(loreList.contains(attackSpeedNBT))
		{
			
		} else
		{
			loreList.add(attackSpeedNBT);
		}
		
		loreTag.put("Lore", loreList);
		tag.put("display", loreTag);
		return;
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
		} else
		{
			return attackDamage;
		}
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
	
	public static double getSharpnessDamage(PlayerEntity player)
	{
		int sharpnessLvl = ModEnchantmentHelper.getSharpnessLevel(player);
		if(sharpnessLvl > 0) 
		{
			return (sharpnessLvl * 0.5D) + 0.5D;
		}
		return 0;
	}
	
	public static void updateAttributeLore(ItemStack stack, PlayerEntity player)
	{
		double attackDamage = NBTHelper.getAttackDamage(stack.getItem());
		double sharpnessDamage = NBTHelper.getSharpnessDamage(player);
		double attackSpeed = NBTHelper.getAttackSpeed(stack.getItem());
		CompoundNBT tag = stack.getTag();
		if(tag.contains("display", 10))
		{
			CompoundNBT display = tag.getCompound("display");
			if(display.contains("Lore", 9))
			{
				ListNBT lore = display.getList("Lore", 8);
				for(int x = 0; x < lore.size(); x++)
				{
					String s = lore.getString(x);
					if(s.contains(" Attack Speed\"}"))
					{
						if(s.contains(String.valueOf(Math.round((player.getBaseAttributeValue(Attributes.ATTACK_SPEED) + attackSpeed) * 10) / 10)))
						{
							return;
						}
						lore.remove(x);
						StringNBT attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(Math.round((player.getBaseAttributeValue(Attributes.ATTACK_SPEED) + attackSpeed) * 10) / 10) + " Attack Speed\"}");
						if((Math.round(player.getBaseAttributeValue(Attributes.ATTACK_SPEED) + attackSpeed) * 10) / 10 != player.getBaseAttributeValue(Attributes.ATTACK_SPEED) + attackSpeed || stack.getItem() instanceof PickaxeItem)
						{
							attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(((float) Math.round((player.getBaseAttributeValue(Attributes.ATTACK_SPEED) + attackSpeed) * 10)) / 10) + " Attack Speed\"}");
						} else
						{
							attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(Math.round((player.getBaseAttributeValue(Attributes.ATTACK_SPEED) + attackSpeed) * 10) / 10) + " Attack Speed\"}");
						}
						
						lore.add(x, attackSpeedNBT);
					}
					if(s.contains(" Attack Damage\"}"))
					{
						if(s.contains(String.valueOf((Math.round(1 + attackDamage + sharpnessDamage) * 10) / 10)))
						{
							return;
						}
						lore.remove(x);
						StringNBT attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(Math.round((1 + attackDamage + sharpnessDamage) * 10) / 10) + " Attack Damage\"}");
						if((Math.round(1 + attackDamage + sharpnessDamage) * 10) / 10 != 1 + attackDamage + sharpnessDamage || stack.getItem() instanceof PickaxeItem)
						{
							attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(((float) Math.round((1 + attackDamage + sharpnessDamage) * 10)) / 10) + " Attack Damage\"}");
						} else
						{
							attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(Math.round((1 + attackDamage + sharpnessDamage) * 10) / 10) + " Attack Damage\"}");
						}
						
						lore.add(x, attackDamageNBT);
					}
				}
			}
		}
	}
	public static void updateAttributeLore(ItemStack stack, PlayerEntity player, Attribute attr, String displayName)
	{
		double attrValue = player.getAttributeValue(attr);
		CompoundNBT tag = stack.getTag();
		if(tag.contains("display", 10) && attrValue > attr.getDefaultValue())
		{
			CompoundNBT display = tag.getCompound("display");
			if(display.contains("Lore", 9))
			{
				ListNBT lore = display.getList("Lore", 8);
				for(int x = 0; x < lore.size(); x++)
				{
					String s = lore.getString(x);
					if(s.contains(" " + displayName + "\"}"))
					{
						if(s.contains(String.valueOf((Math.round(attrValue) * 10) / 10)))
						{
							return;
						}
						lore.remove(x);
						StringNBT attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(Math.round((attrValue) * 10) / 10) + " " + displayName + "\"}");
						if((Math.round(attrValue) * 10) / 10 != 1 + attrValue)
						{
							attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(((float) Math.round((attrValue) * 10)) / 10) + " " + displayName + "\"}");
						} else
						{
							attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72" + String.valueOf(Math.round((attrValue) * 10) / 10) + " " + displayName + "\"}");
						}
						
						lore.add(x, attackDamageNBT);
					}
				}
			}
		}
	}
	
}
