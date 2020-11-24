package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.item.ItemFloatDisabled;
import fr.sinikraft.magicwitchcraft.item.ItemFloatBoots;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureEnableFloatBootsOnKeyPressed extends ElementsMagicWitchcraft.ModElement {
	public ProcedureEnableFloatBootsOnKeyPressed(ElementsMagicWitchcraft instance) {
		super(instance, 182);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure EnableFloatBootsOnKeyPressed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof EntityPlayer)
				? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemFloatBoots.boots, (int) (1)))
				: false)) {
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemFloatBoots.boots, (int) (1)).getItem(), -1, (int) 1, null);
			if (entity instanceof EntityPlayer) {
				((EntityPlayer) entity).inventory.armorInventory.set(0, new ItemStack(ItemFloatDisabled.boots, (int) (1)));
				if (entity instanceof EntityPlayerMP)
					((EntityPlayerMP) entity).inventory.markDirty();
			}
			/* @ItemStack */} else {
			if (((entity instanceof EntityPlayer)
					? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(ItemFloatDisabled.boots, (int) (1)))
					: false)) {
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemFloatDisabled.boots, (int) (1)).getItem(), -1, (int) 1,
							null);
				if (entity instanceof EntityPlayer) {
					((EntityPlayer) entity).inventory.armorInventory.set(0, new ItemStack(ItemFloatBoots.boots, (int) (1)));
					if (entity instanceof EntityPlayerMP)
						((EntityPlayerMP) entity).inventory.markDirty();
				}
				/* @ItemStack */}
		}
	}
}
