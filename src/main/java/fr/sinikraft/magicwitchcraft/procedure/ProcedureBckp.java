package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.WorldServer;
import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.Block;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftVariables;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureBckp extends ElementsMagicWitchcraft.ModElement {
	public ProcedureBckp(ElementsMagicWitchcraft instance) {
		super(instance, 186);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Bckp!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Bckp!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if ((!(MagicWitchcraftVariables.WorldVariables.get(world).ismagicaldimensiongenerated))) {
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity)
						.sendStatusMessage(new TextComponentString("\u00A7eGenerating magical dimension... Please wait and not leave."), (true));
			}
			if (!world.isRemote) {
				Template template = ((WorldServer) world).getStructureTemplateManager().getTemplate(world.getMinecraftServer(),
						new ResourceLocation("magic_witchcraft", "magicaldimension"));
				if (template != null) {
					BlockPos spawnTo = new BlockPos((int) 0, (int) 100, (int) 0);
					IBlockState iblockstate = world.getBlockState(spawnTo);
					world.notifyBlockUpdate(spawnTo, iblockstate, iblockstate, 3);
					template.addBlocksToWorldChunk(world, spawnTo, new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE)
							.setChunk((ChunkPos) null).setReplacedBlock((Block) null).setIgnoreStructureBlock(false).setIgnoreEntities(false));
				}
			}
			world.setSpawnPoint(new BlockPos((int) 43, (int) 102, (int) 94));
			entity.setPositionAndUpdate(43, 102, 94);
			MagicWitchcraftVariables.WorldVariables.get(world).ismagicaldimensiongenerated = (boolean) (true);
			MagicWitchcraftVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof EntityPlayer && !world.isRemote) {
				((EntityPlayer) entity).sendStatusMessage(new TextComponentString("\u00A7aMagical dimension succesfully generated !"), (true));
			}
		}
	}
}
