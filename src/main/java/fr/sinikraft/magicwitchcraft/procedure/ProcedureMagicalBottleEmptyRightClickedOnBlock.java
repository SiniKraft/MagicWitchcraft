package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumFacing;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.IProperty;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.block.BlockSpectralFire;
import fr.sinikraft.magicwitchcraft.block.BlockSapExtractor;
import fr.sinikraft.magicwitchcraft.block.BlockMagicalLog;
import fr.sinikraft.magicwitchcraft.block.BlockDeadLog;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMagicalBottleEmptyRightClickedOnBlock extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMagicalBottleEmptyRightClickedOnBlock(ElementsMagicWitchcraft instance) {
		super(instance, 300);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MagicalBottleEmptyRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MagicalBottleEmptyRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MagicalBottleEmptyRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MagicalBottleEmptyRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure MagicalBottleEmptyRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MagicalBottleEmptyRightClickedOnBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		World world = (World) dependencies.get("world");
		boolean IsSapExtracted = false;
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockSpectralFire.block.getDefaultState().getBlock())) {
			if (((!((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).hasTagCompound()
					? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).getTagCompound()
							.getDouble("Count")
					: -1) == 14))
					&& ((((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.hasTagCompound()
									? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
											.getTagCompound().getString("Stored")
									: "")).equals("SpectralFire"))
							|| (((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.hasTagCompound()
											? ((entity instanceof EntityLivingBase)
													? ((EntityLivingBase) entity).getHeldItemMainhand()
													: ItemStack.EMPTY).getTagCompound().getString("Stored")
											: "")).equals(""))))) {
				world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
				{
					ItemStack _stack = ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					if (!_stack.hasTagCompound())
						_stack.setTagCompound(new NBTTagCompound());
					_stack.getTagCompound().setString("Stored", "SpectralFire");
				}
				{
					ItemStack _stack = ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					if (!_stack.hasTagCompound())
						_stack.setTagCompound(new NBTTagCompound());
					_stack.getTagCompound().setDouble("Count", ((((entity instanceof EntityLivingBase)
							? ((EntityLivingBase) entity).getHeldItemMainhand()
							: ItemStack.EMPTY).hasTagCompound()
									? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
											.getTagCompound().getDouble("Count")
									: -1)
							+ 1));
				}
				itemstack.setItemDamage(
						(int) (15 - (((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.hasTagCompound()
										? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
												.getTagCompound().getDouble("Count")
										: -1)));
			}
		} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockSapExtractor.block.getDefaultState()
				.getBlock())) {
			if (((!((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).hasTagCompound()
					? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).getTagCompound()
							.getDouble("Count")
					: -1) == 14))
					&& ((((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.hasTagCompound()
									? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
											.getTagCompound().getString("Stored")
									: "")).equals("MagicalSap"))
							|| (((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.hasTagCompound()
											? ((entity instanceof EntityLivingBase)
													? ((EntityLivingBase) entity).getHeldItemMainhand()
													: ItemStack.EMPTY).getTagCompound().getString("Stored")
											: "")).equals(""))))) {
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
					if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == BlockMagicalLog.block.getDefaultState()
							.getBlock())) {
						{
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) (z + 1));
							IBlockState _bs = BlockDeadLog.block.getDefaultState();
							IBlockState _bso = world.getBlockState(_bp);
							for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getProperties().entrySet()) {
								IProperty _property = entry.getKey();
								if (_bs.getPropertyKeys().contains(_property))
									_bs = _bs.withProperty(_property, (Comparable) entry.getValue());
							}
							world.setBlockState(_bp, _bs, 3);
						}
						IsSapExtracted = (boolean) (true);
					}
				} else if (((new Object() {
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
					if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == BlockMagicalLog.block.getDefaultState()
							.getBlock())) {
						{
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) (z - 1));
							IBlockState _bs = BlockDeadLog.block.getDefaultState();
							IBlockState _bso = world.getBlockState(_bp);
							for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getProperties().entrySet()) {
								IProperty _property = entry.getKey();
								if (_bs.getPropertyKeys().contains(_property))
									_bs = _bs.withProperty(_property, (Comparable) entry.getValue());
							}
							world.setBlockState(_bp, _bs, 3);
						}
						IsSapExtracted = (boolean) (true);
					}
				} else if (((new Object() {
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
					if (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == BlockMagicalLog.block.getDefaultState()
							.getBlock())) {
						{
							BlockPos _bp = new BlockPos((int) (x - 1), (int) y, (int) z);
							IBlockState _bs = BlockDeadLog.block.getDefaultState();
							IBlockState _bso = world.getBlockState(_bp);
							for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getProperties().entrySet()) {
								IProperty _property = entry.getKey();
								if (_bs.getPropertyKeys().contains(_property))
									_bs = _bs.withProperty(_property, (Comparable) entry.getValue());
							}
							world.setBlockState(_bp, _bs, 3);
						}
						IsSapExtracted = (boolean) (true);
					}
				} else if (((new Object() {
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
					if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == BlockMagicalLog.block.getDefaultState()
							.getBlock())) {
						{
							BlockPos _bp = new BlockPos((int) (x + 1), (int) y, (int) z);
							IBlockState _bs = BlockDeadLog.block.getDefaultState();
							IBlockState _bso = world.getBlockState(_bp);
							for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getProperties().entrySet()) {
								IProperty _property = entry.getKey();
								if (_bs.getPropertyKeys().contains(_property))
									_bs = _bs.withProperty(_property, (Comparable) entry.getValue());
							}
							world.setBlockState(_bp, _bs, 3);
						}
						IsSapExtracted = (boolean) (true);
					}
				}
				if ((IsSapExtracted)) {
					world.playSound((EntityPlayer) null, x, y, z, (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
							.getObject(new ResourceLocation("item.bottle.fill")), SoundCategory.NEUTRAL, (float) 1, (float) 1);
					if ((Math.random() < 0.2)) {
						{
							ItemStack _stack = ((entity instanceof EntityLivingBase)
									? ((EntityLivingBase) entity).getHeldItemMainhand()
									: ItemStack.EMPTY);
							if (!_stack.hasTagCompound())
								_stack.setTagCompound(new NBTTagCompound());
							_stack.getTagCompound().setString("Stored", "MagicalSap");
						}
						{
							ItemStack _stack = ((entity instanceof EntityLivingBase)
									? ((EntityLivingBase) entity).getHeldItemMainhand()
									: ItemStack.EMPTY);
							if (!_stack.hasTagCompound())
								_stack.setTagCompound(new NBTTagCompound());
							_stack.getTagCompound().setDouble("Count",
									((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
											.hasTagCompound()
													? ((entity instanceof EntityLivingBase)
															? ((EntityLivingBase) entity).getHeldItemMainhand()
															: ItemStack.EMPTY).getTagCompound().getDouble("Count")
													: -1)
											+ 1));
						}
						itemstack.setItemDamage((int) (15 - (((entity instanceof EntityLivingBase)
								? ((EntityLivingBase) entity).getHeldItemMainhand()
								: ItemStack.EMPTY).hasTagCompound()
										? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
												.getTagCompound().getDouble("Count")
										: -1)));
					}
				}
			}
		} else if (((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).hasTagCompound()
				? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).getTagCompound()
						.getDouble("Count")
				: -1) >= 1)) {
			if (((((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).hasTagCompound()
					? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).getTagCompound()
							.getString("Stored")
					: "")).equals("SpectralFire"))
					&& ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState()
							.getBlock()))) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), BlockSpectralFire.block.getDefaultState(), 3);
				{
					ItemStack _stack = ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					if (!_stack.hasTagCompound())
						_stack.setTagCompound(new NBTTagCompound());
					_stack.getTagCompound().setDouble("Count", ((((entity instanceof EntityLivingBase)
							? ((EntityLivingBase) entity).getHeldItemMainhand()
							: ItemStack.EMPTY).hasTagCompound()
									? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
											.getTagCompound().getDouble("Count")
									: -1)
							- 1));
				}
				itemstack.setItemDamage(
						(int) (15 - (((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.hasTagCompound()
										? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
												.getTagCompound().getDouble("Count")
										: -1)));
				if (((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).hasTagCompound()
						? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getTagCompound().getDouble("Count")
						: -1) == 0)) {
					{
						ItemStack _stack = ((entity instanceof EntityLivingBase)
								? ((EntityLivingBase) entity).getHeldItemMainhand()
								: ItemStack.EMPTY);
						if (!_stack.hasTagCompound())
							_stack.setTagCompound(new NBTTagCompound());
						_stack.getTagCompound().setString("Stored", "");
					}
				}
				itemstack.setItemDamage(
						(int) (15 - (((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.hasTagCompound()
										? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
												.getTagCompound().getDouble("Count")
										: -1)));
			}
		}
		itemstack.setItemDamage((int) (15
				- (((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).hasTagCompound()
						? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getTagCompound().getDouble("Count")
						: -1)));
		if (((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).hasTagCompound()
				? ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).getTagCompound()
						.getDouble("Count")
				: -1) == 0)) {
			{
				ItemStack _stack = ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY);
				if (!_stack.hasTagCompound())
					_stack.setTagCompound(new NBTTagCompound());
				_stack.getTagCompound().setString("Stored", "");
			}
		}
	}
}
