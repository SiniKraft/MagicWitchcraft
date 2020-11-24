package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureCloseButtonDimensionalSwitcher extends ElementsMagicWitchcraft.ModElement {
	public ProcedureCloseButtonDimensionalSwitcher(ElementsMagicWitchcraft instance) {
		super(instance, 93);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure CloseButtonDimensionalSwitcher!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).closeScreen();
	}
}
