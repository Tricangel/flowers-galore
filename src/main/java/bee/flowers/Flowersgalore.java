package bee.flowers;

import bee.flowers.block.TallBreedableFlower;
import bee.flowers.block.property.ShapeProperty;
import bee.flowers.item.FertilizerItem;
import bee.flowers.registry.*;
import bee.flowers.util.FlowerMutationHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flowersgalore implements ModInitializer {
    public static final String MOD_ID = "flowers-galore";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitialize() {
        FlowersGaloreBlocks.init();
        FlowersGaloreItems.init();
        FlowersGaloreItemComponents.init();
        FlowersGaloreBlockEntities.init();

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, FlowersGaloreItemGroup.FLOWERSGALORE_CREATIVE_TAB_KEY, FlowersGaloreItemGroup.FLOWERSGALORE_ITEMS);


        UseBlockCallback.EVENT.register(((player, level, hand, hitResult) -> {
            BlockPos pos = hitResult.getBlockPos();
            ItemStack stack = player.getItemInHand(hand);
            BlockState state = level.getBlockState(pos);

            if (!(stack.is(Items.SHEARS) && state.getBlock() instanceof TallBreedableFlower)) {
                return InteractionResult.PASS;
            }

            if (state.is(FlowersGaloreBlocks.ROSE_BUSH)) {
                ItemStack rose = new ItemStack(FlowersGaloreItems.ROSE_CUTTING);
                Component itemName = rose.getItem().getName(rose);
                Component colour = state.getValue(FlowersGaloreBlockProperties.COLOUR).getDisplayName();

                rose.set(DataComponents.ITEM_NAME, Component.literal(colour.getString() + " " + itemName.getString()));
                rose.set(FlowersGaloreItemComponents.FLOWER_COLOUR, state.getValue(FlowersGaloreBlockProperties.COLOUR).getColour());
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), rose);
                player.playSound(SoundEvents.SHEARS_SNIP);
                stack.hurtAndBreak(25, player, hand);

                return InteractionResult.SUCCESS;
            }

            if (state.is(FlowersGaloreBlocks.SUNFLOWER) && state.getValue(FlowersGaloreBlockProperties.FLOWER_SHAPE).equals(ShapeProperty.LARGE_FLOWERS)) {
                ItemStack sunflower = new ItemStack(FlowersGaloreBlocks.SUNFLOWER_HEAD);
                Component itemName = sunflower.getItem().getName(sunflower);
                Component colour = state.getValue(FlowersGaloreBlockProperties.COLOUR).getDisplayName();

                sunflower.set(DataComponents.ITEM_NAME, Component.literal(colour.getString() + " " + itemName.getString()));
                sunflower.set(FlowersGaloreItemComponents.FLOWER_COLOUR, state.getValue(FlowersGaloreBlockProperties.COLOUR).getColour());
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), sunflower);
                player.playSound(SoundEvents.SHEARS_SNIP);
                stack.hurtAndBreak(25, player, hand);

                return InteractionResult.SUCCESS;
            }
            return InteractionResult.FAIL;

        }));



    }

}