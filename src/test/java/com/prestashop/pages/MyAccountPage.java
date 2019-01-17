package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    // any page object has to have this constructor
    public MyAccountPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@title='View my customer account']//span")
    public WebElement fullNameOnMyAccountPage;

    @FindBy(xpath = "//a[@title='Information']//span")
    public WebElement myPersonalInformationButton;

    @FindBy(xpath = "//span[.='My addresses']")
    public WebElement myAddressesButton;

}
