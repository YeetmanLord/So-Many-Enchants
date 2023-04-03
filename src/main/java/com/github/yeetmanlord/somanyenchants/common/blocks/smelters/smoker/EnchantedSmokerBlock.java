package com.github.yeetmanlord.somanyenchants.common.blocks.smelters.smoker;

import java.util.Random;

import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.AbstractEnchantedSmelterBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EnchantedSmokerBlock extends AbstractEnchantedSmelterBlock {
	public EnchantedSmokerBlock(AbstractBlock.Properties properties) {
		super(properties);
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader reader) {
		return new EnchantedSmokerTileEntity();
	}

	@Override
	protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
		TileEntity tileentity = worldIn.getBlockEntity(pos);

		if (tileentity instanceof EnchantedSmokerTileEntity) {
			player.openMenu((INamedContainerProvider) tileentity);
			player.awardStat(Stats.INTERACT_WITH_SMOKER);
		}

	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {

		if (stateIn.getValue(LIT)) {
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY();
			double d2 = (double) pos.getZ() + 0.5D;

			if (rand.nextDouble() < 0.1D) {
				worldIn.playLocalSound(d0, d1, d2, SoundEvents.SMOKER_SMOKE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			}

			worldIn.addParticle(ParticleTypes.SMOKE, d0, d1 + 1.1D, d2, 0.0D, 0.0D, 0.0D);
		}

	}

	@Override
	public Block getUnenchantedBlock() {
		return Blocks.SMOKER;
	}

}
