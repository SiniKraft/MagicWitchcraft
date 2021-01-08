
package fr.sinikraft.magicwitchcraft.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;

import fr.sinikraft.magicwitchcraft.itemgroup.MagicWitchCraftItemGroup;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class HoneyPopsItem extends MagicWitchcraftModElements.ModElement {
	@ObjectHolder("magic_witchcraft:honeypops")
	public static final Item block = null;
	public HoneyPopsItem(MagicWitchcraftModElements instance) {
		super(instance, 77);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, MagicWitchcraftModElements.sounds.get(new ResourceLocation("magic_witchcraft:honey_pops")),
					new Item.Properties().group(MagicWitchCraftItemGroup.tab).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("honeypops");
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}
	}
}
