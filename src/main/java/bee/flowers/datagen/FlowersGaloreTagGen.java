package bee.flowers.datagen;

import bee.flowers.registry.FlowersGaloreBlocks;
import bee.flowers.registry.FlowersGaloreItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class FlowersGaloreTagGen extends FabricTagsProvider.ItemTagsProvider {


    public FlowersGaloreTagGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(ItemTags.FLOWERS)
                .add(FlowersGaloreItems.ROSE_CUTTING)
                .add(FlowersGaloreBlocks.SUNFLOWER_HEAD.asItem())
                .add(FlowersGaloreBlocks.ALLIUM.asItem())
                .add(FlowersGaloreBlocks.TULIP.asItem())
                .add(FlowersGaloreBlocks.DANDELION.asItem())
                .add(FlowersGaloreBlocks.POPPY.asItem())
                .add(FlowersGaloreBlocks.ORCHID.asItem())
                .add(FlowersGaloreBlocks.AZURE_BLUET.asItem())
                .add(FlowersGaloreBlocks.OXEYE_DAISY.asItem())
                .add(FlowersGaloreBlocks.CORNFlOWER.asItem())
                .add(FlowersGaloreBlocks.LILY_OF_THE_VALLEY.asItem())
                .add(FlowersGaloreBlocks.SUNFLOWER.asItem())
                .add(FlowersGaloreBlocks.PEONY.asItem())
                .add(FlowersGaloreBlocks.LILAC.asItem())
                .add(FlowersGaloreBlocks.ROSE_BUSH.asItem());

        valueLookupBuilder(ItemTags.BEE_FOOD)
                .add(FlowersGaloreItems.ROSE_CUTTING)
                .add(FlowersGaloreBlocks.SUNFLOWER_HEAD.asItem())
                .add(FlowersGaloreBlocks.ALLIUM.asItem())
                .add(FlowersGaloreBlocks.TULIP.asItem())
                .add(FlowersGaloreBlocks.DANDELION.asItem())
                .add(FlowersGaloreBlocks.POPPY.asItem())
                .add(FlowersGaloreBlocks.ORCHID.asItem())
                .add(FlowersGaloreBlocks.AZURE_BLUET.asItem())
                .add(FlowersGaloreBlocks.OXEYE_DAISY.asItem())
                .add(FlowersGaloreBlocks.CORNFlOWER.asItem())
                .add(FlowersGaloreBlocks.LILY_OF_THE_VALLEY.asItem())
                .add(FlowersGaloreBlocks.SUNFLOWER.asItem())
                .add(FlowersGaloreBlocks.PEONY.asItem())
                .add(FlowersGaloreBlocks.LILAC.asItem())
                .add(FlowersGaloreBlocks.ROSE_BUSH.asItem());


    }
}
