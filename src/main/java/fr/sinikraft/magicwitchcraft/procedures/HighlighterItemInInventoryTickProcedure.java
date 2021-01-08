package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.item.ItemStack;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class HighlighterItemInInventoryTickProcedure extends MagicWitchcraftModElements.ModElement {
	public HighlighterItemInInventoryTickProcedure(MagicWitchcraftModElements instance) {
		super(instance, 363);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure HighlighterItemInInventoryTick!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).getOrCreateTag().getDouble("Cooldown")) > 0)) {
			(itemstack).getOrCreateTag().putDouble("Cooldown", (((itemstack).getOrCreateTag().getDouble("Cooldown")) - 1));
		} else if ((!(((itemstack).getOrCreateTag().getDouble("Cooldown")) == 0))) {
			(itemstack).getOrCreateTag().putDouble("Cooldown", 0);
		}
	}
}
