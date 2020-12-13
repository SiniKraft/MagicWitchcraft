package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.util.EnumHand;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.item.ItemMagicalBottleFilled1;
import fr.sinikraft.magicwitchcraft.item.ItemMagicalBottleEmpty;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMagicalBottleEmptyItemInHandTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMagicalBottleEmptyItemInHandTick(ElementsMagicWitchcraft instance) {
		super(instance, 301);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MagicalBottleEmptyItemInHandTick!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure MagicalBottleEmptyItemInHandTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double Count = 0;
		String Stored = "";
		if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(ItemMagicalBottleEmpty.block, (int) (1)).getItem())) {
			if ((((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).hasTagCompound()
					? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).getTagCompound()
							.getString("Stored")
					: "")).equals("SpectralFire"))) {
				Count = (double) (((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.hasTagCompound()
								? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
										.getTagCompound().getDouble("Count")
								: -1);
				if (entity instanceof EntityLivingBase) {
					ItemStack _setstack = new ItemStack(ItemMagicalBottleFilled1.block, (int) (1));
					_setstack.setCount(1);
					((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
					if (entity instanceof EntityPlayerMP)
						((EntityPlayerMP) entity).inventory.markDirty();
				}
				{
					ItemStack _stack = ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					if (!_stack.hasTagCompound())
						_stack.setTagCompound(new NBTTagCompound());
					_stack.getTagCompound().setString("Stored", "SpectralFire");
				}
				{
					ItemStack _stack = ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					if (!_stack.hasTagCompound())
						_stack.setTagCompound(new NBTTagCompound());
					_stack.getTagCompound().setDouble("Count", (Count));
				}
				itemstack.setItemDamage((int) (Count));
				System.out.println((Count));
			}
		} else if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(ItemMagicalBottleFilled1.block, (int) (1)).getItem())) {
			if ((!(((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).hasTagCompound()
					? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).getTagCompound()
							.getString("Stored")
					: "")).equals("SpectralFire")))) {
				Stored = (String) (((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.hasTagCompound()
								? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
										.getTagCompound().getString("Stored")
								: "");
				Count = (double) (((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.hasTagCompound()
								? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
										.getTagCompound().getDouble("Count")
								: -1);
				if (entity instanceof EntityLivingBase) {
					ItemStack _setstack = new ItemStack(ItemMagicalBottleEmpty.block, (int) (1));
					_setstack.setCount(1);
					((EntityLivingBase) entity).setHeldItem(EnumHand.MAIN_HAND, _setstack);
					if (entity instanceof EntityPlayerMP)
						((EntityPlayerMP) entity).inventory.markDirty();
				}
				{
					ItemStack _stack = ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					if (!_stack.hasTagCompound())
						_stack.setTagCompound(new NBTTagCompound());
					_stack.getTagCompound().setDouble("Count", (Count));
				}
				{
					ItemStack _stack = ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					if (!_stack.hasTagCompound())
						_stack.setTagCompound(new NBTTagCompound());
					_stack.getTagCompound().setString("Stored", (Stored));
				}
			}
		}
	}
}
