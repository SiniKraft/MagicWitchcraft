package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.DirectionProperty;
import net.minecraft.block.BlockState;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.block.MagicalEnergyDumperBlock;
import fr.sinikraft.magicwitchcraft.block.MagicalEnergyConduitBlock;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class MagicalEnergyConduitUpdateTickProcedure extends MagicWitchcraftModElements.ModElement {
	public MagicalEnergyConduitUpdateTickProcedure(MagicWitchcraftModElements instance) {
		super(instance, 330);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure MagicalEnergyConduitUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure MagicalEnergyConduitUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure MagicalEnergyConduitUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure MagicalEnergyConduitUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "EnergyStored")) == 0)) {
			if (((new Object() {
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch (Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.NORTH)) {
				if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == MagicalEnergyConduitBlock.block
						.getDefaultState().getBlock())
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == MagicalEnergyDumperBlock.block
								.getDefaultState().getBlock()))) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) (z + 1)), "EnergyStored")) >= 10)) {
						if (!world.getWorld().isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) (z + 1));
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("EnergyStored", 0);
							world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (!world.getWorld().isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("EnergyStored", 10);
							world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
			} else if (((new Object() {
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch (Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.SOUTH)) {
				if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == MagicalEnergyConduitBlock.block
						.getDefaultState().getBlock())
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == MagicalEnergyDumperBlock.block
								.getDefaultState().getBlock()))) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) (z - 1)), "EnergyStored")) == 10)) {
						if (!world.getWorld().isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) (z - 1));
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("EnergyStored", 0);
							world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (!world.getWorld().isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("EnergyStored", 10);
							world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
			} else if (((new Object() {
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch (Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.EAST)) {
				if ((((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == MagicalEnergyConduitBlock.block
						.getDefaultState().getBlock())
						|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == MagicalEnergyDumperBlock.block
								.getDefaultState().getBlock()))) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) (x - 1), (int) y, (int) z), "EnergyStored")) == 10)) {
						if (!world.getWorld().isRemote) {
							BlockPos _bp = new BlockPos((int) (x - 1), (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("EnergyStored", 0);
							world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (!world.getWorld().isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("EnergyStored", 10);
							world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
			} else if (((new Object() {
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch (Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.WEST)) {
				if ((((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == MagicalEnergyConduitBlock.block
						.getDefaultState().getBlock())
						|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == MagicalEnergyDumperBlock.block
								.getDefaultState().getBlock()))) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) (x + 1), (int) y, (int) z), "EnergyStored")) == 10)) {
						if (!world.getWorld().isRemote) {
							BlockPos _bp = new BlockPos((int) (x + 1), (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("EnergyStored", 0);
							world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (!world.getWorld().isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("EnergyStored", 10);
							world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
			}
		}
	}
}
