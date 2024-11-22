package features.f3_shopping_cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Cart {
    private WebDriver driver;

    // Locators
    private By addToCartButton = By.cssSelector(".btn_inventory");
    private By cartIcon = By.className("shopping_cart_badge");
    private By cartItems = By.className("cart_item");
    private By removeButton = By.cssSelector(".btn_secondary.cart_button");
    private By checkoutButton = By.id("checkout");

    public Cart(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart(String productName) {
        WebElement product = driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']"));
        product.findElement(addToCartButton).click();
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(driver.findElement(cartIcon).getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public List<WebElement> getCartItems() {
        return driver.findElements(cartItems);
    }

    public void removeItem(String productName) {
        WebElement item = driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']"));
        item.findElement(removeButton).click();
    }

    public void clearCart() {
        List<WebElement> items = getCartItems();
        for (WebElement item : items) {
            item.findElement(removeButton).click();
        }
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
