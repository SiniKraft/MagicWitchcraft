package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.item.ItemEnergyCrystalEmpty;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMegaEnderPearlRangedItemUsed extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMegaEnderPearlRangedItemUsed(ElementsMagicWitchcraft instance) {
		super(instance, 188);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MegaEnderPearlRangedItemUsed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))) {
			if (entity instanceof EntityPlayer) {
				ItemStack _setstack = new ItemStack(ItemEnergyCrystalEmpty.block, (int) (1));
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
			}
		}
	}
}
