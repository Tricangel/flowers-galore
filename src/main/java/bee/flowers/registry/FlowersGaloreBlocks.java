package bee.flowers.registry;

import bee.flowers.Flowersgalore;
import bee.flowers.block.BreedableFlower;
import bee.flowers.block.TallBreedableFlower;
import bee.flowers.block.VaseBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class FlowersGaloreBlocks {

    public static final Block ALLIUM = register("allium", instance -> new BreedableFlower(SuspiciousStewEffects.EMPTY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM), true);
    public static final Block TULIP = register("tulip", instance -> new BreedableFlower(SuspiciousStewEffects.EMPTY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_TULIP), true);
    public static final Block DANDELION = register("dandelion", instance -> new BreedableFlower(SuspiciousStewEffects.EMPTY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION), true);
    public static final Block POPPY = register("poppy", instance -> new BreedableFlower(SuspiciousStewEffects.EMPTY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);
    public static final Block ORCHID = register("orchid", instance -> new BreedableFlower(SuspiciousStewEffects.EMPTY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ORCHID), true);
    public static final Block AZURE_BLUET = register("azure_bluet", instance -> new BreedableFlower(SuspiciousStewEffects.EMPTY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.AZURE_BLUET), true);
    public static final Block OXEYE_DAISY = register("oxeye_daisy", instance -> new BreedableFlower(SuspiciousStewEffects.EMPTY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.OXEYE_DAISY), true);
    public static final Block CORNFlOWER = register("cornflower", instance -> new BreedableFlower(SuspiciousStewEffects.EMPTY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.CORNFLOWER), true);
    public static final Block LILY_OF_THE_VALLEY = register("lily_of_the_valley", instance -> new BreedableFlower(SuspiciousStewEffects.EMPTY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_OF_THE_VALLEY), true);


    public static final Block ROSE_BUSH = register("rose_bush", TallBreedableFlower::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH), true);
    public static final Block SUNFLOWER = register("sunflower", TallBreedableFlower::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SUNFLOWER), true);
    public static final Block PEONY = register("peony", TallBreedableFlower::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PEONY), true);

    public static final Block VASE = register("vase", VaseBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT), true);


    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));

        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Flowersgalore.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Flowersgalore.MOD_ID, name));
    }

    public static void init() {
    }

}
