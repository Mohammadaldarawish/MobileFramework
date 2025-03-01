package testcases;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SortingPage;
import utils.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.util.List;
import java.util.stream.Collectors;

public class SortingTests extends BaseTest {
    private SortingPage sortingPage;
    private ExtentTest test;

    @BeforeClass
    public void setUpPageObjects() {
        sortingPage = new SortingPage(driver);
    }

    @Test(priority = 1)
    @Description("Verify sorting by Name in Ascending order")
    public void testSortByNameAscending() {
        test = ExtentManager.getInstance().createTest("testSortByNameAscending");
        ExtentManager.setTest(test);

        logStep("Sorting products by name (Ascending)");
        sortingPage.sortBy("Name - Ascending");

        List<String> productNames = sortingPage.getProductNames();
        List<String> sortedNames = productNames.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(productNames, sortedNames, "❌ Products are not sorted in ascending order by name!");
        test.log(Status.PASS, "✅ Products sorted correctly by Name (Ascending).");
    }

    @Test(priority = 2)
    @Description("Verify sorting by Name in Descending order")
    public void testSortByNameDescending() {
        test = ExtentManager.getInstance().createTest("testSortByNameDescending");
        ExtentManager.setTest(test);

        logStep("Sorting products by name (Descending)");
        sortingPage.sortBy("Name - Descending");

        List<String> productNames = sortingPage.getProductNames();
        List<String> sortedNames = productNames.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());

        Assert.assertEquals(productNames, sortedNames, "❌ Products are not sorted in descending order by name!");
        test.log(Status.PASS, "✅ Products sorted correctly by Name (Descending).");
    }

    @Test(priority = 3)
    @Description("Verify sorting by Price in Ascending order")
    public void testSortByPriceAscending() {
        test = ExtentManager.getInstance().createTest("testSortByPriceAscending");
        ExtentManager.setTest(test);

        logStep("Sorting products by price (Ascending)");
        sortingPage.sortBy("Price - Ascending");

        List<Double> productPrices = sortingPage.getProductPrices();
        List<Double> sortedPrices = productPrices.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(productPrices, sortedPrices, "❌ Products are not sorted in ascending order by price!");
        test.log(Status.PASS, "✅ Products sorted correctly by Price (Ascending).");
    }

    @Test(priority = 4)
    @Description("Verify sorting by Price in Descending order")
    public void testSortByPriceDescending() {
        test = ExtentManager.getInstance().createTest("testSortByPriceDescending");
        ExtentManager.setTest(test);

        logStep("Sorting products by price (Descending)");
        sortingPage.sortBy("Price - Descending");

        List<Double> productPrices = sortingPage.getProductPrices();
        List<Double> sortedPrices = productPrices.stream().sorted((a, b) -> Double.compare(b, a)).collect(Collectors.toList());

        Assert.assertEquals(productPrices, sortedPrices, "❌ Products are not sorted in descending order by price!");
        test.log(Status.PASS, "✅ Products sorted correctly by Price (Descending).");
    }

    @Test(priority = 5)
    @Description("Verify sorting menu is displayed when clicked")
    public void testSortMenuDisplayed() {
        test = ExtentManager.getInstance().createTest("testSortMenuDisplayed");
        ExtentManager.setTest(test);

        logStep("Opening sorting menu");
        sortingPage.openSortMenu();

        Assert.assertTrue(sortingPage.isSortMenuDisplayed(), "❌ Sorting menu is not displayed!");
        test.log(Status.PASS, "✅ Sorting menu is displayed correctly.");
    }

    @Test(priority = 6)
    @Description("Verify default sorting option is selected correctly")
    public void testDefaultSortingOption() {
        test = ExtentManager.getInstance().createTest("testDefaultSortingOption");
        ExtentManager.setTest(test);

        logStep("Checking default sorting option");
        String selectedOption = sortingPage.getSelectedSortOption();

        Assert.assertEquals(selectedOption, "Name - Ascending", "❌ Default sorting option is incorrect!");
        test.log(Status.PASS, "✅ Default sorting option is correctly selected: " + selectedOption);
    }

    @Test(priority = 7)
    @Description("Verify clicking outside closes the sorting menu")
    public void testSortMenuClosesOnOutsideClick() {
        test = ExtentManager.getInstance().createTest("testSortMenuClosesOnOutsideClick");
        ExtentManager.setTest(test);

        logStep("Opening sorting menu");
        sortingPage.openSortMenu();
        Assert.assertTrue(sortingPage.isSortMenuDisplayed(), "❌ Sorting menu did not open!");

        logStep("Clicking outside the sorting menu");
        sortingPage.clickOutsideSortMenu();

        Assert.assertFalse(sortingPage.isSortMenuDisplayed(), "❌ Sorting menu did not close!");
        test.log(Status.PASS, "✅ Sorting menu closed correctly when clicked outside.");
    }

    @Step("Logging Step: {0}")
    public void logStep(String message) {
        System.out.println("✅ " + message);
        test.log(Status.INFO, message);
    }
}
