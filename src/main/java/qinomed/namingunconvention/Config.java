package qinomed.namingunconvention;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static final Config INSTANCE;
    public static final ModConfigSpec SPEC;

    public static ModConfigSpec.ConfigValue<Boolean> BUTTON_ENABLED;
    public static ModConfigSpec.ConfigValue<Integer> X_OFFSET;
    public static ModConfigSpec.ConfigValue<Integer> Y_OFFSET;

    public Config(ModConfigSpec.Builder builder) {
        builder.comment(" Reroll button");
        BUTTON_ENABLED = builder.define("buttonEnabled", true);
        X_OFFSET = builder.define("buttonXOffset", 0);
        Y_OFFSET = builder.define("buttonYOffset", 0);
    }

    static {
        Pair<Config, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(Config::new);
        INSTANCE = pair.getLeft();
        SPEC = pair.getRight();
    }
}
