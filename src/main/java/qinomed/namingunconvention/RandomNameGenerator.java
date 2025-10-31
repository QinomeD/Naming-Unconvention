package qinomed.namingunconvention;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

import java.io.IOException;
import java.util.Random;

public class RandomNameGenerator implements ResourceManagerReloadListener {
    String[] adjectives;
    String[] nouns;
    String[] locations;
    String[] compositions;

    public String generateRandomName() {
        Random random = new Random();

        return compositions[random.nextInt(0, compositions.length)]
                .replace("#", adjectives[random.nextInt(0, adjectives.length)])
                .replace("@", locations[random.nextInt(0, locations.length)])
                .replace("&", nouns[random.nextInt(0, nouns.length)]);
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        try {
            adjectives = readFileLines("adjectives.txt", resourceManager);
            nouns = readFileLines("nouns.txt", resourceManager);
            locations = readFileLines("locations.txt", resourceManager);
            compositions = readFileLines("compositions.txt", resourceManager);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] readFileLines(String filename, ResourceManager resourceManager) throws IOException {
        return resourceManager.getResourceOrThrow(new ResourceLocation(NamingUnconvention.MODID, filename)).openAsReader().lines().toArray(String[]::new);
    }
}
