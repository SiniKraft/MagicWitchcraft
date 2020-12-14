
package fr.sinikraft.magicwitchcraft.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import fr.sinikraft.magicwitchcraft.item.MagicalOrbItem;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class MagicWitchCraftItemGroup extends MagicWitchcraftModElements.ModElement {
	public MagicWitchCraftItemGroup(MagicWitchcraftModElements instance) {
		super(instance, 84);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabmagicwitchcraft") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(MagicalOrbItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
