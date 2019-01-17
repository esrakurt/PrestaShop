package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class CartDetailsTest_PrestaShop2POM extends TestBase {

    WebElement randomQuantity;
    Homepage homepage = new Homepage();
    LoginPage loginpage = new LoginPage();
    Random random = new Random();
    MyAccountPage myAccountPage = new MyAccountPage();
    MyAddressesPage myAddressesPage = new MyAddressesPage();
    CartDetailPage cartDetailPage = new CartDetailPage();

//    Cart Details
    @Test
    public void cartDetails() throws InterruptedException {

//      1. Open browser
//      2. Go to http://automationpractice.com/index.php
        // ABOVE TWO STEPS ARE ALREADY DONE BY THE TESTBASE CLASS

//      3. Click on any product that is not on sale
        WebElement product = cartDetailPage.product("Blouse");
        product.click();

//      4. Enter a random quantity between 2 and 5

        randomQuantity = cartDetailPage.quantityBoxOnCart;
        Random random = new Random();
        int quantity = random.nextInt(3)+2;
        randomQuantity();

//      5. Select a different size
        Random rndmSize = new Random();
        int randomSize = random.nextInt(2)+1;

        cartDetailPage.randomSizeOnCart.click();

//      6. Click on add to cart
        cartDetailPage.addToCartonCart.click();

//      7. Verify confirmation message Product successfully added to your shopping cart
        String expectedConfirmationMessage = "Product successfully added to your shopping cart";
        WebElement actConfirmationMessage = driver.findElement(
                By.xpath("//i[@class = 'icon-ok']//parent::h2"));
        String actualConfirmationMessage =actConfirmationMessage.getAttribute("innerText");
        System.out.println("Expected confirmation message: " + expectedConfirmationMessage + " | Actual confirmation message: " + actualConfirmationMessage);

//        Assert.assertEquals(expectedConfirmationMessage, actualConfirmationMessage);

//      8. Dismiss the confirmation window by clicking on the x icon
        cartDetailPage.xIconOnCart.click();

//      9. Click on the company logo
        cartDetailPage.componyLogoOnCart.click();

//      10. Click on any product that is on sale
        WebElement productOnSale = cartDetailPage.product("Printed Chiffon Dress");
        productOnSale.click();

//      11. Enter a random quantity between 2 and 5
        randomQuantity = cartDetailPage.quantityBoxOnCart;
        randomQuantity();
//        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='fancybox-iframe']"));
//        driver.switchTo().frame(iframe);
//
//        randomQuantity = driver.findElement(By.xpath("//a[@class='btn btn-default button-plus product_quantity_up']"));
//        Thread.sleep(2000);
//        randomQuantity();
//        Thread.sleep(2000);

//      12. Select a different size
        driver.findElement(By.xpath("//select[@id='group_1']/option[2]")).click();

//      13. Click on add to cart
        driver.findElement(By.xpath("//button[@class='exclusive']")).click();

//      14. Verify confirmation message Product successfully added to your shopping cart
        String expectedMessage = "Product successfully added to your shopping cart";
        WebElement actMessage = driver.findElement(
                By.xpath("//i[@class = 'icon-ok']//parent::h2"));
        String actualMessage = actMessage.getAttribute("innerText").trim();
        System.out.println("Expected message: " + expectedMessage + " | Actual message: " + actualMessage);
//        Assert.assertEquals(actualMessage, expectedMessage);

//      15. Dismiss the confirmation window by clicking on the x icon
        driver.findElement(By.xpath("//span[@title='Close window']")).click();

//      16. Hover over the Cart icon
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='View my shopping cart']"))).perform();

//      17. Verify that correct total is displayed

//      18. Verify that total is correct based on the price and item count of the products you added to cart. (Shipping is always $2)

    }

    public void randomQuantity(){
        Random random = new Random();
        int quantity = random.nextInt(3)+2;

        switch (quantity){

            case 0:
                randomQuantity.click();
                break;
            case 1:
                randomQuantity.click();
                randomQuantity.click();
                break;
            case 2:
                randomQuantity.click();
                randomQuantity.click();
                randomQuantity.click();
                break;
            case 3:
                randomQuantity.click();
                randomQuantity.click();
                randomQuantity.click();
                randomQuantity.click();
                break;
        }
    }
}

