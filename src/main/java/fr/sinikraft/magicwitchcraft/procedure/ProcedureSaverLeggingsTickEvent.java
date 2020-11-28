package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureSaverLeggingsTickEvent extends ElementsMagicWitchcraft.ModElement {
	public ProcedureSaverLeggingsTickEvent(ElementsMagicWitchcraft instance) {
		super(instance, 234);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SaverLeggingsTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((!((entity.getEntityData().getDouble("SaverLeggingsCooldown")) == 0))) {
			entity.getEntityData().setDouble("SaverLeggingsCooldown", ((entity.getEntityData().getDouble("SaverLeggingsCooldown")) + 1));
			System.out.println((entity.getEntityData().getDouble("SaverLeggingsCooldown")));
		}
	}
}
