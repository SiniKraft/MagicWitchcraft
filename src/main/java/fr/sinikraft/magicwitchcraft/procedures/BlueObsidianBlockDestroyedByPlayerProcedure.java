package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.block.Blocks;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.block.BlueObsidianBlock;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class BlueObsidianBlockDestroyedByPlayerProcedure extends MagicWitchcraftModElements.ModElement {
	public BlueObsidianBlockDestroyedByPlayerProcedure(MagicWitchcraftModElements instance) {
		super(instance, 249);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure BlueObsidianBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure BlueObsidianBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure BlueObsidianBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure BlueObsidianBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure BlueObsidianBlockDestroyedByPlayer!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
			if ((((EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH,
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))) == 1)
					&& (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()
							.canHarvestBlock(Blocks.OBSIDIAN.getDefaultState())))) {
				if (!world.getWorld().isRemote) {
					ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(BlueObsidianBlock.block, (int) (1)));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
			} else {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.OBSIDIAN.getDefaultState(), 3);
			}
		}
	}
}
