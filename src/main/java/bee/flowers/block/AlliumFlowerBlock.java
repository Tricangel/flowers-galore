package bee.flowers.block;

import bee.flowers.block.property.AlliumColourProperty;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.block.property.ShapeProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Random;

public class AlliumFlowerBlock extends BreedableFlower{
    private static final EnumProperty<ShapeProperty> SHAPE = FlowersGaloreBlockProperties.FLOWER_SHAPE;
    public static final EnumProperty<AlliumColourProperty> COLOUR = FlowersGaloreBlockProperties.ALLIUM_COLOURS;
    public AlliumFlowerBlock(SuspiciousStewEffects suspiciousStewEffects, Properties properties) {
        super(suspiciousStewEffects, properties);
    }

    @Override
    public BlockState mutate(BlockState origin) {
        AlliumColourProperty colour = origin.getValue(FlowersGaloreBlockProperties.ALLIUM_COLOURS);
        ShapeProperty shape = origin.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE);
        Random random = new Random();
        if (random.nextInt(0, 10) == 0) {
            colour = AlliumColourProperty.getRandomColour();
        }

        if (random.nextInt(0, 10) == 0) {
            shape = ShapeProperty.getRandomShape();
        }

        return origin.setValue(COLOUR, colour).setValue(SHAPE, shape);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SHAPE);
        builder.add(COLOUR);
    }

}
