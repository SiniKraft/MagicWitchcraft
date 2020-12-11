package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMysteriousDimensionOnPotionActiveTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMysteriousDimensionOnPotionActiveTick(ElementsMagicWitchcraft instance) {
		super(instance, 97);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MysteriousDimensionOnPotionActiveTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MysteriousDimensionOnPotionActiveTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MysteriousDimensionOnPotionActiveTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MysteriousDimensionOnPotionActiveTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MysteriousDimensionOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(Blocks.COBBLESTONE, (int) (1))) : false)
				|| ((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))) {
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				if ((!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))) {
					if (entity instanceof EntityPlayer)
						((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(Blocks.COBBLESTONE, (int) (1)).getItem(), -1, (int) 1,
								null);
				}
				world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), Blocks.COBBLESTONE.getDefaultState(), 3);
				world.playSound((EntityPlayer) null, x, (y - 1), z,
						(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("block.stone.place")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			}
		}
	}
}
