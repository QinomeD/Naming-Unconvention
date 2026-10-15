package qinomed.namingunconvention;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.fml.config.ModConfig;

public class NamingUnconvention implements ClientModInitializer {

    public static String MODID = "naming_unconvention";
    public static RandomNameGenerator RANDOM_NAME_GENERATOR = new RandomNameFabric();

    @Override
    public void onInitializeClient() {
        ForgeConfigRegistry.INSTANCE.register(MODID, ModConfig.Type.CLIENT, Config.SPEC);
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener((IdentifiableResourceReloadListener) RANDOM_NAME_GENERATOR);
    }

    private static class RandomNameFabric extends RandomNameGenerator implements IdentifiableResourceReloadListener {
        @Override
        public ResourceLocation getFabricId() {
            return new ResourceLocation(MODID, "resources");
        }
    }
}
