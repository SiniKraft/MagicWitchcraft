
package fr.sinikraft.magicwitchcraft.creativetab;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import fr.sinikraft.magicwitchcraft.item.ItemMagicalOrb;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class TabMagicWitchCraft extends ElementsMagicWitchcraft.ModElement {
	public TabMagicWitchCraft(ElementsMagicWitchcraft instance) {
		super(instance, 84);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tabmagicwitchcraft") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(ItemMagicalOrb.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static CreativeTabs tab;
}
