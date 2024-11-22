package features.f2_product_management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriverWait wait;

    // Locators
     By productName = By.className("inventory_details_name");
    private By productImage = By.className("inventory_details_img");
    private By productDescription = By.className("inventory_details_desc");
    private By productPrice = By.className("inventory_details_price");
    private By addToCartButton = By.cssSelector(".btn_inventory");

    public ProductPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean areProductDetailsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).isDisplayed() &&
               wait.until(ExpectedConditions.visibilityOfElementLocated(productImage)).isDisplayed() &&
               wait.until(ExpectedConditions.visibilityOfElementLocated(productDescription)).isDisplayed() &&
               wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice)).isDisplayed() &&
               wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).isDisplayed();
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }
}
