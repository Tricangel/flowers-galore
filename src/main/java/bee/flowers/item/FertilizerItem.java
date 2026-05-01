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

        if (stack.get(FlowersGaloreItemComponents.PREVIOUS_BLOCK) == null) return super.useOn(context);
        if (!(state.getBlock() instanceof BreedableFlower) && !(state.getBlock() instanceof TallBreedableFlower)) return super.useOn(context);

        BlockState previousBlock = stack.get(FlowersGaloreItemComponents.PREVIOUS_BLOCK);

        if (!(previousBlock.getBlock() instanceof BreedableFlower || previousBlock.getBlock() instanceof TallBreedableFlower)) {
            stack.set(FlowersGaloreItemComponents.PREVIOUS_BLOCK, state);
            player.playSound(SoundEvents.BONE_MEAL_USE);
            if (state.getBlock() instanceof TallBreedableFlower) {
                if (state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF).equals(DoubleBlockHalf.UPPER)) pos = pos.below();
                BoneMealItem.addGrowthParticles(level, pos.above(), 25);
            }
            BoneMealItem.addGrowthParticles(level, pos, 25);
            return InteractionResult.SUCCESS;
        }
        BlockState mutatedState;

        if (stack.is(FlowersGaloreItems.GOLDEN_FERTILIZER)) {
            mutatedState = FlowerMutationHelper.mutateFromGoldenParents(state, previousBlock);
        } else {
            mutatedState = FlowerMutationHelper.mutateFromParents(state, previousBlock);
        }

        if (!BonemealableBlock.hasSpreadableNeighbourPos(level, pos, mutatedState)) return super.useOn(context);

        if (mutatedState.getBlock() instanceof TallBreedableFlower) {
            if (mutatedState.canSurvive(level, pos.above())) {
                BonemealableBlock.findSpreadableNeighbourPos(level, pos, mutatedState).ifPresent(blockPos -> {
                    level.setBlockAndUpdate(blockPos, mutatedState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER));
                    level.setBlockAndUpdate(blockPos.above(), mutatedState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER));
                    BoneMealItem.addGrowthParticles(level, blockPos, 20);
                    BoneMealItem.addGrowthParticles(level, blockPos.above(), 20);
                });
            }
        } else  {
            BonemealableBlock.findSpreadableNeighbourPos(level, pos, mutatedState).ifPresent(blockPos -> {
                level.setBlockAndUpdate(blockPos, mutatedState);
                BoneMealItem.addGrowthParticles(level, blockPos, 20);
            });
        }
        stack.set(FlowersGaloreItemComponents.PREVIOUS_BLOCK, Blocks.AIR.defaultBlockState());
        player.playSound(SoundEvents.BONE_MEAL_USE);
        stack.consume(1, player);
        return InteractionResult.SUCCESS;
    }

}


