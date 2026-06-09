package bee.flowers.mixin;

import bee.flowers.Flowersgalore;
import bee.flowers.block.BreedableFlower;
import bee.flowers.block.PottedFlowersGaloreFlower;
import bee.flowers.block.TallBreedableFlower;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(Blocks.class)
public class BlocksMixin {

    @Shadow
    @Final
    public static Block POPPY;

    @Shadow
    @Final
    public static Block DANDELION;

    @Shadow
    @Final
    public static Block OXEYE_DAISY;

    @Shadow
    @Final
    public static Block AZURE_BLUET;

    @Shadow
    @Final
    public static Block BLUE_ORCHID;

    @Shadow
    @Final
    public static Block CORNFLOWER;

    @Shadow
    @Final
    public static Block ALLIUM;

    @Shadow
    @Final
    public static Block LILY_OF_THE_VALLEY;

    @Shadow
    @Final
    public static Block RED_TULIP;

    @ModifyVariable(method = "register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", at = @At(value = "HEAD"), argsOnly = true)
    private static Function<BlockBehaviour.Properties, Block> wawa(Function<BlockBehaviour.Properties, Block> value, String id) {
        if (Flowersgalore.FLOWERS.contains(id)) return instance -> new BreedableFlower(SuspiciousStewEffects.EMPTY, instance);
        if (Flowersgalore.TALL_FLOWERS.contains(id)) return TallBreedableFlower::new;
        return switch (id) {
            case "red_tulip", "orange_tulip", "pink_tulip", "white_tulip" -> instance -> new BreedableFlower(SuspiciousStewEffects.EMPTY, instance);
            case "potted_poppy" -> instance -> new PottedFlowersGaloreFlower(POPPY, instance);
            case "potted_dandelion" -> instance -> new PottedFlowersGaloreFlower(DANDELION, instance);
            case "potted_oxeye_daisy" -> instance -> new PottedFlowersGaloreFlower(OXEYE_DAISY, instance);
            case "potted_azure_bluet" -> instance -> new PottedFlowersGaloreFlower(AZURE_BLUET, instance);
            case "potted_blue_orchid" -> instance -> new PottedFlowersGaloreFlower(BLUE_ORCHID, instance);
            case "potted_cornflower" -> instance -> new PottedFlowersGaloreFlower(CORNFLOWER, instance);
            case "potted_lily_of_the_valley" -> instance -> new PottedFlowersGaloreFlower(LILY_OF_THE_VALLEY, instance);
            case "potted_allium" -> instance -> new PottedFlowersGaloreFlower(ALLIUM, instance);
            default -> value;
        };
    }

    @Inject(method = "register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", at = @At(value = "HEAD"), cancellable = true)
    private static void wawa(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, CallbackInfoReturnable<Block> cir) {
        if (id.contains("tulip") && !id.contains("red")) {

        }
    }

}
