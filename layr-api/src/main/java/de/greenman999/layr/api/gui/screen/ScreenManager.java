package de.greenman999.layr.api.gui.screen;

import java.util.Optional;

/**
 * Manages screen lifecycle:  opening, closing, stacking.
 * Platform implementations bridge to Minecraft's screen system.
 */
public interface ScreenManager {
	/**
	 * Open a LayrScreen.  Replaces the current screen.
	 * Platform adapter will wrap it in a Minecraft Screen and call client. setScreen().
	 */
	void open(LayrScreen screen);

	/**
	 * Close the given screen. If it's the current screen, pops back to previous or null.
	 */
	void close(LayrScreen screen);

	/**
	 * Close the current screen (equivalent to pressing ESC).
	 */
	void closeCurrent();

	/**
	 * Get the currently active LayrScreen, if any.
	 */
	Optional<LayrScreen> getCurrent();
}
