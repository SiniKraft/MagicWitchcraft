package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.item.ItemChunkAnalyser;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureChunkAnalyserRightClickedInAir extends ElementsMagicWitchcraft.ModElement {
	public ProcedureChunkAnalyserRightClickedInAir(ElementsMagicWitchcraft instance) {
		super(instance, 113);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ChunkAnalyserRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure ChunkAnalyserRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure ChunkAnalyserRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure ChunkAnalyserRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double varX = 0;
		double varY = 0;
		double varZ = 0;
		if ((!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))) {
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemChunkAnalyser.block, (int) (1)).getItem(), -1, (int) 1, null);
		}
		if (entity instanceof EntityPlayer && !world.isRemote) {
			((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u00A7aStarting \u00A7achunk \u00A7aanalyser..."), (true));
		}
		if (entity instanceof EntityPlayer && !world.isRemote) {
			((EntityPlayer) entity)
					.sendStatusMessage(new TextComponentString("\u00A7a[ChunkAnalyser]: ----------------Start-of-analysis----------------"), (false));
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
									.getStateFromMeta(0).getBlock()))
							|| (((world.getBlockState(new BlockPos((int) (varX), (int) (varY), (int) (varZ)))).getBlock() == Blocks.DIRT
									.getStateFromMeta(0).getBlock())
									|| ((world.getBlockState(new BlockPos((int) (varX), (int) (varY), (int) (varZ)))).getBlock() == Blocks.BEDROCK
											.getDefaultState().getBlock()))))) {
						if (entity instanceof EntityPlayer && !world.isRemote) {
							((EntityPlayer) entity)
									.sendStatusMessage(
											new TextComponentString((("\u00A7e[ChunkAnalyser]: ") + ""
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
		if (entity instanceof EntityPlayer && !world.isRemote) {
			((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u00A7aDone \u00A7a! \u00A7aSee \u00A7ayour \u00A7alogs."), (true));
		}
		if (entity instanceof EntityPlayer && !world.isRemote) {
			((EntityPlayer) entity)
					.sendStatusMessage(new TextComponentString("\u00A7a[ChunkAnalyser]: ----------------End-of-analysis----------------"), (false));
		}
	}
}
