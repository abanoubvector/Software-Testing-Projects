package features.f3_shopping_cart;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.util.List;

public class CartTest extends BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    Cart cart;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        cart = new Cart(driver);
    }

    @BeforeMethod
    public void setupMethod() throws FileNotFoundException {
        driver.get(config.base_url);
        // Login with valid credentials
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(priority = 1)
    public void testAddItemToCart() {
        cart.addItemToCart("Sauce Labs Backpack");
        Assert.assertEquals(cart.getCartItemCount(), 1, "Cart should contain 1 item");
        BaseTest.validateTestResult(cart.getCartItemCount() == 1, "TC_001 - Add Item to Cart");
    }

    @Test(priority = 2)
    public void testAddMultipleItemsToCart() {
        cart.addItemToCart("Sauce Labs Backpack");
        cart.addItemToCart("Sauce Labs Bike Light");
        Assert.assertEquals(cart.getCartItemCount(), 2, "Cart should contain 2 items");
        BaseTest.validateTestResult(cart.getCartItemCount() == 2, "TC_002 - Add Multiple Items to Cart");
    }

    @Test(priority = 3)
    public void testAddDuplicatedProduct() {
        cart.addItemToCart("Sauce Labs Backpack");
        cart.addItemToCart("Sauce Labs Backpack");
        cart.openCart();
        int quantity = cart.getItemQuantity("Sauce Labs Backpack");
        Assert.assertEquals(quantity, 2, "Quantity of Sauce Labs Backpack should be 2");
        BaseTest.validateTestResult(quantity == 2, "TC_003 - Add Duplicated Product");
    }

    @Test(priority = 4)
    public void testViewCartItems() {
        cart.addItemToCart("Sauce Labs Backpack");
        cart.addItemToCart("Sauce Labs Bike Light");
        cart.openCart();
        List<WebElement> cartItems = cart.getCartItems();
        Assert.assertEquals(cartItems.size(), 2, "Cart should contain 2 items");
        BaseTest.validateTestResult(cartItems.size() == 2, "TC_004 - View Cart Items");
    }

    @Test(priority = 5)
    public void testRemoveItemFromCart() {
        cart.addItemToCart("Sauce Labs Backpack");
        cart.addItemToCart("Sauce Labs Bike Light");
        cart.openCart();
        cart.removeItemFromCart("Sauce Labs Backpack");
        Assert.assertEquals(cart.getCartItemCount(), 1, "Cart should contain 1 item after removal");
        BaseTest.validateTestResult(cart.getCartItemCount() == 1, "TC_005 - Remove Item from Cart");
    }

    @Test(priority = 6)
    public void testRemoveAllItemsFromCart() {
        cart.addItemToCart("Sauce Labs Backpack");
        cart.addItemToCart("Sauce Labs Bike Light");
        cart.removeAllItemsFromCart();
        Assert.assertEquals(cart.getCartItemCount(), 0, "Cart should be empty");
        BaseTest.validateTestResult(cart.getCartItemCount() == 0, "TC_006 - Remove All Items from Cart");
    }

    @Test(priority = 7)
    public void testUpdateItemQuantity() {
        cart.addItemToCart("Sauce Labs Backpack");
        cart.openCart();
        cart.updateItemQuantity("Sauce Labs Backpack", 2);
        int quantity = cart.getItemQuantity("Sauce Labs Backpack");
        Assert.assertEquals(quantity, 2, "Quantity of Sauce Labs Backpack should be updated to 2");
        BaseTest.validateTestResult(quantity == 2, "TC_007 - Update Item Quantity");
    }

    @Test(priority = 8)
    public void testAddProductWithNoStock() {
        cart.addItemToCart("Sauce Labs Onesie");
        String errorMessage = cart.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("out of stock"), "Error message should indicate item is out of stock");
        BaseTest.validateTestResult(errorMessage.contains("out of stock"), "TC_008 - Add Product to The Cart with No Stock");
    }

    @Test(priority = 9)
    public void testProceedToCheckout() {
        cart.addItemToCart("Sauce Labs Backpack");
        cart.openCart();
        cart.proceedToCheckout();
        // Assuming the URL changes when proceeding to checkout
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"), "User should be redirected to checkout page");
        BaseTest.validateTestResult(driver.getCurrentUrl().contains("checkout"), "TC_009 - Proceed to Checkout");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}