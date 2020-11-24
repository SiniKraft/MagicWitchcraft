package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.IProperty;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.item.ItemMagicalWheat;
import fr.sinikraft.magicwitchcraft.item.ItemMagicalOrb;
import fr.sinikraft.magicwitchcraft.item.ItemMagicalBerries;
import fr.sinikraft.magicwitchcraft.block.BlockMagicalCompressor;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMagicalCompressorActivateUpdateTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMagicalCompressorActivateUpdateTick(ElementsMagicWitchcraft instance) {
		super(instance, 161);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MagicalCompressorActivateUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MagicalCompressorActivateUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MagicalCompressorActivateUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MagicalCompressorActivateUpdateTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((!((new Object() {
			public int getAmount(BlockPos pos, int sltid) {
				TileEntity inv = world.getTileEntity(pos);
				if (inv instanceof TileEntityLockableLoot) {
					ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (1))) == 64)) && ((new Object() {
			public int getAmount(BlockPos pos, int sltid) {
				TileEntity inv = world.getTileEntity(pos);
				if (inv instanceof TileEntityLockableLoot) {
					ItemStack stack = ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) >= 8))) {
			if (((new Object() {
				public ItemStack getItemStack(BlockPos pos, int sltid) {
					TileEntity inv = world.getTileEntity(pos);
					if (inv instanceof TileEntityLockableLoot)
						return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
					return ItemStack.EMPTY;
				}
			}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(ItemMagicalWheat.block, (int) (1))
					.getItem())) {
				{
					TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					if (inv != null && (inv instanceof TileEntityLockableLoot)) {
						ItemStack _setstack = new ItemStack(ItemMagicalWheat.block, (int) (1));
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
						}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) - 8));
						((TileEntityLockableLoot) inv).setInventorySlotContents((int) (0), _setstack);
					}
				}
				{
					TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					if (inv != null && (inv instanceof TileEntityLockableLoot)) {
						ItemStack _setstack = new ItemStack(ItemMagicalOrb.block, (int) (1));
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
			} else if (((new Object() {
				public ItemStack getItemStack(BlockPos pos, int sltid) {
					TileEntity inv = world.getTileEntity(pos);
					if (inv instanceof TileEntityLockableLoot)
						return ((TileEntityLockableLoot) inv).getStackInSlot(sltid);
					return ItemStack.EMPTY;
				}
			}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(ItemMagicalBerries.block, (int) (1))
					.getItem())) {
				{
					TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					if (inv != null && (inv instanceof TileEntityLockableLoot)) {
						ItemStack _setstack = new ItemStack(ItemMagicalBerries.block, (int) (1));
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
						}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (0))) - 8));
						((TileEntityLockableLoot) inv).setInventorySlotContents((int) (0), _setstack);
					}
				}
				{
					TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					if (inv != null && (inv instanceof TileEntityLockableLoot)) {
						ItemStack _setstack = new ItemStack(ItemMagicalOrb.block, (int) (1));
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
		{
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			IBlockState _bs = BlockMagicalCompressor.block.getDefaultState();
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
				.getObject(new ResourceLocation("block.stone_button.click_off")), SoundCategory.NEUTRAL, (float) 1, (float) 1);
	}
}
