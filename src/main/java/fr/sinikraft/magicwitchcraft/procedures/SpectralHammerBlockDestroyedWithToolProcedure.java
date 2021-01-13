package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.entity.Entity;
import net.minecraft.block.Block;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class SpectralHammerBlockDestroyedWithToolProcedure extends MagicWitchcraftModElements.ModElement {
	public SpectralHammerBlockDestroyedWithToolProcedure(MagicWitchcraftModElements instance) {
		super(instance, 362);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency entity for procedure SpectralHammerBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency x for procedure SpectralHammerBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency y for procedure SpectralHammerBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency z for procedure SpectralHammerBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency world for procedure SpectralHammerBlockDestroyedWithTool!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double posX = 0;
		double posY = 0;
		double posZ = 0;
		if ((((entity.getHorizontalFacing()) == Direction.EAST) || ((entity.getHorizontalFacing()) == Direction.WEST))) {
			posX = (double) x;
			posY = (double) (y - 2);
			posZ = (double) (z - 2);
			for (int index0 = 0; index0 < (int) (5); index0++) {
				for (int index1 = 0; index1 < (int) (5); index1++) {
					if (((((((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
							.getMaterial() == net.minecraft.block.material.Material.ROCK)
							|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
									.getMaterial() == net.minecraft.block.material.Material.SAND))
							|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
									.getMaterial() == net.minecraft.block.material.Material.CLAY))
							|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
									.getMaterial() == net.minecraft.block.material.Material.EARTH))
							|| (((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
									.getMaterial() == net.minecraft.block.material.Material.ORGANIC)
									|| (((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
											.getMaterial() == net.minecraft.block.material.Material.WOOD)
											|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
													.getMaterial() == net.minecraft.block.material.Material.IRON))))) {
						if (world instanceof World) {
							Block.spawnDrops(world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))), (World) world,
									new BlockPos((int) (posX), (int) (posY), (int) (posZ)));
							world.destroyBlock(new BlockPos((int) (posX), (int) (posY), (int) (posZ)), false);
						}
					}
					posZ = (double) ((posZ) + 1);
				}
				posZ = (double) ((posZ) - 5);
				posY = (double) ((posY) + 1);
			}
		} else if ((((entity.getHorizontalFacing()) == Direction.SOUTH) || ((entity.getHorizontalFacing()) == Direction.NORTH))) {
			posX = (double) (x - 2);
			posY = (double) (y - 2);
			posZ = (double) z;
			for (int index2 = 0; index2 < (int) (5); index2++) {
				for (int index3 = 0; index3 < (int) (5); index3++) {
					if (((((((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
							.getMaterial() == net.minecraft.block.material.Material.ROCK)
							|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
									.getMaterial() == net.minecraft.block.material.Material.SAND))
							|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
									.getMaterial() == net.minecraft.block.material.Material.CLAY))
							|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
									.getMaterial() == net.minecraft.block.material.Material.EARTH))
							|| (((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
									.getMaterial() == net.minecraft.block.material.Material.ORGANIC)
									|| (((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
											.getMaterial() == net.minecraft.block.material.Material.WOOD)
											|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
													.getMaterial() == net.minecraft.block.material.Material.IRON))))) {
						if (world instanceof World) {
							Block.spawnDrops(world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))), (World) world,
									new BlockPos((int) (posX), (int) (posY), (int) (posZ)));
							world.destroyBlock(new BlockPos((int) (posX), (int) (posY), (int) (posZ)), false);
						}
					}
					posX = (double) ((posX) + 1);
				}
				posX = (double) ((posX) - 5);
				posY = (double) ((posY) + 1);
			}
		}
	}
}
