package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.example.pages.HomePage;
import org.example.pages.ProductPage;
import org.example.pages.CartPage;
import org.testng.Assert;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test(groups = "smoke")
    public void verifyProductDetails() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);

        homePage.openBackpack();

        ProductPage productPage = new ProductPage(driver);

        String actualProductName = productPage.getProductName();

        Assert.assertEquals(
                actualProductName,
                "Sauce Labs Backpack"
        );
    }

    @Test(groups = "regression")
    public void verifyAddProductToCart() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        homePage.addBackpackToCart();

        String count = homePage.getCartCount();
        Assert.assertEquals(count, "1");

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        String actualProduct = cartPage.getProductName();

        Assert.assertEquals(actualProduct, "Sauce Labs Backpack");
    }


    @Test(groups = "regression")
    public void verifyRemoveProductFromCart() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        homePage.addBackpackToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        cartPage.removeProduct();

        Assert.assertFalse(cartPage.isProductDisplayed());
    }


    @Test
    public void verifyPriceSorting() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);

        homePage.sortProductsByPriceLowToHigh();

        String firstPrice = homePage.getFirstProductPrice();

        Assert.assertEquals(firstPrice, "$7.99");
    }
}
