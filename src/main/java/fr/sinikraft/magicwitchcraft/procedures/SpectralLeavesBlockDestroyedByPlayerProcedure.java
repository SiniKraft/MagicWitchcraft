package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.block.SpectralLeavesBlock;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class SpectralLeavesBlockDestroyedByPlayerProcedure extends MagicWitchcraftModElements.ModElement {
	public SpectralLeavesBlockDestroyedByPlayerProcedure(MagicWitchcraftModElements instance) {
		super(instance, 222);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency entity for procedure SpectralLeavesBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency x for procedure SpectralLeavesBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency y for procedure SpectralLeavesBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency z for procedure SpectralLeavesBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency world for procedure SpectralLeavesBlockDestroyedByPlayer!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Items.SHEARS, (int) (1)).getItem())) {
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(SpectralLeavesBlock.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		}
	}
}
