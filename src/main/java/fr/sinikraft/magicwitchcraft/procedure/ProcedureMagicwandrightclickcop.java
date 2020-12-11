package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.PotionEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.init.MobEffects;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.IProperty;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.item.ItemSaturationOrb;
import fr.sinikraft.magicwitchcraft.item.ItemHealthOrb;
import fr.sinikraft.magicwitchcraft.item.ItemFrozenOrb;
import fr.sinikraft.magicwitchcraft.item.ItemFireOrb;
import fr.sinikraft.magicwitchcraft.item.ItemBasicMagicWand;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMagicwandrightclickcop extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMagicwandrightclickcop(ElementsMagicWitchcraft instance) {
		super(instance, 191);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Magicwandrightclickcop!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Magicwandrightclickcop!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Magicwandrightclickcop!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Magicwandrightclickcop!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Magicwandrightclickcop!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double posX = 0;
		double posY = 0;
		double posZ = 0;
		if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(ItemFireOrb.block, (int) (1)).getItem())) {
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, (int) 100, (int) 0, (true), (true)));
			if ((!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))) {
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemFireOrb.block, (int) (1)).getItem(), -1, (int) 1, null);
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemBasicMagicWand.block, (int) (1)).getItem(), -1, (int) 1,
							null);
			}
			posX = (double) 1;
			posY = (double) -2;
			posZ = (double) -5;
			for (int index0 = 0; index0 < (int) (5); index0++) {
				for (int index1 = 0; index1 < (int) (5); index1++) {
					for (int index2 = 0; index2 < (int) (5); index2++) {
						if (((world.getBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ))))).getBlock() == Blocks.AIR
								.getDefaultState().getBlock())) {
							world.setBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ))),
									Blocks.FIRE.getDefaultState(), 3);
						}
						posZ = (double) ((posZ) + 1);
					}
					posZ = (double) ((posZ) - 5);
					posX = (double) ((posX) + 1);
				}
				posY = (double) ((posY) + 1);
				posX = (double) ((posX) - 5);
			}
			posY = (double) ((posY) - 5);
			posZ = (double) -5;
			posX = (double) -4;
			posY = (double) -2;
			for (int index3 = 0; index3 < (int) (5); index3++) {
				for (int index4 = 0; index4 < (int) (5); index4++) {
					for (int index5 = 0; index5 < (int) (5); index5++) {
						if (((world.getBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ))))).getBlock() == Blocks.AIR
								.getDefaultState().getBlock())) {
							world.setBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ))),
									Blocks.FIRE.getDefaultState(), 3);
						}
						posZ = (double) ((posZ) + 1);
					}
					posZ = (double) ((posZ) - 5);
					posX = (double) ((posX) + 1);
				}
				posY = (double) ((posY) + 1);
				posX = (double) ((posX) - 5);
			}
			posY = (double) -2;
			posZ = (double) 0;
			posX = (double) -4;
			for (int index6 = 0; index6 < (int) (5); index6++) {
				for (int index7 = 0; index7 < (int) (5); index7++) {
					for (int index8 = 0; index8 < (int) (5); index8++) {
						if (((world.getBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ))))).getBlock() == Blocks.AIR
								.getDefaultState().getBlock())) {
							world.setBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ))),
									Blocks.FIRE.getDefaultState(), 3);
						}
						posZ = (double) ((posZ) + 1);
					}
					posZ = (double) ((posZ) - 5);
					posX = (double) ((posX) + 1);
				}
				posY = (double) ((posY) + 1);
				posX = (double) ((posX) - 5);
			}
			posZ = (double) 0;
			posY = (double) -2;
			posX = (double) 1;
			for (int index9 = 0; index9 < (int) (5); index9++) {
				for (int index10 = 0; index10 < (int) (5); index10++) {
					for (int index11 = 0; index11 < (int) (5); index11++) {
						if (((world.getBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ))))).getBlock() == Blocks.AIR
								.getDefaultState().getBlock())) {
							world.setBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ))),
									Blocks.FIRE.getDefaultState(), 3);
						}
						posZ = (double) ((posZ) + 1);
					}
					posZ = (double) ((posZ) - 5);
					posX = (double) ((posX) + 1);
				}
				posY = (double) ((posY) + 1);
				posX = (double) ((posX) - 5);
			}
		}
		if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(ItemSaturationOrb.block, (int) (1)).getItem())) {
			if ((!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))) {
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSaturationOrb.block, (int) (1)).getItem(), -1, (int) 1,
							null);
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemBasicMagicWand.block, (int) (1)).getItem(), -1, (int) 1,
							null);
			}
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SATURATION, (int) 1, (int) 19, (false), (false)));
		}
		if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(ItemFrozenOrb.block, (int) (1)).getItem())) {
			if ((!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))) {
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemFrozenOrb.block, (int) (1)).getItem(), -1, (int) 1, null);
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemBasicMagicWand.block, (int) (1)).getItem(), -1, (int) 1,
							null);
			}
			posX = (double) -5;
			posY = (double) -5;
			posZ = (double) -5;
			for (int index12 = 0; index12 < (int) (10); index12++) {
				for (int index13 = 0; index13 < (int) (10); index13++) {
					for (int index14 = 0; index14 < (int) (10); index14++) {
						if ((!(((world.getBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ)))))
								.getBlock() == Blocks.AIR.getDefaultState().getBlock())
								|| ((world.getBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ)))))
										.getBlock() == Blocks.BEDROCK.getDefaultState().getBlock())))) {
							{
								BlockPos _bp = new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ)));
								IBlockState _bs = Blocks.PACKED_ICE.getDefaultState();
								IBlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getProperties().entrySet()) {
									IProperty _property = entry.getKey();
									if (_bs.getPropertyKeys().contains(_property))
										_bs = _bs.withProperty(_property, (Comparable) entry.getValue());
								}
								world.setBlockState(_bp, _bs, 3);
							}
						}
						posX = (double) ((posX) + 1);
					}
					posX = (double) -5;
					posY = (double) ((posY) + 1);
				}
				posY = (double) -5;
				posZ = (double) ((posZ) + 1);
			}
		}
		if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(ItemHealthOrb.block, (int) (1)).getItem())) {
			if ((!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))) {
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemHealthOrb.block, (int) (1)).getItem(), -1, (int) 1, null);
				if (entity instanceof EntityPlayer)
					((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemBasicMagicWand.block, (int) (1)).getItem(), -1, (int) 1,
							null);
			}
			if (entity instanceof EntityLivingBase)
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, (int) 1, (int) 19, (false), (false)));
		}
	}
}
