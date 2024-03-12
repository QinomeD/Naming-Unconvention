package qinomed.namingunconvention.mixin;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldGenSettingsComponent;
import net.minecraft.world.level.DataPackConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import qinomed.namingunconvention.NamingUnconvention;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin {
    @Shadow private String initName;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void randomName(Screen p_100861_, DataPackConfig p_100862_, WorldGenSettingsComponent p_100863_, CallbackInfo ci) {
        this.initName = NamingUnconvention.generateRandomName();
    }
}