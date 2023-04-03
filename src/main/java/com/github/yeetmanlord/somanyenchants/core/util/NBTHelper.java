package com.github.yeetmanlord.somanyenchants.core.util;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;

public class NBTHelper
{

	public static double getAttackDamage(Player player)
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

	public static double getAttackSpeed(Player player)
	{
		double attackSpeed = player.getAttributeValue(Attributes.ATTACK_SPEED);
		attackSpeed = MathUtils.roundNearestPlace(attackSpeed, -1);
		return attackSpeed;
	}

	public static double getSharpnessDamage(Player player)
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
			CompoundTag tag = stack.getTag();
			if (tag.contains("AttributeData")) {
				CompoundTag data = tag.getCompound("AttributeData");
				return data.getDouble(Registry.ATTRIBUTE.getKey(attr).toString());
			}
		}
		return 0d;
	}
	
	public static void writeAttributeValue(ItemStack stack, double value, Attribute attr) {
		CompoundTag nbt = stack.getTag();
		CompoundTag tag = new CompoundTag();
		if(nbt.contains("AttributeData")) {
			tag = nbt.getCompound("AttributeData");
		}
		tag.putDouble(Registry.ATTRIBUTE.getKey(attr).toString(), value);
		nbt.put("AttributeData", tag);
	}

}
