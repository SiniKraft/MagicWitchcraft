package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class MagicalBlockEntityWalksOnTheBlockProcedure extends MagicWitchcraftModElements.ModElement {
	public MagicalBlockEntityWalksOnTheBlockProcedure(MagicWitchcraftModElements instance) {
		super(instance, 323);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MagicalBlockEntityWalksOnTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotion((entity.getMotion().getX()), ((entity.getMotion().getY()) * 0.5), (entity.getMotion().getZ()));
		entity.fallDistance = (float) (0);
	}
}
