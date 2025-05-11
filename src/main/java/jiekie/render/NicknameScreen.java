package jiekie.render;

import jiekie.NicknameMod;
import jiekie.widget.InvisibleButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class NicknameScreen extends Screen {
    private TextFieldWidget nicknameField;
    private InvisibleButton confirmButton;
    private static final Identifier Background = Identifier.of(NicknameMod.MOD_ID, "textures/gui/nickname_ui.png");

    public NicknameScreen() {
        super(Text.of("닉네임 입력"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // background
        this.addDrawable(new Drawable() {
            @Override
            public void render(DrawContext context, int mouseX, int mouseY, float delta) {
                context.drawTexture(Background, centerX - 135, centerY - 180, 0, 0, 270, 360, 270, 360);
            }
        });

        // text field
        nicknameField = new TextFieldWidget(textRenderer, centerX - 45, centerY + 50, 90, 18, Text.of("닉네임"));
        nicknameField.setDrawsBackground(false);
        nicknameField.setEditableColor(0XFF8C7A78);
        this.addDrawableChild(nicknameField);
        this.setInitialFocus(nicknameField);

        // confirm button
        confirmButton = new InvisibleButton(centerX - 15, centerY + 75, 30, 30, () -> {
            String nickname = nicknameField.getText().trim();
            if(!nickname.isEmpty()) {
                MinecraftClient.getInstance().player.networkHandler.sendChatCommand("닉네임 설정 " + nickname);
                MinecraftClient.getInstance().setScreen(null);
            }
        });
        this.addDrawableChild(confirmButton);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            MinecraftClient.getInstance().setScreen(null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
