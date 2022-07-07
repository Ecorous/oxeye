package xyz.ecorous.oxeye;

import org.python.core.PyException;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.nio.file.Path;

public class OxeyeMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Oxeye");
	public static void getAndRunScripts() {
		Path gameDir = QuiltLoader.getGameDir();
		//LOGGER.info("Game directory: " + gameDir);
		Path oxeyeDir = gameDir.resolve("oxeye");
		if (!oxeyeDir.toFile().exists()) {
			LOGGER.warn("Oxeye directory does not exist. Creating...");
			oxeyeDir.toFile().mkdir();
		}
		Path scriptsDir = oxeyeDir.resolve("scripts");
		if (!scriptsDir.toFile().exists()) {
			LOGGER.warn("Oxeye scripts directory does not exist. Creating...");
			scriptsDir.toFile().mkdir();
		}
		File[] files = scriptsDir.toFile().listFiles();
		if (files == null) {
			LOGGER.warn("Oxeye scripts directory is empty. Not loading any scripts");
			return;
		} else {
			for (File file : files) {
				if (file.getName().endsWith(".py")) {
					try {
						LOGGER.info("Running script: " + file.getName());
						PythonHandler.runPythonFile(file);
					} catch (PyException e) {
						LOGGER.error("Error running script: " + file.getName());
						LOGGER.error(e.getMessage());
					}
				}
			}

		}
	}

	public void onInitialize(ModContainer mod) {
		getAndRunScripts();
		LOGGER.info("Loaded " + mod.metadata().name() + " version " + mod.metadata().version().raw() + "!");
	}
}
