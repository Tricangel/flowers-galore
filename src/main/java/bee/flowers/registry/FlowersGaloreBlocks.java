package bee.flowers.registry;

import bee.flowers.Flowersgalore;
import bee.flowers.block.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

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
    public static final Block LILAC = register("lilac", TallBreedableFlower::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LILAC), true);


    public static final Block POTTED_ALLIUM = register("potted_allium", instance -> new PottedFlowersGaloreFlower(ALLIUM, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT), false);
    public static final Block POTTED_TULIP = register("potted_tulip", instance -> new PottedFlowersGaloreFlower(TULIP, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT), false);
    public static final Block POTTED_DANDELION = register("potted_dandelion", instance -> new PottedFlowersGaloreFlower(DANDELION, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT), false);
    public static final Block POTTED_POPPY = register("potted_poppy", instance -> new PottedFlowersGaloreFlower(POPPY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT), false);
    public static final Block POTTED_ORCHID = register("potted_orchid", instance -> new PottedFlowersGaloreFlower(ORCHID, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT), false);
    public static final Block POTTED_AZURE_BLUET = register("potted_azure_bluet", instance -> new PottedFlowersGaloreFlower(AZURE_BLUET, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT), false);
    public static final Block POTTED_OXEYE_DAISY = register("potted_oxeye_daisy", instance -> new PottedFlowersGaloreFlower(OXEYE_DAISY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT), false);
    public static final Block POTTED_CORNFlOWER = register("potted_cornflower", instance -> new PottedFlowersGaloreFlower(CORNFlOWER, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT), false);
    public static final Block POTTED_LILY_OF_THE_VALLEY = register("potted_lily_of_the_valley", instance -> new PottedFlowersGaloreFlower(LILY_OF_THE_VALLEY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT), false);

    public static final Block VASE = register("vase", VaseBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT), true);
    public static final Block SUNFLOWER_HEAD = register("sunflower_head", SunflowerHead::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).offsetType(BlockBehaviour.OffsetType.XZ).noCollision().sound(SoundType.GRASS), true);


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
