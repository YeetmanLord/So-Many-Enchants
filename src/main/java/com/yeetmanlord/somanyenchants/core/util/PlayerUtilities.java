package com.yeetmanlord.somanyenchants.core.util;

import java.util.HashMap;

import com.yeetmanlord.somanyenchants.core.init.AttributeInit;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.ForgeMod;

public class PlayerUtilities {

	private float stepHeight;
	private float lastModifiedStepHeight;
	private PlayerEntity player;
	private HashMap<Attribute, Double> prevAttribBaseValue;
	
	
	public PlayerUtilities(PlayerEntity owner) 
	{
		this.prevAttribBaseValue = new HashMap<>();
		setStepHeight(0.6f);
		setLastModifiedStepHeight(0.6f);
		player = owner;
	}

	public void updatePrevAttributesBases() {
		prevAttribBaseValue.put(AttributeInit.ATTACK_DISTANCE.get(), player.getBaseAttributeValue(AttributeInit.ATTACK_DISTANCE.get()));
		prevAttribBaseValue.put(ForgeMod.REACH_DISTANCE.get(), player.getBaseAttributeValue(ForgeMod.REACH_DISTANCE.get()));
		prevAttribBaseValue.put(Attributes.ATTACK_SPEED, player.getBaseAttributeValue(Attributes.ATTACK_SPEED));
		prevAttribBaseValue.put(Attributes.ATTACK_DAMAGE, player.getBaseAttributeValue(Attributes.ATTACK_DAMAGE));
	}
	
	public void resetPrevAttributes() {
		prevAttribBaseValue.put(AttributeInit.ATTACK_DISTANCE.get(), 3d);
		prevAttribBaseValue.put(ForgeMod.REACH_DISTANCE.get(), 5d);
		prevAttribBaseValue.put(Attributes.ATTACK_SPEED, 4d);
		prevAttribBaseValue.put(Attributes.ATTACK_DAMAGE, 1d);
	}
	
	public double getPrevAttribute(Attribute attr) {
		return prevAttribBaseValue.get(attr);
	}
	
	public PlayerEntity getOwner() {
		return player;
	}


	public float getStepHeight() {
		return stepHeight;
	}


	public void setStepHeight(float stepHeight) {
		this.stepHeight = stepHeight;
	}


	public float getLastModifiedStepHeight() {
		return lastModifiedStepHeight;
	}


	public void setLastModifiedStepHeight(float lastModifiedStepHeight) {
		this.lastModifiedStepHeight = lastModifiedStepHeight;
	}

}
