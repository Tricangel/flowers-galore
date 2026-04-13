package bee.flowers;

import bee.flowers.block.property.AlliumColourProperty;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import bee.flowers.registry.FlowersGaloreBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.util.CommonColors;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class FlowersgaloreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockColorRegistry.register(List.of(new BlockTintSource() {
            @Override
            public int color(BlockState state) {
                    AlliumColourProperty colourProperty = state.getValue(FlowersGaloreBlockProperties.ALLIUM_COLOURS);
                    return colourProperty.getColour();
            }

        }
    ), FlowersGaloreBlocks.ALLIUM);

    }
}
