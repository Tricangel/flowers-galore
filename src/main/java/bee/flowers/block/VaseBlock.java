package bee.flowers.block;

import bee.flowers.block.entity.VaseBlockEntity;
import bee.flowers.registry.FlowersGaloreItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class VaseBlock extends BaseEntityBlock {
    public VaseBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof VaseBlockEntity) || !itemStack.is(FlowersGaloreItems.ROSE_CUTTING)) return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
        VaseBlockEntity vaseBlockEntity = (VaseBlockEntity) level.getBlockEntity(pos);

        if (vaseBlockEntity.addIngredients(itemStack)) return InteractionResult.SUCCESS;

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(VaseBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new VaseBlockEntity(worldPosition, blockState);
    }
}


