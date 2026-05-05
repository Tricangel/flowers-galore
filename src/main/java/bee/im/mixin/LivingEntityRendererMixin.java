package bee.im.mixin;

import bee.im.Gold;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.MovingBlockRenderState;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    @Inject(at = @At("HEAD"), method = "submit(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V", cancellable = true)
    private void init(LivingEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera, CallbackInfo ci) {

        if (Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player.hasEffect(Gold.GOLD)) {
                ClientLevel clientLevel = Minecraft.getInstance().level;
                poseStack.pushPose();
                poseStack.translate(-state.boundingBoxWidth / 2, 0, -state.boundingBoxWidth / 2);
                poseStack.scale(state.boundingBoxWidth, state.boundingBoxHeight, state.boundingBoxWidth);

                MovingBlockRenderState movingBlockRenderState = new MovingBlockRenderState();
                movingBlockRenderState.blockState = Blocks.GOLD_BLOCK.defaultBlockState();
                movingBlockRenderState.lightEngine = clientLevel.getLightEngine();
                movingBlockRenderState.cardinalLighting = clientLevel.cardinalLighting();
                movingBlockRenderState.randomSeedPos = player.getOnPos().above();
                movingBlockRenderState.blockPos = player.getOnPos().above();
                movingBlockRenderState.biome = clientLevel.getBiome(player.getOnPos());

                submitNodeCollector.submitMovingBlock(poseStack, movingBlockRenderState);
                poseStack.popPose();
                ci.cancel();

            }
        }
    }



}
