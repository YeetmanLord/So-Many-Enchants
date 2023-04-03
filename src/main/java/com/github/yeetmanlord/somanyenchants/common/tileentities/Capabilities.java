package com.github.yeetmanlord.somanyenchants.common.tileentities;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.block.HopperBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class Capabilities {

	public static boolean insertHook(EnchantedHopperTileEntity hopper)
    {
        Direction hopperFacing = hopper.getBlockState().getValue(HopperBlock.FACING);
        return getItemHandler(hopper.getLevel(), hopper, hopperFacing)
                .map(destinationResult -> {
                    IItemHandler itemHandler = destinationResult.getKey();
                    Object destination = destinationResult.getValue();
                    if (isFull(itemHandler))
                    {
                        return false;
                    }
                    else
                    {
                        for (int i = 0; i < hopper.getContainerSize(); ++i)
                        {
                            if (!hopper.getItem(i).isEmpty())
                            {
                                ItemStack originalSlotContents = hopper.getItem(i).copy();
                                ItemStack insertStack = hopper.removeItem(i, 1);
                                ItemStack remainder = putStackInInventoryAllSlots(hopper, destination, itemHandler, insertStack);

                                if (remainder.isEmpty())
                                {
                                    return true;
                                }

                                hopper.setItem(i, originalSlotContents);
                            }
                        }

                        return false;
                    }
                })
                .orElse(false);
    }
	
	private static Optional<Pair<IItemHandler, Object>> getItemHandler(World world, IHopper hopper, Direction hopperFacing)
    {
        double x = hopper.getLevelX() + (double) hopperFacing.getStepX();
        double y = hopper.getLevelY() + (double) hopperFacing.getStepY();
        double z = hopper.getLevelZ() + (double) hopperFacing.getStepZ();
        return getItemHandler(world, x, y, z, hopperFacing.getOpposite());
    }
	
	public static Optional<Pair<IItemHandler, Object>> getItemHandler(World worldIn, double x, double y, double z, final Direction side)
    {
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(y);
        int k = MathHelper.floor(z);
        BlockPos blockpos = new BlockPos(i, j, k);
        net.minecraft.block.BlockState state = worldIn.getBlockState(blockpos);

        if (state.hasTileEntity())
        {
            TileEntity tileentity = worldIn.getBlockEntity(blockpos);
            if (tileentity != null)
            {
                return tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side)
                    .map(capability -> ImmutablePair.<IItemHandler, Object>of(capability, tileentity));
            }
        }

        return Optional.empty();
    }
	
	private static ItemStack putStackInInventoryAllSlots(TileEntity source, Object destination, IItemHandler destInventory, ItemStack stack)
    {
        for (int slot = 0; slot < destInventory.getSlots() && !stack.isEmpty(); slot++)
        {
            stack = insertStack(source, destination, destInventory, stack, slot);
        }
        return stack;
    }
	
	private static ItemStack insertStack(TileEntity source, Object destination, IItemHandler destInventory, ItemStack stack, int slot)
    {
        ItemStack itemstack = destInventory.getStackInSlot(slot);

        if (destInventory.insertItem(slot, stack, true).isEmpty())
        {
            boolean insertedItem = false;
            boolean inventoryWasEmpty = isEmpty(destInventory);

            if (itemstack.isEmpty())
            {
                destInventory.insertItem(slot, stack, false);
                stack = ItemStack.EMPTY;
                insertedItem = true;
            }
            else if (ItemHandlerHelper.canItemStacksStack(itemstack, stack))
            {
                int originalSize = stack.getCount();
                stack = destInventory.insertItem(slot, stack, false);
                insertedItem = originalSize < stack.getCount();
            }

            if (insertedItem)
            {
            	if (inventoryWasEmpty && destination instanceof HopperTileEntity)
                {
                    HopperTileEntity destinationHopper = (HopperTileEntity)destination;

                    if (!destinationHopper.isOnCustomCooldown())
                    {
                        int k = 0;
                        if (source instanceof HopperTileEntity)
                        {
                            if (destinationHopper.getLastUpdateTime() >= ((HopperTileEntity) source).getLastUpdateTime())
                            {
                                k = 1;
                            }
                        }
                        destinationHopper.setCooldown(8 - k);
                    }
                }
            	if (inventoryWasEmpty && destination instanceof EnchantedHopperTileEntity)
                {
            		EnchantedHopperTileEntity destinationHopper = (EnchantedHopperTileEntity)destination;

                    if (!destinationHopper.isOnCustomCooldown())
                    {
                        int k = 0;
                        if (source instanceof EnchantedHopperTileEntity)
                        {
                            if (destinationHopper.getLastUpdateTime() >= ((EnchantedHopperTileEntity) source).getLastUpdateTime())
                            {
                                k = 1;
                            }
                        }
                        destinationHopper.setCooldown(2 - k);
                    }
                }
            }
        }

        return stack;
    }
	
	 private static boolean isEmpty(IItemHandler itemHandler)
	    {
	        for (int slot = 0; slot < itemHandler.getSlots(); slot++)
	        {
	            ItemStack stackInSlot = itemHandler.getStackInSlot(slot);
	            if (stackInSlot.getCount() > 0)
	            {
	                return false;
	            }
	        }
	        return true;
	    }
	 
	 private static boolean isFull(IItemHandler itemHandler)
	    {
	        for (int slot = 0; slot < itemHandler.getSlots(); slot++)
	        {
	            ItemStack stackInSlot = itemHandler.getStackInSlot(slot);
	            if (stackInSlot.isEmpty() || stackInSlot.getCount() < itemHandler.getSlotLimit(slot))
	            {
	                return false;
	            }
	        }
	        return true;
	    }

	
}
