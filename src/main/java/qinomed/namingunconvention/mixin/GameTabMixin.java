package qinomed.namingunconvention.mixin;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import qinomed.namingunconvention.RandomNameGenerator;

@Mixin(targets = "net.minecraft.client.gui.screens.worldselection.CreateWorldScreen$GameTab")
public class GameTabMixin {
  @Shadow
  @Final
  private EditBox nameEdit;

  public GameTabMixin(){

  }

  @Inject(
      method ={"<init>"},
      at = {@At("TAIL")}
  )
  private void randomName(CreateWorldScreen screen, CallbackInfo ci){
    this.nameEdit.setValue(RandomNameGenerator.generateRandomName());
  }
}
