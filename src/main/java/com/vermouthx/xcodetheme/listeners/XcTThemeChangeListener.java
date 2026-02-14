package com.vermouthx.xcodetheme.listeners;

import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.vermouthx.xcodetheme.enums.XcTVariant;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class XcTThemeChangeListener implements LafManagerListener {

    private final EditorColorsManager editorColorsManager = EditorColorsManager.getInstance();
    private String previousUI = LafManager.getInstance().getCurrentUIThemeLookAndFeel().getName();

    @Override
    public void lookAndFeelChanged(@NotNull LafManager lafManager) {
        String currentUI = lafManager.getCurrentUIThemeLookAndFeel().getName();
        if (!Objects.equals(previousUI, currentUI)) {
            boolean isXcodeTheme = Arrays.stream(XcTVariant.values())
                    .anyMatch(variant -> Objects.equals(currentUI, variant.getName()));
            if (isXcodeTheme) {
                String editorSchemeName = getEditorSchemeNameForTheme(currentUI);
                editorColorsManager.setGlobalScheme(editorColorsManager.getScheme(editorSchemeName));
            }
        }
        previousUI = currentUI;
    }

    private String getEditorSchemeNameForTheme(String themeName) {
        // Map UI theme names to editor color scheme names
        return switch (themeName) {
            case "Xcode Light", "Islands Xcode Light" -> "Xcode Light";
            case "Xcode Dark", "Islands Xcode Dark" -> "Xcode Dark";
            default -> themeName;
        };
    }
}
