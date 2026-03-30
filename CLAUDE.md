# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

JetBrains IDE theme plugin providing Xcode-inspired light and dark color schemes. Published on the JetBrains Marketplace as "Xcode Theme" (ID: `com.vermouthx.xcode-theme`).

## Build Commands

```bash
./gradlew build          # Build plugin + verification
./gradlew buildPlugin    # Build plugin package only
./gradlew verifyPlugin   # Run JetBrains plugin verification
./gradlew test           # Run tests (no test suite yet)
```

## Architecture

The plugin is theme-centric: ~6 small Java classes handle startup notifications and version tracking, while the bulk of the plugin is theme resource files.

**Four theme variants:**
- Xcode Light / Xcode Dark (classic)
- Islands Xcode Light / Islands Xcode Dark (new UI)

**Theme files come in pairs:**
- `.theme.json` — UI component colors (buttons, tabs, trees, menus, borders, etc.) with a named color palette at the top and component overrides in `ui`
- `.xml` — Editor color scheme (syntax highlighting for all supported languages); Light inherits default, Dark inherits Darcula

**Java classes** (`src/main/java/com/vermouthx/xcodetheme/`):
- `XcTManager` — plugin ID constant and version lookup
- `activities/XcTStartupActivity` — shows welcome/upgrade notifications on project open
- `notifications/XcTNotification` — notification content (update release notes here on version bump)
- `settings/XcTMetaSetting` + `XcTMetaState` — persistent version state
- `enums/XcTVariant` — enum mapping theme variants to display names

**Plugin manifest:** `src/main/resources/META-INF/plugin.xml` registers 4 theme providers by UUID.

## Conventions

- **Naming:** Java classes use `XcT` prefix. Theme resources use explicit paired names (e.g., `XcodeDark.theme.json` / `IslandsXcodeDark.theme.json`).
- **Theme JSON:** Preserve key ordering and formatting for readable diffs. Use named color aliases (defined at top of each `.theme.json`) where possible.
- **Commits:** Emoji-prefixed imperative subjects (🐛 fix, 🎨 style, ✨ feature, 🎉 release, 📝 docs, 🧹 cleanup, 🔧 refactor).
- **Version bumps:** Update `gradle.properties`, `CHANGELOG.md`, and `XcTNotification.java` together.
- **Publishing:** Token via `jetbrains.token` system property. Never commit secrets.
