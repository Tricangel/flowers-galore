package bee.flowers;

import bee.flowers.datagen.FlowersGaloreLootGen;
import bee.flowers.datagen.FlowersGaloreModelGen;
import bee.flowers.datagen.FlowersGaloreTagGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FlowersgaloreDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(FlowersGaloreModelGen::new);
        pack.addProvider(FlowersGaloreLootGen::new);

    }
}
