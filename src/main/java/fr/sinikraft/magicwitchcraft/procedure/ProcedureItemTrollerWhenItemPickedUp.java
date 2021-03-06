package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.item.ItemItemTroller;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureItemTrollerWhenItemPickedUp extends ElementsMagicWitchcraft.ModElement {
	public ProcedureItemTrollerWhenItemPickedUp(ElementsMagicWitchcraft instance) {
		super(instance, 239);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ItemTrollerWhenItemPickedUp!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure ItemTrollerWhenItemPickedUp!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure ItemTrollerWhenItemPickedUp!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure ItemTrollerWhenItemPickedUp!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure ItemTrollerWhenItemPickedUp!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure ItemTrollerWhenItemPickedUp!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		World world = (World) dependencies.get("world");
		if (((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false)) {
			{
				ItemStack _stack = (itemstack);
				if (!_stack.hasTagCompound())
					_stack.setTagCompound(new NBTTagCompound());
				_stack.getTagCompound().setString("Owner", (entity.getDisplayName().getFormattedText()));
			}
		} else {
			if ((!((((itemstack).hasTagCompound() ? (itemstack).getTagCompound().getString("Owner") : ""))
					.equals((entity.getDisplayName().getFormattedText()))))) {
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemItemTroller.block, (int) (1)).getItem(), -1, (int) 1,
							null);
				if (!world.isRemote) {
					world.createExplosion(null, (int) x, (int) y, (int) z, (float) 5, true);
				}
			}
		}
	}
}
