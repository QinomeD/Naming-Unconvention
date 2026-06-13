package qinomed.namingunconvention;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NamingUnconvention.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NamingUnconvention {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "naming_unconvention";
    public static final RandomNameGenerator RANDOM_NAME_GENERATOR = new RandomNameGenerator();

    public NamingUnconvention() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void registerReloadListener(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(RANDOM_NAME_GENERATOR);
    }
}
