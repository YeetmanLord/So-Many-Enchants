package com.github.yeetmanlord.somanyenchants.common.blocks.smelters.smoker;

import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.AbstractEnchantedSmelterBlock;
import com.github.yeetmanlord.somanyenchants.core.init.BlockEntityTypeInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EnchantedSmokerBlock extends AbstractEnchantedSmelterBlock
{
	public EnchantedSmokerBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
	{
		return new EnchantedSmokerTileEntity(pos, state);
	}

	@Override
	protected void interactWith(Level worldIn, BlockPos pos, Player player)
	{
		BlockEntity tileentity = worldIn.getBlockEntity(pos);

		if (tileentity instanceof EnchantedSmokerTileEntity)
		{
			player.openMenu((MenuProvider) tileentity);
			player.awardStat(Stats.INTERACT_WITH_SMOKER);
		}

	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand)
	{

		if (stateIn.getValue(LIT))
		{
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY();
			double d2 = (double) pos.getZ() + 0.5D;

			if (rand.nextDouble() < 0.1D)
			{
				worldIn.playLocalSound(d0, d1, d2, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
			}

			worldIn.addParticle(ParticleTypes.SMOKE, d0, d1 + 1.1D, d2, 0.0D, 0.0D, 0.0D);
		}

	}

	@Override
	public Block getUnenchantedBlock()
	{ return Blocks.SMOKER; }

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state,
			BlockEntityType<T> type) {
		return createFurnaceTicker(world, type, BlockEntityTypeInit.ENCHANTED_SMOKER.get());
	}
	
}
