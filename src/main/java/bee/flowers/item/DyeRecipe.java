package bee.flowers.item;


import bee.flowers.block.TallBreedableFlower;
import bee.flowers.block.property.ColourProperty;
import bee.flowers.registry.FlowersGaloreItemComponents;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class DyeRecipe extends CustomRecipe {
    public static final DyeRecipe INSTANCE = new DyeRecipe();
    public static final MapCodec<DyeRecipe> MAP_CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, DyeRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);
    public static final RecipeSerializer<DyeRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);


    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean bl = false;
        for (ItemStack stack : input.items()) {
            if (stack.is(ItemTags.FLOWERS) && stack.has(FlowersGaloreItemComponents.FLOWER_COLOUR)) bl = true;
        }
        return input.ingredientCount() == 1 && bl;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        ItemStack output = ItemStack.EMPTY;
        int outputAmount = 1;
        for (ItemStack stack : input.items()) {
            if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof TallBreedableFlower) outputAmount = 2;

            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) == null) return Items.WHITE_DYE.getDefaultInstance().copyWithCount(outputAmount);
            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) == ColourProperty.WHITE.getColour()) output = Items.WHITE_DYE.getDefaultInstance();
            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) == ColourProperty.ORANGE.getColour()) output = Items.ORANGE_DYE.getDefaultInstance();
            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) == ColourProperty.MAGENTA.getColour()) output = Items.MAGENTA_DYE.getDefaultInstance();
            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) == ColourProperty.LIGHT_BLUE.getColour()) output = Items.LIGHT_BLUE_DYE.getDefaultInstance();
            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) == ColourProperty.YELLOW.getColour()) output = Items.YELLOW_DYE.getDefaultInstance();
            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) == ColourProperty.PINK.getColour()) output = Items.PINK_DYE.getDefaultInstance();
            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) == ColourProperty.PURPLE.getColour()) output = Items.PURPLE_DYE.getDefaultInstance();
            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) == ColourProperty.BLUE.getColour()) output = Items.BLUE_DYE.getDefaultInstance();
            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) == ColourProperty.RED.getColour()) output = Items.RED_DYE.getDefaultInstance();
            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) == ColourProperty.BLACK.getColour()) output = Items.BLACK_DYE.getDefaultInstance();
            if (stack.get(FlowersGaloreItemComponents.FLOWER_COLOUR) == ColourProperty.GOLD.getColour()) output = Items.YELLOW_DYE.getDefaultInstance();

        }


        return output.copyWithCount(outputAmount);
    }

    public static class DyeRecipeType implements RecipeType<DyeRecipe> {
        public static final DyeRecipeType INSTANCE = new DyeRecipeType();


    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return SERIALIZER;
    }
}
