package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
<<<<<<< HEAD
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class CloseButtonDimensionalSwitcherProcedure extends MagicWitchcraftModElements.ModElement {
	public CloseButtonDimensionalSwitcherProcedure(MagicWitchcraftModElements instance) {
		super(instance, 108);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency entity for procedure CloseButtonDimensionalSwitcher!");
=======

@MagicWitchcraftModElements.ModElement.Tag
public class CloseButtonDimensionalSwitcherProcedure extends MagicWitchcraftModElements.ModElement {
	public CloseButtonDimensionalSwitcherProcedure(MagicWitchcraftModElements instance) {
		super(instance, 108);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure CloseButtonDimensionalSwitcher!");
>>>>>>> branch '1.15.2-master' of https://github.com/SiniKraft/MagicWitchcraft
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
	}
}
