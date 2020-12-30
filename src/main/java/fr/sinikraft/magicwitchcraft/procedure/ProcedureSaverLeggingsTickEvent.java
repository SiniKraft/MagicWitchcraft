package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.item.ItemSaver;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureSaverLeggingsTickEvent extends ElementsMagicWitchcraft.ModElement {
	public ProcedureSaverLeggingsTickEvent(ElementsMagicWitchcraft instance) {
		super(instance, 246);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SaverLeggingsTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY)
				.getItem() == new ItemStack(ItemSaver.legs, (int) (1)).getItem())) {
			if (((((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY).hasTagCompound()
					? ((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY).getTagCompound()
							.getDouble("Cooldown")
					: -1) > 0)) {
				{
					ItemStack _stack = ((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY);
					if (!_stack.hasTagCompound())
						_stack.setTagCompound(new NBTTagCompound());
					_stack.getTagCompound().setDouble("Cooldown", ((((entity instanceof EntityPlayer)
							? ((EntityPlayer) entity).inventory.armorInventory.get(1)
							: ItemStack.EMPTY).hasTagCompound()
									? ((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY)
											.getTagCompound().getDouble("Cooldown")
									: -1)
							- 1));
				}
			} else if ((!((((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY)
					.hasTagCompound()
							? ((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY)
									.getTagCompound().getDouble("Cooldown")
							: -1) == 0))) {
				{
					ItemStack _stack = ((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY);
					if (!_stack.hasTagCompound())
						_stack.setTagCompound(new NBTTagCompound());
					_stack.getTagCompound().setDouble("Cooldown", 0);
				}
			}
		}
	}
}
