
package fr.sinikraft.magicwitchcraft.gui.overlay;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.Minecraft;

import fr.sinikraft.magicwitchcraft.procedures.TestDisplayOverlayIngameProcedure;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

import com.google.common.collect.ImmutableMap;

@MagicWitchcraftModElements.ModElement.Tag
public class TestOverlay extends MagicWitchcraftModElements.ModElement {
	public TestOverlay(MagicWitchcraftModElements instance) {
		super(instance, 223);
	}

	@Override
	public void initElements() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void eventHandler(RenderGameOverlayEvent event) {
		if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			int posX = (event.getWindow().getScaledWidth()) / 2;
			int posY = (event.getWindow().getScaledHeight()) / 2;
			PlayerEntity entity = Minecraft.getInstance().player;
			World world = entity.world;
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			if (TestDisplayOverlayIngameProcedure.executeProcedure(ImmutableMap.of("entity", entity))) {
				Minecraft.getInstance().fontRenderer.drawString("You're at " + x + " " + y + " " + z + "", posX + 109, posY + -119, -1);
			}
		}
	}
}
