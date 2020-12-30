package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureHighlighterItemInInventoryTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureHighlighterItemInInventoryTick(ElementsMagicWitchcraft instance) {
		super(instance, 363);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure HighlighterItemInInventoryTick!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).hasTagCompound() ? (itemstack).getTagCompound().getDouble("Cooldown") : -1) > 0)) {
			{
				ItemStack _stack = (itemstack);
				if (!_stack.hasTagCompound())
					_stack.setTagCompound(new NBTTagCompound());
				_stack.getTagCompound().setDouble("Cooldown",
						(((itemstack).hasTagCompound() ? (itemstack).getTagCompound().getDouble("Cooldown") : -1) - 1));
			}
		} else if ((!(((itemstack).hasTagCompound() ? (itemstack).getTagCompound().getDouble("Cooldown") : -1) == 0))) {
			{
				ItemStack _stack = (itemstack);
				if (!_stack.hasTagCompound())
					_stack.setTagCompound(new NBTTagCompound());
				_stack.getTagCompound().setDouble("Cooldown", 0);
			}
		}
	}
}
