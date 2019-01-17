package com.prestashop.tests.functional_tests.login;

import com.github.javafaker.Faker;
import com.prestashop.pages.Homepage;
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

    Homepage homepage = new Homepage();
    LoginPage loginpage = new LoginPage();
    Random random = new Random();
    MyAccountPage myAccountPage = new MyAccountPage();
    MyAddressesPage myAddressesPage = new MyAddressesPage();

    @Test
    public void errorMessageValidation(){
        //            1. Open browser
        //            2. Go to http://automationpractice.com/index.php
        //            3. Click Sign in link
        homepage.signIn.click();

        //            4. Enter new valid email to the email field
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        homepage.email.sendKeys(email);

        //            5. Click on Create Account
        homepage.createAccountButton.click();

        //            6. Fill all the required steps except for first name
        int x = random.nextInt(2);

        switch (x){
            case 0: loginpage.mr.click();
                break;
            case 1: loginpage.mrs.click();
                break;
        }

        String rndmLastName = faker.name().lastName();
        loginpage.lastName.sendKeys(rndmLastName);

        String rndmPassword = faker.internet().password();
        loginpage.password.sendKeys(rndmPassword);

        int rndmDay = faker.number().numberBetween(1, 31);
        Select select1 = new Select(loginpage.day);
        select1.selectByIndex(rndmDay);

        int rndmMonth = faker.number().numberBetween(1, 12);
        Select select2 = new Select(loginpage.month);
        select2.selectByIndex(rndmMonth);

        int rndmYear = random.nextInt(120)+1;
        Select select3 = new Select(loginpage.year);
        select3.selectByIndex(rndmYear);

        String rndmAddress = faker.address().streetAddress();
        String rndmCity = faker.address().city();

        loginpage.lastNameOnAddressSection.sendKeys(rndmLastName);
        loginpage.address.sendKeys(rndmAddress);
        loginpage.city.sendKeys(rndmCity);

        WebElement state = loginpage.state;
        Select selectState = new Select(state);
        int rndmState = random.nextInt(51);
        selectState.selectByIndex(rndmState);

        String rndmZip = faker.address().zipCode().substring(0,5);
        loginpage.zip.sendKeys(rndmZip);

        WebElement country = loginpage.country;
        Select selectCountry = new Select(country);
        selectCountry.selectByValue("21");

        String rndmCellPhone = faker.phoneNumber().cellPhone();
        loginpage.mobilePhone.sendKeys(rndmCellPhone);

        loginpage.alias.sendKeys("My address: ");

        //            7. Click on Register
        loginpage.registerButton.click();

        //            8. Verify that error message firstname is required. is displayed
        String expectedFirstNameErrorMessage = "firstname is required.";
        String actualFirstNameErrorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText();

        System.out.println("Ecxpected error: " + expectedFirstNameErrorMessage + " | Actual error message: " + actualFirstNameErrorMessage);
        Assert.assertEquals(expectedFirstNameErrorMessage, actualFirstNameErrorMessage);
    }




}
