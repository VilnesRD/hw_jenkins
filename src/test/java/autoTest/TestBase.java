package autoTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {
    public static class testBase {
        @BeforeAll
        static void  beforeAll() {
            Configuration.browserSize = "1920x1080";
            Configuration.timeout = 10000;
            Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }

        @BeforeEach
        void addListener() {
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        }

        @AfterEach
        void addAttachments() {
           Attach.screenshotAs("Test screenshot");
           Attach.pageSource();
           Attach.browserConsoleLogs();
           Attach.addVideo();
        }

    }
}
