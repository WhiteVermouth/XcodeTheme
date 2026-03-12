# Repository Guidelines

## Project Structure & Module Organization
This repository is a JetBrains theme plugin built with Gradle. Production code lives in `src/main/java/com/vermouthx/xcodetheme/` and is organized by concern: `activities/`, `listeners/`, `notifications/`, `settings/`, and `enums/`. Theme definitions and plugin metadata live in `src/main/resources/`, including `*.theme.json`, editor schemes `*.xml`, and `META-INF/plugin.xml`. Marketing assets such as screenshots and logos are stored in `assets/`.

## Build, Test, and Development Commands
Use the Gradle wrapper from the repository root:

- `./gradlew build` builds the plugin and runs the default verification steps.
- `./gradlew verifyPlugin` runs JetBrains plugin verification against configured IDE targets.
- `./gradlew test` runs tests if a `src/test` suite exists.
- `./gradlew tasks` lists available development tasks when adding new automation.

Use `./gradlew.bat` on Windows.

## Coding Style & Naming Conventions
Follow existing Java conventions: 4-space indentation, braces on the same line, and descriptive class names. Theme manager and settings classes use the `XcT` prefix, for example `XcTManager` and `XcTMetaState`. Keep theme resource names explicit and paired, such as `XcodeDark.theme.json` and `IslandsXcodeDark.theme.json`. Preserve key ordering and formatting in theme JSON files to keep diffs readable.

## Testing Guidelines
There is currently no committed test suite. When adding behavior in Java, prefer small unit tests under `src/test/java/` and name them after the class under test, for example `XcTManagerTest`. For theme-only changes, validate JSON syntax locally and verify rendering in a JetBrains IDE before opening a PR.

## Commit & Pull Request Guidelines
Follow the repository's historical commit style: emoji-prefixed, imperative subjects such as `🎨 Update selection foreground color in Islands Xcode Light theme` or `🎉 Release version 1.8.5`. Keep each commit focused on one change. Pull requests should include a brief summary, note affected theme variants, link any related issue, and attach screenshots for UI or color changes.

## Configuration Notes
Versioning and publishing settings are defined in `gradle.properties` and `build.gradle.kts`. Do not commit secrets; the publishing token is expected through the `jetbrains.token` system property.

## Release Version Bumps
When bumping to the next plugin version, update all release-related files in the same change:

- `CHANGELOG.md`
- `src/main/java/com/vermouthx/xcodetheme/notifications/XcTNotification.java`
- `gradle.properties`

Keep the version number and release notes consistent across all three files before committing.
