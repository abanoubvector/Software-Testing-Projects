package features.f2_product_management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventorySort {
    private WebDriver driver;

    // Locators
    private By sortDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");

    public InventorySort(WebDriver driver) {
        this.driver = driver;
    }

    public void sortBy(String option) {
        Select dropdown = new Select(driver.findElement(sortDropdown));
        dropdown.selectByVisibleText(option);
    }

    public boolean isNameSortedAToZ() {
        List<WebElement> names = driver.findElements(productNames);
        String previousName = "";
        for (WebElement nameElement : names) {
            String currentName = nameElement.getText();
            if (currentName.compareToIgnoreCase(previousName) < 0) {
                return false;
            }
            previousName = currentName;
        }
        return true;
    }

    public boolean isNameSortedZToA() {
        List<WebElement> names = driver.findElements(productNames);
        String previousName = "ZZZZZZZZZZ"; // A string that should come after all product names
        for (WebElement nameElement : names) {
            String currentName = nameElement.getText();
            if (currentName.compareToIgnoreCase(previousName) > 0) {
                return false;
            }
            previousName = currentName;
        }
        return true;
    }

    public boolean isPriceSortedLowToHigh() {
        List<WebElement> prices = driver.findElements(productPrices);
        double previousPrice = Double.MIN_VALUE;
        for (WebElement priceElement : prices) {
            double currentPrice = Double.parseDouble(priceElement.getText().replace("$", ""));
            if (currentPrice < previousPrice) {
                return false;
            }
            previousPrice = currentPrice;
        }
        return true;
    }

    public boolean isPriceSortedHighToLow() {
        List<WebElement> prices = driver.findElements(productPrices);
        double previousPrice = Double.MAX_VALUE;
        for (WebElement priceElement : prices) {
            double currentPrice = Double.parseDouble(priceElement.getText().replace("$", ""));
            if (currentPrice > previousPrice) {
                return false;
            }
            previousPrice = currentPrice;
        }
        return true;
    }
}
