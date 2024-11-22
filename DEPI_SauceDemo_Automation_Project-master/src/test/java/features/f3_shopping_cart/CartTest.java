package features.f3_shopping_cart;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CartTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private Cart cart;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        cart = new Cart(driver);
        driver.get(config.base_url);
        loginPage.login("standard_user", "secret_sauce");
    }

    // @BeforeMethod
    // public void navigateToInventory() {
    //     driver.get(config.base_url);
    //     loginPage.login("standard_user", "secret_sauce");
    // }

    @Test(priority = 1)
    public void testAddingSingleItemToCart() {
        cart.addToCart("Sauce Labs Backpack");
        int cartCount = cart.getCartItemCount();
        BaseTest.validateTestResult(cartCount == 1, "TestCase ID:DPSAT-T10 - Verify Adding a Single Item to Cart");
        Assert.assertEquals(cartCount, 1, "Cart should contain 1 item");
    }

    @Test(priority = 2)
    public void testAddingMultipleItemsToCart() throws InterruptedException {
        Thread.sleep(3000);
        cart.addToCart("Sauce Labs Backpack");
        cart.addToCart("Sauce Labs Bike Light");
        int cartCount = cart.getCartItemCount();
        BaseTest.validateTestResult(cartCount == 2, "TestCase ID:DPSAT-T11 - Verify Adding Multiple Items to Cart");
        Assert.assertEquals(cartCount, 2, "Cart should contain 2 items");
    }

    @Test(priority = 3)
    public void testViewingItemsInCart() {
        cart.addToCart("Sauce Labs Backpack");
        cart.addToCart("Sauce Labs Bike Light");
        cart.openCart();
        int itemCount = cart.getCartItems().size();
        BaseTest.validateTestResult(itemCount == 2, "TestCase ID:DPSAT-T13 - Verify Viewing Items in Cart");
        Assert.assertEquals(itemCount, 2, "Cart should display 2 items");
    }

    @Test(priority = 4)
    public void testRemovingSingleItemFromCart() {
        cart.addToCart("Sauce Labs Backpack");
        cart.addToCart("Sauce Labs Bike Light");
        cart.openCart();
        cart.removeItem("Sauce Labs Backpack");
        int itemCount = cart.getCartItems().size();
        BaseTest.validateTestResult(itemCount == 1, "TestCase ID:DPSAT-T14 - Verify Removing a Single Item from Cart");
        Assert.assertEquals(itemCount, 1, "Cart should contain 1 item after removal");
    }

    @Test(priority = 5)
    public void testClearingAllItemsFromCart() {
        cart.addToCart("Sauce Labs Backpack");
        cart.addToCart("Sauce Labs Bike Light");
        cart.openCart();
        cart.clearCart();
        int cartCount = cart.getCartItemCount();
        BaseTest.validateTestResult(cartCount == 0, "TestCase ID:DPSAT-T15 - Verify Clearing All Items from Cart");
        Assert.assertEquals(cartCount, 0, "Cart should be empty");
    }

    @Test(priority = 6)
    public void testProceedingToCheckoutWithCartItems() {
        cart.addToCart("Sauce Labs Backpack");
        cart.openCart();
        cart.proceedToCheckout();
        String currentUrl = driver.getCurrentUrl();
        BaseTest.validateTestResult(currentUrl.contains("checkout-step-one.html"), "TestCase ID:DPSAT-T19 - Verify Proceeding to Checkout with Cart Items");
        Assert.assertTrue(currentUrl.contains("checkout-step-one.html"), "User should be redirected to the checkout page");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
