
package fr.sinikraft.magicwitchcraft.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.BucketItem;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.Map;
import java.util.HashMap;

import fr.sinikraft.magicwitchcraft.procedures.TrollWaterMobplayerCollidesBlockProcedure;
import fr.sinikraft.magicwitchcraft.itemgroup.MagicWitchCraftItemGroup;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class TrollWaterBlock extends MagicWitchcraftModElements.ModElement {
	@ObjectHolder("magic_witchcraft:trollwater")
	public static final FlowingFluidBlock block = null;
	@ObjectHolder("magic_witchcraft:trollwater_bucket")
	public static final Item bucket = null;
	public static FlowingFluid flowing = null;
	public static FlowingFluid still = null;
	private ForgeFlowingFluid.Properties fluidproperties = null;
	public TrollWaterBlock(MagicWitchcraftModElements instance) {
		super(instance, 48);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerFluids(RegistryEvent.Register<Fluid> event) {
		event.getRegistry().register(still);
		event.getRegistry().register(flowing);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(still, RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(flowing, RenderType.getTranslucent());
	}

	@Override
	public void initElements() {
		fluidproperties = new ForgeFlowingFluid.Properties(() -> still, () -> flowing, FluidAttributes
				.builder(new ResourceLocation("magic_witchcraft:blocks/troll_water"), new ResourceLocation("magic_witchcraft:blocks/troll_water"))
				.luminosity(0).density(10000).viscosity(100).gaseous()).bucket(() -> bucket).block(() -> block);
		still = (FlowingFluid) new ForgeFlowingFluid.Source(fluidproperties).setRegistryName("trollwater");
		flowing = (FlowingFluid) new ForgeFlowingFluid.Flowing(fluidproperties).setRegistryName("trollwater_flowing");
		elements.blocks.add(() -> new FlowingFluidBlock(still, Block.Properties.create(Material.WATER)) {
			@Override
			public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
				super.onEntityCollision(state, world, pos, entity);
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					TrollWaterMobplayerCollidesBlockProcedure.executeProcedure($_dependencies);
				}
			}
		}.setRegistryName("trollwater"));
		elements.items.add(
				() -> new BucketItem(still, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(MagicWitchCraftItemGroup.tab))
						.setRegistryName("trollwater_bucket"));
	}
}
