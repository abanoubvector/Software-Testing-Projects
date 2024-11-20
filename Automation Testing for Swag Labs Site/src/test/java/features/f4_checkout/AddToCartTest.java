package features.f4_checkout;

import features.f1_auth.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCartTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }
    @Test
    public void testAddToCart() throws InterruptedException {
         // Open the website
            driver.get("https://awesomeqa.com/ui/index.php?route=product/product&product_id=40");

            // Click the 'Add to Cart' button
            WebElement addToCartButton = driver.findElement(By.id("button-cart"));
            addToCartButton.click();
            //Thread.sleep(3000);
            // Wait until the alert is visible (using WebDriverWait)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#product-product > div.alert.alert-success.alert-dismissible")));
            //Thread.sleep(3000);
            System.out.println(alert.getText());
            // Check if the alert is displayed
            if (alert.isDisplayed()) {
                System.out.println("Success: Alert is displayed after adding the product to the cart.");
            } else {
                System.out.println("Failure: Alert is not displayed.");
            }
            // Close the browser
            driver.quit();
        }
    }
