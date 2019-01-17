package com.prestashop.tests.functional_tests.login;

import com.github.javafaker.Faker;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RegistrationTest_PrestaShop2 extends TestBase {


    //        Registration Test
    @Test
    public void registrationTest() throws InterruptedException {
        //        1. Open browser
        //        2. Go to http://automationpractice.com/index.php

        //        3. Click Sign in link
        driver.findElement(By.linkText("Sign in")).click();

        //        4. Enter new valid email to the email field
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        System.out.println(email);
        driver.findElement(By.id("email_create")).sendKeys(email);

        //        5. Click on Create Account
           driver.findElement(By.xpath("//button[@class='btn btn-default button button-medium exclusive']//span")).click();


        //        6. Verify that that email link displays current email
        WebElement cnfrmEmail = driver.findElement(By.xpath("//form//div[1]//div[4]//input[@id='email']"));
        String confirmEmail = cnfrmEmail.getAttribute("value");
        System.out.println(confirmEmail);
        Assert.assertEquals(email, confirmEmail);

        //        7. Fill out all the required steps
        Random random = new Random();
        int x = random.nextInt(2);
        switch (x){
            case 0: driver.findElement(By.id("id_gender1")).click();
                break;
            case 1: driver.findElement(By.id("id_gender2")).click();
                break;
        }
        String rndmName = faker.name().firstName();
        driver.findElement(By.id("customer_firstname")).sendKeys(rndmName);
        String rndmLastName = faker.name().lastName();
        driver.findElement(By.id("customer_lastname")).sendKeys(rndmLastName);
        String rndmPassword = faker.internet().password();
        driver.findElement(By.id("passwd")).sendKeys(rndmPassword);
        WebElement day = driver.findElement(By.id("days"));
        day.click();
        Select select1 = new Select(day);
        int rndmDay = random.nextInt(30) + 1;
        select1.selectByIndex(rndmDay);

        WebElement month = driver.findElement(By.id("months"));
        month.click();
        Select select2 = new Select(month);
        int rndmMonth = random.nextInt(11) + 1;
        select2.selectByIndex(rndmMonth);

        WebElement year = driver.findElement(By.id("years"));
        year.click();
        Select select3 = new Select(year);
        int rndmYear = random.nextInt(120);
        select3.selectByIndex(rndmYear);

        String rndmAddress = faker.address().streetAddress();
        String rndmCity = faker.address().city();

        driver.findElement(By.id("firstname")).sendKeys(rndmName);
        driver.findElement(By.id("lastname")).sendKeys(rndmLastName);
        driver.findElement(By.id("address1")).sendKeys(rndmAddress);
        driver.findElement(By.id("city")).sendKeys(rndmCity);

        WebElement state = driver.findElement(By.id("id_state"));
        Select selectState = new Select(state);
        int rndmState = random.nextInt(51);
        selectState.selectByIndex(rndmState);
        String rndmZip = faker.address().zipCode().substring(0,5);
        driver.findElement(By.id("postcode")).sendKeys(rndmZip);

        WebElement country = driver.findElement(By.id("id_country"));
        Select selectCountry = new Select(country);
        selectCountry.selectByValue("21");

        String rndmCellPhone = faker.phoneNumber().cellPhone();
        driver.findElement(By.id("phone_mobile")).sendKeys(rndmCellPhone);

        driver.findElement(By.id("alias")).sendKeys("My address: ");

//        8. Click on Register
        driver.findElement(By.xpath("//button[@id='submitAccount']//span")).click();

//        9. Verify that correct first and last name is displayed correctly on top right

            String expectedFirstAndLast = rndmName + " " + rndmLastName;
            String actualFirstAndLast = driver.findElement(By.xpath("//a[@title='View my customer account']//span")).getText();

            System.out.println("Expected first and last name: " + expectedFirstAndLast + " | Actual first and lastname: " + actualFirstAndLast);
            Assert.assertEquals(expectedFirstAndLast, actualFirstAndLast);

//        10. Click on My personal information
            driver.findElement(By.xpath("//a[@title='Information']//span")).click();

//        11. Verify that personal information is displayed correctly
            System.out.println("Mrs. is selected " + driver.findElement(By.id("id_gender2")).isSelected());
            System.out.println("Is first name " + rndmName + "? " + driver.findElement(By.id("firstname")).getAttribute("value").equals(rndmName));
            System.out.println("Is last name " + rndmLastName + "? " + driver.findElement(By.id("lastname")).getAttribute("value").equals(rndmLastName));
            System.out.println("Is email " + email+ "? " + driver.findElement(By.id("email")).getAttribute("value").equals(email));
            System.out.println("Is day selected? " + driver.findElement(By.xpath("//select[@id='days']//option[11]")).isSelected());
            System.out.println("Is month selected? " + driver.findElement(By.xpath("//select[@id='months']//option[11]")).isSelected());
            System.out.println("Is year selected? " + driver.findElement(By.xpath("//select[@id='years']//option[11]")).isSelected());

//        12. Click on Back to your account
            driver.findElement(By.linkText("Back to your account")).click();

//        13. Click on My addresses verify that address information is displayed correctly
            driver.findElement(By.xpath("//span[.='My addresses']")).click();

            String expectedAddress = rndmAddress;
            String actualAddress = driver.findElement(By.xpath("//div[@class='col-xs-12 col-sm-6 address']//ul//li[4]")).getText();
            System.out.println("Expected address: " + expectedAddress + " | Actual address: " + actualAddress);
            Assert.assertEquals(expectedAddress, actualAddress);

//        14. Click on sign out link
            driver.findElement(By.xpath("//a[@class='logout']")).click();

//        15. Login using the email and password if the current user

            driver.findElement(By.xpath("//div//input[@id='email']")).sendKeys(email);
            driver.findElement(By.xpath("//div//input[@id='passwd']")).sendKeys(rndmPassword);
            driver.findElement(By.id("SubmitLogin")).click();

//        16. Verify that correct first and last name is displayed correctly on top right

        String expectedFirstAndLastLoginPage = rndmName + " " + rndmLastName;
        String actualFirstAndLastLoginPage = driver.findElement(By.xpath("//a[@title='View my customer account']//span")).getText();

        System.out.println("Expected first and last name: " + expectedFirstAndLastLoginPage + " | Actual first and lastname: " + actualFirstAndLastLoginPage);
        Assert.assertEquals(expectedFirstAndLastLoginPage, actualFirstAndLastLoginPage);

//        NOTE: for the test case above you must to generate random information for email,
//                first name, last name, phone etc.
        }


    }
