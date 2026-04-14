package bee.flowers;

import bee.flowers.block.TallBreedableFlower;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.ItemEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Flowersgalore implements ModInitializer {
    public static final String MOD_ID = "flowers-galore";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitialize() {
        FlowersGaloreBlocks.init();
        FlowersGaloreItems.init();
        FlowersGaloreItemComponents.init();
        FlowersGaloreBlockEntities.init();

        UseBlockCallback.EVENT.register(((player, level, hand, hitResult) -> {
            BlockPos pos = hitResult.getBlockPos();
            ItemStack stack = player.getItemInHand(hand);
            BlockState state = level.getBlockState(pos);

            if (!(stack.is(Items.SHEARS) && state.getBlock() instanceof TallBreedableFlower)) {
                return InteractionResult.PASS;
            }

            ItemStack rose = new ItemStack(FlowersGaloreItems.ROSE_CUTTING);

            rose.set(FlowersGaloreItemComponents.FLOWER_COLOUR, state.getValue(FlowersGaloreBlockProperties.COLOUR).getColour());
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), rose);
            player.playSound(SoundEvents.SHEARS_SNIP);
            stack.hurtAndBreak(25, player, hand);
            return InteractionResult.SUCCESS;

        }));

    }

}