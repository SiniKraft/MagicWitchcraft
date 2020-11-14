
package fr.sinikraft.magicwitchcraft.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.Block;

import fr.sinikraft.magicwitchcraft.creativetab.TabMagicWitchCraft;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class BlockMagicalStairs extends ElementsMagicWitchcraft.ModElement {
	@GameRegistry.ObjectHolder("magic_witchcraft:magicalstairs")
	public static final Block block = null;
	public BlockMagicalStairs(ElementsMagicWitchcraft instance) {
		super(instance, 83);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("magicalstairs"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("magic_witchcraft:magicalstairs", "inventory"));
	}
	public static class BlockCustom extends BlockStairs {
		public BlockCustom() {
			super(new Block(Material.SPONGE).getDefaultState());
			setUnlocalizedName("magicalstairs");
			setSoundType(SoundType.SLIME);
			setHardness(1.5F);
			setResistance(10F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(TabMagicWitchCraft.tab);
			setDefaultSlipperiness(0.7999999999999999f);
		}

		// Slime code.
		public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
			if (entityIn.isSneaking()) {
				super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
			} else {
				entityIn.fall(fallDistance, 0.0F);
			}
		}

		public void onLanded(World worldIn, Entity entityIn) {
			if (entityIn.isSneaking()) {
				super.onLanded(worldIn, entityIn);
			} else if (entityIn.motionY < 0.0D) {
				entityIn.motionY = -entityIn.motionY * 1.0D;
				if (!(entityIn instanceof EntityLivingBase)) {
					entityIn.motionY *= 0.8D;
				}
			}
		}

		@Override
		public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
			entityIn.motionX *= 0.75D;
			entityIn.motionZ *= 0.75D;
			super.onEntityWalk(worldIn, pos, entityIn);
		}

		// end of Slime code.
		@SideOnly(Side.CLIENT)
		@Override
		public BlockRenderLayer getBlockLayer() {
			return BlockRenderLayer.TRANSLUCENT;
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}
	}
}
