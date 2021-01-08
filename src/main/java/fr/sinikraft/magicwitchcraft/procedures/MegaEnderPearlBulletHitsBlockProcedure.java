package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.Collections;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class MegaEnderPearlBulletHitsBlockProcedure extends MagicWitchcraftModElements.ModElement {
	public MegaEnderPearlBulletHitsBlockProcedure(MagicWitchcraftModElements instance) {
		super(instance, 187);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MegaEnderPearlBulletHitsBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure MegaEnderPearlBulletHitsBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure MegaEnderPearlBulletHitsBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure MegaEnderPearlBulletHitsBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		{
			Entity _ent = entity;
			_ent.setPositionAndUpdate(x, y, z);
			if (_ent instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) _ent).connection.setPlayerLocation(x, y, z, _ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
			}
		}
	}
}
