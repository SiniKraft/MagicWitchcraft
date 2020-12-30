
package fr.sinikraft.magicwitchcraft.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Container;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.GuiButton;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import java.io.IOException;

import fr.sinikraft.magicwitchcraft.procedure.ProcedureTeleporterInterfaceGoTo6OnButtonClicked;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureTeleporterInterfaceGoTo5OnButtonClicked;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureTeleporterInterfaceGoTo4OnButtonClicked;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureTeleporterInterfaceGoTo3OnButtonClicked;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureTeleporterInterfaceGoTo2OnButtonClicked;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureTeleporterInterfaceGoTo1OnButtonClicked;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftVariables;
import fr.sinikraft.magicwitchcraft.MagicWitchcraft;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class GuiTeleporterInterface extends ElementsMagicWitchcraft.ModElement {
	public static int GUIID = 13;
	public static HashMap guistate = new HashMap();
	public GuiTeleporterInterface(ElementsMagicWitchcraft instance) {
		super(instance, 337);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		elements.addNetworkMessage(GUIButtonPressedMessageHandler.class, GUIButtonPressedMessage.class, Side.SERVER);
		elements.addNetworkMessage(GUISlotChangedMessageHandler.class, GUISlotChangedMessage.class, Side.SERVER);
	}
	public static class GuiContainerMod extends Container implements Supplier<Map<Integer, Slot>> {
		private IInventory internal;
		private World world;
		private EntityPlayer entity;
		private int x, y, z;
		private Map<Integer, Slot> customSlots = new HashMap<>();
		public GuiContainerMod(World world, int x, int y, int z, EntityPlayer player) {
			this.world = world;
			this.entity = player;
			this.x = x;
			this.y = y;
			this.z = z;
			this.internal = new InventoryBasic("", true, 0);
		}

		public Map<Integer, Slot> get() {
			return customSlots;
		}

		@Override
		public boolean canInteractWith(EntityPlayer player) {
			return internal.isUsableByPlayer(player);
		}

		@Override
		public void onContainerClosed(EntityPlayer playerIn) {
			super.onContainerClosed(playerIn);
			if ((internal instanceof InventoryBasic) && (playerIn instanceof EntityPlayerMP)) {
				this.clearContainer(playerIn, playerIn.world, internal);
			}
		}

		private void slotChanged(int slotid, int ctype, int meta) {
			if (this.world != null && this.world.isRemote) {
				MagicWitchcraft.PACKET_HANDLER.sendToServer(new GUISlotChangedMessage(slotid, x, y, z, ctype, meta));
				handleSlotAction(entity, slotid, ctype, meta, x, y, z);
			}
		}
	}

	public static class GuiWindow extends GuiContainer {
		private World world;
		private int x, y, z;
		private EntityPlayer entity;
		public GuiWindow(World world, int x, int y, int z, EntityPlayer entity) {
			super(new GuiContainerMod(world, x, y, z, entity));
			this.world = world;
			this.x = x;
			this.y = y;
			this.z = z;
			this.entity = entity;
			this.xSize = 256;
			this.ySize = 213;
		}
		private static final ResourceLocation texture = new ResourceLocation("magic_witchcraft:textures/teleporterinterface.png");
		@Override
		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			this.drawDefaultBackground();
			super.drawScreen(mouseX, mouseY, partialTicks);
			this.renderHoveredToolTip(mouseX, mouseY);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mc.renderEngine.bindTexture(texture);
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
			zLevel = 100.0F;
		}

		@Override
		public void updateScreen() {
			super.updateScreen();
		}

		@Override
		protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
			super.mouseClicked(mouseX, mouseY, mouseButton);
		}

		@Override
		protected void keyTyped(char typedChar, int keyCode) throws IOException {
			super.keyTyped(typedChar, keyCode);
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int par1, int par2) {
			this.fontRenderer.drawString("Teleporter", 101, 8, -1);
			this.fontRenderer.drawString("1. ", 11, 26, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN1Name) + "", 29, 26, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN1PosX) + "", 128, 26, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN1PosY) + "", 173, 26, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN1PosZ) + "", 209, 26, -1);
			this.fontRenderer.drawString("2.", 11, 44, -1);
			this.fontRenderer.drawString("3.", 11, 62, -1);
			this.fontRenderer.drawString("4.", 11, 80, -1);
			this.fontRenderer.drawString("5.", 11, 98, -1);
			this.fontRenderer.drawString("6.", 11, 116, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN2Name) + "", 29, 44, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN2PosX) + "", 128, 44, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN2PosY) + "", 173, 44, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN2PosZ) + "", 209, 44, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN3Name) + "", 29, 62, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN3PosX) + "", 128, 62, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN3PosY) + "", 173, 62, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN3PosZ) + "", 209, 62, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN4Name) + "", 29, 80, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN4PosX) + "", 128, 80, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN4PosY) + "", 173, 80, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN4PosZ) + "", 209, 80, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN5Name) + "", 29, 98, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN5PosX) + "", 128, 98, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN5PosY) + "", 173, 98, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN5PosZ) + "", 209, 98, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN6Name) + "", 29, 116, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN6PosX) + "", 128, 116, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN6PosY) + "", 173, 116, -1);
			this.fontRenderer.drawString("" + (MagicWitchcraftVariables.WorldVariables.get(world).TeleporterPublicN6PosZ) + "", 209, 116, -1);
			this.fontRenderer.drawString("Energy stored", 180, 143, -16738048);
			this.fontRenderer.drawString("" + (new Object() {
				public double getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return 0;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "EnergyStored")) + "", 200, 170, -16711936);
			this.fontRenderer.drawString("/ 1000 MER", 193, 179, -16711936);
		}

		@Override
		public void onGuiClosed() {
			super.onGuiClosed();
			Keyboard.enableRepeatEvents(false);
		}

		@Override
		public void initGui() {
			super.initGui();
			this.guiLeft = (this.width - 256) / 2;
			this.guiTop = (this.height - 213) / 2;
			Keyboard.enableRepeatEvents(true);
			this.buttonList.clear();
			this.buttonList.add(new GuiButton(0, this.guiLeft + 20, this.guiTop + 143, 45, 20, "Go to 1"));
			this.buttonList.add(new GuiButton(1, this.guiLeft + 20, this.guiTop + 179, 45, 20, "Go to 2"));
			this.buttonList.add(new GuiButton(2, this.guiLeft + 74, this.guiTop + 143, 45, 20, "Go to 3"));
			this.buttonList.add(new GuiButton(3, this.guiLeft + 74, this.guiTop + 179, 45, 20, "Go to 4"));
			this.buttonList.add(new GuiButton(4, this.guiLeft + 128, this.guiTop + 143, 45, 20, "Go to 5"));
			this.buttonList.add(new GuiButton(5, this.guiLeft + 128, this.guiTop + 179, 45, 20, "Go to 6"));
		}

		@Override
		protected void actionPerformed(GuiButton button) {
			MagicWitchcraft.PACKET_HANDLER.sendToServer(new GUIButtonPressedMessage(button.id, x, y, z));
			handleButtonAction(entity, button.id, x, y, z);
		}

		@Override
		public boolean doesGuiPauseGame() {
			return false;
		}
	}

	public static class GUIButtonPressedMessageHandler implements IMessageHandler<GUIButtonPressedMessage, IMessage> {
		@Override
		public IMessage onMessage(GUIButtonPressedMessage message, MessageContext context) {
			EntityPlayerMP entity = context.getServerHandler().player;
			entity.getServerWorld().addScheduledTask(() -> {
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			});
			return null;
		}
	}

	public static class GUISlotChangedMessageHandler implements IMessageHandler<GUISlotChangedMessage, IMessage> {
		@Override
		public IMessage onMessage(GUISlotChangedMessage message, MessageContext context) {
			EntityPlayerMP entity = context.getServerHandler().player;
			entity.getServerWorld().addScheduledTask(() -> {
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			});
			return null;
		}
	}

	public static class GUIButtonPressedMessage implements IMessage {
		int buttonID, x, y, z;
		public GUIButtonPressedMessage() {
		}

		public GUIButtonPressedMessage(int buttonID, int x, int y, int z) {
			this.buttonID = buttonID;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public void toBytes(io.netty.buffer.ByteBuf buf) {
			buf.writeInt(buttonID);
			buf.writeInt(x);
			buf.writeInt(y);
			buf.writeInt(z);
		}

		@Override
		public void fromBytes(io.netty.buffer.ByteBuf buf) {
			buttonID = buf.readInt();
			x = buf.readInt();
			y = buf.readInt();
			z = buf.readInt();
		}
	}

	public static class GUISlotChangedMessage implements IMessage {
		int slotID, x, y, z, changeType, meta;
		public GUISlotChangedMessage() {
		}

		public GUISlotChangedMessage(int slotID, int x, int y, int z, int changeType, int meta) {
			this.slotID = slotID;
			this.x = x;
			this.y = y;
			this.z = z;
			this.changeType = changeType;
			this.meta = meta;
		}

		@Override
		public void toBytes(io.netty.buffer.ByteBuf buf) {
			buf.writeInt(slotID);
			buf.writeInt(x);
			buf.writeInt(y);
			buf.writeInt(z);
			buf.writeInt(changeType);
			buf.writeInt(meta);
		}

		@Override
		public void fromBytes(io.netty.buffer.ByteBuf buf) {
			slotID = buf.readInt();
			x = buf.readInt();
			y = buf.readInt();
			z = buf.readInt();
			changeType = buf.readInt();
			meta = buf.readInt();
		}
	}
	private static void handleButtonAction(EntityPlayer entity, int buttonID, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureTeleporterInterfaceGoTo1OnButtonClicked.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 1) {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureTeleporterInterfaceGoTo2OnButtonClicked.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 2) {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureTeleporterInterfaceGoTo3OnButtonClicked.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 3) {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureTeleporterInterfaceGoTo4OnButtonClicked.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 4) {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureTeleporterInterfaceGoTo5OnButtonClicked.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 5) {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureTeleporterInterfaceGoTo6OnButtonClicked.executeProcedure($_dependencies);
			}
		}
	}

	private static void handleSlotAction(EntityPlayer entity, int slotID, int changeType, int meta, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}
}
