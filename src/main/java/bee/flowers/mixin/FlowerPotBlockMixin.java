package bee.flowers.mixin;

import bee.flowers.block.BreedableFlower;
import bee.flowers.block.TallBreedableFlower;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.block.property.ShapeProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import bee.flowers.registry.FlowersGaloreItemComponents;
import bee.flowers.util.FlowerMutationHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(FlowerPotBlock.class)
public abstract class FlowerPotBlockMixin {

    @Shadow
    @Final
    private static Map<Block, Block> POTTED_BY_CONTENT;

    @Shadow
    protected abstract boolean isEmpty();

    @Shadow
    public abstract Block getPotted();

    @Inject( at = @At(value = "HEAD"), method = "useItemOn", cancellable = true)
    private void init(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {

        if (itemStack.getItem() instanceof BlockItem blockItem) {
            if (blockItem.getBlock() instanceof BreedableFlower) {
                BlockState blockState = POTTED_BY_CONTENT.getOrDefault(blockItem.getBlock(), Blocks.AIR).defaultBlockState();

                blockState = blockState.setValue(FlowersGaloreBlockProperties.COLOUR, ColourProperty.fromColour(itemStack.getOrDefault(FlowersGaloreItemComponents.FLOWER_COLOUR, ColourProperty.WHITE.getColour())));
                blockState = blockState.setValue(FlowersGaloreBlockProperties.FLOWER_SHAPE, ShapeProperty.fromString(itemStack.getOrDefault(FlowersGaloreItemComponents.FLOWER_SHAPE, ShapeProperty.DEFAULT.getSerializedName())));

                level.setBlock(pos, blockState, 3);
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                player.awardStat(Stats.POT_FLOWER);
                itemStack.consume(1, player);
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }

    }

    @Inject( at = @At(value = "HEAD"), method = "getCloneItemStack", cancellable = true)
    private void clonereturncoolcorrectflower(LevelReader level, BlockPos pos, BlockState state, boolean includeData, CallbackInfoReturnable<ItemStack> cir) {
        if (!this.isEmpty())
            if (this.getPotted() instanceof BreedableFlower) {
                BlockState blockState = POTTED_BY_CONTENT.getOrDefault(this.getPotted(), Blocks.AIR).defaultBlockState();
                ItemStack stack = this.getPotted().asItem().getDefaultInstance();
                if (!blockState.isAir()) {
                    stack.set(FlowersGaloreItemComponents.FLOWER_COLOUR, state.getValue(FlowersGaloreBlockProperties.COLOUR).getColour());
                    stack.set(FlowersGaloreItemComponents.FLOWER_SHAPE, state.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE).getSerializedName());
                    stack.set(DataComponents.ITEM_NAME, FlowerMutationHelper.getName(state));
                }
                cir.setReturnValue(stack);

            }

    }

    @Inject( at = @At(value = "HEAD"), method = "useWithoutItem", cancellable = true)
    private void takereturncoolcorrectflower(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (!this.isEmpty())
            if (this.getPotted() instanceof BreedableFlower) {
                BlockState blockState = POTTED_BY_CONTENT.getOrDefault(this.getPotted(), Blocks.AIR).defaultBlockState();
                ItemStack stack = this.getPotted().asItem().getDefaultInstance();
                if (!blockState.isAir()) {
                    stack.set(FlowersGaloreItemComponents.FLOWER_COLOUR, state.getValue(FlowersGaloreBlockProperties.COLOUR).getColour());
                    stack.set(FlowersGaloreItemComponents.FLOWER_SHAPE, state.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE).getSerializedName());
                    stack.set(DataComponents.ITEM_NAME, FlowerMutationHelper.getName(state));
                }
                if (!player.addItem(stack)) {
                    player.drop(stack, false);
                }

                level.setBlock(pos, Blocks.FLOWER_POT.defaultBlockState(), 3);
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                cir.setReturnValue(InteractionResult.SUCCESS);

            }

    }

}
