package qinomed.namingunconvention.mixin;

import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.tabs.GridLayoutTab;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import qinomed.namingunconvention.Config;
import qinomed.namingunconvention.NamingUnconvention;

import java.util.function.Consumer;

@Mixin(CreateWorldScreen.GameTab.class)
public class GameTabMixin extends GridLayoutTab{
    @Unique
    private WidgetSprites rerollButtonSprite = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath(NamingUnconvention.MODID, "reroll"),
            ResourceLocation.fromNamespaceAndPath(NamingUnconvention.MODID, "reroll_focused")
    );

    @Shadow @Final
    private EditBox nameEdit;

    @Unique
    private Button rerollButton;

    public GameTabMixin(Component pTitle) {
        super(pTitle);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void randomName(CreateWorldScreen screen, CallbackInfo ci) {
        this.nameEdit.setValue(NamingUnconvention.RANDOM_NAME_GENERATOR.generateRandomName());

        if (Config.BUTTON_ENABLED.get()) {
            /*this.rerollButton = new ImageButton(220 + this.nameEdit.getWidth() + Config.X_OFFSET.get(), 66 + Config.Y_OFFSET.get(), 20, 20, 0, -20, 20, BTN_REROLL, 20, 40, (press) -> {
                this.nameEdit.setValue(NamingUnconvention.RANDOM_NAME_GENERATOR.generateRandomName());
            });*/

            this.rerollButton = new ImageButton(
                    0, 0, // to be repositioned in CreateWorldScreenMixin
                    20, 20,
                    rerollButtonSprite,
                    (press) -> this.nameEdit.setValue(NamingUnconvention.RANDOM_NAME_GENERATOR.generateRandomName())
            );
        }
    }

    @Override
    public void visitChildren(Consumer<AbstractWidget> pConsumer) {
        super.visitChildren(pConsumer);
        if (Config.BUTTON_ENABLED.get()) {
            pConsumer.accept(rerollButton);
        }
    }
}
