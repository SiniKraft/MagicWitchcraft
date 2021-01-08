package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.item.ItemStack;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureSpectralPowerInfuserUpdateTickItemInInventoryTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureSpectralPowerInfuserUpdateTickItemInInventoryTick(ElementsMagicWitchcraft instance) {
		super(instance, 316);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure SpectralPowerInfuserUpdateTickItemInInventoryTick!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		itemstack.setItemDamage((int) (15 - ((itemstack).hasTagCompound() ? (itemstack).getTagCompound().getDouble("Count") : -1)));
	}
}
