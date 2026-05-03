package bee.im;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class Gold implements ModInitializer {

	//this is so stupid
	// never let me cook

	//biggest shitpost i have ever made

	public static class ImGold extends MobEffect {
		protected ImGold() {
			super(MobEffectCategory.BENEFICIAL, 13866546);
		}
	}

	public static final String MOD_ID = "im";
	public static final Holder<MobEffect> GOLD =
			Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(MOD_ID, "gold"),
					new ImGold());


	@Override
	public void onInitialize() {

	}
}