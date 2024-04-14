package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {
    private final BrowserstackConfig browserstackConfig;

    public BrowserstackDriver() {
        this.browserstackConfig = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());
    }

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        // и на всякий пишем следующую строчку
        mutableCapabilities.merge(capabilities);
        
        // Set your access credentials
        // можно найти в 'Automate'
        mutableCapabilities.setCapability("browserstack.user", browserstackConfig.getBrowserstackUser());
        mutableCapabilities.setCapability("browserstack.key", browserstackConfig.getBrowserstackKey());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", browserstackConfig.getApp());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", browserstackConfig.getDevice());
        mutableCapabilities.setCapability("os_version", browserstackConfig.getOSVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", browserstackConfig.getProjectName());
        mutableCapabilities.setCapability("build", browserstackConfig.getBuildName());
        mutableCapabilities.setCapability("name", browserstackConfig.getName());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL(browserstackConfig.getRemoteURL()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
