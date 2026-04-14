package bee.flowers.registry;

import bee.flowers.block.property.ColourProperty;
import bee.flowers.block.property.ShapeProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class FlowersGaloreBlockProperties {

    public static final EnumProperty<ShapeProperty> FLOWER_SHAPE = EnumProperty.create("flower_shape", ShapeProperty.class);
    public static final EnumProperty<ColourProperty> COLOUR = EnumProperty.create("colour", ColourProperty.class);

}
