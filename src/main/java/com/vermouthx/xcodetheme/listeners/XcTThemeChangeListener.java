package com.vermouthx.xcodetheme.listeners;

import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.vermouthx.xcodetheme.enums.XcTVariant;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class XcTThemeChangeListener implements LafManagerListener {

    private final EditorColorsManager editorColorsManager = EditorColorsManager.getInstance();
    private String previousUI = LafManager.getInstance().getCurrentUIThemeLookAndFeel().getName();

    @Override
    public void lookAndFeelChanged(@NotNull LafManager lafManager) {
        String currentUI = lafManager.getCurrentUIThemeLookAndFeel().getName();
        if (!Objects.equals(previousUI, currentUI)) {
            if (Objects.equals(currentUI, XcTVariant.LIGHT.getName()) || Objects.equals(currentUI, XcTVariant.DARK.getName())) {
                editorColorsManager.setGlobalScheme(editorColorsManager.getScheme("_@user_" + currentUI));
            }
        }
        previousUI = currentUI;
    }
}
