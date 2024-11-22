package features.f2_product_management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryList {
    private WebDriverWait wait;

    // Locators
    private By productList = By.className("inventory_item");
    private By productName = By.className("inventory_item_name");
    private By productImage = By.className("inventory_item_img");
    private By productDescription = By.className("inventory_item_desc");
    private By productPrice = By.className("inventory_item_price");

    public InventoryList(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<WebElement> getAllProducts() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productList));
    }

    public boolean areProductDetailsDisplayed() {
        List<WebElement> products = getAllProducts();
        for (WebElement product : products) {
            if (!product.findElement(productName).isDisplayed() ||
                !product.findElement(productImage).isDisplayed() ||
                !product.findElement(productDescription).isDisplayed() ||
                !product.findElement(productPrice).isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public WebElement getProductByName(String name) {
        List<WebElement> products = getAllProducts();
        for (WebElement product : products) {
            if (product.findElement(productName).getText().equals(name)) {
                product = product.findElement(productName);
                return product;
            }
        }
        return null;
    }
}
