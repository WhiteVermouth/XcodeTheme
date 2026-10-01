# Repository Guidelines

## Project Structure

This is a JetBrains IDE theme plugin, built with Gradle, that provides Xcode-inspired light and dark themes. It is published on the JetBrains Marketplace as "Xcode Theme" (plugin ID: `com.vermouthx.xcode-theme`).

- `src/main/java/com/vermouthx/xcodetheme/`: Java plugin logic, organized by concern into `activities/`, `notifications/`, `settings/`, and `enums/`.
- `src/main/resources/`: Theme assets (`*.theme.json` UI themes, `*.xml` editor color schemes).
- `src/main/resources/META-INF/plugin.xml`: Plugin metadata; registers the four theme providers by UUID.
- `assets/`: Marketing assets such as screenshots and logos.
- `.github/workflows/build.yml`: CI build, verification, release, and marketplace publish flow.

## Commands

Use the Gradle wrapper from the repo root (`./gradlew.bat` on Windows):

```bash
./gradlew build          # Build the plugin and run the default verification steps
./gradlew buildPlugin    # Build the distributable plugin zip into build/distributions/
./gradlew runIde         # Launch a sandboxed IDE instance with the plugin loaded
./gradlew verifyPlugin   # Run JetBrains plugin verification against configured IDE targets
./gradlew test           # Run tests if a src/test suite exists
./gradlew tasks          # List available tasks when adding new automation
```

CI uses JDK 21; match that locally when possible.

## Architecture

The plugin is theme-centric: six small Java classes handle startup notifications and version tracking, while the bulk of the plugin is theme resource files.

### Theme variants

Two classic variants and two "Islands" variants (new UI) that wrap a JetBrains built-in parent theme:

| Variant | Parent | Editor scheme |
|---|---|---|
| Xcode Light | — | `XcodeLight.xml` |
| Xcode Dark | — | `XcodeDark.xml` |
| Islands Xcode Light | Islands Light | `XcodeLight.xml` (reused) |
| Islands Xcode Dark | Islands Dark | `XcodeDark.xml` (reused) |

### Theme file structure

Each theme consists of files in `src/main/resources/`:

- **`*.theme.json`** — UI component colors (buttons, tabs, trees, menus, borders, etc.), with a named color palette at the top and component overrides under `ui`. Each declares its editor scheme via the `editorScheme` key; Islands variants add a `parentTheme` key and point `editorScheme` at the reused base XML.
- **`*.xml`** — Editor color scheme (syntax highlighting for all supported languages). Xcode Light inherits `Default`; Xcode Dark inherits `Darcula`.

### Java source

All source lives in `src/main/java/com/vermouthx/xcodetheme/`:

- **`XcTManager`** — plugin ID constant and version lookup
- **`activities/XcTStartupActivity`** — runs on project open; shows welcome or upgrade notifications
- **`notifications/XcTNotification`** — notification content, including the `WHATS_NEW` release notes
- **`settings/XcTMetaSetting` + `XcTMetaState`** — persistent state (stores the last-seen version to detect upgrades)
- **`enums/XcTVariant`** — maps theme variants to display names

## Coding Style

- Java: 4-space indentation, braces on the same line, descriptive class names. Plugin classes use the `XcT` prefix (e.g. `XcTManager`, `XcTMetaState`).
- Theme resources: keep names explicit and paired across variants (e.g. `XcodeDark.theme.json` and `IslandsXcodeDark.theme.json`).
- Preserve key ordering and formatting in theme JSON files to keep diffs readable. Use the named color aliases defined at the top of each `.theme.json` where possible.
- Numeric keys such as `arc`, `underlineHeight`, and `rowHeight` must be unquoted JSON numbers — the platform types values straight from the JSON token, so a quoted `"8"` stays a `String` and is silently ignored by `JBUI.getInt`.

## Testing

There is no committed test suite today. Validation is `./gradlew verifyPlugin` (which CI runs on every push and PR) plus manual checks in a JetBrains IDE.

- For theme-only changes, validate JSON syntax locally and verify rendering in `./gradlew runIde` before opening a PR.
- When adding behavior in Java, prefer small unit tests under `src/test/java/`, named after the class under test (e.g. `XcTManagerTest`).

## Commits & Pull Requests

Follow the repository's commit style: emoji-prefixed, imperative subjects (🐛 fix, 🎨 style, ✨ feature, 🎉 release, 📝 docs, 🧹 cleanup, 🔧 refactor), such as `🎨 Update selection foreground color in Islands Xcode Light theme` or `🎉 Release version 1.8.5`.

- Keep each commit focused on one logical change.
- PRs should include a brief summary, note affected theme variants, link any related issue, and attach screenshots for UI or color changes.

## Release Workflow

Every push to `master` or a `release/**` branch, and every PR, runs `build buildPlugin` then `verifyPlugin`. Releases are triggered by pushing a `v*` tag: the release job attaches the built zip to a GitHub Release and publishes to the JetBrains Marketplace.

When bumping to the next plugin version, update all release-related files in the same change, keeping the version number and release notes consistent:

1. `pluginVersion` in `gradle.properties`
2. A new section in `CHANGELOG.md` — the section matching `pluginVersion` is rendered as the marketplace change notes
3. `WHATS_NEW` in `src/main/java/com/vermouthx/xcodetheme/notifications/XcTNotification.java`

## Configuration Notes

Versioning and publishing settings are defined in `gradle.properties` and `build.gradle.kts`. Never commit secrets; the publishing token is read from the `PUBLISH_TOKEN` environment variable (CI supplies it from the `JETBRAINS_TOKEN` secret).
