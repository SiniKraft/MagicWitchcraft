package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureCobwebOnPotionActiveTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureCobwebOnPotionActiveTick(ElementsMagicWitchcraft instance) {
		super(instance, 79);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure CobwebOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setInWeb();
	}
}
