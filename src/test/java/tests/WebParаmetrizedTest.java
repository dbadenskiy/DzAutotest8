package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.Strings;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;



@DisplayName("Параметризованный тест на проверку слова selenide")
public class WebParаmetrizedTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }
        @BeforeEach
        void setUp () {
            open("https://duckduckgo.com/");
        }
    @CsvSource (value = {
            " , https://selenide.org",
            "junit5, https://junit.org"
    })
    @ParameterizedTest (name = "Содержание слова {0} на странице и ссылки {1}")
    @Tag("Smoke")
       void wordOnPages (String searchQuery, String expectedLinc) {
        open("https://duckduckgo.com/");
        $("[name=q]").setValue(searchQuery).pressEnter();
        $(".pAgARfGNTRe_uaK72TAD").shouldHave(text(expectedLinc));
    }


    @Test
    @Tag("Smoke")
    @DisplayName("Содержание слова junit5 на странице")
    void wordJunit5OnPages () {
        open("https://duckduckgo.com/");
        $("[name=q]").setValue("junit5").pressEnter();
        $(".pAgARfGNTRe_uaK72TAD").shouldHave(text("https://junit.org"));
    }
}
