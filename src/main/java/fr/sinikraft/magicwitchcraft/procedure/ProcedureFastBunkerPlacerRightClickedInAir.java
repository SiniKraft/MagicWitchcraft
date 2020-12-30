package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.item.ItemBunkerOrb;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureFastBunkerPlacerRightClickedInAir extends ElementsMagicWitchcraft.ModElement {
	public ProcedureFastBunkerPlacerRightClickedInAir(ElementsMagicWitchcraft instance) {
		super(instance, 277);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure FastBunkerPlacerRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure FastBunkerPlacerRightClickedInAir!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure FastBunkerPlacerRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure FastBunkerPlacerRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure FastBunkerPlacerRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(ItemBunkerOrb.block, (int) (1)).getItem())) {
			if ((!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))) {
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemBunkerOrb.block, (int) (1)).getItem(), -1, (int) 1, null);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) z), Blocks.OBSIDIAN.getDefaultState(), 3);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), Blocks.OBSIDIAN.getDefaultState(), 3);
			}
			if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), Blocks.OBSIDIAN.getDefaultState(), 3);
			}
			if (((world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z), Blocks.OBSIDIAN.getDefaultState(), 3);
			}
			if (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), Blocks.OBSIDIAN.getDefaultState(), 3);
			}
			if (((world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z), Blocks.OBSIDIAN.getDefaultState(), 3);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), Blocks.OBSIDIAN.getDefaultState(), 3);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)), Blocks.OBSIDIAN.getDefaultState(), 3);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), Blocks.OBSIDIAN.getDefaultState(), 3);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)), Blocks.OBSIDIAN.getDefaultState(), 3);
			}
			entity.setPositionAndUpdate(x, y, z);
		}
	}
}
