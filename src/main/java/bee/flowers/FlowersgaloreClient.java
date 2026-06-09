package bee.flowers;

import bee.flowers.block.entity.VaseBlockRenderer;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.item.FlowerColourTintSource;
import bee.flowers.registry.FlowersGaloreBlockEntities;
import bee.flowers.registry.FlowersGaloreBlockProperties;
import bee.flowers.registry.FlowersGaloreBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class FlowersgaloreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockEntityRenderers.register(FlowersGaloreBlockEntities.VASE_BLOCK_ENTITY, VaseBlockRenderer::new);

        ItemTintSources.ID_MAPPER.put(Identifier.fromNamespaceAndPath(Flowersgalore.MOD_ID, "color"), FlowerColourTintSource.MAP_CODEC);



        BlockColorRegistry.register(List.of(state -> {
            ColourProperty colourProperty = state.getValue(FlowersGaloreBlockProperties.COLOUR);
            return colourProperty.getColour();
        }
                ), Blocks.ROSE_BUSH, Blocks.SUNFLOWER, Blocks.PEONY, FlowersGaloreBlocks.TULIP, Blocks.ALLIUM, Blocks.DANDELION, Blocks.POPPY,
                Blocks.BLUE_ORCHID, Blocks.AZURE_BLUET, Blocks.OXEYE_DAISY, Blocks.CORNFLOWER, Blocks.LILY_OF_THE_VALLEY, Blocks.LILAC, FlowersGaloreBlocks.SUNFLOWER_HEAD,
                FlowersGaloreBlocks.POTTED_TULIP, Blocks.POTTED_ALLIUM, Blocks.POTTED_DANDELION, Blocks.POTTED_POPPY, Blocks.POTTED_LILY_OF_THE_VALLEY,
                Blocks.POTTED_BLUE_ORCHID, Blocks.POTTED_AZURE_BLUET, Blocks.POTTED_OXEYE_DAISY, Blocks.POTTED_CORNFLOWER);

    }
}
