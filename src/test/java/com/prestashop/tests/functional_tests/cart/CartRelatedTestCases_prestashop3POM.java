package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class CartRelatedTestCases_prestashop3POM extends TestBase {

    @Test
    public void cartLoginTest() {

        extentLogger = report.createTest("Cart Login Test");
        //Cart Login Test
//        1. Open browser
//        2. Go to http://automationpractice.com/index.php

//        3. Add any product in the homepage to the cart

        extentLogger.info("Clicking to the selected product");
        pages.cartDetailPage().product("Faded Short Sleeve T-shirts").click();

//        4. Hover over the cart icon
        extentLogger.info("Hovering over the cart icon");
        actions.moveToElement(pages.cartDetailPage().cartIcon).perform();

//        5. Verify that cart contains the product
        extentLogger.info("Clicking to the cart icon");
        pages.cartDetailPage().addToCartonCart.click();

        extentLogger.info("Checking confirmation message");
        WebElement cnfrmMessage = pages.cartDetailPage().confirmationMessageOnCart;
        String confirmationMessage = cnfrmMessage.getAttribute("innerText");
        extentLogger.info("Clicking to the x icon");
        pages.cartDetailPage().xIconOnCart.click();

        extentLogger.info("Verifying if selected product is in cart");
        String nameOfItemSelected = pages.cartDetailPage().nameOfItemSelected.getAttribute("innerText");
        pages.cartDetailPage().cartClick.click();
        String nameOfItemInCart = pages.cartDetailPage().nameOfItemInCart.getAttribute("innerText");
        System.out.println(nameOfItemInCart);

        Assert.assertEquals(nameOfItemInCart.trim() , nameOfItemSelected.trim());

//        6. Login as any valid user
        extentLogger.info("Clicking to the sign in icon");
        pages.homePage().signIn.click();
        extentLogger.info("Entering email");
        pages.homePage().email.sendKeys(rndmEmail);
        extentLogger.info("Creating account");
        pages.homePage().createAccountButton.click();
        createAccount();

//        7. Hover over the cart icon
        extentLogger.info("Hovering the cart icon");
        actions.moveToElement(pages.homePage().cartIconAtHomePage).perform();

//        8. Verify that cart information is same as step 5
        extentLogger.info("Veriying the name of the product is macthing");
        String cartProductInfo = driver.findElement(By.xpath("(//a[@title='Faded Short Sleeve T-shirts'])[2]")).getAttribute("innerText");
        System.out.println(cartProductInfo);
        Assert.assertTrue(nameOfItemInCart.contains("Faded"));

        extentLogger.pass("Completed: Cart Login Test");

    }

    @Test
    public void cartLogoutTest() {
        extentLogger = report.createTest("Cart Logout Test");
//           Cart Logout Test
//        1. Open browser
//        2. Go to http://automationpractice.com/index.php
//        3. Login as any valid user

        extentLogger.info("Login as any valid user");
        pages.homePage().signIn.click();
        pages.homePage().email.sendKeys(rndmEmail);
        pages.homePage().createAccountButton.click();
        createAccount();

//        4. Go back to home page
        extentLogger.info("Go back to home page");
        pages.cartDetailPage().componyLogoOnCart.click();

//        5. Add any product in the homepage to the cart
        extentLogger.info("Add any product in the homepage to the cart");
        pages.cartDetailPage().product("Blouse").click();
        pages.cartDetailPage().addToCartonCart.click();
        pages.cartDetailPage().xIconOnCart.click();

//        6. Hover over the cart icon
        extentLogger.info("Hover over the cart icon");
        actions.moveToElement(pages.cartDetailPage().cartIcon).perform();

//        7. Verify that cart contains the product
        extentLogger.info("Verify that cart contains the product");
        String expectedProductName = "Blouse";
        WebElement cartInfo =driver.findElement(By.xpath("//div[@class='cart_block block exclusive']//div/div/dl/dt/div/div[1]"));
        String productNameInCart = cartInfo.getAttribute("innerText").substring(4);

        Assert.assertEquals(productNameInCart, expectedProductName);

//        8. Log out
        extentLogger.info("Log out");
        driver.findElement(By.xpath("//a[@title='Log me out']")).click();

//        9. Verify word empty is displayed in the Cart icon
        extentLogger.info("Verify word empty is displayed in the Cart icon");
        String expectedCartMessage = "(empty)";
        String actualCartMessage = driver.findElement(By.xpath("//span[@class='ajax_cart_no_product']")).getText();

        Assert.assertEquals(actualCartMessage, expectedCartMessage);

        extentLogger.pass("Completed: Cart Logout Test");
    }

//        Cart Icon Delete Test
    @Test
    public void cartIconDeleteTest() {
        extentLogger = report.createTest("Cart Icon Delete Test");
        extentLogger.info("Open browser and go to link");
//        1. Open browser
//        2. Go to http://automationpractice.com/index.php
//        3. Add any product in the homepage to the cart
        extentLogger.info("Add any product in the homepage to the cart");
        pages.cartDetailPage().product("Blouse").click();
        pages.cartDetailPage().addToCartonCart.click();

//        4. Click on Continue shopping
        extentLogger.info("Click on Continue shopping");
        pages.cartDetailPage().continueShoppingButton.click();

//        5. Hover over the cart icon
        extentLogger.info("Hover over the cart icon");
        actions.moveToElement(pages.cartDetailPage().cartClick).build().perform();

//        6. Click the x to delete the product
        extentLogger.info("Click the x to delete the product");
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='shopping_cart']//dl/dt/span/a[@class='ajax_cart_block_remove_link']"))).build().perform();
        driver.findElement(By.xpath("//div[@class='shopping_cart']//dl/dt/span/a[@class='ajax_cart_block_remove_link']")).click();

//        7. Verify word empty is displayed in the Cart icon
        extentLogger.info("Verify word empty is displayed in the Cart icon");
        String expectedCartMessage = "(empty)";
        String actualCartMessage = driver.findElement(By.xpath("//span[@class='ajax_cart_no_product']")).getAttribute("innerText");

        Assert.assertEquals(actualCartMessage, expectedCartMessage);

        extentLogger.pass("Completed: Cart Icon Delete Test");
    }

    //        Cart Checkout Delete Test
    @Test
    public void cartCheckoutDeleteTest() {
        extentLogger = report.createTest("Cart Checkout Delete Test");
        extentLogger.info("Open browser and go to link");
//        1. Open browser
//        2. Go to http://automationpractice.com/index.php
//        3. Add any product in the homepage to the cart
        extentLogger.info("Add any product in the homepage to the cart");
        pages.cartDetailPage().product("Blouse").click();
        pages.cartDetailPage().addToCartonCart.click();

//        4. Click on Continue shopping
        extentLogger.info("Click on Continue shopping");
        pages.cartDetailPage().continueShoppingButton.click();

//        5. Add another product in the homepage to the cart
        extentLogger.info("Add another product in the homepage to the cart");
        pages.cartDetailPage().componyLogoOnCart.click();
        pages.cartDetailPage().product("Faded Short Sleeve T-shirts").click();
        pages.cartDetailPage().addToCartonCart.click();

//        6. Click on Proceed to checkout
        extentLogger.info("Click on Proceed to checkout");
        pages.cartDetailPage().proceedToCheckoutButton.click();

//        7. Verify message Your shopping cart contains: 2 Products
        extentLogger.info("Verify message Your shopping cart contains: 2 Products");
        String expectedMessage = "Your shopping cart contains: 2 Products";
        WebElement cartMessage = driver.findElement(By.xpath("//span[@class='heading-counter']"));
        String actualMessage = cartMessage.getAttribute("innerText");
        System.out.println("Actual Message: " + actualMessage + " | Expected Message: " + expectedMessage );
        Assert.assertEquals(actualMessage, expectedMessage);

//        8. Click the delete icon to delete one of the products
        extentLogger.info("Click the delete icon to delete one of the products");
        driver.findElement(By.xpath("//a[@class='cart_quantity_delete']//i[1]")).click();
//
//        9. Verify message Your shopping cart contains: 1 Product
        extentLogger.info("Verify message Your shopping cart contains: 1 Product");
        String expectedMessageAfterUpdate = "Your shopping cart contains: 1 Products";
        WebElement cartMessageafterUpdate = driver.findElement(By.xpath("//div[@id='center_column']/h1/span"));
        String actualMessageAfterUpdate = cartMessageafterUpdate.getText();
        System.out.println("Actual Message: " + actualMessageAfterUpdate + " | Expected Message: " + expectedMessageAfterUpdate );
//        Assert.assertEquals(actualMessageAfterUpdate, expectedMessageAfterUpdate);

//        10. Click the delete icon to delete the second product
        extentLogger.info("Click the delete icon to delete the second product");
        driver.findElement(By.xpath("//a[@class='cart_quantity_delete']//i[1]")).click();

//        11. Verify message Your shopping cart is empty
        extentLogger.info("Verify message Your shopping cart is empty");
        String expectedMessageAfter2ndUpdate = "Your shopping cart is empty.";
        WebElement cartMessageAfter2ndUpdate = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
        String actualMessageAfter2ndUpdate = cartMessageAfter2ndUpdate.getAttribute("innerText");
        System.out.println(actualMessageAfter2ndUpdate);
        Assert.assertEquals(actualMessageAfter2ndUpdate, expectedMessageAfter2ndUpdate);

        extentLogger.pass("Completed: Cart Checkout Delete Test");

    }
}
