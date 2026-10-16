package qinomed.namingunconvention.mixin;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.tabs.TabManager;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import qinomed.namingunconvention.Config;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin extends Screen {
    @Shadow
    @Final
    private TabManager tabManager;

    protected CreateWorldScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "repositionElements", remap = false, at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/components/tabs/TabManager;setTabArea(Lnet/minecraft/client/gui/navigation/ScreenRectangle;)V",
            shift = At.Shift.AFTER
    ))
    private void repositionButton(CallbackInfo ci) {
        if (Config.BUTTON_ENABLED.get() && tabManager.getCurrentTab() instanceof CreateWorldScreen.GameTab) {
            EditBox nameEdit = (EditBox) this.children().get(4);

            ((ImageButton) this.children().getLast()).setPosition(
                    nameEdit.getX() + nameEdit.getWidth() + 4 + Config.X_OFFSET.get(),
                    nameEdit.getY() + Config.Y_OFFSET.get()
            );
        }
    }
}
