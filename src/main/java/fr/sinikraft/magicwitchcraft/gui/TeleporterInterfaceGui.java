
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import fr.sinikraft.magicwitchcraft.procedures.TeleporterInterfaceGoTo6OnButtonClickedProcedure;
import fr.sinikraft.magicwitchcraft.procedures.TeleporterInterfaceGoTo5OnButtonClickedProcedure;
import fr.sinikraft.magicwitchcraft.procedures.TeleporterInterfaceGoTo4OnButtonClickedProcedure;
import fr.sinikraft.magicwitchcraft.procedures.TeleporterInterfaceGoTo3OnButtonClickedProcedure;
import fr.sinikraft.magicwitchcraft.procedures.TeleporterInterfaceGoTo2OnButtonClickedProcedure;
import fr.sinikraft.magicwitchcraft.procedures.TeleporterInterfaceGoTo1OnButtonClickedProcedure;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModVariables;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class TeleporterInterfaceGui extends MagicWitchcraftModElements.ModElement {
	public static HashMap guistate = new HashMap();
	private static ContainerType<GuiContainerMod> containerType = null;
	public TeleporterInterfaceGui(MagicWitchcraftModElements instance) {
		super(instance, 337);
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
		event.getRegistry().register(containerType.setRegistryName("teleporterinterface"));
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
		public GuiWindow(GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
			super(container, inventory, text);
			this.world = container.world;
			this.x = container.x;
			this.y = container.y;
			this.z = container.z;
			this.entity = container.entity;
			this.xSize = 256;
			this.ySize = 213;
		}
		private static final ResourceLocation texture = new ResourceLocation("magic_witchcraft:textures/teleporterinterface.png");
		@Override
		public void render(int mouseX, int mouseY, float partialTicks) {
			this.renderBackground();
			super.render(mouseX, mouseY, partialTicks);
			this.renderHoveredToolTip(mouseX, mouseY);
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
		public void tick() {
			super.tick();
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
			this.font.drawString("Teleporter", 100, 7, -1);
			this.font.drawString("1. ", 10, 25, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN1Name) + "", 28, 25, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN1PosX) + "", 127, 25, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN1PosY) + "", 172, 25, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN1PosZ) + "", 208, 25, -1);
			this.font.drawString("2.", 10, 43, -1);
			this.font.drawString("3.", 10, 61, -1);
			this.font.drawString("4.", 10, 79, -1);
			this.font.drawString("5.", 10, 97, -1);
			this.font.drawString("6.", 10, 115, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN2Name) + "", 28, 43, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN2PosX) + "", 127, 43, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN2PosY) + "", 172, 43, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN2PosZ) + "", 208, 43, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN3Name) + "", 28, 61, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN3PosX) + "", 127, 61, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN3PosY) + "", 172, 61, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN3PosZ) + "", 208, 61, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4Name) + "", 28, 79, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosX) + "", 127, 79, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosY) + "", 172, 79, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosZ) + "", 208, 79, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN5Name) + "", 28, 97, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN5PosX) + "", 127, 97, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN5PosY) + "", 172, 97, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN5PosZ) + "", 208, 97, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN6Name) + "", 28, 115, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN6PosX) + "", 127, 115, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN6PosY) + "", 172, 115, -1);
			this.font.drawString("" + (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN6PosZ) + "", 208, 115, -1);
			this.font.drawString("Energy stored", 180, 142, -16738048);
			this.font.drawString("" + (new Object() {
				public double getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return 0;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "EnergyStored")) + "", 199, 169, -16711936);
			this.font.drawString("/ 1000 MER", 192, 178, -16711936);
		}

		@Override
		public boolean keyPressed(int key, int b, int c) {
			if (key == 256) {
				this.minecraft.player.closeScreen();
				return true;
			}
			return super.keyPressed(key, b, c);
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
			this.addButton(new Button(this.guiLeft + 19, this.guiTop + 142, 45, 20, "Go to 1", e -> {
				MagicWitchcraftMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(0, x, y, z));
				handleButtonAction(entity, 0, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 19, this.guiTop + 178, 45, 20, "Go to 2", e -> {
				MagicWitchcraftMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(1, x, y, z));
				handleButtonAction(entity, 1, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 73, this.guiTop + 142, 45, 20, "Go to 3", e -> {
				MagicWitchcraftMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(2, x, y, z));
				handleButtonAction(entity, 2, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 73, this.guiTop + 178, 45, 20, "Go to 4", e -> {
				MagicWitchcraftMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(3, x, y, z));
				handleButtonAction(entity, 3, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 127, this.guiTop + 142, 45, 20, "Go to 5", e -> {
				MagicWitchcraftMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(4, x, y, z));
				handleButtonAction(entity, 4, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 127, this.guiTop + 178, 45, 20, "Go to 6", e -> {
				MagicWitchcraftMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(5, x, y, z));
				handleButtonAction(entity, 5, x, y, z);
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
				TeleporterInterfaceGoTo1OnButtonClickedProcedure.executeProcedure($_dependencies);
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
				TeleporterInterfaceGoTo2OnButtonClickedProcedure.executeProcedure($_dependencies);
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
				TeleporterInterfaceGoTo3OnButtonClickedProcedure.executeProcedure($_dependencies);
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
				TeleporterInterfaceGoTo4OnButtonClickedProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 4) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				TeleporterInterfaceGoTo5OnButtonClickedProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 5) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				TeleporterInterfaceGoTo6OnButtonClickedProcedure.executeProcedure($_dependencies);
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
