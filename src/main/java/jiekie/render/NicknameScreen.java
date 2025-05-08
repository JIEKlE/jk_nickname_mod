package jiekie.render;

import jiekie.NicknameMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class NicknameScreen extends Screen {
    private TextFieldWidget nicknameField;
    private ButtonWidget confirmButton;
    private ButtonWidget closeButton;
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
                int boxWidth = 200;
                int boxHeight = 100;
                int left = centerX - (boxWidth / 2);
                int top = centerY - (boxHeight / 2);
                int right = centerX + (boxWidth / 2);
                int bottom = centerY + (boxHeight / 2);
                context.fill(left, top, right, bottom, 0xAA000000);
            }
        });

        // label
        this.addDrawable(new Drawable() {
            @Override
            public void render(DrawContext context, int mouseX, int mouseY, float delta) {
                context.drawCenteredTextWithShadow(textRenderer, Text.of("닉네임 입력"), centerX, centerY - 35, 0xFFFFFFFF);
            }
        });

        // text field
        nicknameField = new TextFieldWidget(textRenderer, centerX - 70, centerY - 15, 140, 18, Text.of("닉네임"));
        this.addDrawableChild(nicknameField);
        this.setInitialFocus(nicknameField);

        // confirm button
        confirmButton = ButtonWidget.builder(Text.of("확인"), button -> {
            String nickname = nicknameField.getText().trim();
            if(!nickname.isEmpty()) {
                MinecraftClient.getInstance().player.networkHandler.sendChatCommand("닉네임 설정 " + nickname);
                MinecraftClient.getInstance().setScreen(null);
            }
        }).dimensions(centerX - 45, centerY + 15, 40, 18).build();
        this.addDrawableChild(confirmButton);

        // close button
        closeButton = ButtonWidget.builder(Text.of("닫기"), button -> {
            MinecraftClient.getInstance().setScreen(null);
        }).dimensions(centerX, centerY + 15, 40, 18).build();
        this.addDrawableChild(closeButton);
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
