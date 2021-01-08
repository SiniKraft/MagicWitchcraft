package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.entity.Entity;
import net.minecraft.block.material.Material;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureSpectralHammerBlockDestroyedWithTool extends ElementsMagicWitchcraft.ModElement {
	public ProcedureSpectralHammerBlockDestroyedWithTool(ElementsMagicWitchcraft instance) {
		super(instance, 362);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SpectralHammerBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SpectralHammerBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SpectralHammerBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SpectralHammerBlockDestroyedWithTool!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SpectralHammerBlockDestroyedWithTool!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double posX = 0;
		double posY = 0;
		double posZ = 0;
		if ((((entity.getHorizontalFacing()) == EnumFacing.EAST) || ((entity.getHorizontalFacing()) == EnumFacing.WEST))) {
			posX = (double) x;
			posY = (double) (y - 2);
			posZ = (double) (z - 2);
			for (int index0 = 0; index0 < (int) (5); index0++) {
				for (int index1 = 0; index1 < (int) (5); index1++) {
					if (((((((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ)))).getMaterial() == Material.ROCK)
							|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ)))).getMaterial() == Material.SAND))
							|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ)))).getMaterial() == Material.CLAY))
							|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ)))).getMaterial() == Material.GROUND))
							|| (((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ)))).getMaterial() == Material.GRASS)
									|| (((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ)))).getMaterial() == Material.WOOD)
											|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
													.getMaterial() == Material.IRON))))) {
						world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))).getBlock().dropBlockAsItem(world,
								new BlockPos((int) (posX), (int) (posY), (int) (posZ)),
								world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))), 1);
						world.setBlockToAir(new BlockPos((int) (posX), (int) (posY), (int) (posZ)));
					}
					posZ = (double) ((posZ) + 1);
				}
				posZ = (double) ((posZ) - 5);
				posY = (double) ((posY) + 1);
			}
		} else if ((((entity.getHorizontalFacing()) == EnumFacing.SOUTH) || ((entity.getHorizontalFacing()) == EnumFacing.NORTH))) {
			posX = (double) (x - 2);
			posY = (double) (y - 2);
			posZ = (double) z;
			for (int index2 = 0; index2 < (int) (5); index2++) {
				for (int index3 = 0; index3 < (int) (5); index3++) {
					if (((((((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ)))).getMaterial() == Material.ROCK)
							|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ)))).getMaterial() == Material.SAND))
							|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ)))).getMaterial() == Material.CLAY))
							|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ)))).getMaterial() == Material.GROUND))
							|| (((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ)))).getMaterial() == Material.GRASS)
									|| (((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ)))).getMaterial() == Material.WOOD)
											|| ((world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))))
													.getMaterial() == Material.IRON))))) {
						world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))).getBlock().dropBlockAsItem(world,
								new BlockPos((int) (posX), (int) (posY), (int) (posZ)),
								world.getBlockState(new BlockPos((int) (posX), (int) (posY), (int) (posZ))), 1);
						world.setBlockToAir(new BlockPos((int) (posX), (int) (posY), (int) (posZ)));
					}
					posX = (double) ((posX) + 1);
				}
				posX = (double) ((posX) - 5);
				posY = (double) ((posY) + 1);
			}
		}
	}
}
