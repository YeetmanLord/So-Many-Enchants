package com.yeetmanlord.somanyenchants.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.init.AttributeInit;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.command.arguments.BlockStateParser;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

public class PlayerAttributeHandler {
	public static final UUID ATTACK_REACH_UUID = UUID.fromString("0ed05f3b-bb77-42e5-9dee-16c9a2e909f9");
	public static final UUID FORGE_REACH_UUID = UUID.fromString("5d445e83-f619-4e3b-8013-236ff0e4154c");
	protected static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
	protected static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

	public static void addToAttributeBase(PlayerEntity player, Attribute attr, double addon, ItemStack stack) {
		PlayerUtilities util = Main.getPlayerUtil(player);
		NBTHelper.writeAttributeValue(stack, addon, attr);
		player.getAttributeManager().createInstanceIfAbsent(attr)
				.setBaseValue(player.getBaseAttributeValue(attr) + addon);
	}

	public static void removeAttribute(PlayerEntity player, Attribute attr, ItemStack stack) {
		PlayerUtilities util = Main.getPlayerUtil(player);
		double value = NBTHelper.getAttributeValue(stack, attr);
		player.getAttributeManager().createInstanceIfAbsent(attr)
				.setBaseValue(player.getBaseAttributeValue(attr) - value);
	}

	public static void reset(PlayerEntity player) {
		Attribute[] attr = new Attribute[] { Attributes.ARMOR, Attributes.ARMOR_TOUGHNESS, Attributes.ATTACK_SPEED,
				Attributes.ATTACK_SPEED, Attributes.MAX_HEALTH, Attributes.KNOCKBACK_RESISTANCE,
				AttributeInit.ATTACK_DISTANCE.get(), ForgeMod.REACH_DISTANCE.get() };
		for (ItemStack armorPiece : player.inventory.armorInventory) {
			for (Attribute x : attr) {
				removeAttribute(player, x, armorPiece);
			}
		}

		for (Attribute x : attr) {
			removeAttribute(player, x, player.getHeldItemMainhand());
		}
	}

	@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
	public static class TooltipUpdate {
		@SubscribeEvent(priority = EventPriority.HIGHEST)
		public static void updateTooltip(final ItemTooltipEvent event) {
			ItemStack stack = event.getItemStack();
			PlayerEntity playerIn = event.getPlayer();
			List<ITextComponent> tooltips = event.getToolTip();
			HashMap<ITextComponent, Integer> map = new HashMap<>();
			for (int x = 0; x < tooltips.size(); x++) {
				map.put(tooltips.get(x), x);
			}

			List<ITextComponent> list = new ArrayList<>();
			List<ITextComponent> list1 = new ArrayList<>();
			List<ITextComponent> list2 = new ArrayList<>();

			int i = func_242393_J(stack);

			if (func_242394_a(i, ItemStack.TooltipDisplayFlags.MODIFIERS)) {
				for (EquipmentSlotType equipmentslottype : EquipmentSlotType.values()) {
					Multimap<Attribute, AttributeModifier> multimap = stack.getAttributeModifiers(equipmentslottype);
					if (!multimap.isEmpty()) {

						for (Entry<Attribute, AttributeModifier> entry : multimap.entries()) {
							AttributeModifier attributemodifier = entry.getValue();
							double d0 = attributemodifier.getAmount();
							boolean flag = false;
							if (playerIn != null) {
								if (attributemodifier.getID().equals(ATTACK_DAMAGE_MODIFIER)) {
									d0 = d0 + playerIn.getBaseAttributeValue(Attributes.ATTACK_DAMAGE);
									d0 = d0 + (double) EnchantmentHelper.getModifierForCreature(stack,
											CreatureAttribute.UNDEFINED);
									flag = true;
								} else if (attributemodifier.getID().equals(ATTACK_SPEED_MODIFIER)) {
									d0 += playerIn.getBaseAttributeValue(Attributes.ATTACK_SPEED);
									flag = true;
								}
							}

							double d1;
							if (attributemodifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE
									&& attributemodifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
								if (entry.getKey().equals(Attributes.KNOCKBACK_RESISTANCE)) {
									d1 = d0 * 10.0D;
								} else {
									d1 = d0;
								}
							} else {
								d1 = d0 * 100.0D;
							}
							if (flag) {
								list.add(
										(new StringTextComponent(" "))
												.appendSibling(new TranslationTextComponent(
														"attribute.modifier.equals."
																+ attributemodifier.getOperation().getId(),
														ItemStack.DECIMALFORMAT.format(d1),
														new TranslationTextComponent(
																entry.getKey().getAttributeName())))
												.mergeStyle(TextFormatting.DARK_GREEN));
							} else if (d0 > 0.0D) {
								list1.add((new TranslationTextComponent(
										"attribute.modifier.plus." + attributemodifier.getOperation().getId(),
										ItemStack.DECIMALFORMAT.format(d1),
										new TranslationTextComponent(entry.getKey().getAttributeName())))
												.mergeStyle(TextFormatting.BLUE));
							} else if (d0 < 0.0D) {
								d1 = d1 * -1.0D;
								list1.add((new TranslationTextComponent(
										"attribute.modifier.take." + attributemodifier.getOperation().getId(),
										ItemStack.DECIMALFORMAT.format(d1),
										new TranslationTextComponent(entry.getKey().getAttributeName())))
												.mergeStyle(TextFormatting.RED));
							}
						}
					}
				}

				if (list.size() > 0) {
					int x = map.get(list.get(list.size() - 1));
					if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.ATTACK_REACH.get(), stack)) {
						event.getToolTip().add(x,
								(new StringTextComponent(" "
										+ String.valueOf(ItemStack.DECIMALFORMAT.format(
												playerIn.getBaseAttributeValue(AttributeInit.ATTACK_DISTANCE.get())))
										+ " ").appendSibling(
												new TranslationTextComponent(
														AttributeInit.ATTACK_DISTANCE.get().getAttributeName()))
												.mergeStyle(TextFormatting.DARK_GREEN)));
					}

					if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.BLOCK_REACH.get(), stack)) {
						event.getToolTip().add(x,
								(new StringTextComponent(" "
										+ String.valueOf(ItemStack.DECIMALFORMAT
												.format(playerIn.getBaseAttributeValue(ForgeMod.REACH_DISTANCE.get())))
										+ " Block reach distance")).mergeStyle(TextFormatting.DARK_GREEN));
					}
				}

				else if (list1.size() > 0) {
					if (stack.getItem() instanceof ArmorItem) {
						for (EquipmentSlotType equipmentslottype : EquipmentSlotType.values()) {
							Multimap<Attribute, AttributeModifier> multimap = stack
									.getAttributeModifiers(equipmentslottype);
							if (!multimap.isEmpty()) {
								for (Entry<Attribute, AttributeModifier> entry : multimap.entries()) {
									AttributeModifier attributemodifier = entry.getValue();
									double d0 = attributemodifier.getAmount();
									boolean flag = false;
									if (playerIn != null) {
										if (attributemodifier.getID().equals(ATTACK_DAMAGE_MODIFIER)) {
											d0 = d0 + playerIn.getBaseAttributeValue(Attributes.ATTACK_DAMAGE);
											d0 = d0 + (double) EnchantmentHelper.getModifierForCreature(stack,
													CreatureAttribute.UNDEFINED);
											flag = true;
										} else if (attributemodifier.getID().equals(ATTACK_SPEED_MODIFIER)) {
											d0 += playerIn.getBaseAttributeValue(Attributes.ATTACK_SPEED);
											flag = true;
										}
									}

									double d1;
									if (attributemodifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE
											&& attributemodifier
													.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
										if (entry.getKey().equals(Attributes.KNOCKBACK_RESISTANCE)) {
											d1 = d0 * 10.0D;
										} else {
											d1 = d0;
										}
									} else {
										d1 = d0 * 100.0D;
									}
									double addon = 0d;
									if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.HEAVY.get(), stack)
											&& entry.getKey().getAttributeName()
													.equals(Attributes.KNOCKBACK_RESISTANCE.getAttributeName())) {
										addon += ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HEAVY.get(),
												stack);
									} else if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.TEMPER.get(), stack)
											&& entry.getKey().getAttributeName()
													.equals(Attributes.ARMOR_TOUGHNESS.getAttributeName())) {
										addon += ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.TEMPER.get(),
												stack);
									} else if (ModEnchantmentHelper.hasEnchant(EnchantmentInit.REINFORCEMENT.get(),
											stack)
											&& entry.getKey().getAttributeName()
													.equals(Attributes.ARMOR.getAttributeName())) {
										addon += ModEnchantmentHelper
												.getEnchantmentLevel(EnchantmentInit.REINFORCEMENT.get(), stack) * 2d;
									}
									if (d0 > 0.0D && !flag) {
										list2.add((new TranslationTextComponent(
												"attribute.modifier.plus." + attributemodifier.getOperation().getId(),
												ItemStack.DECIMALFORMAT.format(d1 + addon),
												new TranslationTextComponent(entry.getKey().getAttributeName())))
														.mergeStyle(TextFormatting.BLUE));
									} else if (d0 < 0.0D && !flag) {
										d1 = d1 * -1.0D;
										list2.add((new TranslationTextComponent(
												"attribute.modifier.take." + attributemodifier.getOperation().getId(),
												ItemStack.DECIMALFORMAT.format(d1 + addon),
												new TranslationTextComponent(entry.getKey().getAttributeName())))
														.mergeStyle(TextFormatting.RED));
									}
								}
							}
						}
						int y = 0;
						for (ITextComponent x : list1) {
							int z = map.get(x);
							event.getToolTip().set(z, list2.get(y));
							y++;
						}
						i = map.get(list1.get(list1.size() - 1));
						int level = ModEnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HEALTH_BOOST.get(), stack);
						if (level > 0) {
							event.getToolTip().add(i, (new TranslationTextComponent(
									"attribute.modifier.plus.0",
									ItemStack.DECIMALFORMAT.format(level * 2d),
									new TranslationTextComponent(Attributes.MAX_HEALTH.getAttributeName())
											)).mergeStyle(TextFormatting.BLUE));
						}
					}
				}
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static Collection<ITextComponent> getPlacementTooltip(String stateString) {
		try {
			BlockStateParser blockstateparser = (new BlockStateParser(new StringReader(stateString), true)).parse(true);
			BlockState blockstate = blockstateparser.getState();
			ResourceLocation resourcelocation = blockstateparser.getTag();
			boolean flag = blockstate != null;
			boolean flag1 = resourcelocation != null;
			if (flag || flag1) {
				if (flag) {
					return Lists.newArrayList(
							blockstate.getBlock().getTranslatedName().mergeStyle(TextFormatting.DARK_GRAY));
				}

				ITag<Block> itag = BlockTags.getCollection().get(resourcelocation);
				if (itag != null) {
					Collection<Block> collection = itag.getAllElements();
					if (!collection.isEmpty()) {
						return collection.stream().map(Block::getTranslatedName).map((p_222119_0_) -> {
							return p_222119_0_.mergeStyle(TextFormatting.DARK_GRAY);
						}).collect(Collectors.toList());
					}
				}
			}
		} catch (CommandSyntaxException commandsyntaxexception) {
		}

		return Lists.newArrayList((new StringTextComponent("missingno")).mergeStyle(TextFormatting.DARK_GRAY));
	}

	@OnlyIn(Dist.CLIENT)
	private static boolean func_242394_a(int p_242394_0_, ItemStack.TooltipDisplayFlags p_242394_1_) {
		return (p_242394_0_ & p_242394_1_.func_242397_a()) == 0;
	}

	@OnlyIn(Dist.CLIENT)
	private static int func_242393_J(ItemStack stack) {
		return stack.hasTag() && stack.getTag().contains("HideFlags", 99) ? stack.getTag().getInt("HideFlags") : 0;
	}
}
