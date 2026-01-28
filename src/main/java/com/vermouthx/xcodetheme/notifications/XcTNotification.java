package com.vermouthx.xcodetheme.notifications;

import com.intellij.ide.BrowserUtil;
import com.intellij.notification.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.vermouthx.xcodetheme.XcTManager;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class XcTNotification {

    @Language("HTML")
    private static final String RELEASE_NOTE = "<ul>" + "<li>🎨 Fixed main window background color inconsistency in Islands Xcode themes</li>" + "</ul>";

    @Language("HTML")
    private final static String WELCOME_MESSAGE = "<p>Thank you for choosing <span>Xcode Theme</span>.</p>";

    private static final String NOTIFICATION_GROUP_ID = "Xcode Theme";
    private static final String DONATE_LINK = "https://www.buymeacoffee.com/nszihan";
    private static final String GITHUB_LINK = "https://github.com/WhiteVermouth/XcodeTheme";
    private static final Icon icon = IconLoader.getIcon("/icons/logo.png", XcTNotification.class);

    private static NotificationGroup getNotificationGroup() {
        return NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID);
    }

    public static void notifyReleaseNote(@Nullable Project project) {
        Notification notification = getNotificationGroup().createNotification("Xcode Theme updated to v" + XcTManager.currentVersion(), RELEASE_NOTE, NotificationType.INFORMATION);
        addNotificationActions(notification);
        notification.setIcon(icon);
        notification.notify(project);
    }

    public static void notifyWelcome(@Nullable Project project) {
        Notification notification = getNotificationGroup().createNotification("Xcode Theme is installed", WELCOME_MESSAGE, NotificationType.INFORMATION);
        addNotificationActions(notification);
        notification.setIcon(icon);
        notification.notify(project);
    }

    private static void addNotificationActions(Notification notification) {
        notification.addAction(NotificationAction.createSimple("GitHub", () -> BrowserUtil.browse(GITHUB_LINK)));
        notification.addAction(NotificationAction.createSimple("Donate", () -> BrowserUtil.browse(DONATE_LINK)));
    }

}
