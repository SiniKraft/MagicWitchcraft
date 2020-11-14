package fr.sinikraft.magicwitchcraft.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.EntityItem;

import fr.sinikraft.magicwitchcraft.block.BlockMysteriousLog;
import fr.sinikraft.magicwitchcraft.block.BlockMagicalSapling;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ProcedureMagicalLeaves2UpdateTick extends ElementsMagicWitchcraft.ModElement {
	public ProcedureMagicalLeaves2UpdateTick(ElementsMagicWitchcraft instance) {
		super(instance, 98);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MagicalLeaves2UpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MagicalLeaves2UpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MagicalLeaves2UpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MagicalLeaves2UpdateTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == BlockMysteriousLog.block.getDefaultState()
				.getBlock()))) {
			if ((!((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == BlockMysteriousLog.block.getDefaultState()
					.getBlock()))) {
				if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == BlockMysteriousLog.block.getDefaultState()
						.getBlock()))) {
					if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == BlockMysteriousLog.block
							.getDefaultState().getBlock()))) {
						if ((!((world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) z))).getBlock() == BlockMysteriousLog.block
								.getDefaultState().getBlock()))) {
							if ((!((world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) z))).getBlock() == BlockMysteriousLog.block
									.getDefaultState().getBlock()))) {
								if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 2)))).getBlock() == BlockMysteriousLog.block
										.getDefaultState().getBlock()))) {
									if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2)))).getBlock() == BlockMysteriousLog.block
											.getDefaultState().getBlock()))) {
										if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2))))
												.getBlock() == BlockMysteriousLog.block.getDefaultState().getBlock()))) {
											if ((!((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z)))
													.getBlock() == BlockMysteriousLog.block.getDefaultState().getBlock()))) {
												if ((!((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z)))
														.getBlock() == BlockMysteriousLog.block.getDefaultState().getBlock()))) {
													if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1))))
															.getBlock() == BlockMysteriousLog.block.getDefaultState().getBlock()))) {
														if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1))))
																.getBlock() == BlockMysteriousLog.block.getDefaultState().getBlock()))) {
															if ((!((world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 1), (int) z)))
																	.getBlock() == BlockMysteriousLog.block.getDefaultState().getBlock()))) {
																if ((!((world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 1), (int) z)))
																		.getBlock() == BlockMysteriousLog.block.getDefaultState().getBlock()))) {
																	if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 2))))
																			.getBlock() == BlockMysteriousLog.block.getDefaultState().getBlock()))) {
																		if ((!((world
																				.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 2))))
																						.getBlock() == BlockMysteriousLog.block.getDefaultState()
																								.getBlock()))) {
																			if ((!((world.getBlockState(
																					new BlockPos((int) x, (int) (y - 1), (int) (z - 2))))
																							.getBlock() == BlockMysteriousLog.block.getDefaultState()
																									.getBlock()))) {
																				if ((!((world.getBlockState(
																						new BlockPos((int) (x + 1), (int) (y - 1), (int) (z + 1))))
																								.getBlock() == BlockMysteriousLog.block
																										.getDefaultState().getBlock()))) {
																					if ((!((world.getBlockState(new BlockPos((int) (x - 1),
																							(int) (y - 1), (int) (z - 1))))
																									.getBlock() == BlockMysteriousLog.block
																											.getDefaultState().getBlock()))) {
																						if ((!((world.getBlockState(new BlockPos((int) (x - 1),
																								(int) (y - 1), (int) (z + 1))))
																										.getBlock() == BlockMysteriousLog.block
																												.getDefaultState().getBlock()))) {
																							if ((!((world.getBlockState(new BlockPos((int) (x + 1),
																									(int) (y - 1), (int) (z - 1))))
																											.getBlock() == BlockMysteriousLog.block
																													.getDefaultState().getBlock()))) {
																								if ((!((world.getBlockState(new BlockPos(
																										(int) (x + 2), (int) (y - 1), (int) (z + 2))))
																												.getBlock() == BlockMysteriousLog.block
																														.getDefaultState()
																														.getBlock()))) {
																									if ((!((world.getBlockState(new BlockPos(
																											(int) (x - 2), (int) (y - 1),
																											(int) (z - 2))))
																													.getBlock() == BlockMysteriousLog.block
																															.getDefaultState()
																															.getBlock()))) {
																										if ((!((world.getBlockState(new BlockPos(
																												(int) (x - 2), (int) (y - 1),
																												(int) (z + 2))))
																														.getBlock() == BlockMysteriousLog.block
																																.getDefaultState()
																																.getBlock()))) {
																											if ((!((world.getBlockState(new BlockPos(
																													(int) (x + 2), (int) (y - 1),
																													(int) (z - 2))))
																															.getBlock() == BlockMysteriousLog.block
																																	.getDefaultState()
																																	.getBlock()))) {
																												if ((!((world.getBlockState(
																														new BlockPos((int) x,
																																(int) (y - 1),
																																(int) z)))
																																		.getBlock() == BlockMysteriousLog.block
																																				.getDefaultState()
																																				.getBlock()))) {
																													if ((!((world.getBlockState(
																															new BlockPos(
																																	(int) (x + 2),
																																	(int) (y - 1),
																																	(int) (z + 1))))
																																			.getBlock() == BlockMysteriousLog.block
																																					.getDefaultState()
																																					.getBlock()))) {
																														if ((!((world.getBlockState(
																																new BlockPos(
																																		(int) (x - 2),
																																		(int) (y - 1),
																																		(int) (z - 1))))
																																				.getBlock() == BlockMysteriousLog.block
																																						.getDefaultState()
																																						.getBlock()))) {
																															if ((!((world
																																	.getBlockState(
																																			new BlockPos(
																																					(int) (x - 1),
																																					(int) (y - 1),
																																					(int) (z + 2))))
																																							.getBlock() == BlockMysteriousLog.block
																																									.getDefaultState()
																																									.getBlock()))) {
																																if ((!((world
																																		.getBlockState(
																																				new BlockPos(
																																						(int) (x + 1),
																																						(int) (y - 1),
																																						(int) (z - 2))))
																																								.getBlock() == BlockMysteriousLog.block
																																										.getDefaultState()
																																										.getBlock()))) {
																																	if ((!((world
																																			.getBlockState(
																																					new BlockPos(
																																							(int) (x + 2),
																																							(int) y,
																																							(int) (z + 1))))
																																									.getBlock() == BlockMysteriousLog.block
																																											.getDefaultState()
																																											.getBlock()))) {
																																		if ((!((world
																																				.getBlockState(
																																						new BlockPos(
																																								(int) (x - 2),
																																								(int) y,
																																								(int) (z - 1))))
																																										.getBlock() == BlockMysteriousLog.block
																																												.getDefaultState()
																																												.getBlock()))) {
																																			if ((!((world
																																					.getBlockState(
																																							new BlockPos(
																																									(int) (x - 1),
																																									(int) y,
																																									(int) (z + 2))))
																																											.getBlock() == BlockMysteriousLog.block
																																													.getDefaultState()
																																													.getBlock()))) {
																																				if ((!((world
																																						.getBlockState(
																																								new BlockPos(
																																										(int) (x + 1),
																																										(int) y,
																																										(int) (z - 2))))
																																												.getBlock() == BlockMysteriousLog.block
																																														.getDefaultState()
																																														.getBlock()))) {
																																					if ((!((world
																																							.getBlockState(
																																									new BlockPos(
																																											(int) (x + 2),
																																											(int) (y - 1),
																																											(int) (z + 1))))
																																													.getBlock() == BlockMysteriousLog.block
																																															.getDefaultState()
																																															.getBlock()))) {
																																						if ((!((world
																																								.getBlockState(
																																										new BlockPos(
																																												(int) (x + 2),
																																												(int) (y - 1),
																																												(int) (z - 1))))
																																														.getBlock() == BlockMysteriousLog.block
																																																.getDefaultState()
																																																.getBlock()))) {
																																							if ((!((world
																																									.getBlockState(
																																											new BlockPos(
																																													(int) (x - 1),
																																													(int) (y - 1),
																																													(int) (z + 2))))
																																															.getBlock() == BlockMysteriousLog.block
																																																	.getDefaultState()
																																																	.getBlock()))) {
																																								if ((!((world
																																										.getBlockState(
																																												new BlockPos(
																																														(int) (x - 1),
																																														(int) (y - 1),
																																														(int) (z - 2))))
																																																.getBlock() == BlockMysteriousLog.block
																																																		.getDefaultState()
																																																		.getBlock()))) {
																																									if ((!((world
																																											.getBlockState(
																																													new BlockPos(
																																															(int) (x + 2),
																																															(int) y,
																																															(int) (z + 1))))
																																																	.getBlock() == BlockMysteriousLog.block
																																																			.getDefaultState()
																																																			.getBlock()))) {
																																										if ((!((world
																																												.getBlockState(
																																														new BlockPos(
																																																(int) (x + 2),
																																																(int) y,
																																																(int) (z - 1))))
																																																		.getBlock() == BlockMysteriousLog.block
																																																				.getDefaultState()
																																																				.getBlock()))) {
																																											if ((!((world
																																													.getBlockState(
																																															new BlockPos(
																																																	(int) (x - 1),
																																																	(int) y,
																																																	(int) (z + 2))))
																																																			.getBlock() == BlockMysteriousLog.block
																																																					.getDefaultState()
																																																					.getBlock()))) {
																																												if ((!((world
																																														.getBlockState(
																																																new BlockPos(
																																																		(int) (x + 1),
																																																		(int) y,
																																																		(int) (z - 2))))
																																																				.getBlock() == BlockMysteriousLog.block
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
																																																						.getBlock() == BlockMysteriousLog.block
																																																								.getDefaultState()
																																																								.getBlock()))) {
																																															if ((!((world
																																																	.getBlockState(
																																																			new BlockPos(
																																																					(int) (x - 1),
																																																					(int) y,
																																																					(int) (z + 2))))
																																																							.getBlock() == BlockMysteriousLog.block
																																																									.getDefaultState()
																																																									.getBlock()))) {
																																																if ((!((world
																																																		.getBlockState(
																																																				new BlockPos(
																																																						(int) (x - 2),
																																																						(int) y,
																																																						(int) (z + 1))))
																																																								.getBlock() == BlockMysteriousLog.block
																																																										.getDefaultState()
																																																										.getBlock()))) {
																																																	if ((!((world
																																																			.getBlockState(
																																																					new BlockPos(
																																																							(int) (x + 1),
																																																							(int) y,
																																																							(int) (z + 2))))
																																																									.getBlock() == BlockMysteriousLog.block
																																																											.getDefaultState()
																																																											.getBlock()))) {
																																																		world.setBlockToAir(
																																																				new BlockPos(
																																																						(int) x,
																																																						(int) y,
																																																						(int) z));
																																																		if ((Math
																																																				.random() <= 0.05)) {
																																																			if (!world.isRemote) {
																																																				EntityItem entityToSpawn = new EntityItem(
																																																						world,
																																																						x,
																																																						y,
																																																						z,
																																																						new ItemStack(
																																																								BlockMagicalSapling.block,
																																																								(int) (1)));
																																																				entityToSpawn
																																																						.setPickupDelay(
																																																								10);
																																																				world.spawnEntity(
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
