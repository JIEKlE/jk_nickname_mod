package jiekie;

import jiekie.network.NicknamePayload;
import jiekie.render.NicknameScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class NicknameModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PayloadTypeRegistry.playS2C().register(NicknamePayload.ID, NicknamePayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(NicknamePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                MinecraftClient.getInstance().setScreen(new NicknameScreen());
            });
        });
    }
}
