package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    // any page object has to have this constructor
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@id='email_create']")
    public WebElement email;

//    @FindBy(xpath = "//div[@class='account_creation']/div[1]/div[1]/label/div[1]")
//    public WebElement title;

    @FindBy(id = "id_gender1")
    public WebElement mr;

    @FindBy(id = "id_gender2")
    public WebElement mrs;

    @FindBy(id = "customer_firstname")
    public WebElement firstName;

    @FindBy(id = "customer_lastname")
    public WebElement lastName;

    @FindBy(xpath = "//form//div[1]//div[4]//input[@id='email']")
    public WebElement readyEmail;

    @FindBy(id = "passwd")
    public WebElement password;

    @FindBy(id = "days")
    public WebElement day;

    @FindBy(id = "months")
    public WebElement month;

    @FindBy(id = "years")
    public WebElement year;

    @FindBy(id = "firstname")
    public WebElement firstNameOnAddressSection;

    @FindBy(id = "lastname")
    public WebElement lastNameOnAddressSection;

    @FindBy(id = "address1")
    public WebElement address;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "id_state")
    public WebElement state;

    @FindBy(id = "postcode")
    public WebElement zip;

    @FindBy(id = "id_country")
    public WebElement country;

    @FindBy(id = "phone_mobile")
    public WebElement mobilePhone;

    @FindBy(id = "alias")
    public WebElement alias;

    @FindBy(xpath = "//button[@id='submitAccount']//span")
    public WebElement registerButton;

}
