package bee.flowers.registry;

import bee.flowers.Flowersgalore;
import bee.flowers.block.property.ColourProperty;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FlowersGaloreItemGroup {

    public static final ResourceKey<CreativeModeTab> FLOWERSGALORE_CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Flowersgalore.MOD_ID, "creative_tab")
    );

    public static final CreativeModeTab FLOWERSGALORE_ITEMS =
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(FlowersGaloreItems.FERTILIZER))
                    .title(Component.translatable("creativeTab.flowers-galore"))
                    .displayItems((params, output) -> {
                        output.accept(flower(FlowersGaloreBlocks.TULIP.asItem()));



                        output.accept(FlowersGaloreItems.FERTILIZER);
                        output.accept(FlowersGaloreItems.GOLDEN_FERTILIZER);
                        output.accept(FlowersGaloreBlocks.VASE);

                        output.accept(flower(FlowersGaloreItems.ROSE_CUTTING));
                        output.accept(flower(FlowersGaloreBlocks.SUNFLOWER_HEAD.asItem()));
                    }).build();



    public static ItemStack flower(Item item) {
        ItemStack stack = new ItemStack(item);
        stack.set(FlowersGaloreItemComponents.FLOWER_COLOUR, ColourProperty.WHITE.getColour());
        return stack;

    }

}
