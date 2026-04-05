package qinomed.namingunconvention;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

import java.io.IOException;
import java.util.Random;

public class RandomNameGenerator {
  private static String[] readFileLines(String filename) throws IOException{
    return Minecraft.getInstance()
        .getResourceManager()
        .openAsReader(Identifier.fromNamespaceAndPath("naming_unconvention", filename))
        .lines().toArray(String[]::new);
  }

  public static String generateRandomName() {
    try {
      Random random = new Random();
      String[] adjectives = readFileLines("adjectives.txt");
      String[] nouns = readFileLines("nouns.txt");
      String[] locations = readFileLines("locations.txt");
      String[] compositions = readFileLines("compositions.txt");
      return compositions[random.nextInt(0, compositions.length)]
          .replace("#", adjectives[random.nextInt(0, adjectives.length)])
          .replace("@", locations[random.nextInt(0, locations.length)])
          .replace("&", nouns[random.nextInt(0, nouns.length)]);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
