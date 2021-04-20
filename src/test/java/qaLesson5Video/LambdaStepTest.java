package qaLesson5Video;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaStepTest {

    private static final String BASE_URL = "http://github.com";

    private static final String ISSUES = "Issues";

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NUMBER = "#68";

    @Test
    public void testIssueSearch (){
        SelenideLogger.addListener("allure", new AllureSelenide());

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
