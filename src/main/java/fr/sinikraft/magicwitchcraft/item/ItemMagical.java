
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

import fr.sinikraft.magicwitchcraft.procedure.ProcedureMagicalHelmetTickEvent;
import fr.sinikraft.magicwitchcraft.creativetab.TabMagicWitchCraft;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ItemMagical extends ElementsMagicWitchcraft.ModElement {
	@GameRegistry.ObjectHolder("magic_witchcraft:magicalhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("magic_witchcraft:magicalbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("magic_witchcraft:magicallegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("magic_witchcraft:magicalboots")
	public static final Item boots = null;
	public ItemMagical(ElementsMagicWitchcraft instance) {
		super(instance, 166);
	}

	@Override
	public void initElements() {
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("MAGICAL", "magic_witchcraft:magical_helmet", 50, new int[]{4, 10, 12, 4}, 18,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("block.slime.place")), 3f);
		elements.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD) {
			@Override
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemstack) {
				super.onArmorTick(world, entity, itemstack);
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					ProcedureMagicalHelmetTickEvent.executeProcedure($_dependencies);
				}
			}
		}.setUnlocalizedName("magicalhelmet").setRegistryName("magicalhelmet").setCreativeTab(TabMagicWitchCraft.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("magic_witchcraft:magicalhelmet", "inventory"));
	}
}
