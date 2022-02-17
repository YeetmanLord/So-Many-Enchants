package com.yeetmanlord.somanyenchants.core.util;

import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

public class NBTHelper
{

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

}
