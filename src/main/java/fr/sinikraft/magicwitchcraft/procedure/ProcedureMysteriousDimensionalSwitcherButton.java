package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraftforge.common.DimensionManager;

import net.minecraft.world.WorldServer;
import net.minecraft.world.Teleporter;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Iterator;

import fr.sinikraft.magicwitchcraft.world.WorldMysteriousDimension;
import fr.sinikraft.magicwitchcraft.item.ItemDimensionalOrb;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMysteriousDimensionalSwitcherButton extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMysteriousDimensionalSwitcherButton(ElementsMagicWitchcraft instance) {
		super(instance, 92);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MysteriousDimensionalSwitcherButton!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemDimensionalOrb.block, (int) (1)).getItem(), -1, (int) 1, null);
		if (!entity.world.isRemote && !entity.isRiding() && !entity.isBeingRidden() && entity instanceof EntityPlayerMP) {
			int dimensionID = WorldMysteriousDimension.DIMID;
			class TeleporterDirect extends Teleporter {
				public TeleporterDirect(WorldServer worldserver) {
					super(worldserver);
				}

				@Override
				public void placeInPortal(Entity entity, float yawrotation) {
				}

				@Override
				public boolean placeInExistingPortal(Entity entity, float yawrotation) {
					return true;
				}

				@Override
				public boolean makePortal(Entity entity) {
					return true;
				}
			}
			EntityPlayerMP _player = (EntityPlayerMP) entity;
			_player.mcServer.getPlayerList().transferPlayerToDimension(_player, dimensionID, new TeleporterDirect(_player.getServerWorld()));
			_player.connection.setPlayerLocation(DimensionManager.getWorld(dimensionID).getSpawnPoint().getX(),
					DimensionManager.getWorld(dimensionID).getSpawnPoint().getY() + 1, DimensionManager.getWorld(dimensionID).getSpawnPoint().getZ(),
					_player.rotationYaw, _player.rotationPitch);
		}
		if (entity instanceof EntityPlayerMP) {
			Advancement _adv = ((MinecraftServer) ((EntityPlayerMP) entity).mcServer).getAdvancementManager()
					.getAdvancement(new ResourceLocation("magic_witchcraft:dimensionaltraveler"));
			AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
			if (!_ap.isDone()) {
				Iterator _iterator = _ap.getRemaningCriteria().iterator();
				while (_iterator.hasNext()) {
					String _criterion = (String) _iterator.next();
					((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
				}
			}
		}
	}
}
