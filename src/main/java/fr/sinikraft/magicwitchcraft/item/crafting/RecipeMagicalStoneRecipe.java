
package fr.sinikraft.magicwitchcraft.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import fr.sinikraft.magicwitchcraft.block.BlockMagicalStone;
import fr.sinikraft.magicwitchcraft.block.BlockMagicalCobbleStone;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class RecipeMagicalStoneRecipe extends ElementsMagicWitchcraft.ModElement {
	public RecipeMagicalStoneRecipe(ElementsMagicWitchcraft instance) {
		super(instance, 211);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockMagicalCobbleStone.block, (int) (1)), new ItemStack(BlockMagicalStone.block, (int) (1)), 0F);
	}
}
