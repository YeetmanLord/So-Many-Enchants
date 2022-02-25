package com.yeetmanlord.somanyenchants.core.events;

import com.yeetmanlord.somanyenchants.SoManyEnchants;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.AttributeInit;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.yeetmanlord.somanyenchants.core.network.message.AttackPacket;
import com.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;
import com.yeetmanlord.somanyenchants.core.util.PlayerAttributeHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent.ClickInputEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SoManyEnchants.MOD_ID, bus = Bus.FORGE)
public class ReachEnchantments
{

	@SubscribeEvent
	public static void attackReach(final LivingEquipmentChangeEvent event)
	{
		if (Config.attackReach.isEnabled.get() == true)
		{
			if (event.getEntityLiving() instanceof Player) {
				Player player = (Player)event.getEntityLiving();
				ItemStack to = event.getTo();
				ItemStack from = event.getFrom();
				EquipmentSlot slot = event.getSlot();
				if(slot == EquipmentSlot.MAINHAND) {
					int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ATTACK_REACH.get(), to);
					if (level > 0)
					{
						double value = level * 1.5;
						PlayerAttributeHandler.addToAttributeBase(player, AttributeInit.ATTACK_DISTANCE.get(), value, to);
						return;
					}
					level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ATTACK_REACH.get(), from);
					if (level > 0) {
						PlayerAttributeHandler.removeAttribute(player, AttributeInit.ATTACK_DISTANCE.get(), from);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void blockReach(final LivingEquipmentChangeEvent event)
	{
		if (Config.blockReach.isEnabled.get() == true)
		{
			if (event.getEntityLiving() instanceof Player) {
				Player player = (Player)event.getEntityLiving();
				ItemStack to = event.getTo();
				ItemStack from = event.getFrom();
				EquipmentSlot slot = event.getSlot();
				if(slot == EquipmentSlot.MAINHAND) {
					int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.BLOCK_REACH.get(), to);
					if (level > 0)
					{
						double value = level * 1.5;
						PlayerAttributeHandler.addToAttributeBase(player, ForgeMod.REACH_DISTANCE.get(), value, to);
						return;
					}
					level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.BLOCK_REACH.get(), from);
					if (level > 0) {
						PlayerAttributeHandler.removeAttribute(player, ForgeMod.REACH_DISTANCE.get(), from);
					}
				}
			}
		}
	}

	@SuppressWarnings("unused")
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void extraReach(final ClickInputEvent click)
	{
		Minecraft mc = Minecraft.getInstance();
		Player player = mc.player;

		if (click.isAttack() && player != null)
		{
			// Handles raytracing
			double reachDist = 4.0D;

			if (player.getAttributes().hasAttribute(AttributeInit.ATTACK_DISTANCE.get()))
			{
				reachDist = player.getAttribute(AttributeInit.ATTACK_DISTANCE.get()).getValue();
			}

			Vec3 startVector = player.getEyePosition(1.0F);
			Vec3 lookVector = player.getViewVector(1.0F);
			Vec3 endVector = startVector.add(lookVector.x * reachDist, lookVector.y * reachDist,
					lookVector.z * reachDist);
			AABB axisalignedbb = player.getBoundingBox().expandTowards(lookVector.scale(reachDist)).inflate(1.0D, 1.0D,
					1.0D);
			EntityHitResult entityRayTrace = ProjectileUtil.getEntityHitResult(player, startVector, endVector,
					axisalignedbb, (p_215312_0_) ->
					{
						return !p_215312_0_.isSpectator() && p_215312_0_.isPickable();
					}, reachDist * reachDist);

			if (entityRayTrace != null)
			{
				// Gets entity
				Entity tracedEntity = entityRayTrace.getEntity();

				if (player.distanceToSqr(tracedEntity) > 9.0D
						&& player.distanceToSqr(tracedEntity) <= Math.pow(reachDist, 2.0D) && !player.isCreative())
				{
					NetworkHandler.CHANNEL.sendToServer(new AttackPacket(tracedEntity.getId()));
				}
				else if (player.distanceToSqr(tracedEntity) > 36.0D
						&& player.distanceToSqr(tracedEntity) <= Math.pow(reachDist, 2.0D) && player.isCreative())
				{
					NetworkHandler.CHANNEL.sendToServer(new AttackPacket(tracedEntity.getId()));
				}

				return;
			}
			else if (entityRayTrace == null || player.isSpectator())
			{

			}
			else
			{
				SoManyEnchants.LOGGER.error("Ray trace failed. This is not a good thing!!");
				SoManyEnchants.LOGGER.error(entityRayTrace);
				return;
			}

		}

	}
}
