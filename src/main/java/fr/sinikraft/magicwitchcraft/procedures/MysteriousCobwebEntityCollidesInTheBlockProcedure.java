package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class MysteriousCobwebEntityCollidesInTheBlockProcedure extends MagicWitchcraftModElements.ModElement {
	public MysteriousCobwebEntityCollidesInTheBlockProcedure(MagicWitchcraftModElements instance) {
		super(instance, 100);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MysteriousCobwebEntityCollidesInTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotionMultiplier(null, new Vec3d(0.25D, (double) 0.05F, 0.25D));
	}
}
