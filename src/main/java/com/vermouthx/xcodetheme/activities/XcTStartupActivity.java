package com.vermouthx.xcodetheme.activities;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.vermouthx.xcodetheme.XcTMeta;
import com.vermouthx.xcodetheme.settings.XcTMetaSetting;
import org.jetbrains.annotations.NotNull;

public class XcTStartupActivity implements StartupActivity, DumbAware {

    @Override
    public void runActivity(@NotNull Project project) {
        String currentVersion = XcTMeta.currentVersion();
        XcTMetaSetting setting = ServiceManager.getService(XcTMetaSetting.class);
        if (!currentVersion.equals(setting.getVersion())) {
            setting.setVersion(currentVersion);
        }
    }
}
