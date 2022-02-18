package com.yeetmanlord.somanyenchants.core.events;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.AttributeInit;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.yeetmanlord.somanyenchants.core.network.message.AttackPacket;
import com.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;
import com.yeetmanlord.somanyenchants.core.util.PlayerAttributeHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
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

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class ReachEnchantments
{

	@SubscribeEvent
	public static void attackReach(final LivingEquipmentChangeEvent event)
	{
		if (Config.attackReach.isEnabled.get() == true)
		{
			if (event.getEntityLiving() instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity)event.getEntityLiving();
				ItemStack to = event.getTo();
				ItemStack from = event.getFrom();
				EquipmentSlotType slot = event.getSlot();
				if(slot == EquipmentSlotType.MAINHAND) {
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
			if (event.getEntityLiving() instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity)event.getEntityLiving();
				ItemStack to = event.getTo();
				ItemStack from = event.getFrom();
				EquipmentSlotType slot = event.getSlot();
				if(slot == EquipmentSlotType.MAINHAND) {
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
		PlayerEntity player = mc.player;

		if (click.isAttack() && player != null)
		{
			// Handles raytracing
			double reachDist = 4.0D;

			if (player.getAttributeManager().hasAttributeInstance(AttributeInit.ATTACK_DISTANCE.get()))
			{
				reachDist = player.getAttribute(AttributeInit.ATTACK_DISTANCE.get()).getValue();
			}

			Vector3d startVector = player.getEyePosition(1.0F);
			Vector3d lookVector = player.getLook(1.0F);
			Vector3d endVector = startVector.add(lookVector.x * reachDist, lookVector.y * reachDist,
					lookVector.z * reachDist);
			AxisAlignedBB axisalignedbb = player.getBoundingBox().expand(lookVector.scale(reachDist)).grow(1.0D, 1.0D,
					1.0D);
			EntityRayTraceResult entityRayTrace = ProjectileHelper.rayTraceEntities(player, startVector, endVector,
					axisalignedbb, (p_215312_0_) ->
					{
						return !p_215312_0_.isSpectator() && p_215312_0_.canBeCollidedWith();
					}, reachDist * reachDist);

			if (entityRayTrace != null)
			{
				// Gets entity
				Entity tracedEntity = entityRayTrace.getEntity();

				if (player.getDistanceSq(tracedEntity) > 9.0D
						&& player.getDistanceSq(tracedEntity) <= Math.pow(reachDist, 2.0D) && !player.isCreative())
				{
					NetworkHandler.CHANNEL.sendToServer(new AttackPacket(tracedEntity.getEntityId()));
				}
				else if (player.getDistanceSq(tracedEntity) > 36.0D
						&& player.getDistanceSq(tracedEntity) <= Math.pow(reachDist, 2.0D) && player.isCreative())
				{
					NetworkHandler.CHANNEL.sendToServer(new AttackPacket(tracedEntity.getEntityId()));
				}

				return;
			}
			else if (entityRayTrace == null || player.isSpectator())
			{

			}
			else
			{
				Main.LOGGER.error("Ray trace failed. This is not a good thing!!");
				Main.LOGGER.error(entityRayTrace);
				return;
			}

		}

	}
}
