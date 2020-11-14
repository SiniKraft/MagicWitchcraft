
package fr.sinikraft.magicwitchcraft.world;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.DimensionManager;

import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.WorldType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.World;
import net.minecraft.world.DimensionType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ReportedException;
import net.minecraft.init.Blocks;
import net.minecraft.init.Biomes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.CrashReport;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockFalling;

import javax.annotation.Nullable;

import java.util.Random;
import java.util.List;

import fr.sinikraft.magicwitchcraft.procedure.ProcedureMagicalDimensionPlayerEntersDimension;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class WorldMagicalDimension extends ElementsMagicWitchcraft.ModElement {
	public static int DIMID = 4;
	public static final boolean NETHER_TYPE = false;
	public static DimensionType dtype;
	public WorldMagicalDimension(ElementsMagicWitchcraft instance) {
		super(instance, 144);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		if (DimensionManager.isDimensionRegistered(DIMID)) {
			DIMID = DimensionManager.getNextFreeDimId();
			System.err.println("Dimension ID for dimension magicaldimension is already registered. Falling back to ID: " + DIMID);
		}
		dtype = DimensionType.register("magicaldimension", "_magicaldimension", DIMID, WorldProviderMod.class, true);
		DimensionManager.registerDimension(DIMID, dtype);
	}
	public static class WorldProviderMod extends WorldProvider {
		@Override
		public void init() {
			this.biomeProvider = new BiomeProviderCustom(this.world.getSeed());
			this.nether = NETHER_TYPE;
			this.hasSkyLight = true;
		}

		@Override
		public void calculateInitialWeather() {
		}

		@Override
		public void updateWeather() {
		}

		@Override
		public boolean canDoLightning(net.minecraft.world.chunk.Chunk chunk) {
			return false;
		}

		@Override
		public boolean canDoRainSnowIce(net.minecraft.world.chunk.Chunk chunk) {
			return false;
		}

		@Override
		public DimensionType getDimensionType() {
			return dtype;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Vec3d getFogColor(float par1, float par2) {
			return new Vec3d(0.752941176471, 0.847058823529, 1);
		}

		@Override
		public IChunkGenerator createChunkGenerator() {
			return new ChunkProviderModded(this.world, this.world.getSeed() - DIMID);
		}

		@Override
		public boolean isSurfaceWorld() {
			return true;
		}

		@Override
		public boolean canRespawnHere() {
			return false;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public boolean doesXZShowFog(int par1, int par2) {
			return true;
		}

		@Override
		public WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos) {
			return WorldSleepResult.BED_EXPLODES;
		}

		@Override
		protected void generateLightBrightnessTable() {
			float f = 0.5f;
			for (int i = 0; i <= 15; ++i) {
				float f1 = 1 - (float) i / 15f;
				this.lightBrightnessTable[i] = (1 - f1) / (f1 * 3 + 1) * (1 - f) + f;
			}
		}

		@Override
		public boolean doesWaterVaporize() {
			return false;
		}

		@Override
		public void onPlayerAdded(EntityPlayerMP entity) {
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
				ProcedureMagicalDimensionPlayerEntersDimension.executeProcedure($_dependencies);
			}
		}
	}

	public static class ChunkProviderModded implements IChunkGenerator {
		private static final IBlockState STONE = Blocks.AIR.getDefaultState();
		private static final IBlockState STONE2 = Blocks.AIR.getDefaultState();
		private static final IBlockState FLUID = Blocks.AIR.getDefaultState();
		private static final IBlockState AIR = Blocks.AIR.getDefaultState();
		private static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
		private static final int SEALEVEL = 63;
		private final Random random;
		private final NoiseGeneratorOctaves perlin1;
		private final NoiseGeneratorOctaves perlin2;
		private final NoiseGeneratorOctaves perlin;
		private final NoiseGeneratorPerlin height;
		private final NoiseGeneratorOctaves depth;
		private final World world;
		private final WorldType terrainType;
		private final MapGenBase caveGenerator;
		private final MapGenBase ravineGenerator;
		private Biome[] biomesForGeneration;
		private double[] heightMap;
		private double[] depthbuff = new double[256];
		private double[] noiseRegMain;
		private double[] limitRegMin;
		private double[] limitRegMax;
		private double[] depthReg;
		private float[] biomeWeights;
		public ChunkProviderModded(World worldIn, long seed) {
			worldIn.setSeaLevel(SEALEVEL);
			caveGenerator = new MapGenCaves() {
				@Override
				protected boolean canReplaceBlock(IBlockState a, IBlockState b) {
					if (a.getBlock() == STONE.getBlock())
						return true;
					return super.canReplaceBlock(a, b);
				}
			};
			ravineGenerator = new MapGenRavine() {
				@Override
				protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
					net.minecraft.world.biome.Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
					IBlockState state = data.getBlockState(x, y, z);
					if (state.getBlock() == STONE.getBlock() || state.getBlock() == biome.topBlock.getBlock()
							|| state.getBlock() == biome.fillerBlock.getBlock()) {
						if (y - 1 < 10) {
							data.setBlockState(x, y, z, FLOWING_LAVA);
						} else {
							data.setBlockState(x, y, z, AIR);
							if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == biome.fillerBlock.getBlock()) {
								data.setBlockState(x, y - 1, z, biome.topBlock.getBlock().getDefaultState());
							}
						}
					}
				}
			};
			this.world = worldIn;
			this.terrainType = worldIn.getWorldInfo().getTerrainType();
			this.random = new Random(seed);
			this.perlin1 = new NoiseGeneratorOctaves(this.random, 16);
			this.perlin2 = new NoiseGeneratorOctaves(this.random, 16);
			this.perlin = new NoiseGeneratorOctaves(this.random, 8);
			this.height = new NoiseGeneratorPerlin(this.random, 4);
			this.depth = new NoiseGeneratorOctaves(this.random, 16);
			this.heightMap = new double[825];
			this.biomeWeights = new float[25];
			for (int i = -2; i <= 2; i++)
				for (int j = -2; j <= 2; j++)
					this.biomeWeights[i + 2 + (j + 2) * 5] = 10 / MathHelper.sqrt((float) (i * i + j * j) + 0.2f);
		}

		@Override
		public Chunk generateChunk(int x, int z) {
			this.random.setSeed((long) x * 535358712L + (long) z * 347539041L);
			ChunkPrimer chunkprimer = new ChunkPrimer();
			this.setBlocksInChunk(x, z, chunkprimer);
			this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 16, z * 16, 16, 16);
			this.replaceBiomeBlocks(x, z, chunkprimer, this.biomesForGeneration);
			this.caveGenerator.generate(this.world, x, z, chunkprimer);
			this.ravineGenerator.generate(this.world, x, z, chunkprimer);
			Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
			byte[] abyte = chunk.getBiomeArray();
			for (int i = 0; i < abyte.length; ++i)
				abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
			chunk.generateSkylightMap();
			return chunk;
		}

		@Override
		public void populate(int x, int z) {
			BlockFalling.fallInstantly = true;
			int i = x * 16;
			int j = z * 16;
			BlockPos blockpos = new BlockPos(i, 0, j);
			Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
			this.random.setSeed(this.world.getSeed());
			long k = this.random.nextLong() / 2 * 2 + 1;
			long l = this.random.nextLong() / 2 * 2 + 1;
			this.random.setSeed((long) x * k + (long) z * l ^ this.world.getSeed());
			net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.random, x, z, false);
			if (this.random.nextInt(4) == 0)
				if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.random, x, z, false,
						net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE)) {
					int i1 = this.random.nextInt(16) + 8;
					int j1 = this.random.nextInt(256);
					int k1 = this.random.nextInt(16) + 8;
					(new WorldGenLakes(FLUID.getBlock())).generate(this.world, this.random, blockpos.add(i1, j1, k1));
				}
			net.minecraftforge.common.MinecraftForge.EVENT_BUS
					.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Pre(this.world, this.random, blockpos));
			biome.decorate(this.world, this.random, new BlockPos(i, 0, j));
			net.minecraftforge.common.MinecraftForge.EVENT_BUS
					.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Post(this.world, this.random, blockpos));
			if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.random, x, z, false,
					net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS))
				WorldEntitySpawner.performWorldGenSpawning(this.world, biome, i + 8, j + 8, 16, 16, this.random);
			net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.random, x, z, false);
			BlockFalling.fallInstantly = false;
		}

		@Override
		public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
			return this.world.getBiome(pos).getSpawnableList(creatureType);
		}

		@Override
		public void recreateStructures(Chunk chunkIn, int x, int z) {
		}

		@Override
		public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
			return false;
		}

		@Override
		public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
			return null;
		}

		@Override
		public boolean generateStructures(Chunk chunkIn, int x, int z) {
			return false;
		}

		public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
			this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
			this.generateHeightmap(x * 4, 0, z * 4);
			for (int i = 0; i < 4; ++i) {
				int j = i * 5;
				int k = (i + 1) * 5;
				for (int l = 0; l < 4; ++l) {
					int i1 = (j + l) * 33;
					int j1 = (j + l + 1) * 33;
					int k1 = (k + l) * 33;
					int l1 = (k + l + 1) * 33;
					for (int i2 = 0; i2 < 32; ++i2) {
						double d0 = 0.125D;
						double d1 = this.heightMap[i1 + i2];
						double d2 = this.heightMap[j1 + i2];
						double d3 = this.heightMap[k1 + i2];
						double d4 = this.heightMap[l1 + i2];
						double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
						double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
						double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
						double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;
						for (int j2 = 0; j2 < 8; ++j2) {
							double d9 = 0.25D;
							double d10 = d1;
							double d11 = d2;
							double d12 = (d3 - d1) * 0.25D;
							double d13 = (d4 - d2) * 0.25D;
							for (int k2 = 0; k2 < 4; ++k2) {
								double d14 = 0.25D;
								double d16 = (d11 - d10) * 0.25D;
								double lvt_45_1_ = d10 - d16;
								for (int l2 = 0; l2 < 4; ++l2) {
									if ((lvt_45_1_ += d16) > 0.0D) {
										primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, STONE);
									} else if (i2 * 8 + j2 < SEALEVEL) {
										primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, FLUID);
									}
								}
								d10 += d12;
								d11 += d13;
							}
							d1 += d5;
							d2 += d6;
							d3 += d7;
							d4 += d8;
						}
					}
				}
			}
		}

		private void generateHeightmap(int p_185978_1_, int p_185978_2_, int p_185978_3_) {
			this.depthReg = this.depth.generateNoiseOctaves(this.depthReg, p_185978_1_, p_185978_3_, 5, 5, (double) 200, (double) 200, (double) 0.5f);
			float f = 684.412f;
			float f1 = 684.412f;
			this.noiseRegMain = this.perlin.generateNoiseOctaves(this.noiseRegMain, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5,
					(double) (f / 80), (double) (f1 / 160), (double) (f / 80));
			this.limitRegMin = this.perlin1.generateNoiseOctaves(this.limitRegMin, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5, (double) f,
					(double) f1, (double) f);
			this.limitRegMax = this.perlin2.generateNoiseOctaves(this.limitRegMax, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5, (double) f,
					(double) f1, (double) f);
			int i = 0;
			int j = 0;
			for (int k = 0; k < 5; ++k) {
				for (int l = 0; l < 5; ++l) {
					float f2 = 0.0F;
					float f3 = 0.0F;
					float f4 = 0.0F;
					int i1 = 2;
					Biome biome = this.biomesForGeneration[k + 2 + (l + 2) * 10];
					for (int j1 = -2; j1 <= 2; ++j1) {
						for (int k1 = -2; k1 <= 2; ++k1) {
							Biome biome1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
							float f5 = 0 + biome1.getBaseHeight() * 1;
							float f6 = 0 + biome1.getHeightVariation() * 1;
							if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0F) {
								f5 = 1.0F + f5 * 2.0F;
								f6 = 1.0F + f6 * 4.0F;
							}
							float f7 = this.biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);
							if (biome1.getBaseHeight() > biome.getBaseHeight()) {
								f7 /= 2.0F;
							}
							f2 += f6 * f7;
							f3 += f5 * f7;
							f4 += f7;
						}
					}
					f2 = f2 / f4;
					f3 = f3 / f4;
					f2 = f2 * 0.9F + 0.1F;
					f3 = (f3 * 4.0F - 1.0F) / 8.0F;
					double d7 = this.depthReg[j] / 8000.0D;
					if (d7 < 0.0D) {
						d7 = -d7 * 0.3D;
					}
					d7 = d7 * 3.0D - 2.0D;
					if (d7 < 0.0D) {
						d7 = d7 / 2.0D;
						if (d7 < -1.0D) {
							d7 = -1.0D;
						}
						d7 = d7 / 1.4D;
						d7 = d7 / 2.0D;
					} else {
						if (d7 > 1.0D) {
							d7 = 1.0D;
						}
						d7 = d7 / 8.0D;
					}
					++j;
					double d8 = (double) f3;
					double d9 = (double) f2;
					d8 = d8 + d7 * 0.2D;
					d8 = d8 * (double) 8.5f / 8.0D;
					double d0 = (double) 8.5f + d8 * 4.0D;
					for (int l1 = 0; l1 < 33; ++l1) {
						double d1 = ((double) l1 - d0) * (double) 12 * 128.0D / 256.0D / d9;
						if (d1 < 0.0D) {
							d1 *= 4.0D;
						}
						double d2 = this.limitRegMin[i] / (double) 512;
						double d3 = this.limitRegMax[i] / (double) 512;
						double d4 = (this.noiseRegMain[i] / 10.0D + 1.0D) / 2.0D;
						double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;
						if (l1 > 29) {
							double d6 = (double) ((float) (l1 - 29) / 3.0F);
							d5 = d5 * (1.0D - d6) + -10.0D * d6;
						}
						this.heightMap[i] = d5;
						++i;
					}
				}
			}
		}

		private void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn) {
			this.depthbuff = this.height.getRegion(this.depthbuff, (double) (x * 16), (double) (z * 16), 16, 16, 0.0625, 0.0625, 1);
			for (int i = 0; i < 16; i++)
				for (int j = 0; j < 16; j++)
					generateBiomeTerrain(this.world, this.random, primer, x * 16 + i, z * 16 + j, this.depthbuff[j + i * 16], biomesIn[j + i * 16]);
		}

		/**
		 * Given x, z coordinates, we count down all the y positions starting at 255 and
		 * working our way down. When we hit a non-air block, we replace it with
		 * biome.topBlock (default grass, descendants may set otherwise), and then a
		 * relatively shallow layer of blocks of type biome.fillerBlock (default dirt).
		 * A random set of blocks below y == 5 (but always including y == 0) is replaced
		 * with bedrock. If we don't hit non-air until somewhat below sea level, we top
		 * with gravel and fill down with stone. If biome.fillerBlock is red sand, we
		 * replace some of that with red sandstone.
		 */
		public final void generateBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal, Biome biome) {
			int i = SEALEVEL;
			IBlockState iblockstate = biome.topBlock;
			IBlockState iblockstate1 = biome.fillerBlock;
			int j = -1;
			int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
			int l = x & 15;
			int i1 = z & 15;
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
			for (int j1 = 255; j1 >= 0; --j1) {
				if (j1 <= rand.nextInt(5)) {
					chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
				} else {
					IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);
					if (iblockstate2.getMaterial() == Material.AIR) {
						j = -1;
					} else if (iblockstate2.getBlock() == STONE.getBlock()) {
						if (j == -1) {
							if (k <= 0) {
								iblockstate = AIR;
								iblockstate1 = STONE;
							} else if (j1 >= i - 4 && j1 <= i + 1) {
								iblockstate = biome.topBlock;
								iblockstate1 = biome.fillerBlock;
							}
							if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
								if (biome.getTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F) {
									iblockstate = FLUID;
								} else {
									iblockstate = FLUID;
								}
							}
							j = k;
							if (j1 >= i - 1) {
								chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
							} else if (j1 < i - 7 - k) {
								iblockstate = AIR;
								iblockstate1 = STONE;
								chunkPrimerIn.setBlockState(i1, j1, l, STONE2);
							} else {
								chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
							}
						} else if (j > 0) {
							--j;
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
							if (j == 0 && iblockstate1.getBlock() == Blocks.SAND && k > 1) {
								j = rand.nextInt(4) + Math.max(0, j1 - 63);
								iblockstate1 = iblockstate1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? STONE2 : STONE2;
							}
						}
					}
				}
			}
		}
	}

	public static class GenLayerBiomesCustom extends GenLayer {
		private Biome[] allowedBiomes = {Biome.REGISTRY.getObject(new ResourceLocation("void")),};
		public GenLayerBiomesCustom(long seed) {
			super(seed);
		}

		@Override
		public int[] getInts(int x, int z, int width, int depth) {
			int[] dest = IntCache.getIntCache(width * depth);
			for (int dz = 0; dz < depth; dz++) {
				for (int dx = 0; dx < width; dx++) {
					this.initChunkSeed(dx + x, dz + z);
					dest[(dx + dz * width)] = Biome.getIdForBiome(this.allowedBiomes[nextInt(this.allowedBiomes.length)]);
				}
			}
			return dest;
		}
	}

	public static class BiomeProviderCustom extends BiomeProvider {
		private GenLayer genBiomes;
		private GenLayer biomeIndexLayer;
		private BiomeCache biomeCache;
		public BiomeProviderCustom() {
			this.biomeCache = new BiomeCache(this);
		}

		public BiomeProviderCustom(long seed) {
			this.biomeCache = new BiomeCache(this);
			GenLayer[] agenlayer = makeTheWorld(seed);
			this.genBiomes = agenlayer[0];
			this.biomeIndexLayer = agenlayer[1];
		}

		private GenLayer[] makeTheWorld(long seed) {
			GenLayer biomes = new GenLayerBiomesCustom(1);
			biomes = new GenLayerZoom(1000, biomes);
			biomes = new GenLayerZoom(1001, biomes);
			biomes = new GenLayerZoom(1002, biomes);
			biomes = new GenLayerZoom(1003, biomes);
			biomes = new GenLayerZoom(1004, biomes);
			biomes = new GenLayerZoom(1005, biomes);
			GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10, biomes);
			biomes.initWorldGenSeed(seed);
			genlayervoronoizoom.initWorldGenSeed(seed);
			return new GenLayer[]{biomes, genlayervoronoizoom};
		}

		public BiomeProviderCustom(World world) {
			this(world.getSeed());
		}

		@Override
		public void cleanupCache() {
			this.biomeCache.cleanupCache();
		}

		@Override
		public Biome getBiome(BlockPos pos) {
			return this.getBiome(pos, null);
		}

		@Override
		public Biome getBiome(BlockPos pos, Biome defaultBiome) {
			return this.biomeCache.getBiome(pos.getX(), pos.getZ(), defaultBiome);
		}

		@Override
		public Biome[] getBiomes(Biome[] oldBiomeList, int x, int z, int width, int depth) {
			return this.getBiomes(oldBiomeList, x, z, width, depth, true);
		}

		@Override /**
					 * Returns an array of biomes for the location input.
					 */
		public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
			IntCache.resetIntCache();
			if (biomes == null || biomes.length < width * height) {
				biomes = new Biome[width * height];
			}
			int[] aint = this.genBiomes.getInts(x, z, width, height);
			try {
				for (int i = 0; i < width * height; ++i) {
					biomes[i] = Biome.getBiome(aint[i], Biomes.DEFAULT);
				}
				return biomes;
			} catch (Throwable throwable) {
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
				crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(biomes.length));
				crashreportcategory.addCrashSection("x", Integer.valueOf(x));
				crashreportcategory.addCrashSection("z", Integer.valueOf(z));
				crashreportcategory.addCrashSection("w", Integer.valueOf(width));
				crashreportcategory.addCrashSection("h", Integer.valueOf(height));
				throw new ReportedException(crashreport);
			}
		}

		@Override /**
					 * Gets a list of biomes for the specified blocks.
					 */
		public Biome[] getBiomes(@Nullable Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag) {
			IntCache.resetIntCache();
			if (listToReuse == null || listToReuse.length < width * length) {
				listToReuse = new Biome[width * length];
			}
			if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0) {
				Biome[] abiome = this.biomeCache.getCachedBiomes(x, z);
				System.arraycopy(abiome, 0, listToReuse, 0, width * length);
				return listToReuse;
			} else {
				int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);
				for (int i = 0; i < width * length; ++i) {
					listToReuse[i] = Biome.getBiome(aint[i], Biomes.DEFAULT);
				}
				return listToReuse;
			}
		}

		@Override /**
					 * checks given Chunk's Biomes against List of allowed ones
					 */
		public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed) {
			IntCache.resetIntCache();
			int i = x - radius >> 2;
			int j = z - radius >> 2;
			int k = x + radius >> 2;
			int l = z + radius >> 2;
			int i1 = k - i + 1;
			int j1 = l - j + 1;
			int[] aint = this.genBiomes.getInts(i, j, i1, j1);
			try {
				for (int k1 = 0; k1 < i1 * j1; ++k1) {
					Biome biome = Biome.getBiome(aint[k1]);
					if (!allowed.contains(biome)) {
						return false;
					}
				}
				return true;
			} catch (Throwable throwable) {
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
				crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
				crashreportcategory.addCrashSection("x", Integer.valueOf(x));
				crashreportcategory.addCrashSection("z", Integer.valueOf(z));
				crashreportcategory.addCrashSection("radius", Integer.valueOf(radius));
				crashreportcategory.addCrashSection("allowed", allowed);
				throw new ReportedException(crashreport);
			}
		}

		@Override
		@Nullable
		public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
			IntCache.resetIntCache();
			int i = x - range >> 2;
			int j = z - range >> 2;
			int k = x + range >> 2;
			int l = z + range >> 2;
			int i1 = k - i + 1;
			int j1 = l - j + 1;
			int[] aint = this.genBiomes.getInts(i, j, i1, j1);
			BlockPos blockpos = null;
			int k1 = 0;
			for (int l1 = 0; l1 < i1 * j1; ++l1) {
				int i2 = i + l1 % i1 << 2;
				int j2 = j + l1 / i1 << 2;
				Biome biome = Biome.getBiome(aint[l1]);
				if (biomes.contains(biome) && (blockpos == null || random.nextInt(k1 + 1) == 0)) {
					blockpos = new BlockPos(i2, 0, j2);
					++k1;
				}
			}
			return blockpos;
		}
	}
}
