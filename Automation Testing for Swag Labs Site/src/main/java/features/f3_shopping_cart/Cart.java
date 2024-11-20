package features.f3_shopping_cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Cart {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By cartIcon = By.className("shopping_cart_link");
    By addToCartButton = By.xpath("//button[text()='Add to cart']");
    By removeButton = By.xpath("//button[text()='Remove']");
    By cartItemsList = By.className("cart_item");
    By itemQuantity = By.className("cart_quantity");
    By checkoutButton = By.id("checkout");
    By errorMessage = By.cssSelector("[data-test='error']");

    // Constructor
    public Cart(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Methods
    public void addItemToCart(String itemName) {
        By addToCartLocator = By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']");
        driver.findElement(addToCartLocator).click();
    }

    public int getCartItemCount() {
        String cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon)).getText();
        return cartBadge.isEmpty() ? 0 : Integer.parseInt(cartBadge);
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public void removeItemFromCart(String itemName) {
        By removeButtonLocator = By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='cart_item']//button[text()='Remove']");
        driver.findElement(removeButtonLocator).click();
    }

    public void removeAllItemsFromCart() {
        openCart();
        List<WebElement> removeButtons = driver.findElements(removeButton);
        for (WebElement button : removeButtons) {
            button.click();
        }
    }

    public void updateItemQuantity(String itemName, int newQuantity) {
        // Assuming there's a way to update quantity, which isn't clear from the provided HTML
        // This is a placeholder implementation
        By quantityInputLocator = By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='cart_item']//input[@class='cart_quantity']");
        WebElement quantityInput = driver.findElement(quantityInputLocator);
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(newQuantity));
        // Assuming there's an update button
        By updateButtonLocator = By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='cart_item']//button[text()='Update']");
        driver.findElement(updateButtonLocator).click();
    }

    public int getItemQuantity(String itemName) {
        By quantityLocator = By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='cart_item']//div[@class='cart_quantity']");
        String quantityText = driver.findElement(quantityLocator).getText();
        return Integer.parseInt(quantityText);
    }

    public List<WebElement> getCartItems() {
        return driver.findElements(cartItemsList);
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}