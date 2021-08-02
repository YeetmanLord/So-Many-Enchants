package com.yeetmanlord.enchantsplus.core.events;

import com.yeetmanlord.enchantsplus.Main;
import com.yeetmanlord.enchantsplus.core.init.AttributeInit;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.InputEvent.ClickInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class ExtraReach 
{
	@SubscribeEvent
	public static void extraReach(final ClickInputEvent click)
	{
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity playerE = (PlayerEntity)mc.player;
		ServerPlayerEntity player = Minecraft.getInstance().getIntegratedServer().getPlayerList().getPlayerByUUID(playerE.getUniqueID());
		if(click.isAttack() && !click.isCanceled())
		{
			//Handles raytracing
			double reachDist = 4.0D;
			if(playerE.getAttributeManager().hasAttributeInstance(AttributeInit.ATTACK_DISTANCE.get()))
			{
				reachDist = playerE.getAttribute(AttributeInit.ATTACK_DISTANCE.get()).getValue();
			}
			Vector3d startVector = playerE.getEyePosition(1.0F);
			Vector3d lookVector = playerE.getLook(1.0F);
			Vector3d endVector = startVector.add(lookVector.x * reachDist, lookVector.y * reachDist, lookVector.z * reachDist);	
			AxisAlignedBB axisalignedbb = playerE.getBoundingBox().expand(lookVector.scale(reachDist)).grow(1.0D, 1.0D, 1.0D);
			EntityRayTraceResult entityRayTrace = ProjectileHelper.rayTraceEntities(player, startVector, endVector, axisalignedbb, (p_215312_0_) -> {
	               return !p_215312_0_.isSpectator() && p_215312_0_.canBeCollidedWith();
	            }, reachDist * reachDist);
			
			
			if(entityRayTrace != null && !player.isSpectator())
			{
				//Gets entity
				Entity tracedEntity = entityRayTrace.getEntity();
				if(player.getDistanceSq(tracedEntity) > 9.0D && player.getDistanceSq(tracedEntity) <= Math.pow(reachDist, 2.0D))
				{
					//Attacks the entity
					player.attackTargetEntityWithCurrentItem(tracedEntity);
				}
			} else if(entityRayTrace == null || player.isSpectator())
			{
				
			} else
			{
				Main.LOGGER.error("Ray trace failed. This is not a good thing!!");
				Main.LOGGER.info(entityRayTrace);
			}
		}
		
	}
}
