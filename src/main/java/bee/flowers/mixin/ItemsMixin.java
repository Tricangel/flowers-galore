package bee.flowers.mixin;

import bee.flowers.block.BreedableFlower;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.registry.FlowersGaloreItemComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Items.class)
public class ItemsMixin {

    @ModifyVariable(method = "registerBlock(Lnet/minecraft/world/level/block/Block;Ljava/util/function/BiFunction;Lnet/minecraft/world/item/Item$Properties;)Lnet/minecraft/world/item/Item;", at = @At(value = "HEAD"), argsOnly = true)
    private static Item.Properties wawa(Item.Properties value, Block block) {
        String name = block.getDescriptionId();
        name = name.replace("block.minecraft.", "");
        return switch (name) {
            case "poppy", "rose_bush" -> value.component(FlowersGaloreItemComponents.FLOWER_COLOUR, ColourProperty.RED.getColour());
            case "dandelion", "oxeye_daisy", "sunflower", "azure_bluet" -> value.component(FlowersGaloreItemComponents.FLOWER_COLOUR, ColourProperty.YELLOW.getColour());
            case "blue_orchid" -> value.component(FlowersGaloreItemComponents.FLOWER_COLOUR, ColourProperty.LIGHT_BLUE.getColour());
            case "cornflower" -> value.component(FlowersGaloreItemComponents.FLOWER_COLOUR, ColourProperty.BLUE.getColour());
            case "lily_of_the_valley" -> value.component(FlowersGaloreItemComponents.FLOWER_COLOUR, ColourProperty.WHITE.getColour());
            case "allium", "lilac" -> value.component(FlowersGaloreItemComponents.FLOWER_COLOUR, ColourProperty.MAGENTA.getColour());
            case "peony", "red_tulip" -> value.component(FlowersGaloreItemComponents.FLOWER_COLOUR, ColourProperty.PINK.getColour());
            default -> value;
        };


    }

}
