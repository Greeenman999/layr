package de.greenman999.layr.impl.gui.screen;

import de.greenman999.layr.api.gui.screen.LayrScreen;
import de.greenman999.layr.api.gui.screen.ScreenManager;
import net.minecraft.client.Minecraft;

import java.util.Optional;

public class ScreenManagerImpl implements ScreenManager {
	private final Minecraft minecraft;

	public ScreenManagerImpl(Minecraft minecraft) {
		this.minecraft = minecraft;
	}

	@Override
	public void open(LayrScreen screen) {
		minecraft.setScreen(new LayrScreenWrapper(screen));
	}

	@Override
	public void close(LayrScreen screen) {
		if (getCurrent().map(s -> s == screen).orElse(false)) {
			minecraft.setScreen(null);
		}
	}

	@Override
	public void closeCurrent() {
		minecraft.setScreen(null);
	}

	@Override
	public Optional<LayrScreen> getCurrent() {
		var currentScreen = minecraft.screen;
		if (currentScreen instanceof LayrScreenWrapper) {
			return Optional.of(((LayrScreenWrapper) currentScreen).layrScreen);
		}
		return Optional.empty();
	}
}
