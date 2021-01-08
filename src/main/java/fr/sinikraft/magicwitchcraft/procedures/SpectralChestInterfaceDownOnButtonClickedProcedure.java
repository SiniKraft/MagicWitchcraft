package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class SpectralChestInterfaceDownOnButtonClickedProcedure extends MagicWitchcraftModElements.ModElement {
	public SpectralChestInterfaceDownOnButtonClickedProcedure(MagicWitchcraftModElements instance) {
		super(instance, 228);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure SpectralChestInterfaceDownOnButtonClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure SpectralChestInterfaceDownOnButtonClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure SpectralChestInterfaceDownOnButtonClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure SpectralChestInterfaceDownOnButtonClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure SpectralChestInterfaceDownOnButtonClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (entity instanceof PlayerEntity && world instanceof World) {
			BlockPos _bp = new BlockPos((int) x, (int) (y - 1), (int) z);
			world.getBlockState(_bp).getBlock().onBlockActivated(world.getBlockState(_bp), world.getWorld(), _bp, (PlayerEntity) entity,
					Hand.MAIN_HAND, BlockRayTraceResult.createMiss(new Vec3d(_bp.getX(), _bp.getY(), _bp.getZ()), Direction.UP, _bp));
		}
	}
}
