package fr.sinikraft.magicwitchcraft;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.client.Minecraft;

public class MagicWitchcraftVariables {
	public static String Version = "1.12.2";
	public static class MapVariables extends WorldSavedData {
		public static final String DATA_NAME = "magic_witchcraft_mapvars";
		public MapVariables() {
			super(DATA_NAME);
		}

		public MapVariables(String s) {
			super(s);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbt) {
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
			return nbt;
		}

		public void syncData(World world) {
			this.markDirty();
			if (world.isRemote) {
				MagicWitchcraft.PACKET_HANDLER.sendToServer(new WorldSavedDataSyncMessage(0, this));
			} else {
				MagicWitchcraft.PACKET_HANDLER.sendToAll(new WorldSavedDataSyncMessage(0, this));
			}
		}

		public static MapVariables get(World world) {
			MapVariables instance = (MapVariables) world.getMapStorage().getOrLoadData(MapVariables.class, DATA_NAME);
			if (instance == null) {
				instance = new MapVariables();
				world.getMapStorage().setData(DATA_NAME, instance);
			}
			return instance;
		}
	}

	public static class WorldVariables extends WorldSavedData {
		public static final String DATA_NAME = "magic_witchcraft_worldvars";
		public double TeleporterPublicN1PosX = 0;
		public double TeleporterPublicN1PosY = 0;
		public double TeleporterPublicN1PosZ = 0;
		public String TeleporterPublicN1Name = "Not defined !";
		public double TeleporterPublicN2PosX = 0;
		public double TeleporterPublicN2PosY = 0;
		public double TeleporterPublicN2PosZ = 0;
		public String TeleporterPublicN2Name = "Not defined !";
		public double TeleporterPublicN3PosX = 0;
		public double TeleporterPublicN3PosY = 0;
		public double TeleporterPublicN3PosZ = 0;
		public String TeleporterPublicN3Name = "Not defined !";
		public double TeleporterPublicN4PosX = 0;
		public double TeleporterPublicN4PosY = 0;
		public double TeleporterPublicN4PosZ = 0;
		public String TeleporterPublicN4Name = "Not defined !";
		public double TeleporterPublicN5PosX = 0;
		public double TeleporterPublicN5PosY = 0;
		public double TeleporterPublicN5PosZ = 0;
		public String TeleporterPublicN5Name = "Not defined !";
		public double TeleporterPublicN6PosX = 0;
		public double TeleporterPublicN6PosY = 0;
		public double TeleporterPublicN6PosZ = 0;
		public String TeleporterPublicN6Name = "Not defined !";
		public WorldVariables() {
			super(DATA_NAME);
		}

		public WorldVariables(String s) {
			super(s);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbt) {
			TeleporterPublicN1PosX = nbt.getDouble("TeleporterPublicN1PosX");
			TeleporterPublicN1PosY = nbt.getDouble("TeleporterPublicN1PosY");
			TeleporterPublicN1PosZ = nbt.getDouble("TeleporterPublicN1PosZ");
			TeleporterPublicN1Name = nbt.getString("TeleporterPublicN1Name");
			TeleporterPublicN2PosX = nbt.getDouble("TeleporterPublicN2PosX");
			TeleporterPublicN2PosY = nbt.getDouble("TeleporterPublicN2PosY");
			TeleporterPublicN2PosZ = nbt.getDouble("TeleporterPublicN2PosZ");
			TeleporterPublicN2Name = nbt.getString("TeleporterPublicN2Name");
			TeleporterPublicN3PosX = nbt.getDouble("TeleporterPublicN3PosX");
			TeleporterPublicN3PosY = nbt.getDouble("TeleporterPublicN3PosY");
			TeleporterPublicN3PosZ = nbt.getDouble("TeleporterPublicN3PosZ");
			TeleporterPublicN3Name = nbt.getString("TeleporterPublicN3Name");
			TeleporterPublicN4PosX = nbt.getDouble("TeleporterPublicN4PosX");
			TeleporterPublicN4PosY = nbt.getDouble("TeleporterPublicN4PosY");
			TeleporterPublicN4PosZ = nbt.getDouble("TeleporterPublicN4PosZ");
			TeleporterPublicN4Name = nbt.getString("TeleporterPublicN4Name");
			TeleporterPublicN5PosX = nbt.getDouble("TeleporterPublicN5PosX");
			TeleporterPublicN5PosY = nbt.getDouble("TeleporterPublicN5PosY");
			TeleporterPublicN5PosZ = nbt.getDouble("TeleporterPublicN5PosZ");
			TeleporterPublicN5Name = nbt.getString("TeleporterPublicN5Name");
			TeleporterPublicN6PosX = nbt.getDouble("TeleporterPublicN6PosX");
			TeleporterPublicN6PosY = nbt.getDouble("TeleporterPublicN6PosY");
			TeleporterPublicN6PosZ = nbt.getDouble("TeleporterPublicN6PosZ");
			TeleporterPublicN6Name = nbt.getString("TeleporterPublicN6Name");
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
			nbt.setDouble("TeleporterPublicN1PosX", TeleporterPublicN1PosX);
			nbt.setDouble("TeleporterPublicN1PosY", TeleporterPublicN1PosY);
			nbt.setDouble("TeleporterPublicN1PosZ", TeleporterPublicN1PosZ);
			nbt.setString("TeleporterPublicN1Name", TeleporterPublicN1Name);
			nbt.setDouble("TeleporterPublicN2PosX", TeleporterPublicN2PosX);
			nbt.setDouble("TeleporterPublicN2PosY", TeleporterPublicN2PosY);
			nbt.setDouble("TeleporterPublicN2PosZ", TeleporterPublicN2PosZ);
			nbt.setString("TeleporterPublicN2Name", TeleporterPublicN2Name);
			nbt.setDouble("TeleporterPublicN3PosX", TeleporterPublicN3PosX);
			nbt.setDouble("TeleporterPublicN3PosY", TeleporterPublicN3PosY);
			nbt.setDouble("TeleporterPublicN3PosZ", TeleporterPublicN3PosZ);
			nbt.setString("TeleporterPublicN3Name", TeleporterPublicN3Name);
			nbt.setDouble("TeleporterPublicN4PosX", TeleporterPublicN4PosX);
			nbt.setDouble("TeleporterPublicN4PosY", TeleporterPublicN4PosY);
			nbt.setDouble("TeleporterPublicN4PosZ", TeleporterPublicN4PosZ);
			nbt.setString("TeleporterPublicN4Name", TeleporterPublicN4Name);
			nbt.setDouble("TeleporterPublicN5PosX", TeleporterPublicN5PosX);
			nbt.setDouble("TeleporterPublicN5PosY", TeleporterPublicN5PosY);
			nbt.setDouble("TeleporterPublicN5PosZ", TeleporterPublicN5PosZ);
			nbt.setString("TeleporterPublicN5Name", TeleporterPublicN5Name);
			nbt.setDouble("TeleporterPublicN6PosX", TeleporterPublicN6PosX);
			nbt.setDouble("TeleporterPublicN6PosY", TeleporterPublicN6PosY);
			nbt.setDouble("TeleporterPublicN6PosZ", TeleporterPublicN6PosZ);
			nbt.setString("TeleporterPublicN6Name", TeleporterPublicN6Name);
			return nbt;
		}

		public void syncData(World world) {
			this.markDirty();
			if (world.isRemote) {
				MagicWitchcraft.PACKET_HANDLER.sendToServer(new WorldSavedDataSyncMessage(1, this));
			} else {
				MagicWitchcraft.PACKET_HANDLER.sendToDimension(new WorldSavedDataSyncMessage(1, this), world.provider.getDimension());
			}
		}

		public static WorldVariables get(World world) {
			WorldVariables instance = (WorldVariables) world.getPerWorldStorage().getOrLoadData(WorldVariables.class, DATA_NAME);
			if (instance == null) {
				instance = new WorldVariables();
				world.getPerWorldStorage().setData(DATA_NAME, instance);
			}
			return instance;
		}
	}

	public static class WorldSavedDataSyncMessageHandler implements IMessageHandler<WorldSavedDataSyncMessage, IMessage> {
		@Override
		public IMessage onMessage(WorldSavedDataSyncMessage message, MessageContext context) {
			if (context.side == Side.SERVER)
				context.getServerHandler().player.getServerWorld()
						.addScheduledTask(() -> syncData(message, context, context.getServerHandler().player.world));
			else
				Minecraft.getMinecraft().addScheduledTask(() -> syncData(message, context, Minecraft.getMinecraft().player.world));
			return null;
		}

		private void syncData(WorldSavedDataSyncMessage message, MessageContext context, World world) {
			if (context.side == Side.SERVER) {
				message.data.markDirty();
				if (message.type == 0)
					MagicWitchcraft.PACKET_HANDLER.sendToAll(message);
				else
					MagicWitchcraft.PACKET_HANDLER.sendToDimension(message, world.provider.getDimension());
			}
			if (message.type == 0) {
				world.getMapStorage().setData(MapVariables.DATA_NAME, message.data);
			} else {
				world.getPerWorldStorage().setData(WorldVariables.DATA_NAME, message.data);
			}
		}
	}

	public static class WorldSavedDataSyncMessage implements IMessage {
		public int type;
		public WorldSavedData data;
		public WorldSavedDataSyncMessage() {
		}

		public WorldSavedDataSyncMessage(int type, WorldSavedData data) {
			this.type = type;
			this.data = data;
		}

		@Override
		public void toBytes(io.netty.buffer.ByteBuf buf) {
			buf.writeInt(this.type);
			ByteBufUtils.writeTag(buf, this.data.writeToNBT(new NBTTagCompound()));
		}

		@Override
		public void fromBytes(io.netty.buffer.ByteBuf buf) {
			this.type = buf.readInt();
			if (this.type == 0)
				this.data = new MapVariables();
			else
				this.data = new WorldVariables();
			this.data.readFromNBT(ByteBufUtils.readTag(buf));
		}
	}
}
