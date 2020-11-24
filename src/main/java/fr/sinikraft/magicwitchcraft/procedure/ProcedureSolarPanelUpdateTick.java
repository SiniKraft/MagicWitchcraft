package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;

import fr.sinikraft.magicwitchcraft.item.ItemEnergyCrystalEmpty;
import fr.sinikraft.magicwitchcraft.item.ItemEnergyCrystal;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureSolarPanelUpdateTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureSolarPanelUpdateTick(ElementsMagicWitchcraft instance) {
		super(instance, 163);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SolarPanelUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SolarPanelUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SolarPanelUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SolarPanelUpdateTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))) {
			if (((new Object() {
				public ItemStack getItemStack(BlockPos pos, int sltid) {
					TileEntity inv = world.getTileEntity(pos);
					if (inv instanceof TileEntityLockableLoot)
						return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
					return ItemStack.EMPTY;
				}
			}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(ItemEnergyCrystalEmpty.block, (int) (1))
					.getItem())) {
				if ((!((new Object() {
					public int getAmount(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof TileEntityLockableLoot) {
							ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
							if (stack != null)
								return stack.getCount();
						}
						return 0;
					}
				}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (1))) == 64))) {
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv instanceof TileEntityLockableLoot)
							((TileEntityLockableLoot) inv).decrStackSize((int) (0), (int) (1));
					}
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv != null && (inv instanceof TileEntityLockableLoot)) {
							ItemStack _setstack = new ItemStack(ItemEnergyCrystal.block, (int) (1));
							_setstack.setCount(((new Object() {
								public int getAmount(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof TileEntityLockableLoot) {
										ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (1))) + 1));
							((TileEntityLockableLoot) inv).setInventorySlotContents((int) (1), _setstack);
						}
					}
				}
			}
		}
	}
}
