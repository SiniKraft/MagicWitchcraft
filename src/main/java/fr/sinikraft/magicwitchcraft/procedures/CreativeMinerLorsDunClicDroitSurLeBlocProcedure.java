package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Explosion;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class CreativeMinerLorsDunClicDroitSurLeBlocProcedure extends MagicWitchcraftModElements.ModElement {
	public CreativeMinerLorsDunClicDroitSurLeBlocProcedure(MagicWitchcraftModElements instance) {
		super(instance, 329);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure CreativeMinerLorsDunClicDroitSurLeBloc!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure CreativeMinerLorsDunClicDroitSurLeBloc!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure CreativeMinerLorsDunClicDroitSurLeBloc!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double posY = 0;
		posY = (double) 255;
		for (int index0 = 0; index0 < (int) (255); index0++) {
			if (world instanceof World && !world.getWorld().isRemote) {
				world.getWorld().createExplosion(null, (int) x, (int) (posY), (int) z, (float) 6, Explosion.Mode.BREAK);
			}
			posY = (double) ((posY) - 1);
		}
	}
}
