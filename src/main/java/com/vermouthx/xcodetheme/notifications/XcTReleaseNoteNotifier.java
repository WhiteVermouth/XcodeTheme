package com.vermouthx.xcodetheme.notifications;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.vermouthx.xcodetheme.XcTMeta;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class XcTReleaseNoteNotifier {

    private final static String RELEASE_NOTE = "What's New?<br>" +
            "<ul>" +
            "<li>Enhanced <em>Xcode Light</em> Color Scheme</li>" +
            "</ul>" +
            "Please visit the <a href=\"https://github.com/WhiteVermouth/XcodeTheme/blob/master/CHANGELOG.md\">Changelog</a> for more details.<br>";

    private final static Icon icon = IconLoader.getIcon("/icons/logo.svg", XcTReleaseNoteNotifier.class);

    public static void notifyReleaseNote(@Nullable Project project) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("xcode-theme-notification-group")
                .createNotification(
                        "Xcode Theme updated to v" + XcTMeta.currentVersion(),
                        RELEASE_NOTE,
                        NotificationType.INFORMATION,
                        NotificationListener.URL_OPENING_LISTENER
                )
                .setIcon(icon)
                .notify(project);
    }

}
