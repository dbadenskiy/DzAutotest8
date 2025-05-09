package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.Strings;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;



@DisplayName("Параметризованный тест на проверку слова selenide")
public class WebParаmetrizedTest extends TestBase{




    @ValueSource(strings = {
            "Города",
            "Столицы",
            "Страны"
    })
    @ParameterizedTest(name = "При поиске по запросу {0} всегда должна быть ссылка на источник wikipedia")
    @Tag("SMOKE")
    void linkOnPage(String searchQuery) {
        $("[name=q]").setValue(searchQuery).pressEnter();
        $(".pAgARfGNTRe_uaK72TAD").shouldHave(text("https://ru.wikipedia.org"));
    }


    @CsvSource(value = {
            "Город + Крупный населённый пункт",
            "Столица + Столи́ца — главный город независимого государства",
            "Страна + Страна́ — территория, выделяемая по географическому положению"
    }, delimiter = '+')
    @ParameterizedTest(name = "Наличие слова {0} на странице и его пояснение {1}")
    @Tag("Smoke")
    void xplanationOfAttributes(String searchQuery, String expectedText) {
        $("[name=q]").setValue(searchQuery).pressEnter();
        $(".Cwg3TAWR68fVkDBMYLUZ").shouldHave(text(expectedText));
    }


    // Вариант, использую ресурсы из файла
    @CsvFileSource(resources = "/test_data/xplanationOfAttributesFromTheCSVFile.csv")
    @ParameterizedTest(name = "Наличие слова {0} на странице и его пояснение {1}")
    @Tag("Smoke")
    void xplanationOfAttributesFromTheCSVFile(String searchQuery, String expectedText) {
        $("[name=q]").setValue(searchQuery).pressEnter();
        $(".Cwg3TAWR68fVkDBMYLUZ").shouldHave(text(expectedText));
    }



    static Stream <Arguments> explanationOfAttributesFromArguments() {
return Stream.of(
        Arguments.of("Город", "Крупный населённый пункт"),
        Arguments.of("Столица", "Столи́ца — главный город независимого государства"),
        Arguments.of("Страна", "Страна́ — территория, выделяемая по географическому положению")
);
    }

    @MethodSource()
    @ParameterizedTest (name = "Для текста {0} отображается пояснение {1}")
    @Tag("Smoke")
    void explanationOfAttributesFromArguments(String searchQuery, String expectedText) {
        $("[name=q]").setValue(searchQuery).pressEnter();
        $(".Cwg3TAWR68fVkDBMYLUZ").shouldHave(text(expectedText));
    }






}




