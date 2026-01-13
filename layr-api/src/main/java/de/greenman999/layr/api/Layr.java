package de.greenman999.layr.api;

import de.greenman999.layr.api.gui.screen.ScreenManager;

/**
 * The main interface for the Layr API, providing access to various Layr functionalities.
 */
public interface Layr {

	/**
	 * Gets the screen manager for managing Layr screens.
	 * @return The screen manager instance.
	 */
	ScreenManager getScreenManager();
}
