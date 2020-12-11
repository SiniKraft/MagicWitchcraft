package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.IProperty;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.gui.GuiMagicalCompressorGUI;
import fr.sinikraft.magicwitchcraft.block.BlockMagicalCompressorActivate;
import fr.sinikraft.magicwitchcraft.MagicWitchcraft;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMagicalCompressorOnBlockRightClicked extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMagicalCompressorOnBlockRightClicked(ElementsMagicWitchcraft instance) {
		super(instance, 175);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MagicalCompressorOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MagicalCompressorOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MagicalCompressorOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MagicalCompressorOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MagicalCompressorOnBlockRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((entity.isSneaking())) {
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).openGui(MagicWitchcraft.instance, GuiMagicalCompressorGUI.GUIID, world, x, y, z);
		} else {
			{
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				IBlockState _bs = BlockMagicalCompressorActivate.block.getDefaultState();
				IBlockState _bso = world.getBlockState(_bp);
				for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getProperties().entrySet()) {
					IProperty _property = entry.getKey();
					if (_bs.getPropertyKeys().contains(_property))
						_bs = _bs.withProperty(_property, (Comparable) entry.getValue());
				}
				TileEntity _te = world.getTileEntity(_bp);
				NBTTagCompound _bnbt = null;
				if (_te != null) {
					_bnbt = _te.writeToNBT(new NBTTagCompound());
					_te.invalidate();
				}
				world.setBlockState(_bp, _bs, 3);
				if (_bnbt != null) {
					_te = world.getTileEntity(_bp);
					if (_te != null) {
						try {
							_te.readFromNBT(_bnbt);
						} catch (Exception ignored) {
						}
					}
				}
			}
			world.playSound((EntityPlayer) null, x, y, z, (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("block.stone_button.click_on")), SoundCategory.NEUTRAL, (float) 1, (float) 1);
		}
	}
}
