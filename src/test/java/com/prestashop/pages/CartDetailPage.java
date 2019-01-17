package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CartDetailPage {
    // any page object has to have this constructor
    public CartDetailPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // to locate different objects using the same xpath, only change the product name
    public WebElement product(String productName){
        String  xpath = "//h5//a[@title='"+productName+"'][1]";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    @FindBy(xpath = "//span[@id='our_price_display']")
    public WebElement priceOnCart;

    @FindBy(xpath = "//input[@id='quantity_wanted']")
    public WebElement quantityBoxOnCart;

    @FindBy(xpath = "//select[@id='group_1']/option[2]")
    public WebElement randomSizeOnCart;

    @FindBy(xpath = "//button[@class='exclusive']//span")
    public WebElement addToCartonCart;

    @FindBy(xpath = "//i[@class = 'icon-ok']//parent::h2")
    public WebElement confirmationMessageOnCart;

    @FindBy(xpath = "//span[@class='cross']")
    public WebElement xIconOnCart;

    @FindBy(xpath = "//div[@id='header_logo']")
    public WebElement componyLogoOnCart;

    // after step 10 on cartdetails test-prestashop 2 is not COMPLETED
}
