package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.item.ItemStack;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class SpectralPowerInfuserUpdateTickItemInInventoryTickProcedure extends MagicWitchcraftModElements.ModElement {
	public SpectralPowerInfuserUpdateTickItemInInventoryTickProcedure(MagicWitchcraftModElements instance) {
		super(instance, 316);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure SpectralPowerInfuserUpdateTickItemInInventoryTick!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		((itemstack)).setDamage((int) (15 - ((itemstack).getOrCreateTag().getDouble("Count"))));
	}
}
