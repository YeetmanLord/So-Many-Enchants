package com.yeetmanlord.somanyenchants.core.util;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
		StringNBT extraValNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
				+ String.valueOf(MathUtils.roundNearestPlace(extraVal + at.getDefaultValue(), -1)) + " " + name
				+ "\"}");

		if (MathUtils.roundNearestPlace(extraVal + at.getDefaultValue(), -1) == MathUtils
				.roundNearestPlace(extraVal + at.getDefaultValue(), 0))
		{
			extraValNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
					+ String.valueOf((int) MathUtils.roundNearestPlace(extraVal + at.getDefaultValue(), 0)) + " " + name
					+ "\"}");
		}

		if (loreList.contains(extraValNBT))
		{
			return;
		}

		if (loreList.contains(extraValNBT))
		{
			return;
		}
		else
		{
			loreList.add(extraValNBT);
		}

		displayList.remove("Lore");
		displayList.put("Lore", loreList);
	}

	public static void removeCustomAttributeLore(ItemStack stack, String name)
	{
		CompoundNBT tag = stack.getTag();

		if (tag.contains("display", 10))
		{
			CompoundNBT display = tag.getCompound("display");

			if (display.contains("Lore", 9))
			{
				ListNBT lore = display.getList("Lore", 8);

				for (int x = 0; x < lore.size(); x++)
				{
					String s = lore.getString(x);

					if (s.contains(name))
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

		if (tag.contains("display", 10))
		{
			CompoundNBT displayList = tag.getCompound("display");

			if (displayList.contains("Lore", 9))
			{
				ListNBT loreList = displayList.getList("Lore", 8);

				double attackDamage = NBTHelper.getAttackDamage(player) + 1;
				double attackSpeed = NBTHelper.getAttackSpeed(player);

				StringNBT space = StringNBT.valueOf("{\"text\": \"\"}");
				StringNBT extra = StringNBT.valueOf("{\"text\": \"\u00a77When in Main Hand\"}");

				if (loreList.contains(space))
				{
				}
				else
				{
					loreList.add(space);
				}

				if (loreList.contains(extra))
				{
				}
				else
				{
					loreList.add(extra);
				}

				StringNBT attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
						+ String.valueOf(MathUtils.roundNearestPlace(attackDamage, -1)) + " Attack Damage\"}");

				if (MathUtils.roundNearestPlace(attackDamage, -1) == MathUtils.roundNearestPlace(attackDamage, 0))
				{
					attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
							+ String.valueOf((int) MathUtils.roundNearestPlace(attackDamage, 0)) + " Attack Damage\"}");
				}

				if (loreList.contains(attackDamageNBT))
				{
				}
				else
				{
					loreList.add(attackDamageNBT);
				}

				StringNBT attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
						+ String.valueOf(MathUtils.roundNearestPlace(attackSpeed, -1)) + " Attack Speed\"}");

				if (MathUtils.roundNearestPlace(attackSpeed, -1) == MathUtils.roundNearestPlace(attackSpeed, 0))
				{
					attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
							+ String.valueOf((int) MathUtils.roundNearestPlace(attackSpeed, 0)) + " Attack Speed\"}");
				}

				if (loreList.contains(attackSpeedNBT))
				{

				}
				else
				{
					loreList.add(attackSpeedNBT);
				}

				displayList.put("Lore", loreList);
				return;
			}

			ListNBT loreList = displayList.getList("Lore", 8);

			double attackDamage = NBTHelper.getAttackDamage(player) + 1;
			double attackSpeed = NBTHelper.getAttackSpeed(player);

			StringNBT space = StringNBT.valueOf("{\"text\": \"\"}");
			StringNBT extra = StringNBT.valueOf("{\"text\": \"\u00a77When in Main Hand\"}");


			if (loreList.contains(space))
			{
			}
			else
			{
				loreList.add(space);
			}

			if (loreList.contains(extra))
			{
			}
			else
			{
				loreList.add(extra);
			}

			StringNBT attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
					+ String.valueOf(MathUtils.roundNearestPlace(attackDamage, -1)) + " Attack Damage\"}");

			if (MathUtils.roundNearestPlace(attackDamage, -1) == MathUtils.roundNearestPlace(attackDamage, 0))
			{
				attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
						+ String.valueOf((int) MathUtils.roundNearestPlace(attackDamage, 0)) + " Attack Damage\"}");
			}

			if (loreList.contains(attackDamageNBT))
			{
			}
			else
			{
				loreList.add(attackDamageNBT);
			}

			StringNBT attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
					+ String.valueOf(MathUtils.roundNearestPlace(attackSpeed, -1)) + " Attack Speed\"}");

			if (MathUtils.roundNearestPlace(attackSpeed, -1) == MathUtils.roundNearestPlace(attackSpeed, 0))
			{
				attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
						+ String.valueOf((int) MathUtils.roundNearestPlace(attackSpeed, 0)) + " Attack Speed\"}");
			}

			if (loreList.contains(attackSpeedNBT))
			{

			}
			else
			{
				loreList.add(attackSpeedNBT);
			}

			displayList.put("Lore", loreList);
			return;
		}

		CompoundNBT loreTag = new CompoundNBT();
		ListNBT loreList = new ListNBT();

		double attackDamage = NBTHelper.getAttackDamage(player) + 1;
		double attackSpeed = NBTHelper.getAttackSpeed(player);

		StringNBT space = StringNBT.valueOf("{\"text\": \"\"}");
		StringNBT extra = StringNBT.valueOf("{\"text\": \"\u00a77When in Main Hand\"}");

		if (loreList.contains(space))
		{
		}
		else
		{
			loreList.add(space);
		}

		if (loreList.contains(extra))
		{
		}
		else
		{
			loreList.add(extra);
		}

		StringNBT attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
				+ String.valueOf(MathUtils.roundNearestPlace(attackDamage, -1)) + " Attack Damage\"}");

		if (MathUtils.roundNearestPlace(attackDamage, -1) == MathUtils.roundNearestPlace(attackDamage, 0))
		{
			attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
					+ String.valueOf((int) MathUtils.roundNearestPlace(attackDamage, 0)) + " Attack Damage\"}");
		}

		if (loreList.contains(attackDamageNBT))
		{
		}
		else
		{
			loreList.add(attackDamageNBT);
		}

		StringNBT attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
				+ String.valueOf(MathUtils.roundNearestPlace(attackSpeed, -1)) + " Attack Speed\"}");

		if (MathUtils.roundNearestPlace(attackSpeed, -1) == MathUtils.roundNearestPlace(attackSpeed, 0))
		{
			attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
					+ String.valueOf((int) MathUtils.roundNearestPlace(attackSpeed, 0)) + " Attack Speed\"}");
		}

		if (loreList.contains(attackSpeedNBT))
		{

		}
		else
		{
			loreList.add(attackSpeedNBT);
		}

		loreTag.put("Lore", loreList);
		tag.put("display", loreTag);
		return;
	}

	public static double getAttackDamage(PlayerEntity player)
	{
		double attackDamage = AttributeHelper.getAttackDamage(player.getHeldItemMainhand().getItem())
				+ NBTHelper.getSharpnessDamage(player)
				+ (player.getAttributeValue(Attributes.ATTACK_DAMAGE)
						- AttributeHelper.getAttackDamage(player.getHeldItemMainhand().getItem())
						- AttributeHelper.getStrengthEffect(player)
						- player.getBaseAttributeValue(Attributes.ATTACK_DAMAGE));
		attackDamage = MathUtils.roundNearestPlace(attackDamage, -1);
		return attackDamage;
	}

	public static double getAttackSpeed(PlayerEntity player)
	{
		double attackSpeed = player.getAttributeValue(Attributes.ATTACK_SPEED);
		attackSpeed = MathUtils.roundNearestPlace(attackSpeed, -1);
		return attackSpeed;
	}

	public static double getSharpnessDamage(PlayerEntity player)
	{
		int sharpnessLvl = ModEnchantmentHelper.getSharpnessLevel(player);

		if (sharpnessLvl > 0)
		{
			return (sharpnessLvl * 0.5D) + 0.5D;
		}

		return 0;
	}

	public static void updateAttributeLore(ItemStack stack, PlayerEntity player)
	{
		double attackDamage = NBTHelper.getAttackDamage(player) + 1;
		double attackSpeed = NBTHelper.getAttackSpeed(player);
		CompoundNBT tag = stack.getTag();

		if (tag.contains("display", 10))
		{
			CompoundNBT display = tag.getCompound("display");

			if (display.contains("Lore", 9))
			{
				ListNBT lore = display.getList("Lore", 8);

				for (int x = 0; x < lore.size(); x++)
				{
					String s = lore.getString(x);

					if (s.contains(" Attack Speed\"}")
							&& attackSpeed != player.getBaseAttributeValue(Attributes.ATTACK_SPEED))
					{

						if (s.contains(String.valueOf(MathUtils.roundNearestPlace(attackSpeed, -1))))
						{
							return;
						}

						lore.remove(x);
						StringNBT attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
								+ String.valueOf(MathUtils.roundNearestPlace(attackSpeed, -1)) + " Attack Speed\"}");

						if (MathUtils.roundNearestPlace(attackSpeed, -1) == MathUtils.roundNearestPlace(attackSpeed, 0))
						{
							attackSpeedNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
									+ String.valueOf((int) MathUtils.roundNearestPlace(attackSpeed, 0))
									+ " Attack Speed\"}");
						}

						lore.add(x, attackSpeedNBT);
					}

					if (s.contains(" Attack Damage\"}")
							&& attackDamage != player.getBaseAttributeValue(Attributes.ATTACK_DAMAGE))
					{

						if (s.contains(String.valueOf(MathUtils.roundNearestPlace(attackDamage, -1))))
						{
							return;
						}

						lore.remove(x);
						StringNBT attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
								+ String.valueOf(MathUtils.roundNearestPlace(attackDamage, -1)) + " Attack Damage\"}");

						if (MathUtils.roundNearestPlace(attackDamage, -1) == MathUtils.roundNearestPlace(attackDamage,
								0))
						{
							attackDamageNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
									+ String.valueOf((int) MathUtils.roundNearestPlace(attackDamage, 0))
									+ " Attack Damage\"}");
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

		if (tag.contains("display", 10) && attrValue > attr.getDefaultValue())
		{
			CompoundNBT display = tag.getCompound("display");

			if (display.contains("Lore", 9))
			{
				ListNBT lore = display.getList("Lore", 8);

				for (int x = 0; x < lore.size(); x++)
				{
					String s = lore.getString(x);

					if (s.contains(" " + displayName + "\"}"))
					{

						if (s.contains(String.valueOf(MathUtils.roundNearestPlace(attrValue, -1))))
						{
							return;
						}

						lore.remove(x);
						StringNBT newAttrNBT = StringNBT.valueOf(
								"{\"text\": \" \u00a72" + String.valueOf(MathUtils.roundNearestPlace(attrValue, -1))
										+ " " + displayName + "\"}");

						if (MathUtils.roundNearestPlace(attrValue, -1) == MathUtils.roundNearestPlace(attrValue, 0))
						{
							newAttrNBT = StringNBT.valueOf("{\"text\": \" \u00a72"
									+ String.valueOf((int) MathUtils.roundNearestPlace(attrValue, 0)) + " "
									+ displayName + "\"}");
						}

						lore.add(x, newAttrNBT);
					}

				}

			}

		}

	}

}
