package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

// removed check dependencies
@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMegaEnderPearlBulletHitsBlock extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMegaEnderPearlBulletHitsBlock(ElementsMagicWitchcraft instance) {
		super(instance, 172);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		entity.setPositionAndUpdate(x, y, z);
	}
}
