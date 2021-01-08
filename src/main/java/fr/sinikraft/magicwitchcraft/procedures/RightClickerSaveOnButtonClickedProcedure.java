package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.block.BlockState;

import java.util.Map;
import java.util.HashMap;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class RightClickerSaveOnButtonClickedProcedure extends MagicWitchcraftModElements.ModElement {
	public RightClickerSaveOnButtonClickedProcedure(MagicWitchcraftModElements instance) {
		super(instance, 232);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				System.err.println("Failed to load dependency guistate for procedure RightClickerSaveOnButtonClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure RightClickerSaveOnButtonClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure RightClickerSaveOnButtonClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure RightClickerSaveOnButtonClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure RightClickerSaveOnButtonClicked!");
			return;
		}
		HashMap guistate = (HashMap) dependencies.get("guistate");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("firstblockposx", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:FirstBlockPosX");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("firstblockposy", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:FirstBlockPosY");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("firstblockposz", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:FirstBlockPosZ");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("firstblockname", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:FirstBlockName");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("secondblockposx", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:SecondBlockPosX");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("secondblockposy", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:SecondBlockPosY");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("secondblockposz", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:SecondBlockPosZ");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("secondblockname", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:SecondBlockName");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("thirdblockposx", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:ThirdBlockPosX");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("thirdblockposy", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:ThirdBlockPosY");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("thirdblockposz", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:ThirdBlockPosZ");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("thirdblockname", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:ThirdBlockName");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("fourthblockposx", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:FourthBlockPosX");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("fourthblockposy", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:FourthBlockPosY");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("fourthblockposz", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:FourthBlockPosZ");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("fourthblockname", (new Object() {
					public String getText() {
						TextFieldWidget textField = (TextFieldWidget) guistate.get("text:FourthBlockName");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText()));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
	}
}
