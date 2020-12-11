package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.IProperty;

import java.util.Random;

import fr.sinikraft.magicwitchcraft.item.ItemStorageModule;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureAutoBlockRemoverRedstoneOn extends ElementsMagicWitchcraft.ModElement {
	public ProcedureAutoBlockRemoverRedstoneOn(ElementsMagicWitchcraft instance) {
		super(instance, 123);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure AutoBlockRemoverRedstoneOn!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure AutoBlockRemoverRedstoneOn!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure AutoBlockRemoverRedstoneOn!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure AutoBlockRemoverRedstoneOn!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((new Object() {
			public EnumFacing getEnumFacing(BlockPos pos) {
				try {
					IBlockState _bs = world.getBlockState(pos);
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing"))
							return _bs.getValue((PropertyDirection) prop);
					}
					return EnumFacing.NORTH;
				} catch (Exception e) {
					return EnumFacing.NORTH;
				}
			}
		}.getEnumFacing(new BlockPos((int) x, (int) y, (int) z))) == EnumFacing.UP)) {
			if (((new Object() {
				public ItemStack getItemStack(BlockPos pos, int sltid) {
					TileEntity inv = world.getTileEntity(pos);
					if (inv instanceof TileEntityLockableLoot)
						return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
					return ItemStack.EMPTY;
				}
			}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem()
					.canHarvestBlock((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)))))) {
				if (((new Object() {
					public ItemStack getItemStack(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof TileEntityLockableLoot)
							return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
						return ItemStack.EMPTY;
					}
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(ItemStorageModule.block, (int) (1))
						.getItem())) {
					if (((((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof TileEntityLockableLoot)
								return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0)))
							.getItem() == (new ItemStack((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock())).getItem())
							|| ((new Object() {
								public ItemStack getItemStack(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof TileEntityLockableLoot)
										return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
									return ItemStack.EMPTY;
								}
							}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(Blocks.AIR, (int) (1))
									.getItem()))
							&& ((new Object() {
								public int getAmount(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof TileEntityLockableLoot) {
										ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) != 64))) {
						{
							TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (inv != null && (inv instanceof TileEntityLockableLoot)) {
								ItemStack _setstack = (new ItemStack(
										(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock()));
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
								}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) + 1));
								((TileEntityLockableLoot) inv).setInventorySlotContents((int) (0), _setstack);
							}
						}
						world.destroyBlock(new BlockPos((int) x, (int) (y + 1), (int) z), false);
						{
							TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (inv != null && (inv instanceof TileEntityLockableLoot)) {
								ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot((int) (0));
								if (stack != null) {
									if (stack.attemptDamageItem((int) 1, new Random(), null)) {
										stack.shrink(1);
										stack.setItemDamage(0);
									}
								}
							}
						}
					}
				} else {
					world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).getBlock().dropBlockAsItem(world,
							new BlockPos((int) x, (int) (y + 1), (int) z), world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)), 1);
					world.setBlockToAir(new BlockPos((int) x, (int) (y + 1), (int) z));
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv != null && (inv instanceof TileEntityLockableLoot)) {
							ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot((int) (0));
							if (stack != null) {
								if (stack.attemptDamageItem((int) 1, new Random(), null)) {
									stack.shrink(1);
									stack.setItemDamage(0);
								}
							}
						}
					}
				}
			}
		} else {
			if (((new Object() {
				public EnumFacing getEnumFacing(BlockPos pos) {
					try {
						IBlockState _bs = world.getBlockState(pos);
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing"))
								return _bs.getValue((PropertyDirection) prop);
						}
						return EnumFacing.NORTH;
					} catch (Exception e) {
						return EnumFacing.NORTH;
					}
				}
			}.getEnumFacing(new BlockPos((int) x, (int) y, (int) z))) == EnumFacing.DOWN)) {
				if (((new Object() {
					public ItemStack getItemStack(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof TileEntityLockableLoot)
							return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
						return ItemStack.EMPTY;
					}
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem()
						.canHarvestBlock((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z)))))) {
					if (((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof TileEntityLockableLoot)
								return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(ItemStorageModule.block, (int) (1))
							.getItem())) {
						if (((((new Object() {
							public ItemStack getItemStack(BlockPos pos, int sltid) {
								TileEntity inv = world.getTileEntity(pos);
								if (inv instanceof TileEntityLockableLoot)
									return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
								return ItemStack.EMPTY;
							}
						}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0)))
								.getItem() == (new ItemStack((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock()))
										.getItem())
								|| ((new Object() {
									public ItemStack getItemStack(BlockPos pos, int sltid) {
										TileEntity inv = world.getTileEntity(pos);
										if (inv instanceof TileEntityLockableLoot)
											return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
										return ItemStack.EMPTY;
									}
								}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(Blocks.AIR, (int) (1))
										.getItem()))
								&& ((new Object() {
									public int getAmount(BlockPos pos, int sltid) {
										TileEntity inv = world.getTileEntity(pos);
										if (inv instanceof TileEntityLockableLoot) {
											ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
											if (stack != null)
												return stack.getCount();
										}
										return 0;
									}
								}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) != 64))) {
							{
								TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (inv != null && (inv instanceof TileEntityLockableLoot)) {
									ItemStack _setstack = (new ItemStack(
											(world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock()));
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
									}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) + 1));
									((TileEntityLockableLoot) inv).setInventorySlotContents((int) (0), _setstack);
								}
							}
							world.destroyBlock(new BlockPos((int) x, (int) (y - 1), (int) z), false);
							{
								TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (inv != null && (inv instanceof TileEntityLockableLoot)) {
									ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot((int) (0));
									if (stack != null) {
										if (stack.attemptDamageItem((int) 1, new Random(), null)) {
											stack.shrink(1);
											stack.setItemDamage(0);
										}
									}
								}
							}
						}
					} else {
						world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z)).getBlock().dropBlockAsItem(world,
								new BlockPos((int) x, (int) (y - 1), (int) z), world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z)), 1);
						world.setBlockToAir(new BlockPos((int) x, (int) (y - 1), (int) z));
						{
							TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (inv != null && (inv instanceof TileEntityLockableLoot)) {
								ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot((int) (0));
								if (stack != null) {
									if (stack.attemptDamageItem((int) 1, new Random(), null)) {
										stack.shrink(1);
										stack.setItemDamage(0);
									}
								}
							}
						}
					}
				}
			} else {
				if (((new Object() {
					public EnumFacing getEnumFacing(BlockPos pos) {
						try {
							IBlockState _bs = world.getBlockState(pos);
							for (IProperty<?> prop : _bs.getProperties().keySet()) {
								if (prop.getName().equals("facing"))
									return _bs.getValue((PropertyDirection) prop);
							}
							return EnumFacing.NORTH;
						} catch (Exception e) {
							return EnumFacing.NORTH;
						}
					}
				}.getEnumFacing(new BlockPos((int) x, (int) y, (int) z))) == EnumFacing.NORTH)) {
					if (((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof TileEntityLockableLoot)
								return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem()
							.canHarvestBlock((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))))))) {
						if (((new Object() {
							public ItemStack getItemStack(BlockPos pos, int sltid) {
								TileEntity inv = world.getTileEntity(pos);
								if (inv instanceof TileEntityLockableLoot)
									return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
								return ItemStack.EMPTY;
							}
						}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2)))
								.getItem() == new ItemStack(ItemStorageModule.block, (int) (1)).getItem())) {
							if (((((new Object() {
								public ItemStack getItemStack(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof TileEntityLockableLoot)
										return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
									return ItemStack.EMPTY;
								}
							}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0)))
									.getItem() == (new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock()))
											.getItem())
									|| ((new Object() {
										public ItemStack getItemStack(BlockPos pos, int sltid) {
											TileEntity inv = world.getTileEntity(pos);
											if (inv instanceof TileEntityLockableLoot)
												return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
											return ItemStack.EMPTY;
										}
									}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0)))
											.getItem() == new ItemStack(Blocks.AIR, (int) (1)).getItem()))
									&& ((new Object() {
										public int getAmount(BlockPos pos, int sltid) {
											TileEntity inv = world.getTileEntity(pos);
											if (inv instanceof TileEntityLockableLoot) {
												ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
												if (stack != null)
													return stack.getCount();
											}
											return 0;
										}
									}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) != 64))) {
								{
									TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
									if (inv != null && (inv instanceof TileEntityLockableLoot)) {
										ItemStack _setstack = (new ItemStack(
												(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock()));
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
										}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) + 1));
										((TileEntityLockableLoot) inv).setInventorySlotContents((int) (0), _setstack);
									}
								}
								world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z - 1)), false);
								{
									TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
									if (inv != null && (inv instanceof TileEntityLockableLoot)) {
										ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot((int) (0));
										if (stack != null) {
											if (stack.attemptDamageItem((int) 1, new Random(), null)) {
												stack.shrink(1);
												stack.setItemDamage(0);
											}
										}
									}
								}
							}
						} else {
							world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))).getBlock().dropBlockAsItem(world,
									new BlockPos((int) x, (int) y, (int) (z - 1)), world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))),
									1);
							world.setBlockToAir(new BlockPos((int) x, (int) y, (int) (z - 1)));
							{
								TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (inv != null && (inv instanceof TileEntityLockableLoot)) {
									ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot((int) (0));
									if (stack != null) {
										if (stack.attemptDamageItem((int) 1, new Random(), null)) {
											stack.shrink(1);
											stack.setItemDamage(0);
										}
									}
								}
							}
						}
					}
				} else {
					if (((new Object() {
						public EnumFacing getEnumFacing(BlockPos pos) {
							try {
								IBlockState _bs = world.getBlockState(pos);
								for (IProperty<?> prop : _bs.getProperties().keySet()) {
									if (prop.getName().equals("facing"))
										return _bs.getValue((PropertyDirection) prop);
								}
								return EnumFacing.NORTH;
							} catch (Exception e) {
								return EnumFacing.NORTH;
							}
						}
					}.getEnumFacing(new BlockPos((int) x, (int) y, (int) z))) == EnumFacing.SOUTH)) {
						if (((new Object() {
							public ItemStack getItemStack(BlockPos pos, int sltid) {
								TileEntity inv = world.getTileEntity(pos);
								if (inv instanceof TileEntityLockableLoot)
									return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
								return ItemStack.EMPTY;
							}
						}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem()
								.canHarvestBlock((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))))))) {
							if (((new Object() {
								public ItemStack getItemStack(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof TileEntityLockableLoot)
										return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
									return ItemStack.EMPTY;
								}
							}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2)))
									.getItem() == new ItemStack(ItemStorageModule.block, (int) (1)).getItem())) {
								if (((((new Object() {
									public ItemStack getItemStack(BlockPos pos, int sltid) {
										TileEntity inv = world.getTileEntity(pos);
										if (inv instanceof TileEntityLockableLoot)
											return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
										return ItemStack.EMPTY;
									}
								}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0)))
										.getItem() == (new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock()))
												.getItem())
										|| ((new Object() {
											public ItemStack getItemStack(BlockPos pos, int sltid) {
												TileEntity inv = world.getTileEntity(pos);
												if (inv instanceof TileEntityLockableLoot)
													return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
												return ItemStack.EMPTY;
											}
										}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0)))
												.getItem() == new ItemStack(Blocks.AIR, (int) (1)).getItem()))
										&& ((new Object() {
											public int getAmount(BlockPos pos, int sltid) {
												TileEntity inv = world.getTileEntity(pos);
												if (inv instanceof TileEntityLockableLoot) {
													ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
													if (stack != null)
														return stack.getCount();
												}
												return 0;
											}
										}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) != 64))) {
									{
										TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
										if (inv != null && (inv instanceof TileEntityLockableLoot)) {
											ItemStack _setstack = (new ItemStack(
													(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock()));
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
											}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) + 1));
											((TileEntityLockableLoot) inv).setInventorySlotContents((int) (0), _setstack);
										}
									}
									world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z + 1)), false);
									{
										TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
										if (inv != null && (inv instanceof TileEntityLockableLoot)) {
											ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot((int) (0));
											if (stack != null) {
												if (stack.attemptDamageItem((int) 1, new Random(), null)) {
													stack.shrink(1);
													stack.setItemDamage(0);
												}
											}
										}
									}
								}
							} else {
								world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))).getBlock().dropBlockAsItem(world,
										new BlockPos((int) x, (int) y, (int) (z + 1)),
										world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))), 1);
								world.setBlockToAir(new BlockPos((int) x, (int) y, (int) (z + 1)));
								{
									TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
									if (inv != null && (inv instanceof TileEntityLockableLoot)) {
										ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot((int) (0));
										if (stack != null) {
											if (stack.attemptDamageItem((int) 1, new Random(), null)) {
												stack.shrink(1);
												stack.setItemDamage(0);
											}
										}
									}
								}
							}
						}
					} else {
						if (((new Object() {
							public EnumFacing getEnumFacing(BlockPos pos) {
								try {
									IBlockState _bs = world.getBlockState(pos);
									for (IProperty<?> prop : _bs.getProperties().keySet()) {
										if (prop.getName().equals("facing"))
											return _bs.getValue((PropertyDirection) prop);
									}
									return EnumFacing.NORTH;
								} catch (Exception e) {
									return EnumFacing.NORTH;
								}
							}
						}.getEnumFacing(new BlockPos((int) x, (int) y, (int) z))) == EnumFacing.EAST)) {
							if (((new Object() {
								public ItemStack getItemStack(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof TileEntityLockableLoot)
										return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
									return ItemStack.EMPTY;
								}
							}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem()
									.canHarvestBlock((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)))))) {
								if (((new Object() {
									public ItemStack getItemStack(BlockPos pos, int sltid) {
										TileEntity inv = world.getTileEntity(pos);
										if (inv instanceof TileEntityLockableLoot)
											return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
										return ItemStack.EMPTY;
									}
								}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2)))
										.getItem() == new ItemStack(ItemStorageModule.block, (int) (1)).getItem())) {
									if (((((new Object() {
										public ItemStack getItemStack(BlockPos pos, int sltid) {
											TileEntity inv = world.getTileEntity(pos);
											if (inv instanceof TileEntityLockableLoot)
												return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
											return ItemStack.EMPTY;
										}
									}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == (new ItemStack(
											(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock())).getItem())
											|| ((new Object() {
												public ItemStack getItemStack(BlockPos pos, int sltid) {
													TileEntity inv = world.getTileEntity(pos);
													if (inv instanceof TileEntityLockableLoot)
														return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
													return ItemStack.EMPTY;
												}
											}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0)))
													.getItem() == new ItemStack(Blocks.AIR, (int) (1)).getItem()))
											&& ((new Object() {
												public int getAmount(BlockPos pos, int sltid) {
													TileEntity inv = world.getTileEntity(pos);
													if (inv instanceof TileEntityLockableLoot) {
														ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
														if (stack != null)
															return stack.getCount();
													}
													return 0;
												}
											}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) != 64))) {
										{
											TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
											if (inv != null && (inv instanceof TileEntityLockableLoot)) {
												ItemStack _setstack = (new ItemStack(
														(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock()));
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
												}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) + 1));
												((TileEntityLockableLoot) inv).setInventorySlotContents((int) (0), _setstack);
											}
										}
										world.destroyBlock(new BlockPos((int) (x + 1), (int) y, (int) z), false);
										{
											TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
											if (inv != null && (inv instanceof TileEntityLockableLoot)) {
												ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot((int) (0));
												if (stack != null) {
													if (stack.attemptDamageItem((int) 1, new Random(), null)) {
														stack.shrink(1);
														stack.setItemDamage(0);
													}
												}
											}
										}
									}
								} else {
									world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)).getBlock().dropBlockAsItem(world,
											new BlockPos((int) (x + 1), (int) y, (int) z),
											world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)), 1);
									world.setBlockToAir(new BlockPos((int) (x + 1), (int) y, (int) z));
									{
										TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
										if (inv != null && (inv instanceof TileEntityLockableLoot)) {
											ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot((int) (0));
											if (stack != null) {
												if (stack.attemptDamageItem((int) 1, new Random(), null)) {
													stack.shrink(1);
													stack.setItemDamage(0);
												}
											}
										}
									}
								}
							}
						} else {
							if (((new Object() {
								public EnumFacing getEnumFacing(BlockPos pos) {
									try {
										IBlockState _bs = world.getBlockState(pos);
										for (IProperty<?> prop : _bs.getProperties().keySet()) {
											if (prop.getName().equals("facing"))
												return _bs.getValue((PropertyDirection) prop);
										}
										return EnumFacing.NORTH;
									} catch (Exception e) {
										return EnumFacing.NORTH;
									}
								}
							}.getEnumFacing(new BlockPos((int) x, (int) y, (int) z))) == EnumFacing.WEST)) {
								if (((new Object() {
									public ItemStack getItemStack(BlockPos pos, int sltid) {
										TileEntity inv = world.getTileEntity(pos);
										if (inv instanceof TileEntityLockableLoot)
											return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
										return ItemStack.EMPTY;
									}
								}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem()
										.canHarvestBlock((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)))))) {
									if (((new Object() {
										public ItemStack getItemStack(BlockPos pos, int sltid) {
											TileEntity inv = world.getTileEntity(pos);
											if (inv instanceof TileEntityLockableLoot)
												return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
											return ItemStack.EMPTY;
										}
									}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2)))
											.getItem() == new ItemStack(ItemStorageModule.block, (int) (1)).getItem())) {
										if (((((new Object() {
											public ItemStack getItemStack(BlockPos pos, int sltid) {
												TileEntity inv = world.getTileEntity(pos);
												if (inv instanceof TileEntityLockableLoot)
													return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
												return ItemStack.EMPTY;
											}
										}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == (new ItemStack(
												(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock())).getItem())
												|| ((new Object() {
													public ItemStack getItemStack(BlockPos pos, int sltid) {
														TileEntity inv = world.getTileEntity(pos);
														if (inv instanceof TileEntityLockableLoot)
															return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
														return ItemStack.EMPTY;
													}
												}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0)))
														.getItem() == new ItemStack(Blocks.AIR, (int) (1)).getItem()))
												&& ((new Object() {
													public int getAmount(BlockPos pos, int sltid) {
														TileEntity inv = world.getTileEntity(pos);
														if (inv instanceof TileEntityLockableLoot) {
															ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
															if (stack != null)
																return stack.getCount();
														}
														return 0;
													}
												}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) != 64))) {
											{
												TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
												if (inv != null && (inv instanceof TileEntityLockableLoot)) {
													ItemStack _setstack = (new ItemStack(
															(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock()));
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
													}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) + 1));
													((TileEntityLockableLoot) inv).setInventorySlotContents((int) (0), _setstack);
												}
											}
											world.destroyBlock(new BlockPos((int) (x - 1), (int) y, (int) z), false);
											{
												TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
												if (inv != null && (inv instanceof TileEntityLockableLoot)) {
													ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot((int) (0));
													if (stack != null) {
														if (stack.attemptDamageItem((int) 1, new Random(), null)) {
															stack.shrink(1);
															stack.setItemDamage(0);
														}
													}
												}
											}
										}
									} else {
										world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)).getBlock().dropBlockAsItem(world,
												new BlockPos((int) (x - 1), (int) y, (int) z),
												world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)), 1);
										world.setBlockToAir(new BlockPos((int) (x - 1), (int) y, (int) z));
										{
											TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
											if (inv != null && (inv instanceof TileEntityLockableLoot)) {
												ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot((int) (0));
												if (stack != null) {
													if (stack.attemptDamageItem((int) 1, new Random(), null)) {
														stack.shrink(1);
														stack.setItemDamage(0);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
