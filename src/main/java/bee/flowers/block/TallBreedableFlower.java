package bee.flowers.block;

import bee.flowers.block.property.ColourProperty;
import bee.flowers.block.property.ShapeProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import bee.flowers.registry.FlowersGaloreItemComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class TallBreedableFlower extends TallFlowerBlock {
    public static final EnumProperty<ShapeProperty> SHAPE = FlowersGaloreBlockProperties.FLOWER_SHAPE;
    public static final EnumProperty<ColourProperty> COLOUR = FlowersGaloreBlockProperties.COLOUR;

    public TallBreedableFlower(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        ItemStack stack = this.asItem().getDefaultInstance();
        Component name = this.getName(state);
        stack.set(DataComponents.ITEM_NAME, name);
        stack.set(FlowersGaloreItemComponents.FLOWER_COLOUR, state.getValue(FlowersGaloreBlockProperties.COLOUR).getColour());
        stack.set(FlowersGaloreItemComponents.FLOWER_SHAPE, state.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE).getSerializedName());
        return stack;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity by, ItemStack itemStack) {
        BlockPos abovePos = pos.above();
        level.setBlock(abovePos, copyWaterloggedFrom(level, abovePos, state.setValue(HALF, DoubleBlockHalf.UPPER)), 3);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        if (pos.getY() < level.getMaxY() && level.getBlockState(pos.above()).canBeReplaced(context)) {
            BlockState state = this.defaultBlockState();
            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) != null) {
                state = state.setValue(FlowersGaloreBlockProperties.COLOUR, ColourProperty.fromColour(stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR)));
            }
            if (stack.get(FlowersGaloreItemComponents.FLOWER_SHAPE) != null) {
                return  state.setValue(FlowersGaloreBlockProperties.FLOWER_SHAPE, ShapeProperty.fromString(stack.get(FlowersGaloreItemComponents.FLOWER_SHAPE)));
            }
            return state;
        } else return null;
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        if (state.getValue(HALF).equals(DoubleBlockHalf.LOWER)) {
            ItemStack stack = this.asItem().getDefaultInstance();
            Component name = this.getName(state);
            stack.set(DataComponents.ITEM_NAME, name);
            stack.set(FlowersGaloreItemComponents.FLOWER_COLOUR, state.getValue(FlowersGaloreBlockProperties.COLOUR).getColour());
            stack.set(FlowersGaloreItemComponents.FLOWER_SHAPE, state.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE).getSerializedName());
            return List.of(stack);
        } else return List.of();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SHAPE);
        builder.add(COLOUR);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return true;
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState neighborState, Direction direction) {
        return true;
    }

    public Component getName(BlockState state) {
        Component itemName = this.getName();
        Component colour = state.getValue(FlowersGaloreBlockProperties.COLOUR).getDisplayName();
        if (state.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE) != ShapeProperty.DEFAULT){
            Component shape = state.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE).getDisplayName();
            return Component.literal(shape.getString() + " " + colour.getString() + " " + itemName.getString());
        }
        return Component.literal(colour.getString() + " " + itemName.getString());
    }

}
