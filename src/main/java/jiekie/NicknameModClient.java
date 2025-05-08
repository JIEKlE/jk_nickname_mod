package jiekie;

import jiekie.render.NicknameScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class NicknameModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // nickname shortcut
        KeyBinding openGuiKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.nickname.open", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Y, "categories.nickname")
        );
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(openGuiKey.wasPressed())
                client.setScreen(new NicknameScreen());
        });
    }
}
