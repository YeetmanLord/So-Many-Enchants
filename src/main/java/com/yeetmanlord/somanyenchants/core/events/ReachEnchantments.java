package com.yeetmanlord.somanyenchants.core.events;

import java.util.UUID;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.AttributeInit;
import com.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.yeetmanlord.somanyenchants.core.network.message.AttackPacket;
import com.yeetmanlord.somanyenchants.core.util.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import net.minecraft.item.TridentItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent.ClickInputEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class ReachEnchantments {

	private static boolean enchanted = false;

	@SubscribeEvent
	public static void attackReach(final PlayerTickEvent event) {
		if (Config.a.isEnabled.get() == true) {
			boolean attributeFound = false;
			PlayerEntity player = event.player;
			ItemStack itemInHand = player.getHeldItemMainhand();

			if (itemInHand == ItemStack.EMPTY)
				return;
			if (itemInHand.isEnchanted() && !enchanted) {
				ListNBT enchants = itemInHand.getEnchantmentTagList();
				for (int x = 0; x < enchants.size(); x++) {
					CompoundNBT enchant = enchants.getCompound(x);
					if (enchant.getInt("lvl") <= Short.MAX_VALUE) {
						String id = enchant.getString("id");
						Short lvl = enchant.getShort("lvl");
						if (id.matches("so_many_enchants:attack_reach") && lvl >= 1) {
							ListNBT attributes = itemInHand.getTag().getList("AttributeModifiers", 10);
							for (int y = 0; y < attributes.size(); y++) {
								CompoundNBT attribute = attributes.getCompound(x);
								String name = attribute.getString("Name");
								String atName = attribute.getString("AttributeName");
								double amount = attribute.getDouble("amount");
								if (amount > 1024.0D) {
									attribute.putDouble("amount", 1024.0D);
									return;
								}
								if (atName.matches("so_many_enchants:player.attack_distance")
										&& name.matches("Mainhand modifier")) {
									return;
								} else if (!attributeFound) {
									attributeFound = false;
								}
							}
							itemInHand.addAttributeModifier(
									AttributeInit.ATTACK_DISTANCE.get(), new AttributeModifier(new UUID(123, 321),
											"Mainhand modifier", (double) lvl * 1.5D, Operation.ADDITION),
									EquipmentSlotType.MAINHAND);
							addModifiers(itemInHand, player, lvl);
							CompoundNBT nbt = itemInHand.getTag();
							nbt.putInt("HideFlags", 2);
							enchanted = true;
							return;
						}
					} else {
						enchant.putShort("lvl", Short.MAX_VALUE);
						return;
					}
				}
			}
			if (itemInHand.getAttributeModifiers(EquipmentSlotType.MAINHAND) != null
					&& (itemInHand.getItem() instanceof TieredItem || itemInHand.getItem() instanceof TridentItem)) {
				ListNBT attributes = itemInHand.getTag().getList("AttributeModifiers", 10);
				for (int x = 0; x < attributes.size(); x++) {
					CompoundNBT attribute = attributes.getCompound(x);
					String name = attribute.getString("Name");
					String atName = attribute.getString("AttributeName");
					double amount = attribute.getDouble("amount");
					if (amount > 1024.0D) {
						attribute.putDouble("amount", 1024.0D);
						return;
					}

					if (atName.matches("so_many_enchants:player.attack_distance")
							&& name.matches("Mainhand modifier")) {
						if (itemInHand.isEnchanted()) {
							ListNBT enchants = itemInHand.getEnchantmentTagList();
							for (int y = 0; y < enchants.size(); y++) {

								CompoundNBT enchant = enchants.getCompound(y);
								if (enchant.getInt("lvl") <= Short.MAX_VALUE) {
									String id = enchant.getString("id");
									if (id.matches("so_many_enchants:attack_reach")) {
										return;
									} else if (y < enchants.size() - 1) {

									} else if (y >= enchants.size() - 1) {
										NBTHelper.removeCustomAttributeLore(player.getHeldItemMainhand(),
												"Attack Reach Distance");
										attributes.remove(x);
										return;
									} else {
										Main.LOGGER.error("{} has invalid weapon attribute",
												(Object) player.getName().getString());
										return;
									}
								}
							}
						} else {
							NBTHelper.removeCustomAttributeLore(player.getHeldItemMainhand(), "Attack Reach Distance");
							attributeFound = false;
							attributes.remove(x);
							enchanted = false;
							return;
						}
					} else if (enchanted && x >= attributes.size()) {
						enchanted = false;
						return;
					}
				}
				if (enchanted) {
					enchanted = false;
				}
			}
		}
	}

	private static void addModifiers(ItemStack stack, PlayerEntity player, double reachLvl) {
		final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
		final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
		Item item = stack.getItem();
		double atSpeed = player.getBaseAttributeValue(Attributes.ATTACK_SPEED);
		Attribute reachDistBase = AttributeInit.ATTACK_DISTANCE.get();
		if (item instanceof SwordItem) {
			stack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
					"Mainhand modifier", NBTHelper.getAttackDamage(item), Operation.ADDITION),
					EquipmentSlotType.MAINHAND);
			stack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER,
					"Mainhand modifier", NBTHelper.getAttackSpeed(item), Operation.ADDITION),
					EquipmentSlotType.MAINHAND);
			NBTHelper.renderCustomAttributeLore(player, reachLvl * 1.5, reachDistBase, "Attack Reach Distance");
			return;
		} else if (!(item instanceof HoeItem)) {
			stack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
					"Mainhand modifier", NBTHelper.getAttackDamage(item), Operation.ADDITION),
					EquipmentSlotType.MAINHAND);
			stack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER,
					"Mainhand modifier", NBTHelper.getAttackSpeed(item), Operation.ADDITION),
					EquipmentSlotType.MAINHAND);
			NBTHelper.renderCustomAttributeLore(player, reachLvl * 1.5, reachDistBase, "Attack Reach Distance");
		} else if (item instanceof HoeItem) {
			NBTHelper.renderCustomAttributeLore(player, reachLvl * 1.5, reachDistBase, "Attack Reach Distance");
		} else
			return;
	}

	private static int wait = 0;
	private static boolean enchantedBlock = false;

	@SubscribeEvent
	public static void updateAttributeLore(final PlayerTickEvent event)
	{
		PlayerEntity player = event.player;
		ItemStack itemInHand = player.getHeldItemMainhand();
		if (wait >= 20 && itemInHand.hasTag()) {
			wait = 0;
			NBTHelper.updateAttributeLore(itemInHand, player);
			NBTHelper.updateAttributeLore(itemInHand, player, AttributeInit.ATTACK_DISTANCE.get(), "Attack Reach Distance");
			NBTHelper.updateAttributeLore(itemInHand, player, ForgeMod.REACH_DISTANCE.get(), "Block Reach Distance");
		} else if (wait >= 20) {
			wait = 0;
		}
		wait++;
	}
	
	
	@SubscribeEvent
	public static void blockReach(final PlayerTickEvent event) {
		if (Config.b.isEnabled.get() == true) {
			boolean attributeFound = false;
			PlayerEntity player = event.player;
			ItemStack itemInHand = player.getHeldItemMainhand();

			if (itemInHand == ItemStack.EMPTY)
				return;
			
			if (itemInHand.isEnchanted() && !enchantedBlock) {
				ListNBT enchants = itemInHand.getEnchantmentTagList();
				for (int x = 0; x < enchants.size(); x++) {
					CompoundNBT enchant = enchants.getCompound(x);
					if (enchant.getInt("lvl") <= Short.MAX_VALUE) {
						String id = enchant.getString("id");
						Short lvl = enchant.getShort("lvl");
						if (id.matches("so_many_enchants:block_reach") && lvl >= 1) {
							ListNBT attributes = itemInHand.getTag().getList("AttributeModifiers", 10);
							for (int y = 0; y < attributes.size(); y++) {
								CompoundNBT attribute = attributes.getCompound(x);
								String name = attribute.getString("Name");
								String atName = attribute.getString("AttributeName");
								double amount = attribute.getDouble("amount");
								if (amount > 1024.0D) {
									attribute.putDouble("amount", 1024.0D);
									return;
								}
								if (atName.matches("forge:reach_distance") && name.matches("")) {
									return;
								} else if (!attributeFound) {
									attributeFound = false;
								}
							}
							itemInHand.addAttributeModifier(ForgeMod.REACH_DISTANCE.get(),
									new AttributeModifier(new UUID(321, 123), "", (double) lvl, Operation.ADDITION),
									EquipmentSlotType.MAINHAND);
							addModifiersBlock(itemInHand, player, lvl);
							CompoundNBT nbt = itemInHand.getTag();
							nbt.putInt("HideFlags", 2);
							enchantedBlock = true;
							return;
						}
					} else {
						enchant.putShort("lvl", Short.MAX_VALUE);
						return;
					}
				}
			}
			if (itemInHand.getAttributeModifiers(EquipmentSlotType.MAINHAND) != null
					&& (itemInHand.getItem() instanceof TieredItem || itemInHand.getItem() instanceof TridentItem)) {
				ListNBT attributes = itemInHand.getTag().getList("AttributeModifiers", 10);
				for (int x = 0; x < attributes.size(); x++) {
					CompoundNBT attribute = attributes.getCompound(x);
					String name = attribute.getString("Name");
					String atName = attribute.getString("AttributeName");
					double amount = attribute.getDouble("amount");
					if (amount > 1024.0D) {
						attribute.putDouble("amount", 1024.0D);
						return;
					}

					if (atName.matches("forge:reach_distance") && name.matches("")) {
						if (itemInHand.isEnchanted()) {
							ListNBT enchants = itemInHand.getEnchantmentTagList();
							for (int y = 0; y < enchants.size(); y++) {

								CompoundNBT enchant = enchants.getCompound(y);
								if (enchant.getInt("lvl") <= Short.MAX_VALUE) {
									String id = enchant.getString("id");
									if (id.matches("so_many_enchants:block_reach")) {
										return;
									} else if (y < enchants.size() - 1) {

									} else if (y >= enchants.size() - 1) {
										NBTHelper.removeCustomAttributeLore(player.getHeldItemMainhand(),
												"Block Reach Distance");
										attributes.remove(x);
										enchantedBlock = false;
										return;
									} else {
										Main.LOGGER.warn("{} has invalid weapon attribute",
												(Object) player.getName().getString());
										return;
									}
								}
							}
						} else {
							NBTHelper.removeCustomAttributeLore(player.getHeldItemMainhand(), "Block Reach Distance");
							attributeFound = false;
							attributes.remove(x);
							enchantedBlock = false;
							return;
						}
					} else if (enchantedBlock && x >= attributes.size()) {
						enchantedBlock = false;
						return;
					}
				}
				if (enchantedBlock) {
					enchantedBlock = false;
				}
			}
		}
	}

	private static void addModifiersBlock(ItemStack stack, PlayerEntity player, double reachLvl) {
		final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
		final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
		Item item = stack.getItem();
		double atSpeed = player.getBaseAttributeValue(Attributes.ATTACK_SPEED);
		Attribute reachDistBase = ForgeMod.REACH_DISTANCE.get();
		if (item instanceof SwordItem) {
			stack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
					"Mainhand modifier", NBTHelper.getAttackDamage(item), Operation.ADDITION),
					EquipmentSlotType.MAINHAND);
			stack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER,
					"Mainhand modifier", NBTHelper.getAttackSpeed(item), Operation.ADDITION),
					EquipmentSlotType.MAINHAND);
			NBTHelper.renderCustomAttributeLore(player, reachLvl, reachDistBase, "Block Reach Distance");
			return;
		} else if (!(item instanceof HoeItem)) {
			stack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
					"Mainhand modifier", NBTHelper.getAttackDamage(item), Operation.ADDITION),
					EquipmentSlotType.MAINHAND);
			stack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER,
					"Mainhand modifier", NBTHelper.getAttackSpeed(item), Operation.ADDITION),
					EquipmentSlotType.MAINHAND);
			NBTHelper.renderCustomAttributeLore(player, reachLvl, reachDistBase, "Block Reach Distance");
		} else if (item instanceof HoeItem) {
			NBTHelper.renderCustomAttributeLore(player, reachLvl, reachDistBase, "Block Reach Distance");
		} else
			return;
	}

	@SuppressWarnings("unused")
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void extraReach(final ClickInputEvent click) {
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;
		if (click.isAttack() && player != null) {
			// Handles raytracing
			double reachDist = 4.0D;
			if (player.getAttributeManager().hasAttributeInstance(AttributeInit.ATTACK_DISTANCE.get())) {
				reachDist = player.getAttribute(AttributeInit.ATTACK_DISTANCE.get()).getValue();
			}
			Vector3d startVector = player.getEyePosition(1.0F);
			Vector3d lookVector = player.getLook(1.0F);
			Vector3d endVector = startVector.add(lookVector.x * reachDist, lookVector.y * reachDist,
					lookVector.z * reachDist);
			AxisAlignedBB axisalignedbb = player.getBoundingBox().expand(lookVector.scale(reachDist)).grow(1.0D, 1.0D,
					1.0D);
			EntityRayTraceResult entityRayTrace = ProjectileHelper.rayTraceEntities(player, startVector, endVector,
					axisalignedbb, (p_215312_0_) -> {
						return !p_215312_0_.isSpectator() && p_215312_0_.canBeCollidedWith();
					}, reachDist * reachDist);
			if (entityRayTrace != null) {
				// Gets entity
				Entity tracedEntity = entityRayTrace.getEntity();
				if (player.getDistanceSq(tracedEntity) > 9.0D
						&& player.getDistanceSq(tracedEntity) <= Math.pow(reachDist, 2.0D) && !player.isCreative()) {
					NetworkHandler.CHANNEL.sendToServer(new AttackPacket(tracedEntity.getEntityId()));
				} else if(player.getDistanceSq(tracedEntity) > 36.0D
						&& player.getDistanceSq(tracedEntity) <= Math.pow(reachDist, 2.0D) && player.isCreative())
				{
					NetworkHandler.CHANNEL.sendToServer(new AttackPacket(tracedEntity.getEntityId()));
				}
				return;
			} else if (entityRayTrace == null || player.isSpectator()) {

			} else {
				Main.LOGGER.error("Ray trace failed. This is not a good thing!!");
				Main.LOGGER.info(entityRayTrace);
				return;
			}
		}
	}
}
