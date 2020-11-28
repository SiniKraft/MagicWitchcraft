package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureStickyStoneEntityWalksOnTheBlock extends ElementsMagicWitchcraft.ModElement {
	public ProcedureStickyStoneEntityWalksOnTheBlock(ElementsMagicWitchcraft instance) {
		super(instance, 232);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure StickyStoneEntityWalksOnTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.motionX = (entity.motionX);
		entity.motionY = 0;
		entity.motionZ = (entity.motionZ);
	}
}
