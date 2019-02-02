package com.prestashop.tests.functional_tests.login;

import com.github.javafaker.Faker;
import com.prestashop.pages.HomePage;
import com.prestashop.pages.LoginPage;
import com.prestashop.pages.MyAccountPage;
import com.prestashop.pages.MyAddressesPage;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

//    Error Message Validation: First name
//
public class ErrorMessageValidationTest_PrestaShop2POM extends TestBase {

    Random random = new Random();

    @Test
    public void errorMessageValidation(){
        extentLogger = report.createTest("Error Message Validation Test");
        extentLogger.info("Open browser and go to the website");
        //            1. Open browser
        //            2. Go to http://automationpractice.com/index.php
        //            3. Click Sign in link
        extentLogger.info("Click Sign in link");
        pages.homePage().signIn.click();

        //            4. Enter new valid email to the email field
        extentLogger.info("Enter new valid email to the email field");
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        pages.homePage().email.sendKeys(email);

        //            5. Click on Create Account
        extentLogger.info("Click on Create Account");
        pages.homePage().createAccountButton.click();

        //            6. Fill all the required steps except for first name
        extentLogger.info("Fill all the required steps except for first name");
        int x = random.nextInt(2);

        switch (x){
            case 0: pages.loginPage().mr.click();
                break;
            case 1: pages.loginPage().mrs.click();
                break;
        }

        String rndmLastName = faker.name().lastName();
        pages.loginPage().lastName.sendKeys(rndmLastName);

        String rndmPassword = faker.internet().password();
        pages.loginPage().password.sendKeys(rndmPassword);

        int rndmDay = faker.number().numberBetween(1, 31);
        Select select1 = new Select(pages.loginPage().day);
        select1.selectByIndex(rndmDay);

        int rndmMonth = faker.number().numberBetween(1, 12);
        Select select2 = new Select(pages.loginPage().month);
        select2.selectByIndex(rndmMonth);

        int rndmYear = random.nextInt(120)+1;
        Select select3 = new Select(pages.loginPage().year);
        select3.selectByIndex(rndmYear);

        String rndmAddress = faker.address().streetAddress();
        String rndmCity = faker.address().city();

        pages.loginPage().lastNameOnAddressSection.sendKeys(rndmLastName);
        pages.loginPage().address.sendKeys(rndmAddress);
        pages.loginPage().city.sendKeys(rndmCity);

        WebElement state = pages.loginPage().state;
        Select selectState = new Select(state);
        int rndmState = random.nextInt(51);
        selectState.selectByIndex(rndmState);

        String rndmZip = faker.address().zipCode().substring(0,5);
        pages.loginPage().zip.sendKeys(rndmZip);

        WebElement country = pages.loginPage().country;
        Select selectCountry = new Select(country);
        selectCountry.selectByValue("21");

        String rndmCellPhone = faker.phoneNumber().cellPhone();
        pages.loginPage().mobilePhone.sendKeys(rndmCellPhone);

        pages.loginPage().alias.sendKeys("My address: ");

        //            7. Click on Register
        extentLogger.info("Click on Register");
        pages.loginPage().registerButton.click();

        //            8. Verify that error message firstname is required. is displayed
        extentLogger.info("Verify that error message firstname is required. is displayed");
        String expectedFirstNameErrorMessage = "firstname is required.";
        String actualFirstNameErrorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText();

        System.out.println("Ecxpected error: " + expectedFirstNameErrorMessage + " | Actual error message: " + actualFirstNameErrorMessage);
        Assert.assertEquals(expectedFirstNameErrorMessage, actualFirstNameErrorMessage);

        extentLogger.pass("Completed: Error Message Validation Test");
    }




}
