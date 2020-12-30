
package fr.sinikraft.magicwitchcraft.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

import java.util.Random;

import fr.sinikraft.magicwitchcraft.procedure.ProcedureSaverBlockUpdateTick;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class BlockSaverBlockGreen extends ElementsMagicWitchcraft.ModElement {
	@GameRegistry.ObjectHolder("magic_witchcraft:saverblockgreen")
	public static final Block block = null;
	public BlockSaverBlockGreen(ElementsMagicWitchcraft instance) {
		super(instance, 355);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("saverblockgreen"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("magic_witchcraft:saverblockgreen", "inventory"));
	}
	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.CLOTH);
			setUnlocalizedName("saverblockgreen");
			setSoundType(SoundType.CLOTH);
			setHardness(10000F);
			setResistance(10000F);
			setLightLevel(0F);
			setLightOpacity(255);
			setCreativeTab(null);
			setBlockUnbreakable();
		}

		@Override
		public int tickRate(World world) {
			return 30;
		}

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(Blocks.AIR, (int) (1));
		}

		@Override
		public MapColor getMapColor(IBlockState state, IBlockAccess blockAccess, BlockPos pos) {
			return MapColor.GREEN;
		}

		@Override
		public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
			return false;
		}

		@Override
		public int quantityDropped(Random random) {
			return 0;
		}

		@Override
		public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
			super.onBlockAdded(world, pos, state);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			world.scheduleUpdate(new BlockPos(x, y, z), this, this.tickRate(world));
		}

		@Override
		public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
			super.updateTick(world, pos, state, random);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureSaverBlockUpdateTick.executeProcedure($_dependencies);
			}
			world.scheduleUpdate(new BlockPos(x, y, z), this, this.tickRate(world));
		}
	}
}
