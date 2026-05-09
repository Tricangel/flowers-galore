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
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class FlowersgaloreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockEntityRenderers.register(FlowersGaloreBlockEntities.VASE_BLOCK_ENTITY, VaseBlockRenderer::new);

        ItemTintSources.ID_MAPPER.put(Identifier.fromNamespaceAndPath(Flowersgalore.MOD_ID, "color"), FlowerColourTintSource.MAP_CODEC);



        BlockColorRegistry.register(List.of(new BlockTintSource() {
                                                @Override
                                                public int color(BlockState state) {
                                                    ColourProperty colourProperty = state.getValue(FlowersGaloreBlockProperties.COLOUR);
                                                    return colourProperty.getColour();
                                                }

                                            }
        ), FlowersGaloreBlocks.ROSE_BUSH, FlowersGaloreBlocks.SUNFLOWER, FlowersGaloreBlocks.PEONY, FlowersGaloreBlocks.TULIP, FlowersGaloreBlocks.ALLIUM, FlowersGaloreBlocks.DANDELION, FlowersGaloreBlocks.POPPY,
                FlowersGaloreBlocks.ORCHID, FlowersGaloreBlocks.AZURE_BLUET, FlowersGaloreBlocks.OXEYE_DAISY, FlowersGaloreBlocks.CORNFlOWER, FlowersGaloreBlocks.LILY_OF_THE_VALLEY, FlowersGaloreBlocks.LILAC, FlowersGaloreBlocks.SUNFLOWER_HEAD,
                FlowersGaloreBlocks.POTTED_TULIP, FlowersGaloreBlocks.POTTED_ALLIUM, FlowersGaloreBlocks.POTTED_DANDELION, FlowersGaloreBlocks.POTTED_POPPY, FlowersGaloreBlocks.POTTED_LILY_OF_THE_VALLEY,
                FlowersGaloreBlocks.POTTED_ORCHID, FlowersGaloreBlocks.POTTED_AZURE_BLUET, FlowersGaloreBlocks.POTTED_OXEYE_DAISY, FlowersGaloreBlocks.POTTED_CORNFlOWER);

    }
}
