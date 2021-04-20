package qaLesson5Video;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

public class GitHubIssueTest {

    private static final String BASE_URL = "http://github.com";

    private static final String ISSUES = "Issues";

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NUMBER = "#68";


    @Test
    @Owner("ФИО ответственного1")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("critical")})
    @Link(name = "Base URL", value = BASE_URL)

    @Feature("Issues")
    @Story("Поиск Issue")
    @DisplayName("Поиск Issue по номеру в репозитории")

    public  void testIssueSearch (){
        //SelenideLogger.addListener("allure", new AllureSelenide());

        open(BASE_URL);
        $x("//input[contains(@class,'header-search-input')]").click();
        $x("//input[contains(@class,'header-search-input')]").sendKeys(REPOSITORY);
        $x("//input[contains(@class,'header-search-input')]").submit();
        $x("//a[contains(@data-hydro-click,'" + REPOSITORY + "')]").click();
        $x("//span[@data-content='" + ISSUES + "']").click();
        $x("//span[contains(text(),'" + ISSUE_NUMBER + "')]").should(Condition.exist);
    }

    @Test
    @Owner("ФИО ответственного2")
    @Tags({@Tag("web"), @Tag("critical")})
    @Link(name = "Base URL", value = BASE_URL)

    @Feature("Issues")
    @Story("Создание Issue")
    @DisplayName("Создание Issue в новом репозитории")

    public  void testIssueCreate (){
        parameter("Repository", REPOSITORY);
        parameter("Issue number", ISSUE_NUMBER);


        step("Открываем главную страницу", () -> {
            open(BASE_URL);
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $x("//input[contains(@class,'header-search-input')]").click();
            $x("//input[contains(@class,'header-search-input')]").sendKeys(REPOSITORY);
            $x("//input[contains(@class,'header-search-input')]").submit();
        });

        step("Переходим в репозиторий " + REPOSITORY, () -> {
            $x("//a[contains(@data-hydro-click,'" + REPOSITORY + "')]").click();
        });

        step("Переходим в раздел " + ISSUES, () -> {
            $x("//span[@data-content='" + ISSUES + "']").click();
        });
        step("Проверяем что Issue с номером " + ISSUE_NUMBER + " существует", () -> {
            $x("//span[contains(text(),'" + ISSUE_NUMBER + "')]").should(Condition.exist);
        });
    }

}
