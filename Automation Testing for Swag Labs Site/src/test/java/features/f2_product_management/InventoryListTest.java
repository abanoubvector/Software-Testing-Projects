package features.f2_product_management;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class InventoryListTest extends BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    Inventory inventory;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventory = new Inventory(driver);
    }

    @BeforeMethod
    public void setupMethod() {
        driver.get(config.base_url);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(priority = 1)
    public void testAddProductToCart() {
        String productName = "Sauce Labs Backpack";
        inventory.addProductToCart(productName);
        Assert.assertTrue(inventory.isProductAddedToCart(productName), "Product was not added to cart successfully");
        BaseTest.validateTestResult(inventory.isProductAddedToCart(productName), "Add Product to Cart");
    }

    @Test(priority = 2)
    public void testAddMultipleProductsToCart() {
        String[] products = {"Sauce Labs Backpack", "Sauce Labs Bike Light"};
        for (String product : products) {
            inventory.addProductToCart(product);
        }
        for (String product : products) {
            Assert.assertTrue(inventory.isProductAddedToCart(product), "Product " + product + " was not added to cart successfully");
        }
        BaseTest.validateTestResult(inventory.isProductAddedToCart(products[0]) && inventory.isProductAddedToCart(products[1]), "Add Multiple Products to Cart");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}