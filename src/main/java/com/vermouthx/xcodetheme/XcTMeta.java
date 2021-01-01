package com.vermouthx.xcodetheme;

import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.extensions.PluginId;

public class XcTMeta {

    private XcTMeta() {
    }

    public final static String XC_PLUGIN_ID = "com.vermouthx.xcode-theme";

    public static String currentVersion() {
        return PluginManagerCore.getPlugin(PluginId.getId(XC_PLUGIN_ID)).getVersion();
    }
}
