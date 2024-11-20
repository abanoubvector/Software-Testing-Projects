package e2e_test;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import features.f2_product_management.Inventory;
import features.f4_checkout.Checkout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class E2ETest extends BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    Inventory inventory;
    Checkout checkout;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventory = new Inventory(driver);
        checkout = new Checkout(driver);
    }

    @BeforeMethod
    public void setupMethod() {
        driver.get(config.base_url);
    }

    @Test(priority = 1)
    public void testCompleteShoppingFlow() throws InterruptedException {
        // Login
        loginPage.login("standard_user", "secret_sauce");

        // Add products to cart
        inventory.addProductToCart("Sauce Labs Backpack");
        inventory.addProductToCart("Sauce Labs Bike Light");

        // Sort products
        inventory.sortProducts("Price (high to low)");

        // Proceed to checkout
        checkout.goToCart();
        checkout.clickCheckout();

        // Fill checkout information
        checkout.enterCheckoutInformation("John", "Doe", "12345");
        checkout.clickContinue();
        checkout.clickFinish();

        // Verify order completion
        String completeMessage = checkout.getCheckoutCompleteMessage();
        Assert.assertEquals(completeMessage, checkout.checkoutCompleteResponse);
        BaseTest.validateTestResult(completeMessage.equals(checkout.checkoutCompleteResponse), "E2E - Complete Shopping Flow");
    }

    @Test(priority = 2)
    public void testProductSortingAndCheckout() throws InterruptedException {
        // Login
        loginPage.login("standard_user", "secret_sauce");

        // Sort products
        inventory.sortProducts("Name (A to Z)");

        // Add first product to cart
        inventory.addProductToCart("Sauce Labs Backpack");

        // Sort products again
        inventory.sortProducts("Price (low to high)");

        // Add cheapest product to cart
        inventory.addProductToCart("Sauce Labs Onesie");

        // Proceed to checkout
        checkout.goToCart();
        checkout.clickCheckout();

        // Fill checkout information
        checkout.enterCheckoutInformation("Jane", "Doe", "54321");
        checkout.clickContinue();
        checkout.clickFinish();

        // Verify order completion
        String completeMessage = checkout.getCheckoutCompleteMessage();
        Assert.assertEquals(completeMessage, checkout.checkoutCompleteResponse);
        BaseTest.validateTestResult(completeMessage.equals(checkout.checkoutCompleteResponse), "E2E - Product Sorting and Checkout");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}