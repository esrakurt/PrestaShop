package com.prestashop.tests.functional_tests.cart;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class CartDetailsTest_PrestaShop2 extends TestBase {

    WebElement randomQuantity;

//    Cart Details
    @Test
    public void cartDetails() throws InterruptedException {

//      1. Open browser
//      2. Go to http://automationpractice.com/index.php
        // ABOVE TWO STEPS ARE ALREADY DONE BY THE TESTBASE CLASS

//      3. Click on any product that is not on sale
        WebElement product = driver.findElement(By.linkText("Blouse"));
        product.click();

//      4. Enter a random quantity between 2 and 5

        randomQuantity = driver.findElement(By.xpath("//a[@class='btn btn-default button-plus product_quantity_up']/span"));
        Random random = new Random();
        int quantity = random.nextInt(2) + 1;
        randomQuantity();

//      5. Select a different size
        Random rndmSize = new Random();
        int randomSize = random.nextInt(2) + 1;
        driver.findElement(By.xpath("//select[@id='group_1']/option[@value='"+randomSize+"']")).click();

//      6. Click on add to cart
        driver.findElement(By.xpath("//button[@class='exclusive']//span")).click();

//      7. Verify confirmation message Product successfully added to your shopping cart
        String expectedConfirmationMessage = "Product successfully added to your shopping cart";
        WebElement actConfirmationMessage = driver.findElement(
                By.xpath("//i[@class = 'icon-ok']//parent::h2"));
        String actualConfirmationMessage = actConfirmationMessage.getAttribute("innerText");
        System.out.println("Expected confirmation message: " + expectedConfirmationMessage + " | Actual confirmation message: " + actualConfirmationMessage);
//
//        Assert.assertEquals(expectedConfirmationMessage, actualConfirmationMessage);

//      8. Dismiss the confirmation window by clicking on the x icon
        driver.findElement(By.xpath("//span[@class='cross']")).click();

//      9. Click on the company logo
        driver.findElement(By.xpath("//div[@id='header_logo']")).click();

//      10. Click on any product that is on sale
        driver.findElement(By.xpath("//a[@title='Printed Chiffon Dress']")).click();

//      11. Enter a random quantity between 2 and 5
        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='fancybox-iframe']"));
        driver.switchTo().frame(iframe);

        randomQuantity = driver.findElement(By.xpath("//a[@class='btn btn-default button-plus product_quantity_up']"));
        Thread.sleep(2000);
        randomQuantity();
        Thread.sleep(2000);

//      12. Select a different size
//        driver.findElement(By.xpath("//select[@id='group_1']/option[@value='2']")).click();
        Random rndmSizeForSaleItem = new Random();
        int randomSizeForSaleItem = random.nextInt(2) + 1;
        driver.findElement(By.xpath("//select[@id='group_1']/option[@value='"+randomSizeForSaleItem+"']")).click();

//      13. Click on add to cart
        driver.findElement(By.xpath("//button[@class='exclusive']")).click();

//      14. Verify confirmation message Product successfully added to your shopping cart
        String expectedMessage = "Product successfully added to your shopping cart";
        WebElement actMessage = driver.findElement(
                By.xpath("//i[@class = 'icon-ok']//parent::h2"));
        String actualMessage = actMessage.getAttribute("innerText");
        System.out.println("Expected message: " + expectedMessage + " | Actual message: " + actualMessage);
        Assert.assertEquals(expectedMessage, actualMessage);

//      15. Dismiss the confirmation window by clicking on the x icon
        driver.findElement(By.xpath("//span[@title='Close window']")).click();

//      16. Hover over the Cart icon
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='View my shopping cart']"))).perform();

//      17. Verify that correct total is displayed

//      18. Verify that total is correct based on the price and item count of the products you added to cart. (Shipping is always $2)
    }

        public void randomQuantity(){
            Random random = new Random();
            int quantity = random.nextInt(3);

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


