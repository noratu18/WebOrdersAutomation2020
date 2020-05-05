package com.weborders.tests;

import com.weborders.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTests extends AbstractBaseTest {


    @Test
    public void login(){
        extentTest = extentReports.createTest("Verify page logo");

        LoginPage loginPage = new LoginPage();
        loginPage.login();
        // we imported Assert class to not use Assert.
        assertEquals(loginPage.getPageLogoText(),"Web Orders");
        //if test passes this below code will work
        extentTest.pass("Logo verified!");



    }
}
