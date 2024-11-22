package features.f2_product_management;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ProductDetailsTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryList inventoryList;
    private ProductPage productPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventoryList = new InventoryList(driver);
        productPage = new ProductPage(driver);
    }

    @BeforeMethod
    public void navigateToInventory() {
        driver.get(config.base_url);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testProductDisplayOnProductPage() {
        String expectedProductName = "Sauce Labs Bike Light";
        WebElement product = inventoryList.getProductByName(expectedProductName);
        product.click();
        
        boolean areDetailsDisplayed = productPage.areProductDetailsDisplayed();
        String productName = productPage.getProductName();
        
        BaseTest.validateTestResult(areDetailsDisplayed && productName.equals(expectedProductName), 
                                    "TestCase ID:DPSAT-T38 - Verify Product Display on Product Page");
        Assert.assertTrue(areDetailsDisplayed, "All product details should be displayed on the product page");
        Assert.assertEquals(productName, expectedProductName, "The correct product should be displayed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
