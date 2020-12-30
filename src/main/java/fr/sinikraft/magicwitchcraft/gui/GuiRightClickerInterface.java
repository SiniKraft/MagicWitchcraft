
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
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Container;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiButton;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import java.io.IOException;

import fr.sinikraft.magicwitchcraft.procedure.ProcedureRightClickerSaveOnButtonClicked;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureRightClickerCallOnButtonClicked;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureRightClickerCall4OnButtonClicked;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureRightClickerCall3OnButtonClicked;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureRightClickerCall2OnButtonClicked;
import fr.sinikraft.magicwitchcraft.MagicWitchcraft;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class GuiRightClickerInterface extends ElementsMagicWitchcraft.ModElement {
	public static int GUIID = 8;
	public static HashMap guistate = new HashMap();
	public GuiRightClickerInterface(ElementsMagicWitchcraft instance) {
		super(instance, 231);
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
		GuiTextField FirstBlockPosX;
		GuiTextField FirstBlockPosY;
		GuiTextField FirstBlockPosZ;
		GuiTextField FirstBlockName;
		GuiTextField SecondBlockPosX;
		GuiTextField SecondBlockPosY;
		GuiTextField SecondBlockPosZ;
		GuiTextField SecondBlockName;
		GuiTextField ThirdBlockPosX;
		GuiTextField ThirdBlockPosY;
		GuiTextField ThirdBlockPosZ;
		GuiTextField ThirdBlockName;
		GuiTextField FourthBlockPosX;
		GuiTextField FourthBlockPosY;
		GuiTextField FourthBlockPosZ;
		GuiTextField FourthBlockName;
		public GuiWindow(World world, int x, int y, int z, EntityPlayer entity) {
			super(new GuiContainerMod(world, x, y, z, entity));
			this.world = world;
			this.x = x;
			this.y = y;
			this.z = z;
			this.entity = entity;
			this.xSize = 201;
			this.ySize = 239;
		}
		private static final ResourceLocation texture = new ResourceLocation("magic_witchcraft:textures/rightclickerinterface.png");
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
			FirstBlockPosX.updateCursorCounter();
			FirstBlockPosY.updateCursorCounter();
			FirstBlockPosZ.updateCursorCounter();
			FirstBlockName.updateCursorCounter();
			SecondBlockPosX.updateCursorCounter();
			SecondBlockPosY.updateCursorCounter();
			SecondBlockPosZ.updateCursorCounter();
			SecondBlockName.updateCursorCounter();
			ThirdBlockPosX.updateCursorCounter();
			ThirdBlockPosY.updateCursorCounter();
			ThirdBlockPosZ.updateCursorCounter();
			ThirdBlockName.updateCursorCounter();
			FourthBlockPosX.updateCursorCounter();
			FourthBlockPosY.updateCursorCounter();
			FourthBlockPosZ.updateCursorCounter();
			FourthBlockName.updateCursorCounter();
		}

		@Override
		protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
			FirstBlockPosX.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			FirstBlockPosY.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			FirstBlockPosZ.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			FirstBlockName.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			SecondBlockPosX.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			SecondBlockPosY.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			SecondBlockPosZ.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			SecondBlockName.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			ThirdBlockPosX.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			ThirdBlockPosY.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			ThirdBlockPosZ.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			ThirdBlockName.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			FourthBlockPosX.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			FourthBlockPosY.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			FourthBlockPosZ.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			FourthBlockName.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			super.mouseClicked(mouseX, mouseY, mouseButton);
		}

		@Override
		protected void keyTyped(char typedChar, int keyCode) throws IOException {
			FirstBlockPosX.textboxKeyTyped(typedChar, keyCode);
			if (FirstBlockPosX.isFocused())
				return;
			FirstBlockPosY.textboxKeyTyped(typedChar, keyCode);
			if (FirstBlockPosY.isFocused())
				return;
			FirstBlockPosZ.textboxKeyTyped(typedChar, keyCode);
			if (FirstBlockPosZ.isFocused())
				return;
			FirstBlockName.textboxKeyTyped(typedChar, keyCode);
			if (FirstBlockName.isFocused())
				return;
			SecondBlockPosX.textboxKeyTyped(typedChar, keyCode);
			if (SecondBlockPosX.isFocused())
				return;
			SecondBlockPosY.textboxKeyTyped(typedChar, keyCode);
			if (SecondBlockPosY.isFocused())
				return;
			SecondBlockPosZ.textboxKeyTyped(typedChar, keyCode);
			if (SecondBlockPosZ.isFocused())
				return;
			SecondBlockName.textboxKeyTyped(typedChar, keyCode);
			if (SecondBlockName.isFocused())
				return;
			ThirdBlockPosX.textboxKeyTyped(typedChar, keyCode);
			if (ThirdBlockPosX.isFocused())
				return;
			ThirdBlockPosY.textboxKeyTyped(typedChar, keyCode);
			if (ThirdBlockPosY.isFocused())
				return;
			ThirdBlockPosZ.textboxKeyTyped(typedChar, keyCode);
			if (ThirdBlockPosZ.isFocused())
				return;
			ThirdBlockName.textboxKeyTyped(typedChar, keyCode);
			if (ThirdBlockName.isFocused())
				return;
			FourthBlockPosX.textboxKeyTyped(typedChar, keyCode);
			if (FourthBlockPosX.isFocused())
				return;
			FourthBlockPosY.textboxKeyTyped(typedChar, keyCode);
			if (FourthBlockPosY.isFocused())
				return;
			FourthBlockPosZ.textboxKeyTyped(typedChar, keyCode);
			if (FourthBlockPosZ.isFocused())
				return;
			FourthBlockName.textboxKeyTyped(typedChar, keyCode);
			if (FourthBlockName.isFocused())
				return;
			super.keyTyped(typedChar, keyCode);
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int par1, int par2) {
			this.fontRenderer.drawString("Right clicker", 73, 25, -1);
			this.fontRenderer.drawString("First block", 10, 34, -1);
			FirstBlockPosX.drawTextBox();
			FirstBlockPosY.drawTextBox();
			FirstBlockPosZ.drawTextBox();
			FirstBlockName.drawTextBox();
			this.fontRenderer.drawString("Second block", 10, 79, -1);
			SecondBlockPosX.drawTextBox();
			SecondBlockPosY.drawTextBox();
			SecondBlockPosZ.drawTextBox();
			SecondBlockName.drawTextBox();
			this.fontRenderer.drawString("Third block", 10, 124, -1);
			ThirdBlockPosX.drawTextBox();
			ThirdBlockPosY.drawTextBox();
			ThirdBlockPosZ.drawTextBox();
			ThirdBlockName.drawTextBox();
			this.fontRenderer.drawString("Fourth block", 10, 169, -1);
			FourthBlockPosX.drawTextBox();
			FourthBlockPosY.drawTextBox();
			FourthBlockPosZ.drawTextBox();
			FourthBlockName.drawTextBox();
		}

		@Override
		public void onGuiClosed() {
			super.onGuiClosed();
			Keyboard.enableRepeatEvents(false);
		}

		@Override
		public void initGui() {
			super.initGui();
			this.guiLeft = (this.width - 201) / 2;
			this.guiTop = (this.height - 239) / 2;
			Keyboard.enableRepeatEvents(true);
			this.buttonList.clear();
			FirstBlockPosX = new GuiTextField(0, this.fontRenderer, 10, 52, 18, 20);
			guistate.put("text:FirstBlockPosX", FirstBlockPosX);
			FirstBlockPosX.setMaxStringLength(32767);
			FirstBlockPosX.setText("X");
			FirstBlockPosY = new GuiTextField(1, this.fontRenderer, 37, 52, 18, 20);
			guistate.put("text:FirstBlockPosY", FirstBlockPosY);
			FirstBlockPosY.setMaxStringLength(32767);
			FirstBlockPosY.setText("Y");
			FirstBlockPosZ = new GuiTextField(2, this.fontRenderer, 64, 52, 18, 20);
			guistate.put("text:FirstBlockPosZ", FirstBlockPosZ);
			FirstBlockPosZ.setMaxStringLength(32767);
			FirstBlockPosZ.setText("Z");
			this.buttonList.add(new GuiButton(0, this.guiLeft + 154, this.guiTop + 52, 40, 20, "Call1"));
			FirstBlockName = new GuiTextField(3, this.fontRenderer, 91, 52, 54, 20);
			guistate.put("text:FirstBlockName", FirstBlockName);
			FirstBlockName.setMaxStringLength(32767);
			FirstBlockName.setText("Name");
			SecondBlockPosX = new GuiTextField(4, this.fontRenderer, 10, 97, 18, 20);
			guistate.put("text:SecondBlockPosX", SecondBlockPosX);
			SecondBlockPosX.setMaxStringLength(32767);
			SecondBlockPosX.setText("X");
			SecondBlockPosY = new GuiTextField(5, this.fontRenderer, 37, 97, 18, 20);
			guistate.put("text:SecondBlockPosY", SecondBlockPosY);
			SecondBlockPosY.setMaxStringLength(32767);
			SecondBlockPosY.setText("Y");
			SecondBlockPosZ = new GuiTextField(6, this.fontRenderer, 64, 97, 18, 20);
			guistate.put("text:SecondBlockPosZ", SecondBlockPosZ);
			SecondBlockPosZ.setMaxStringLength(32767);
			SecondBlockPosZ.setText("Z");
			SecondBlockName = new GuiTextField(7, this.fontRenderer, 91, 97, 54, 20);
			guistate.put("text:SecondBlockName", SecondBlockName);
			SecondBlockName.setMaxStringLength(32767);
			SecondBlockName.setText("Name");
			this.buttonList.add(new GuiButton(1, this.guiLeft + 154, this.guiTop + 97, 40, 20, "Call2"));
			ThirdBlockPosX = new GuiTextField(8, this.fontRenderer, 10, 142, 18, 20);
			guistate.put("text:ThirdBlockPosX", ThirdBlockPosX);
			ThirdBlockPosX.setMaxStringLength(32767);
			ThirdBlockPosX.setText("X");
			ThirdBlockPosY = new GuiTextField(9, this.fontRenderer, 37, 142, 18, 20);
			guistate.put("text:ThirdBlockPosY", ThirdBlockPosY);
			ThirdBlockPosY.setMaxStringLength(32767);
			ThirdBlockPosY.setText("Y");
			ThirdBlockPosZ = new GuiTextField(10, this.fontRenderer, 64, 142, 18, 20);
			guistate.put("text:ThirdBlockPosZ", ThirdBlockPosZ);
			ThirdBlockPosZ.setMaxStringLength(32767);
			ThirdBlockPosZ.setText("Z");
			ThirdBlockName = new GuiTextField(11, this.fontRenderer, 91, 142, 54, 20);
			guistate.put("text:ThirdBlockName", ThirdBlockName);
			ThirdBlockName.setMaxStringLength(32767);
			ThirdBlockName.setText("Name");
			this.buttonList.add(new GuiButton(2, this.guiLeft + 154, this.guiTop + 142, 40, 20, "Call3"));
			FourthBlockPosX = new GuiTextField(12, this.fontRenderer, 10, 187, 18, 20);
			guistate.put("text:FourthBlockPosX", FourthBlockPosX);
			FourthBlockPosX.setMaxStringLength(32767);
			FourthBlockPosX.setText("X");
			FourthBlockPosY = new GuiTextField(13, this.fontRenderer, 37, 187, 18, 20);
			guistate.put("text:FourthBlockPosY", FourthBlockPosY);
			FourthBlockPosY.setMaxStringLength(32767);
			FourthBlockPosY.setText("Y");
			FourthBlockPosZ = new GuiTextField(14, this.fontRenderer, 64, 187, 18, 20);
			guistate.put("text:FourthBlockPosZ", FourthBlockPosZ);
			FourthBlockPosZ.setMaxStringLength(32767);
			FourthBlockPosZ.setText("Z");
			FourthBlockName = new GuiTextField(15, this.fontRenderer, 91, 187, 54, 20);
			guistate.put("text:FourthBlockName", FourthBlockName);
			FourthBlockName.setMaxStringLength(32767);
			FourthBlockName.setText("Name");
			this.buttonList.add(new GuiButton(3, this.guiLeft + 154, this.guiTop + 187, 40, 20, "Call4"));
			this.buttonList.add(new GuiButton(4, this.guiLeft + 155, this.guiTop + 22, 40, 20, "Save"));
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
				ProcedureRightClickerCallOnButtonClicked.executeProcedure($_dependencies);
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
				ProcedureRightClickerCall2OnButtonClicked.executeProcedure($_dependencies);
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
				ProcedureRightClickerCall3OnButtonClicked.executeProcedure($_dependencies);
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
				ProcedureRightClickerCall4OnButtonClicked.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 4) {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("guistate", guistate);
				$_dependencies.put("world", world);
				ProcedureRightClickerSaveOnButtonClicked.executeProcedure($_dependencies);
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
