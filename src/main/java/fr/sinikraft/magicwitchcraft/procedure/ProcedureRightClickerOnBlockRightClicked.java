package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.gui.GuiRightClickerInterface;
import fr.sinikraft.magicwitchcraft.MagicWitchcraft;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureRightClickerOnBlockRightClicked extends ElementsMagicWitchcraft.ModElement {
	public ProcedureRightClickerOnBlockRightClicked(ElementsMagicWitchcraft instance) {
		super(instance, 237);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure RightClickerOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure RightClickerOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure RightClickerOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure RightClickerOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure RightClickerOnBlockRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((entity.isSneaking())) {
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString((("First block : ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "firstblockname"))) + "" + (" ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "firstblockposx"))) + "" + (" ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "firstblockposy"))) + "" + (" ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "firstblockposz"))))), (false));
			}
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString((("Second block : ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "secondblockname"))) + "" + (" ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "secondblockposx"))) + "" + (" ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "secondblockposy"))) + "" + (" ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "secondblockposz"))))), (false));
			}
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString((("Third block : ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "thirdblockname"))) + "" + (" ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "thirdblockposx"))) + "" + (" ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "thirdblockposy"))) + "" + (" ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "thirdblockposz"))))), (false));
			}
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString((("Fourth block : ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockname"))) + "" + (" ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockposx"))) + "" + (" ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockposy"))) + "" + (" ") + "" + ((new Object() {
					public String getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "fourthblockposz"))))), (false));
			}
		} else {
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).openGui(MagicWitchcraft.instance, GuiRightClickerInterface.GUIID, world, x, y, z);
		}
	}
}
