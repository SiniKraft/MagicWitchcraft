package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.block.state.IBlockState;

import fr.sinikraft.magicwitchcraft.item.ItemSpectralOrb;
import fr.sinikraft.magicwitchcraft.item.ItemMagicalOrb;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMagicalEnergyGeneratorUpdateTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMagicalEnergyGeneratorUpdateTick(ElementsMagicWitchcraft instance) {
		super(instance, 324);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MagicalEnergyGeneratorUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MagicalEnergyGeneratorUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MagicalEnergyGeneratorUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MagicalEnergyGeneratorUpdateTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		boolean IsGenerated = false;
		IsGenerated = (boolean) (false);
		if (((new Object() {
			public ItemStack getItemStack(BlockPos pos, int sltid) {
				TileEntity inv = world.getTileEntity(pos);
				if (inv instanceof TileEntityLockableLoot)
					return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
				return ItemStack.EMPTY;
			}
		}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(Items.COAL, (int) (1), 0).getItem())) {
			if ((!((new Object() {
				public double getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "EnergyStored")) >= 1000))) {
				if ((((new Object() {
					public ItemStack getItemStack(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof TileEntityLockableLoot)
							return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
						return ItemStack.EMPTY;
					}
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(ItemMagicalOrb.block, (int) (1))
						.getItem()) || ((new Object() {
							public ItemStack getItemStack(BlockPos pos, int sltid) {
								TileEntity inv = world.getTileEntity(pos);
								if (inv instanceof TileEntityLockableLoot)
									return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
								return ItemStack.EMPTY;
							}
						}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1)))
								.getItem() == new ItemStack(ItemSpectralOrb.block, (int) (1)).getItem()))) {
					if (((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof TileEntityLockableLoot)
								return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(ItemMagicalOrb.block, (int) (1))
							.getItem())) {
						if ((!(((new Object() {
							public double getValue(BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(new BlockPos((int) x, (int) y, (int) z), "EnergyStored")) + 50) >= 1001))) {
							if (!world.isRemote) {
								BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
								TileEntity _tileEntity = world.getTileEntity(_bp);
								IBlockState _bs = world.getBlockState(_bp);
								if (_tileEntity != null)
									_tileEntity.getTileData().setDouble("EnergyStored", ((new Object() {
										public double getValue(BlockPos pos, String tag) {
											TileEntity tileEntity = world.getTileEntity(pos);
											if (tileEntity != null)
												return tileEntity.getTileData().getDouble(tag);
											return -1;
										}
									}.getValue(new BlockPos((int) x, (int) y, (int) z), "EnergyStored")) + 50));
								world.notifyBlockUpdate(_bp, _bs, _bs, 3);
							}
							IsGenerated = (boolean) (true);
						}
					} else if (((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof TileEntityLockableLoot)
								return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(ItemSpectralOrb.block, (int) (1))
							.getItem())) {
						if ((!(((new Object() {
							public double getValue(BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(new BlockPos((int) x, (int) y, (int) z), "EnergyStored")) + 100) >= 1001))) {
							if (!world.isRemote) {
								BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
								TileEntity _tileEntity = world.getTileEntity(_bp);
								IBlockState _bs = world.getBlockState(_bp);
								if (_tileEntity != null)
									_tileEntity.getTileData().setDouble("EnergyStored", ((new Object() {
										public double getValue(BlockPos pos, String tag) {
											TileEntity tileEntity = world.getTileEntity(pos);
											if (tileEntity != null)
												return tileEntity.getTileData().getDouble(tag);
											return -1;
										}
									}.getValue(new BlockPos((int) x, (int) y, (int) z), "EnergyStored")) + 100));
								world.notifyBlockUpdate(_bp, _bs, _bs, 3);
							}
							IsGenerated = (boolean) (true);
						}
					}
					if ((IsGenerated)) {
						if (((new Object() {
							public double getValue(BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(new BlockPos((int) x, (int) y, (int) z), "BurnTime")) == 0)) {
							{
								TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (inv instanceof TileEntityLockableLoot)
									((TileEntityLockableLoot) inv).decrStackSize((int) (0), (int) (1));
							}
							if (!world.isRemote) {
								BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
								TileEntity _tileEntity = world.getTileEntity(_bp);
								IBlockState _bs = world.getBlockState(_bp);
								if (_tileEntity != null)
									_tileEntity.getTileData().setDouble("BurnTime", 5);
								world.notifyBlockUpdate(_bp, _bs, _bs, 3);
							}
						}
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							IBlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().setDouble("BurnTime", ((new Object() {
									public double getValue(BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(new BlockPos((int) x, (int) y, (int) z), "BurnTime")) - 1));
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						{
							TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (inv instanceof TileEntityLockableLoot)
								((TileEntityLockableLoot) inv).decrStackSize((int) (1), (int) (1));
						}
					}
				}
			}
		}
	}
}
