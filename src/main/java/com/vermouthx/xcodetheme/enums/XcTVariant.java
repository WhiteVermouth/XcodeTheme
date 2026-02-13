package com.vermouthx.xcodetheme.enums;

public enum XcTVariant {
    LIGHT("Xcode Light"),
    DARK("Xcode Dark"),
    ISLANDS_LIGHT("Islands Xcode Light"),
    ISLANDS_DARK("Islands Xcode Dark");

    private final String name;

    XcTVariant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
