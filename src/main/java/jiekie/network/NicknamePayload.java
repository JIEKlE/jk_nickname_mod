package jiekie.network;

import jiekie.NicknameMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class NicknamePayload implements CustomPayload {
    public static final Id<NicknamePayload> ID = new Id<>(Identifier.of(NicknameMod.MOD_ID, "set_nickname"));
    public static final PacketCodec<RegistryByteBuf, NicknamePayload> CODEC =
            PacketCodec.ofStatic(NicknamePayload::write, NicknamePayload::read).cast();

    public static void write(RegistryByteBuf buf, NicknamePayload payload) {}

    public static NicknamePayload read(RegistryByteBuf buf) {
        return new NicknamePayload();
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
