package qinomed.namingunconvention.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldGenSettingsComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.DataPackConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import qinomed.namingunconvention.NamingUnconvention;

import java.io.IOException;
import java.util.Random;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin {
    @Shadow private String initName;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void randomName(Screen p_100861_, DataPackConfig p_100862_, WorldGenSettingsComponent p_100863_, CallbackInfo ci) {
        try {
            Random random = new Random();

            String[] adjectives = readFileLines("adjectives.txt");
            String[] nouns = readFileLines("nouns.txt");
            String[] locations = readFileLines("locations.txt");
            String[] compositions = readFileLines("compositions.txt");

            String name = compositions[random.nextInt(0, compositions.length)]
                    .replace("#", adjectives[random.nextInt(0, adjectives.length)])
                    .replace("@", locations[random.nextInt(0, locations.length)])
                    .replace("&", nouns[random.nextInt(0, nouns.length)]);

            this.initName = name;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] readFileLines(String filename) throws IOException {
        return Minecraft.getInstance().getResourceManager().getResourceOrThrow(new ResourceLocation(NamingUnconvention.MODID, filename)).openAsReader().lines().toArray(String[]::new);
    }
}