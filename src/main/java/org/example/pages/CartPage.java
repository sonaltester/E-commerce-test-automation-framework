package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    By cartButton = By.className("shopping_cart_link");
    By cartItem = By.className("inventory_item_name");
    By removeButton = By.id("remove-sauce-labs-backpack");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openCart() {
        driver.findElement(cartButton).click();
    }

    public String getProductName() {
        return driver.findElement(cartItem).getText();
    }

    public void removeProduct() {
        driver.findElement(removeButton).click();
    }

    public boolean isProductDisplayed() {
        return driver.findElements(cartItem).size() > 0;
    }

    public void clickCheckout() {
        driver.findElement(By.id("checkout")).click();
    }
}