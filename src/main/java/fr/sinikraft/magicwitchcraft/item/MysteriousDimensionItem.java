
package fr.sinikraft.magicwitchcraft.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ActionResultType;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;

import fr.sinikraft.magicwitchcraft.world.dimension.MysteriousDimensionDimension;
import fr.sinikraft.magicwitchcraft.itemgroup.MagicWitchCraftItemGroup;

public class MysteriousDimensionItem extends Item {
	@ObjectHolder("magic_witchcraft:mysterious_dimension")
	public static final Item block = null;
	public MysteriousDimensionItem() {
		super(new Item.Properties().group(MagicWitchCraftItemGroup.tab).maxDamage(64));
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity entity = context.getPlayer();
		BlockPos pos = context.getPos().offset(context.getFace());
		ItemStack itemstack = context.getItem();
		World world = context.getWorld();
		if (!entity.canPlayerEdit(pos, context.getFace(), itemstack)) {
			return ActionResultType.FAIL;
		} else {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			if (world.isAirBlock(pos) && true)
				MysteriousDimensionDimension.portal.portalSpawn(world, pos);
			itemstack.damageItem(1, entity, c -> c.sendBreakAnimation(context.getHand()));
			return ActionResultType.SUCCESS;
		}
	}
}
