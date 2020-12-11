package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

import fr.sinikraft.magicwitchcraft.block.BlockMysteriousPortal;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMagicalDimensionPortalUpdateTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMagicalDimensionPortalUpdateTick(ElementsMagicWitchcraft instance) {
		super(instance, 184);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MagicalDimensionPortalUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MagicalDimensionPortalUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MagicalDimensionPortalUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MagicalDimensionPortalUpdateTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!(((((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == BlockMysteriousPortal.block.getDefaultState()
				.getBlock())
				&& ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == BlockMysteriousPortal.block.getDefaultState()
						.getBlock()))
				&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == BlockMysteriousPortal.block.getDefaultState()
						.getBlock()))
				&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == BlockMysteriousPortal.block.getDefaultState()
						.getBlock())))) {
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
		}
	}
}
