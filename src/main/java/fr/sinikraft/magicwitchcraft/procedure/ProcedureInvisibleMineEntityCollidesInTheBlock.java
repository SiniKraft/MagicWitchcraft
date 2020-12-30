package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureInvisibleMineEntityCollidesInTheBlock extends ElementsMagicWitchcraft.ModElement {
	public ProcedureInvisibleMineEntityCollidesInTheBlock(ElementsMagicWitchcraft instance) {
		super(instance, 201);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure InvisibleMineEntityCollidesInTheBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure InvisibleMineEntityCollidesInTheBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure InvisibleMineEntityCollidesInTheBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure InvisibleMineEntityCollidesInTheBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure InvisibleMineEntityCollidesInTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))) {
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
			if (!world.isRemote) {
				world.createExplosion(null, (int) x, (int) y, (int) z, (float) 4, true);
			}
		}
	}
}
