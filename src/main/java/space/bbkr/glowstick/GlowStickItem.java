package space.bbkr.glowstick;

import grondag.frex.api.light.ItemLight;
import grondag.frex.api.light.ItemLightProvider;
import grondag.frex.impl.light.ItemLightLoader;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GlowStickItem extends Item implements ItemLightProvider {
	public GlowStickItem(Settings settings) {
		super(settings);
	}

	@Override
	public ItemLight getItemLight(ItemStack stack) {
		if (stack.getOrCreateTag().getInt("CustomModelData") == 1) {
			return ItemLight.of(0.93F, 0.3725F, 0.9255F, 0.5804F, true);
		}
		return ItemLightLoader.get(stack);
	}

}
