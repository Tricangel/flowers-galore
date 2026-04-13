package bee.flowers.block.property;

import net.minecraft.util.StringRepresentable;

import java.util.Random;

public enum ShapeProperty implements StringRepresentable {
    DEFAULT("default"),
    POINTY("pointy"),
    WOAH("woah"),
    ANOTHERONE("anotherone");

    private final String name;

    public static ShapeProperty getRandomShape() {
        int random = new Random().nextInt(1, 4);

        switch (random) {
            case 1 -> {return ShapeProperty.DEFAULT;}
            case 2 -> {return ShapeProperty.POINTY;}
            case 3 -> {return ShapeProperty.WOAH;}
            case 4 -> {return ShapeProperty.ANOTHERONE;}

        }
        return ShapeProperty.DEFAULT;
    }

    ShapeProperty(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
