package qinomed.namingunconvention.mixin;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import qinomed.namingunconvention.NamingUnconvention;

@Mixin(CreateWorldScreen.GameTab.class)
public class GameTabMixin {
    @Shadow @Final
    public EditBox nameEdit;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void randomName(CreateWorldScreen screen, CallbackInfo ci) {
        this.nameEdit.setValue(NamingUnconvention.generateRandomName());
    }
}
