package bee.flowers.block.property;

import net.minecraft.util.StringRepresentable;

import java.util.Random;

public enum ColourProperty implements StringRepresentable {
    BLUE("blue"),
    PURPLE("purple"),
    MAGENTA("magenta"),
    PINK("pink"),
    YELLOW("yellow"),
    ORANGE("orange"),
    RED("red"),
    GREEN("green");

    public final String name;

    public static ColourProperty getRandomColour() {
        int random = new Random().nextInt(1, 8);

        switch (random) {
            case 1 -> {return ColourProperty.BLUE;}
            case 2 -> {return ColourProperty.PURPLE;}
            case 3 -> {return ColourProperty.MAGENTA;}
            case 4 -> {return ColourProperty.PINK;}
            case 5 -> {return ColourProperty.YELLOW;}
            case 6 -> {return ColourProperty.ORANGE;}
            case 7 -> {return ColourProperty.RED;}
            case 8 -> {return ColourProperty.GREEN;}
        }
        return ColourProperty.GREEN;
    }


    ColourProperty(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
