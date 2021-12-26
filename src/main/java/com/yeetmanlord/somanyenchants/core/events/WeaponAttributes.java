package com.yeetmanlord.somanyenchants.core.events;

import java.util.UUID;

import com.yeetmanlord.somanyenchants.Main;
import com.yeetmanlord.somanyenchants.core.util.AttributeHelper;
import com.yeetmanlord.somanyenchants.core.util.NBTHelper;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.item.TridentItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class WeaponAttributes
{
	public static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
	public static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

	private static boolean enchanted = false;

	@SubscribeEvent
	public static void mainhand(final LivingEquipmentChangeEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			ItemStack itemInHand = player.getHeldItemMainhand();
			boolean attributeFound = false;

			if (event.getSlot() == EquipmentSlotType.MAINHAND && player.getHeldItemMainhand() != null)
			{
				ItemStack stack = player.getHeldItemMainhand();

				if (itemInHand.isEnchanted() && !enchanted)
				{
					ListNBT enchants = itemInHand.getEnchantmentTagList();

					for (int x = 0; x < enchants.size(); x++)
					{
						CompoundNBT enchant = enchants.getCompound(x);

						if (enchant.getInt("lvl") <= Short.MAX_VALUE)
						{
							String id = enchant.getString("id");
							Short lvl = enchant.getShort("lvl");

							if (id.matches("so_many_enchants:heavy_blade") && lvl >= 1)
							{
								ListNBT attributes = itemInHand.getTag().getList("AttributeModifiers", 10);

								for (int y = 0; y < attributes.size(); y++)
								{
									CompoundNBT attribute = attributes.getCompound(x);
									String name = attribute.getString("Name");
									String atName = attribute.getString("AttributeName");
									double amount = attribute.getDouble("amount");

									if (amount > 1024.0D)
									{
										attribute.putDouble("amount", 1024.0D);
										return;
									}
									if (atName.matches("minecraft:generic.attack_speed")
											&& name.matches("Mainhand modifier"))
									{
										return;
									}
									else if (!attributeFound)
									{
										attributeFound = false;
									}

								}
								float amount = (float) (-0.2 * lvl + AttributeHelper.getAttackSpeed(itemInHand.getItem()));
								if(amount <= -4) amount = (float) -3.9;
								itemInHand.addAttributeModifier(
										Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER,
												"Mainhand modifier", (double) amount, Operation.ADDITION),
										EquipmentSlotType.MAINHAND);
								addModifiers(itemInHand, player, (Math.max(0, lvl - 1) * 2 + 4));
								CompoundNBT nbt = itemInHand.getTag();
								nbt.putInt("HideFlags", 2);
								enchanted = true;
								return;
							}
							else if (id.matches("so_many_enchants:light_blade") && lvl >= 1)
							{
								ListNBT attributes = itemInHand.getTag().getList("AttributeModifiers", 10);

								for (int y = 0; y < attributes.size(); y++)
								{
									CompoundNBT attribute = attributes.getCompound(x);
									String name = attribute.getString("Name");
									String atName = attribute.getString("AttributeName");
									double amount = attribute.getDouble("amount");

									if (amount > 1024.0D)
									{
										attribute.putDouble("amount", 1024.0D);
									}
									if (atName.matches("minecraft:generic.attack_speed")
											&& name.matches("Mainhand modifier"))
									{
										return;
									}
									else if (!attributeFound)
									{
										attributeFound = false;
									}
								}
								float amount = (float) (lvl * 0.5 + AttributeHelper.getAttackSpeed(itemInHand.getItem()));
								itemInHand.addAttributeModifier(
										Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER,
												"Mainhand modifier", (double) amount, Operation.ADDITION),
										EquipmentSlotType.MAINHAND);
								addModifiers(itemInHand, player, ((Math.max(0, lvl - 1)) * -.5) - 1);
								CompoundNBT nbt = itemInHand.getTag();
								nbt.putInt("HideFlags", 2);
								enchanted = true;
								return;
							}
						}
						else
						{
							enchant.putShort("lvl", Short.MAX_VALUE);
							return;
						}

					}

				}
				if (itemInHand.getAttributeModifiers(EquipmentSlotType.MAINHAND) != null
						&& (itemInHand.getItem() instanceof TieredItem || itemInHand.getItem() instanceof TridentItem)
						&& itemInHand.getTag() != null)
				{
					ListNBT attributes = itemInHand.getTag().getList("AttributeModifiers", 10);

					for (int x = 0; x < attributes.size(); x++)
					{
						CompoundNBT attribute = attributes.getCompound(x);
						String name = attribute.getString("Name");
						String atName = attribute.getString("AttributeName");
						double amount = attribute.getDouble("amount");

						if (amount > 1024.0D)
						{
							attribute.putDouble("amount", 1024.0D);
							return;
						}

						if (atName.matches("minecraft:generic.attack_speed")
								&& name.matches("Mainhand modifier"))
						{

							if (itemInHand.isEnchanted())
							{
								ListNBT enchants = itemInHand.getEnchantmentTagList();

								for (int y = 0; y < enchants.size(); y++)
								{

									CompoundNBT enchant = enchants.getCompound(y);

									if (enchant.getInt("lvl") <= Short.MAX_VALUE)
									{
										String id = enchant.getString("id");

										if (id.matches("so_many_enchants:light_blade") || id.matches("so_many_enchants:heavy_blade") || id.matches("so_many_enchants:attack_reach") || id.matches("so_many_enchants:block_reach"))
										{
											return;
										}
										else if (y < enchants.size() - 1)
										{

										}
										else if (y >= enchants.size() - 1)
										{
											CompoundNBT nbt = itemInHand.getTag();
											nbt.remove("AttributeModifiers");
											return;
										}
										else
										{
											Main.LOGGER.error("{} has invalid weapon attribute",
													(Object) player.getName().getString());
											return;
										}

									}

								}

							}
							else
							{
								attributeFound = false;
								enchanted = false;
								CompoundNBT nbt = itemInHand.getTag();
								nbt.remove("AttributeModifiers");
								return;
							}

						}
						else if (enchanted && x >= attributes.size())
						{
							enchanted = false;
							return;
						}

					}

					if (enchanted)
					{
						enchanted = false;
					}

				}

			}

		}

	}

	private static void addModifiers(ItemStack stack, PlayerEntity player, double damageAmount)
	{
		stack.addAttributeModifier(Attributes.ATTACK_DAMAGE,
				new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Mainhand modifier",
						NBTHelper.getAttackDamage(player) + damageAmount, Operation.ADDITION),
				EquipmentSlotType.MAINHAND);
		NBTHelper.renderAttributeLore(player);
		return;
	}
}
