package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;

    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueButton = By.id("continue");
    By finishButton = By.id("finish");
    By confirmationMessage = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String name) {
        driver.findElement(firstName).sendKeys(name);
    }

    public void enterLastName(String name) {
        driver.findElement(lastName).sendKeys(name);
    }

    public void enterPostalCode(String code) {
        driver.findElement(postalCode).sendKeys(code);
    }

    public void clickContinue() {

        driver.findElement(continueButton).click();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        );
    }

    public String getConfirmationMessage() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Current URL after Finish: " + driver.getCurrentUrl());
        System.out.println("Current Page Title: " + driver.getTitle());

        wait.until(
                ExpectedConditions.urlContains("checkout-complete")
        );

        return driver.findElement(
                By.cssSelector(".complete-header")
        ).getText();
    }
    public void clickFinish() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        );

        driver.findElement(finishButton).click();
    }
}