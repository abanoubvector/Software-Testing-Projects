package features.f1_auth;

import config.config;
import features.Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LogoutTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private Logout logout;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        logout = new Logout(driver);
        driver.get(config.base_url);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testSuccessfulLogout() {
        logout.clickLogout();
        boolean isLoggedOut = logout.isLogoutSuccessful();
        BaseTest.validateTestResult(isLoggedOut, "TestCase ID:DPSAT-T42 - Successful Logout from User Account");
        Assert.assertTrue(isLoggedOut, "User should be logged out and redirected to the login page");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
