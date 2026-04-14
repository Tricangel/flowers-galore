package bee.flowers.item;

import bee.flowers.registry.FlowersGaloreItemComponents;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public record RoseColourTintSource(int colour) implements ItemTintSource {

    public static final MapCodec<RoseColourTintSource> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ExtraCodecs.RGB_COLOR_CODEC.fieldOf("color").forGetter(RoseColourTintSource::colour)
            ).apply(instance, RoseColourTintSource::new)
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
