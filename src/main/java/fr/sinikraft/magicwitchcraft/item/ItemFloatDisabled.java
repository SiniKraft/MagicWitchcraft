
package fr.sinikraft.magicwitchcraft.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ItemFloatDisabled extends ElementsMagicWitchcraft.ModElement {
	@GameRegistry.ObjectHolder("magic_witchcraft:floatdisabledhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("magic_witchcraft:floatdisabledbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("magic_witchcraft:floatdisabledlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("magic_witchcraft:floatdisabledboots")
	public static final Item boots = null;
	public ItemFloatDisabled(ElementsMagicWitchcraft instance) {
		super(instance, 180);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("FLOATDISABLED", "magic_witchcraft:floatboots", 50, new int[]{4, 10, 12, 4}, 18,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), 2f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("floatdisabledboots")
				.setRegistryName("floatdisabledboots").setCreativeTab(null));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("magic_witchcraft:floatdisabledboots", "inventory"));
	}
}
