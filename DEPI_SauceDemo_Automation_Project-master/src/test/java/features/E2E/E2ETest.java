package features.E2E;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import features.f1_auth.Logout;
import features.f3_shopping_cart.Cart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class E2ETest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private Cart cart;
    private Logout logout;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        cart = new Cart(driver);
        logout = new Logout(driver);
        driver.get(config.base_url);
    }

    @Test(priority = 1)
    public void testEndToEndScenario() {
        // Step 1: Login
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "User should be logged in and redirected to the inventory page");

        // Step 2: Add items to the cart
        cart.addToCart("Sauce Labs Backpack");
        cart.addToCart("Sauce Labs Bike Light");
        int cartCount = cart.getCartItemCount();
        Assert.assertEquals(cartCount, 2, "Cart should contain 2 items");

        // Step 3: Proceed to checkout
        cart.openCart();
        cart.proceedToCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"), "User should be redirected to the checkout page");

        // Step 4: Logout
        logout.clickLogout();
        Assert.assertTrue(logout.isLogoutSuccessful(), "User should be logged out and redirected to the login page");
    }

    @Test(priority = 2)
    public void testLoginAndAddToCart() {
        // Step 1: Login
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "User should be logged in and redirected to the inventory page");

        // Step 2: Add items to the cart
        cart.addToCart("Sauce Labs Backpack");
        cart.addToCart("Sauce Labs Bike Light");
        int cartCount = cart.getCartItemCount();
        Assert.assertEquals(cartCount, 2, "Cart should contain 2 items");
    }

    @Test(priority = 3)
    public void testProceedToCheckout() {
        // Precondition: User is logged in and items are added to the cart
        loginPage.login("standard_user", "secret_sauce");
        cart.addToCart("Sauce Labs Backpack");
        cart.openCart();

        // Step 3: Proceed to checkout
        cart.proceedToCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"), "User should be redirected to the checkout page");
    }

    @Test(priority = 4)
    public void testLogout() {
        // Precondition: User is logged in
        loginPage.login("standard_user", "secret_sauce");

        // Step 4: Logout
        logout.clickLogout();
        Assert.assertTrue(logout.isLogoutSuccessful(), "User should be logged out and redirected to the login page");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
