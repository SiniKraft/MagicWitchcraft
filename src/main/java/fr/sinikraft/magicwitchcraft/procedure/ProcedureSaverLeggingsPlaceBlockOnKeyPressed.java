package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.item.ItemSaver;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureSaverLeggingsPlaceBlockOnKeyPressed extends ElementsMagicWitchcraft.ModElement {
	public ProcedureSaverLeggingsPlaceBlockOnKeyPressed(ElementsMagicWitchcraft instance) {
		super(instance, 230);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY)
				.getItem() == new ItemStack(ItemSaver.legs, (int) (1)).getItem())) {
			entity.fallDistance = (float) (0);
		}
	}
}
