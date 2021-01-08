package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.item.FloatDisabledItem;
import fr.sinikraft.magicwitchcraft.item.FloatBootsItem;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class EnableFloatBootsOnKeyPressedProcedure extends MagicWitchcraftModElements.ModElement {
	public EnableFloatBootsOnKeyPressedProcedure(MagicWitchcraftModElements instance) {
		super(instance, 197);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure EnableFloatBootsOnKeyPressed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(FloatBootsItem.boots, (int) (1)))
				: false)) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(FloatBootsItem.boots, (int) (1));
				((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
			}
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).inventory.armorInventory.set((int) 0, new ItemStack(FloatDisabledItem.boots, (int) (1)));
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
			/* @ItemStack */} else {
			if (((entity instanceof PlayerEntity)
					? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(FloatDisabledItem.boots, (int) (1)))
					: false)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(FloatDisabledItem.boots, (int) (1));
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
				}
				if (entity instanceof PlayerEntity) {
					((PlayerEntity) entity).inventory.armorInventory.set((int) 0, new ItemStack(FloatBootsItem.boots, (int) (1)));
					if (entity instanceof ServerPlayerEntity)
						((ServerPlayerEntity) entity).inventory.markDirty();
				}
				/* @ItemStack */}
		}
	}
}
