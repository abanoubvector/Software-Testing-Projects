package features.f2_product_management;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class InventorySortTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventorySort inventorySort;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventorySort = new InventorySort(driver);
    }

    @BeforeMethod
    public void navigateToInventory() {
        driver.get(config.base_url);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testProductSortingByNameAToZ() {
        inventorySort.sortBy("Name (A to Z)");
        boolean isSorted = inventorySort.isNameSortedAToZ();
        BaseTest.validateTestResult(isSorted, "TestCase ID:DPSAT-T35a - Verify Product Sorting Functionality by Name (A to Z)");
        Assert.assertTrue(isSorted, "Products should be sorted by name from A to Z");
    }

    @Test
    public void testProductSortingByNameZToA() {
        inventorySort.sortBy("Name (Z to A)");
        boolean isSorted = inventorySort.isNameSortedZToA();
        BaseTest.validateTestResult(isSorted, "TestCase ID:DPSAT-T35b - Verify Product Sorting Functionality by Name (Z to A)");
        Assert.assertTrue(isSorted, "Products should be sorted by name from Z to A");
    }

    @Test
    public void testProductSortingByPriceLowToHigh() {
        inventorySort.sortBy("Price (low to high)");
        boolean isSorted = inventorySort.isPriceSortedLowToHigh();
        BaseTest.validateTestResult(isSorted, "TestCase ID:DPSAT-T35c - Verify Product Sorting Functionality by Price (Low to High)");
        Assert.assertTrue(isSorted, "Products should be sorted by price from low to high");
    }

    @Test
    public void testProductSortingByPriceHighToLow() {
        inventorySort.sortBy("Price (high to low)");
        boolean isSorted = inventorySort.isPriceSortedHighToLow();
        BaseTest.validateTestResult(isSorted, "TestCase ID:DPSAT-T35d - Verify Product Sorting Functionality by Price (High to Low)");
        Assert.assertTrue(isSorted, "Products should be sorted by price from high to low");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
