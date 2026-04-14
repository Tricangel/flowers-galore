package bee.flowers.registry;

import bee.flowers.Flowersgalore;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;

public class FlowersGaloreItemComponents {
    public static void init() {
    }

    public static final DataComponentType<Integer> FLOWER_COLOUR = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Flowersgalore.MOD_ID, "flower_colour"),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    public static final DataComponentType<BlockState> PREVIOUS_BLOCK = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Flowersgalore.MOD_ID, "previous_block"),
            DataComponentType.<BlockState>builder().persistent(BlockState.CODEC).build()
    );
}
