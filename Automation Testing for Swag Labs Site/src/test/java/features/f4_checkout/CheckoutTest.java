package features.f4_checkout;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import features.f2_product_management.Inventory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckoutTest extends BaseTest {
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
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(priority = 1)
    public void testSuccessfulCheckout() {
        inventory.addProductToCart("Sauce Labs Backpack");
        checkout.completeCheckout("John", "Doe", "12345");
        String completeMessage = checkout.getCheckoutCompleteMessage();
        Assert.assertEquals(completeMessage, checkout.checkoutCompleteResponse);
        BaseTest.validateTestResult(completeMessage.equals(checkout.checkoutCompleteResponse), "TC_001 - Successful Checkout");
    }

    @Test(priority = 2)
    public void testCheckoutWithInvalidInformation() {
        inventory.addProductToCart("Sauce Labs Backpack");
        checkout.goToCart();
        checkout.clickCheckout();
        checkout.enterCheckoutInformation("", "", "");
        checkout.clickContinue();
        String errorMessage = checkout.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Error: First Name is required"));
        BaseTest.validateTestResult(errorMessage.contains("Error: First Name is required"), "TC_002 - Checkout with Invalid Information");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}