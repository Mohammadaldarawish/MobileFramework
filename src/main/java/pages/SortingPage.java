package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class SortingPage extends BasePage {

    private final By sortButton = By.xpath("//XCUIElementTypeButton[@name='Sort by:']");
    private final String sortOptionTemplate = "//XCUIElementTypeStaticText[@name='%s']";
    private final By productNames = By.xpath("//XCUIElementTypeStaticText[contains(@name, 'Sauce Labs')]");
    private final By productPrices = By.xpath("//XCUIElementTypeStaticText[contains(@name, '$')]");
    private final By sortMenu = By.xpath("//XCUIElementTypeSheet");
    private final By selectedSortOption = By.xpath("//XCUIElementTypeSheet//XCUIElementTypeStaticText[contains(@name, '✓')]");

    public SortingPage(AppiumDriver driver) {
        super(driver);
    }

    public void sortBy(String option) {
        click(sortButton);
        By sortOptionLocator = By.xpath(String.format(sortOptionTemplate, option));
        click(sortOptionLocator);
    }

    public List<String> getProductNames() {
        return driver.findElements(productNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        return driver.findElements(productPrices).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public void openSortMenu() {
        click(sortButton);
    }

    public boolean isSortMenuDisplayed() {
        return !driver.findElements(sortMenu).isEmpty();
    }

    public String getSelectedSortOption() {
        return getText(selectedSortOption);
    }

    public void clickOutsideSortMenu() {
        new TouchAction<>((PerformsTouchActions) driver)
                .tap(TapOptions.tapOptions()
                        .withPosition(PointOption.point(100, 100))) // Tap at coordinates (100,100)
                .perform();
    }
}
