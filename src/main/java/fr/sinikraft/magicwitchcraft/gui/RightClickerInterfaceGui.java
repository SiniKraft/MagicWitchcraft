
package fr.sinikraft.magicwitchcraft.gui;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.network.PacketBuffer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import fr.sinikraft.magicwitchcraft.procedures.RightClickerSaveOnButtonClickedProcedure;
import fr.sinikraft.magicwitchcraft.procedures.RightClickerCallOnButtonClickedProcedure;
import fr.sinikraft.magicwitchcraft.procedures.RightClickerCall4OnButtonClickedProcedure;
import fr.sinikraft.magicwitchcraft.procedures.RightClickerCall3OnButtonClickedProcedure;
import fr.sinikraft.magicwitchcraft.procedures.RightClickerCall2OnButtonClickedProcedure;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class RightClickerInterfaceGui extends MagicWitchcraftModElements.ModElement {
	public static HashMap guistate = new HashMap();
	private static ContainerType<GuiContainerMod> containerType = null;
	public RightClickerInterfaceGui(MagicWitchcraftModElements instance) {
		super(instance, 231);
		elements.addNetworkMessage(ButtonPressedMessage.class, ButtonPressedMessage::buffer, ButtonPressedMessage::new,
				ButtonPressedMessage::handler);
		elements.addNetworkMessage(GUISlotChangedMessage.class, GUISlotChangedMessage::buffer, GUISlotChangedMessage::new,
				GUISlotChangedMessage::handler);
		containerType = new ContainerType<>(new GuiContainerModFactory());
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@OnlyIn(Dist.CLIENT)
	public void initElements() {
		DeferredWorkQueue.runLater(() -> ScreenManager.registerFactory(containerType, GuiWindow::new));
	}

	@SubscribeEvent
	public void registerContainer(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(containerType.setRegistryName("rightclickerinterface"));
	}
	public static class GuiContainerModFactory implements IContainerFactory {
		public GuiContainerMod create(int id, PlayerInventory inv, PacketBuffer extraData) {
			return new GuiContainerMod(id, inv, extraData);
		}
	}

	public static class GuiContainerMod extends Container implements Supplier<Map<Integer, Slot>> {
		private World world;
		private PlayerEntity entity;
		private int x, y, z;
		private IItemHandler internal;
		private Map<Integer, Slot> customSlots = new HashMap<>();
		private boolean bound = false;
		public GuiContainerMod(int id, PlayerInventory inv, PacketBuffer extraData) {
			super(containerType, id);
			this.entity = inv.player;
			this.world = inv.player.world;
			this.internal = new ItemStackHandler(0);
			BlockPos pos = null;
			if (extraData != null) {
				pos = extraData.readBlockPos();
				this.x = pos.getX();
				this.y = pos.getY();
				this.z = pos.getZ();
			}
		}

		public Map<Integer, Slot> get() {
			return customSlots;
		}

		@Override
		public boolean canInteractWith(PlayerEntity player) {
			return true;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class GuiWindow extends ContainerScreen<GuiContainerMod> {
		private World world;
		private int x, y, z;
		private PlayerEntity entity;
		TextFieldWidget FirstBlockPosX;
		TextFieldWidget FirstBlockPosY;
		TextFieldWidget FirstBlockPosZ;
		TextFieldWidget FirstBlockName;
		TextFieldWidget SecondBlockPosX;
		TextFieldWidget SecondBlockPosY;
		TextFieldWidget SecondBlockPosZ;
		TextFieldWidget SecondBlockName;
		TextFieldWidget ThirdBlockPosX;
		TextFieldWidget ThirdBlockPosY;
		TextFieldWidget ThirdBlockPosZ;
		TextFieldWidget ThirdBlockName;
		TextFieldWidget FourthBlockPosX;
		TextFieldWidget FourthBlockPosY;
		TextFieldWidget FourthBlockPosZ;
		TextFieldWidget FourthBlockName;
		public GuiWindow(GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
			super(container, inventory, text);
			this.world = container.world;
			this.x = container.x;
			this.y = container.y;
			this.z = container.z;
			this.entity = container.entity;
			this.xSize = 201;
			this.ySize = 239;
		}
		private static final ResourceLocation texture = new ResourceLocation("magic_witchcraft:textures/rightclickerinterface.png");
		@Override
		public void render(int mouseX, int mouseY, float partialTicks) {
			this.renderBackground();
			super.render(mouseX, mouseY, partialTicks);
			this.renderHoveredToolTip(mouseX, mouseY);
			FirstBlockPosX.render(mouseX, mouseY, partialTicks);
			FirstBlockPosY.render(mouseX, mouseY, partialTicks);
			FirstBlockPosZ.render(mouseX, mouseY, partialTicks);
			FirstBlockName.render(mouseX, mouseY, partialTicks);
			SecondBlockPosX.render(mouseX, mouseY, partialTicks);
			SecondBlockPosY.render(mouseX, mouseY, partialTicks);
			SecondBlockPosZ.render(mouseX, mouseY, partialTicks);
			SecondBlockName.render(mouseX, mouseY, partialTicks);
			ThirdBlockPosX.render(mouseX, mouseY, partialTicks);
			ThirdBlockPosY.render(mouseX, mouseY, partialTicks);
			ThirdBlockPosZ.render(mouseX, mouseY, partialTicks);
			ThirdBlockName.render(mouseX, mouseY, partialTicks);
			FourthBlockPosX.render(mouseX, mouseY, partialTicks);
			FourthBlockPosY.render(mouseX, mouseY, partialTicks);
			FourthBlockPosZ.render(mouseX, mouseY, partialTicks);
			FourthBlockName.render(mouseX, mouseY, partialTicks);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
			GL11.glColor4f(1, 1, 1, 1);
			Minecraft.getInstance().getTextureManager().bindTexture(texture);
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			this.blit(k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		}

		@Override
		public boolean keyPressed(int key, int b, int c) {
			if (key == 256) {
				this.minecraft.player.closeScreen();
				return true;
			}
			if (FirstBlockPosX.isFocused())
				return FirstBlockPosX.keyPressed(key, b, c);
			if (FirstBlockPosY.isFocused())
				return FirstBlockPosY.keyPressed(key, b, c);
			if (FirstBlockPosZ.isFocused())
				return FirstBlockPosZ.keyPressed(key, b, c);
			if (FirstBlockName.isFocused())
				return FirstBlockName.keyPressed(key, b, c);
			if (SecondBlockPosX.isFocused())
				return SecondBlockPosX.keyPressed(key, b, c);
			if (SecondBlockPosY.isFocused())
				return SecondBlockPosY.keyPressed(key, b, c);
			if (SecondBlockPosZ.isFocused())
				return SecondBlockPosZ.keyPressed(key, b, c);
			if (SecondBlockName.isFocused())
				return SecondBlockName.keyPressed(key, b, c);
			if (ThirdBlockPosX.isFocused())
				return ThirdBlockPosX.keyPressed(key, b, c);
			if (ThirdBlockPosY.isFocused())
				return ThirdBlockPosY.keyPressed(key, b, c);
			if (ThirdBlockPosZ.isFocused())
				return ThirdBlockPosZ.keyPressed(key, b, c);
			if (ThirdBlockName.isFocused())
				return ThirdBlockName.keyPressed(key, b, c);
			if (FourthBlockPosX.isFocused())
				return FourthBlockPosX.keyPressed(key, b, c);
			if (FourthBlockPosY.isFocused())
				return FourthBlockPosY.keyPressed(key, b, c);
			if (FourthBlockPosZ.isFocused())
				return FourthBlockPosZ.keyPressed(key, b, c);
			if (FourthBlockName.isFocused())
				return FourthBlockName.keyPressed(key, b, c);
			return super.keyPressed(key, b, c);
		}

		@Override
		public void tick() {
			super.tick();
			FirstBlockPosX.tick();
			FirstBlockPosY.tick();
			FirstBlockPosZ.tick();
			FirstBlockName.tick();
			SecondBlockPosX.tick();
			SecondBlockPosY.tick();
			SecondBlockPosZ.tick();
			SecondBlockName.tick();
			ThirdBlockPosX.tick();
			ThirdBlockPosY.tick();
			ThirdBlockPosZ.tick();
			ThirdBlockName.tick();
			FourthBlockPosX.tick();
			FourthBlockPosY.tick();
			FourthBlockPosZ.tick();
			FourthBlockName.tick();
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
			this.font.drawString("Right clicker", 73, 24, -1);
			this.font.drawString("First block", 10, 33, -1);
			this.font.drawString("Second block", 10, 78, -1);
			this.font.drawString("Third block", 10, 123, -1);
			this.font.drawString("Fourth block", 10, 168, -1);
		}

		@Override
		public void removed() {
			super.removed();
			Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
		}

		@Override
		public void init(Minecraft minecraft, int width, int height) {
			super.init(minecraft, width, height);
			minecraft.keyboardListener.enableRepeatEvents(true);
			FirstBlockPosX = new TextFieldWidget(this.font, this.guiLeft + 10, this.guiTop + 51, 18, 20, "X") {
				{
					setSuggestion("X");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("X");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("X");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:FirstBlockPosX", FirstBlockPosX);
			FirstBlockPosX.setMaxStringLength(32767);
			this.children.add(this.FirstBlockPosX);
			FirstBlockPosY = new TextFieldWidget(this.font, this.guiLeft + 37, this.guiTop + 51, 18, 20, "Y") {
				{
					setSuggestion("Y");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Y");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Y");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:FirstBlockPosY", FirstBlockPosY);
			FirstBlockPosY.setMaxStringLength(32767);
			this.children.add(this.FirstBlockPosY);
			FirstBlockPosZ = new TextFieldWidget(this.font, this.guiLeft + 64, this.guiTop + 51, 18, 20, "Z") {
				{
					setSuggestion("Z");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Z");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Z");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:FirstBlockPosZ", FirstBlockPosZ);
			FirstBlockPosZ.setMaxStringLength(32767);
			this.children.add(this.FirstBlockPosZ);
			this.addButton(new Button(this.guiLeft + 154, this.guiTop + 52, 40, 20, "Call1", e -> {
				MagicWitchcraftMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(0, x, y, z));
				handleButtonAction(entity, 0, x, y, z);
			}));
			FirstBlockName = new TextFieldWidget(this.font, this.guiLeft + 91, this.guiTop + 51, 54, 20, "Name") {
				{
					setSuggestion("Name");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Name");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Name");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:FirstBlockName", FirstBlockName);
			FirstBlockName.setMaxStringLength(32767);
			this.children.add(this.FirstBlockName);
			SecondBlockPosX = new TextFieldWidget(this.font, this.guiLeft + 10, this.guiTop + 96, 18, 20, "X") {
				{
					setSuggestion("X");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("X");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("X");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:SecondBlockPosX", SecondBlockPosX);
			SecondBlockPosX.setMaxStringLength(32767);
			this.children.add(this.SecondBlockPosX);
			SecondBlockPosY = new TextFieldWidget(this.font, this.guiLeft + 37, this.guiTop + 96, 18, 20, "Y") {
				{
					setSuggestion("Y");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Y");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Y");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:SecondBlockPosY", SecondBlockPosY);
			SecondBlockPosY.setMaxStringLength(32767);
			this.children.add(this.SecondBlockPosY);
			SecondBlockPosZ = new TextFieldWidget(this.font, this.guiLeft + 64, this.guiTop + 96, 18, 20, "Z") {
				{
					setSuggestion("Z");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Z");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Z");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:SecondBlockPosZ", SecondBlockPosZ);
			SecondBlockPosZ.setMaxStringLength(32767);
			this.children.add(this.SecondBlockPosZ);
			SecondBlockName = new TextFieldWidget(this.font, this.guiLeft + 91, this.guiTop + 96, 54, 20, "Name") {
				{
					setSuggestion("Name");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Name");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Name");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:SecondBlockName", SecondBlockName);
			SecondBlockName.setMaxStringLength(32767);
			this.children.add(this.SecondBlockName);
			this.addButton(new Button(this.guiLeft + 154, this.guiTop + 96, 40, 20, "Call2", e -> {
				MagicWitchcraftMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(1, x, y, z));
				handleButtonAction(entity, 1, x, y, z);
			}));
			ThirdBlockPosX = new TextFieldWidget(this.font, this.guiLeft + 10, this.guiTop + 141, 18, 20, "X") {
				{
					setSuggestion("X");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("X");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("X");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:ThirdBlockPosX", ThirdBlockPosX);
			ThirdBlockPosX.setMaxStringLength(32767);
			this.children.add(this.ThirdBlockPosX);
			ThirdBlockPosY = new TextFieldWidget(this.font, this.guiLeft + 37, this.guiTop + 141, 18, 20, "Y") {
				{
					setSuggestion("Y");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Y");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Y");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:ThirdBlockPosY", ThirdBlockPosY);
			ThirdBlockPosY.setMaxStringLength(32767);
			this.children.add(this.ThirdBlockPosY);
			ThirdBlockPosZ = new TextFieldWidget(this.font, this.guiLeft + 64, this.guiTop + 141, 18, 20, "Z") {
				{
					setSuggestion("Z");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Z");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Z");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:ThirdBlockPosZ", ThirdBlockPosZ);
			ThirdBlockPosZ.setMaxStringLength(32767);
			this.children.add(this.ThirdBlockPosZ);
			ThirdBlockName = new TextFieldWidget(this.font, this.guiLeft + 91, this.guiTop + 141, 54, 20, "Name") {
				{
					setSuggestion("Name");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Name");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Name");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:ThirdBlockName", ThirdBlockName);
			ThirdBlockName.setMaxStringLength(32767);
			this.children.add(this.ThirdBlockName);
			this.addButton(new Button(this.guiLeft + 154, this.guiTop + 141, 40, 20, "Call3", e -> {
				MagicWitchcraftMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(2, x, y, z));
				handleButtonAction(entity, 2, x, y, z);
			}));
			FourthBlockPosX = new TextFieldWidget(this.font, this.guiLeft + 10, this.guiTop + 186, 18, 20, "X") {
				{
					setSuggestion("X");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("X");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("X");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:FourthBlockPosX", FourthBlockPosX);
			FourthBlockPosX.setMaxStringLength(32767);
			this.children.add(this.FourthBlockPosX);
			FourthBlockPosY = new TextFieldWidget(this.font, this.guiLeft + 37, this.guiTop + 186, 18, 20, "Y") {
				{
					setSuggestion("Y");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Y");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Y");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:FourthBlockPosY", FourthBlockPosY);
			FourthBlockPosY.setMaxStringLength(32767);
			this.children.add(this.FourthBlockPosY);
			FourthBlockPosZ = new TextFieldWidget(this.font, this.guiLeft + 64, this.guiTop + 186, 18, 20, "Z") {
				{
					setSuggestion("Z");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Z");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Z");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:FourthBlockPosZ", FourthBlockPosZ);
			FourthBlockPosZ.setMaxStringLength(32767);
			this.children.add(this.FourthBlockPosZ);
			FourthBlockName = new TextFieldWidget(this.font, this.guiLeft + 91, this.guiTop + 186, 54, 20, "Name") {
				{
					setSuggestion("Name");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Name");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Name");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:FourthBlockName", FourthBlockName);
			FourthBlockName.setMaxStringLength(32767);
			this.children.add(this.FourthBlockName);
			this.addButton(new Button(this.guiLeft + 154, this.guiTop + 186, 40, 20, "Call4", e -> {
				MagicWitchcraftMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(3, x, y, z));
				handleButtonAction(entity, 3, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 155, this.guiTop + 22, 40, 20, "Save", e -> {
				MagicWitchcraftMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(4, x, y, z));
				handleButtonAction(entity, 4, x, y, z);
			}));
		}
	}

	public static class ButtonPressedMessage {
		int buttonID, x, y, z;
		public ButtonPressedMessage(PacketBuffer buffer) {
			this.buttonID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
		}

		public ButtonPressedMessage(int buttonID, int x, int y, int z) {
			this.buttonID = buttonID;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public static void buffer(ButtonPressedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.buttonID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
		}

		public static void handler(ButtonPressedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}

	public static class GUISlotChangedMessage {
		int slotID, x, y, z, changeType, meta;
		public GUISlotChangedMessage(int slotID, int x, int y, int z, int changeType, int meta) {
			this.slotID = slotID;
			this.x = x;
			this.y = y;
			this.z = z;
			this.changeType = changeType;
			this.meta = meta;
		}

		public GUISlotChangedMessage(PacketBuffer buffer) {
			this.slotID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
			this.changeType = buffer.readInt();
			this.meta = buffer.readInt();
		}

		public static void buffer(GUISlotChangedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.slotID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
			buffer.writeInt(message.changeType);
			buffer.writeInt(message.meta);
		}

		public static void handler(GUISlotChangedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}
	private static void handleButtonAction(PlayerEntity entity, int buttonID, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				RightClickerCallOnButtonClickedProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 1) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				RightClickerCall2OnButtonClickedProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 2) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				RightClickerCall3OnButtonClickedProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 3) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				RightClickerCall4OnButtonClickedProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 4) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("guistate", guistate);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				RightClickerSaveOnButtonClickedProcedure.executeProcedure($_dependencies);
			}
		}
	}

	private static void handleSlotAction(PlayerEntity entity, int slotID, int changeType, int meta, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}
}
