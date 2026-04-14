package bee.flowers.block.entity;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.Display;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class VaseBlockEntityRenderState extends BlockEntityRenderState {
    public List<ItemStackRenderState> ingredients = new ArrayList<>();

}
