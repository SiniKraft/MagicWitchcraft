package fr.sinikraft.magicwitchcraft.procedures;

<<<<<<< HEAD
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;
import java.util.Collections;

import fr.sinikraft.magicwitchcraft.item.SaverItem;
import fr.sinikraft.magicwitchcraft.block.SaverBlockGreenBlock;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftMod;

@MagicWitchcraftModElements.ModElement.Tag
public class SaverLeggingsPlaceBlockOnKeyPressedProcedure extends MagicWitchcraftModElements.ModElement {
	public SaverLeggingsPlaceBlockOnKeyPressedProcedure(MagicWitchcraftModElements instance) {
		super(instance, 242);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency entity for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency x for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency y for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency z for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MagicWitchcraftMod.LOGGER.warn("Failed to load dependency world for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof LivingEntity)
				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
				: ItemStack.EMPTY).getItem() == new ItemStack(SaverItem.legs, (int) (1)).getItem())) {
			if (((((entity instanceof LivingEntity)
					? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
					: ItemStack.EMPTY).getOrCreateTag().getDouble("Cooldown")) == 0)) {
				if (((((world.getBlockState(new BlockPos((int) x, (int) (y + 15), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())
						&& ((world.getBlockState(new BlockPos((int) x, (int) (y + 16), (int) z))).getBlock() == Blocks.AIR.getDefaultState()
								.getBlock()))
						&& ((world.getBlockState(new BlockPos((int) x, (int) (y + 14), (int) z))).getBlock() == Blocks.AIR.getDefaultState()
								.getBlock()))) {
					entity.fallDistance = (float) (0);
					world.setBlockState(new BlockPos((int) x, (int) (y + 14), (int) z), SaverBlockGreenBlock.block.getDefaultState(), 3);
					{
						Entity _ent = entity;
						_ent.setPositionAndUpdate((x + 0.5), (y + 15), (z + 0.5));
						if (_ent instanceof ServerPlayerEntity) {
							((ServerPlayerEntity) _ent).connection.setPlayerLocation((x + 0.5), (y + 15), (z + 0.5), _ent.rotationYaw,
									_ent.rotationPitch, Collections.emptySet());
						}
					}
					((entity instanceof LivingEntity)
							? ((LivingEntity) entity)
									.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
							: ItemStack.EMPTY).getOrCreateTag().putDouble("Cooldown", 600);
					if (world instanceof World && !world.isRemote()) {
						((World) world).playSound(null, new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("block.metal_pressure_plate.click_on")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1);
					} else {
						((World) world).playSound((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("block.metal_pressure_plate.click_on")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
					}
				}
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((("\u00A7cPlease wait ") + ""
							+ (Math.round(((((entity instanceof LivingEntity)
									? ((LivingEntity) entity)
											.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
									: ItemStack.EMPTY).getOrCreateTag().getDouble("Cooldown")) / 20)))
							+ "" + (" \u00A7cseconds before using this item !"))), (false));
				}
			}
=======
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import fr.sinikraft.magicwitchcraft.item.SaverItem;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class SaverLeggingsPlaceBlockOnKeyPressedProcedure extends MagicWitchcraftModElements.ModElement {
	public SaverLeggingsPlaceBlockOnKeyPressedProcedure(MagicWitchcraftModElements instance) {
		super(instance, 242);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure SaverLeggingsPlaceBlockOnKeyPressed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get((int) 1) : ItemStack.EMPTY)
				.getItem() == new ItemStack(SaverItem.legs, (int) (1)).getItem())) {
			entity.fallDistance = (float) (0);
>>>>>>> branch '1.15.2-master' of https://github.com/SiniKraft/MagicWitchcraft
		}
	}
}
