package com.github.yeetmanlord.somanyenchants.core.events;

import org.lwjgl.glfw.GLFW;

import com.github.yeetmanlord.somanyenchants.SoManyEnchants;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.AttributeInit;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.github.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.github.yeetmanlord.somanyenchants.core.network.message.AttackPacket;
import com.github.yeetmanlord.somanyenchants.core.util.AttributeHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class ReachEnchantments {

	@SubscribeEvent
	public static void reachEnchantments(final LivingEquipmentChangeEvent event) {

		if (event.getEntity() instanceof Player) {
			EquipmentSlot slot = event.getSlot();

			if (slot == EquipmentSlot.MAINHAND) {
				AttributeHelper.apply(EnchantmentInit.BLOCK_REACH.get(), ForgeMod.REACH_DISTANCE.get(), Config.blockReach, event, 1d);
				AttributeHelper.apply(EnchantmentInit.ATTACK_REACH.get(), AttributeInit.ATTACK_DISTANCE.get(), Config.attackReach, event, 1.5d);
			}

		}

	}

	@SuppressWarnings("unused")
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void extraReach(final InputEvent.MouseButton click) {

		Minecraft mc = Minecraft.getInstance();
		Player player = mc.player;

		if (click.getButton() == GLFW.GLFW_MOUSE_BUTTON_LEFT && player != null) {
			// Handles raytracing
			double reachDist = 4.0D;

			if (player.getAttributes().hasAttribute(AttributeInit.ATTACK_DISTANCE.get())) {
				reachDist = player.getAttribute(AttributeInit.ATTACK_DISTANCE.get()).getValue();
			}

			Vec3 startVector = player.getEyePosition(1.0F);
			Vec3 lookVector = player.getViewVector(1.0F);
			Vec3 endVector = startVector.add(lookVector.x * reachDist, lookVector.y * reachDist, lookVector.z * reachDist);
			AABB axisalignedbb = player.getBoundingBox().expandTowards(lookVector.scale(reachDist)).inflate(1.0D, 1.0D, 1.0D);
			EntityHitResult entityRayTrace = ProjectileUtil.getEntityHitResult(player, startVector, endVector, axisalignedbb, (p_215312_0_) -> {
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
