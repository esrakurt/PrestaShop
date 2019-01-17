package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalInformationPage {

        // any page object has to have this constructor
        public PersonalInformationPage (){
            PageFactory.initElements(Driver.getDriver(), this);
        }

        @FindBy(id = "id_gender2")
        public WebElement socialTitle;

        @FindBy(id = "firstname")
        public WebElement firstNamePersonalInfo;

        @FindBy(id = "lastname")
        public WebElement lastNamePersonalInfo;

        @FindBy(id = "email")
        public WebElement emailPersonalInfo;

        @FindBy(xpath = "//select[@id='days']//option[11]")
        public WebElement dayPersonalInfo;

        @FindBy(xpath = "//select[@id='months']//option[11]")
        public WebElement monthPersonalInfo;

        @FindBy(xpath = "//select[@id='years']//option[11]")
        public WebElement yearPersonalInfo;

        @FindBy(linkText = "Back to your account")
        public WebElement backToYourAccountButton;


}
