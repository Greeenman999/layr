package de.greenman999.layr.api.gui.screen;

import de.greenman999.layr.api.renderer.RenderContext;

/**
 * Abstract screen that Veneer extends to create UI screens.
 * Platform adapters wrap this in a Minecraft Screen implementation.
 */
public abstract class LayrScreen {

	/**
	 * Called every frame to render the screen content.
	 * Use ctx to issue draw calls via Layr's rendering API.
	 *
	 * @param ctx Screen-specific render context with helpers
	 * @param mouseX Current mouse X position in screen coordinates
	 * @param mouseY Current mouse Y position in screen coordinates
	 * @param partialTick Partial tick time for interpolation
	 */
	public abstract void renderContent(RenderContext ctx, int mouseX, int mouseY, float partialTick);

	/**
	 * Called when screen is closed (before removal).
	 * Clean up resources here.
	 */
	public void onClose() {}

	/**
	 * Called on every tick (20 times per second in MC).
	 * Use for animations, input polling, etc.
	 */
	public void onTick() {}


	public boolean onMouseClick(double mouseX, double mouseY, int button) {
		return false;
	}

	public boolean onMouseRelease(double mouseX, double mouseY, int button) {
		return false;
	}

	public boolean onMouseDrag(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
		return false;
	}

	public boolean onMouseScroll(double mouseX, double mouseY, double scrollX, double scrollY) {
		return false;
	}

	public boolean onKeyPress(int keyCode, int scanCode, int modifiers) {
		return false;
	}

	public boolean onKeyRelease(int keyCode, int scanCode, int modifiers) {
		return false;
	}

	public boolean onCharTyped(int character, double modifiers) {
		return false;
	}

}
