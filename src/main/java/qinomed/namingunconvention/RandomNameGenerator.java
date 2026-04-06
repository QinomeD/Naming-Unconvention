package qinomed.namingunconvention;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

import java.io.IOException;
import java.util.Random;

public class RandomNameGenerator {
  public static String generateRandomName() {
    String[] adjectives;
    String[] nouns;
    String[] locations;
    String[] compositions;

    try {
      Random random = new Random();
      adjectives = readFileLines("adjectives.txt");
      nouns = readFileLines("nouns.txt");
      locations = readFileLines("locations.txt");
      compositions = readFileLines("compositions.txt");
      return compositions[random.nextInt(0, compositions.length)]
          .replace("#", adjectives[random.nextInt(0, adjectives.length)])
          .replace("@", locations[random.nextInt(0, locations.length)])
          .replace("&", nouns[random.nextInt(0, nouns.length)]);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static String[] readFileLines(String filename) throws IOException{
    return Minecraft.getInstance()
        .getResourceManager()
        .openAsReader(Identifier.fromNamespaceAndPath("naming_unconvention", filename))
        .lines().toArray(String[]::new);
  }
}
