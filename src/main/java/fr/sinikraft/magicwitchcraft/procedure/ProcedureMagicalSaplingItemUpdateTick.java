package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.IProperty;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.block.BlockMagicalSapling;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMagicalSaplingItemUpdateTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMagicalSaplingItemUpdateTick(ElementsMagicWitchcraft instance) {
		super(instance, 142);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MagicalSaplingItemUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MagicalSaplingItemUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MagicalSaplingItemUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MagicalSaplingItemUpdateTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		{
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			IBlockState _bs = BlockMagicalSapling.block.getDefaultState();
			IBlockState _bso = world.getBlockState(_bp);
			for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getProperties().entrySet()) {
				IProperty _property = entry.getKey();
				if (_bs.getPropertyKeys().contains(_property))
					_bs = _bs.withProperty(_property, (Comparable) entry.getValue());
			}
			world.setBlockState(_bp, _bs, 3);
		}
	}
}
