package features.f4_checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Checkout {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By cartButton = By.className("shopping_cart_link");
    By checkoutButton = By.id("checkout");
    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By postalCodeField = By.id("postal-code");
    By continueButton = By.id("continue");
    By finishButton = By.id("finish");
    By checkoutCompleteHeader = By.className("complete-header");
    By errorMessage = By.cssSelector("[data-test='error']");

    // Responses
    public String checkoutCompleteResponse = "Thank you for your order!";

    // Constructor
    public Checkout(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void goToCart() {
        driver.findElement(cartButton).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public String getCheckoutCompleteMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutCompleteHeader)).getText();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public void completeCheckout(String firstName, String lastName, String postalCode) {
        goToCart();
        clickCheckout();
        enterCheckoutInformation(firstName, lastName, postalCode);
        clickContinue();
        clickFinish();
    }
}