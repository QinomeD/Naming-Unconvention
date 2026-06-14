package qinomed.namingunconvention;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NamingUnconvention.MODID)
@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class NamingUnconvention {
    public static final String MODID = "naming_unconvention";
    public static final RandomNameGenerator RANDOM_NAME_GENERATOR = new RandomNameGenerator();

    public NamingUnconvention(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);

        // Register ourselves for server and other game events we are interested in
        // NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void registerReloadListener(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(RANDOM_NAME_GENERATOR);
    }
}
