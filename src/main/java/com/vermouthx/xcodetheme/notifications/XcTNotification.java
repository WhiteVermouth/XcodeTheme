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
    private static final String WHATS_NEW = "<ul>" + "<li>🎨 Fixed selection foreground color contrast in Xcode Light and Islands Xcode Light themes</li>" + "</ul>";

    @Language("HTML")
    private static final String RELEASE_NOTE = "<p>🎉 <strong>Welcome to Xcode Theme v" + XcTManager.currentVersion() + "!</strong> Here's what's new:</p>" + WHATS_NEW + "<p>If you enjoy Xcode Theme, click <strong>☕ Donate</strong> below to support development. Your support is appreciated!</p>" + "<p><em>Enjoy the latest improvements!</em> ⌘</p>";

    @Language("HTML")
    private static final String WELCOME_MESSAGE = "<p>🎉 <strong>Welcome!</strong> Xcode Theme is now installed and ready to use.</p>" + "<ul>" + "<li>Go to <strong>Settings → Appearance & Behavior → Appearance</strong></li>" + "<li>Select <strong>Xcode Light</strong> or <strong>Xcode Dark</strong> from the Theme dropdown</li>" + "<li>Restart your IDE for the best experience</li>" + "</ul>" + "<p>If you enjoy Xcode Theme, click <strong>☕ Donate</strong> below to support development. Your support is appreciated!</p>" + "<p><em>Happy coding!</em> ⌘</p>";

    private static final String NOTIFICATION_GROUP_ID = "Xcode Theme";
    private static final String DONATE_LINK = "https://www.buymeacoffee.com/nszihan";
    private static final String GITHUB_LINK = "https://github.com/WhiteVermouth/XcodeTheme";
    private static final Icon icon = IconLoader.getIcon("/icons/logo.png", XcTNotification.class);

    private static NotificationGroup getNotificationGroup() {
        return NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID);
    }

    public static void notifyReleaseNote(@Nullable Project project) {
        Notification notification = getNotificationGroup().createNotification("🎨 Xcode Theme v" + XcTManager.currentVersion() + " - Release Notes", RELEASE_NOTE, NotificationType.INFORMATION);
        addNotificationActions(notification);
        notification.setIcon(icon);
        notification.notify(project);
    }

    public static void notifyWelcome(@Nullable Project project) {
        Notification notification = getNotificationGroup().createNotification("🎨 Xcode Theme Successfully Installed", WELCOME_MESSAGE, NotificationType.INFORMATION);
        addNotificationActions(notification);
        notification.setIcon(icon);
        notification.notify(project);
    }

    private static void addNotificationActions(Notification notification) {
        notification.addAction(NotificationAction.createSimple("📖 GitHub", () -> BrowserUtil.browse(GITHUB_LINK)));
        notification.addAction(NotificationAction.createSimple("☕ Donate", () -> BrowserUtil.browse(DONATE_LINK)));
    }

}
