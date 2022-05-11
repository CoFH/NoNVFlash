package cofh.nonvflash;

import net.minecraftforge.fml.common.Mod;

@Mod ("no_nv_flash")
public class NoNVFlash {

    public static boolean fadeOut = true;
    public static int fadeTicks = 20;
    public static float maxBrightness = 1.0F;
    public static float fadeRate = maxBrightness / fadeTicks;

    public NoNVFlash() {

        Config.register();
    }

}
