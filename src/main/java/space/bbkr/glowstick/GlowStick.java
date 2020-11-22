package space.bbkr.glowstick;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;

import com.mojang.serialization.Lifecycle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.GiveCommand;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GlowStick implements ModInitializer {

	@Override
	public void onInitialize() {
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			//I'm sorry.
			Registry.ITEM.set(
					Registry.ITEM.getRawId(Items.BLAZE_ROD),
					Registry.ITEM.getKey(Items.BLAZE_ROD).orElseThrow(NullPointerException::new),
					new GlowStickItem(new Item.Settings().group(ItemGroup.MATERIALS)),
					Lifecycle.stable()
			);
		}

		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
				dispatcher.register(
						CommandManager.literal("glowstick")
								.requires(source -> source.hasPermissionLevel(2))
								.executes(
										context -> dispatcher.execute(
												"give @p blaze_rod{CustomModelData:1,display:{Name:'[{\"text\":\"Lemma\\'s Glow Stick\",\"italic\":false,\"color\":\"#5fec94\"}]'}} 1",
												context.getSource()
										)
						)
				)
		);
	}
}
