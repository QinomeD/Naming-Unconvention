package qinomed.namingunconvention;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NamingUnconvention.MODID)
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NamingUnconvention {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "naming_unconvention";
    public static final RandomNameGenerator RANDOM_NAME_GENERATOR = new RandomNameGenerator();

    public NamingUnconvention() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void registerReloadListener(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(RANDOM_NAME_GENERATOR);
    }
}
