package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import net.minecraft.init.Blocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.block.BlockTrollWater;
import fr.sinikraft.magicwitchcraft.block.BlockMagicalLava;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureFloatBootsBootsTickEvent extends ElementsMagicWitchcraft.ModElement {
	public ProcedureFloatBootsBootsTickEvent(ElementsMagicWitchcraft instance) {
		super(instance, 194);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure FloatBootsBootsTickEvent!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure FloatBootsBootsTickEvent!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure FloatBootsBootsTickEvent!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure FloatBootsBootsTickEvent!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure FloatBootsBootsTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.WATER.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.FLOWING_WATER.getDefaultState().getBlock()))
				|| ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.FLOWING_LAVA.getDefaultState()
								.getBlock()))
						|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockMagicalLava.block.getDefaultState()
								.getBlock())
								|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockTrollWater.block
										.getDefaultState().getBlock()))))) {
			entity.setPositionAndUpdate((entity.posX), ((entity.posY) + 0.3), (entity.posZ));
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, (int) 20, (int) 0, (false), (false)));
			entity.fallDistance = (float) (0);
		}
	}
}
