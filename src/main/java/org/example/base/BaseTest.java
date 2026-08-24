package org.example.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.nio.file.Files;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        driver.quit();
    }


    public void takeScreenshot(String fileName) {

        try {

            TakesScreenshot screenshot =
                    (TakesScreenshot) driver;

            File source =
                    screenshot.getScreenshotAs(OutputType.FILE);

            File destination =
                    new File("screenshots/" + fileName + ".png");

            Files.createDirectories(destination.toPath().getParent());

            Files.copy(
                    source.toPath(),
                    destination.toPath()
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}