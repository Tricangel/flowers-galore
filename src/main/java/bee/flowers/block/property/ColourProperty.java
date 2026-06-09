package bee.flowers.block.property;

import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.Random;

public enum ColourProperty implements StringRepresentable {
    RANDOM("random", 0, Component.translatable("color.flowers-galore.random")),
    WHITE("white", 16777215, Component.translatable("color.minecraft.white")),
    ORANGE("orange", 16742431, Component.translatable("color.minecraft.orange")),
    MAGENTA("magenta", 14586879, Component.translatable("color.minecraft.magenta")),
    LIGHT_BLUE("light_blue", 3133181, Component.translatable("color.minecraft.light_blue")),
    YELLOW("yellow", 16772175, Component.translatable("color.minecraft.yellow")),
    PINK("pink", 15451645, Component.translatable("color.minecraft.pink")),
    PURPLE("purple", 12938751, Component.translatable("color.minecraft.purple")),
    BLUE("blue", 4614891, Component.translatable("color.minecraft.blue")),
    RED("red", 16722731, Component.translatable("color.minecraft.red")),
    BLACK("black", 2631720, Component.translatable("color.minecraft.black")),
    GOLD("gold", 13866546, Component.translatable("color.minecraft.gold"));

    private final String name;
    private final int colour;
    private final Component displayName;

    public static ColourProperty getRandomColour() {
        int random = new Random().nextInt(1, 10);

        switch (random) {
            case 1 -> {
                return ColourProperty.WHITE;
            }
            case 2 -> {
                return ColourProperty.ORANGE;
            }
            case 3 -> {
                return ColourProperty.MAGENTA;
            }
            case 4 -> {
                return ColourProperty.LIGHT_BLUE;
            }
            case 5 -> {
                return ColourProperty.YELLOW;
            }
            case 6 -> {
                return ColourProperty.PINK;
            }
            case 7 -> {
                return ColourProperty.PURPLE;
            }
            case 8 -> {
                return ColourProperty.BLUE;
            }
            case 9 -> {
                return ColourProperty.RED;
            }
            case 10 -> {
                return ColourProperty.BLACK;
            }


        }
        return ColourProperty.RED;
    }

    ColourProperty(String name, int colour, Component displayName) {
        this.name = name;
        this.displayName = displayName;
        this.colour = ARGB.opaque(colour);
    }

    public static ColourProperty fromColour(int colour) {
        Map<Integer, ColourProperty> map = Map.of(
                WHITE.getColour(), WHITE,
                ORANGE.getColour(), ORANGE,
                MAGENTA.getColour(), MAGENTA,
                LIGHT_BLUE.getColour(), LIGHT_BLUE,
                YELLOW.getColour(), YELLOW,
                PINK.getColour(), PINK,
                PURPLE.getColour(), PURPLE,
                BLUE.getColour(), BLUE,
                RED.getColour(), RED,
                BLACK.getColour(), BLACK
        );

        if (colour == GOLD.getColour()) return GOLD;

        return map.getOrDefault(colour, ColourProperty.getRandomColour());
    }

    public Component getDisplayName() {
        return displayName;
    }

    public int getColour() {
        return colour;
    }


    @Override
    public String getSerializedName() {
        return name;
    }


}
