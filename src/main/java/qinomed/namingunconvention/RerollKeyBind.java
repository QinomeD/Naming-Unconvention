package qinomed.namingunconvention;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class RerollKeyBind {
    public static final String KEY_CATEGORY = "key.category.naming_unconvention.naming_unconvention";
    public static final String KEY_REROLL = "key.naming_unconvention.reroll";

    public static final KeyMapping REROLL = new KeyMapping(KEY_REROLL, KeyConflictContext.GUI, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY);
}
