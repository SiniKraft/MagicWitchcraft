package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureSpectralChestInterfaceDownOnButtonClicked extends ElementsMagicWitchcraft.ModElement {
	public ProcedureSpectralChestInterfaceDownOnButtonClicked(ElementsMagicWitchcraft instance) {
		super(instance, 215);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SpectralChestInterfaceDownOnButtonClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SpectralChestInterfaceDownOnButtonClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SpectralChestInterfaceDownOnButtonClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SpectralChestInterfaceDownOnButtonClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SpectralChestInterfaceDownOnButtonClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (entity instanceof EntityPlayer) {
			BlockPos _bp = new BlockPos((int) x, (int) (y - 1), (int) z);
			world.getBlockState(_bp).getBlock().onBlockActivated(world, _bp, world.getBlockState(_bp), (EntityPlayer) entity, EnumHand.MAIN_HAND,
					EnumFacing.UP, _bp.getX(), _bp.getY(), _bp.getZ());
		}
	}
}
