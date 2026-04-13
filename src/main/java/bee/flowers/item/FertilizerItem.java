package bee.flowers.item;

import bee.flowers.block.BreedableFlower;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class FertilizerItem extends Item {
    public FertilizerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockState state = level.getBlockState(context.getClickedPos());

        if (state.getBlock() instanceof BreedableFlower) {

        }

        return super.useOn(context);
    }
}
