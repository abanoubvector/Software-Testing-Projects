package features.f2_product_management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By productName = By.className("inventory_item_name");
    By productPrice = By.className("inventory_item_price");
    By productDescription = By.className("inventory_item_desc");
    By addToCartButton = By.xpath("//button[starts-with(@id, 'add-to-cart-')]");
    By removeButton = By.xpath("//button[starts-with(@id, 'remove-')]");


    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public boolean isAddedToCart() {
        return !driver.findElements(removeButton).isEmpty();
    }
}