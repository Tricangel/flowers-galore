package bee.flowers.block;

import bee.flowers.Flowersgalore;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.block.property.ShapeProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import bee.flowers.registry.FlowersGaloreBlocks;
import bee.flowers.registry.FlowersGaloreItemComponents;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;

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
        Component name = this.getName(state);
        stack.set(DataComponents.ITEM_NAME, name);
        stack.set(FlowersGaloreItemComponents.FLOWER_COLOUR, state.getValue(FlowersGaloreBlockProperties.COLOUR).getColour());
        return stack;
    }

    @Override
    public SuspiciousStewEffects getSuspiciousEffects() {
        return this.suspiciousStewEffects;
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
            ItemStack stack = this.asItem().getDefaultInstance();
            Component name = this.getName(state);
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

    public Component getName(BlockState state) {
        Component itemName = this.getName();
        Component colour = state.getValue(FlowersGaloreBlockProperties.COLOUR).getDisplayName();
        if (state.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE) != ShapeProperty.DEFAULT){
            Component shape = state.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE).getDisplayName();
            return Component.literal(shape.getString() + " " + colour.getString() + " " + itemName.getString());
        }
        return Component.literal(colour.getString() + " " + itemName.getString());
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
}
