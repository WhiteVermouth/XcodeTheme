package com.vermouthx.xcodetheme;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.extensions.PluginId;

import java.util.Objects;

public class XcTManager {

    private XcTManager() {
    }

    public final static String XC_PLUGIN_ID = "com.vermouthx.xcode-theme";

    public static String currentVersion() {
        return Objects.requireNonNull(PluginManager.getPlugin(PluginId.getId(XC_PLUGIN_ID))).getVersion();
    }
}
