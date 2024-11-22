package features.f4_checkout;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import features.f3_shopping_cart.Cart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckoutTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private Cart cart;
    private Checkout checkout;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        cart = new Cart(driver);
        checkout = new Checkout(driver);

        driver.get(config.base_url);
        loginPage.login("standard_user", "secret_sauce");
        cart.addToCart("Sauce Labs Backpack");
        cart.addToCart("Sauce Labs Bolt T-Shirt");
        cart.openCart();
        cart.proceedToCheckout();
    }

    // @BeforeMethod
    // public void setupMethod() {
    //     driver.get(config.base_url);
    //     loginPage.login("standard_user", "secret_sauce");
    //     cart.addToCart("Sauce Labs Backpack");
    //     cart.openCart();
    //     cart.proceedToCheckout();
    // }

    @Test(priority = 1)
    public void testSuccessfulCheckout() {
        checkout.fillCheckoutInfo("Jon", "Do", "1234");
        checkout.clickFinish();
        String confirmationMessage = checkout.getOrderConfirmationMessage();
        BaseTest.validateTestResult(confirmationMessage.contains("Thank you for your order!"), "TestCase ID:DPSAT-T20 - Verify successful checkout");
        Assert.assertTrue(confirmationMessage.contains("Thank you for your order!"), "Order should be placed successfully");
    }

    @Test(priority = 2)
    public void testCheckoutWithoutShippingInformation() {
        checkout.clickContinue();
        String errorMessage = checkout.getErrorMessage();
        BaseTest.validateTestResult(errorMessage.contains("Error: First Name is required"), "TestCase ID:DPSAT-T23 - Verify checkout without shipping");
        Assert.assertTrue(errorMessage.contains("Error: First Name is required"), "Error message should be displayed for missing shipping information");
    }

    @Test(priority = 3)
    public void testOrderSummaryAccuracy() {
        checkout.fillCheckoutInfo("Jon", "Do", "1234");
        boolean isAccurate = checkout.isOrderSummaryAccurate();
        BaseTest.validateTestResult(isAccurate, "TestCase ID:DPSAT-T28 - Verify order summary accuracy");
        Assert.assertTrue(isAccurate, "Order summary should accurately reflect all items, pricing, discounts, taxes, and shipping charges");
    }

    @Test(priority = 4)
    public void testInvalidCheckoutInfo() {
        try {
            checkout.fillCheckoutInfo("123&", "123&", "Ahmed");
            boolean errorMessage = checkout.ErrorMessageIsDisplayed();
            BaseTest.validateTestResult(errorMessage, "TestCase ID:DPSAT-T46 - Verify Checkout with Invalid Input");
            Assert.assertTrue(errorMessage, "Error message should be displayed for invalid input");
        } catch (Exception e) {
            BaseTest.validateTestResult(false, "TestCase ID:DPSAT-T46 - Verify Checkout with Invalid Input");
            Assert.fail("An exception occurred during the test: " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void testEmptyFirstName() {
        checkout.enterLastName("Doe");
        checkout.enterPostalCode("12345");
        checkout.clickContinue();
        String errorMessage = checkout.getErrorMessage();
        BaseTest.validateTestResult(errorMessage.contains("Error: First Name is required"), "TestCase ID:DPSAT-T54 - Verify First Name Filed Within Empty Input");
        Assert.assertTrue(errorMessage.contains("Error: First Name is required"), "Error message should be displayed for empty first name");
    }

    @Test(priority = 6)
    public void testEmptyLastName() {
        checkout.enterFirstName("John");
        checkout.enterPostalCode("12345");
        checkout.clickContinue();
        String errorMessage = checkout.getErrorMessage();
        BaseTest.validateTestResult(errorMessage.contains("Error: Last Name is required"), "TestCase ID:DPSAT-T55 - Verify Last Name filed within Empty Input");
        Assert.assertTrue(errorMessage.contains("Error: Last Name is required"), "Error message should be displayed for empty last name");
    }

    @Test(priority = 7)
    public void testEmptyPostalCode() {
        checkout.enterFirstName("John");
        checkout.enterLastName("Doe");
        checkout.clickContinue();
        String errorMessage = checkout.getErrorMessage();
        BaseTest.validateTestResult(errorMessage.contains("Error: Postal Code is required"), "TestCase ID:DPSAT-T56 - Verify Postal Code Filed within Empty Input");
        Assert.assertTrue(errorMessage.contains("Error: Postal Code is required"), "Error message should be displayed for empty postal code");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
