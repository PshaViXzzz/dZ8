package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import utils.Language;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTest extends TestBase {
    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }
    @CsvSource(value = {
            "Москва | Москва, Россия",
            "Санкт-Петербург | Санкт-Петербург, Россия"
    },
            delimiter = '|')
    @ParameterizedTest(name = "Для поискового запроса {0} должен находить запрос {1}")
    void simpleEnumSourceHotelsTest(String city, String expectedSearchResult) {
        open(baseUrl);
        $(".search-field-wrapper input").setValue(city);
        sleep(10000);
        $(".ui-menu-item a").shouldHave(text(expectedSearchResult));
    }

    @EnumSource(Language.class)
    @ParameterizedTest
    void simpleEnumSourceHotelsTest(Language language) {
        open(baseUrl);
        $$(".toggle-language a").find(text(language.name())).click();
        $(".screen-poster h1").shouldHave(text(language.description));
    }

    static Stream<Arguments> simpleMethodSourceHotelsTest() {
        return Stream.of(Arguments.of(Language.EN, List.of("PLACE OR NAME OF THE HOTEL", "ARRIVAL DATE", "08OCT", "OCT", "DEPARTURE DATE", "09OCT", "OCT", "ADULTS", "CHILDREN", "ADD GUESTS", "")),
                Arguments.of(Language.RU, List.of("МЕСТО ИЛИ НАЗВАНИЕ ОТЕЛЯ", "ДАТА ЗАЕЗДА", "08ОКТ", "ОКТ", "ДАТА ВЫЕЗДА", "09ОКТ", "ОКТ", "ВЗРОСЛЫЕ", "ДЕТИ", "БОЛЬШЕ\nГОСТЕЙ", "")));
    }

    @MethodSource
    @ParameterizedTest
    void simpleMethodSourceHotelsTest(Language language, List<String> expectedFieldNames) {
        open(baseUrl);
        $$(".toggle-language a").find(text(language.name())).click();
        $$(".search-form span").filter(visible).shouldHave(texts(expectedFieldNames));
    }
}
