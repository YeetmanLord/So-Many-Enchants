package com.github.yeetmanlord.somanyenchants.core.events;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.AttributeInit;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.github.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.github.yeetmanlord.somanyenchants.core.network.message.AttackPacket;
import com.github.yeetmanlord.somanyenchants.core.util.AttributeHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent.ClickInputEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class ReachEnchantments {

	@SubscribeEvent
	public static void reachEnchantments(final LivingEquipmentChangeEvent event) {

		if (event.getEntityLiving() instanceof PlayerEntity) {
			EquipmentSlotType slot = event.getSlot();

			if (slot == EquipmentSlotType.MAINHAND) {
				AttributeHelper.apply(EnchantmentInit.BLOCK_REACH.get(), ForgeMod.REACH_DISTANCE.get(), Config.blockReach, event, 1d);
				AttributeHelper.apply(EnchantmentInit.ATTACK_REACH.get(), AttributeInit.ATTACK_DISTANCE.get(), Config.attackReach, event, 1.5d);
			}

		}

	}

	@SuppressWarnings("unused") @SubscribeEvent @OnlyIn(Dist.CLIENT)
	public static void extraReach(final ClickInputEvent click) {

		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;

		if (click.isAttack() && player != null) {
			// Handles raytracing
			double reachDist = 4.0D;

			if (player.getAttributes().hasAttribute(AttributeInit.ATTACK_DISTANCE.get())) {
				reachDist = player.getAttribute(AttributeInit.ATTACK_DISTANCE.get()).getValue();
			}

			Vector3d startVector = player.getEyePosition(1.0F);
			Vector3d lookVector = player.getViewVector(1.0F);
			Vector3d endVector = startVector.add(lookVector.x * reachDist, lookVector.y * reachDist, lookVector.z * reachDist);
			AxisAlignedBB axisalignedbb = player.getBoundingBox().expandTowards(lookVector.scale(reachDist)).inflate(1.0D, 1.0D, 1.0D);
			EntityRayTraceResult entityRayTrace = ProjectileHelper.getEntityHitResult(player, startVector, endVector, axisalignedbb, (p_215312_0_) -> {
				return !p_215312_0_.isSpectator() && p_215312_0_.isPickable();
			}, reachDist * reachDist);

			if (entityRayTrace != null) {
				// Gets entity
				Entity tracedEntity = entityRayTrace.getEntity();

				if (player.distanceToSqr(tracedEntity) > 9.0D && player.distanceToSqr(tracedEntity) <= Math.pow(reachDist, 2.0D) && !player.isCreative()) {
					NetworkHandler.CHANNEL.sendToServer(new AttackPacket(tracedEntity.getId()));
				}
				else if (player.distanceToSqr(tracedEntity) > 36.0D && player.distanceToSqr(tracedEntity) <= Math.pow(reachDist, 2.0D) && player.isCreative()) {
					NetworkHandler.CHANNEL.sendToServer(new AttackPacket(tracedEntity.getId()));
				}

				return;
			}
			else if (entityRayTrace == null || player.isSpectator()) {

			}
			else {
				SoManyEnchants.LOGGER.error("Ray trace failed. This is not a good thing!!");
				SoManyEnchants.LOGGER.error(entityRayTrace);
				return;
			}

		}

	}

}
