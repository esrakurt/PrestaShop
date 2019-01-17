package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAddressesPage {

     // any page object has to have this constructor
        public MyAddressesPage (){
            PageFactory.initElements(Driver.getDriver(), this);
        }

        @FindBy(xpath = "//div[@class='col-xs-12 col-sm-6 address']//ul//li[4]")
        public WebElement myAddressOnMyAddressesPage;

        @FindBy(xpath = "//a[@class='logout']")
        public WebElement signOutButtonOnMyAddressesPage;

}
