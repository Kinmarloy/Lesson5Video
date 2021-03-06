package qaLesson5Video;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import packageBaseSteps.BaseSteps;

public class MethodStepTest {

    public BaseSteps steps = new BaseSteps();

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NUMBER = "#68";

    @Test
    public void testIssueSearch (){
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.goToRepositoryFromSearch(REPOSITORY);
        steps.openRepositoryIssues();
        steps.shouldSeeIssueWithNumber(ISSUE_NUMBER);

    }

    @Test
    @Disabled
    public void testIssueCreate() {
        final String title = "Новая задача";

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.goToRepositoryFromSearch(REPOSITORY);
        steps.openRepositoryIssues();

        steps.createIssueWithTitle(title);
        steps.shouldSeeIssueWithTitle(title);
    }

}
