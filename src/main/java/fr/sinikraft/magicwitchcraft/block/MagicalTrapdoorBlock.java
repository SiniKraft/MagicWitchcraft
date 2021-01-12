
package fr.sinikraft.magicwitchcraft.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Collections;

import fr.sinikraft.magicwitchcraft.itemgroup.MagicWitchCraftItemGroup;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

// ADDED IMPORTS
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;

@MagicWitchcraftModElements.ModElement.Tag
public class MagicalTrapdoorBlock extends MagicWitchcraftModElements.ModElement {
	@ObjectHolder("magic_witchcraft:magicaltrapdoor")
	public static final Block block = null;
	public MagicalTrapdoorBlock(MagicWitchcraftModElements instance) {
		super(instance, 36);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(MagicWitchCraftItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getTranslucent());
	}
	public static class CustomBlock extends TrapDoorBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.SPONGE).sound(SoundType.SLIME).hardnessAndResistance(1.5f, 40f).lightValue(0)
					.slipperiness(0.7999999999999999f).notSolid());
			setRegistryName("magicaltrapdoor");
		}
	/**
    * Block's chance to react to a living entity falling on it.
    */
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
       if (entityIn.isSuppressingBounce()) {
          super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
       } else {
          entityIn.onLivingFall(fallDistance, 0.0F);
       }

    }


    /**
    * Called when an Entity lands on this Block. This method *must* update motionY because the entity will not do that
    * on its own
    */
   public void onLanded(IBlockReader worldIn, Entity entityIn) {
      if (entityIn.isSuppressingBounce()) {
         super.onLanded(worldIn, entityIn);
      } else {
         this.func_226946_a_(entityIn);
      }

   }

   private void func_226946_a_(Entity p_226946_1_) {
      Vec3d vec3d = p_226946_1_.getMotion();
      if (vec3d.y < 0.0D) {
         double d0 = p_226946_1_ instanceof LivingEntity ? 1.0D : 0.8D;
         p_226946_1_.setMotion(vec3d.x, -vec3d.y * d0, vec3d.z);
      }

   }

   /**
    * Called when the given entity walks on this Block
    */
   public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
      double d0 = Math.abs(entityIn.getMotion().y);
      if (d0 < 0.1D && !entityIn.isSteppingCarefully()) {
         double d1 = 0.4D + d0 * 0.2D;
         entityIn.setMotion(entityIn.getMotion().mul(d1, 1.0D, d1));
      }

      super.onEntityWalk(worldIn, pos, entityIn);
   }
		@Override
		public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return false;
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
