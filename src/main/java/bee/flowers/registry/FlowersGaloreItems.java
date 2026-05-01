package bee.flowers.registry;

import bee.flowers.Flowersgalore;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.item.FertilizerItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Function;

public class FlowersGaloreItems {

    public static final Item FERTILIZER = register("fertilizer", FertilizerItem::new, new Item.Properties().component(FlowersGaloreItemComponents.PREVIOUS_BLOCK, Blocks.AIR.defaultBlockState()));
    public static final Item GOLDEN_FERTILIZER = register("golden_fertilizer", FertilizerItem::new, new Item.Properties().component(FlowersGaloreItemComponents.PREVIOUS_BLOCK, Blocks.AIR.defaultBlockState()));

    public static final Item ROSE_CUTTING = register("rose_cutting", Item::new, new Item.Properties().component(FlowersGaloreItemComponents.FLOWER_COLOUR, ColourProperty.RED.getColour()).equippable(EquipmentSlot.HEAD));


    public static void init() {
    }

    public static <GenericItem extends Item> GenericItem register(String name, Function<Item.Properties, GenericItem> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Flowersgalore.MOD_ID, name));
        GenericItem item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

}
