package com.github.yeetmanlord.somanyenchants.core.util;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class NBTHelper
{

	public static double getAttackDamage(PlayerEntity player)
	{
		double attackDamage = AttributeHelper.getAttackDamage(player.getMainHandItem().getItem())
				+ NBTHelper.getSharpnessDamage(player)
				+ (player.getAttributeValue(Attributes.ATTACK_DAMAGE)
						- AttributeHelper.getAttackDamage(player.getMainHandItem().getItem())
						- AttributeHelper.getStrengthEffect(player)
						- player.getAttributeBaseValue(Attributes.ATTACK_DAMAGE));
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
	
	public static double getAttributeValue(ItemStack stack, Attribute attr) {
		if (stack.hasTag()) {
			CompoundNBT tag = stack.getTag();
			if (tag.contains("AttributeData")) {
				CompoundNBT data = tag.getCompound("AttributeData");
				return data.getDouble(attr.getDescriptionId());
			}
		}
		return 0d;
	}
	
	public static void writeAttributeValue(ItemStack stack, double value, Attribute attr) {
		CompoundNBT nbt = stack.getTag();
		CompoundNBT tag = new CompoundNBT();
		if(nbt.contains("AttributeData")) {
			tag = nbt.getCompound("AttributeData");
		}
		tag.putDouble(attr.getDescriptionId(), value);
		nbt.put("AttributeData", tag);
	}

}
