package jiekie.widget;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public class InvisibleButton extends ClickableWidget {
    private final Runnable onClick;

    public InvisibleButton(int x, int y, int width, int height, Runnable onClick) {
        super(x, y, width, height, Text.empty());
        this.onClick = onClick;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {

    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        this.onClick.run();
    }
}
