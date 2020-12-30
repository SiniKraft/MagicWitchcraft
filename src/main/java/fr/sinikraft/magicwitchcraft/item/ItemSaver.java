
package fr.sinikraft.magicwitchcraft.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import fr.sinikraft.magicwitchcraft.procedure.ProcedureSaverLeggingsTickEvent;
import fr.sinikraft.magicwitchcraft.creativetab.TabMagicWitchCraft;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ItemSaver extends ElementsMagicWitchcraft.ModElement {
	@GameRegistry.ObjectHolder("magic_witchcraft:saverhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("magic_witchcraft:saverbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("magic_witchcraft:saverlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("magic_witchcraft:saverboots")
	public static final Item boots = null;
	public ItemSaver(ElementsMagicWitchcraft instance) {
		super(instance, 67);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("SAVER", "magic_witchcraft:saver_leggings", 50, new int[]{4, 10, 12, 4}, 18,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("item.armor.equip_leather")),
				3f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					ProcedureSaverLeggingsTickEvent.executeProcedure($_dependencies);
				}
			}
		}.setUnlocalizedName("saverlegs").setRegistryName("saverlegs").setCreativeTab(TabMagicWitchCraft.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("magic_witchcraft:saverlegs", "inventory"));
	}
}
