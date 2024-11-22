package features.f4_checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Checkout {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By orderConfirmationMessage = By.className("complete-header");
    private By errorMessage = By.cssSelector("[data-test='error']");

    // Locators for order summary elements
    private By itemTotalLocator = By.className("summary_subtotal_label");
    private By taxLocator = By.className("summary_tax_label");
    private By totalLocator = By.className("summary_total_label");
    private By inventoryItemsLocator = By.className("inventory_item_price");
    
    public Checkout(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public String getOrderConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationMessage)).getText();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public boolean ErrorMessageIsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();
    }

    public boolean isOrderSummaryAccurate() {
        // Get all item prices
        List<WebElement> itemPrices = driver.findElements(inventoryItemsLocator);
        double calculatedSubtotal = 0.0;
        for (WebElement priceElement : itemPrices) {
            String priceText = priceElement.getText().replace("$", "");
            calculatedSubtotal += Double.parseDouble(priceText);
        }

        // Get displayed subtotal
        String subtotalText = driver.findElement(itemTotalLocator).getText().split("\\$")[1];
        double displayedSubtotal = Double.parseDouble(subtotalText);

        // Check if calculated subtotal matches displayed subtotal
        if (Math.abs(calculatedSubtotal - displayedSubtotal) > 0.01) {
            return false;
        }

        // Get tax amount
        String taxText = driver.findElement(taxLocator).getText().split("\\$")[1];
        double taxAmount = Double.parseDouble(taxText);

        // Get total amount
        String totalText = driver.findElement(totalLocator).getText().split("\\$")[1];
        double totalAmount = Double.parseDouble(totalText);

        // Check if total is accurate (subtotal + tax)
        double calculatedTotal = calculatedSubtotal + taxAmount;
        return Math.abs(calculatedTotal - totalAmount) <= 0.01;
    }
}
