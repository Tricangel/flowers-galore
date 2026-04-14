package bee.flowers.registry;

import bee.flowers.Flowersgalore;
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

    public static final Block ALLIUM = register("allium", instance -> new FlowerBlock(SuspiciousStewEffects.EMPTY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_TULIP), true);

    public static final Block ROSE_BUSH = register("rose_bush", TallBreedableFlower::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH), true);

    public static final Block VASE = register("base", VaseBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT), true);


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
