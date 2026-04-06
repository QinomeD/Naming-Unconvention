package qinomed.namingunconvention.mixin;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import qinomed.namingunconvention.ICreateWorldScreenBridge;
import qinomed.namingunconvention.RandomNameGenerator;
import qinomed.namingunconvention.CustomRerollWidget;

@Mixin(CreateWorldScreen.class)
public abstract class MixinCreateWorldScreen extends Screen implements ICreateWorldScreenBridge {
  protected MixinCreateWorldScreen(Component title) {
    super(title);
  }

  @Unique private EditBox nameEdit;
  @Unique private CustomRerollWidget rerollButton;
  @Shadow @Final private WorldCreationUiState uiState;

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

    int x = this.nameEdit.getX() + this.nameEdit.getWidth() + 4;
    int y = this.nameEdit.getY();

    this.rerollButton = this.addRenderableWidget(new CustomRerollWidget(x, y, () -> {
      String newName = RandomNameGenerator.generateRandomName();
      this.uiState.setName(newName);
      this.nameEdit.setValue(newName);
    }));
  }

  @Inject(method = "extractRenderState", at = @At("HEAD"))
  private void syncRerollButton(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
    if (this.rerollButton == null || this.nameEdit == null) {
      return;
    }

    boolean onGameTab = this.children().contains(this.nameEdit);

    this.rerollButton.visible = onGameTab;
    this.rerollButton.active = onGameTab;

    if (onGameTab) {
      this.rerollButton.setX(this.nameEdit.getX() + this.nameEdit.getWidth() + 4);
      this.rerollButton.setY(this.nameEdit.getY());
    }
  }
}