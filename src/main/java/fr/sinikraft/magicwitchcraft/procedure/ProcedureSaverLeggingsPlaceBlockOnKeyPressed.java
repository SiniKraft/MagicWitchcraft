package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import fr.sinikraft.magicwitchcraft.item.ItemSaver;
import fr.sinikraft.magicwitchcraft.block.BlockSaverBlockGreen;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureSaverLeggingsPlaceBlockOnKeyPressed extends ElementsMagicWitchcraft.ModElement {
	public ProcedureSaverLeggingsPlaceBlockOnKeyPressed(ElementsMagicWitchcraft instance) {
		super(instance, 242);
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
		if ((((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY)
				.getItem() == new ItemStack(ItemSaver.legs, (int) (1)).getItem())) {
			if (((((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY).hasTagCompound()
					? ((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY).getTagCompound()
							.getDouble("Cooldown")
					: -1) == 0)) {
				if (((((world.getBlockState(new BlockPos((int) x, (int) (y + 15), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())
						&& ((world.getBlockState(new BlockPos((int) x, (int) (y + 16), (int) z))).getBlock() == Blocks.AIR.getDefaultState()
								.getBlock()))
						&& ((world.getBlockState(new BlockPos((int) x, (int) (y + 14), (int) z))).getBlock() == Blocks.AIR.getDefaultState()
								.getBlock()))) {
					entity.fallDistance = (float) (0);
					world.setBlockState(new BlockPos((int) x, (int) (y + 14), (int) z), BlockSaverBlockGreen.block.getDefaultState(), 3);
					entity.setPositionAndUpdate((x + 0.5), (y + 15), (z + 0.5));
					{
						ItemStack _stack = ((entity instanceof EntityPlayer)
								? ((EntityPlayer) entity).inventory.armorInventory.get(1)
								: ItemStack.EMPTY);
						if (!_stack.hasTagCompound())
							_stack.setTagCompound(new NBTTagCompound());
						_stack.getTagCompound().setDouble("Cooldown", 600);
					}
					world.playSound((EntityPlayer) null, (entity.posX), (entity.posY), (entity.posZ),
							(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
									.getObject(new ResourceLocation("block.metal_pressureplate.click_on")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				}
			} else {
				if (entity instanceof EntityPlayer && !world.isRemote) {
					((EntityPlayer) entity).sendStatusMessage(new TextComponentString((("\u00A7cPlease wait ") + ""
							+ (Math.round(
									((((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(1) : ItemStack.EMPTY)
											.hasTagCompound()
													? ((entity instanceof EntityPlayer)
															? ((EntityPlayer) entity).inventory.armorInventory.get(1)
															: ItemStack.EMPTY).getTagCompound().getDouble("Cooldown")
													: -1)
											/ 20)))
							+ "" + (" \u00A7cseconds before using this item !"))), (false));
				}
			}
		}
	}
}
