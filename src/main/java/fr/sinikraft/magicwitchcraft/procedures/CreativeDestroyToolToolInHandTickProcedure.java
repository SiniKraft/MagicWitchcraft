package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
<<<<<<< HEAD
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class CreativeDestroyToolToolInHandTickProcedure extends MagicWitchcraftModElements.ModElement {
	public CreativeDestroyToolToolInHandTickProcedure(MagicWitchcraftModElements instance) {
		super(instance, 318);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency itemstack for procedure CreativeDestroyToolToolInHandTick!");
=======

@MagicWitchcraftModElements.ModElement.Tag
public class CreativeDestroyToolToolInHandTickProcedure extends MagicWitchcraftModElements.ModElement {
	public CreativeDestroyToolToolInHandTickProcedure(MagicWitchcraftModElements instance) {
		super(instance, 318);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure CreativeDestroyToolToolInHandTick!");
>>>>>>> branch '1.15.2-master' of https://github.com/SiniKraft/MagicWitchcraft
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((!((EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, (itemstack))) == 1))) {
			((itemstack)).addEnchantment(Enchantments.SILK_TOUCH, (int) 1);
		}
		if ((!((itemstack).getOrCreateTag().getBoolean("Unbreakable")))) {
			(itemstack).getOrCreateTag().putBoolean("Unbreakable", (true));
		}
	}
}
