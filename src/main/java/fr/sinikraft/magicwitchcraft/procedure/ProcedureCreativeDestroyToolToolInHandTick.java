package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureCreativeDestroyToolToolInHandTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureCreativeDestroyToolToolInHandTick(ElementsMagicWitchcraft instance) {
		super(instance, 318);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure CreativeDestroyToolToolInHandTick!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((!((EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, (itemstack))) == 1))) {
			itemstack.addEnchantment(Enchantments.SILK_TOUCH, (int) 1);
		}
		if ((!((itemstack).hasTagCompound() && (itemstack).getTagCompound().getBoolean("Unbreakable")))) {
			{
				ItemStack _stack = (itemstack);
				if (!_stack.hasTagCompound())
					_stack.setTagCompound(new NBTTagCompound());
				_stack.getTagCompound().setBoolean("Unbreakable", (true));
			}
		}
	}
}
