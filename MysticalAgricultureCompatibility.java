/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class MagicWitchcraftModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside fr.sinikraft.magicwitchcraft as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package fr.sinikraft.magicwitchcraft;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.ModList;

@MagicWitchcraftModElements.ModElement.Tag
public class MysticalAgricultureCompatibility extends MagicWitchcraftModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public MysticalAgricultureCompatibility(MagicWitchcraftModElements instance) {
		super(instance, 385);
	}

	@Override
	public void initElements() {
	}

	@Override  // pas encore tout a fait prêt !
	public void init(FMLCommonSetupEvent event) {
		System.out.printf("[MagicWitchcraft]: Launching Mystical Agriculture compatibility ...%n");
		if (ModList.get().isLoaded("mysticalcustomization")) {
			System.out.printf("[MagicWitchcraft]: Mystical Agriculture and Mystical Customization detected !%n");
		} else {
			System.out.printf("[MagicWitchcraft]: Mystical Agriculture and Mystical Customization are not detected, canceling ...%n");
			}
		}


    



}
