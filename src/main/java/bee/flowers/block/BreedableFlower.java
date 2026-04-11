package bee.flowers.block;

import bee.flowers.block.property.ColourProperty;
import bee.flowers.block.property.ShapeProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class BreedableFlower extends VegetationBlock implements SuspiciousEffectHolder {
    private static final EnumProperty<ShapeProperty> SHAPE = FlowersGaloreBlockProperties.FLOWER_SHAPE;
    public static final EnumProperty<ColourProperty> COLOUR = FlowersGaloreBlockProperties.COLOUR;
    private SuspiciousStewEffects suspiciousStewEffects = null;

    public BreedableFlower(Properties properties, SuspiciousStewEffects suspiciousStewEffects) {
        super(properties);
        this.suspiciousStewEffects = suspiciousStewEffects;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SHAPE);
        builder.add(COLOUR);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (itemStack.is(Items.BONE_MEAL)) {
            breed(level, pos);
            itemStack.consume(1, player);
            return InteractionResult.SUCCESS;

        }

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    public void breed(Level level, BlockPos origin) {
        BlockState originState =  level.getBlockState(origin);


        for (int i = 0; i < 4; i++) {
            BlockPos pos = origin.relative(Direction.getRandom(RandomSource.create()));
            BlockState state = level.getBlockState(pos);
            if (state.isAir() && this.canSurvive(originState, level, pos)) {
                BlockState mutatedState = mutate(originState);
                level.setBlock(pos, mutatedState, 0);
                break;
            }
        }



    }

    public static BlockState mutate(BlockState origin) {
        ColourProperty colour = origin.getValue(FlowersGaloreBlockProperties.COLOUR);
        ShapeProperty shape = origin.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE);
        Random random = new Random();
        if (random.nextInt(0, 10) == 0) {
            colour = ColourProperty.getRandomColour();
        }

        if (random.nextInt(0, 10) == 0) {
            shape = ShapeProperty.getRandomShape();
        }

        return origin.setValue(COLOUR, colour).setValue(SHAPE, shape);

    }

    public BreedableFlower(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return null;
    }

    @Override
    public SuspiciousStewEffects getSuspiciousEffects() {
        return this.suspiciousStewEffects;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return true;
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState neighborState, Direction direction) {
        return true;
    }
}
