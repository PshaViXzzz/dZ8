package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1080x720";
        Configuration.baseUrl = "https://www.hotels.ru/";
        Configuration.pageLoadStrategy = "eager";
    }

}
