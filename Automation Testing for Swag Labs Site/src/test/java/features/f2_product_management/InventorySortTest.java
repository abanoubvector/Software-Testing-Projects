package features.f2_product_management;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import helper.transformers.JSONHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class InventorySortTest extends BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    Inventory inventory;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventory = new Inventory(driver);
    }

    @BeforeMethod
    public void setupMethod() {
        driver.get(config.base_url);
    }

    @Test(priority = 1)
    public void testSorting() throws FileNotFoundException, InterruptedException {
        String[] testData = JSONHelper.parseJSON(config.JsonFiles.AUTH_TEST_DATA.getFileName(), 0);
        if (testData[3].equals("valid_credentials")) {
            loginPage.login(testData[0], testData[1]);

            inventory.sortProducts("Price (high to low)");
            Assert.assertTrue(inventory.areProductsSortedByPrice(false), "Products are not sorted correctly by price (high to low)");

            inventory.sortProducts("Name (Z to A)");
            Assert.assertTrue(inventory.areProductsSortedByName(false), "Products are not sorted correctly by name (Z to A)");

            inventory.sortProducts("Name (A to Z)");
            Assert.assertTrue(inventory.areProductsSortedByName(true), "Products are not sorted correctly by name (A to Z)");

            inventory.sortProducts("Price (low to high)");
            Assert.assertTrue(inventory.areProductsSortedByPrice(true), "Products are not sorted correctly by price (low to high)");

            BaseTest.validateTestResult(true, "Sorting functionality");
        } else {
            System.out.println("Wrong Data!");
            BaseTest.validateTestResult(false, "Sorting functionality - Invalid credentials");
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}