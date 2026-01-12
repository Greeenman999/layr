package de.greenman999.layr.api;

import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Simple bootstrap singleton used by consumers (Veneer/example mods) to get the Layr instance.
 * <p>
 * Pattern:
 *  - Implementation registers explicitly via registerFactory(...) in its mod initializer (recommended).
 *  - Consumers call LayrProvider.get() to obtain the instance. If no explicit factory was registered,
 *    we attempt to discover one via ServiceLoader as a fallback.
 */
public final class LayrProvider {
	private static final AtomicReference<LayrFactory> factoryRef = new AtomicReference<>();
	private static volatile Layr INSTANCE;

	private LayrProvider() {}

	/**
	 * Register an implementation factory explicitly (call from implementation mod initializer).
	 * This is the recommended approach in Minecraft mod environments.
	 */
	public static void registerFactory(LayrFactory factory) {
		factoryRef.set(factory);
	}

	/**
	 * Get (and lazily create) the singleton Layr instance.
	 * First checks an explicitly registered factory, then falls back to ServiceLoader discovery.
	 */
	public static synchronized Layr get() {
		if (INSTANCE != null) return INSTANCE;

		LayrFactory factory = factoryRef.get();
		if (factory == null) {
			ServiceLoader<LayrFactory> loader = ServiceLoader.load(LayrFactory.class);
			for (LayrFactory f : loader) {
				factory = f;
				break; // pick first discovered as a fallback
			}
		}

		if (factory == null) {
			throw new IllegalStateException("No LayrFactory registered or discovered. " +
					"Ensure an implementation module registers a factory (LayrProvider.registerFactory(...)).");
		}

		INSTANCE = factory.create();
		return INSTANCE;
	}
}
