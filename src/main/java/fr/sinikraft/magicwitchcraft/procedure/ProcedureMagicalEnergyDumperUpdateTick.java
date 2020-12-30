package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.state.IBlockState;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMagicalEnergyDumperUpdateTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMagicalEnergyDumperUpdateTick(ElementsMagicWitchcraft instance) {
		super(instance, 327);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MagicalEnergyDumperUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MagicalEnergyDumperUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MagicalEnergyDumperUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MagicalEnergyDumperUpdateTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((((new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(new BlockPos((int) x, (int) (y + 1), (int) z), "EnergyStored")) >= 10) && ((new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "EnergyStored")) == 0))) {
			if (!world.isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) (y + 1), (int) z);
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
					}.getValue(new BlockPos((int) x, (int) (y + 1), (int) z), "EnergyStored")) - 10));
				world.notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				IBlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().setDouble("EnergyStored", 10);
				world.notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
	}
}
