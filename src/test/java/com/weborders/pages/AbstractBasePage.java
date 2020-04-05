package com.weborders.pages;

//abstract class meant to be extended

import com.weborders.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractBasePage {

    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, 20);

    //constructor-->to crete object
    public AbstractBasePage(){
        //this one to findBy annotations, to initialize elements
        PageFactory.initElements(driver,this);


    }
}
