package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.GuiTextField;

import java.util.HashMap;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftVariables;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureTeleporterBeaconInterfaceSetOnButtonClicked extends ElementsMagicWitchcraft.ModElement {
	public ProcedureTeleporterBeaconInterfaceSetOnButtonClicked(ElementsMagicWitchcraft instance) {
		super(instance, 343);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure TeleporterBeaconInterfaceSetOnButtonClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure TeleporterBeaconInterfaceSetOnButtonClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure TeleporterBeaconInterfaceSetOnButtonClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure TeleporterBeaconInterfaceSetOnButtonClicked!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			System.err.println("Failed to load dependency guistate for procedure TeleporterBeaconInterfaceSetOnButtonClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure TeleporterBeaconInterfaceSetOnButtonClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		World world = (World) dependencies.get("world");
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
				GuiTextField textField = (GuiTextField) guistate.get("text:NumberToSet");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())) == 1)) {
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN1PosX = (double) x;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN1PosY = (double) y;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN1PosZ = (double) z;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN1Name = (String) (new Object() {
				public String getText() {
					GuiTextField textField = (GuiTextField) guistate.get("text:NewName");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText());
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).closeScreen();
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u00A7aSet !"), (false));
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
				GuiTextField textField = (GuiTextField) guistate.get("text:NumberToSet");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())) == 2)) {
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN2PosX = (double) x;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN2PosY = (double) y;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN2PosZ = (double) z;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN2Name = (String) (new Object() {
				public String getText() {
					GuiTextField textField = (GuiTextField) guistate.get("text:NewName");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText());
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).closeScreen();
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u00A7aSet !"), (false));
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
				GuiTextField textField = (GuiTextField) guistate.get("text:NumberToSet");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())) == 3)) {
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN3PosX = (double) x;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN3PosY = (double) y;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN3PosZ = (double) z;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN3Name = (String) (new Object() {
				public String getText() {
					GuiTextField textField = (GuiTextField) guistate.get("text:NewName");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText());
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).closeScreen();
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u00A7aSet !"), (false));
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
				GuiTextField textField = (GuiTextField) guistate.get("text:NumberToSet");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())) == 4)) {
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN4PosX = (double) x;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN4PosY = (double) y;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN4PosZ = (double) z;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN4Name = (String) (new Object() {
				public String getText() {
					GuiTextField textField = (GuiTextField) guistate.get("text:NewName");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText());
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).closeScreen();
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u00A7aSet !"), (false));
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
				GuiTextField textField = (GuiTextField) guistate.get("text:NumberToSet");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())) == 5)) {
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN5PosX = (double) x;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN5PosY = (double) y;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN5PosZ = (double) z;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN5Name = (String) (new Object() {
				public String getText() {
					GuiTextField textField = (GuiTextField) guistate.get("text:NewName");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText());
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).closeScreen();
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u00A7aSet !"), (false));
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
				GuiTextField textField = (GuiTextField) guistate.get("text:NumberToSet");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())) == 6)) {
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN6PosX = (double) x;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN6PosY = (double) y;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN6PosZ = (double) z;
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN6Name = (String) (new Object() {
				public String getText() {
					GuiTextField textField = (GuiTextField) guistate.get("text:NewName");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText());
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).closeScreen();
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u00A7aSet !"), (false));
			}
		} else {
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u00A7cNumber must be valid and between 1 and 6 !"), (false));
			}
		}
	}
}
