
package fr.sinikraft.magicwitchcraft.world.biome;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.BiomeDictionary;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.init.Blocks;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityIllusionIllager;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;

import java.util.Random;

import fr.sinikraft.magicwitchcraft.entity.EntityDangerousDragon;
import fr.sinikraft.magicwitchcraft.block.BlockSpectralLeaves;
import fr.sinikraft.magicwitchcraft.block.BlockMagicalLog;
import fr.sinikraft.magicwitchcraft.block.BlockMagicalGrass;
import fr.sinikraft.magicwitchcraft.block.BlockMagicalDirt;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class BiomeMagicalBiome extends ElementsMagicWitchcraft.ModElement {
	@GameRegistry.ObjectHolder("magic_witchcraft:magicalbiome")
	public static final BiomeGenCustom biome = null;
	public BiomeMagicalBiome(ElementsMagicWitchcraft instance) {
		super(instance, 216);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.FOREST);
	}
	static class BiomeGenCustom extends Biome {
		public BiomeGenCustom() {
			super(new Biome.BiomeProperties("Magical biome").setRainfall(0F).setBaseHeight(0.1F).setWaterColor(-4980486).setHeightVariation(0.2F)
					.setTemperature(0.5F));
			setRegistryName("magicalbiome");
			topBlock = BlockMagicalGrass.block.getDefaultState();
			fillerBlock = BlockMagicalDirt.block.getDefaultState();
			decorator.generateFalls = true;
			decorator.treesPerChunk = 4;
			decorator.flowersPerChunk = 1;
			decorator.grassPerChunk = 4;
			decorator.deadBushPerChunk = 0;
			decorator.mushroomsPerChunk = 1;
			decorator.bigMushroomsPerChunk = 0;
			decorator.reedsPerChunk = 0;
			decorator.cactiPerChunk = 0;
			decorator.sandPatchesPerChunk = 0;
			decorator.gravelPatchesPerChunk = 0;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
			this.spawnableCreatureList.add(new SpawnListEntry(EntityAmbientCreature.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityIronGolem.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityBlaze.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityZombie.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityGiantZombie.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityLlama.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityIllusionIllager.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityZombie.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntitySlime.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityDangerousDragon.EntityCustom.class, 40, 1, 5));
		}

		@SideOnly(Side.CLIENT)
		@Override
		public int getGrassColorAtPos(BlockPos pos) {
			return -10066177;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public int getFoliageColorAtPos(BlockPos pos) {
			return -10066177;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public int getSkyColorByTemp(float currentTemperature) {
			return -39169;
		}

		@Override
		public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
			return new CustomTree();
		}
	}

	static class CustomTree extends WorldGenAbstractTree {
		CustomTree() {
			super(false);
		}

		@Override
		public boolean generate(World world, Random rand, BlockPos position) {
			int height = rand.nextInt(5) + 7;
			boolean spawnTree = true;
			if (position.getY() >= 1 && position.getY() + height + 1 <= world.getHeight()) {
				for (int j = position.getY(); j <= position.getY() + 1 + height; j++) {
					int k = 1;
					if (j == position.getY())
						k = 0;
					if (j >= position.getY() + height - 1)
						k = 2;
					for (int px = position.getX() - k; px <= position.getX() + k && spawnTree; px++) {
						for (int pz = position.getZ() - k; pz <= position.getZ() + k && spawnTree; pz++) {
							if (j >= 0 && j < world.getHeight()) {
								if (!this.isReplaceable(world, new BlockPos(px, j, pz))) {
									spawnTree = false;
								}
							} else {
								spawnTree = false;
							}
						}
					}
				}
				if (!spawnTree) {
					return false;
				} else {
					Block ground = world.getBlockState(position.add(0, -1, 0)).getBlock();
					Block ground2 = world.getBlockState(position.add(0, -2, 0)).getBlock();
					if (!((ground == BlockMagicalGrass.block.getDefaultState().getBlock()
							|| ground == BlockMagicalDirt.block.getDefaultState().getBlock())
							&& (ground2 == BlockMagicalGrass.block.getDefaultState().getBlock()
									|| ground2 == BlockMagicalDirt.block.getDefaultState().getBlock())))
						return false;
					IBlockState state = world.getBlockState(position.down());
					if (position.getY() < world.getHeight() - height - 1) {
						world.setBlockState(position.down(), BlockMagicalDirt.block.getDefaultState(), 2);
						for (int genh = position.getY() - 3 + height; genh <= position.getY() + height; genh++) {
							int i4 = genh - (position.getY() + height);
							int j1 = (int) (1 - i4 * 0.5);
							for (int k1 = position.getX() - j1; k1 <= position.getX() + j1; ++k1) {
								for (int i2 = position.getZ() - j1; i2 <= position.getZ() + j1; ++i2) {
									int j2 = i2 - position.getZ();
									if (Math.abs(position.getX()) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0) {
										BlockPos blockpos = new BlockPos(k1, genh, i2);
										state = world.getBlockState(blockpos);
										if (state.getBlock().isAir(state, world, blockpos) || state.getBlock().isLeaves(state, world, blockpos)
												|| state.getBlock() == Blocks.AIR.getDefaultState().getBlock()
												|| state.getBlock() == BlockSpectralLeaves.block.getDefaultState().getBlock()) {
											this.setBlockAndNotifyAdequately(world, blockpos, BlockSpectralLeaves.block.getDefaultState());
										}
									}
								}
							}
						}
						for (int genh = 0; genh < height; genh++) {
							BlockPos genhPos = position.up(genh);
							state = world.getBlockState(genhPos);
							if (state.getBlock().isAir(state, world, genhPos) || state.getBlock() == Blocks.AIR.getDefaultState().getBlock()
									|| state.getBlock() == BlockSpectralLeaves.block.getDefaultState().getBlock()) {
								this.setBlockAndNotifyAdequately(world, position.up(genh), BlockMagicalLog.block.getDefaultState());
							}
						}
						if (rand.nextInt(4) == 0 && height > 5) {
							for (int hlevel = 0; hlevel < 2; hlevel++) {
								for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
									if (rand.nextInt(4 - hlevel) == 0) {
										EnumFacing enumfacing1 = enumfacing.getOpposite();
										this.setBlockAndNotifyAdequately(world,
												position.add(enumfacing1.getFrontOffsetX(), height - 5 + hlevel, enumfacing1.getFrontOffsetZ()),
												Blocks.AIR.getDefaultState());
									}
								}
							}
						}
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}

		private void addVines(World world, BlockPos pos) {
			this.setBlockAndNotifyAdequately(world, pos, Blocks.AIR.getDefaultState());
			int i = 5;
			for (BlockPos blockpos = pos.down(); world.isAirBlock(blockpos) && i > 0; --i) {
				this.setBlockAndNotifyAdequately(world, blockpos, Blocks.AIR.getDefaultState());
				blockpos = blockpos.down();
			}
		}

		@Override
		protected boolean canGrowInto(Block blockType) {
			return blockType.getDefaultState().getMaterial() == Material.AIR || blockType == BlockMagicalLog.block.getDefaultState().getBlock()
					|| blockType == BlockSpectralLeaves.block.getDefaultState().getBlock()
					|| blockType == BlockMagicalGrass.block.getDefaultState().getBlock()
					|| blockType == BlockMagicalDirt.block.getDefaultState().getBlock();
		}

		@Override
		protected void setDirtAt(World world, BlockPos pos) {
			if (world.getBlockState(pos).getBlock() != BlockMagicalDirt.block.getDefaultState().getBlock())
				this.setBlockAndNotifyAdequately(world, pos, BlockMagicalDirt.block.getDefaultState());
		}

		@Override
		public boolean isReplaceable(World world, BlockPos pos) {
			net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
			return state.getBlock().isAir(state, world, pos) || canGrowInto(state.getBlock()) || state.getBlock().isReplaceable(world, pos);
		}
	}
}
