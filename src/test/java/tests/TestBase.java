package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = BrowserstackDriver.class.getName();
        // строчка для работы с appium-подобными приложениями (костыль для selenide #1)
        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        // костыль для selenide #2
        open();
    }

    @AfterEach
    void afterEach() {
        String sessionId = sessionId().toString();
        // Attach.screenshotAs("Last screenshot"); // todo
        Attach.pageSource();
        // пока не выполнить данную команду, видео не начнет формироваться
        closeWebDriver();

        Attach.addVideo(sessionId);
    }

}
