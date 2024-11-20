package features.f2_product_management;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ProductDetailsTest extends BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    Inventory inventory;
    ProductPage productPage;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventory = new Inventory(driver);
        productPage = new ProductPage(driver);
    }

    @BeforeMethod
    public void setupMethod() {
        driver.get(config.base_url);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(priority = 1)
    public void testProductDetails() {
        String productName = "Sauce Labs Backpack";
        inventory.addProductToCart(productName);

        // Navigate to product details page (you might need to implement this method in Inventory class)
        // inventory.navigateToProductDetails(productName);

        Assert.assertEquals(productPage.getProductName(), productName, "Product name doesn't match");
        Assert.assertTrue(productPage.getProductPrice().startsWith("$"), "Product price is not displayed correctly");
        Assert.assertFalse(productPage.getProductDescription().isEmpty(), "Product description is empty");
        Assert.assertTrue(productPage.isAddedToCart(), "Product is not shown as added to cart");

        BaseTest.validateTestResult(
                productPage.getProductName().equals(productName) &&
                        productPage.getProductPrice().startsWith("$") &&
                        !productPage.getProductDescription().isEmpty() &&
                        productPage.isAddedToCart(),
                "Product Details Test"
        );
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}