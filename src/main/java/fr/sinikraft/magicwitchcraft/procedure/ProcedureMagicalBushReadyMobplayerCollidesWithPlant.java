package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMagicalBushReadyMobplayerCollidesWithPlant extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMagicalBushReadyMobplayerCollidesWithPlant(ElementsMagicWitchcraft instance) {
		super(instance, 141);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MagicalBushReadyMobplayerCollidesWithPlant!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((Math.random() < 0.001)) {
			if ((!(entity instanceof EntityZombie))) {
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, (int) 1, (int) 0, (false), (false)));
			} else {
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, (int) 1, (int) 0, (false), (false)));
			}
		}
	}
}
