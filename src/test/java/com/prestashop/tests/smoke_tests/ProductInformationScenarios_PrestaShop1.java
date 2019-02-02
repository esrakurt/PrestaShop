package com.prestashop.tests.smoke_tests;

//Product information - price:
//        1. Go to http://automationpractice.com/index.php
//        2. Click on any product
//        3. Verify that same name and price displayed as on the home page
//
//        Product information - details:
//        4. that default quantity is 1
//        5. Verify that default size is S
//        6. Verify that size options are S, M, L
//
//        Product information – Add to cart:
//        7. Click on Add to cart
//        8. Verify confirmation message “Product successfully added to your shopping
//        cart”
//        9. that default quantity is 1
//        10. Verify that default size is S
//        11. Verify that same name and price displayed as on the home page

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductInformationScenarios_PrestaShop1 {

    WebDriver driver;

    WebElement product;
    WebElement price;
    String productName;
    String productPrice;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();

//        1. Go to http://automationpractice.com/index.php
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        product= driver.findElement(By.linkText("Blouse"));
        productName = product.getText();

        price = driver.findElement(By.xpath("(//span[@class='price product-price'])[4]"));
        productPrice = price.getText();
    }

    @Test
    public void productInformationPrice(){
        //        2. Click on any product
        product.click();
        //        3. Verify that same name and price displayed as on the home page
        // no need to create webelement since there will be no interaction with the website, only name and price will be checked.
        String productPageName = driver.findElement(By.xpath("//div[@class='pb-center-column col-xs-12 col-sm-4']//h1")).getText();
        String productPagePrice = driver.findElement(By.xpath("//span[@id='our_price_display']")).getText();

        System.out.println("HomePage product name: " + productName +" | Product page product name: " + productPageName);
        System.out.println("HomePage product price: " + productPrice +" | Product page product price: " + productPrice);

        Assert.assertEquals(productName, productPageName);
        Assert.assertEquals(productPrice, productPagePrice);
    }
    // Product information - details:
//

//        6. Verify that size options are S, M, L

    @Test
    public void productInformationDetails(){
        // 4. verify that default quantity is 1

        product.click();
        WebElement quantity = driver.findElement(By.id("quantity_wanted"));
        String expectedQuantity = "1";
        String actualQuantity = quantity.getAttribute("value");

        System.out.println("Expected Quantity: " + expectedQuantity + " | Actual Quantity: " + actualQuantity);
        Assert.assertEquals(expectedQuantity, actualQuantity);


        //        5. Verify that default size is S

        WebElement size = driver.findElement(By.id("group_1"));
        // using select class since there is a dropdown menu
        Select select = new Select(size);
        String expectedSize = "S";
        String actualSize = select.getFirstSelectedOption().getText();

        System.out.println("Expected Size: " + expectedSize + " | Actual Size: " + actualSize);
        Assert.assertEquals(expectedSize, actualSize);


        //        6. Verify that size options are S, M, L

        // create list to store size options, then use select object

        List<String> expectedSizeOptions = new ArrayList<String>();
        expectedSizeOptions.add("S");
        expectedSizeOptions.add("M");
        expectedSizeOptions.add("L");

        List<WebElement> actualSizeOptions = select.getOptions();

        for (int i = 0; i < actualSizeOptions.size(); i++) {
            System.out.println("Expected size options: " + expectedSizeOptions.get(i) + " Actual size option: " + actualSizeOptions.get(i).getText());
            Assert.assertEquals(expectedSizeOptions.get(i) , actualSizeOptions.get(i).getText());

        }
    }


    @Test
    public void productInformationAddToCart(){

        product.click();
        //        7. Click on Add to cart
        driver.findElement(By.xpath("//span[.='Add to cart']")).click();

        //        8. Verify confirmation message “Product successfully added to your shopping cart”

        WebElement actualMessage= driver.findElement(By.xpath("//div[@class='layer_cart_product col-xs-12 col-md-6']//h2"));
        String expectedMessage = "Product successfully added to your shopping cart";

        actualMessage.click();
        String actualMsg = actualMessage.getText();
        System.out.println("Expected confirmation message: " + expectedMessage + " | Actual confirmation message: " + actualMsg);
        Assert.assertEquals(expectedMessage, actualMsg);

        //        9. verify that default quantity is 1

        WebElement defaultQuantity = driver.findElement(By.id("layer_cart_product_quantity"));
        String expectedQuantity = "1";
        String actualQuantity = defaultQuantity.getText();
        System.out.println("Expected default quantity: " + expectedQuantity + " | Actual quantity: " + actualQuantity);
        Assert.assertEquals(expectedQuantity, actualQuantity);


        //        10. Verify that default size is S

        String expectedSize = "S";
        WebElement defaultSize = driver.findElement(By.id("layer_cart_product_attributes"));
        String actualSize = defaultSize.getText();
        Assert.assertTrue(actualSize.substring(actualSize.indexOf(","),actualSize.length()).contains(expectedSize));

        //        11. Verify that same name and price displayed as on the home page

        // we have home page name and price from the before method test

        String cartName = driver.findElement(By.id("layer_cart_product_title")).getText();
        String cartPrice = driver.findElement(By.id("layer_cart_product_price")).getText();

        System.out.println("Expected name from homepage: " + productName + " | Actual name: " + cartName);
        System.out.println("Expected price from homepage: " + productPrice + " | Actual price: " + cartPrice);

        Assert.assertEquals(productName, cartName);
        Assert.assertEquals(productPrice, cartPrice);

    }
}
