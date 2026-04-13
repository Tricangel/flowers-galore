package bee.flowers.block.property;

import net.minecraft.util.ARGB;
import net.minecraft.util.StringRepresentable;

import java.util.Random;

public enum AlliumColourProperty implements StringRepresentable {
    WHITE("white", 14084328),
    ORANGE("orange", 12413474),
    MAGENTA("magenta", 16739839),
    LIGHT_BLUE("light_blue", 3133181),
    YELLOW("yellow", 16772175),
    PINK("pink", 12949486),
    PURPLE("purple", 12938751),
    BLUE("blue", 4614891),
    RED("red", 15544364),
    BLACK("black", 2631720);

    private final String name;
    private final int colour;

    public static AlliumColourProperty getRandomColour() {
        int random = new Random().nextInt(1, 10);

        switch (random) {
            case 1 -> {return AlliumColourProperty.WHITE;}
            case 2 -> {return AlliumColourProperty.ORANGE;}
            case 3 -> {return AlliumColourProperty.MAGENTA;}
            case 4 -> {return AlliumColourProperty.LIGHT_BLUE;}
            case 5 -> {return AlliumColourProperty.YELLOW;}
            case 6 -> {return AlliumColourProperty.PINK;}
            case 7 -> {return AlliumColourProperty.PURPLE;}
            case 8 -> {return AlliumColourProperty.BLUE;}
            case 9 -> {return AlliumColourProperty.RED;}
            case 10 -> {return AlliumColourProperty.BLACK;}



        }
        return AlliumColourProperty.WHITE;
    }

    AlliumColourProperty(String name, int colour) {
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
