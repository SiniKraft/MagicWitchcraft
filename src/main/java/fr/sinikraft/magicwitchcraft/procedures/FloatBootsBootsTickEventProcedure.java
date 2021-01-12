package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;
import java.util.Collections;

import fr.sinikraft.magicwitchcraft.block.TrollWaterBlock;
import fr.sinikraft.magicwitchcraft.block.MagicalLavaBlock;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class FloatBootsBootsTickEventProcedure extends MagicWitchcraftModElements.ModElement {
	public FloatBootsBootsTickEventProcedure(MagicWitchcraftModElements instance) {
		super(instance, 194);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure FloatBootsBootsTickEvent!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure FloatBootsBootsTickEvent!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure FloatBootsBootsTickEvent!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure FloatBootsBootsTickEvent!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure FloatBootsBootsTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.WATER.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.WATER.getDefaultState().getBlock()))
				|| ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock()))
						|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MagicalLavaBlock.block.getDefaultState()
								.getBlock())
								|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == TrollWaterBlock.block
										.getDefaultState().getBlock()))))) {
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate((entity.getPosX()), ((entity.getPosY()) + 0.3), (entity.getPosZ()));
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation((entity.getPosX()), ((entity.getPosY()) + 0.3), (entity.getPosZ()),
							_ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
				}
			}
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, (int) 20, (int) 0, (false), (false)));
			entity.fallDistance = (float) (0);
		}
	}
}
