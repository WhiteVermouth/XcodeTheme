package com.vermouthx.xcodetheme.notifications;

import com.intellij.notification.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.vermouthx.xcodetheme.XcTManager;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class XcTNotification {

    @Language("HTML")
    private static final String RELEASE_NOTE = "<div>" +
            "<h3>What's New?</h3>" +
            "<ul>" +
            "<li>Support JetBrains 2022 EAP</li>" +
            "</ul>" +
            "<br>" +
            "<p><a href=\"https://github.com/WhiteVermouth/XcodeTheme/blob/master/CHANGELOG.md\">Changelog</a> | <a href=\"https://github.com/WhiteVermouth/XcodeTheme\">Repository</a></p>" +
            "</div>";

    @Language("HTML")
    private final static String WELCOME_MESSAGE = "<div>" +
            "<p>Thank you for choosing <span>Xcode Theme</span>.</p>" +
            "<br>" +
            "<p><a href=\"https://github.com/WhiteVermouth/XcodeTheme/blob/master/CHANGELOG.md\">Changelog</a> | <a href=\"https://github.com/WhiteVermouth/XcodeTheme\">Repository</a></p>" +
            "</div>";

    private static final String NOTIFICATION_GROUP_ID = "Xcode Theme";

    private static final Icon icon = IconLoader.getIcon("/icons/logo.png", XcTNotification.class);

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
                "Xcode Theme is installed",
                WELCOME_MESSAGE,
                NotificationType.INFORMATION,
                NotificationListener.URL_OPENING_LISTENER
        );
        notification.setIcon(icon);
        notification.notify(project);
    }

}
