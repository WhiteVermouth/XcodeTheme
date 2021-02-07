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
            "<li>Enhanced UI and color scheme</li>" +
            "<li>Support JetBrains 2019 series (2019.2 at least)</li>" +
            "</ul>" +
            "<p>Please visit the <a href=\"https://github.com/WhiteVermouth/XcodeTheme/blob/master/CHANGELOG.md\">Changelog</a> for more details.</p>" +
            "<p>Enjoy this theme? Consider <a href=\"https://github.com/WhiteVermouth/XcodeTheme\">STAR</a> this project.</p>" +
            "</div>";

    @Language("HTML")
    private final static String WELCOME_MESSAGE = "Thank you for choosing Xcode Theme. " +
            "Xcode Theme is fully open-source. If this extension is helpful to you, consider <a href=\"https://github.com/WhiteVermouth/XcodeTheme\">STAR</a> this project.";

    private static final Icon icon = IconLoader.getIcon("/icons/logo.png", XcTNotifier.class);

    private static final NotificationGroup NOTIFICATION_GROUP =
            new NotificationGroup("Xcode Theme", NotificationDisplayType.STICKY_BALLOON, true);

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
