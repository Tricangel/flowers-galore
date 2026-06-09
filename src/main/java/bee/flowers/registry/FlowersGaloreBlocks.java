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

    public static final Block TULIP = register("tulip", instance -> new BreedableFlower(SuspiciousStewEffects.EMPTY, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_TULIP), true);

    public static final Block POTTED_TULIP = register("potted_tulip", instance -> new PottedFlowersGaloreFlower(TULIP, instance), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT), false);

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
