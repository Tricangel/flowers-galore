package bee.flowers;

import bee.flowers.block.property.ColourProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import bee.flowers.registry.FlowersGaloreBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.CommonColors;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class FlowersgaloreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockColorRegistry.register(List.of(new BlockTintSource() {
            @Override
            public int color(BlockState state) {
                    ColourProperty colourProperty = state.getValue(FlowersGaloreBlockProperties.COLOUR);
                    int colour = 0;

                    switch (colourProperty.name) {
                        case "red" -> colour = CommonColors.RED;
                        case "orange" -> colour = CommonColors.SOFT_RED;
                        case "yellow" -> colour = CommonColors.YELLOW;
                        case "green" -> colour = CommonColors.GREEN;
                        case "blue" -> colour = CommonColors.BLUE;
                        case "purple" -> colour = CommonColors.DARK_PURPLE;
                        case "pink" -> colour = CommonColors.COSMOS_PINK;
                        case "magenta" -> colour = CommonColors.GRAY;
                    }

                    return colour;
            }

        }
    ), FlowersGaloreBlocks.flower);

    }
}
