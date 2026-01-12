package de.greenman999.layr.impl;

import de.greenman999.layr.api.Layr;
import de.greenman999.layr.api.LayrFactory;

public class MyLayrFactory implements LayrFactory {
	@Override
	public Layr create() {
		return new MyLayr();
	}
}
