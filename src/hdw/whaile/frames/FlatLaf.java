package frames;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import java.util.Collections;

public class FlatLaf {
    public static void start() {
        FlatMacDarkLaf.setup();
        com.formdev.flatlaf.FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@foreground", "#2B2B2B"));
        com.formdev.flatlaf.FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#D9D9D9"));
    }
}
