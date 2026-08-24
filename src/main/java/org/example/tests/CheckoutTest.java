package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.CartPage;
import org.example.pages.CheckoutPage;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CheckoutTest extends BaseTest {

    @Test(groups = "smoke")
    public void completeCheckoutTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        homePage.addBackpackToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName("Sonal");
        checkoutPage.enterLastName("Tester");
        checkoutPage.enterPostalCode("380001");

        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        String message = checkoutPage.getConfirmationMessage();

        Assert.assertEquals(message, "Thank you for your order!");

        takeScreenshot("checkout" );
    }
}
