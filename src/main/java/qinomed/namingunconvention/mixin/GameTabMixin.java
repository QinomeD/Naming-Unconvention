package qinomed.namingunconvention.mixin;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.tabs.GridLayoutTab;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
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

@Mixin(CreateWorldScreen.GameTab.class)
public class GameTabMixin {
    @Unique
    private ResourceLocation BTN_REROLL = new ResourceLocation(NamingUnconvention.MODID, "textures/reroll.png");

    @Shadow @Final
    public EditBox nameEdit;
    @Shadow
    @Final
    CreateWorldScreen this$0;
    @Unique
    private Button rerollButton;

    @Inject(method = "<init>", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void randomName(CreateWorldScreen screen, CallbackInfo ci, GridLayout.RowHelper gridlayout$rowhelper1) {
        this.nameEdit.setValue(NamingUnconvention.RANDOM_NAME_GENERATOR.generateRandomName());

        GridLayout.RowHelper helperGrid = (new GridLayout()).rowSpacing(2).createRowHelper(2);

        if (((GridLayoutTab) ((Object) this)).layout.children.get(0) instanceof GridLayout worldNameGrid) {
            helperGrid.addChild(worldNameGrid.children.get(1), helperGrid.newCellSettings().padding(1));
            helperGrid.addChild(new ImageButton(0, 0, 20, 20, 0, -20, 20, BTN_REROLL, 20, 40, (press) -> {
                this.nameEdit.setValue(NamingUnconvention.RANDOM_NAME_GENERATOR.generateRandomName());
            }), helperGrid.newCellSettings().paddingHorizontal(4));
            worldNameGrid.cellInhabitants.set(1, new GridLayout.CellInhabitant())
            worldNameGrid.children.set(1, helperGrid.getGrid());
        }
        /*this.rerollButton = new ImageButton(this.nameEdit.getX() + this.nameEdit.getWidth(), this.nameEdit.getY(), 20, 20, 0, -20, 20, BTN_REROLL, 20, 40, (press) -> {
            this.nameEdit.setValue(NamingUnconvention.RANDOM_NAME_GENERATOR.generateRandomName());
        });
        this$0.addRenderableWidget(rerollButton);*/

        /*this.rerollButton = (new ImageButton(this.nameEdit.getX() + 210, 60, 20, 20, 0, -20, 20, BTN_REROLL, 20, 40, (press) -> {
            this.nameEdit.setValue(NamingUnconvention.RANDOM_NAME_GENERATOR.generateRandomName());
        }));*/
    }
}
