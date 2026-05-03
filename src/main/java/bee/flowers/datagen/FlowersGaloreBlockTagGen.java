package bee.flowers.datagen;

import bee.flowers.registry.FlowersGaloreBlocks;
import bee.flowers.registry.FlowersGaloreItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class FlowersGaloreBlockTagGen extends FabricTagsProvider.BlockTagsProvider {


    public FlowersGaloreBlockTagGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(BlockTags.BEE_ATTRACTIVE)
                .add(FlowersGaloreBlocks.SUNFLOWER_HEAD)
                .add(FlowersGaloreBlocks.ALLIUM)
                .add(FlowersGaloreBlocks.TULIP)
                .add(FlowersGaloreBlocks.DANDELION)
                .add(FlowersGaloreBlocks.POPPY)
                .add(FlowersGaloreBlocks.ORCHID)
                .add(FlowersGaloreBlocks.AZURE_BLUET)
                .add(FlowersGaloreBlocks.OXEYE_DAISY)
                .add(FlowersGaloreBlocks.CORNFlOWER)
                .add(FlowersGaloreBlocks.LILY_OF_THE_VALLEY)
                .add(FlowersGaloreBlocks.SUNFLOWER)
                .add(FlowersGaloreBlocks.PEONY)
                .add(FlowersGaloreBlocks.LILAC)
                .add(FlowersGaloreBlocks.ROSE_BUSH);

        valueLookupBuilder(BlockTags.FLOWERS)
                .add(FlowersGaloreBlocks.SUNFLOWER_HEAD)
                .add(FlowersGaloreBlocks.ALLIUM)
                .add(FlowersGaloreBlocks.TULIP)
                .add(FlowersGaloreBlocks.DANDELION)
                .add(FlowersGaloreBlocks.POPPY)
                .add(FlowersGaloreBlocks.ORCHID)
                .add(FlowersGaloreBlocks.AZURE_BLUET)
                .add(FlowersGaloreBlocks.OXEYE_DAISY)
                .add(FlowersGaloreBlocks.CORNFlOWER)
                .add(FlowersGaloreBlocks.LILY_OF_THE_VALLEY)
                .add(FlowersGaloreBlocks.SUNFLOWER)
                .add(FlowersGaloreBlocks.PEONY)
                .add(FlowersGaloreBlocks.LILAC)
                .add(FlowersGaloreBlocks.ROSE_BUSH);


    }
}
