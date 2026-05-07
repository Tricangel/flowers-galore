package bee.flowers.item;

import bee.flowers.block.BreedableFlower;
import bee.flowers.block.TallBreedableFlower;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import bee.flowers.registry.FlowersGaloreBlocks;
import bee.flowers.registry.FlowersGaloreItemComponents;
import bee.flowers.registry.FlowersGaloreItems;
import bee.flowers.util.FlowerMutationHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;

import java.util.Optional;

public class FertilizerItem extends Item {
    public FertilizerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockState state = level.getBlockState(context.getClickedPos());
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();

        if (!(state.getBlock() instanceof BreedableFlower) && !(state.getBlock() instanceof TallBreedableFlower)) return super.useOn(context);

        if (getPreviousBlock(level, pos, stack)) {
            player.playSound(SoundEvents.BONE_MEAL_USE);
            return InteractionResult.SUCCESS;
        }

        BlockState previousBlock = stack.get(FlowersGaloreItemComponents.PREVIOUS_BLOCK);
        BlockState mutatedState;

        if (stack.is(FlowersGaloreItems.GOLDEN_FERTILIZER)) {
            mutatedState = FlowerMutationHelper.mutateFromGoldenParents(state, previousBlock);
        } else {
            mutatedState = FlowerMutationHelper.mutateFromParents(state, previousBlock);
        }

        if (placeNearby(level, pos, state, mutatedState)) {
            stack.set(FlowersGaloreItemComponents.PREVIOUS_BLOCK, Blocks.AIR.defaultBlockState());
            player.playSound(SoundEvents.BONE_MEAL_USE);
            stack.consume(1, player);
            return InteractionResult.SUCCESS;
        } else return InteractionResult.FAIL;
    }


    private boolean getPreviousBlock(Level level, BlockPos pos, ItemStack stack) {
        if (stack.get(FlowersGaloreItemComponents.PREVIOUS_BLOCK) != null &&
                (stack.get(FlowersGaloreItemComponents.PREVIOUS_BLOCK).getBlock() instanceof BreedableFlower || stack.get(FlowersGaloreItemComponents.PREVIOUS_BLOCK).getBlock() instanceof TallBreedableFlower)) {
            return false;
        }
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof TallBreedableFlower) {
            if (state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF).equals(DoubleBlockHalf.UPPER)) {
                state = state.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER);
                pos = pos.below();
            }
            BoneMealItem.addGrowthParticles(level, pos.above(), 20);
        }
            BoneMealItem.addGrowthParticles(level, pos, 20);
        stack.set(FlowersGaloreItemComponents.PREVIOUS_BLOCK, state);
        return true;
    }

    private boolean placeNearby(Level level, BlockPos pos, BlockState parent, BlockState mutatedState) {
        if (parent.getBlock() instanceof TallBreedableFlower && parent.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF).equals(DoubleBlockHalf.UPPER)) {
            pos = pos.below();
            if (mutatedState.getBlock() instanceof TallBreedableFlower) {
                mutatedState = mutatedState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER);
            }
        }
        Optional<BlockPos> pos1 = BonemealableBlock.findSpreadableNeighbourPos(level, pos, mutatedState);
        if (pos1.isEmpty()) return false;
        BlockPos mutatedPos = pos1.get();

        if (mutatedState.getBlock() instanceof TallBreedableFlower) {
            level.setBlockAndUpdate(mutatedPos.above(), mutatedState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER));
            BoneMealItem.addGrowthParticles(level, mutatedPos.above(), 20);
        }

        level.setBlockAndUpdate(mutatedPos, mutatedState);
        BoneMealItem.addGrowthParticles(level, mutatedPos, 20);

        return true;

    }


}


