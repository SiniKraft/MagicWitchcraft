package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.item.SaverItem;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class SaverLeggingsPlaceBlockOnKeyPressedProcedure extends MagicWitchcraftModElements.ModElement {
	public SaverLeggingsPlaceBlockOnKeyPressedProcedure(MagicWitchcraftModElements instance) {
		super(instance, 242);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get((int) 1) : ItemStack.EMPTY)
				.getItem() == new ItemStack(SaverItem.legs, (int) (1)).getItem())) {
			entity.fallDistance = (float) (0);
		}
	}
}
