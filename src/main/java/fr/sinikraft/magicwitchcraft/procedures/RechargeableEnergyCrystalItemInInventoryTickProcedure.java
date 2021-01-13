package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.item.ItemStack;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class RechargeableEnergyCrystalItemInInventoryTickProcedure extends MagicWitchcraftModElements.ModElement {
	public RechargeableEnergyCrystalItemInInventoryTickProcedure(MagicWitchcraftModElements instance) {
		super(instance, 315);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency itemstack for procedure RechargeableEnergyCrystalItemInInventoryTick!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		((itemstack)).setDamage((int) (101 - ((itemstack).getOrCreateTag().getDouble("Energy"))));
	}
}
