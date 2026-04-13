package bee.flowers.block;

import bee.flowers.Flowersgalore;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.block.property.ShapeProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import bee.flowers.registry.FlowersGaloreBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public abstract class BreedableFlower extends FlowerBlock {
    private SuspiciousStewEffects suspiciousStewEffects = null;

    public BreedableFlower(SuspiciousStewEffects suspiciousStewEffects, Properties properties) {
        super(suspiciousStewEffects, properties);
    }


    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (itemStack.is(Items.BONE_MEAL) && !level.isClientSide()) {
            if (breed(level, pos)) {
                itemStack.consume(1, player);
                return InteractionResult.SUCCESS;
            }

        }

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    public boolean breed(Level level, BlockPos origin) {
        BlockState originState =  level.getBlockState(origin);
        BlockState mutatedState = null;


        for (int i = -1; i < 1; i++) {
            for (int j = -1; j < 1; j++) {
                BlockPos pos = origin.north(i).east(j);
                BlockState state = level.getBlockState(pos);
                if (state.isAir() && this.canSurvive(originState, level, pos)) {
                    mutatedState = mutate(originState);
                    level.setBlock(pos, mutatedState, 0);
                    return true;
                }
            }
        }
        return false;
    }

    public abstract BlockState mutate(BlockState origin);



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
