package features.f1_auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By loginErrorMessage = By.cssSelector("[data-test='error']");
    By unAuthorizedErrorMessage = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");
    By productTitle = By.cssSelector("[data-test='title']");

    By passwordInputType = By.id("password");


    /*******************************************************************************************************************************************************/
    //Responses
    String valid_credentials_response = "Products";
    String invalid_username_response = "Epic sadface: Username and password do not match any user in this service";
    String invalid_password_response = "Epic sadface: Username and password do not match any user in this service";
    String empty_username_response = "Epic sadface: Username is required";
    String empty_password_response = "Epic sadface: Password is required";
    String empty_username_and_password_response = "Epic sadface: Username is required";
    String locked_user_response = "Epic sadface: Sorry, this user has been locked out.";
    String username_Case_Sensitivity_response = "Epic sadface: Username and password do not match any user in this service";
    String password_Case_Sensitivity_response = "Epic sadface: Username and password do not match any user in this service";
    String SQL_injection_response = "Epic sadface: Username and password do not match any user in this service";
    String password_input_type_response = "password";
    String navigate_to_inventory_without_credentials_response = "Epic sadface: You can only access '/inventory.html' when you are logged in.";
    /*******************************************************************************************************************************************************/


    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Methods
    public void enterUsername(String username) {driver.findElement(usernameField).sendKeys(username);}

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {driver.findElement(loginButton).click();}

    public String getLoginErrorMessage() {return wait.until(ExpectedConditions.visibilityOfElementLocated(loginErrorMessage)).getText();}

    public String getUnAuthorizedErrorMessage() {return driver.findElement(unAuthorizedErrorMessage).getText();}

    public String getProductTitle() {return wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).getText();}

    public String getPasswordInputType() {return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputType)).getAttribute("type");}

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}