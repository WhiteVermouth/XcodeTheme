package com.vermouthx.xcodetheme.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "XcTSetting", storages = {@Storage("xcode-theme.xml")})
public class XcTMetaSetting implements PersistentStateComponent<XcTMetaState> {
    private XcTMetaState myState = new XcTMetaState();

    public void setVersion(String version) {
        myState.version = version;
    }

    public String getVersion() {
        return myState.version;
    }

    @Override
    public @Nullable XcTMetaState getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull XcTMetaState state) {
        myState = state;
    }

}
