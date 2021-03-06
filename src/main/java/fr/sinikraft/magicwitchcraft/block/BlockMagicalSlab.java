
package fr.sinikraft.magicwitchcraft.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.Item;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.Block;

import java.util.Random;

import fr.sinikraft.magicwitchcraft.creativetab.TabMagicWitchCraft;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class BlockMagicalSlab extends ElementsMagicWitchcraft.ModElement {
	@GameRegistry.ObjectHolder("magic_witchcraft:magicalslab")
	public static final Block block = null;
	@GameRegistry.ObjectHolder("magic_witchcraft:magicalslab_double")
	public static final Block block_slab_double = null;
	public BlockMagicalSlab(ElementsMagicWitchcraft instance) {
		super(instance, 85);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("magicalslab"));
		elements.blocks.add(() -> new BlockCustom.Double().setRegistryName("magicalslab_double"));
		elements.items.add(() -> new ItemSlab(block, (BlockSlab) block, (BlockSlab) block_slab_double).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("magic_witchcraft:magicalslab", "inventory"));
	}
	public static class BlockCustom extends BlockSlab {
		public BlockCustom() {
			super(Material.SPONGE);
			setUnlocalizedName("magicalslab");
			setSoundType(SoundType.SLIME);
			setHardness(1.5F);
			setResistance(40F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(TabMagicWitchCraft.tab);
			setDefaultSlipperiness(0.7999999999999999f);
			IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, BlockCustom.Variant.DEFAULT);
			if (!this.isDouble())
				state = state.withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM);
			this.setDefaultState(state);
			this.useNeighborBrightness = !this.isDouble();
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
		public static final PropertyEnum<BlockCustom.Variant> VARIANT = PropertyEnum.<BlockCustom.Variant>create("variant",
				BlockCustom.Variant.class);
		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune) {
			return Item.getItemFromBlock(block);
		}

		@Override
		public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
			return new ItemStack(block);
		}

		@Override
		protected net.minecraft.block.state.BlockStateContainer createBlockState() {
			return this.isDouble()
					? new net.minecraft.block.state.BlockStateContainer(this, new IProperty[]{VARIANT})
					: new net.minecraft.block.state.BlockStateContainer(this, new IProperty[]{HALF, VARIANT});
		}

		@Override
		public IBlockState getStateFromMeta(int meta) {
			if (this.isDouble()) {
				return this.getDefaultState();
			} else {
				return this.getDefaultState().withProperty(HALF, BlockSlab.EnumBlockHalf.values()[meta % 2]);
			}
		}

		@Override
		public int getMetaFromState(IBlockState state) {
			if (this.isDouble()) {
				return 0;
			} else {
				return state.getValue(HALF).ordinal();
			}
		}

		@Override
		public String getUnlocalizedName(int meta) {
			return super.getUnlocalizedName();
		}

		@Override
		public IProperty<?> getVariantProperty() {
			return VARIANT;
		}

		@Override
		public Comparable<?> getTypeForItem(ItemStack stack) {
			return BlockCustom.Variant.DEFAULT;
		}

		@Override
		public boolean isDouble() {
			return false;
		}

		@Override
		public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
			if (isDouble())
				return true;
			return super.doesSideBlockRendering(state, world, pos, face);
		}
		public enum Variant implements IStringSerializable {
			DEFAULT;
			public String getName() {
				return "default";
			}
		}

		public static class Double extends BlockCustom {
			@Override
			public boolean isDouble() {
				return true;
			}
		}
		@SideOnly(Side.CLIENT)
		@Override
		public BlockRenderLayer getBlockLayer() {
			return BlockRenderLayer.TRANSLUCENT;
		}

		@Override
		public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
			return BlockFaceShape.UNDEFINED;
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}
	}
}
