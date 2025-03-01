package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import utils.ExtentManager;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected AppiumDriver driver;
    protected ExtentTest test;

    @Parameters({"platform", "deviceName", "platformVersion", "appiumPort"})
    @BeforeClass
    public void setUp(String platform, String deviceName, String platformVersion, String appiumPort) throws MalformedURLException {
        driver = createDriver(platform, deviceName, platformVersion, appiumPort);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("✅ " + platform + " (" + deviceName + ") launched successfully on Appium port " + appiumPort);
    }

    private AppiumDriver createDriver(String platform, String deviceName, String platformVersion, String appiumPort) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String appPath = System.getProperty("user.dir") + (platform.equalsIgnoreCase("Android") ? "/resources/android-app.apk" : "/resources/ios-app.app");

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        caps.setCapability(MobileCapabilityType.APP, new File(appPath).getAbsolutePath());

        if (platform.equalsIgnoreCase("Android")) {
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            return new AndroidDriver(new URL("http://127.0.0.1:" + appiumPort + "/wd/hub"), caps);
        } else {
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            return new IOSDriver(new URL("http://127.0.0.1:" + appiumPort + "/wd/hub"), caps);
        }
    }

    @AfterMethod
    public void captureFailureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
            ExtentManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());
            ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath);
            attachScreenshotAllure(result.getMethod().getMethodName());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Driver closed.");
        }
        ExtentManager.flush();
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public void attachScreenshotAllure(String methodName) {
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public String takeScreenshot(String methodName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
        String filePath = screenshotDir + methodName + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
