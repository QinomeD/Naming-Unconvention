package qinomed.namingunconvention;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.util.Random;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NamingUnconvention.MODID)
public class NamingUnconvention {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "naming_unconvention";

    public NamingUnconvention() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static String generateRandomName() {
        try {
            Random random = new Random();

            // I know loading 4 txt files every time doesn't look good, but it's not used that often and it's not too slow either
            String[] adjectives = readFileLines("adjectives.txt");
            String[] nouns = readFileLines("nouns.txt");
            String[] locations = readFileLines("locations.txt");
            String[] compositions = readFileLines("compositions.txt");

            return compositions[random.nextInt(0, compositions.length)]
                    .replace("#", adjectives[random.nextInt(0, adjectives.length)])
                    .replace("@", locations[random.nextInt(0, locations.length)])
                    .replace("&", nouns[random.nextInt(0, nouns.length)]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] readFileLines(String filename) throws IOException {
        return Minecraft.getInstance().getResourceManager().getResourceOrThrow(new ResourceLocation(NamingUnconvention.MODID, filename)).openAsReader().lines().toArray(String[]::new);
    }

    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (event.getKey() == RerollKeyBind.REROLL.getKey().getValue() && event.getAction() == 1) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.screen instanceof CreateWorldScreen screen && screen.tabNavigationBar != null && screen.tabNavigationBar.tabs.get(0) instanceof CreateWorldScreen.GameTab gameTab) {
                    gameTab.nameEdit.setValue(generateRandomName());
                }
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(RerollKeyBind.REROLL);
        }
    }
}
