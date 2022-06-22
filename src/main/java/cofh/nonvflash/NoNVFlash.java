package cofh.nonvflash;

import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod ("no_nv_flash")
public class NoNVFlash {

    public static Supplier<Boolean> fadeOut;
    public static Supplier<Integer> fadeTicks;
    public static Supplier<Double> maxBrightness;
    public static Supplier<Double> fadeRate = () -> maxBrightness.get() / fadeTicks.get();

    public NoNVFlash() {

        Config.register();
    }

}
