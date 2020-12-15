package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class SpectralBlockEntityWalksOnTheBlockProcedure extends MagicWitchcraftModElements.ModElement {
	public SpectralBlockEntityWalksOnTheBlockProcedure(MagicWitchcraftModElements instance) {
		super(instance, 324);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure SpectralBlockEntityWalksOnTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotion((entity.getMotion().getX()), ((entity.getMotion().getY()) * (-0.8)), (entity.getMotion().getZ()));
		entity.fallDistance = (float) (0);
	}
}
