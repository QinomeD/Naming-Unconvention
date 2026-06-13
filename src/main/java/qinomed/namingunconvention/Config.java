package qinomed.namingunconvention;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static final Config INSTANCE;
    public static final ForgeConfigSpec SPEC;

    public static ForgeConfigSpec.ConfigValue<Boolean> BUTTON_ENABLED;
    public static ForgeConfigSpec.ConfigValue<Integer> X_OFFSET;
    public static ForgeConfigSpec.ConfigValue<Integer> Y_OFFSET;

    public Config(ForgeConfigSpec.Builder builder) {
        BUTTON_ENABLED = builder.define("buttonEnabled", true);
        X_OFFSET = builder.define("buttonXOffset", 0);
        Y_OFFSET = builder.define("buttonYOffset", 0);
    }

    static {
        Pair<Config, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Config::new);
        INSTANCE = pair.getLeft();
        SPEC = pair.getRight();
    }
}
