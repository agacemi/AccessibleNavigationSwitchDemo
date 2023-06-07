package com.eot.sample.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;

import static com.eot.sample.Hooks.*;

public class AppiumAccessibilitySwitchTest {

    private AppiumDriver driver;

    @BeforeSuite
    public void beforeAll() {
        startAppiumServer();
    }

    @AfterSuite
    public void afterAll() {
        stopAppiumServer();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        String methodName = method.getName();
        String udid = "emulator-5554";
        log(String.format("Running test '%s' on '%s'", methodName, udid));
        driver = createAppiumDriver(udid);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (null != driver) {
            log("Close the driver");
            driver.quit();
        }
    }

    @Test
    public void runAccessibleNavigationSwitchTest() {
        log("Before any appium request, accessibleNavigation should be false");
        screenshot("before");
        driver.findElement(By.className("android.view.View"));
        screenshot("after");
        log("After appium request, accessibleNavigation should still be false but is true");
    }

    private void screenshot(String filename) {
        File srcFile=driver.getScreenshotAs(OutputType.FILE);
        String path_screenshot = "./src/test/resources/screens/flutter_" + System.getenv("FLUTTER_VERSION") + "/";
        File targetFile=new File(path_screenshot + filename +".jpg");
        try {
            FileUtils.copyFile(srcFile,targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void log(String message) {
        System.out.println(" ### " + new Date() + " ### " + message);
    }

    private AppiumDriver createAppiumDriver(String udid) {
        log(String.format("Create AppiumDriver with appium server with device udid - '%s'", udid));

        // Appium 1.x
        // DesiredCapabilities capabilities = new DesiredCapabilities();

        // Appium 2.x
        UiAutomator2Options capabilities = new UiAutomator2Options();

        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("autoGrantPermissions",
                true);
        capabilities.setCapability("app",
                new File("./src/test/resources/sampleApps/access_bug-" + System.getenv("FLUTTER_VERSION") + ".apk").getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
        URL appiumServerUrl = getAppiumServerUrl();
        AppiumDriver appiumDriver = new AppiumDriver(appiumServerUrl, capabilities);
        log(String.format("Created AppiumDriver for - %s, appiumPort - %s", udid, appiumServerUrl));
        return appiumDriver;
    }
}
