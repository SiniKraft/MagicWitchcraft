package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import fr.sinikraft.magicwitchcraft.block.BlockPurpleObsidian;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedurePurpleObsidianBlockDestroyedByPlayer extends ElementsMagicWitchcraft.ModElement {
	public ProcedurePurpleObsidianBlockDestroyedByPlayer(ElementsMagicWitchcraft instance) {
		super(instance, 251);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PurpleObsidianBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PurpleObsidianBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PurpleObsidianBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PurpleObsidianBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PurpleObsidianBlockDestroyedByPlayer!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))) {
			if ((((EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH,
					((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY))) == 1)
					&& (((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()
							.canHarvestBlock(Blocks.OBSIDIAN.getDefaultState())))) {
				if (!world.isRemote) {
					EntityItem entityToSpawn = new EntityItem(world, x, y, z, new ItemStack(BlockPurpleObsidian.block, (int) (1)));
					entityToSpawn.setPickupDelay(10);
					world.spawnEntity(entityToSpawn);
				}
			} else {
				if (!world.isRemote) {
					world.createExplosion(null, (int) x, (int) y, (int) z, (float) 8, true);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.OBSIDIAN.getDefaultState(), 3);
			}
		}
	}
}
