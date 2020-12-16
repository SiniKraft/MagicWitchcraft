package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.block.Blocks;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.block.MysteriousLogBlock;
import fr.sinikraft.magicwitchcraft.block.MagicalSaplingBlock;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class MagicalLeaves2UpdateTickProcedure extends MagicWitchcraftModElements.ModElement {
	public MagicalLeaves2UpdateTickProcedure(MagicWitchcraftModElements instance) {
		super(instance, 141);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure MagicalLeaves2UpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure MagicalLeaves2UpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure MagicalLeaves2UpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure MagicalLeaves2UpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == MysteriousLogBlock.block.getDefaultState()
				.getBlock()))) {
			if ((!((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == MysteriousLogBlock.block.getDefaultState()
					.getBlock()))) {
				if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == MysteriousLogBlock.block.getDefaultState()
						.getBlock()))) {
					if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == MysteriousLogBlock.block
							.getDefaultState().getBlock()))) {
						if ((!((world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) z))).getBlock() == MysteriousLogBlock.block
								.getDefaultState().getBlock()))) {
							if ((!((world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) z))).getBlock() == MysteriousLogBlock.block
									.getDefaultState().getBlock()))) {
								if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 2)))).getBlock() == MysteriousLogBlock.block
										.getDefaultState().getBlock()))) {
									if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2)))).getBlock() == MysteriousLogBlock.block
											.getDefaultState().getBlock()))) {
										if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2))))
												.getBlock() == MysteriousLogBlock.block.getDefaultState().getBlock()))) {
											if ((!((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z)))
													.getBlock() == MysteriousLogBlock.block.getDefaultState().getBlock()))) {
												if ((!((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z)))
														.getBlock() == MysteriousLogBlock.block.getDefaultState().getBlock()))) {
													if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1))))
															.getBlock() == MysteriousLogBlock.block.getDefaultState().getBlock()))) {
														if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1))))
																.getBlock() == MysteriousLogBlock.block.getDefaultState().getBlock()))) {
															if ((!((world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 1), (int) z)))
																	.getBlock() == MysteriousLogBlock.block.getDefaultState().getBlock()))) {
																if ((!((world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 1), (int) z)))
																		.getBlock() == MysteriousLogBlock.block.getDefaultState().getBlock()))) {
																	if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 2))))
																			.getBlock() == MysteriousLogBlock.block.getDefaultState().getBlock()))) {
																		if ((!((world
																				.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 2))))
																						.getBlock() == MysteriousLogBlock.block.getDefaultState()
																								.getBlock()))) {
																			if ((!((world.getBlockState(
																					new BlockPos((int) x, (int) (y - 1), (int) (z - 2))))
																							.getBlock() == MysteriousLogBlock.block.getDefaultState()
																									.getBlock()))) {
																				if ((!((world.getBlockState(
																						new BlockPos((int) (x + 1), (int) (y - 1), (int) (z + 1))))
																								.getBlock() == MysteriousLogBlock.block
																										.getDefaultState().getBlock()))) {
																					if ((!((world.getBlockState(new BlockPos((int) (x - 1),
																							(int) (y - 1), (int) (z - 1))))
																									.getBlock() == MysteriousLogBlock.block
																											.getDefaultState().getBlock()))) {
																						if ((!((world.getBlockState(new BlockPos((int) (x - 1),
																								(int) (y - 1), (int) (z + 1))))
																										.getBlock() == MysteriousLogBlock.block
																												.getDefaultState().getBlock()))) {
																							if ((!((world.getBlockState(new BlockPos((int) (x + 1),
																									(int) (y - 1), (int) (z - 1))))
																											.getBlock() == MysteriousLogBlock.block
																													.getDefaultState().getBlock()))) {
																								if ((!((world.getBlockState(new BlockPos(
																										(int) (x + 2), (int) (y - 1), (int) (z + 2))))
																												.getBlock() == MysteriousLogBlock.block
																														.getDefaultState()
																														.getBlock()))) {
																									if ((!((world.getBlockState(new BlockPos(
																											(int) (x - 2), (int) (y - 1),
																											(int) (z - 2))))
																													.getBlock() == MysteriousLogBlock.block
																															.getDefaultState()
																															.getBlock()))) {
																										if ((!((world.getBlockState(new BlockPos(
																												(int) (x - 2), (int) (y - 1),
																												(int) (z + 2))))
																														.getBlock() == MysteriousLogBlock.block
																																.getDefaultState()
																																.getBlock()))) {
																											if ((!((world.getBlockState(new BlockPos(
																													(int) (x + 2), (int) (y - 1),
																													(int) (z - 2))))
																															.getBlock() == MysteriousLogBlock.block
																																	.getDefaultState()
																																	.getBlock()))) {
																												if ((!((world.getBlockState(
																														new BlockPos((int) x,
																																(int) (y - 1),
																																(int) z)))
																																		.getBlock() == MysteriousLogBlock.block
																																				.getDefaultState()
																																				.getBlock()))) {
																													if ((!((world.getBlockState(
																															new BlockPos(
																																	(int) (x + 2),
																																	(int) (y - 1),
																																	(int) (z + 1))))
																																			.getBlock() == MysteriousLogBlock.block
																																					.getDefaultState()
																																					.getBlock()))) {
																														if ((!((world.getBlockState(
																																new BlockPos(
																																		(int) (x - 2),
																																		(int) (y - 1),
																																		(int) (z - 1))))
																																				.getBlock() == MysteriousLogBlock.block
																																						.getDefaultState()
																																						.getBlock()))) {
																															if ((!((world
																																	.getBlockState(
																																			new BlockPos(
																																					(int) (x - 1),
																																					(int) (y - 1),
																																					(int) (z + 2))))
																																							.getBlock() == MysteriousLogBlock.block
																																									.getDefaultState()
																																									.getBlock()))) {
																																if ((!((world
																																		.getBlockState(
																																				new BlockPos(
																																						(int) (x + 1),
																																						(int) (y - 1),
																																						(int) (z - 2))))
																																								.getBlock() == MysteriousLogBlock.block
																																										.getDefaultState()
																																										.getBlock()))) {
																																	if ((!((world
																																			.getBlockState(
																																					new BlockPos(
																																							(int) (x + 2),
																																							(int) y,
																																							(int) (z + 1))))
																																									.getBlock() == MysteriousLogBlock.block
																																											.getDefaultState()
																																											.getBlock()))) {
																																		if ((!((world
																																				.getBlockState(
																																						new BlockPos(
																																								(int) (x - 2),
																																								(int) y,
																																								(int) (z - 1))))
																																										.getBlock() == MysteriousLogBlock.block
																																												.getDefaultState()
																																												.getBlock()))) {
																																			if ((!((world
																																					.getBlockState(
																																							new BlockPos(
																																									(int) (x - 1),
																																									(int) y,
																																									(int) (z + 2))))
																																											.getBlock() == MysteriousLogBlock.block
																																													.getDefaultState()
																																													.getBlock()))) {
																																				if ((!((world
																																						.getBlockState(
																																								new BlockPos(
																																										(int) (x + 1),
																																										(int) y,
																																										(int) (z - 2))))
																																												.getBlock() == MysteriousLogBlock.block
																																														.getDefaultState()
																																														.getBlock()))) {
																																					if ((!((world
																																							.getBlockState(
																																									new BlockPos(
																																											(int) (x + 2),
																																											(int) (y - 1),
																																											(int) (z + 1))))
																																													.getBlock() == MysteriousLogBlock.block
																																															.getDefaultState()
																																															.getBlock()))) {
																																						if ((!((world
																																								.getBlockState(
																																										new BlockPos(
																																												(int) (x + 2),
																																												(int) (y - 1),
																																												(int) (z - 1))))
																																														.getBlock() == MysteriousLogBlock.block
																																																.getDefaultState()
																																																.getBlock()))) {
																																							if ((!((world
																																									.getBlockState(
																																											new BlockPos(
																																													(int) (x - 1),
																																													(int) (y - 1),
																																													(int) (z + 2))))
																																															.getBlock() == MysteriousLogBlock.block
																																																	.getDefaultState()
																																																	.getBlock()))) {
																																								if ((!((world
																																										.getBlockState(
																																												new BlockPos(
																																														(int) (x - 1),
																																														(int) (y - 1),
																																														(int) (z - 2))))
																																																.getBlock() == MysteriousLogBlock.block
																																																		.getDefaultState()
																																																		.getBlock()))) {
																																									if ((!((world
																																											.getBlockState(
																																													new BlockPos(
																																															(int) (x + 2),
																																															(int) y,
																																															(int) (z + 1))))
																																																	.getBlock() == MysteriousLogBlock.block
																																																			.getDefaultState()
																																																			.getBlock()))) {
																																										if ((!((world
																																												.getBlockState(
																																														new BlockPos(
																																																(int) (x + 2),
																																																(int) y,
																																																(int) (z - 1))))
																																																		.getBlock() == MysteriousLogBlock.block
																																																				.getDefaultState()
																																																				.getBlock()))) {
																																											if ((!((world
																																													.getBlockState(
																																															new BlockPos(
																																																	(int) (x - 1),
																																																	(int) y,
																																																	(int) (z + 2))))
																																																			.getBlock() == MysteriousLogBlock.block
																																																					.getDefaultState()
																																																					.getBlock()))) {
																																												if ((!((world
																																														.getBlockState(
																																																new BlockPos(
																																																		(int) (x + 1),
																																																		(int) y,
																																																		(int) (z - 2))))
																																																				.getBlock() == MysteriousLogBlock.block
																																																						.getDefaultState()
																																																						.getBlock()))) {
																																													if ((Math
																																															.random() <= 0.3)) {
																																														if ((!((world
																																																.getBlockState(
																																																		new BlockPos(
																																																				(int) (x - 2),
																																																				(int) y,
																																																				(int) (z + 1))))
																																																						.getBlock() == MysteriousLogBlock.block
																																																								.getDefaultState()
																																																								.getBlock()))) {
																																															if ((!((world
																																																	.getBlockState(
																																																			new BlockPos(
																																																					(int) (x - 1),
																																																					(int) y,
																																																					(int) (z + 2))))
																																																							.getBlock() == MysteriousLogBlock.block
																																																									.getDefaultState()
																																																									.getBlock()))) {
																																																if ((!((world
																																																		.getBlockState(
																																																				new BlockPos(
																																																						(int) (x - 2),
																																																						(int) y,
																																																						(int) (z + 1))))
																																																								.getBlock() == MysteriousLogBlock.block
																																																										.getDefaultState()
																																																										.getBlock()))) {
																																																	if ((!((world
																																																			.getBlockState(
																																																					new BlockPos(
																																																							(int) (x + 1),
																																																							(int) y,
																																																							(int) (z + 2))))
																																																									.getBlock() == MysteriousLogBlock.block
																																																											.getDefaultState()
																																																											.getBlock()))) {
																																																		world.setBlockState(
																																																				new BlockPos(
																																																						(int) x,
																																																						(int) y,
																																																						(int) z),
																																																				Blocks.AIR
																																																						.getDefaultState(),
																																																				3);
																																																		if ((Math
																																																				.random() <= 0.05)) {
																																																			if (!world
																																																					.getWorld().isRemote) {
																																																				ItemEntity entityToSpawn = new ItemEntity(
																																																						world.getWorld(),
																																																						x,
																																																						y,
																																																						z,
																																																						new ItemStack(
																																																								MagicalSaplingBlock.block,
																																																								(int) (1)));
																																																				entityToSpawn
																																																						.setPickupDelay(
																																																								(int) 10);
																																																				world.addEntity(
																																																						entityToSpawn);
																																																			}
																																																		}
																																																	}
																																																}
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
