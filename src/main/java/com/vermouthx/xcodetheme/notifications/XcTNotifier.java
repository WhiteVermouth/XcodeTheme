package com.vermouthx.xcodetheme.notifications;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.vermouthx.xcodetheme.XcTMeta;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class XcTNotifier {

    private final static String RELEASE_NOTE = "What's New?<br>" +
            "<ul>" +
            "<li>Enhanced color schemes</li>" +
            "<li>Adopt new plugin logo</li>" +
            "</ul>" +
            "Please visit the <a href=\"https://github.com/WhiteVermouth/XcodeTheme/blob/master/CHANGELOG.md\">Changelog</a> for more details.<br>" +
            "Helpful? Consider <a href=\"https://github.com/WhiteVermouth/XcodeTheme\">STAR</a> this project.";

    private final static String WELCOME_MESSAGE = "Thank you for choosing Xcode Theme. " +
            "Xcode Theme is fully open-source. If this extension is helpful to you, consider <a href=\"https://github.com/WhiteVermouth/XcodeTheme\">STAR</a> this project.";

    private final static Icon icon = IconLoader.getIcon("/icons/logo.png", XcTNotifier.class);
    private final static String NOTIFICATION_GROUP_ID = "xcode-theme-notification-group";

    public static void notifyReleaseNote(@Nullable Project project) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup(NOTIFICATION_GROUP_ID)
                .createNotification(
                        "Xcode Theme updated to v" + XcTMeta.currentVersion(),
                        RELEASE_NOTE,
                        NotificationType.INFORMATION,
                        NotificationListener.URL_OPENING_LISTENER
                )
                .setIcon(icon)
                .notify(project);
    }

    public static void notifyWelcome(@Nullable Project project) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup(NOTIFICATION_GROUP_ID)
                .createNotification(
                        "Xcode Theme v" + XcTMeta.currentVersion() + "installed successfully",
                        WELCOME_MESSAGE,
                        NotificationType.INFORMATION,
                        NotificationListener.URL_OPENING_LISTENER
                )
                .setIcon(icon)
                .notify(project);
    }

}
