package config;

import org.aeonbits.owner.Config;

public interface BrowserstackConfig extends Config {
    @Key("browserstack.user")
    @DefaultValue("bogoutdinovrusla_7sHrJu")
    String getBrowserstackUser();

    @Key("browserstack.key")
    @DefaultValue("MK8szVrvtqekwjtZvbT1")
    String getBrowserstackKey();

    @Key("remoteURL")
    @DefaultValue("http://hub.browserstack.com/wd/hub")
    String getRemoteURL();

    @Key("app")
    @DefaultValue("bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c")
    String getApp();

    @Key("device")
    @DefaultValue("Google Pixel 3")
    String getDevice();

    @Key("os_version")
    @DefaultValue("9.0")
    String getOSVersion();

    @Key("project")
    @DefaultValue("First Java Project")
    String getProjectName();

    @Key("build")
    @DefaultValue("browserstack-build-1")
    String getBuildName();

    @Key("name")
    @DefaultValue("first_test")
    String getName();
}
