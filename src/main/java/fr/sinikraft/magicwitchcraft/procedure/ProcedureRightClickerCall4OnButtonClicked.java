package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureRightClickerCall4OnButtonClicked extends ElementsMagicWitchcraft.ModElement {
	public ProcedureRightClickerCall4OnButtonClicked(ElementsMagicWitchcraft instance) {
		super(instance, 236);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure RightClickerCall4OnButtonClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure RightClickerCall4OnButtonClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure RightClickerCall4OnButtonClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure RightClickerCall4OnButtonClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure RightClickerCall4OnButtonClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((((!(((new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockposx"))).equals("X"))) || (!(((new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockposx"))).equals("")))) && ((!(((new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockposy"))).equals("Y"))) || (!(((new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockposy"))).equals(""))))) && ((!(((new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockposz"))).equals("Z"))) || (!(((new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockposz"))).equals("")))))) {
			if (entity instanceof EntityPlayer) {
				BlockPos _bp = new BlockPos((int) new Object() {
					int convert(String s) {
						try {
							return Integer.parseInt(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockposx"))), (int) new Object() {
					int convert(String s) {
						try {
							return Integer.parseInt(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockposy"))), (int) new Object() {
					int convert(String s) {
						try {
							return Integer.parseInt(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockposz"))));
				world.getBlockState(_bp).getBlock().onBlockActivated(world, _bp, world.getBlockState(_bp), (EntityPlayer) entity, EnumHand.MAIN_HAND,
						EnumFacing.UP, _bp.getX(), _bp.getY(), _bp.getZ());
			}
		}
	}
}
