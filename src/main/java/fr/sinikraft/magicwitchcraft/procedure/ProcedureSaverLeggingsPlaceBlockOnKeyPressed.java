package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.item.ItemSaver;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureSaverLeggingsPlaceBlockOnKeyPressed extends ElementsMagicWitchcraft.ModElement {
	public ProcedureSaverLeggingsPlaceBlockOnKeyPressed(ElementsMagicWitchcraft instance) {
		super(instance, 230);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY)
				.getItem() == new ItemStack(ItemSaver.legs, (int) (1)).getItem())
				&& ((entity.getEntityData().getDouble("SaverLeggingsCooldown")) == 0))) {
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				if (((entity instanceof EntityPlayer)
						? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(Blocks.COBBLESTONE, (int) (1)))
						: false)) {
					if (entity instanceof EntityPlayer)
						((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(Blocks.COBBLESTONE, (int) (1)).getItem(), -1, (int) 1,
								null);
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), Blocks.COBBLESTONE.getDefaultState(), 3);
					entity.fallDistance = (float) (0);
					entity.getEntityData().setDouble("SaverLeggingsCooldown", -100);
				} else if (((entity instanceof EntityPlayer)
						? ((EntityPlayer) entity).inventory.hasItemStack(new ItemStack(Blocks.DIRT, (int) (1), 0))
						: false)) {
					if (entity instanceof EntityPlayer)
						((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(Blocks.DIRT, (int) (1), 0).getItem(), 0, (int) 1, null);
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), Blocks.DIRT.getStateFromMeta(0), 3);
					entity.fallDistance = (float) (0);
					entity.getEntityData().setDouble("SaverLeggingsCooldown", -100);
				}
			}
		}
	}
}
