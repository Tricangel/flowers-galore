package bee.flowers.block.entity;

import bee.flowers.registry.FlowersGaloreItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemModels;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.List;

//based on Cassian's toil and trouble mod
//https://github.com/cassiancc/Toil-and-Trouble

public class VaseBlockRenderer implements BlockEntityRenderer<VaseBlockEntity, VaseBlockEntityRenderState> {
    private final ItemModelResolver itemRenderer;

    public VaseBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.itemModelResolver();
    }

    @Override
    public VaseBlockEntityRenderState createRenderState() {
        return new VaseBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(VaseBlockEntity blockEntity, VaseBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        NonNullList<ItemStack> items = blockEntity.getIngredients();
        for (ItemStack item : items) {
            ItemStackRenderState renderState = new ItemStackRenderState();
            this.itemRenderer.updateForTopItem(renderState, item, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, (int) blockEntity.getBlockPos().asLong());
            state.ingredients.add(renderState);
        }

    }

    @Override
    public void submit(VaseBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        List<ItemStackRenderState> items = state.ingredients;
        for (int i = 0; i < items.size(); i++) {
            poseStack.pushPose();
            poseStack.translate(.5, 1, .5);
            if (i == 0) {
                poseStack.mulPose(Axis.XP.rotation(-100));
                poseStack.mulPose(Axis.YP.rotation(15));
            }
            if (i == 1) {
                poseStack.mulPose(Axis.ZP.rotation(15));
                poseStack.mulPose(Axis.YP.rotation(-20));
            }
            if (i == 2) {
                poseStack.mulPose(Axis.XP.rotation(20));
                poseStack.mulPose(Axis.YP.rotation(-15));
            }
            if (i == 3) {
                poseStack.mulPose(Axis.ZP.rotation(-15));
                poseStack.mulPose(Axis.YP.rotation(20));
            }

            ItemStackRenderState itemStack = items.get(i);
            itemStack.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }

    }
}
