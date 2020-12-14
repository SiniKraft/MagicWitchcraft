package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMagicalBushReadyMobplayerCollidesWithPlant extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMagicalBushReadyMobplayerCollidesWithPlant(ElementsMagicWitchcraft instance) {
		super(instance, 156);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MagicalBushReadyMobplayerCollidesWithPlant!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((Math.random() < 0.01) && (!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false)))) {
			entity.attackEntityFrom(DamageSource.CACTUS, (float) 2);
		}
	}
}
