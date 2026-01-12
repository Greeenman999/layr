package de.greenman999.layr.impl;

import de.greenman999.layr.LayrMod;
import de.greenman999.layr.api.Layr;

public class MyLayr implements Layr {
	@Override
	public void init() {
		LayrMod.LOGGER.info("MyLayr initialized!");
	}

	@Override
	public void tick() {

	}

	@Override
	public void render() {

	}

	@Override
	public void dispose() {
		LayrMod.LOGGER.info("MyLayr disposed!");
	}
}
