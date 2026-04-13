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

    public static ColourProperty getRandomColour() {
        int random = new Random().nextInt(1, 10);

        switch (random) {
            case 1 -> {return ColourProperty.WHITE;}
            case 2 -> {return ColourProperty.ORANGE;}
            case 3 -> {return ColourProperty.MAGENTA;}
            case 4 -> {return ColourProperty.LIGHT_BLUE;}
            case 5 -> {return ColourProperty.YELLOW;}
            case 6 -> {return ColourProperty.PINK;}
            case 7 -> {return ColourProperty.PURPLE;}
            case 8 -> {return ColourProperty.BLUE;}
            case 9 -> {return ColourProperty.RED;}
            case 10 -> {return ColourProperty.BLACK;}



        }
        return ColourProperty.WHITE;
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
