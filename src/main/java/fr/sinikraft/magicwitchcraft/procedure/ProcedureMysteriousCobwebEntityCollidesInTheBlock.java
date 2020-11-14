package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMysteriousCobwebEntityCollidesInTheBlock extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMysteriousCobwebEntityCollidesInTheBlock(ElementsMagicWitchcraft instance) {
		super(instance, 43);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MysteriousCobwebEntityCollidesInTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setInWeb();
	}
}
