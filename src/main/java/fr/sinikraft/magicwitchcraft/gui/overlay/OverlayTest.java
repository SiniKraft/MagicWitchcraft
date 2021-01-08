
package fr.sinikraft.magicwitchcraft.gui.overlay;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.Minecraft;

import fr.sinikraft.magicwitchcraft.item.ItemMagical;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class OverlayTest extends ElementsMagicWitchcraft.ModElement {
	public OverlayTest(ElementsMagicWitchcraft instance) {
		super(instance, 208);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new GUIRenderEventClass());
	}
	public static class GUIRenderEventClass {
		@SubscribeEvent(priority = EventPriority.NORMAL)
		@SideOnly(Side.CLIENT)
		public void eventHandler(RenderGameOverlayEvent event) {
			if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
				int posX = (event.getResolution().getScaledWidth()) / 2;
				int posY = (event.getResolution().getScaledHeight()) / 2;
				EntityPlayer entity = Minecraft.getMinecraft().player;
				World world = entity.world;
				int x = (int) Math.round(entity.posX);
				int y = (int) Math.round(entity.posY);
				int z = (int) Math.round(entity.posZ);
				if ((((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).inventory.armorInventory.get(3) : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemMagical.helmet, (int) (1)).getItem())) {
					Minecraft.getMinecraft().fontRenderer.drawString("You're at " + x + " " + y + " " + z + "", posX + 109, posY + -119, -1);
				}
			}
		}
	}
}
