package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class CartDetailsTest_PrestaShop2POM extends TestBase {


//    Cart Details
    @Test
    public void cartDetails() throws InterruptedException {
        extentLogger = report.createTest("Cart Details Test");
        extentLogger.info("Clicking to the selected product");
//      1. Open browser
//      2. Go to http://automationpractice.com/index.php
        // ABOVE TWO STEPS ARE ALREADY DONE BY THE TESTBASE CLASS

//      3. Click on any product that is not on sale
        extentLogger.info("Click on any product that is not on sale");
        WebElement product = pages.cartDetailPage().product("Blouse");
        product.click();

//      4. Enter a random quantity between 2 and 5
        extentLogger.info("Enter a random quantity between 2 and 5");
        Random r = new Random();
        int quantity = r.nextInt(4)+1;
        String qtty = (" "+quantity+" ").trim();
        pages.cartDetailPage().quantityBoxOnCart.clear();
        pages.cartDetailPage().quantityBoxOnCart.sendKeys(qtty);

//      5. Select a different size
        extentLogger.info("Select a different size");
        Random rndmSize = new Random();
        int randomSize = rndmSize.nextInt(2)+1;
        pages.cartDetailPage().randomSizeOnCart(randomSize);

//      6. Click on add to cart
        extentLogger.info("Click on add to cart");
        pages.cartDetailPage().addToCartonCart.click();

//      7. Verify confirmation message Product successfully added to your shopping cart
        extentLogger.info("Verify confirmation message Product successfully added to your shopping cart");
        String expectedConfirmationMessage = "Product successfully added to your shopping cart";
        WebElement actConfirmationMessage = driver.findElement(
                By.xpath("//i[@class = 'icon-ok']//parent::h2"));
        String actualConfirmationMessage =actConfirmationMessage.getAttribute("innerText");
        System.out.println("Expected confirmation message: " + expectedConfirmationMessage + " | Actual confirmation message: " + actualConfirmationMessage);

        Assert.assertEquals(expectedConfirmationMessage.trim(), actualConfirmationMessage.trim());

//      8. Dismiss the confirmation window by clicking on the x icon
        extentLogger.info("Dismiss the confirmation window by clicking on the x icon");
        pages.cartDetailPage().xIconOnCart.click();

//      9. Click on the company logo
        extentLogger.info("Click on the company logo");
        pages.cartDetailPage().componyLogoOnCart.click();

//      10. Click on any product that is on sale
        extentLogger.info("Click on any product that is on sale");
        WebElement productOnSale = pages.cartDetailPage().product("Printed Chiffon Dress");
        productOnSale.click();

//      11. Enter a random quantity between 2 and 5
        extentLogger.info("Enter a random quantity between 2 and 5");
        quantity = r.nextInt(4)+1;
        qtty = (" "+quantity+" ").trim();
        pages.cartDetailPage().quantityBoxOnCart.clear();
        pages.cartDetailPage().quantityBoxOnCart.sendKeys(qtty);

//      12. Select a different size
        extentLogger.info("Select a different size");
        pages.cartDetailPage().randomSizeOnCart(randomSize);

//      13. Click on add to cart
        extentLogger.info("Select a different size");
        pages.cartDetailPage().addToCartonCart.click();

//      14. Verify confirmation message Product successfully added to your shopping cart
        extentLogger.info("Verify confirmation message Product successfully added to your shopping cart");
        String expectedMessage = "Product successfully added to your shopping cart";
        WebElement actMessage = driver.findElement(
                By.xpath("//i[@class = 'icon-ok']//parent::h2"));
        String actualMessage = actMessage.getAttribute("innerText").trim();
        System.out.println("Expected message: " + expectedMessage + " | Actual message: " + actualMessage);
        Assert.assertEquals(actualMessage.trim(), expectedMessage.trim());

//      15. Dismiss the confirmation window by clicking on the x icon
        extentLogger.info("Dismiss the confirmation window by clicking on the x icon");
        pages.cartDetailPage().xIconOnCart.click();

//      16. Hover over the Cart icon
        extentLogger.info("Hover over the Cart icon");
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='View my shopping cart']"))).perform();

//      17. Verify that correct total is displayed
        extentLogger.info("Verify that correct total is displayed");
        actions.moveToElement(pages.cartDetailPage().cartIcon).perform();
        actions.moveToElement(driver.findElement(By.xpath("//div//span[@class='price cart_block_total ajax_block_cart_total']"))).perform();
        String expectedTotal = driver.findElement(By.xpath("//div//span[@class='price cart_block_total ajax_block_cart_total']")).getText();
        actions.moveToElement(driver.findElement(
                By.xpath("//div[@class='shopping_cart']//div/div//div//div[@class='cart-prices']//div[2]//span[@class='price cart_block_total ajax_block_cart_total']"))).build().perform();
        driver.findElement(By.xpath("//div[@class='shopping_cart']//div/div//div//div[@class='cart-prices']//div[2]//span[@class='price cart_block_total ajax_block_cart_total']")).click();
        WebElement actTotal = driver.findElement(
                By.xpath("//div[@class='shopping_cart']//div/div//div//div[@class='cart-prices']//div[2]//span[@class='price cart_block_total ajax_block_cart_total']"));
        String actualTotal = actTotal.getText();
        Assert.assertEquals(actualTotal, expectedTotal);

//      18. Verify that total is correct based on the price and item count of the products you added to cart. (Shipping is always $2)
        extentLogger.info("Verify that total is correct based on the price and item count of the products you added to cart. (Shipping is always $2)");
        pages.cartDetailPage().cartClick.click();

        WebElement blouseP = driver.findElement(By.xpath("//tr[@id='product_2_7_0_0']//td[6]//span"));
        String blousePrice = blouseP.getText().substring(1);

        WebElement printedP = driver.findElement(By.xpath("//tr[@id='product_7_34_0_0']//td[6]//span"));
        String printedPrice = printedP.getText().substring(1);

        WebElement totalP= driver.findElement(By.xpath("//table[@id='cart_summary']//tfoot//tr[7]//td[2]//span"));
        String totalPrice = totalP.getText().substring(1);

        double expectedTotalPrice = Double.valueOf(blousePrice) + Double.valueOf(printedPrice) + 2.00;

        Assert.assertEquals(expectedTotalPrice, Double.valueOf(totalPrice));

        extentLogger.pass("Completed: Cart Details Test");

    }



}

