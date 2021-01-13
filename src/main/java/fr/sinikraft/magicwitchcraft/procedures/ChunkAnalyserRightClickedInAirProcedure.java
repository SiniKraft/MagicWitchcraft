package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.item.ChunkAnalyserItem;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
<<<<<<< HEAD
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class ChunkAnalyserRightClickedInAirProcedure extends MagicWitchcraftModElements.ModElement {
	public ChunkAnalyserRightClickedInAirProcedure(MagicWitchcraftModElements instance) {
		super(instance, 128);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency entity for procedure ChunkAnalyserRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency x for procedure ChunkAnalyserRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency z for procedure ChunkAnalyserRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency world for procedure ChunkAnalyserRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double varX = 0;
		double varY = 0;
		double varZ = 0;
		if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(ChunkAnalyserItem.block, (int) (1));
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7aStarting \u00A7achunk \u00A7aanalyser..."), (true));
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity)
					.sendStatusMessage(new StringTextComponent("\u00A7a[ChunkAnalyser]: ----------------Start-of-analysis----------------"), (false));
		}
		varX = (double) x;
		varY = (double) 255;
		varZ = (double) z;
		for (int index0 = 0; index0 < (int) (254); index0++) {
			for (int index1 = 0; index1 < (int) (16); index1++) {
				for (int index2 = 0; index2 < (int) (16); index2++) {
					if ((!((((world.getBlockState(new BlockPos((int) (varX), (int) (varY), (int) (varZ)))).getBlock() == Blocks.AIR.getDefaultState()
							.getBlock())
							|| ((world.getBlockState(new BlockPos((int) (varX), (int) (varY), (int) (varZ)))).getBlock() == Blocks.STONE
									.getDefaultState().getBlock()))
							|| (((world.getBlockState(new BlockPos((int) (varX), (int) (varY), (int) (varZ)))).getBlock() == Blocks.DIRT
									.getDefaultState().getBlock())
									|| ((world.getBlockState(new BlockPos((int) (varX), (int) (varY), (int) (varZ)))).getBlock() == Blocks.BEDROCK
											.getDefaultState().getBlock()))))) {
						if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
							((PlayerEntity) entity)
									.sendStatusMessage(
											new StringTextComponent((("\u00A7e[ChunkAnalyser]: ") + ""
													+ ((world.getBlockState(new BlockPos((int) (varX), (int) (varY), (int) (varZ))))) + "" + (" at: ")
													+ "" + ((varX)) + "" + (" ") + "" + ((varY)) + "" + (" ") + "" + ((varZ)) + "" + ("."))),
											(false));
						}
					}
					varX = (double) ((varX) + 1);
				}
				varX = (double) ((varX) - 16);
				varZ = (double) ((varZ) + 1);
			}
			varY = (double) ((varY) - 1);
			varZ = (double) ((varZ) - 16);
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7aDone \u00A7a! \u00A7aSee \u00A7ayour \u00A7alogs."), (true));
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
=======

@MagicWitchcraftModElements.ModElement.Tag
public class ChunkAnalyserRightClickedInAirProcedure extends MagicWitchcraftModElements.ModElement {
	public ChunkAnalyserRightClickedInAirProcedure(MagicWitchcraftModElements instance) {
		super(instance, 128);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ChunkAnalyserRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure ChunkAnalyserRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure ChunkAnalyserRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure ChunkAnalyserRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double varX = 0;
		double varY = 0;
		double varZ = 0;
		if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(ChunkAnalyserItem.block, (int) (1));
				((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
			}
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7aStarting \u00A7achunk \u00A7aanalyser..."), (true));
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote) {
			((PlayerEntity) entity)
					.sendStatusMessage(new StringTextComponent("\u00A7a[ChunkAnalyser]: ----------------Start-of-analysis----------------"), (false));
		}
		varX = (double) x;
		varY = (double) 255;
		varZ = (double) z;
		for (int index0 = 0; index0 < (int) (254); index0++) {
			for (int index1 = 0; index1 < (int) (16); index1++) {
				for (int index2 = 0; index2 < (int) (16); index2++) {
					if ((!((((world.getBlockState(new BlockPos((int) (varX), (int) (varY), (int) (varZ)))).getBlock() == Blocks.AIR.getDefaultState()
							.getBlock())
							|| ((world.getBlockState(new BlockPos((int) (varX), (int) (varY), (int) (varZ)))).getBlock() == Blocks.STONE
									.getDefaultState().getBlock()))
							|| (((world.getBlockState(new BlockPos((int) (varX), (int) (varY), (int) (varZ)))).getBlock() == Blocks.DIRT
									.getDefaultState().getBlock())
									|| ((world.getBlockState(new BlockPos((int) (varX), (int) (varY), (int) (varZ)))).getBlock() == Blocks.BEDROCK
											.getDefaultState().getBlock()))))) {
						if (entity instanceof PlayerEntity && !entity.world.isRemote) {
							((PlayerEntity) entity)
									.sendStatusMessage(
											new StringTextComponent((("\u00A7e[ChunkAnalyser]: ") + ""
													+ ((world.getBlockState(new BlockPos((int) (varX), (int) (varY), (int) (varZ))))) + "" + (" at: ")
													+ "" + ((varX)) + "" + (" ") + "" + ((varY)) + "" + (" ") + "" + ((varZ)) + "" + ("."))),
											(false));
						}
					}
					varX = (double) ((varX) + 1);
				}
				varX = (double) ((varX) - 16);
				varZ = (double) ((varZ) + 1);
			}
			varY = (double) ((varY) - 1);
			varZ = (double) ((varZ) - 16);
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7aDone \u00A7a! \u00A7aSee \u00A7ayour \u00A7alogs."), (true));
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote) {
>>>>>>> branch '1.15.2-master' of https://github.com/SiniKraft/MagicWitchcraft
			((PlayerEntity) entity)
					.sendStatusMessage(new StringTextComponent("\u00A7a[ChunkAnalyser]: ----------------End-of-analysis----------------"), (false));
		}
	}
}
