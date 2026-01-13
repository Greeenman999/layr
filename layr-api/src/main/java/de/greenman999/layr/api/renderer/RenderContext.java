package de.greenman999.layr.api.renderer;

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
}
