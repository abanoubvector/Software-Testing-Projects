package features.Base;

public class BaseTest {


    protected static void validateTestResult(boolean condition, String testCaseId) {
        if (condition) {
            System.out.println(testCaseId + " Passed");
        } else {
            System.out.println(testCaseId + " Failed");
        }
    }
}
