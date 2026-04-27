package bee.flowers.datagen;

import bee.flowers.registry.FlowersGaloreBlocks;
import bee.flowers.registry.FlowersGaloreItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Items;

public class FlowersGaloreModelGen extends FabricModelProvider {
    public FlowersGaloreModelGen(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {

    }
}
