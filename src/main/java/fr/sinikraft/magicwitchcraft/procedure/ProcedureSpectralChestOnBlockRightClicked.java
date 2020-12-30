package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.gui.GuiSpectralChestInterface;
import fr.sinikraft.magicwitchcraft.MagicWitchcraft;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureSpectralChestOnBlockRightClicked extends ElementsMagicWitchcraft.ModElement {
	public ProcedureSpectralChestOnBlockRightClicked(ElementsMagicWitchcraft instance) {
		super(instance, 229);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SpectralChestOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SpectralChestOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SpectralChestOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SpectralChestOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SpectralChestOnBlockRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		world.playSound((EntityPlayer) null, x, y, z,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.splash")),
				SoundCategory.NEUTRAL, (float) 1, (float) 1);
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).openGui(MagicWitchcraft.instance, GuiSpectralChestInterface.GUIID, world, x, y, z);
	}
}
