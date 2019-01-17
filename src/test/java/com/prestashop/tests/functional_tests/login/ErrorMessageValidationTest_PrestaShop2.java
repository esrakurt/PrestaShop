package com.prestashop.tests.functional_tests.login;

import com.github.javafaker.Faker;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

//    Error Message Validation: First name
//
public class ErrorMessageValidationTest_PrestaShop2 extends TestBase {

    @Test
    public void errorMessageValidation(){
        //            1. Open browser
        //            2. Go to http://automationpractice.com/index.php
        //            3. Click Sign in link
        driver.findElement(By.linkText("Sign in")).click();

        //            4. Enter new valid email to the email field
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        driver.findElement(By.id("email_create")).sendKeys(email);

        //            5. Click on Create Account
        driver.findElement(By.xpath("//button[@class='btn btn-default button button-medium exclusive']//span")).click();

        //            6. Fill all the required steps except for first name
        Random random = new Random();
        int rndmGender = random.nextInt(2) + 1;
        driver.findElement(By.xpath("//div[@class='account_creation']/div[1]/div[1]/label/div[1]")).click(); // click on Mrs
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

        //            7. Click on Register
        driver.findElement(By.xpath("//button[@id='submitAccount']//span")).click();

        //            8. Verify that error message firstname is required. is displayed
        String expectedFirstNameErrorMessage = "firstname is required.";
        String actualFirstNameErrorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText();

        System.out.println("Ecxpected error: " + expectedFirstNameErrorMessage + " | Actual error message: " + actualFirstNameErrorMessage);
        Assert.assertEquals(expectedFirstNameErrorMessage, actualFirstNameErrorMessage);
    }




}
