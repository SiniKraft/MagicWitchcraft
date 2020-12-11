package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.potion.PotionEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.init.MobEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.item.ItemSaver;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureStickyStoneEntityWalksOnTheBlock extends ElementsMagicWitchcraft.ModElement {
	public ProcedureStickyStoneEntityWalksOnTheBlock(ElementsMagicWitchcraft instance) {
		super(instance, 244);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure StickyStoneEntityWalksOnTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))) {
			if ((!(((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY)
					.getItem() == new ItemStack(ItemSaver.legs, (int) (1)).getItem()))) {
				if (entity instanceof EntityLivingBase)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) 1, (int) 254, (true), (false)));
			}
		}
	}
}
