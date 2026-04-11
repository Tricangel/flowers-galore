package bee.flowers.block.property;

import net.minecraft.util.ARGB;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.Random;

public enum ColourProperty implements StringRepresentable {
    WHITE("white", 15790320),
    ORANGE("orange", 15435844),
    MAGENTA("magenta", 12801229),
    LIGHT_BLUE("light_blue", 6719955),
    YELLOW("yellow", 14602026),
    LIME("lime", 4312372),
    PINK("pink", 14188952),
    CYAN("cyan", 2651799),
    PURPLE("purple", 8073150),
    BLUE("blue", 2437522),
    GREEN("green", 3887386),
    RED("red", 11743532),
    BLACK("black", 1973019);

    private final String name;
    private final int colour;

    public static ColourProperty getRandomColour() {
        int random = new Random().nextInt(1, 4);

        switch (random) {
            case 1 -> {return ColourProperty.WHITE;}
            case 2 -> {return ColourProperty.ORANGE;}
            case 3 -> {return ColourProperty.MAGENTA;}
            case 4 -> {return ColourProperty.LIGHT_BLUE;}
            case 5 -> {return ColourProperty.YELLOW;}
            case 6 -> {return ColourProperty.LIME;}
            case 7 -> {return ColourProperty.PINK;}
            case 8 -> {return ColourProperty.CYAN;}
            case 9 -> {return ColourProperty.PURPLE;}
            case 10 -> {return ColourProperty.BLUE;}
            case 11 -> {return ColourProperty.GREEN;}
            case 12 -> {return ColourProperty.RED;}
            case 13 -> {return ColourProperty.BLACK;}



        }
        return ColourProperty.GREEN;
    }

    ColourProperty(String name, int colour) {
        this.name = name;
        this.colour = ARGB.opaque(colour);
    }

    public int getColour() {
        return colour;
    }


    @Override
    public String getSerializedName() {
        return name;
    }
}
