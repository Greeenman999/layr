package de.greenman999.layr.impl.renderer;

import de.greenman999.layr.api.renderer.RenderContext;
import net.minecraft.client.gui.GuiGraphics;
import org.jspecify.annotations.NonNull;

import java.awt.*;

public record RenderContextImpl(@NonNull GuiGraphics guiGraphics) implements RenderContext {

	@Override
	public void fillRect(int x, int y, int width, int height, Color color) {
		guiGraphics.fill(x, y, x + width, y + height, color.getRGB());
	}
}
