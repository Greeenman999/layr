package de.greenman999.layr.impl.gui.screen;


import de.greenman999.layr.api.gui.screen.LayrScreen;
import de.greenman999.layr.impl.renderer.RenderContextImpl;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.awt.*;

/**
 * Wraps a LayrScreen in a Minecraft Screen for Fabric.
 */
public class LayrScreenWrapper extends Screen {
	final LayrScreen layrScreen;

	public LayrScreenWrapper(LayrScreen layrScreen) {
		super(Component.empty()); // or custom title
		this.layrScreen = layrScreen;
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	public void render(@NonNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		super.render(guiGraphics, mouseX, mouseY, partialTick);
		layrScreen.renderContent(new RenderContextImpl(guiGraphics), mouseX, mouseY, partialTick);
	}

	@Override
	public void tick() {
		super.tick();
		layrScreen.onTick();
	}

	@Override
	public void removed() {
		super.removed();
		layrScreen.onClose();
	}

	@Override
	public boolean mouseClicked(@NonNull MouseButtonEvent event, boolean isDoubleClick) {
		return layrScreen.onMouseClick(event.x(), event.y(), event.button()) || super.mouseClicked(event, isDoubleClick);
	}

	@Override
	public boolean mouseReleased(@NonNull MouseButtonEvent event) {
		return layrScreen.onMouseRelease(event.x(), event.y(), event.button()) || super.mouseReleased(event);
	}

	@Override
	public boolean mouseDragged(@NonNull MouseButtonEvent event, double mouseX, double mouseY) {
		return layrScreen.onMouseDrag(event.x(), event.y(), event.button(), mouseX, mouseY) || super.mouseDragged(event, mouseX, mouseY);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
		return layrScreen.onMouseScroll(mouseX, mouseY, scrollX, scrollY) || super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		return layrScreen.onKeyPress(event.key(), event.scancode(), event.modifiers()) || super.keyPressed(event);
	}

	@Override
	public boolean keyReleased(@NonNull KeyEvent event) {
		return layrScreen.onKeyRelease(event.key(), event.scancode(), event.modifiers()) || super.keyReleased(event);
	}

	@Override
	public boolean charTyped(@NonNull CharacterEvent event) {
		return layrScreen.onCharTyped(event.codepoint(), event.modifiers()) || super.charTyped(event);
	}
}
