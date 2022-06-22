package cofh.nonvflash;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;

import static net.minecraftforge.fml.config.ModConfig.Type.CLIENT;

public class Config {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        registered = true;

        genClientConfig();

        ModLoadingContext.get().registerConfig(CLIENT, clientSpec);
    }

    private Config() {

    }

    // region CONFIG SPEC
    private static final ForgeConfigSpec.Builder CLIENT_CONFIG = new ForgeConfigSpec.Builder();
    private static ForgeConfigSpec clientSpec;

    private static void genClientConfig() {

        NoNVFlash.fadeOut = CLIENT_CONFIG
                .comment("If TRUE, Night Vision brightness will gradually fade over a number of ticks instead of abruptly stopping.")
                .define("Fade Out", true);

        NoNVFlash.fadeTicks = CLIENT_CONFIG
                .comment("If the fade out option is enabled (TRUE), adjust this value to change the duration of the fade.")
                .defineInRange("Fade Out Ticks", 20, 10, 200);

        NoNVFlash.maxBrightness = CLIENT_CONFIG
                .comment("Adjust this value to change the default brightness of the Night Vision effect. Setting this to 0 will effectively disable it.")
                .defineInRange("Max Brightness", 1.0, 0.0, 1.0);

        clientSpec = CLIENT_CONFIG.build();
    }
    // endregion
}