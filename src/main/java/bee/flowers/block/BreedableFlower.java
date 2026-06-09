package bee.flowers.block;

import bee.flowers.Flowersgalore;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.block.property.ShapeProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import bee.flowers.registry.FlowersGaloreBlocks;
import bee.flowers.registry.FlowersGaloreItemComponents;
import bee.flowers.util.FlowerMutationHelper;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class BreedableFlower extends FlowerBlock implements BonemealableBlock {
    private static final EnumProperty<ShapeProperty> SHAPE = FlowersGaloreBlockProperties.FLOWER_SHAPE;
    public static final EnumProperty<ColourProperty> COLOUR = FlowersGaloreBlockProperties.COLOUR;
    private SuspiciousStewEffects suspiciousStewEffects = null;

    public BreedableFlower(SuspiciousStewEffects suspiciousStewEffects, Properties properties) {
        super(suspiciousStewEffects, properties);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        ItemStack stack = this.asItem().getDefaultInstance();
        Component name = FlowerMutationHelper.getName(state);
        stack.set(DataComponents.ITEM_NAME, name);
        stack.set(FlowersGaloreItemComponents.FLOWER_SHAPE, state.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE).getSerializedName());
        stack.set(FlowersGaloreItemComponents.FLOWER_COLOUR, state.getValue(FlowersGaloreBlockProperties.COLOUR).getColour());
        return stack;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        ItemStack stack = context.getItemInHand();
        BlockState state = this.defaultBlockState();

        if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) != null) {
            state = state.setValue(FlowersGaloreBlockProperties.COLOUR, ColourProperty.fromColour(stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR)));
        }
        if (stack.get(FlowersGaloreItemComponents.FLOWER_SHAPE) != null) {
            return  state.setValue(FlowersGaloreBlockProperties.FLOWER_SHAPE, ShapeProperty.fromString(stack.get(FlowersGaloreItemComponents.FLOWER_SHAPE)));
        }
        return state;
    }

    @Override
    public SuspiciousStewEffects getSuspiciousEffects() {
        return this.suspiciousStewEffects;
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        if (state.getValue(FlowersGaloreBlockProperties.COLOUR).equals(ColourProperty.RANDOM)) state = state.setValue(FlowersGaloreBlockProperties.COLOUR, ColourProperty.getRandomColour());
        ItemStack stack = this.asItem().getDefaultInstance();
            Component name = FlowerMutationHelper.getName(state);
            stack.set(DataComponents.ITEM_NAME, name);
            stack.set(FlowersGaloreItemComponents.FLOWER_COLOUR, state.getValue(FlowersGaloreBlockProperties.COLOUR).getColour());
            stack.set(FlowersGaloreItemComponents.FLOWER_SHAPE, state.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE).getSerializedName());
            return List.of(stack);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SHAPE);
        builder.add(COLOUR);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return true;
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState neighborState, Direction direction) {
        return true;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return false;

    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {

    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
        if (state.getValue(FlowersGaloreBlockProperties.COLOUR).equals(ColourProperty.RANDOM)) {
            level.setBlockAndUpdate(pos, state.setValue(FlowersGaloreBlockProperties.COLOUR, ColourProperty.getRandomColour()));
        }
    }
}
