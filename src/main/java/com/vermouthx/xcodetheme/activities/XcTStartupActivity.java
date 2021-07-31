package com.vermouthx.xcodetheme.activities;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.vermouthx.xcodetheme.XcTManager;
import com.vermouthx.xcodetheme.notifications.XcTNotification;
import com.vermouthx.xcodetheme.settings.XcTMetaSetting;
import org.jetbrains.annotations.NotNull;

public class XcTStartupActivity implements StartupActivity, DumbAware {

    @Override
    public void runActivity(@NotNull Project project) {
        String currentVersion = XcTManager.currentVersion();
        XcTMetaSetting setting = ServiceManager.getService(XcTMetaSetting.class);
        if (setting.getVersion() == null || setting.getVersion().isEmpty()) {
            setting.setVersion(currentVersion);
            XcTNotification.notifyWelcome(project);
            return;
        }
        if (!currentVersion.equals(setting.getVersion())) {
            setting.setVersion(currentVersion);
            XcTNotification.notifyReleaseNote(project);
        }
    }
}
