package features.f2_product_management;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class InventoryListTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryList inventoryList;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventoryList = new InventoryList(driver);
    }

    @BeforeMethod
    public void navigateToInventory() {
        driver.get(config.base_url);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testProductDisplayOnHomepage() {
        boolean areProductsDisplayed = inventoryList.areProductDetailsDisplayed();
        BaseTest.validateTestResult(areProductsDisplayed, "TestCase ID:DPSAT-T36 - Verify Product Display on Homepage");
        Assert.assertTrue(areProductsDisplayed, "All product details should be displayed on the homepage");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
