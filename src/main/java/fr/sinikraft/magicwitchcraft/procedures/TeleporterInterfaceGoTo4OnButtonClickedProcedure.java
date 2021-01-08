package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import java.util.Map;
import java.util.Collections;

import fr.sinikraft.magicwitchcraft.block.TeleporterBeaconBlock;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModVariables;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class TeleporterInterfaceGoTo4OnButtonClickedProcedure extends MagicWitchcraftModElements.ModElement {
	public TeleporterInterfaceGoTo4OnButtonClickedProcedure(MagicWitchcraftModElements instance) {
		super(instance, 347);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure TeleporterInterfaceGoTo4OnButtonClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure TeleporterInterfaceGoTo4OnButtonClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure TeleporterInterfaceGoTo4OnButtonClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure TeleporterInterfaceGoTo4OnButtonClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure TeleporterInterfaceGoTo4OnButtonClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "EnergyStored")) >= 100)) {
			if (((world.getBlockState(new BlockPos((int) (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosX),
					(int) (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosY),
					(int) (MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosZ))))
							.getBlock() == TeleporterBeaconBlock.block.getDefaultState().getBlock())) {
				if (!world.getWorld().isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("EnergyStored", ((new Object() {
							public double getValue(BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(new BlockPos((int) x, (int) y, (int) z), "EnergyStored")) - 100));
					world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).closeScreen();
				{
					Entity _ent = entity;
					_ent.setPositionAndUpdate(((MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosX) + 0.5),
							((MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosY) + 1),
							((MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosZ) + 0.5));
					if (_ent instanceof ServerPlayerEntity) {
						((ServerPlayerEntity) _ent).connection.setPlayerLocation(
								((MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosX) + 0.5),
								((MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosY) + 1),
								((MagicWitchcraftModVariables.WorldVariables.get(world).TeleporterPublicN4PosZ) + 0.5), _ent.rotationYaw,
								_ent.rotationPitch, Collections.emptySet());
					}
				}
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7aTeleported !"), (false));
				}
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cThere isn't a teleporter beacon here !"), (false));
				}
			}
		} else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cNot enough energy !"), (false));
			}
		}
	}
}
