package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;

import java.util.Map;
import java.util.HashMap;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModVariables;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class TeleporterBeaconInterfaceSetOnButtonClickedProcedure extends MagicWitchcraftModElements.ModElement {
	public TeleporterBeaconInterfaceSetOnButtonClickedProcedure(MagicWitchcraftModElements instance) {
		super(instance, 343);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency entity for procedure TeleporterBeaconInterfaceSetOnButtonClicked!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency guistate for procedure TeleporterBeaconInterfaceSetOnButtonClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency x for procedure TeleporterBeaconInterfaceSetOnButtonClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency y for procedure TeleporterBeaconInterfaceSetOnButtonClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency z for procedure TeleporterBeaconInterfaceSetOnButtonClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency world for procedure TeleporterBeaconInterfaceSetOnButtonClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:NumberToSet");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())) == 1)) {
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN1PosX = (double) x;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN1PosY = (double) y;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN1PosZ = (double) z;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN1Name = (String) (new Object() {
				public String getText() {
					TextFieldWidget textField = (TextFieldWidget) guistate.get("text:NewName");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText());
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7aSet !"), (false));
			}
		} else if ((new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:NumberToSet");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())) == 2)) {
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN2PosX = (double) x;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN2PosY = (double) y;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN2PosZ = (double) z;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN2Name = (String) (new Object() {
				public String getText() {
					TextFieldWidget textField = (TextFieldWidget) guistate.get("text:NewName");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText());
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7aSet !"), (false));
			}
		} else if ((new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:NumberToSet");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())) == 3)) {
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN3PosX = (double) x;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN3PosY = (double) y;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN3PosZ = (double) z;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN3Name = (String) (new Object() {
				public String getText() {
					TextFieldWidget textField = (TextFieldWidget) guistate.get("text:NewName");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText());
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7aSet !"), (false));
			}
		} else if ((new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:NumberToSet");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())) == 4)) {
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosX = (double) x;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosY = (double) y;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosZ = (double) z;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4Name = (String) (new Object() {
				public String getText() {
					TextFieldWidget textField = (TextFieldWidget) guistate.get("text:NewName");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText());
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7aSet !"), (false));
			}
		} else if ((new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:NumberToSet");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())) == 5)) {
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN5PosX = (double) x;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN5PosY = (double) y;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN5PosZ = (double) z;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN5Name = (String) (new Object() {
				public String getText() {
					TextFieldWidget textField = (TextFieldWidget) guistate.get("text:NewName");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText());
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7aSet !"), (false));
			}
		} else if ((new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:NumberToSet");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())) == 6)) {
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN6PosX = (double) x;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN6PosY = (double) y;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN6PosZ = (double) z;
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN6Name = (String) (new Object() {
				public String getText() {
					TextFieldWidget textField = (TextFieldWidget) guistate.get("text:NewName");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText());
			MagicWitchcraftModVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7aSet !"), (false));
			}
		} else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cNumber must be valid and between 1 and 6 !"), (false));
			}
		}
	}
}
