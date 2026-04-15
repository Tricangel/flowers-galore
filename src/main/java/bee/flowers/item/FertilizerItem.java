package bee.flowers.item;

import bee.flowers.block.TallBreedableFlower;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import bee.flowers.registry.FlowersGaloreBlocks;
import bee.flowers.registry.FlowersGaloreItemComponents;
import bee.flowers.util.FlowerMutationHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
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
        BlockState parent1 = level.getBlockState(context.getClickedPos());
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();

            if (parent1.getBlock() instanceof TallBreedableFlower) {
                if (stack.get(FlowersGaloreItemComponents.PREVIOUS_BLOCK) != null && !(stack.get(FlowersGaloreItemComponents.PREVIOUS_BLOCK).getBlock() instanceof TallBreedableFlower)) {
                    stack.set(FlowersGaloreItemComponents.PREVIOUS_BLOCK, parent1);
                    context.getPlayer().playSound(SoundEvents.BONE_MEAL_USE);
                    BoneMealItem.addGrowthParticles(level, pos, 25);
                    BoneMealItem.addGrowthParticles(level, pos.above(), 25);
                    return InteractionResult.SUCCESS;
                }

                BlockState parent2 = stack.get(FlowersGaloreItemComponents.PREVIOUS_BLOCK);

                BlockState mutatedState = FlowerMutationHelper.mutateFromParents(parent1, parent2);


                if (parent1.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF).equals(DoubleBlockHalf.UPPER))
                    pos = pos.below();
                BoneMealItem.addGrowthParticles(level, pos, 25);
                BoneMealItem.addGrowthParticles(level, pos.above(), 25);
                BonemealableBlock.findSpreadableNeighbourPos(level, pos, mutatedState).ifPresent(blockPos -> {
                    level.setBlockAndUpdate(blockPos, mutatedState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER));
                    level.setBlockAndUpdate(blockPos.above(), mutatedState.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER));
                    BoneMealItem.addGrowthParticles(level, blockPos, 20);
                    BoneMealItem.addGrowthParticles(level, blockPos.above(), 20);
                });

                stack.set(FlowersGaloreItemComponents.PREVIOUS_BLOCK, Blocks.AIR.defaultBlockState());
                context.getPlayer().playSound(SoundEvents.BONE_MEAL_USE);
                stack.consume(1, context.getPlayer());
                return InteractionResult.SUCCESS;
            }



            return super.useOn(context);
        }
    }

