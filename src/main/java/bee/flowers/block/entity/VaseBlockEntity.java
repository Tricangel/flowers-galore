package bee.flowers.block.entity;

import bee.flowers.Flowersgalore;
import bee.flowers.registry.FlowersGaloreBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class VaseBlockEntity extends BlockEntity implements Clearable {
    private final NonNullList<ItemStack> ingredients;
    public VaseBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(FlowersGaloreBlockEntities.VASE_BLOCK_ENTITY, worldPosition, blockState);
        this.ingredients = NonNullList.withSize(4, ItemStack.EMPTY);
    }

    public boolean addIngredients(ItemStack stack) {

        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).isEmpty()) {
                ingredients.set(i, stack.copy());
                stack.shrink(1);
                return true;
            }
        }

        return false;
    }

    public NonNullList<ItemStack> getIngredients() {
        return ingredients;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        super.preRemoveSideEffects(pos, state);
        Containers.dropContents(this.level, pos, ingredients);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        try (ProblemReporter.ScopedCollector scopedCollector = new ProblemReporter.ScopedCollector(this.problemPath(), Flowersgalore.LOGGER)) {
            TagValueOutput tagValueOutput = TagValueOutput.createWithContext(scopedCollector, provider);
            ContainerHelper.saveAllItems(tagValueOutput, this.ingredients, true);
            return tagValueOutput.buildResult();
        }
    }

    @Override
    protected void loadAdditional(ValueInput valueInput) {
        ContainerHelper.loadAllItems(valueInput, this.ingredients);
    }

    @Override
    protected void saveAdditional(ValueOutput valueOutput) {
        ContainerHelper.saveAllItems(valueOutput, this.ingredients, true);

        super.saveAdditional(valueOutput);
    }

    @Override
    public void clearContent() {
        ingredients.clear();
    }
}
