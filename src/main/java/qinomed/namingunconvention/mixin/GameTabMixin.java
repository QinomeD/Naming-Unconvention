package qinomed.namingunconvention.mixin;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.tabs.GridLayoutTab;
import net.minecraft.client.gui.layouts.GridLayout;
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
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import qinomed.namingunconvention.NamingUnconvention;

import java.util.function.Consumer;

@Mixin(CreateWorldScreen.GameTab.class)
public class GameTabMixin extends GridLayoutTab{
    @Unique
    private ResourceLocation BTN_REROLL = new ResourceLocation(NamingUnconvention.MODID, "textures/reroll.png");

    @Shadow @Final
    public EditBox nameEdit;

    @Unique
    private Button rerollButton;

    public GameTabMixin(Component pTitle) {
        super(pTitle);
    }

    @Inject(method = "<init>", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void randomName(CreateWorldScreen screen, CallbackInfo ci, GridLayout.RowHelper gridlayout$rowhelper1) {
        this.nameEdit.setValue(NamingUnconvention.RANDOM_NAME_GENERATOR.generateRandomName());

        this.rerollButton = new ImageButton(216 + this.nameEdit.getWidth() + 4, 66, 20, 20, 0, -20, 20, BTN_REROLL, 20, 40, (press) -> {
            this.nameEdit.setValue(NamingUnconvention.RANDOM_NAME_GENERATOR.generateRandomName());
        });
    }

    @Override
    public void visitChildren(Consumer<AbstractWidget> pConsumer) {
        super.visitChildren(pConsumer);
        pConsumer.accept(rerollButton);
    }
}
