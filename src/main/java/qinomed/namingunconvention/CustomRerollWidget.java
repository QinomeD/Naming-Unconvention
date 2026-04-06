package qinomed.namingunconvention;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class CustomRerollWidget extends AbstractWidget {
  private static final String TEXTURE_PATH = "textures/reroll.png";
  private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(NamingUnconvention.MOD_ID, TEXTURE_PATH);
  private static final int SPRITE_WIDTH = 20;
  private static final int SPRITE_HEIGHT = 20;
  private static final int TEXTURE_WIDTH = 20;
  private static final int TEXTURE_HEIGHT = 40;
  private final Runnable onPress;

  public CustomRerollWidget(int x, int y, Runnable onPress) {
    super(x, y, SPRITE_WIDTH, SPRITE_HEIGHT,
        Component.translatable("button.naming_unconvention.reroll"));
    this.onPress = onPress;
    this.setTooltip(Tooltip.create(this.getMessage()));
  }

  @Override
  protected void extractWidgetRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
    if (!this.visible) {
      return;
    }

    float u = 0.0F;
    float v = this.isHovered() ? 0.0F : SPRITE_HEIGHT;
    int x = this.getX();
    int y = this.getY();
    int width = this.width;
    int height = this.height;

    graphics.blit(
        RenderPipelines.GUI_TEXTURED,
        TEXTURE,
        x,
        y,
        u,
        v,
        width,
        height,
        TEXTURE_WIDTH,
        TEXTURE_HEIGHT
    );
  }

  @Override
  public void onClick(final @NonNull MouseButtonEvent event, final boolean doubleClick) {
    this.onPress.run();
  }

  @Override
  protected void updateWidgetNarration(@NonNull NarrationElementOutput builder) {

  }
}