package bee.flowers.item;

import bee.flowers.registry.FlowersGaloreItemComponents;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public record FlowerColourTintSource(int colour) implements ItemTintSource {

    public static final MapCodec<FlowerColourTintSource> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ExtraCodecs.RGB_COLOR_CODEC.fieldOf("color").forGetter(FlowerColourTintSource::colour)
            ).apply(instance, FlowerColourTintSource::new)
    );

    @Override
    public int calculate(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity owner) {
        if (itemStack.has(FlowersGaloreItemComponents.FLOWER_COLOUR)) {
            return itemStack.get(FlowersGaloreItemComponents.FLOWER_COLOUR);
        }
        return colour;
    }

    @Override
    public MapCodec<? extends ItemTintSource> type() {
        return MAP_CODEC;
    }
}
