package com.weborders.pages;

//abstract class meant to be extended

import com.weborders.utilities.BrowserUtilities;
import com.weborders.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractBasePage {

    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, 20);

    @FindBy(tagName = "h1") // common element for all pages
    protected WebElement pageLogo;

    @FindBy(tagName = "h2")
    protected WebElement pageSubtitle;


    public String getPageSubtitleTest(){
        BrowserUtilities.waitForPageToLoad(10);
        return pageSubtitle.getText().trim();

    }


    public String getPageLogoText(){
        return pageLogo.getText();
    }


    //constructor-->to crete object
    public AbstractBasePage(){
        //this one to findBy annotations, to initialize elements
        PageFactory.initElements(driver,this);
    }

    /**
     * Specify component name as a parameter, like; View all products or Orders
     * @param component
     */
    public void navigateTo(String component){
        String locator = "//a[text()='"+component+"']"; // this locator is same for all 3 elements
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).click();
    }

}
