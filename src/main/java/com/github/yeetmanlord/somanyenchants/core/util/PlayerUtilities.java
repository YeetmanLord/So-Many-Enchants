package com.github.yeetmanlord.somanyenchants.core.util;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerUtilities {

	private float stepHeight;
	private float lastModifiedStepHeight;
	private PlayerEntity player;
	
	
	public PlayerUtilities(PlayerEntity owner) 
	{
		setStepHeight(0.6f);
		setLastModifiedStepHeight(0.6f);
		player = owner;
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
