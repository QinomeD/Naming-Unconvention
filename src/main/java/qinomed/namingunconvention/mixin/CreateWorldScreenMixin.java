package qinomed.namingunconvention.mixin;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldGenSettingsComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.DataPackConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import qinomed.namingunconvention.NamingUnconvention;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {
    private ResourceLocation BTN_REROLL = new ResourceLocation(NamingUnconvention.MODID, "textures/reroll.png");

    @Shadow private String initName;

    @Shadow protected abstract <T extends GuiEventListener & Widget & NarratableEntry> T addRenderableWidget(T pWidget);

    @Shadow public EditBox nameEdit;
    @Unique private Button rerollButton;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void randomName(Screen p_100861_, DataPackConfig p_100862_, WorldGenSettingsComponent p_100863_, CallbackInfo ci) {
        this.initName = NamingUnconvention.generateRandomName();
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void addButton(CallbackInfo ci) {
        this.rerollButton = this.addRenderableWidget(new ImageButton(this.nameEdit.x + 210, 60, 20, 20, 0, 0, 0, BTN_REROLL, 32, 32, (press) -> {
            this.nameEdit.setValue(NamingUnconvention.generateRandomName());
        }));
    }

    @Inject(method = "setWorldGenSettingsVisible", at = @At("TAIL"))
    private void toggleRerollVisibility(boolean pWorldGenSettingsVisible, CallbackInfo ci) {
        if (rerollButton != null) {
            this.rerollButton.visible = !pWorldGenSettingsVisible;
        }
    }
}