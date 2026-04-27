package bee.flowers.util;

import bee.flowers.block.property.ColourProperty;
import bee.flowers.block.property.ShapeProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import bee.flowers.registry.FlowersGaloreBlocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Random;

//dont i sound so smart with this name
public class FlowerMutationHelper {

    public static BlockState mutateFromParents(BlockState parent1, BlockState parent2) {
        Random random = new Random();
        ColourProperty colour = addColoursTogether(parent1.getValue(FlowersGaloreBlockProperties.COLOUR), parent2.getValue(FlowersGaloreBlockProperties.COLOUR));
        ShapeProperty shape = random.nextBoolean() ? parent1.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE) : parent2.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE);

        //mutatecolours
        if (random.nextInt(0, 10) == 0) {
            colour = ColourProperty.getRandomColour();
        }

        //mutateshape which sure doesnt do anything as of writing this, its still important
        if (random.nextInt(0, 10) == 0) {
            shape = ShapeProperty.getRandomShape();
        }
        BlockState mutatedState =  random.nextBoolean() ? parent1 : parent2;
        mutatedState = mutatedState.setValue(FlowersGaloreBlockProperties.COLOUR, colour);
        mutatedState = mutatedState.setValue(FlowersGaloreBlockProperties.FLOWER_SHAPE, shape);

        return mutatedState;

    }

    public static ColourProperty addColoursTogether(ColourProperty colour, ColourProperty colour1) {
        List<ColourProperty> colours = List.of(colour1, colour);

        if (colour.equals(colour1)) return colour;

        if (colours.contains(ColourProperty.PINK) && colours.contains(ColourProperty.PURPLE)) return ColourProperty.MAGENTA;

        if (colours.contains(ColourProperty.WHITE) && colours.contains(ColourProperty.RED)) return ColourProperty.PINK;

        if (colours.contains(ColourProperty.WHITE) && colours.contains(ColourProperty.BLUE)) return ColourProperty.LIGHT_BLUE;

        if (colours.contains(ColourProperty.RED) && colours.contains(ColourProperty.YELLOW)) return ColourProperty.ORANGE;

        if (colours.contains(ColourProperty.BLACK) && colours.contains(ColourProperty.PINK)) return ColourProperty.PURPLE;

        if (colours.contains(ColourProperty.RED) && colours.contains(ColourProperty.BLUE)) return ColourProperty.PURPLE;

        if (colours.contains(ColourProperty.PURPLE) && colours.contains(ColourProperty.WHITE)) return ColourProperty.PINK;

        return new Random().nextBoolean() ? colour : colour1;

    }

}
