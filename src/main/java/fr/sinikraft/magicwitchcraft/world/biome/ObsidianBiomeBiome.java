
package fr.sinikraft.magicwitchcraft.world.biome;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;

import fr.sinikraft.magicwitchcraft.entity.DangerousWitchEntity;
import fr.sinikraft.magicwitchcraft.block.GreyObsidianBlock;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class ObsidianBiomeBiome extends MagicWitchcraftModElements.ModElement {
	@ObjectHolder("magic_witchcraft:obsidianbiome")
	public static final CustomBiome biome = null;
	public ObsidianBiomeBiome(MagicWitchcraftModElements instance) {
		super(instance, 259);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.NETHER);
	}
	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder().downfall(0f).depth(0.1f).scale(0.2f).temperature(0.5f).precipitation(Biome.RainType.NONE)
					.category(Biome.Category.NETHER).waterColor(-14329397).waterFogColor(-14329397)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(GreyObsidianBlock.block.getDefaultState(),
							GreyObsidianBlock.block.getDefaultState(), GreyObsidianBlock.block.getDefaultState())));
			setRegistryName("obsidianbiome");
			DefaultBiomeFeatures.addCarvers(this);
			DefaultBiomeFeatures.addMonsterRooms(this);
			DefaultBiomeFeatures.addStructures(this);
			DefaultBiomeFeatures.addOres(this);
			DefaultBiomeFeatures.addLakes(this);
			this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
			this.addStructure(Feature.PILLAGER_OUTPOST.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(DangerousWitchEntity.entity, 15, 1, 15));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CREEPER, 15, 1, 15));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SLIME, 15, 1, 15));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.GHAST, 15, 1, 15));
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getGrassColor(double posX, double posZ) {
			return -13261999;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getFoliageColor() {
			return -13261999;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getSkyColor() {
			return -5916161;
		}
	}
}
