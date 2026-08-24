package org.example.tests;

import org.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.pages.LoginPage;
import org.testng.annotations.DataProvider;

public class LoginTest extends BaseTest {

    @Test(groups = "smoke")
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains("inventory"));
    }


    @Test(groups = "regression")
    public void invalidLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("abc", "secret_sauce");

        String error = loginPage.getErrorMessage();

        Assert.assertTrue(
                error.contains("Username and password do not match")
        );
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][] {
                {"abc", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTestWithMultipleUsers(String username, String password) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);

        takeScreenshot("login-" + username);




//        String errorMessage = loginPage.getErrorMessage();



//        Assert.assertTrue(
//                errorMessage.contains("Username and password do not match")
//        );
    }
}