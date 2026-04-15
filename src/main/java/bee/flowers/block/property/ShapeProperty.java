package bee.flowers.block.property;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

import java.util.Map;
import java.util.Random;

public enum ShapeProperty implements StringRepresentable {
    DEFAULT("default", Component.translatable("shape.flowers-galore.default")),
    LARGE_FLOWERS("large_flowers", Component.translatable("shape.flowers-galore.large_flowers")),
    SMALL_FLOWERS("small_flowers", Component.translatable("shape.flowers-galore.small_flowers")),
    MANY_FLOWERS("many_flowers", Component.translatable("shape.flowers-galore.many_flowers"));

    private final String name;
    private final Component displayName;

    public static ShapeProperty getRandomShape() {
        int random = new Random().nextInt(1, 4);

        switch (random) {
            case 1 -> {
                return ShapeProperty.DEFAULT;
            }
            case 2 -> {
                return ShapeProperty.LARGE_FLOWERS;
            }
            case 3 -> {
                return ShapeProperty.SMALL_FLOWERS;
            }
            case 4 -> {
                return ShapeProperty.MANY_FLOWERS;
            }

        }
        return ShapeProperty.DEFAULT;
    }

    ShapeProperty(String name, Component displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public static ShapeProperty fromString(String string) {
        Map<String, ShapeProperty> map = Map.of(
                DEFAULT.getSerializedName(), DEFAULT,
                LARGE_FLOWERS.getSerializedName(), LARGE_FLOWERS,
                SMALL_FLOWERS.getSerializedName(), SMALL_FLOWERS,
                MANY_FLOWERS.getSerializedName(), MANY_FLOWERS
        );
        return map.get(string);
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public Component getDisplayName() {
        return displayName;
    }
}
