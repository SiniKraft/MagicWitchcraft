package fr.sinikraft.magicwitchcraft.procedures;

<<<<<<< HEAD
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class CobwebOnPotionActiveTickProcedure extends MagicWitchcraftModElements.ModElement {
	public CobwebOnPotionActiveTickProcedure(MagicWitchcraftModElements instance) {
		super(instance, 94);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency entity for procedure CobwebOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotionMultiplier(null, new Vector3d(0.25D, (double) 0.05F, 0.25D));
=======
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class CobwebOnPotionActiveTickProcedure extends MagicWitchcraftModElements.ModElement {
	public CobwebOnPotionActiveTickProcedure(MagicWitchcraftModElements instance) {
		super(instance, 94);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure CobwebOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotionMultiplier(null, new Vec3d(0.25D, (double) 0.05F, 0.25D));
>>>>>>> branch '1.15.2-master' of https://github.com/SiniKraft/MagicWitchcraft
	}
}
