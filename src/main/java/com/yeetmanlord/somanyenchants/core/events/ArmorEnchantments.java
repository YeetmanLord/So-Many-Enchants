package com.yeetmanlord.somanyenchants.core.events;

import java.util.UUID;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;
import com.yeetmanlord.somanyenchants.core.network.NetworkHandler;
import com.yeetmanlord.somanyenchants.core.network.message.FlyingPacket;
import com.yeetmanlord.somanyenchants.core.util.AttributeHelper;
import com.yeetmanlord.somanyenchants.core.util.MathUtils;
import com.yeetmanlord.somanyenchants.core.util.ModEnchantmentHelper;
import com.yeetmanlord.somanyenchants.core.util.PlayerUtilities;
import com.yeetmanlord.somanyenchants.core.util.Scheduler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangeGameModeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.PacketDistributor;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class ArmorEnchantments {
	@SubscribeEvent
	public static void extraArmor(final LivingEquipmentChangeEvent event) {
		LivingEntity living = event.getEntityLiving();
		if (living instanceof PlayerEntity && Config.reinforcement.isEnabled.get() == true) {
			PlayerEntity player = (PlayerEntity) living;
			ItemStack head = player.inventory.armorInventory.get(3);
			ItemStack chest = player.inventory.armorInventory.get(2);
			ItemStack legs = player.inventory.armorInventory.get(1);
			ItemStack feet = player.inventory.armorInventory.get(0);
			UUID HeadSlot = new UUID(1234, 4321);
			UUID ChestSlot = new UUID(4321, 1234);
			UUID LegSlot = new UUID(-1234, -4321);
			UUID FootSlot = new UUID(-4321, -1234);
			if (head.isEnchanted()) {
				int ench = ModEnchantmentHelper.getReinforcementLevel(player, 3);
				if (ench >= 16) {
					ench = 16;
				}
				if (ench > 0) {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", head);
					if (!flag) {
						AttributeHelper.newAttribute(head, Attributes.ARMOR, "Armor Modifier",
								getArmorVal(head, EquipmentSlotType.HEAD) + (1.0D * ench), true, false, true, player,
								EquipmentSlotType.HEAD, HeadSlot);
					} else {
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", head);
						if (flag1) {
							AttributeHelper.changeAttribute(head, Attributes.ARMOR,
									getArmorVal(head, EquipmentSlotType.HEAD) + ench * 1.0D, "Armor Modifier", false,
									false, player, EquipmentSlotType.HEAD);
						}
					}
				} else {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", head);
					if (flag) {
						AttributeHelper.changeAttribute(head, Attributes.ARMOR,
								getArmorVal(head, EquipmentSlotType.HEAD), "Armor Modifier", false, false, player,
								EquipmentSlotType.HEAD);
					}
				}
			} else {
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", head);
				if (flag) {
					AttributeHelper.changeAttribute(head, Attributes.ARMOR, getArmorVal(head, EquipmentSlotType.HEAD),
							"Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
				}
			}
			if (chest.isEnchanted()) {
				int ench = ModEnchantmentHelper.getReinforcementLevel(player, 2);
				if (ench >= 16) {
					ench = 16;
				}
				if (ench > 0) {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", chest);
					if (!flag) {
						AttributeHelper.newAttribute(chest, Attributes.ARMOR, "Armor Modifier",
								getArmorVal(chest, EquipmentSlotType.CHEST) + (1.0D * ench), true, false, true, player,
								EquipmentSlotType.CHEST, ChestSlot);
					} else {
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", chest);
						if (flag1) {
							AttributeHelper.changeAttribute(chest, Attributes.ARMOR,
									getArmorVal(chest, EquipmentSlotType.CHEST) + ench * 1.0D, "Armor Modifier", false,
									false, player, EquipmentSlotType.CHEST);
						}
					}
				} else {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", chest);
					if (flag) {
						AttributeHelper.changeAttribute(chest, Attributes.ARMOR,
								getArmorVal(chest, EquipmentSlotType.CHEST), "Armor Modifier", false, false, player,
								EquipmentSlotType.CHEST);
					}
				}
			} else {
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", chest);
				if (flag) {
					AttributeHelper.changeAttribute(chest, Attributes.ARMOR,
							getArmorVal(chest, EquipmentSlotType.CHEST), "Armor Modifier", false, false, player,
							EquipmentSlotType.CHEST);
				}
			}
			if (legs.isEnchanted()) {
				int ench = ModEnchantmentHelper.getReinforcementLevel(player, 1);
				if (ench >= 16) {
					ench = 16;
				}
				if (ench > 0) {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", legs);
					if (!flag) {
						AttributeHelper.newAttribute(legs, Attributes.ARMOR, "Armor Modifier",
								getArmorVal(legs, EquipmentSlotType.LEGS) + (1.0D * ench), true, false, true, player,
								EquipmentSlotType.LEGS, LegSlot);
					} else {
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", legs);
						if (flag1) {
							AttributeHelper.changeAttribute(legs, Attributes.ARMOR,
									getArmorVal(legs, EquipmentSlotType.LEGS) + ench * 1.0D, "Armor Modifier", false,
									false, player, EquipmentSlotType.LEGS);
						}
					}
				} else {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", legs);
					if (flag) {
						AttributeHelper.changeAttribute(legs, Attributes.ARMOR,
								getArmorVal(legs, EquipmentSlotType.LEGS), "Armor Modifier", false, false, player,
								EquipmentSlotType.LEGS);
					}
				}
			} else {
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", legs);
				if (flag) {
					AttributeHelper.changeAttribute(legs, Attributes.ARMOR, getArmorVal(legs, EquipmentSlotType.LEGS),
							"Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
				}
			}
			if (feet.isEnchanted()) {
				int ench = ModEnchantmentHelper.getReinforcementLevel(player, 0);
				if (ench >= 16) {
					ench = 16;
				}
				if (ench > 0) {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", feet);
					if (!flag) {
						AttributeHelper.newAttribute(feet, Attributes.ARMOR, "Armor Modifier",
								getArmorVal(feet, EquipmentSlotType.FEET) + (1.0D * (double) ench), true, false, true,
								player, EquipmentSlotType.FEET, FootSlot);
					} else {
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", feet);
						if (flag1) {
							AttributeHelper.changeAttribute(feet, Attributes.ARMOR,
									getArmorVal(feet, EquipmentSlotType.FEET) + ench * 1.0D, "Armor Modifier", false,
									false, player, EquipmentSlotType.FEET);
						}
					}
				} else {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", feet);
					if (flag) {
						AttributeHelper.changeAttribute(feet, Attributes.ARMOR,
								getArmorVal(feet, EquipmentSlotType.FEET), "Armor Modifier", false, false, player,
								EquipmentSlotType.FEET);
					}
				}
			} else {
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier", feet);
				if (flag) {
					AttributeHelper.changeAttribute(feet, Attributes.ARMOR, getArmorVal(feet, EquipmentSlotType.FEET),
							"Armor Modifier", false, false, player, EquipmentSlotType.FEET);
				}
			}
		}
	}

	private static double getArmorVal(ItemStack stack, EquipmentSlotType slotIn) {
		if (stack.getItem() instanceof ArmorItem) {
			ArmorItem armor = (ArmorItem) stack.getItem();
			float baseArmor = armor.getArmorMaterial().getDamageReductionAmount(slotIn);
			float kbresistance = armor.getArmorMaterial().getKnockbackResistance();
			float toughness = armor.getArmorMaterial().getToughness();
			return baseArmor;
		}
		return 0;
	}

	@SubscribeEvent
	public static void extraToughness(final LivingEquipmentChangeEvent event) {
		LivingEntity living = event.getEntityLiving();
		if (living instanceof PlayerEntity && Config.temper.isEnabled.get() == true) {
			PlayerEntity player = (PlayerEntity) living;
			ItemStack head = player.inventory.armorInventory.get(3);
			ItemStack chest = player.inventory.armorInventory.get(2);
			ItemStack legs = player.inventory.armorInventory.get(1);
			ItemStack feet = player.inventory.armorInventory.get(0);
			UUID HeadSlot = new UUID(1234, 4321);
			UUID ChestSlot = new UUID(4321, 1234);
			UUID LegSlot = new UUID(-1234, -4321);
			UUID FootSlot = new UUID(-4321, -1234);
			if (head.isEnchanted()) {
				int ench = ModEnchantmentHelper.getTemperLevel(player, 3);
				if (ench >= 16) {
					ench = 16;
				}
				if (ench > 0) {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
							head);
					if (!flag) {
						AttributeHelper.newAttribute(head, Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
								getArmorVal(head) + (0.5D * ench), true, false, true, player, EquipmentSlotType.HEAD,
								HeadSlot);
					} else {
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
								head);
						if (flag1) {
							AttributeHelper.changeAttribute(head, Attributes.ARMOR_TOUGHNESS,
									getArmorVal(head) + ench * 0.5D, "Armor Modifier", false, false, player,
									EquipmentSlotType.HEAD);
						}
					}
				} else {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
							head);
					if (flag) {
						AttributeHelper.changeAttribute(head, Attributes.ARMOR_TOUGHNESS, getArmorVal(head),
								"Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
					}
				}
			} else {
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", head);
				if (flag) {
					AttributeHelper.changeAttribute(head, Attributes.ARMOR_TOUGHNESS, getArmorVal(head),
							"Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
				}
			}
			if (chest.isEnchanted()) {
				int ench = ModEnchantmentHelper.getTemperLevel(player, 2);
				if (ench >= 16) {
					ench = 16;
				}
				if (ench > 0) {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
							chest);
					if (!flag) {
						AttributeHelper.newAttribute(chest, Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
								getArmorVal(chest) + (0.5D * ench), true, false, true, player, EquipmentSlotType.CHEST,
								ChestSlot);
					} else {
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
								chest);
						if (flag1) {
							AttributeHelper.changeAttribute(chest, Attributes.ARMOR_TOUGHNESS,
									getArmorVal(chest) + ench * 0.5D, "Armor Modifier", false, false, player,
									EquipmentSlotType.CHEST);
						}
					}
				} else {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
							chest);
					if (flag) {
						AttributeHelper.changeAttribute(chest, Attributes.ARMOR_TOUGHNESS, getArmorVal(chest),
								"Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
					}
				}
			} else {
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", chest);
				if (flag) {
					AttributeHelper.changeAttribute(chest, Attributes.ARMOR_TOUGHNESS, getArmorVal(chest),
							"Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
				}
			}
			if (legs.isEnchanted()) {
				int ench = ModEnchantmentHelper.getTemperLevel(player, 1);
				if (ench >= 16) {
					ench = 16;
				}
				if (ench > 0) {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
							legs);
					if (!flag) {
						AttributeHelper.newAttribute(legs, Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
								getArmorVal(legs) + (0.5D * ench), true, false, true, player, EquipmentSlotType.LEGS,
								LegSlot);
					} else {
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
								legs);
						if (flag1) {
							AttributeHelper.changeAttribute(legs, Attributes.ARMOR_TOUGHNESS,
									getArmorVal(legs) + ench * 0.5D, "Armor Modifier", false, false, player,
									EquipmentSlotType.LEGS);
						}
					}
				} else {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
							legs);
					if (flag) {
						AttributeHelper.changeAttribute(legs, Attributes.ARMOR_TOUGHNESS, getArmorVal(legs),
								"Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
					}
				}
			} else {
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", legs);
				if (flag) {
					AttributeHelper.changeAttribute(legs, Attributes.ARMOR_TOUGHNESS, getArmorVal(legs),
							"Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
				}
			}
			if (feet.isEnchanted()) {
				int ench = ModEnchantmentHelper.getTemperLevel(player, 0);
				if (ench >= 16) {
					ench = 16;
				}
				if (ench > 0) {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
							feet);
					if (!flag) {
						AttributeHelper.newAttribute(feet, Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
								getArmorVal(feet) + (0.5D * (double) ench), true, false, true, player,
								EquipmentSlotType.FEET, FootSlot);
					} else {
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
								feet);
						if (flag1) {
							AttributeHelper.changeAttribute(feet, Attributes.ARMOR_TOUGHNESS,
									getArmorVal(feet) + ench * 0.5D, "Armor Modifier", false, false, player,
									EquipmentSlotType.FEET);
						}
					}
				} else {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
							feet);
					if (flag) {
						AttributeHelper.changeAttribute(feet, Attributes.ARMOR_TOUGHNESS, getArmorVal(feet),
								"Armor Modifier", false, false, player, EquipmentSlotType.FEET);
					}
				}
			} else {
				boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier", feet);
				if (flag) {
					AttributeHelper.changeAttribute(feet, Attributes.ARMOR_TOUGHNESS, getArmorVal(feet),
							"Armor Modifier", false, false, player, EquipmentSlotType.FEET);
				}
			}
		}
	}

	private static double getArmorVal(ItemStack stack) {
		if (stack.getItem() instanceof ArmorItem) {
			ArmorItem armor = (ArmorItem) stack.getItem();
			float toughness = armor.getArmorMaterial().getToughness();

			return toughness;
		}
		return 0;
	}

	@SubscribeEvent
	public static void applyFlight(final LivingEquipmentChangeEvent event) {
		LivingEntity living = event.getEntityLiving();
		if (living instanceof PlayerEntity && Config.flight.isEnabled.get() == true) {
			PlayerEntity player = (PlayerEntity) living;
			if (event.getSlot() == EquipmentSlotType.FEET && !player.isCreative() && !player.isSpectator()) {
				ItemStack newSlot = event.getTo();
				ItemStack oldSlot = event.getFrom();
				if (newSlot != ItemStack.EMPTY) {
					int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FLIGHT.get(), newSlot);
					if (level > 0 && !player.getShouldBeDead()) {
						player.abilities.allowFlying = true;
						player.sendPlayerAbilities();
						NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {return (ServerPlayerEntity) player;}), new FlyingPacket(true));
						
					}
				} else if (oldSlot != ItemStack.EMPTY) {
					int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FLIGHT.get(), oldSlot); 
					if (level > 0 && !player.getShouldBeDead()) {
						player.abilities.allowFlying = false;
						player.abilities.isFlying = false;
						player.sendPlayerAbilities();
						NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {return (ServerPlayerEntity) player;}), new FlyingPacket(false));
					}
				}
			}
		}
	}

	private static boolean enchant = false;
	private static boolean enchantChest = false;
	private static boolean enchantLegs = false;
	private static boolean enchantFeet = false;

	@SubscribeEvent
	public static void armorHPBoost(final LivingEquipmentChangeEvent event) {
		LivingEntity entity = event.getEntityLiving();
		if (entity instanceof PlayerEntity && Config.healthBoost.isEnabled.get() == true) {
			PlayerEntity player = (PlayerEntity) entity;
			if (player.inventory.armorInventory.get(3).isEnchanted()) {
				EquipmentSlotType slot = EquipmentSlotType.HEAD;
				ListNBT tagList = player.inventory.armorInventory.get(3).getEnchantmentTagList();
				for (int x = 0; x < tagList.size(); x++) {
					CompoundNBT idTag = tagList.getCompound(x);
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier",
							player.inventory.armorInventory.get(3))
							|| AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
									player.inventory.armorInventory.get(3))
							|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
									player.inventory.armorInventory.get(3));
					if (idTag.getString("id").matches("so_many_enchants:health_boost") && !enchant) {
						short enchantLvl = idTag.getShort("lvl");
						ItemStack armor = player.inventory.armorInventory.get(3);
						if (enchantLvl >= 1 && enchantLvl <= 16) {
							armor.addAttributeModifier(
									Attributes.MAX_HEALTH, new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64),
											"Armor Modifier", 1d * enchantLvl, AttributeModifier.Operation.ADDITION),
									slot);
							if (armor.getItem() instanceof ArmorItem && !flag) {
								float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getDamageReductionAmount(slot);
								float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getKnockbackResistance();
								float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
								if (toughness > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64),
													"Armor Modifier", toughness, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (baseArmor > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR, new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64),
													"Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (kbresistance > 0) {
									armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
											new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64), "Armor Modifier",
													kbresistance, AttributeModifier.Operation.ADDITION),
											slot);
								}
							}
							enchant = true;

						} else {
							armor.addAttributeModifier(Attributes.MAX_HEALTH,
									new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64), "Armor Modifier", 1d * 16,
											AttributeModifier.Operation.ADDITION),
									slot);
							if (armor.getItem() instanceof ArmorItem && !flag) {
								float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getDamageReductionAmount(slot);
								float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getKnockbackResistance();
								float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
								if (toughness > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64),
													"Armor Modifier", toughness, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (baseArmor > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR, new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64),
													"Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (kbresistance > 0) {
									armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
											new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64), "Armor Modifier",
													kbresistance, AttributeModifier.Operation.ADDITION),
											slot);
								}
							}
							enchant = true;
						}
					} else if (enchant == false) {
						ListNBT atList = player.inventory.armorInventory.get(3).getTag().getList("AttributeModifiers",
								10);
						for (int z = 0; z < atList.size(); z++) {
							CompoundNBT atTag = atList.getCompound(z);
							String n = atTag.getString("Name");
							String s = atTag.getString("AttributeName");
							if (s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier")) {
								ItemStack armor = player.inventory.armorInventory.get(3);
								atList.remove(z);
								if (armor.getItem() instanceof ArmorItem && !flag) {
									float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getDamageReductionAmount(slot);
									float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getKnockbackResistance();
									float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
									if (toughness > 0) {
										armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
												new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64), "Armor Modifier",
														toughness, AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (baseArmor > 0) {
										armor.addAttributeModifier(Attributes.ARMOR,
												new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64), "Armor Modifier",
														baseArmor, AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (kbresistance > 0) {
										armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
												new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64), "Armor Modifier",
														kbresistance, AttributeModifier.Operation.ADDITION),
												slot);
									}
								}
							}
						}
					}
				}
			} else {

				EquipmentSlotType slot = EquipmentSlotType.HEAD;
				if (player.inventory.armorInventory.get(3).hasTag()) {

					if (player.inventory.armorInventory.get(3).getTag().contains("AttributeModifiers", 10)) {
						ListNBT tagList = player.inventory.armorInventory.get(3).getTag().getList("AttributeModifiers",
								10);
						for (int x = 0; x < tagList.size(); x++) {
							CompoundNBT idTag = tagList.getCompound(x);
							boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier",
									player.inventory.armorInventory.get(3))
									|| AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
											player.inventory.armorInventory.get(3))
									|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE,
											"Armor Modifier", player.inventory.armorInventory.get(3));
							String n = idTag.getString("Name");
							String s = idTag.getString("AttributeName");
							if (s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier")) {
								ItemStack armor = player.inventory.armorInventory.get(3);
								tagList.remove(x);
								if (armor.getItem() instanceof ArmorItem && !flag) {
									float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getDamageReductionAmount(slot);
									float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getKnockbackResistance();
									float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
									if (toughness > 0) {
										armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
												new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64), "Armor Modifier",
														toughness, AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (baseArmor > 0) {
										armor.addAttributeModifier(Attributes.ARMOR,
												new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64), "Armor Modifier",
														baseArmor, AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (kbresistance > 0) {
										armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
												new AttributeModifier(new UUID(2 ^ 64, 2 ^ 64), "Armor Modifier",
														kbresistance, AttributeModifier.Operation.ADDITION),
												slot);
									}
								}
								enchant = false;
							}
						}
						return;
					}
				}
				enchant = false;

			}

			if (player.inventory.armorInventory.get(2).isEnchanted()) {
				EquipmentSlotType slot = EquipmentSlotType.CHEST;
				ListNBT tagList = player.inventory.armorInventory.get(2).getEnchantmentTagList();
				for (int x = 0; x < tagList.size(); x++) {
					CompoundNBT idTag = tagList.getCompound(x);
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier",
							player.inventory.armorInventory.get(2))
							|| AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
									player.inventory.armorInventory.get(2))
							|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
									player.inventory.armorInventory.get(2));
					if (idTag.getString("id").matches("so_many_enchants:health_boost") && !enchantChest) {
						short enchantLvl = idTag.getShort("lvl");
						ItemStack armor = player.inventory.armorInventory.get(2);
						if (enchantLvl >= 1 && enchantLvl <= 16) {
							armor.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(1, 2 ^ 64),
									"Armor Modifier", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), slot);
							if (armor.getItem() instanceof ArmorItem && !flag) {
								float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getDamageReductionAmount(slot);
								float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getKnockbackResistance();
								float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
								if (toughness > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2 ^ 64),
													"Armor Modifier", toughness, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (baseArmor > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR, new AttributeModifier(new UUID(1, 2 ^ 64),
													"Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (kbresistance > 0) {
									armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
											new AttributeModifier(new UUID(1, 2 ^ 64), "Armor Modifier", kbresistance,
													AttributeModifier.Operation.ADDITION),
											slot);
								}
							}
							enchantChest = true;

						} else if (enchantLvl > 16) {
							armor.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(1, 2 ^ 64),
									"Armor Modifier", 1d * 16, AttributeModifier.Operation.ADDITION), slot);
							if (armor.getItem() instanceof ArmorItem && !flag) {
								float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getDamageReductionAmount(slot);
								float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getKnockbackResistance();
								float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
								if (toughness > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2 ^ 64),
													"Armor Modifier", toughness, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (baseArmor > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR, new AttributeModifier(new UUID(1, 2 ^ 64),
													"Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (kbresistance > 0) {
									armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
											new AttributeModifier(new UUID(1, 2 ^ 64), "Armor Modifier", kbresistance,
													AttributeModifier.Operation.ADDITION),
											slot);
								}
							}
							enchantChest = true;
						}
					} else if (enchantChest == false) {
						ListNBT atList = player.inventory.armorInventory.get(2).getTag().getList("AttributeModifiers",
								10);
						for (int z = 0; z < atList.size(); z++) {
							CompoundNBT atTag = atList.getCompound(z);
							String n = idTag.getString("Name");
							String s = idTag.getString("AttributeName");
							if (s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier")) {
								ItemStack armor = player.inventory.armorInventory.get(2);
								atList.remove(z);
								if (armor.getItem() instanceof ArmorItem && !flag) {
									float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getDamageReductionAmount(slot);
									float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getKnockbackResistance();
									float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
									if (toughness > 0) {
										armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
												new AttributeModifier(new UUID(1, 2 ^ 64), "Armor Modifier", toughness,
														AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (baseArmor > 0) {
										armor.addAttributeModifier(Attributes.ARMOR,
												new AttributeModifier(new UUID(1, 2 ^ 64), "Armor Modifier", baseArmor,
														AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (kbresistance > 0) {
										armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
												new AttributeModifier(new UUID(1, 2 ^ 64), "Armor Modifier",
														kbresistance, AttributeModifier.Operation.ADDITION),
												slot);
									}
								}
							}
						}
					}
				}
			} else {
				if (player.inventory.armorInventory.get(2).hasTag()) {

					if (player.inventory.armorInventory.get(2).getTag().contains("AttributeModifiers", 10)) {
						ListNBT tagList = player.inventory.armorInventory.get(2).getTag().getList("AttributeModifiers",
								10);
						for (int x = 0; x < tagList.size(); x++) {
							EquipmentSlotType slot = EquipmentSlotType.CHEST;
							CompoundNBT idTag = tagList.getCompound(x);
							boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier",
									player.inventory.armorInventory.get(1))
									|| AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
											player.inventory.armorInventory.get(1))
									|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE,
											"Armor Modifier", player.inventory.armorInventory.get(1));
							String n = idTag.getString("Name");
							String s = idTag.getString("AttributeName");
							if (s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier")) {
								ItemStack armor = player.inventory.armorInventory.get(2);
								tagList.remove(x);
								if (armor.getItem() instanceof ArmorItem && !flag) {
									float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getDamageReductionAmount(slot);
									float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getKnockbackResistance();
									float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
									if (toughness > 0) {
										armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
												new AttributeModifier(new UUID(1, 2 ^ 64), "Armor Modifier", toughness,
														AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (baseArmor > 0) {
										armor.addAttributeModifier(Attributes.ARMOR,
												new AttributeModifier(new UUID(1, 2 ^ 64), "Armor Modifier", baseArmor,
														AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (kbresistance > 0) {
										armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
												new AttributeModifier(new UUID(1, 2 ^ 64), "Armor Modifier",
														kbresistance, AttributeModifier.Operation.ADDITION),
												slot);
									}
								}
								enchantChest = false;
							}
						}
						return;
					}
				}
				enchantChest = false;
			}
			if (player.inventory.armorInventory.get(1).isEnchanted()) {
				EquipmentSlotType slot = EquipmentSlotType.LEGS;
				ListNBT tagList = player.inventory.armorInventory.get(1).getEnchantmentTagList();
				for (int x = 0; x < tagList.size(); x++) {
					CompoundNBT idTag = tagList.getCompound(x);
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier",
							player.inventory.armorInventory.get(1))
							|| AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
									player.inventory.armorInventory.get(1))
							|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
									player.inventory.armorInventory.get(1));
					if (idTag.getString("id").matches("so_many_enchants:health_boost") && !enchantLegs) {
						short enchantLvl = idTag.getShort("lvl");
						ItemStack armor = player.inventory.armorInventory.get(1);
						if (enchantLvl >= 1 && enchantLvl <= 16) {
							armor.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(2, 2 ^ 64),
									"Armor Modifier", 1d * enchantLvl, AttributeModifier.Operation.ADDITION), slot);
							if (armor.getItem() instanceof ArmorItem && !flag) {
								float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getDamageReductionAmount(slot);
								float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getKnockbackResistance();
								float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
								if (toughness > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(1, 2 ^ 64),
													"Armor Modifier", toughness, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (baseArmor > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR, new AttributeModifier(new UUID(2, 2 ^ 64),
													"Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (kbresistance > 0) {
									armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
											new AttributeModifier(new UUID(2, 2 ^ 64), "Armor Modifier", kbresistance,
													AttributeModifier.Operation.ADDITION),
											slot);
								}
							}
							enchantLegs = true;

						} else if (enchantLvl > 16) {
							armor.addAttributeModifier(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(2, 2 ^ 64),
									"Armor Modifier", 1d * 16, AttributeModifier.Operation.ADDITION), slot);
							if (armor.getItem() instanceof ArmorItem && !flag) {
								float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getDamageReductionAmount(slot);
								float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getKnockbackResistance();
								float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
								if (toughness > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR_TOUGHNESS, new AttributeModifier(new UUID(2, 2 ^ 64),
													"Armor Modifier", toughness, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (baseArmor > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR, new AttributeModifier(new UUID(2, 2 ^ 64),
													"Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (kbresistance > 0) {
									armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
											new AttributeModifier(new UUID(2, 2 ^ 64), "Armor Modifier", kbresistance,
													AttributeModifier.Operation.ADDITION),
											slot);
								}
							}
							enchantLegs = true;
						}
					} else if (enchantLegs == false) {
						ListNBT atList = player.inventory.armorInventory.get(1).getTag().getList("AttributeModifiers",
								10);
						for (int z = 0; z < atList.size(); z++) {
							CompoundNBT atTag = atList.getCompound(z);
							String n = idTag.getString("Name");
							String s = idTag.getString("AttributeName");
							if (s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier")) {
								ItemStack armor = player.inventory.armorInventory.get(1);
								atList.remove(z);
								if (armor.getItem() instanceof ArmorItem && !flag) {
									float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getDamageReductionAmount(slot);
									float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getKnockbackResistance();
									float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
									if (toughness > 0) {
										armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
												new AttributeModifier(new UUID(2, 2 ^ 64), "Armor Modifier", toughness,
														AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (baseArmor > 0) {
										armor.addAttributeModifier(Attributes.ARMOR,
												new AttributeModifier(new UUID(2, 2 ^ 64), "Armor Modifier", baseArmor,
														AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (kbresistance > 0) {
										armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
												new AttributeModifier(new UUID(2, 2 ^ 64), "Armor Modifier",
														kbresistance, AttributeModifier.Operation.ADDITION),
												slot);
									}
								}
							}
						}
					}
				}
			} else {
				EquipmentSlotType slot = EquipmentSlotType.LEGS;
				if (player.inventory.armorInventory.get(1).hasTag()) {

					if (player.inventory.armorInventory.get(1).getTag().contains("AttributeModifiers", 10)) {
						ListNBT tagList = player.inventory.armorInventory.get(0).getTag().getList("AttributeModifiers",
								10);
						for (int x = 0; x < tagList.size(); x++) {
							boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier",
									player.inventory.armorInventory.get(1))
									|| AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
											player.inventory.armorInventory.get(1))
									|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE,
											"Armor Modifier", player.inventory.armorInventory.get(1));
							CompoundNBT idTag = tagList.getCompound(x);
							String n = idTag.getString("Name");
							String s = idTag.getString("AttributeName");
							if (s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier")) {
								ItemStack armor = player.inventory.armorInventory.get(1);
								tagList.remove(x);
								if (armor.getItem() instanceof ArmorItem && !flag) {
									float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getDamageReductionAmount(slot);
									float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getKnockbackResistance();
									float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
									if (toughness > 0) {
										armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
												new AttributeModifier(new UUID(1, 2 ^ 64), "Armor Modifier", toughness,
														AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (baseArmor > 0) {
										armor.addAttributeModifier(Attributes.ARMOR,
												new AttributeModifier(new UUID(1, 2 ^ 64), "Armor Modifier", baseArmor,
														AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (kbresistance > 0) {
										armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
												new AttributeModifier(new UUID(1, 2 ^ 64), "Armor Modifier",
														kbresistance, AttributeModifier.Operation.ADDITION),
												slot);
									}
								}
								enchantLegs = false;
							}
						}
						return;
					}
				}
				enchantLegs = false;
			}
			if (player.inventory.armorInventory.get(0).isEnchanted()) {
				EquipmentSlotType slot = EquipmentSlotType.FEET;
				ListNBT tagList = player.inventory.armorInventory.get(0).getEnchantmentTagList();
				for (int x = 0; x < tagList.size(); x++) {
					CompoundNBT idTag = tagList.getCompound(x);
					boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier",
							player.inventory.armorInventory.get(0))
							|| AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
									player.inventory.armorInventory.get(0))
							|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
									player.inventory.armorInventory.get(0));

					if (idTag.getString("id").matches("so_many_enchants:health_boost") && !enchantFeet) {
						short enchantLvl = idTag.getShort("lvl");
						ItemStack armor = player.inventory.armorInventory.get(0);
						if (enchantLvl >= 1 && enchantLvl <= 16) {

							armor.addAttributeModifier(
									Attributes.MAX_HEALTH, new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64),
											"Armor Modifier", 1d * enchantLvl, AttributeModifier.Operation.ADDITION),
									slot);
							if (armor.getItem() instanceof ArmorItem && !flag) {
								float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getDamageReductionAmount(slot);
								float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getKnockbackResistance();
								float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
								if (toughness > 0) {
									armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
											new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64), "Armor Modifier",
													toughness, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (baseArmor > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR, new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64),
													"Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (kbresistance > 0) {
									armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
											new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64), "Armor Modifier",
													kbresistance, AttributeModifier.Operation.ADDITION),
											slot);
								}
							}
							enchantFeet = true;

						} else if (enchantLvl > 16) {
							armor.addAttributeModifier(Attributes.MAX_HEALTH,
									new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64), "Armor Modifier", 1d * 16,
											AttributeModifier.Operation.ADDITION),
									slot);
							if (armor.getItem() instanceof ArmorItem && !flag) {
								float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getDamageReductionAmount(slot);
								float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
										.getKnockbackResistance();
								float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
								if (toughness > 0) {
									armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
											new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64), "Armor Modifier",
													toughness, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (baseArmor > 0) {
									armor.addAttributeModifier(
											Attributes.ARMOR, new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64),
													"Armor Modifier", baseArmor, AttributeModifier.Operation.ADDITION),
											slot);
								}
								if (kbresistance > 0) {
									armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
											new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64), "Armor Modifier",
													kbresistance, AttributeModifier.Operation.ADDITION),
											slot);
								}
							}
							enchantFeet = true;
						}
					} else if (enchantFeet == false) {
						ListNBT atList = player.inventory.armorInventory.get(0).getTag().getList("AttributeModifiers",
								10);
						for (int z = 0; z < atList.size(); z++) {
							CompoundNBT atTag = atList.getCompound(z);
							if (atTag.getString("AttributeName").matches("minecraft:generic.max_health")) {
								ItemStack armor = player.inventory.armorInventory.get(0);
								atList.remove(z);
								if (armor.getItem() instanceof ArmorItem && !flag) {
									float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getDamageReductionAmount(slot);
									float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getKnockbackResistance();
									float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
									if (toughness > 0) {
										armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
												new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64), "Armor Modifier",
														toughness, AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (baseArmor > 0) {
										armor.addAttributeModifier(Attributes.ARMOR,
												new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64), "Armor Modifier",
														baseArmor, AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (kbresistance > 0) {
										armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
												new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64), "Armor Modifier",
														kbresistance, AttributeModifier.Operation.ADDITION),
												slot);
									}
								}
							}
						}
					}
				}
			} else {
				EquipmentSlotType slot = EquipmentSlotType.FEET;
				if (player.inventory.armorInventory.get(0).hasTag()) {
					if (player.inventory.armorInventory.get(0).getTag().getList("AttributeModifiers", 10) != null) {
						ListNBT tagList = player.inventory.armorInventory.get(0).getTag().getList("AttributeModifiers",
								10);
						for (int x = 0; x < tagList.size(); x++) {
							CompoundNBT idTag = tagList.getCompound(x);
							boolean flag = AttributeHelper.isAttributePresent(Attributes.ARMOR, "Armor Modifier",
									player.inventory.armorInventory.get(0))
									|| AttributeHelper.isAttributePresent(Attributes.ARMOR_TOUGHNESS, "Armor Modifier",
											player.inventory.armorInventory.get(0))
									|| AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE,
											"Armor Modifier", player.inventory.armorInventory.get(0));
							String n = idTag.getString("Name");
							String s = idTag.getString("AttributeName");
							if (s.matches("minecraft.generic.max_health") && n.matches("Armor Modifier")) {
								ItemStack armor = player.inventory.armorInventory.get(0);
								tagList.remove(x);
								if (armor.getItem() instanceof ArmorItem && !flag) {
									float baseArmor = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getDamageReductionAmount(slot);
									float kbresistance = ((ArmorItem) armor.getItem()).getArmorMaterial()
											.getKnockbackResistance();
									float toughness = ((ArmorItem) armor.getItem()).getArmorMaterial().getToughness();
									if (toughness > 0) {
										armor.addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
												new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64), "Armor Modifier",
														toughness, AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (baseArmor > 0) {
										armor.addAttributeModifier(Attributes.ARMOR,
												new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64), "Armor Modifier",
														baseArmor, AttributeModifier.Operation.ADDITION),
												slot);
									}
									if (kbresistance > 0) {
										armor.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
												new AttributeModifier(new UUID(-(2 ^ 61), 2 ^ 64), "Armor Modifier",
														kbresistance, AttributeModifier.Operation.ADDITION),
												slot);
									}
								}
								enchantFeet = false;
							}
						}
						return;
					}
				}
				enchantFeet = false;

			}
		}
	}

	@SubscribeEvent
	public static void catVision(final PlayerTickEvent event) {
		PlayerEntity player = event.player;
		if (ModEnchantmentHelper.hasCatVision(player) && Config.catVision.isEnabled.get() == true) {
			player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 220, 0, false, false, false));
		}
	}

	@SubscribeEvent
	public static void extraKBResistance(final LivingEquipmentChangeEvent event) {
		LivingEntity living = event.getEntityLiving();
		if (living instanceof PlayerEntity && Config.heavyArmor.isEnabled.get() == true) {
			PlayerEntity player = (PlayerEntity) living;
			ItemStack head = player.inventory.armorInventory.get(3);
			ItemStack chest = player.inventory.armorInventory.get(2);
			ItemStack legs = player.inventory.armorInventory.get(1);
			ItemStack feet = player.inventory.armorInventory.get(0);
			UUID HeadSlot = new UUID(1234, 4321);
			UUID ChestSlot = new UUID(4321, 1234);
			UUID LegSlot = new UUID(-1234, -4321);
			UUID FootSlot = new UUID(-4321, -1234);
			if (head.isEnchanted()) {
				int ench = ModEnchantmentHelper.getHeavyArmorLevel(player, 3);
				if (ench >= 16) {
					ench = 16;
				}
				if (ench > 0) {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
							head);
					if (!flag) {
						AttributeHelper.newAttribute(head, Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
								getKBVal(head) + (0.05D * ench), true, false, true, player, EquipmentSlotType.HEAD,
								HeadSlot);
					} else {
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE,
								"Armor Modifier", head);
						if (flag1) {
							AttributeHelper.changeAttribute(head, Attributes.KNOCKBACK_RESISTANCE,
									getKBVal(head) + ench * 0.05D, "Armor Modifier", false, false, player,
									EquipmentSlotType.HEAD);
						}
					}
				} else {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
							head);
					if (flag) {
						AttributeHelper.changeAttribute(head, Attributes.KNOCKBACK_RESISTANCE, getKBVal(head),
								"Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
					}
				}
			} else {
				boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
						head);
				if (flag) {
					AttributeHelper.changeAttribute(head, Attributes.KNOCKBACK_RESISTANCE, getKBVal(head),
							"Armor Modifier", false, false, player, EquipmentSlotType.HEAD);
				}
			}
			if (chest.isEnchanted()) {
				int ench = ModEnchantmentHelper.getHeavyArmorLevel(player, 2);
				if (ench >= 16) {
					ench = 16;
				}
				if (ench > 0) {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
							chest);
					if (!flag) {
						AttributeHelper.newAttribute(chest, Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
								getKBVal(chest) + (0.05D * ench), true, false, true, player, EquipmentSlotType.CHEST,
								ChestSlot);
					} else {
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE,
								"Armor Modifier", chest);
						if (flag1) {
							AttributeHelper.changeAttribute(chest, Attributes.KNOCKBACK_RESISTANCE,
									getKBVal(chest) + ench * 0.05D, "Armor Modifier", false, false, player,
									EquipmentSlotType.CHEST);
						}
					}
				} else {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
							chest);
					if (flag) {
						AttributeHelper.changeAttribute(chest, Attributes.KNOCKBACK_RESISTANCE, getKBVal(chest),
								"Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
					}
				}
			} else {
				boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
						chest);
				if (flag) {
					AttributeHelper.changeAttribute(chest, Attributes.KNOCKBACK_RESISTANCE, getKBVal(chest),
							"Armor Modifier", false, false, player, EquipmentSlotType.CHEST);
				}
			}
			if (legs.isEnchanted()) {
				int ench = ModEnchantmentHelper.getHeavyArmorLevel(player, 1);
				if (ench >= 16) {
					ench = 16;
				}
				if (ench > 0) {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
							legs);
					if (!flag) {
						AttributeHelper.newAttribute(legs, Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
								getKBVal(legs) + (0.05D * ench), true, false, true, player, EquipmentSlotType.LEGS,
								LegSlot);
					} else {
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE,
								"Armor Modifier", legs);
						if (flag1) {
							AttributeHelper.changeAttribute(legs, Attributes.KNOCKBACK_RESISTANCE,
									getKBVal(legs) + ench * 0.05D, "Armor Modifier", false, false, player,
									EquipmentSlotType.LEGS);
						}
					}
				} else {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
							legs);
					if (flag) {
						AttributeHelper.changeAttribute(legs, Attributes.KNOCKBACK_RESISTANCE, getKBVal(legs),
								"Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
					}
				}
			} else {
				boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
						legs);
				if (flag) {
					AttributeHelper.changeAttribute(legs, Attributes.KNOCKBACK_RESISTANCE, getKBVal(legs),
							"Armor Modifier", false, false, player, EquipmentSlotType.LEGS);
				}
			}
			if (feet.isEnchanted()) {
				int ench = ModEnchantmentHelper.getHeavyArmorLevel(player, 0);
				if (ench >= 16) {
					ench = 16;
				}
				if (ench > 0) {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
							feet);
					if (!flag) {
						AttributeHelper.newAttribute(feet, Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
								getKBVal(feet) + (0.05D * (double) ench), true, false, true, player,
								EquipmentSlotType.FEET, FootSlot);
					} else {
						boolean flag1 = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE,
								"Armor Modifier", feet);
						if (flag1) {
							AttributeHelper.changeAttribute(feet, Attributes.KNOCKBACK_RESISTANCE,
									getKBVal(feet) + ench * 0.05D, "Armor Modifier", false, false, player,
									EquipmentSlotType.FEET);
						}
					}
				} else {
					boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
							feet);
					if (flag) {
						AttributeHelper.changeAttribute(feet, Attributes.KNOCKBACK_RESISTANCE, getKBVal(feet),
								"Armor Modifier", false, false, player, EquipmentSlotType.FEET);
					}
				}
			} else {
				boolean flag = AttributeHelper.isAttributePresent(Attributes.KNOCKBACK_RESISTANCE, "Armor Modifier",
						feet);
				if (flag) {
					AttributeHelper.changeAttribute(feet, Attributes.KNOCKBACK_RESISTANCE, getKBVal(feet),
							"Armor Modifier", false, false, player, EquipmentSlotType.FEET);
				}
			}
		}
	}

	private static double getKBVal(ItemStack stack) {
		if (stack.getItem() instanceof ArmorItem) {
			ArmorItem armor = (ArmorItem) stack.getItem();
			float kbresistance = armor.getArmorMaterial().getKnockbackResistance();
			return kbresistance;
		}
		return 0;
	}

	@SubscribeEvent
	public static void stepAssist(final LivingEquipmentChangeEvent event) {
		LivingEntity e = event.getEntityLiving();
		if (e instanceof PlayerEntity && Config.stepAssist.isEnabled.get() == true) {
			PlayerEntity player = (PlayerEntity) e;
			ItemStack a = event.getFrom();
			ItemStack b = event.getTo();
			PlayerUtilities util = Main.getPlayerUtil(player);
			if (event.getSlot() == EquipmentSlotType.FEET) {
				if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) > 0 && ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) <= 3) {
					player.stepHeight = player.stepHeight
							- ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) * 0.5f;
					util.setLastModifiedStepHeight(0.6f
							+ ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) * 0.5f);
					util.setStepHeight(player.stepHeight);
				}
				else if(ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) > 3)
				{
					player.stepHeight = player.stepHeight
							- 3 * 0.5f;
					util.setLastModifiedStepHeight(0.6f
							+ 3 * 0.5f);
					util.setStepHeight(player.stepHeight);
				}

				if (ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), b) > 0 && ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), b) <= 3) {
					util.setLastModifiedStepHeight(player.stepHeight
							- ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) * 0.5f);
					player.stepHeight = 0.6f
							+ ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), b) * 0.5f;
					util.setStepHeight(player.stepHeight);
				}
				else if(ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STEP_ASSIST.get(), a) > 3)
				{
					util.setLastModifiedStepHeight(player.stepHeight
							- 3 * 0.5f);
					player.stepHeight = 0.6f
							+ 3 * 0.5f;
					util.setStepHeight(player.stepHeight);
				}
			}
		}
	}

	@SubscribeEvent
	public static void updateStepAssist(final PlayerTickEvent event) {

		PlayerEntity player = event.player;
		PlayerUtilities util = Main.getPlayerUtil(player);
		if (ModEnchantmentHelper.getStepAssistLevel(player) > 0 && Config.stepAssist.isEnabled.get() == true) {
			player.stepHeight = util.getStepHeight();
		} else if (MathUtils.roundNearestPlace(util.getStepHeight(), -1) == 0.6f
				&& MathUtils.roundNearestPlace(util.getLastModifiedStepHeight(), -1) != MathUtils
						.roundNearestPlace(util.getStepHeight(), -1)
				&& ModEnchantmentHelper.getStepAssistLevel(player) == 0
				&& MathUtils.roundNearestPlace(player.stepHeight, -1) == MathUtils.roundNearestPlace(util.getLastModifiedStepHeight(), -1) && Config.stepAssist.isEnabled.get() == true) {
			player.stepHeight = 0.6f;
		}
	}
	
	public static class ClientAccess
	{
		public static void updatePlayerFlying(boolean flying) 
		{
			@SuppressWarnings("resource")
			ClientPlayerEntity player = Minecraft.getInstance().player;
			player.abilities.allowFlying = flying;
			player.sendPlayerAbilities();
		}
	}

	@SubscribeEvent
	public static void switchGM(final PlayerChangeGameModeEvent event)
	{
		PlayerEntity player = event.getPlayer();
		Scheduler sch = Main.getScheduler(player);
		sch.schedule(() -> new Runnable() {
			@Override
			public void run() 
			{
				ItemStack stack = player.inventory.armorInventory.get(0);
				int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FLIGHT.get(), stack);
				if(level > 0)
				{
					player.abilities.allowFlying = true;
					player.sendPlayerAbilities();
					NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {return (ServerPlayerEntity) player;}), new FlyingPacket(true));
				}
			}
		}, 0);
	}
}
