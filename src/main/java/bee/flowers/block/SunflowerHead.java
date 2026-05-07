package bee.flowers.block;

import bee.flowers.block.property.ColourProperty;
import bee.flowers.block.property.ShapeProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import bee.flowers.registry.FlowersGaloreItemComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class SunflowerHead extends Block {
    public static final EnumProperty<ColourProperty> COLOUR = FlowersGaloreBlockProperties.COLOUR;
    public SunflowerHead(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.or(
                Shapes.create(0.1875, 0.0625, 0.6875, 0.8125, 0.0625, 1.3125),
                Shapes.create(0.3125, 0, 0.3125, 0.6875, 0.0625, 0.6875)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(COLOUR);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return !level.isEmptyBlock(pos.below()) && level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
        if (!canSurvive(state, level, pos)) level.destroyBlock(pos, true);
    }

    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        ItemStack stack = this.asItem().getDefaultInstance();
        Component name = this.getName(state);
        stack.set(DataComponents.ITEM_NAME, name);
        stack.set(FlowersGaloreItemComponents.FLOWER_COLOUR, state.getValue(FlowersGaloreBlockProperties.COLOUR).getColour());
        return List.of(stack);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        ItemStack stack = context.getItemInHand();
        BlockState state = this.defaultBlockState();

        if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) != null) {
            state = state.setValue(FlowersGaloreBlockProperties.COLOUR, ColourProperty.fromColour(stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR)));
        }
        return state;
    }


    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        ItemStack stack = this.asItem().getDefaultInstance();
        Component name = this.getName(state);
        stack.set(DataComponents.ITEM_NAME, name);
        stack.set(FlowersGaloreItemComponents.FLOWER_COLOUR, state.getValue(FlowersGaloreBlockProperties.COLOUR).getColour());
        return stack;
    }

    public static Component getName(BlockState state) {
        //im so so sorry for this
        Component itemName = state.getBlock().getName();
        Component colour = state.getValue(FlowersGaloreBlockProperties.COLOUR).getDisplayName();
        return Component.literal(colour.getString() + " " + itemName.getString());
    }



}
