
package fr.sinikraft.magicwitchcraft.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Explosion;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.NonNullList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.Block;

import java.util.Random;

import fr.sinikraft.magicwitchcraft.world.WorldMysteriousDimension;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureMagicalBushReadyPlantRightClicked;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureMagicalBushReadyMobplayerCollidesWithPlant;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureMagicalBerriesBlockDestroyedByPlayer;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureMagicalBerriesBlockDestroyedByExplosion;
import fr.sinikraft.magicwitchcraft.creativetab.TabMagicWitchCraft;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class BlockMagicalBushReady extends ElementsMagicWitchcraft.ModElement {
	@GameRegistry.ObjectHolder("magic_witchcraft:magicalbushready")
	public static final Block block = null;
	public BlockMagicalBushReady(ElementsMagicWitchcraft instance) {
		super(instance, 39);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustomFlower());
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("magic_witchcraft:magicalbushready", "inventory"));
	}

	@Override
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		boolean dimensionCriteria = false;
		if (dimID == WorldMysteriousDimension.DIMID)
			dimensionCriteria = true;
		if (!dimensionCriteria)
			return;
		boolean biomeCriteria = false;
		Biome biome = world.getBiome(new BlockPos(chunkX, 128, chunkZ));
		if (Biome.REGISTRY.getNameForObject(biome).equals(new ResourceLocation("magic_witchcraft:mysteriousbiome")))
			biomeCriteria = true;
		if (!biomeCriteria)
			return;
		for (int i = 0; i < 1; i++) {
			int l6 = chunkX + random.nextInt(16) + 8;
			int i11 = random.nextInt(128);
			int l14 = chunkZ + random.nextInt(16) + 8;
			(new WorldGenFlowers(((BlockFlower) block), BlockFlower.EnumFlowerType.DANDELION)).generate(world, random, new BlockPos(l6, i11, l14));
		}
	}
	public static class BlockCustomFlower extends BlockFlower {
		public BlockCustomFlower() {
			setSoundType(SoundType.PLANT);
			setCreativeTab(TabMagicWitchCraft.tab);
			setHardness(0F);
			setResistance(0F);
			setLightLevel(0F);
			setUnlocalizedName("magicalbushready");
			setRegistryName("magicalbushready");
		}

		@Override
		public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
			return 100;
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(new ItemStack(Blocks.AIR, (int) (1)));
		}

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(Item.getItemFromBlock(this), 1, this.damageDropped(state));
		}

		@Override
		public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
			return EnumPlantType.Plains;
		}

		@Override
		public BlockFlower.EnumFlowerColor getBlockType() {
			return BlockFlower.EnumFlowerColor.YELLOW;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void getSubBlocks(CreativeTabs tab, net.minecraft.util.NonNullList<ItemStack> list) {
			for (BlockFlower.EnumFlowerType blockflower$enumflowertype : BlockFlower.EnumFlowerType.getTypes(this.getBlockType())) {
				list.add(new ItemStack(this, 1, blockflower$enumflowertype.getMeta()));
			}
		}

		@Override
		public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
			super.onEntityCollidedWithBlock(world, pos, state, entity);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				ProcedureMagicalBushReadyMobplayerCollidesWithPlant.executeProcedure($_dependencies);
			}
		}

		@Override
		public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer entity, boolean willHarvest) {
			boolean retval = super.removedByPlayer(state, world, pos, entity, willHarvest);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureMagicalBerriesBlockDestroyedByPlayer.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion e) {
			super.onBlockDestroyedByExplosion(world, pos, e);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureMagicalBerriesBlockDestroyedByExplosion.executeProcedure($_dependencies);
			}
		}

		public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entity, EnumHand hand, EnumFacing direction,
				float hitX, float hitY, float hitZ) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureMagicalBushReadyPlantRightClicked.executeProcedure($_dependencies);
			}
			return true;
		}
	}
}
