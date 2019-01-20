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

    public WebElement randomSizeOnCart(int randomSize) {
        String xpath = "//select[@id='group_1']/option[@value='" + randomSize + "']";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    @FindBy(xpath = "//span[@id='our_price_display']")
    public WebElement priceOnCart;

    @FindBy(xpath = "//input[@id='quantity_wanted']")
    public WebElement quantityBoxOnCart;

//    @FindBy(xpath = "//select[@id='group_1']/option[@value='"+randomSize+"']")
//    public WebElement randomSizeOnCart;

    @FindBy(xpath = "//button[@class='exclusive']//span")
    public WebElement addToCartonCart;

    @FindBy (xpath = "//button[@name='Submit']")
    public WebElement cartIcon;

    @FindBy(xpath = "//i[@class = 'icon-ok']//parent::h2")
    public WebElement confirmationMessageOnCart;

    @FindBy(xpath = "//span[@class='cross']")
    public WebElement xIconOnCart;

    @FindBy(xpath = "//div[@id='header_logo']")
    public WebElement componyLogoOnCart;

    @FindBy(xpath = "//h1[@itemprop='name']")
    public WebElement nameOfItemSelected;

    @FindBy(xpath = "//a[@title='View my shopping cart']//b")
    public WebElement cartClick;

    @FindBy(xpath = "//tr[@id='product_1_1_0_0']//td[2]//p")
    public WebElement nameOfItemInCart;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    public WebElement continueShoppingButton;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    public WebElement proceedToCheckoutButton;

}
