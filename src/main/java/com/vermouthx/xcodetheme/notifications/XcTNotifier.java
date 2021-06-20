package com.vermouthx.xcodetheme.notifications;

import com.intellij.notification.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.vermouthx.xcodetheme.XcTManager;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class XcTNotifier {

    @Language("HTML")
    private static final String RELEASE_NOTE = "<div>" +
            "<h3>What's New</h3>" +
            "<ul>" +
            "<li>Support IntelliJ 2021.2 EAP</li>" +
            "</ul>" +
            "<p><a href=\"https://github.com/WhiteVermouth/XcodeTheme/blob/master/CHANGELOG.md\">Changelog</a> | <a href=\"https://github.com/WhiteVermouth/XcodeTheme\">Star Repository</a></p>" +
            "</div>";

    @Language("HTML")
    private final static String WELCOME_MESSAGE = "<div>" +
            "<p>Thank you for choosing <span>Xcode Theme</span>.</p>" +
            "<p><a href=\"https://github.com/WhiteVermouth/XcodeTheme/blob/master/CHANGELOG.md\">Changelog</a> | <a href=\"https://github.com/WhiteVermouth/XcodeTheme\">Star Repository</a></p>" +
            "</div>";

    private static final String NOTIFICATION_GROUP_ID = "xcode-theme";

    private static final Icon icon = IconLoader.getIcon("/icons/logo.png", XcTNotifier.class);

    private static final NotificationGroup NOTIFICATION_GROUP = NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID);

    public static void notifyReleaseNote(@Nullable Project project) {
        Notification notification = NOTIFICATION_GROUP.createNotification(
                "Xcode Theme updated to v" + XcTManager.currentVersion(),
                RELEASE_NOTE,
                NotificationType.INFORMATION,
                NotificationListener.URL_OPENING_LISTENER
        );
        notification.setIcon(icon);
        notification.notify(project);
    }

    public static void notifyWelcome(@Nullable Project project) {
        Notification notification = NOTIFICATION_GROUP.createNotification(
                "Xcode Theme installed successfully",
                WELCOME_MESSAGE,
                NotificationType.INFORMATION,
                NotificationListener.URL_OPENING_LISTENER
        );
        notification.setIcon(icon);
        notification.notify(project);
    }

}
