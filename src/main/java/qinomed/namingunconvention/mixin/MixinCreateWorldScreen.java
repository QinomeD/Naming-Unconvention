package qinomed.namingunconvention.mixin;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import qinomed.namingunconvention.ICreateWorldScreenBridge;
import qinomed.namingunconvention.NamingUnconvention;
import qinomed.namingunconvention.RandomNameGenerator;

@Mixin(CreateWorldScreen.class)
public abstract class MixinCreateWorldScreen extends Screen implements ICreateWorldScreenBridge {
  protected MixinCreateWorldScreen(Component title) {
    super(title);
  }

  @Unique
  private static final Identifier BTN_REROLL = Identifier.fromNamespaceAndPath(
      NamingUnconvention.MOD_ID, "reroll"
  );

  @Unique
  private static final Component REROLL_TOOLTIP =
      Component.translatable("button.naming_unconvention.reroll");

  @Shadow @Final
  private WorldCreationUiState uiState;

  @Unique
  private EditBox nameEdit;

  @Override
  public void naming_unconvention$setNameEdit(EditBox nameEdit) {
    this.nameEdit = nameEdit;
  }

  @Inject(method = "<init>", at = @At("TAIL"))
  private void randomName(CallbackInfo ci) {
    this.uiState.setName(RandomNameGenerator.generateRandomName());
  }

  @Inject(method = "init", at = @At("TAIL"))
  private void addButton(CallbackInfo ci) {
    if (this.nameEdit == null) {
      return;
    }

    WidgetSprites sprites = new WidgetSprites(BTN_REROLL);
    int x = this.nameEdit.getX() + this.nameEdit.getWidth() + 4;
    int y = this.nameEdit.getY();
    int buttonDim = 20;

    ImageButton rerollButton = this.addRenderableWidget(new ImageButton(
        x,
        y,
        buttonDim,
        buttonDim,
        sprites,
        button -> {
          String newName = RandomNameGenerator.generateRandomName();
          this.uiState.setName(newName);
          this.nameEdit.setValue(newName);
        },
        REROLL_TOOLTIP
    ));

    rerollButton.setTooltip(Tooltip.create(REROLL_TOOLTIP));
  }
}