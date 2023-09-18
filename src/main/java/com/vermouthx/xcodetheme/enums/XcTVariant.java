package com.vermouthx.xcodetheme.enums;

public enum XcTVariant {
    LIGHT("Xcode Light"), DARK("Xcode Dark");

    private final String name;

    XcTVariant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
