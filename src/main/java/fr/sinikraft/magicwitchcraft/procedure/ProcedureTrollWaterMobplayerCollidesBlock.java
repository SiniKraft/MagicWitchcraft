package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.item.ItemFloatBoots;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureTrollWaterMobplayerCollidesBlock extends ElementsMagicWitchcraft.ModElement {
	public ProcedureTrollWaterMobplayerCollidesBlock(ElementsMagicWitchcraft instance) {
		super(instance, 178);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure TrollWaterMobplayerCollidesBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((Math.random() < 0.1) && (!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false)))
				&& (!(((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(0) : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemFloatBoots.boots, (int) (1)).getItem())))) {
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity)
						.setHealth((float) (((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHealth() : -1) - 0.5));
		}
	}
}
