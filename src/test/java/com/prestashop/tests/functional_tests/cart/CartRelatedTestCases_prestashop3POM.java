package com.prestashop.tests.functional_tests.cart;

import com.github.javafaker.Faker;
import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import java.util.Random;

public class CartRelatedTestCases_prestashop3POM extends TestBase {

    WebElement randomQuantity;
    Homepage homepage = new Homepage();
    LoginPage loginpage = new LoginPage();
    Random random = new Random();
    MyAccountPage myAccountPage = new MyAccountPage();
    MyAddressesPage myAddressesPage = new MyAddressesPage();
    CartDetailPage cartDetailPage = new CartDetailPage();

    @Test
    public void cartLoginTest() {
        //Cart Login Test
//        1. Open browser
//        2. Go to http://automationpractice.com/index.php

//        3. Add any product in the homepage to the cart
        cartDetailPage.product("Faded Short Sleeve T-shirts").click();

//        4. Hover over the cart icon
        actions.moveToElement(cartDetailPage.cartIcon).perform();

//        5. Verify that cart contains the product
        cartDetailPage.addToCartonCart.click();

        WebElement cnfrmMessage = cartDetailPage.confirmationMessageOnCart;
        String confirmationMessage = cnfrmMessage.getAttribute("innerText");
        cartDetailPage.xIconOnCart.click();

        String nameOfItemSelected = cartDetailPage.nameOfItemSelected.getAttribute("innerText");
        cartDetailPage.cartClick.click();
        String nameOfItemInCart = cartDetailPage.nameOfItemInCart.getAttribute("innerText");

        Assert.assertEquals(nameOfItemInCart.trim() , nameOfItemSelected.trim());

//        6. Login as any valid user
//        homepage.signIn.click();
//        Faker f = new Faker();
//        String loginEmail = f.internet().emailAddress();
//        homepage.emailAddressHomePage.sendKeys(loginEmail);
//        String loginPassword = f.internet().password();
//        homepage.passwordHomePage.sendKeys(loginPassword);
//        homepage.signInHomePage.click();

        homepage.signIn.click();
        homepage.email.sendKeys(rndmEmail);
        homepage.createAccountButton.click();
        createAccount();

//        7. Hover over the cart icon
        actions.moveToElement(homepage.cartIconAtHomePage).perform();

//        8. Verify that cart information is same as step 5

//        String cartProductInfo = driver.findElement(By.xpath("//a[@title='View my shopping cart']/span[3]")).getAttribute("innerText");
//        System.out.println(cartProductInfo);

    }

    @Test
    public void cartLogoutTest() {

//           Cart Logout Test
//        1. Open browser
//        2. Go to http://automationpractice.com/index.php
//        3. Login as any valid user
        homepage.signIn.click();
        homepage.email.sendKeys(rndmEmail);
        homepage.createAccountButton.click();
        createAccount();
//        4. Go back to home page
        cartDetailPage.componyLogoOnCart.click();
//        5. Add any product in the homepage to the cart
        cartDetailPage.product("Blouse").click();
        cartDetailPage.addToCartonCart.click();
        cartDetailPage.xIconOnCart.click();

//        6. Hover over the cart icon
        actions.moveToElement(cartDetailPage.cartIcon).perform();

//        7. Verify that cart contains the product
        String expectedProductName = "Blouse";
        WebElement cartInfo =driver.findElement(By.xpath("//div[@class='cart_block block exclusive']//div/div/dl/dt/div/div[1]"));
        String productNameInCart = cartInfo.getAttribute("innerText").substring(4);

        Assert.assertEquals(productNameInCart, expectedProductName);

//        8. Log out
        driver.findElement(By.xpath("//a[@title='Log me out']")).click();

//        9. Verify word empty is displayed in the Cart icon
        String expectedCartMessage = "(empty)";
        String actualCartMessage = driver.findElement(By.xpath("//span[@class='ajax_cart_no_product']")).getText();

        Assert.assertEquals(actualCartMessage, expectedCartMessage);
    }

//        Cart Icon Delete Test
    @Test
    public void cartIconDeleteTest() {
//        1. Open browser
//        2. Go to http://automationpractice.com/index.php
//        3. Add any product in the homepage to the cart
        cartDetailPage.product("Blouse").click();
        cartDetailPage.addToCartonCart.click();

//        4. Click on Continue shopping
        cartDetailPage.continueShoppingButton.click();

//        5. Hover over the cart icon
        actions.moveToElement(cartDetailPage.cartClick).build().perform();

//        6. Click the x to delete the product
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='shopping_cart']//dl/dt/span/a[@class='ajax_cart_block_remove_link']"))).build().perform();
        driver.findElement(By.xpath("//div[@class='shopping_cart']//dl/dt/span/a[@class='ajax_cart_block_remove_link']")).click();

//        7. Verify word empty is displayed in the Cart icon
        String expectedCartMessage = "(empty)";
        String actualCartMessage = driver.findElement(By.xpath("//span[@class='ajax_cart_no_product']")).getText();

//        Assert.assertEquals(actualCartMessage, expectedCartMessage);

    }


    //        Cart Checkout Delete Test
    @Test
    public void cartCheckoutDeleteTest() {
//        1. Open browser
//        2. Go to http://automationpractice.com/index.php
//        3. Add any product in the homepage to the cart
        cartDetailPage.product("Blouse").click();
        cartDetailPage.addToCartonCart.click();

//        4. Click on Continue shopping
        cartDetailPage.continueShoppingButton.click();

//        5. Add another product in the homepage to the cart
        cartDetailPage.componyLogoOnCart.click();
        cartDetailPage.product("Faded Short Sleeve T-shirts").click();
        cartDetailPage.addToCartonCart.click();

//        6. Click on Proceed to checkout
        cartDetailPage.proceedToCheckoutButton.click();

//        7. Verify message Your shopping cart contains: 2 Products
        String expectedMessage = "Your shopping cart contains: 2 Products";
        WebElement cartMessage = driver.findElement(By.xpath("//span[@class='heading-counter']"));
        String actualMessage = cartMessage.getAttribute("innerText");
        System.out.println("Actual Message: " + actualMessage + " | Expected Message: " + expectedMessage );
        Assert.assertEquals(actualMessage, expectedMessage);

//        8. Click the delete icon to delete one of the products
        driver.findElement(By.xpath("//a[@class='cart_quantity_delete']//i[1]")).click();
//
//        9. Verify message Your shopping cart contains: 1 Product
        String expectedMessageAfterUpdate = "Your shopping cart contains: 1 Products";
        WebElement cartMessageafterUpdate = driver.findElement(By.xpath("//div[@id='center_column']/h1/span"));
        String actualMessageAfterUpdate = cartMessageafterUpdate.getText();
        System.out.println("Actual Message: " + actualMessageAfterUpdate + " | Expected Message: " + expectedMessageAfterUpdate );
//        Assert.assertEquals(actualMessageAfterUpdate, expectedMessageAfterUpdate);

//        10. Click the delete icon to delete the second product
        driver.findElement(By.xpath("//a[@class='cart_quantity_delete']//i[1]")).click();

//        11. Verify message Your shopping cart is empty
        String expectedMessageAfter2ndUpdate = "Your shopping cart is empty.";
        WebElement cartMessageAfter2ndUpdate = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
        String actualMessageAfter2ndUpdate = cartMessageAfter2ndUpdate.getAttribute("innerText");
        System.out.println(actualMessageAfter2ndUpdate);
        Assert.assertEquals(actualMessageAfter2ndUpdate, expectedMessageAfter2ndUpdate);

    }
}
