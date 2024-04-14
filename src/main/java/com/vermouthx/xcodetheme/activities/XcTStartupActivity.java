package com.vermouthx.xcodetheme.activities;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import com.vermouthx.xcodetheme.XcTManager;
import com.vermouthx.xcodetheme.notifications.XcTNotification;
import com.vermouthx.xcodetheme.settings.XcTMetaSetting;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class XcTStartupActivity implements ProjectActivity, DumbAware {

    @Nullable
    @Override
    public Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
        String currentVersion = XcTManager.currentVersion();
        XcTMetaSetting setting = ApplicationManager.getApplication().getService(XcTMetaSetting.class);
        if (setting.getVersion() == null || setting.getVersion().isEmpty()) {
            setting.setVersion(currentVersion);
            XcTNotification.notifyWelcome(project);
            return null;
        }
        if (!currentVersion.equals(setting.getVersion())) {
            setting.setVersion(currentVersion);
            XcTNotification.notifyReleaseNote(project);
        }
        return null;
    }
}
