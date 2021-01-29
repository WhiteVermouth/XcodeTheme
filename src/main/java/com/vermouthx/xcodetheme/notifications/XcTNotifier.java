package com.vermouthx.xcodetheme.notifications;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.vermouthx.xcodetheme.XcTManager;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class XcTNotifier {

    @Language("HTML")
    private final static String RELEASE_NOTE = "What's New?<br>" +
            "<ul>" +
            "<li>Enhanced color schemes</li>" +
            "<li>Adopt new plugin logo</li>" +
            "</ul>" +
            "<p>Please visit the <a href=\"https://github.com/WhiteVermouth/XcodeTheme/blob/master/CHANGELOG.md\">Changelog</a> for more details.</p>" +
            "<p>Enjoy this theme? Consider <a href=\"https://github.com/WhiteVermouth/XcodeTheme\">STAR</a> this project.</p>";

    @Language("HTML")
    private final static String WELCOME_MESSAGE = "Thank you for choosing Xcode Theme. " +
            "Xcode Theme is fully open-source. If this extension is helpful to you, consider <a href=\"https://github.com/WhiteVermouth/XcodeTheme\">STAR</a> this project.";

    private final static Icon icon = IconLoader.getIcon("/icons/logo.png", XcTNotifier.class);
    private final static String NOTIFICATION_GROUP_ID = "Xcode Theme";

    public static void notifyReleaseNote(@Nullable Project project) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup(NOTIFICATION_GROUP_ID)
                .createNotification(
                        "Xcode Theme updated to v" + XcTManager.currentVersion(),
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
                        "Xcode Theme v" + XcTManager.currentVersion() + "installed successfully",
                        WELCOME_MESSAGE,
                        NotificationType.INFORMATION,
                        NotificationListener.URL_OPENING_LISTENER
                )
                .setIcon(icon)
                .notify(project);
    }

}
