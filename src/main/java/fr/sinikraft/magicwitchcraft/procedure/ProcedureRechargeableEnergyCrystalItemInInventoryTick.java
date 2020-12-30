package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.item.ItemStack;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureRechargeableEnergyCrystalItemInInventoryTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureRechargeableEnergyCrystalItemInInventoryTick(ElementsMagicWitchcraft instance) {
		super(instance, 315);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure RechargeableEnergyCrystalItemInInventoryTick!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		itemstack.setItemDamage((int) (101 - ((itemstack).hasTagCompound() ? (itemstack).getTagCompound().getDouble("Energy") : -1)));
	}
}
