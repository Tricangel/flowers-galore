package bee.flowers;

import bee.flowers.block.property.ColourProperty;
import bee.flowers.registry.FlowersGaloreBlocks;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.DyeColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Flowersgalore implements ModInitializer {
	public static final String MOD_ID = "flowers-galore";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		FlowersGaloreBlocks.init();
	}


	public static DyeColor getRandomColour() {
		int random = new Random().nextInt(1, 8);

		switch (random) {
			case 1 -> {return DyeColor.BLUE;}
			case 2 -> {return DyeColor.PURPLE;}
			case 3 -> {return DyeColor.MAGENTA;}
			case 4 -> {return DyeColor.PINK;}
			case 5 -> {return DyeColor.YELLOW;}
			case 6 -> {return DyeColor.ORANGE;}
			case 7 -> {return DyeColor.RED;}
			case 8 -> {return DyeColor.GREEN;}
			case 9 -> {return DyeColor.BLACK;}
			case 10 -> {return DyeColor.BROWN;}
			case 11 -> {return DyeColor.LIGHT_BLUE;}
			case 12 -> {return DyeColor.LIME;}
			case 13 -> {return DyeColor.CYAN;}
			case 14 -> {return DyeColor.LIGHT_GRAY;}
			case 15 -> {return DyeColor.GRAY;}
			case 16 -> {return DyeColor.WHITE;}

		}
		return DyeColor.GREEN;
	}

}