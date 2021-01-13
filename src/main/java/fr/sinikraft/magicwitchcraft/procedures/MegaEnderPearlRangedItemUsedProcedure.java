package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.item.EnergyCrystalEmptyItem;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
<<<<<<< HEAD
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class MegaEnderPearlRangedItemUsedProcedure extends MagicWitchcraftModElements.ModElement {
	public MegaEnderPearlRangedItemUsedProcedure(MagicWitchcraftModElements instance) {
		super(instance, 188);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency entity for procedure MegaEnderPearlRangedItemUsed!");
=======

@MagicWitchcraftModElements.ModElement.Tag
public class MegaEnderPearlRangedItemUsedProcedure extends MagicWitchcraftModElements.ModElement {
	public MegaEnderPearlRangedItemUsedProcedure(MagicWitchcraftModElements instance) {
		super(instance, 188);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MegaEnderPearlRangedItemUsed!");
>>>>>>> branch '1.15.2-master' of https://github.com/SiniKraft/MagicWitchcraft
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(EnergyCrystalEmptyItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
	}
}
