package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.state.Property;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.item.SaturationOrbItem;
import fr.sinikraft.magicwitchcraft.item.HealthOrbItem;
import fr.sinikraft.magicwitchcraft.item.FrozenOrbItem;
import fr.sinikraft.magicwitchcraft.item.FireOrbItem;
import fr.sinikraft.magicwitchcraft.item.BasicMagicWandItem;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class MagicWandRightClickedInAirProcedure extends MagicWitchcraftModElements.ModElement {
	public MagicWandRightClickedInAirProcedure(MagicWitchcraftModElements instance) {
		super(instance, 88);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency entity for procedure MagicWandRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency x for procedure MagicWandRightClickedInAir!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency y for procedure MagicWandRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency z for procedure MagicWandRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency world for procedure MagicWandRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double posX = 0;
		double posY = 0;
		double posZ = 0;
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(FireOrbItem.block, (int) (1)).getItem())) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, (int) 100, (int) 0, (true), (true)));
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(FireOrbItem.block, (int) (1));
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(BasicMagicWandItem.block, (int) (1));
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
			posX = (double) 1;
			posY = (double) (-2);
			posZ = (double) (-5);
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
			posZ = (double) (-5);
			posX = (double) (-4);
			posY = (double) (-2);
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
			posY = (double) (-2);
			posZ = (double) 0;
			posX = (double) (-4);
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
			posY = (double) (-2);
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
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(SaturationOrbItem.block, (int) (1)).getItem())) {
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(SaturationOrbItem.block, (int) (1));
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(BasicMagicWandItem.block, (int) (1));
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SATURATION, (int) 1, (int) 19, (false), (false)));
		}
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(FrozenOrbItem.block, (int) (1)).getItem())) {
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(FrozenOrbItem.block, (int) (1));
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(BasicMagicWandItem.block, (int) (1));
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
			posX = (double) (-5);
			posY = (double) (-5);
			posZ = (double) (-5);
			for (int index12 = 0; index12 < (int) (10); index12++) {
				for (int index13 = 0; index13 < (int) (10); index13++) {
					for (int index14 = 0; index14 < (int) (10); index14++) {
						if (((((world.getBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ)))))
								.getBlock() == Blocks.GRASS_BLOCK.getDefaultState().getBlock())
								|| ((world.getBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ)))))
										.getBlock() == Blocks.DIRT.getDefaultState().getBlock()))
								|| (((world.getBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ)))))
										.getBlock() == Blocks.STONE.getDefaultState().getBlock())
										|| ((world.getBlockState(new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ)))))
												.getBlock() == Blocks.SAND.getDefaultState().getBlock())))) {
							{
								BlockPos _bp = new BlockPos((int) (x + (posX)), (int) (y + (posY)), (int) (z + (posZ)));
								BlockState _bs = Blocks.PACKED_ICE.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								world.setBlockState(_bp, _bs, 3);
							}
						}
						posX = (double) ((posX) + 1);
					}
					posX = (double) (-5);
					posY = (double) ((posY) + 1);
				}
				posY = (double) (-5);
				posZ = (double) ((posZ) + 1);
			}
		}
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(HealthOrbItem.block, (int) (1)).getItem())) {
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(HealthOrbItem.block, (int) (1));
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(BasicMagicWandItem.block, (int) (1));
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, (int) 1, (int) 19, (false), (false)));
		}
	}
}
