package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.block.state.IBlockState;

import java.util.HashMap;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureRightClickerSaveOnButtonClicked extends ElementsMagicWitchcraft.ModElement {
	public ProcedureRightClickerSaveOnButtonClicked(ElementsMagicWitchcraft instance) {
		super(instance, 232);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure RightClickerSaveOnButtonClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure RightClickerSaveOnButtonClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure RightClickerSaveOnButtonClicked!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			System.err.println("Failed to load dependency guistate for procedure RightClickerSaveOnButtonClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure RightClickerSaveOnButtonClicked!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		World world = (World) dependencies.get("world");
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("firstblockposx", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:FirstBlockPosX");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("firstblockposy", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:FirstBlockPosY");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("firstblockposz", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:FirstBlockPosZ");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("firstblockname", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:FirstBlockName");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("secondblockposx", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:SecondBlockPosX");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("secondblockposy", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:SecondBlockPosY");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("secondblockposz", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:SecondBlockPosZ");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("secondblockname", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:SecondBlockName");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("thirdblockposx", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:ThirdBlockPosX");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("thirdblockposy", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:ThirdBlockPosY");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("thirdblockposz", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:ThirdBlockPosZ");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("thirdblockname", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:ThirdBlockName");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("fourthblockposx", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:FourthBlockPosX");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("fourthblockposy", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:FourthBlockPosY");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("fourthblockposz", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:FourthBlockPosZ");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			IBlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().setString("fourthblockname", (new Object() {
					public String getText() {
						GuiTextField textField = (GuiTextField) guistate.get("text:FourthBlockName");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
	}
}
