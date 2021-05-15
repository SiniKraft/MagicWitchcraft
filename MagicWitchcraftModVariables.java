package fr.sinikraft.magicwitchcraft;

import org.lwjgl.Version;

import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.IServerWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.function.Supplier;

public class MagicWitchcraftModVariables {
	public MagicWitchcraftModVariables(MagicWitchcraftModElements elements) {
		elements.addNetworkMessage(WorldSavedDataSyncMessage.class, WorldSavedDataSyncMessage::buffer, WorldSavedDataSyncMessage::new,
				WorldSavedDataSyncMessage::handler);
	}
	public static String Version = "1.14.4";
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote()) {
			WorldSavedData mapdata = MapVariables.get(event.getPlayer().world);
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (mapdata != null)
				MagicWitchcraftMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(0, mapdata));
			if (worlddata != null)
				MagicWitchcraftMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote()) {
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (worlddata != null)
				MagicWitchcraftMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
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
		public void read(CompoundNBT nbt) {
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
		public CompoundNBT write(CompoundNBT nbt) {
			nbt.putDouble("TeleporterPublicN1PosX", TeleporterPublicN1PosX);
			nbt.putDouble("TeleporterPublicN1PosY", TeleporterPublicN1PosY);
			nbt.putDouble("TeleporterPublicN1PosZ", TeleporterPublicN1PosZ);
			nbt.putString("TeleporterPublicN1Name", TeleporterPublicN1Name);
			nbt.putDouble("TeleporterPublicN2PosX", TeleporterPublicN2PosX);
			nbt.putDouble("TeleporterPublicN2PosY", TeleporterPublicN2PosY);
			nbt.putDouble("TeleporterPublicN2PosZ", TeleporterPublicN2PosZ);
			nbt.putString("TeleporterPublicN2Name", TeleporterPublicN2Name);
			nbt.putDouble("TeleporterPublicN3PosX", TeleporterPublicN3PosX);
			nbt.putDouble("TeleporterPublicN3PosY", TeleporterPublicN3PosY);
			nbt.putDouble("TeleporterPublicN3PosZ", TeleporterPublicN3PosZ);
			nbt.putString("TeleporterPublicN3Name", TeleporterPublicN3Name);
			nbt.putDouble("TeleporterPublicN4PosX", TeleporterPublicN4PosX);
			nbt.putDouble("TeleporterPublicN4PosY", TeleporterPublicN4PosY);
			nbt.putDouble("TeleporterPublicN4PosZ", TeleporterPublicN4PosZ);
			nbt.putString("TeleporterPublicN4Name", TeleporterPublicN4Name);
			nbt.putDouble("TeleporterPublicN5PosX", TeleporterPublicN5PosX);
			nbt.putDouble("TeleporterPublicN5PosY", TeleporterPublicN5PosY);
			nbt.putDouble("TeleporterPublicN5PosZ", TeleporterPublicN5PosZ);
			nbt.putString("TeleporterPublicN5Name", TeleporterPublicN5Name);
			nbt.putDouble("TeleporterPublicN6PosX", TeleporterPublicN6PosX);
			nbt.putDouble("TeleporterPublicN6PosY", TeleporterPublicN6PosY);
			nbt.putDouble("TeleporterPublicN6PosZ", TeleporterPublicN6PosZ);
			nbt.putString("TeleporterPublicN6Name", TeleporterPublicN6Name);
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (world instanceof World && !world.isRemote())
				MagicWitchcraftMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(((World) world)::getDimensionKey),
						new WorldSavedDataSyncMessage(1, this));
		}
		static WorldVariables clientSide = new WorldVariables();
		public static WorldVariables get(IWorld world) {
			if (world instanceof ServerWorld) {
				return ((ServerWorld) world).getSavedData().getOrCreate(WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends WorldSavedData {
		public static final String DATA_NAME = "magic_witchcraft_mapvars";
		public MapVariables() {
			super(DATA_NAME);
		}

		public MapVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (world instanceof World && !world.isRemote())
				MagicWitchcraftMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new WorldSavedDataSyncMessage(0, this));
		}
		static MapVariables clientSide = new MapVariables();
		public static MapVariables get(IWorld world) {
			if (world instanceof IServerWorld) {
				return ((IServerWorld) world).getWorld().getServer().getWorld(World.OVERWORLD).getSavedData().getOrCreate(MapVariables::new,
						DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class WorldSavedDataSyncMessage {
		public int type;
		public WorldSavedData data;
		public WorldSavedDataSyncMessage(PacketBuffer buffer) {
			this.type = buffer.readInt();
			this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
			this.data.read(buffer.readCompoundTag());
		}

		public WorldSavedDataSyncMessage(int type, WorldSavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(WorldSavedDataSyncMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.type);
			buffer.writeCompoundTag(message.data.write(new CompoundNBT()));
		}

		public static void handler(WorldSavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
