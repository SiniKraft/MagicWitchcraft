package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.gui.GuiMysteriousExtractorRecipe1;
import fr.sinikraft.magicwitchcraft.MagicWitchcraft;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMysteriousExtractorGUIViewRecipesOnButtonClicked extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMysteriousExtractorGUIViewRecipesOnButtonClicked(ElementsMagicWitchcraft instance) {
		super(instance, 367);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MysteriousExtractorGUIViewRecipesOnButtonClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MysteriousExtractorGUIViewRecipesOnButtonClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MysteriousExtractorGUIViewRecipesOnButtonClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MysteriousExtractorGUIViewRecipesOnButtonClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MysteriousExtractorGUIViewRecipesOnButtonClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).openGui(MagicWitchcraft.instance, GuiMysteriousExtractorRecipe1.GUIID, world, x, y, z);
	}
}
