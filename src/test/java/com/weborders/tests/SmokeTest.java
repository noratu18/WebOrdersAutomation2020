package com.weborders.tests;

import com.weborders.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class SmokeTest extends AbstractBaseTest {

    @Test(dataProvider = "smokeTestData")
    public void smokeTest(String component, String expectedPageSubtitle) {
        extentTest = extentReports.createTest("Verify" + component);

        LoginPage loginPage = new LoginPage();
        loginPage.login();
        loginPage.navigateTo("View all products");
        assertEquals(loginPage.getPageSubtitleTest(), "List of Products");

        extentTest.pass(component + " verified!");


    }



    @DataProvider
    public Object[][] smokeTestData(){
        return new Object[][]{
                {"View all orders", "List of All Orders"},
                {"View all products", "List of Products"},
                {"Order", "Order"}
        };
    }
}
