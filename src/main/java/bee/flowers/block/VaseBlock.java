package bee.flowers.block;

import bee.flowers.block.entity.VaseBlockEntity;
import bee.flowers.registry.FlowersGaloreItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class VaseBlock extends BaseEntityBlock {
    public VaseBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof VaseBlockEntity) || !itemStack.is(FlowersGaloreItems.ROSE_CUTTING)) return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
        VaseBlockEntity vaseBlockEntity = (VaseBlockEntity) level.getBlockEntity(pos);

        if (vaseBlockEntity.addIngredients(itemStack)) {
            return InteractionResult.SUCCESS;
        }

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

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.or(
                Shapes.create(0.25, 0, 0.25, 0.75, 0.75, 0.75),
                Shapes.create(0.25, 0.625, 0.5, 0.5, 0.6875, 0.75),
                Shapes.create(0.3125, 0.6875, 0.3125, 0.6875, 0.75, 0.6875)
        );
    }

    @Override
    protected void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack tool, boolean dropExperience) {
        if (level.getBlockEntity(pos) instanceof VaseBlockEntity vaseBlockEntity) {
            for (int i = 0; i < vaseBlockEntity.getIngredients().size(); i++) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), vaseBlockEntity.getIngredients().get(i));
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), Items.ACACIA_FENCE.getDefaultInstance());
            }
        }
        super.spawnAfterBreak(state, level, pos, tool, dropExperience);
    }
}


