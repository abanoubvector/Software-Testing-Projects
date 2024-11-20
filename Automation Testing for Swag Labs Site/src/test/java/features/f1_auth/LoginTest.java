package features.f1_auth;

import config.config;
import features.Base.BaseTest;
import features.f2_product_management.Inventory;
import helper.transformers.JSONHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

public class LoginTest extends BaseTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @BeforeMethod
    public void setupMethod() {
        driver.get(config.base_url);
    }


    @Test(priority = 1)
    public void test_login_with_valid_credentials() throws FileNotFoundException {
        String[] testData = JSONHelper.parseJSON(config.JsonFiles.AUTH_TEST_DATA.getFileName(), 0);
        if (testData[3].equals("valid_credentials")) {
            loginPage.login(testData[0], testData[1]);
            String titleText = loginPage.getProductTitle();
            BaseTest.validateTestResult(titleText.equals(loginPage.valid_credentials_response), "TestCase ID:001 - Valid Login");
            Assert.assertEquals(titleText, loginPage.valid_credentials_response);
        } else {
            System.out.println("Wrong Data!");
        }
    }

    @Test(priority = 2)
    public void test_login_with_inValid_username() throws FileNotFoundException {
        String[] testData = JSONHelper.parseJSON(config.JsonFiles.AUTH_TEST_DATA.getFileName(), 1);
        if (testData[3].equals("invalid_username")) {
            loginPage.login(testData[0], testData[1]);
            String errorText = loginPage.getLoginErrorMessage();
            BaseTest.validateTestResult(errorText.equals(loginPage.invalid_username_response), "TestCase ID:002 - Invalid Username");
            Assert.assertEquals(errorText, loginPage.invalid_username_response);
        } else {
            System.out.println("Wrong Data!");
        }
    }

    @Test(priority = 3)
    public void test_login_with_invalid_password() throws FileNotFoundException {
        String[] testData = JSONHelper.parseJSON(config.JsonFiles.AUTH_TEST_DATA.getFileName(), 2);
        if (testData[3].equals("invalid_password")) {
            loginPage.login(testData[0], testData[1]);
            String errorText = loginPage.getLoginErrorMessage();
            BaseTest.validateTestResult(errorText.equals(loginPage.invalid_password_response), "TestCase ID:003 - Invalid Password");
            Assert.assertEquals(errorText, loginPage.invalid_password_response);
        } else {
            System.out.println("Wrong Data!");
        }
    }

    @Test(priority = 4)
    public void testEmptyUsername() throws FileNotFoundException {
        String[] testData = JSONHelper.parseJSON(config.JsonFiles.AUTH_TEST_DATA.getFileName(), 3);
        if (testData[3].equals("empty_username")) {
            loginPage.login(testData[0], testData[1]);
            String errorText = loginPage.getLoginErrorMessage();
            BaseTest.validateTestResult(errorText.equals(loginPage.empty_username_response), "TestCase ID:004 - Empty Username");
            Assert.assertEquals(errorText, loginPage.empty_username_response);
        } else {
            System.out.println("Wrong Data!");
        }
    }

    @Test(priority = 5)
    public void testEmptyPassword() throws FileNotFoundException {
        String[] testData = JSONHelper.parseJSON(config.JsonFiles.AUTH_TEST_DATA.getFileName(), 4);
        if (testData[3].equals("empty_password")) {
            loginPage.login(testData[0], testData[1]);
            String errorText = loginPage.getLoginErrorMessage();
            BaseTest.validateTestResult(errorText.equals(loginPage.empty_password_response), "TestCase ID:005 - Empty Password");
            Assert.assertEquals(errorText, loginPage.empty_password_response);
        } else {
            System.out.println("Wrong Data!");
        }
    }

    @Test(priority = 6)
    public void testEmptyUsernameAndPassword() throws FileNotFoundException {
        String[] testData = JSONHelper.parseJSON(config.JsonFiles.AUTH_TEST_DATA.getFileName(), 5);
        if (testData[3].equals("empty_username_and_password")) {
            loginPage.login(testData[0], testData[1]);
            String errorText = loginPage.getLoginErrorMessage();
            BaseTest.validateTestResult(errorText.equals(loginPage.empty_username_and_password_response), "TestCase ID:006 - Empty Username and Password");
            Assert.assertEquals(errorText, loginPage.empty_username_and_password_response);
        } else {
            System.out.println("Wrong Data!");
        }
    }

    @Test(priority = 7)
    public void testLockedOutUser() throws FileNotFoundException {
        String[] testData = JSONHelper.parseJSON(config.JsonFiles.AUTH_TEST_DATA.getFileName(), 6);
        if (testData[3].equals("locked_user")) {
            loginPage.login(testData[0], testData[1]);
            String errorText = loginPage.getLoginErrorMessage();
            BaseTest.validateTestResult(errorText.equals(loginPage.locked_user_response), "TestCase ID:007 - Locked Out User");
            Assert.assertEquals(errorText, loginPage.locked_user_response);
        } else {
            System.out.println("Wrong Data!");
        }
    }

    @Test(priority = 8)
    public void testCaseSensitivityForUsername() throws FileNotFoundException {
        String[] testData = JSONHelper.parseJSON(config.JsonFiles.AUTH_TEST_DATA.getFileName(), 7);
        if (testData[3].equals("username_Case_Sensitivity")) {
            loginPage.login(testData[0], testData[1]);
            String errorText = loginPage.getLoginErrorMessage();
            BaseTest.validateTestResult(errorText.equals(loginPage.username_Case_Sensitivity_response), "TestCase ID:008 - Case Sensitivity on Username");
            Assert.assertEquals(errorText, loginPage.username_Case_Sensitivity_response);
        } else {
            System.out.println("Wrong Data!");
        }
    }

    @Test(priority = 9)
    public void testCaseSensitivityForPassword() throws FileNotFoundException {
        String[] testData = JSONHelper.parseJSON(config.JsonFiles.AUTH_TEST_DATA.getFileName(), 8);
        if (testData[3].equals("password_Case_Sensitivity")) {
            loginPage.login(testData[0], testData[1]);
            String errorText = loginPage.getLoginErrorMessage();
            BaseTest.validateTestResult(errorText.equals(loginPage.password_Case_Sensitivity_response), "TestCase ID:009 - Case Sensitivity on Password");
            Assert.assertEquals(errorText, loginPage.password_Case_Sensitivity_response);
        } else {
            System.out.println("Wrong Data!");
        }
    }

    @Test(priority = 10)
    public void test_SQL_injection_on_login() throws FileNotFoundException {
        String[] testData = JSONHelper.parseJSON(config.JsonFiles.AUTH_TEST_DATA.getFileName(), 9);
        if (testData[3].equals("SQL_injection")) {
            loginPage.login(testData[0], testData[1]);
            String errorText = loginPage.getLoginErrorMessage();
            BaseTest.validateTestResult(errorText.equals(loginPage.SQL_injection_response), "TestCase ID:010 - SQL Injection on Login");
            Assert.assertEquals(errorText, loginPage.SQL_injection_response);
        } else {
            System.out.println("Wrong Data!");
        }
    }

    @Test(priority = 11)
    public void passwordFieldMasking() {
        String passwordFieldType = loginPage.getPasswordInputType();
        BaseTest.validateTestResult(passwordFieldType.equals(loginPage.password_input_type_response), "TestCase ID:011 - Password Field Masking");
        Assert.assertEquals(passwordFieldType, loginPage.password_input_type_response);
    }

    @Test(priority = 12)
    public void navigate_to_inventory_without_credentials()  {
        driver.get(config.inventory_url);
        String errorText = loginPage.getUnAuthorizedErrorMessage();
        BaseTest.validateTestResult(errorText.equals(loginPage.navigate_to_inventory_without_credentials_response), "TestCase ID:012 - Accessing 'inventory.html' when logged out");
        Assert.assertEquals(errorText, loginPage.navigate_to_inventory_without_credentials_response);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
