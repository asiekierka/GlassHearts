package com.elytradev.glasshearts.world;

import java.util.Random;

import com.elytradev.glasshearts.GlassHearts;
import com.elytradev.glasshearts.block.BlockOre;
import com.elytradev.glasshearts.enums.EnumGem;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class GenerateGems implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!world.provider.isSurfaceWorld()) return;
		for (int i = 0; i < 12; i++) {
			int x = (chunkX*16)+random.nextInt(16);
			int y = random.nextInt(24)+3;
			int z = (chunkZ*16)+random.nextInt(16);
			BlockPos pos = new BlockPos(x, y, z);
			if (world.getBlockState(pos).getBlock() == Blocks.STONE) {
				EnumGem gem = BlockOre.VALID_GEMS[random.nextInt(BlockOre.VALID_GEMS.length)];
				if (GlassHearts.inst.configGenerateGems.contains(gem)) {
					world.setBlockState(pos, GlassHearts.inst.ORE.getDefaultState().withProperty(BlockOre.VARIANT, gem));
				}
			}
		}
	}

}