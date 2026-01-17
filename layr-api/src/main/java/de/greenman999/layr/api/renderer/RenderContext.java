package de.greenman999.layr.api.renderer;

import org.joml.Vector4f;

import java.awt.*;

/**
 * Short-lived per-frame context used to issue drawing operations.
 * Not thread-safe; must be used on render thread.
 */
public interface RenderContext {

	/**
	 * Draw a filled rectangle with current transform.
	 */
	void fillRect(int x, int y, int width, int height, Color color);

	/**
	 * Draw a filled rounded rectangle with current transform.
	 * @param x The x position of the rectangle.
	 * @param y The y position of the rectangle.
	 * @param width The width of the rectangle.
	 * @param height The height of the rectangle.
	 * @param cornerRadii The corner radii for each corner (top-left, top-right, bottom-right, bottom-left).
	 * @param color The color of the rectangle.
	 */
	void fillRoundedRect(int x, int y, int width, int height, Vector4f cornerRadii, Color color);
}
