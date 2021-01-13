package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
<<<<<<< HEAD
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class MagicalBushReadyMobplayerCollidesWithPlantProcedure extends MagicWitchcraftModElements.ModElement {
	public MagicalBushReadyMobplayerCollidesWithPlantProcedure(MagicWitchcraftModElements instance) {
		super(instance, 156);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency entity for procedure MagicalBushReadyMobplayerCollidesWithPlant!");
=======

@MagicWitchcraftModElements.ModElement.Tag
public class MagicalBushReadyMobplayerCollidesWithPlantProcedure extends MagicWitchcraftModElements.ModElement {
	public MagicalBushReadyMobplayerCollidesWithPlantProcedure(MagicWitchcraftModElements instance) {
		super(instance, 156);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MagicalBushReadyMobplayerCollidesWithPlant!");
>>>>>>> branch '1.15.2-master' of https://github.com/SiniKraft/MagicWitchcraft
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((Math.random() < 0.01) && (!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false)))) {
			entity.attackEntityFrom(DamageSource.CACTUS, (float) 2);
		}
	}
}
