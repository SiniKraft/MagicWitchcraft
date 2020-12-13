
package fr.sinikraft.magicwitchcraft.util;

import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import fr.sinikraft.magicwitchcraft.item.ItemMagicalBottleFilled1;
import fr.sinikraft.magicwitchcraft.item.ItemMagicalBottleFilled0;
import fr.sinikraft.magicwitchcraft.item.ItemMagicalBottleEmpty;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class OreDictMagicalBottle extends ElementsMagicWitchcraft.ModElement {
	public OreDictMagicalBottle(ElementsMagicWitchcraft instance) {
		super(instance, 299);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		OreDictionary.registerOre("magicalbottle", new ItemStack(ItemMagicalBottleEmpty.block, (int) (1)));
		OreDictionary.registerOre("magicalbottle", new ItemStack(ItemMagicalBottleFilled0.block, (int) (1)));
		OreDictionary.registerOre("magicalbottle", new ItemStack(ItemMagicalBottleFilled1.block, (int) (1)));
	}
}
