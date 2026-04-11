package bee.flowers;

import bee.flowers.registry.FlowersGaloreBlocks;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flowersgalore implements ModInitializer {
	public static final String MOD_ID = "flowers-galore";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		FlowersGaloreBlocks.init();
	}
}