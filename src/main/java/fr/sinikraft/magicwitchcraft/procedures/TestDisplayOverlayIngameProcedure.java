package fr.sinikraft.magicwitchcraft.procedures;

import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

import fr.sinikraft.magicwitchcraft.item.MagicalItem;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

@MagicWitchcraftModElements.ModElement.Tag
public class TestDisplayOverlayIngameProcedure extends MagicWitchcraftModElements.ModElement {
	public TestDisplayOverlayIngameProcedure(MagicWitchcraftModElements instance) {
		super(instance, 322);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure TestDisplayOverlayIngame!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((new Object() {
			public ItemStack getItemStack(int sltid, Entity entity) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					_retval.set(capability.getStackInSlot(sltid).copy());
				});
				return _retval.get();
			}
		}.getItemStack((int) (3), entity)).getItem() == new ItemStack(MagicalItem.helmet, (int) (1)).getItem())) {
			return (true);
		}
		return (false);
	}
}
