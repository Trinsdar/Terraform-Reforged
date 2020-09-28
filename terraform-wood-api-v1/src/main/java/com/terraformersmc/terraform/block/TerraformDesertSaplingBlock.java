package com.terraformersmc.terraform.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class TerraformDesertSaplingBlock extends TerraformSaplingBlock {

	private final boolean onlySand;

	public TerraformDesertSaplingBlock(SaplingGenerator generator, Settings settings) {
		this(generator, settings, false);
	}

	public TerraformDesertSaplingBlock(SaplingGenerator generator, Settings settings, boolean onlySand) {
		super(generator, settings);
		this.onlySand = onlySand;
	}

	@Override
	public boolean canPlantOnTop(BlockState blockState, BlockView blockView, BlockPos pos) {
		if (onlySand) {
			return BlockTags.SAND.contains(blockState.getBlock());
		} else {
			return BlockTags.SAND.contains(blockState.getBlock()) || super.canPlantOnTop(blockState, blockView, pos);
		}
	}
}
