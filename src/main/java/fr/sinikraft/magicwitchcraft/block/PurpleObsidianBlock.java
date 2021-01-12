
package fr.sinikraft.magicwitchcraft.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.Explosion;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Direction;
import net.minecraft.state.StateContainer;
import net.minecraft.state.DirectionProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockItem;
import net.minecraft.fluid.IFluidState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.Random;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

import fr.sinikraft.magicwitchcraft.world.dimension.ObsidianDimensionDimension;
import fr.sinikraft.magicwitchcraft.procedures.PurpleObsidianBlockDestroyedByPlayerProcedure;
import fr.sinikraft.magicwitchcraft.procedures.PurpleObsidianBlockDestroyedByExplosionProcedure;
import fr.sinikraft.magicwitchcraft.itemgroup.MagicWitchCraftItemGroup;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class PurpleObsidianBlock extends MagicWitchcraftModElements.ModElement {
	@ObjectHolder("magic_witchcraft:purpleobsidian")
	public static final Block block = null;
	public PurpleObsidianBlock(MagicWitchcraftModElements instance) {
		super(instance, 73);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(MagicWitchCraftItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public static final DirectionProperty FACING = DirectionalBlock.FACING;
		public CustomBlock() {
			super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(100f, 3600f).lightValue(0).harvestLevel(3)
					.harvestTool(ToolType.PICKAXE));
			this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
			setRegistryName("purpleobsidian");
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void addInformation(ItemStack itemstack, IBlockReader world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("The best obsidian."));
			list.add(new StringTextComponent("An all-in-one."));
		}

		@Override
		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(FACING);
		}

		public BlockState rotate(BlockState state, Rotation rot) {
			return state.with(FACING, rot.rotate(state.get(FACING)));
		}

		public BlockState mirror(BlockState state, Mirror mirrorIn) {
			return state.rotate(mirrorIn.toRotation(state.get(FACING)));
		}

		@Override
		public BlockState getStateForPlacement(BlockItemUseContext context) {
			;
			return this.getDefaultState().with(FACING, context.getNearestLookingDirection().getOpposite());
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 0));
		}

		@Override
		public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity entity, boolean willHarvest, IFluidState fluid) {
			boolean retval = super.removedByPlayer(state, world, pos, entity, willHarvest, fluid);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				PurpleObsidianBlockDestroyedByPlayerProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		public void onExplosionDestroy(World world, BlockPos pos, Explosion e) {
			super.onExplosionDestroy(world, pos, e);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				PurpleObsidianBlockDestroyedByExplosionProcedure.executeProcedure($_dependencies);
			}
		}
	}
	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("magic_witchcraft:obsidianbiome")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, new OreFeature(OreFeatureConfig::deserialize) {
				@Override
				public boolean place(IWorld world, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
					DimensionType dimensionType = world.getDimension().getType();
					boolean dimensionCriteria = false;
					if (dimensionType == ObsidianDimensionDimension.type)
						dimensionCriteria = true;
					if (!dimensionCriteria)
						return false;
					return super.place(world, generator, rand, pos, config);
				}
			}.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("purpleobsidian", "purpleobsidian", blockAt -> {
				boolean blockCriteria = false;
				if (blockAt.getBlock() == GreyObsidianBlock.block.getDefaultState().getBlock())
					blockCriteria = true;
				return blockCriteria;
			}), block.getDefaultState(), 3)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 0, 0, 256))));
		}
	}
}
