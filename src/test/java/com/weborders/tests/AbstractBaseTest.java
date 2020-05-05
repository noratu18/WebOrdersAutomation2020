package com.weborders.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.weborders.utilities.BrowserUtilities;
import com.weborders.utilities.ConfigurationReader;
import com.weborders.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import static org.testng.Assert.*;


public abstract class AbstractBaseTest {

        protected WebDriver driver = Driver.getDriver();

        protected static ExtentReports extentReports;
        protected static ExtentHtmlReporter extentHtmlReporter;
        protected static ExtentTest extentTest;



        @BeforeTest
        public void beforeTest(){
            extentReports = new ExtentReports();
            String reportPath = "";

            //we need this statement this because of operational mac.os/windows.win
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                reportPath = System.getProperty("user.dir") + "\\test-output\\report.html";
            } else {
                reportPath = System.getProperty("user.dir") + "/test-output/report.html" ;
            }
            extentHtmlReporter = new ExtentHtmlReporter(reportPath);
            extentReports.attachReporter(extentHtmlReporter);
            extentHtmlReporter.config().setReportName("WebOrders Automation");


        }


        @AfterTest
        public void afterTest(){
            extentReports.flush();
        }


        @BeforeMethod
        @Parameters("browser")
        public void setup(@Optional String browser){
            System.out.println("Browser type: " + browser);
            driver = browser == null ? Driver.getDriver(): Driver.getDriver(browser);
            driver.get(ConfigurationReader.getProperty("url"));;
            driver.manage().window().maximize();

        }

        //iTestResult -->  is to determine the test result, if test fails we take screenshot
        @AfterMethod
        public void tearDown(ITestResult testResult){
            if(testResult.getStatus() == ITestResult.FAILURE){
                String screenshotLocation = BrowserUtilities.getScreenShot(testResult.getName());
                try {
                    extentTest.fail(testResult.getName());//test name that failed
                    extentTest.addScreenCaptureFromPath(screenshotLocation);//screenshot as an evidence
                    extentTest.fail(testResult.getThrowable());//error message
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Failed to attach screenshot");
                }
            }else if (testResult.getStatus() == ITestResult.SUCCESS) {
                extentTest.pass(testResult.getName()); // if test is success then get the name
            }else if (testResult.getStatus() == ITestResult.SKIP){
                extentTest.skip(testResult.getName()); // if test skips then get the name


            }
            BrowserUtilities.wait(3);
            Driver.closeDriver();

        }



}
