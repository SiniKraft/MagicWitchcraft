package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureRedObsidianBlockDestroyedByExplosion extends ElementsMagicWitchcraft.ModElement {
	public ProcedureRedObsidianBlockDestroyedByExplosion(ElementsMagicWitchcraft instance) {
		super(instance, 248);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure RedObsidianBlockDestroyedByExplosion!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure RedObsidianBlockDestroyedByExplosion!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure RedObsidianBlockDestroyedByExplosion!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure RedObsidianBlockDestroyedByExplosion!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (!world.isRemote) {
			world.createExplosion(null, (int) x, (int) y, (int) z, (float) 8, true);
		}
	}
}
