package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureItemTrollerItemInInventoryTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureItemTrollerItemInInventoryTick(ElementsMagicWitchcraft instance) {
		super(instance, 238);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ItemTrollerItemInInventoryTick!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure ItemTrollerItemInInventoryTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		{
			ItemStack _stack = (itemstack);
			if (!_stack.hasTagCompound())
				_stack.setTagCompound(new NBTTagCompound());
			_stack.getTagCompound().setString("Owner", (entity.getDisplayName().getFormattedText()));
		}
	}
}
