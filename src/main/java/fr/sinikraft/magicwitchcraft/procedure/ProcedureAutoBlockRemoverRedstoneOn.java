package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.IProperty;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureAutoBlockRemoverRedstoneOn extends ElementsMagicWitchcraft.ModElement {
	public ProcedureAutoBlockRemoverRedstoneOn(ElementsMagicWitchcraft instance) {
		super(instance, 68);
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
			if ((new ItemStack(Items.DIAMOND_PICKAXE, (int) (1)).getItem()
					.canHarvestBlock((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)))))) {
				if (!world.isRemote) {
					EntityItem entityToSpawn = new EntityItem(world, x, (y + 1), z,
							(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock())));
					entityToSpawn.setPickupDelay(10);
					world.spawnEntity(entityToSpawn);
				}
				world.destroyBlock(new BlockPos((int) x, (int) (y + 1), (int) z), false);
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
				if ((new ItemStack(Items.DIAMOND_PICKAXE, (int) (1)).getItem()
						.canHarvestBlock((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z)))))) {
					if (!world.isRemote) {
						EntityItem entityToSpawn = new EntityItem(world, x, (y - 1), z,
								(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock())));
						entityToSpawn.setPickupDelay(10);
						world.spawnEntity(entityToSpawn);
					}
					world.destroyBlock(new BlockPos((int) x, (int) (y - 1), (int) z), false);
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
					if ((new ItemStack(Items.DIAMOND_PICKAXE, (int) (1)).getItem()
							.canHarvestBlock((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))))))) {
						if (!world.isRemote) {
							EntityItem entityToSpawn = new EntityItem(world, x, y, (z - 1),
									(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock())));
							entityToSpawn.setPickupDelay(10);
							world.spawnEntity(entityToSpawn);
						}
						world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z - 1)), false);
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
						if ((new ItemStack(Items.DIAMOND_PICKAXE, (int) (1)).getItem()
								.canHarvestBlock((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))))))) {
							if ((new ItemStack(Items.DIAMOND_PICKAXE, (int) (1)).getItem()
									.canHarvestBlock((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))))))) {
								if (!world.isRemote) {
									EntityItem entityToSpawn = new EntityItem(world, x, y, (z + 1),
											(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock())));
									entityToSpawn.setPickupDelay(10);
									world.spawnEntity(entityToSpawn);
								}
								world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z + 1)), false);
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
							if ((new ItemStack(Items.DIAMOND_PICKAXE, (int) (1)).getItem()
									.canHarvestBlock((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)))))) {
								if (!world.isRemote) {
									EntityItem entityToSpawn = new EntityItem(world, (x + 1), y, z,
											(new ItemStack((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock())));
									entityToSpawn.setPickupDelay(10);
									world.spawnEntity(entityToSpawn);
								}
								world.destroyBlock(new BlockPos((int) (x + 1), (int) y, (int) z), false);
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
								if ((new ItemStack(Items.DIAMOND_PICKAXE, (int) (1)).getItem()
										.canHarvestBlock((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)))))) {
									if (!world.isRemote) {
										EntityItem entityToSpawn = new EntityItem(world, (x - 1), y, z,
												(new ItemStack((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock())));
										entityToSpawn.setPickupDelay(10);
										world.spawnEntity(entityToSpawn);
									}
									world.destroyBlock(new BlockPos((int) (x - 1), (int) y, (int) z), false);
								}
							}
						}
					}
				}
			}
		}
	}
}
