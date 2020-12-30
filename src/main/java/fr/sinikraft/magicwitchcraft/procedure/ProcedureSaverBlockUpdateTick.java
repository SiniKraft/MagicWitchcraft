package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.IProperty;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.block.BlockSaverBlockYellow;
import fr.sinikraft.magicwitchcraft.block.BlockSaverBlockRed;
import fr.sinikraft.magicwitchcraft.block.BlockSaverBlockGreen;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureSaverBlockUpdateTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureSaverBlockUpdateTick(ElementsMagicWitchcraft instance) {
		super(instance, 358);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SaverBlockUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SaverBlockUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SaverBlockUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SaverBlockUpdateTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockSaverBlockGreen.block.getDefaultState().getBlock())) {
			{
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				IBlockState _bs = BlockSaverBlockYellow.block.getDefaultState();
				IBlockState _bso = world.getBlockState(_bp);
				for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getProperties().entrySet()) {
					IProperty _property = entry.getKey();
					if (_bs.getPropertyKeys().contains(_property))
						_bs = _bs.withProperty(_property, (Comparable) entry.getValue());
				}
				world.setBlockState(_bp, _bs, 3);
			}
			world.playSound((EntityPlayer) null, x, y, z, (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("block.wood_pressureplate.click_on")), SoundCategory.NEUTRAL, (float) 1, (float) 1);
		} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockSaverBlockYellow.block.getDefaultState()
				.getBlock())) {
			{
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				IBlockState _bs = BlockSaverBlockRed.block.getDefaultState();
				IBlockState _bso = world.getBlockState(_bp);
				for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getProperties().entrySet()) {
					IProperty _property = entry.getKey();
					if (_bs.getPropertyKeys().contains(_property))
						_bs = _bs.withProperty(_property, (Comparable) entry.getValue());
				}
				world.setBlockState(_bp, _bs, 3);
			}
			world.playSound((EntityPlayer) null, x, y, z, (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("block.wood_pressureplate.click_on")), SoundCategory.NEUTRAL, (float) 1, (float) 1);
		} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockSaverBlockRed.block.getDefaultState()
				.getBlock())) {
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
			world.playSound((EntityPlayer) null, x, y, z, (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("block.wood_pressureplate.click_off")), SoundCategory.NEUTRAL, (float) 1, (float) 1);
		}
	}
}
