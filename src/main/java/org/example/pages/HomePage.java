package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    WebDriver driver;

    By backpack = By.id("add-to-cart-sauce-labs-backpack");
    By cartCount = By.className("shopping_cart_badge");
    By sortDropdown = By.className("product_sort_container");
    By productPrices = By.className("inventory_item_price");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void addBackpackToCart() {
        driver.findElement(backpack).click();
    }

    public void openBackpack() {
        driver.findElement(By.id("item_4_title_link")).click();
    }

    public String getCartCount() {
        return driver.findElement(cartCount).getText();
    }

    public void sortProductsByPriceLowToHigh() {

        Select select = new Select(
                driver.findElement(sortDropdown)
        );

        select.selectByValue("lohi");
    }

    public String getFirstProductPrice() {
        return driver.findElements(productPrices)
                .get(0)
                .getText();
    }
}