package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    // any page object has to have this constructor
    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(linkText = "Sign in")
    public WebElement signIn;

    @FindBy(xpath = "//input[@id='email_create']")
    public WebElement email;

    @FindBy(id = "SubmitCreate")
    public WebElement createAccountButton;

    @FindBy(xpath = "//div//input[@id='email']")
    public WebElement emailAddressHomePage;

    @FindBy(xpath = "//div//input[@id='passwd']")
    public WebElement passwordHomePage;

    @FindBy(id= "SubmitLogin")
    public WebElement signInHomePage;


    @FindBy(xpath = "//div[@class='shopping_cart']/a")
    public WebElement cartIconAtHomePage;


}

/*
    // to locate different objects using the same xpath, only change the product name
    public WebElement product(String productName){
        String  xpath = "//h5//a[@title='"+productName+"'][1]";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }
 */
