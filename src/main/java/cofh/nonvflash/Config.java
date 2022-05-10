package cofh.nonvflash;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static net.minecraftforge.fml.config.ModConfig.Type.CLIENT;

public class Config {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        FMLJavaModLoadingContext.get().getModEventBus().register(Config.class);
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

        clientFadeOut = CLIENT_CONFIG
                .comment("If TRUE, Night Vision brightness will gradually fade over a number of ticks instead of abruptly stopping.")
                .define("Fade Out", true);

        clientFadeOutTicks = CLIENT_CONFIG
                .comment("If the fade out option is enabled (TRUE), adjust this value to change the duration of the fade.")
                .defineInRange("Fade Out Ticks", 20, 10, 200);

        clientMaxBrightness = CLIENT_CONFIG
                .comment("Adjust this value to change the default brightness of the Night Vision effect. Setting this to 0 will effectively disable it.")
                .defineInRange("Max Brightness", 1.0, 0.0, 1.0);

        clientSpec = CLIENT_CONFIG.build();

        refreshClientConfig();
    }

    private static void refreshClientConfig() {

        NoNVFlash.fadeOut = clientFadeOut.get();
        NoNVFlash.fadeTicks = clientFadeOutTicks.get();
        NoNVFlash.maxBrightness = clientMaxBrightness.get().floatValue();

        NoNVFlash.fadeRate = NoNVFlash.maxBrightness / NoNVFlash.fadeTicks;
    }

    // region CONFIGURATION
    @SubscribeEvent
    public static void configLoading(ModConfigEvent.Loading event) {

        if (event.getConfig().getType() == CLIENT) {
            refreshClientConfig();
        }
    }

    @SubscribeEvent
    public static void configReloading(ModConfigEvent.Reloading event) {

        if (event.getConfig().getType() == CLIENT) {
            refreshClientConfig();
        }
    }
    // endregion

    // region VARIABLES
    public static BooleanValue clientFadeOut;
    public static IntValue clientFadeOutTicks;
    public static DoubleValue clientMaxBrightness;
    // endregion
}