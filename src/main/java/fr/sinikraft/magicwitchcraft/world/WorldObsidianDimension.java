
package fr.sinikraft.magicwitchcraft.world;

import org.jline.terminal.Size;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.EnumHelperClient;

import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.World;
import net.minecraft.world.Teleporter;
import net.minecraft.world.DimensionType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ReportedException;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumFacing;
import net.minecraft.item.ItemBlock;
import net.minecraft.init.Blocks;
import net.minecraft.init.Biomes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.Entity;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.CrashReport;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.Block;

import javax.annotation.Nullable;

import java.util.Random;
import java.util.List;

import fr.sinikraft.magicwitchcraft.procedure.ProcedureObsidianDimensionPlayerEntersDimension;
import fr.sinikraft.magicwitchcraft.item.ItemObsidianDimension;
import fr.sinikraft.magicwitchcraft.block.BlockSpectralBlock;
import fr.sinikraft.magicwitchcraft.block.BlockMagicalLava;
import fr.sinikraft.magicwitchcraft.block.BlockGreyObsidian;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

import com.google.common.cache.LoadingCache;

@ElementsMagicWitchcraft.ModElement.Tag
public class WorldObsidianDimension extends ElementsMagicWitchcraft.ModElement {
	public static int DIMID = 5;
	public static final boolean NETHER_TYPE = true;
	@GameRegistry.ObjectHolder("magic_witchcraft:obsidiandimension_portal")
	public static final BlockCustomPortal portal = null;
	public static DimensionType dtype;
	public WorldObsidianDimension(ElementsMagicWitchcraft instance) {
		super(instance, 254);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustomPortal());
		elements.items.add(() -> new ItemBlock(portal).setRegistryName(portal.getRegistryName()));
		elements.items.add(() -> new ItemObsidianDimension().setUnlocalizedName("obsidiandimension").setRegistryName("obsidiandimension"));
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		if (DimensionManager.isDimensionRegistered(DIMID)) {
			DIMID = DimensionManager.getNextFreeDimId();
			System.err.println("Dimension ID for dimension obsidiandimension is already registered. Falling back to ID: " + DIMID);
		}
		dtype = DimensionType.register("obsidiandimension", "_obsidiandimension", DIMID, WorldProviderMod.class, true);
		DimensionManager.registerDimension(DIMID, dtype);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(ItemObsidianDimension.block, 0,
				new ModelResourceLocation("magic_witchcraft:obsidiandimension", "inventory"));
	}
	public static class WorldProviderMod extends WorldProvider {
		@Override
		public void init() {
			this.biomeProvider = new BiomeProviderCustom(this.world.getSeed());
			this.nether = NETHER_TYPE;
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
		@SideOnly(Side.CLIENT)
		public net.minecraft.client.audio.MusicTicker.MusicType getMusicType() {
			return EnumHelperClient.addMusicType("music.nether",
					(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation(("music.nether"))), 6000,
					24000);
		}

		@Override
		public DimensionType getDimensionType() {
			return dtype;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Vec3d getFogColor(float par1, float par2) {
			return new Vec3d(0.756862745098, 0.352941176471, 0);
		}

		@Override
		public IChunkGenerator createChunkGenerator() {
			return new ChunkProviderModded(this.world, this.world.getSeed() - DIMID);
		}

		@Override
		public boolean isSurfaceWorld() {
			return false;
		}

		@Override
		public boolean canRespawnHere() {
			return false;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public boolean doesXZShowFog(int par1, int par2) {
			return false;
		}

		@Override
		public WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos) {
			return WorldSleepResult.BED_EXPLODES;
		}

		@Override
		public boolean doesWaterVaporize() {
			return true;
		}

		@Override
		public void onPlayerAdded(EntityPlayerMP entity) {
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				ProcedureObsidianDimensionPlayerEntersDimension.executeProcedure($_dependencies);
			}
		}
	}

	public static class TeleporterDimensionMod extends Teleporter {
		private Vec3d lastPortalVec;
		private EnumFacing teleportDirection;
		public TeleporterDimensionMod(WorldServer worldServer, Vec3d lastPortalVec, EnumFacing teleportDirection) {
			super(worldServer);
			this.lastPortalVec = lastPortalVec;
			this.teleportDirection = teleportDirection;
		}

		@Override
		public boolean makePortal(Entity entityIn) {
			int i = 16;
			double d0 = -1.0D;
			int j = MathHelper.floor(entityIn.posX);
			int k = MathHelper.floor(entityIn.posY);
			int l = MathHelper.floor(entityIn.posZ);
			int i1 = j;
			int j1 = k;
			int k1 = l;
			int l1 = 0;
			int i2 = this.random.nextInt(4);
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
			for (int j2 = j - 16; j2 <= j + 16; ++j2) {
				double d1 = (double) j2 + 0.5D - entityIn.posX;
				for (int l2 = l - 16; l2 <= l + 16; ++l2) {
					double d2 = (double) l2 + 0.5D - entityIn.posZ;
					label293 : for (int j3 = this.world.getActualHeight() - 1; j3 >= 0; --j3) {
						if (this.world.isAirBlock(blockpos$mutableblockpos.setPos(j2, j3, l2))) {
							while (j3 > 0 && this.world.isAirBlock(blockpos$mutableblockpos.setPos(j2, j3 - 1, l2))) {
								--j3;
							}
							for (int k3 = i2; k3 < i2 + 4; ++k3) {
								int l3 = k3 % 2;
								int i4 = 1 - l3;
								if (k3 % 4 >= 2) {
									l3 = -l3;
									i4 = -i4;
								}
								for (int j4 = 0; j4 < 3; ++j4) {
									for (int k4 = 0; k4 < 4; ++k4) {
										for (int l4 = -1; l4 < 4; ++l4) {
											int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
											int j5 = j3 + l4;
											int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
											blockpos$mutableblockpos.setPos(i5, j5, k5);
											if (l4 < 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid()
													|| l4 >= 0 && !this.world.isAirBlock(blockpos$mutableblockpos)) {
												continue label293;
											}
										}
									}
								}
								double d5 = (double) j3 + 0.5D - entityIn.posY;
								double d7 = d1 * d1 + d5 * d5 + d2 * d2;
								if (d0 < 0.0D || d7 < d0) {
									d0 = d7;
									i1 = j2;
									j1 = j3;
									k1 = l2;
									l1 = k3 % 4;
								}
							}
						}
					}
				}
			}
			if (d0 < 0.0D) {
				for (int l5 = j - 16; l5 <= j + 16; ++l5) {
					double d3 = (double) l5 + 0.5D - entityIn.posX;
					for (int j6 = l - 16; j6 <= l + 16; ++j6) {
						double d4 = (double) j6 + 0.5D - entityIn.posZ;
						label231 : for (int i7 = this.world.getActualHeight() - 1; i7 >= 0; --i7) {
							if (this.world.isAirBlock(blockpos$mutableblockpos.setPos(l5, i7, j6))) {
								while (i7 > 0 && this.world.isAirBlock(blockpos$mutableblockpos.setPos(l5, i7 - 1, j6))) {
									--i7;
								}
								for (int k7 = i2; k7 < i2 + 2; ++k7) {
									int j8 = k7 % 2;
									int j9 = 1 - j8;
									for (int j10 = 0; j10 < 4; ++j10) {
										for (int j11 = -1; j11 < 4; ++j11) {
											int j12 = l5 + (j10 - 1) * j8;
											int i13 = i7 + j11;
											int j13 = j6 + (j10 - 1) * j9;
											blockpos$mutableblockpos.setPos(j12, i13, j13);
											if (j11 < 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid()
													|| j11 >= 0 && !this.world.isAirBlock(blockpos$mutableblockpos)) {
												continue label231;
											}
										}
									}
									double d6 = (double) i7 + 0.5D - entityIn.posY;
									double d8 = d3 * d3 + d6 * d6 + d4 * d4;
									if (d0 < 0.0D || d8 < d0) {
										d0 = d8;
										i1 = l5;
										j1 = i7;
										k1 = j6;
										l1 = k7 % 2;
									}
								}
							}
						}
					}
				}
			}
			int i6 = i1;
			int k2 = j1;
			int k6 = k1;
			int l6 = l1 % 2;
			int i3 = 1 - l6;
			if (l1 % 4 >= 2) {
				l6 = -l6;
				i3 = -i3;
			}
			if (d0 < 0.0D) {
				j1 = MathHelper.clamp(j1, 70, this.world.getActualHeight() - 10);
				k2 = j1;
				for (int j7 = -1; j7 <= 1; ++j7) {
					for (int l7 = 1; l7 < 3; ++l7) {
						for (int k8 = -1; k8 < 3; ++k8) {
							int k9 = i6 + (l7 - 1) * l6 + j7 * i3;
							int k10 = k2 + k8;
							int k11 = k6 + (l7 - 1) * i3 - j7 * l6;
							boolean flag = k8 < 0;
							this.world.setBlockState(new BlockPos(k9, k10, k11),
									flag ? BlockSpectralBlock.block.getDefaultState().getBlock().getDefaultState() : Blocks.AIR.getDefaultState());
						}
					}
				}
			}
			IBlockState iblockstate = portal.getDefaultState().withProperty(BlockPortal.AXIS, l6 == 0 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
			for (int i8 = 0; i8 < 4; ++i8) {
				for (int l8 = 0; l8 < 4; ++l8) {
					for (int l9 = -1; l9 < 4; ++l9) {
						int l10 = i6 + (l8 - 1) * l6;
						int l11 = k2 + l9;
						int k12 = k6 + (l8 - 1) * i3;
						boolean flag1 = l8 == 0 || l8 == 3 || l9 == -1 || l9 == 3;
						this.world.setBlockState(new BlockPos(l10, l11, k12),
								flag1 ? BlockSpectralBlock.block.getDefaultState().getBlock().getDefaultState() : iblockstate, 2);
					}
				}
				for (int i9 = 0; i9 < 4; ++i9) {
					for (int i10 = -1; i10 < 4; ++i10) {
						int i11 = i6 + (i9 - 1) * l6;
						int i12 = k2 + i10;
						int l12 = k6 + (i9 - 1) * i3;
						BlockPos blockpos = new BlockPos(i11, i12, l12);
						this.world.notifyNeighborsOfStateChange(blockpos, this.world.getBlockState(blockpos).getBlock(), false);
					}
				}
			}
			return true;
		}

		@Override
		public void placeInPortal(Entity entityIn, float rotationYaw) {
			if (this.world.provider.getDimensionType().getId() != 1) {
				if (!this.placeInExistingPortal(entityIn, rotationYaw)) {
					this.makePortal(entityIn);
					this.placeInExistingPortal(entityIn, rotationYaw);
				}
			} else {
				int i = MathHelper.floor(entityIn.posX);
				int j = MathHelper.floor(entityIn.posY) - 1;
				int k = MathHelper.floor(entityIn.posZ);
				int l = 1;
				int i1 = 0;
				for (int j1 = -2; j1 <= 2; ++j1) {
					for (int k1 = -2; k1 <= 2; ++k1) {
						for (int l1 = -1; l1 < 3; ++l1) {
							int i2 = i + k1 * 1 + j1 * 0;
							int j2 = j + l1;
							int k2 = k + k1 * 0 - j1 * 1;
							boolean flag = l1 < 0;
							this.world.setBlockState(new BlockPos(i2, j2, k2),
									flag ? BlockSpectralBlock.block.getDefaultState().getBlock().getDefaultState() : Blocks.AIR.getDefaultState());
						}
					}
				}
				entityIn.setLocationAndAngles((double) i, (double) j, (double) k, entityIn.rotationYaw, 0.0F);
				entityIn.motionX = 0.0D;
				entityIn.motionY = 0.0D;
				entityIn.motionZ = 0.0D;
			}
		}

		@Override
		public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
			int i = 128;
			double d0 = -1.0D;
			int j = MathHelper.floor(entityIn.posX);
			int k = MathHelper.floor(entityIn.posZ);
			boolean flag = true;
			BlockPos blockpos = BlockPos.ORIGIN;
			long l = ChunkPos.asLong(j, k);
			if (this.destinationCoordinateCache.containsKey(l)) {
				Teleporter.PortalPosition teleporter$portalposition = (Teleporter.PortalPosition) this.destinationCoordinateCache.get(l);
				d0 = 0.0D;
				blockpos = teleporter$portalposition;
				teleporter$portalposition.lastUpdateTime = this.world.getTotalWorldTime();
				flag = false;
			} else {
				BlockPos blockpos3 = new BlockPos(entityIn);
				for (int i1 = -128; i1 <= 128; ++i1) {
					BlockPos blockpos2;
					for (int j1 = -128; j1 <= 128; ++j1) {
						for (BlockPos blockpos1 = blockpos3.add(i1, this.world.getActualHeight() - 1 - blockpos3.getY(), j1); blockpos1
								.getY() >= 0; blockpos1 = blockpos2) {
							blockpos2 = blockpos1.down();
							if (this.world.getBlockState(blockpos1).getBlock() == portal) {
								for (blockpos2 = blockpos1.down(); this.world.getBlockState(blockpos2).getBlock() == portal; blockpos2 = blockpos2
										.down()) {
									blockpos1 = blockpos2;
								}
								double d1 = blockpos1.distanceSq(blockpos3);
								if (d0 < 0.0D || d1 < d0) {
									d0 = d1;
									blockpos = blockpos1;
								}
							}
						}
					}
				}
			}
			if (d0 >= 0.0D) {
				if (flag) {
					this.destinationCoordinateCache.put(l, new Teleporter.PortalPosition(blockpos, this.world.getTotalWorldTime()));
				}
				double d5 = (double) blockpos.getX() + 0.5D;
				double d7 = (double) blockpos.getZ() + 0.5D;
				BlockPattern.PatternHelper blockpattern$patternhelper = portal.createPatternHelper(this.world, blockpos);
				boolean flag1 = blockpattern$patternhelper.getForwards().rotateY().getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE;
				double d2 = blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X
						? (double) blockpattern$patternhelper.getFrontTopLeft().getZ()
						: (double) blockpattern$patternhelper.getFrontTopLeft().getX();
				double d6 = (double) (blockpattern$patternhelper.getFrontTopLeft().getY() + 1)
						- lastPortalVec.y * (double) blockpattern$patternhelper.getHeight();
				if (flag1) {
					++d2;
				}
				if (blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X) {
					d7 = d2 + (1.0D - lastPortalVec.x) * (double) blockpattern$patternhelper.getWidth()
							* (double) blockpattern$patternhelper.getForwards().rotateY().getAxisDirection().getOffset();
				} else {
					d5 = d2 + (1.0D - lastPortalVec.x) * (double) blockpattern$patternhelper.getWidth()
							* (double) blockpattern$patternhelper.getForwards().rotateY().getAxisDirection().getOffset();
				}
				float f = 0.0F;
				float f1 = 0.0F;
				float f2 = 0.0F;
				float f3 = 0.0F;
				if (blockpattern$patternhelper.getForwards().getOpposite() == teleportDirection) {
					f = 1.0F;
					f1 = 1.0F;
				} else if (blockpattern$patternhelper.getForwards().getOpposite() == teleportDirection.getOpposite()) {
					f = -1.0F;
					f1 = -1.0F;
				} else if (blockpattern$patternhelper.getForwards().getOpposite() == teleportDirection.rotateY()) {
					f2 = 1.0F;
					f3 = -1.0F;
				} else {
					f2 = -1.0F;
					f3 = 1.0F;
				}
				double d3 = entityIn.motionX;
				double d4 = entityIn.motionZ;
				entityIn.motionX = d3 * (double) f + d4 * (double) f3;
				entityIn.motionZ = d3 * (double) f2 + d4 * (double) f1;
				entityIn.rotationYaw = rotationYaw - (float) (teleportDirection.getOpposite().getHorizontalIndex() * 90)
						+ (float) (blockpattern$patternhelper.getForwards().getHorizontalIndex() * 90);
				if (entityIn instanceof EntityPlayerMP) {
					((EntityPlayerMP) entityIn).connection.setPlayerLocation(d5, d6, d7, entityIn.rotationYaw, entityIn.rotationPitch);
				} else {
					entityIn.setLocationAndAngles(d5, d6, d7, entityIn.rotationYaw, entityIn.rotationPitch);
				}
				return true;
			} else {
				return false;
			}
		}
	}

	public static class BlockCustomPortal extends BlockPortal {
		public BlockCustomPortal() {
			setHardness(-1.0F);
			setUnlocalizedName("obsidiandimension_portal");
			setRegistryName("obsidiandimension_portal");
			setLightLevel(0F);
		}

		@Override
		public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
		}

		public void portalSpawn(World worldIn, BlockPos pos) {
			BlockCustomPortal.Size portalsize = new BlockCustomPortal.Size(worldIn, pos, EnumFacing.Axis.X);
			if (portalsize.isValid() && portalsize.portalBlockCount == 0) {
				portalsize.placePortalBlocks();
			} else {
				portalsize = new BlockCustomPortal.Size(worldIn, pos, EnumFacing.Axis.Z);
				if (portalsize.isValid() && portalsize.portalBlockCount == 0)
					portalsize.placePortalBlocks();
			}
		}

		@Override
		public BlockPattern.PatternHelper createPatternHelper(World worldIn, BlockPos p_181089_2_) {
			EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Z;
			BlockCustomPortal.Size blockportal$size = new BlockCustomPortal.Size(worldIn, p_181089_2_, EnumFacing.Axis.X);
			LoadingCache<BlockPos, BlockWorldState> loadingcache = BlockPattern.createLoadingCache(worldIn, true);
			if (!blockportal$size.isValid()) {
				enumfacing$axis = EnumFacing.Axis.X;
				blockportal$size = new BlockCustomPortal.Size(worldIn, p_181089_2_, EnumFacing.Axis.Z);
			}
			if (!blockportal$size.isValid()) {
				return new BlockPattern.PatternHelper(p_181089_2_, EnumFacing.NORTH, EnumFacing.UP, loadingcache, 1, 1, 1);
			} else {
				int[] aint = new int[EnumFacing.AxisDirection.values().length];
				EnumFacing enumfacing = blockportal$size.rightDir.rotateYCCW();
				BlockPos blockpos = blockportal$size.bottomLeft.up(blockportal$size.getHeight() - 1);
				for (EnumFacing.AxisDirection enumfacing$axisdirection : EnumFacing.AxisDirection.values()) {
					BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(
							enumfacing.getAxisDirection() == enumfacing$axisdirection
									? blockpos
									: blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1),
							EnumFacing.getFacingFromAxis(enumfacing$axisdirection, enumfacing$axis), EnumFacing.UP, loadingcache,
							blockportal$size.getWidth(), blockportal$size.getHeight(), 1);
					for (int i = 0; i < blockportal$size.getWidth(); ++i) {
						for (int j = 0; j < blockportal$size.getHeight(); ++j) {
							BlockWorldState blockworldstate = blockpattern$patternhelper.translateOffset(i, j, 1);
							if (blockworldstate.getBlockState() != null && blockworldstate.getBlockState().getMaterial() != Material.AIR) {
								++aint[enumfacing$axisdirection.ordinal()];
							}
						}
					}
				}
				EnumFacing.AxisDirection enumfacing$axisdirection1 = EnumFacing.AxisDirection.POSITIVE;
				for (EnumFacing.AxisDirection enumfacing$axisdirection2 : EnumFacing.AxisDirection.values()) {
					if (aint[enumfacing$axisdirection2.ordinal()] < aint[enumfacing$axisdirection1.ordinal()]) {
						enumfacing$axisdirection1 = enumfacing$axisdirection2;
					}
				}
				return new BlockPattern.PatternHelper(
						enumfacing.getAxisDirection() == enumfacing$axisdirection1
								? blockpos
								: blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1),
						EnumFacing.getFacingFromAxis(enumfacing$axisdirection1, enumfacing$axis), EnumFacing.UP, loadingcache,
						blockportal$size.getWidth(), blockportal$size.getHeight(), 1);
			}
		}

		@Override /**
					 * Called when a neighboring block was changed and marks that this state should
					 * perform any checks during a neighbor change. Cases may include when redstone
					 * power is updated, cactus blocks popping off due to a neighboring solid block,
					 * etc.
					 */
		public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
			EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis) state.getValue(AXIS);
			if (enumfacing$axis == EnumFacing.Axis.X) {
				BlockCustomPortal.Size blockportal$size = new BlockCustomPortal.Size(worldIn, pos, EnumFacing.Axis.X);
				if (!blockportal$size.isValid() || blockportal$size.portalBlockCount < blockportal$size.width * blockportal$size.height) {
					worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
				}
			} else if (enumfacing$axis == EnumFacing.Axis.Z) {
				BlockCustomPortal.Size blockportal$size1 = new BlockCustomPortal.Size(worldIn, pos, EnumFacing.Axis.Z);
				if (!blockportal$size1.isValid() || blockportal$size1.portalBlockCount < blockportal$size1.width * blockportal$size1.height) {
					worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
				}
			}
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
			for (int i = 0; i < 4; i++) {
				double px = pos.getX() + random.nextFloat();
				double py = pos.getY() + random.nextFloat();
				double pz = pos.getZ() + random.nextFloat();
				double vx = (random.nextFloat() - 0.5) / 2f;
				double vy = (random.nextFloat() - 0.5) / 2f;
				double vz = (random.nextFloat() - 0.5) / 2f;
				int j = random.nextInt(4) - 1;
				if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
					px = pos.getX() + 0.5 + 0.25 * j;
					vx = random.nextFloat() * 2 * j;
				} else {
					pz = pos.getZ() + 0.5 + 0.25 * j;
					vz = random.nextFloat() * 2 * j;
				}
				world.spawnParticle(EnumParticleTypes.CRIT, px, py, pz, vx, vy, vz);
			}
			if (random.nextInt(110) == 0)
				world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
						(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
								.getObject(new ResourceLocation(("entity.slime.squish"))),
						SoundCategory.BLOCKS, 0.5f, random.nextFloat() * 0.4F + 0.8F, false);
		}

		public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
			if (!worldIn.isRemote && !entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn instanceof EntityPlayerMP) {
				EntityPlayerMP thePlayer = (EntityPlayerMP) entityIn;
				if (thePlayer.timeUntilPortal > 0) {
					thePlayer.timeUntilPortal = 10;
				} else if (thePlayer.dimension != DIMID) {
					thePlayer.timeUntilPortal = 10;
					ReflectionHelper.setPrivateValue(EntityPlayerMP.class, thePlayer, true, "invulnerableDimensionChange", "field_184851_cj");
					thePlayer.mcServer.getPlayerList().transferPlayerToDimension(thePlayer, DIMID, getTeleporterForDimension(thePlayer, pos, DIMID));
				} else {
					thePlayer.timeUntilPortal = 10;
					ReflectionHelper.setPrivateValue(EntityPlayerMP.class, thePlayer, true, "invulnerableDimensionChange", "field_184851_cj");
					thePlayer.mcServer.getPlayerList().transferPlayerToDimension(thePlayer, 0, getTeleporterForDimension(thePlayer, pos, 0));
				}
			}
		}

		private TeleporterDimensionMod getTeleporterForDimension(Entity entity, BlockPos pos, int dimid) {
			BlockPattern.PatternHelper bph = portal.createPatternHelper(entity.world, new BlockPos(pos));
			double d0 = bph.getForwards().getAxis() == EnumFacing.Axis.X
					? (double) bph.getFrontTopLeft().getZ()
					: (double) bph.getFrontTopLeft().getX();
			double d1 = bph.getForwards().getAxis() == EnumFacing.Axis.X ? entity.posZ : entity.posX;
			d1 = Math.abs(MathHelper.pct(d1 - (double) (bph.getForwards().rotateY().getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE ? 1 : 0),
					d0, d0 - (double) bph.getWidth()));
			double d2 = MathHelper.pct(entity.posY - 1, (double) bph.getFrontTopLeft().getY(),
					(double) (bph.getFrontTopLeft().getY() - bph.getHeight()));
			return new TeleporterDimensionMod(entity.getServer().getWorld(dimid), new Vec3d(d1, d2, 0), bph.getForwards());
		}
		public static class Size {
			private final World world;
			private final EnumFacing.Axis axis;
			private final EnumFacing rightDir;
			private final EnumFacing leftDir;
			private int portalBlockCount;
			private BlockPos bottomLeft;
			private int height;
			private int width;
			public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_) {
				this.world = worldIn;
				this.axis = p_i45694_3_;
				if (p_i45694_3_ == EnumFacing.Axis.X) {
					this.leftDir = EnumFacing.EAST;
					this.rightDir = EnumFacing.WEST;
				} else {
					this.leftDir = EnumFacing.NORTH;
					this.rightDir = EnumFacing.SOUTH;
				}
				for (BlockPos blockpos = p_i45694_2_; p_i45694_2_.getY() > blockpos.getY() - 21 && p_i45694_2_.getY() > 0
						&& this.isEmptyBlock(worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down()) {
					;
				}
				int i = this.getDistanceUntilEdge(p_i45694_2_, this.leftDir) - 1;
				if (i >= 0) {
					this.bottomLeft = p_i45694_2_.offset(this.leftDir, i);
					this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
					if (this.width < 2 || this.width > 21) {
						this.bottomLeft = null;
						this.width = 0;
					}
				}
				if (this.bottomLeft != null) {
					this.height = this.calculatePortalHeight();
				}
			}

			protected int getDistanceUntilEdge(BlockPos p_180120_1_, EnumFacing p_180120_2_) {
				int i;
				for (i = 0; i < 22; ++i) {
					BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);
					if (!this.isEmptyBlock(this.world.getBlockState(blockpos).getBlock())
							|| this.world.getBlockState(blockpos.down()).getBlock() != BlockSpectralBlock.block.getDefaultState().getBlock()) {
						break;
					}
				}
				Block block = this.world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
				return block == BlockSpectralBlock.block.getDefaultState().getBlock() ? i : 0;
			}

			public int getHeight() {
				return this.height;
			}

			public int getWidth() {
				return this.width;
			}

			protected int calculatePortalHeight() {
				label56 : for (this.height = 0; this.height < 21; ++this.height) {
					for (int i = 0; i < this.width; ++i) {
						BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
						Block block = this.world.getBlockState(blockpos).getBlock();
						if (!this.isEmptyBlock(block)) {
							break label56;
						}
						if (block == portal) {
							++this.portalBlockCount;
						}
						if (i == 0) {
							block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();
							if (block != BlockSpectralBlock.block.getDefaultState().getBlock()) {
								break label56;
							}
						} else if (i == this.width - 1) {
							block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();
							if (block != BlockSpectralBlock.block.getDefaultState().getBlock()) {
								break label56;
							}
						}
					}
				}
				for (int j = 0; j < this.width; ++j) {
					if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != BlockSpectralBlock.block
							.getDefaultState().getBlock()) {
						this.height = 0;
						break;
					}
				}
				if (this.height <= 21 && this.height >= 3) {
					return this.height;
				} else {
					this.bottomLeft = null;
					this.width = 0;
					this.height = 0;
					return 0;
				}
			}

			protected boolean isEmptyBlock(Block blockIn) {
				return blockIn.getDefaultState().getMaterial() == Material.AIR || blockIn == Blocks.FIRE || blockIn == portal;
			}

			public boolean isValid() {
				return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
			}

			public void placePortalBlocks() {
				for (int i = 0; i < this.width; ++i) {
					BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);
					for (int j = 0; j < this.height; ++j) {
						this.world.setBlockState(blockpos.up(j), portal.getDefaultState().withProperty(BlockPortal.AXIS, this.axis), 2);
					}
				}
			}
		}
	}

	public static class ChunkProviderModded implements IChunkGenerator {
		private static final IBlockState STONE = BlockGreyObsidian.block.getDefaultState();
		private static final IBlockState STONE2 = BlockGreyObsidian.block.getDefaultState();
		private static final IBlockState STONE3 = Blocks.OBSIDIAN.getDefaultState();
		private static final IBlockState FLUID = BlockMagicalLava.block.getDefaultState();
		private static final IBlockState AIR = Blocks.AIR.getDefaultState();
		private static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
		private static final int SEALEVEL = 63;
		private final World world;
		private final Random random;
		private final NoiseGeneratorOctaves lperlinNoise1;
		private final NoiseGeneratorOctaves lperlinNoise2;
		private final NoiseGeneratorOctaves perlinNoise1;
		private final NoiseGeneratorOctaves secondaryStoneNoiseGen;
		private final NoiseGeneratorOctaves depthNoiseGen;
		private final NoiseGeneratorOctaves depthNoise;
		private final MapGenBase genNetherCaves;
		private double[] stoneNoise3 = new double[256];
		private double[] stoneNoise2 = new double[256];
		private double[] depthBuffer = new double[256];
		private double[] buffer;
		private double[] pnr;
		private double[] ar;
		private double[] br;
		private double[] dr;
		double[] unused;
		public ChunkProviderModded(World worldIn, long seed) {
			worldIn.setSeaLevel(SEALEVEL);
			this.world = worldIn;
			this.random = new Random(seed);
			this.lperlinNoise1 = new NoiseGeneratorOctaves(this.random, 16);
			this.lperlinNoise2 = new NoiseGeneratorOctaves(this.random, 16);
			this.perlinNoise1 = new NoiseGeneratorOctaves(this.random, 8);
			this.secondaryStoneNoiseGen = new NoiseGeneratorOctaves(this.random, 4);
			this.depthNoiseGen = new NoiseGeneratorOctaves(this.random, 4);
			this.depthNoise = new NoiseGeneratorOctaves(this.random, 16);
			this.genNetherCaves = new MapGenCavesHell();
		}

		@Override
		public Chunk generateChunk(int x, int z) {
			this.random.setSeed((long) x * 347539041L + (long) z * 535358712L);
			ChunkPrimer chunkprimer = new ChunkPrimer();
			this.prepareHeights(x, z, chunkprimer);
			this.buildSurfaces(x, z, chunkprimer);
			this.genNetherCaves.generate(this.world, x, z, chunkprimer);
			Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
			Biome[] abiome = this.world.getBiomeProvider().getBiomes((Biome[]) null, x * 16, z * 16, 16, 16);
			byte[] abyte = chunk.getBiomeArray();
			for (int i = 0; i < abyte.length; ++i)
				abyte[i] = (byte) Biome.getIdForBiome(abiome[i]);
			chunk.resetRelightChecks();
			return chunk;
		}

		@Override
		public void populate(int x, int z) {
			BlockFalling.fallInstantly = true;
			net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.random, x, z, false);
			int i = x * 16;
			int j = z * 16;
			BlockPos blockpos = new BlockPos(i, 0, j);
			Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
			ChunkPos chunkpos = new ChunkPos(x, z);
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

		public void prepareHeights(int p_185936_1_, int p_185936_2_, ChunkPrimer primer) {
			int i = 4;
			int j = this.world.getSeaLevel() / 2 + 1;
			int k = 5;
			int l = 17;
			int i1 = 5;
			this.buffer = this.getHeights(this.buffer, p_185936_1_ * 4, 0, p_185936_2_ * 4, 5, 17, 5);
			for (int j1 = 0; j1 < 4; ++j1) {
				for (int k1 = 0; k1 < 4; ++k1) {
					for (int l1 = 0; l1 < 16; ++l1) {
						double d0 = 0.125D;
						double d1 = this.buffer[((j1 + 0) * 5 + k1 + 0) * 17 + l1 + 0];
						double d2 = this.buffer[((j1 + 0) * 5 + k1 + 1) * 17 + l1 + 0];
						double d3 = this.buffer[((j1 + 1) * 5 + k1 + 0) * 17 + l1 + 0];
						double d4 = this.buffer[((j1 + 1) * 5 + k1 + 1) * 17 + l1 + 0];
						double d5 = (this.buffer[((j1 + 0) * 5 + k1 + 0) * 17 + l1 + 1] - d1) * 0.125D;
						double d6 = (this.buffer[((j1 + 0) * 5 + k1 + 1) * 17 + l1 + 1] - d2) * 0.125D;
						double d7 = (this.buffer[((j1 + 1) * 5 + k1 + 0) * 17 + l1 + 1] - d3) * 0.125D;
						double d8 = (this.buffer[((j1 + 1) * 5 + k1 + 1) * 17 + l1 + 1] - d4) * 0.125D;
						for (int i2 = 0; i2 < 8; ++i2) {
							double d9 = 0.25D;
							double d10 = d1;
							double d11 = d2;
							double d12 = (d3 - d1) * 0.25D;
							double d13 = (d4 - d2) * 0.25D;
							for (int j2 = 0; j2 < 4; ++j2) {
								double d14 = 0.25D;
								double d15 = d10;
								double d16 = (d11 - d10) * 0.25D;
								for (int k2 = 0; k2 < 4; ++k2) {
									IBlockState iblockstate = null;
									if (l1 * 8 + i2 < j) {
										iblockstate = FLUID;
									}
									if (d15 > 0.0D) {
										iblockstate = STONE;
									}
									int l2 = j2 + j1 * 4;
									int i3 = i2 + l1 * 8;
									int j3 = k2 + k1 * 4;
									primer.setBlockState(l2, i3, j3, iblockstate);
									d15 += d16;
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

		private double[] getHeights(double[] p_185938_1_, int p_185938_2_, int p_185938_3_, int p_185938_4_, int p_185938_5_, int p_185938_6_,
				int p_185938_7_) {
			if (p_185938_1_ == null) {
				p_185938_1_ = new double[p_185938_5_ * p_185938_6_ * p_185938_7_];
			}
			net.minecraftforge.event.terraingen.ChunkGeneratorEvent.InitNoiseField event = new net.minecraftforge.event.terraingen.ChunkGeneratorEvent.InitNoiseField(
					this, p_185938_1_, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_);
			net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
			if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY)
				return event.getNoisefield();
			double d0 = 684.412D;
			double d1 = 2053.236D;
			this.unused = new NoiseGeneratorOctaves(this.random, 10).generateNoiseOctaves(this.unused, p_185938_2_, p_185938_3_, p_185938_4_,
					p_185938_5_, 1, p_185938_7_, 1.0D, 0.0D, 1.0D);
			this.dr = this.depthNoise.generateNoiseOctaves(this.dr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 100.0D, 0.0D,
					100.0D);
			this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_,
					8.555150000000001D, 34.2206D, 8.555150000000001D);
			this.ar = this.lperlinNoise1.generateNoiseOctaves(this.ar, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_,
					684.412D, 2053.236D, 684.412D);
			this.br = this.lperlinNoise2.generateNoiseOctaves(this.br, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_,
					684.412D, 2053.236D, 684.412D);
			int i = 0;
			double[] adouble = new double[p_185938_6_];
			for (int j = 0; j < p_185938_6_; ++j) {
				adouble[j] = Math.cos((double) j * Math.PI * 6.0D / (double) p_185938_6_) * 2.0D;
				double d2 = (double) j;
				if (j > p_185938_6_ / 2) {
					d2 = (double) (p_185938_6_ - 1 - j);
				}
				if (d2 < 4.0D) {
					d2 = 4.0D - d2;
					adouble[j] -= d2 * d2 * d2 * 10.0D;
				}
			}
			for (int l = 0; l < p_185938_5_; ++l) {
				for (int i1 = 0; i1 < p_185938_7_; ++i1) {
					double d3 = 0.0D;
					for (int k = 0; k < p_185938_6_; ++k) {
						double d4 = adouble[k];
						double d5 = this.ar[i] / 512.0D;
						double d6 = this.br[i] / 512.0D;
						double d7 = (this.pnr[i] / 10.0D + 1.0D) / 2.0D;
						double d8;
						if (d7 < 0.0D) {
							d8 = d5;
						} else if (d7 > 1.0D) {
							d8 = d6;
						} else {
							d8 = d5 + (d6 - d5) * d7;
						}
						d8 = d8 - d4;
						if (k > p_185938_6_ - 4) {
							double d9 = (double) ((float) (k - (p_185938_6_ - 4)) / 3.0F);
							d8 = d8 * (1.0D - d9) + -10.0D * d9;
						}
						if ((double) k < 0.0D) {
							double d10 = (0.0D - (double) k) / 4.0D;
							d10 = MathHelper.clamp(d10, 0.0D, 1.0D);
							d8 = d8 * (1.0D - d10) + -10.0D * d10;
						}
						p_185938_1_[i] = d8;
						++i;
					}
				}
			}
			return p_185938_1_;
		}

		public void buildSurfaces(int p_185937_1_, int p_185937_2_, ChunkPrimer primer) {
			if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, p_185937_1_, p_185937_2_, primer, this.world))
				return;
			int i = this.world.getSeaLevel() + 1;
			double d0 = 0.03125D;
			this.stoneNoise2 = this.secondaryStoneNoiseGen.generateNoiseOctaves(this.stoneNoise2, p_185937_1_ * 16, p_185937_2_ * 16, 0, 16, 16, 1,
					0.03125D, 0.03125D, 1.0D);
			this.stoneNoise3 = this.secondaryStoneNoiseGen.generateNoiseOctaves(this.stoneNoise3, p_185937_1_ * 16, 109, p_185937_2_ * 16, 16, 1, 16,
					0.03125D, 1.0D, 0.03125D);
			this.depthBuffer = this.depthNoiseGen.generateNoiseOctaves(this.depthBuffer, p_185937_1_ * 16, p_185937_2_ * 16, 0, 16, 16, 1, 0.0625D,
					0.0625D, 0.0625D);
			for (int j = 0; j < 16; ++j) {
				for (int k = 0; k < 16; ++k) {
					boolean flag = this.stoneNoise2[j + k * 16] + this.random.nextDouble() * 0.2D > 0.0D;
					boolean flag1 = this.stoneNoise3[j + k * 16] + this.random.nextDouble() * 0.2D > 0.0D;
					int l = (int) (this.depthBuffer[j + k * 16] / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
					int i1 = -1;
					IBlockState iblockstate = STONE;
					IBlockState iblockstate1 = STONE;
					for (int j1 = 127; j1 >= 0; --j1) {
						if (j1 < 127 - this.random.nextInt(5) && j1 > this.random.nextInt(5)) {
							IBlockState iblockstate2 = primer.getBlockState(k, j1, j);
							if (iblockstate2.getBlock() != null && iblockstate2.getMaterial() != Material.AIR) {
								if (iblockstate2.getBlock() == STONE.getBlock()) {
									if (i1 == -1) {
										if (l <= 0) {
											iblockstate = AIR;
											iblockstate1 = STONE;
										} else if (j1 >= i - 4 && j1 <= i + 1) {
											iblockstate = STONE;
											iblockstate1 = STONE;
											if (flag1) {
												iblockstate = STONE3;
												iblockstate1 = STONE;
											}
											if (flag) {
												iblockstate = STONE2;
												iblockstate1 = STONE2;
											}
										}
										if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
											iblockstate = FLUID;
										}
										i1 = l;
										if (j1 >= i - 1) {
											primer.setBlockState(k, j1, j, iblockstate);
										} else {
											primer.setBlockState(k, j1, j, iblockstate1);
										}
									} else if (i1 > 0) {
										--i1;
										primer.setBlockState(k, j1, j, iblockstate1);
									}
								}
							} else {
								i1 = -1;
							}
						} else {
							primer.setBlockState(k, j1, j, BEDROCK);
						}
					}
				}
			}
		}
	}

	public static class GenLayerBiomesCustom extends GenLayer {
		private Biome[] allowedBiomes = {Biome.REGISTRY.getObject(new ResourceLocation("magic_witchcraft:obsidianbiome")),};
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
