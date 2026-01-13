package de.greenman999.layr.impl;

import de.greenman999.layr.api.Layr;
import de.greenman999.layr.api.gui.screen.ScreenManager;
import de.greenman999.layr.impl.gui.screen.ScreenManagerImpl;
import net.minecraft.client.Minecraft;

public class MyLayr implements Layr {

	@Override
	public ScreenManager getScreenManager() {
		return new ScreenManagerImpl(Minecraft.getInstance());
	}

}
