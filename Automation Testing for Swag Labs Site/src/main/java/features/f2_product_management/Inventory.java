package features.f2_product_management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Inventory {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By productSort = By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select");
    By productList = By.className("inventory_item");
    By productName = By.className("inventory_item_name");
    By productPrice = By.className("inventory_item_price");
    By addToCartButton = By.xpath("//button[starts-with(@id, 'add-to-cart-')]");

    // Constructor
    public Inventory(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void sortProducts(String sortTechnique) throws InterruptedException {
        Select objSelect = new Select(driver.findElement(productSort));
        objSelect.selectByVisibleText(sortTechnique);
        Thread.sleep(3000);
    }

    public List<WebElement> getProductList() {
        return driver.findElements(productList);
    }

    public void addProductToCart(String productName) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button[starts-with(@id, 'add-to-cart-')]", productName);
        driver.findElement(By.xpath(xpath)).click();
    }

    public boolean isProductAddedToCart(String productName) {
        //String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button[starts-with(@id, 'remove-')]", productName);
        return !driver.findElements(addToCartButton).isEmpty();
    }

    public boolean areProductsSortedByPrice(boolean ascending) {
        List<WebElement> prices = driver.findElements(productPrice);
        double previousPrice = ascending ? Double.MIN_VALUE : Double.MAX_VALUE;
        for (WebElement priceElement : prices) {
            double currentPrice = Double.parseDouble(priceElement.getText().replace("$", ""));
            if (ascending && currentPrice < previousPrice) {
                return false;
            } else if (!ascending && currentPrice > previousPrice) {
                return false;
            }
            previousPrice = currentPrice;
        }
        return true;
    }

    public boolean areProductsSortedByName(boolean ascending) {
        List<WebElement> names = driver.findElements(productName);
        String previousName = ascending ? "" : "zzz";
        for (WebElement nameElement : names) {
            String currentName = nameElement.getText();
            if (ascending && currentName.compareTo(previousName) < 0) {
                return false;
            } else if (!ascending && currentName.compareTo(previousName) > 0) {
                return false;
            }
            previousName = currentName;
        }
        return true;
    }
}