package com.vermouthx.xcodetheme;

import com.intellij.ide.plugins.cl.PluginAwareClassLoader;

public class XcTManager {
    public final static String XC_PLUGIN_ID = "com.vermouthx.xcode-theme";

    public static String currentVersion() {
        ClassLoader classLoader = XcTManager.class.getClassLoader();
        if (classLoader instanceof PluginAwareClassLoader) {
            return ((PluginAwareClassLoader) classLoader).getPluginDescriptor().getVersion();
        }
        return "";
    }
}
