package com.prestashop.tests.functional_tests.login;

import com.github.javafaker.Faker;
import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTest_PrestaShop2POM extends TestBase {


    //        Registration Test
    @Test
    public void registrationTest() throws InterruptedException {

        Random random = new Random();
        extentLogger = report.createTest("Registration Test");
        //        1. Open browser
        //        2. Go to http://automationpractice.com/index.php
        //        3. Click Sign in link
        extentLogger.info("Click Sign in link");
        pages.homePage().signIn.click();

        //        4. Enter new valid email to the email field
        extentLogger.info("Enter new valid email to the email field");
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        pages.homePage().email.sendKeys(email);


        //        5. Click on Create Account
        extentLogger.info("Click on Create Account");
        pages.homePage().createAccountButton.click();

        //        6. Verify that that email link displays current email
        extentLogger.info("Verify that that email link displays current email");
        WebElement cnfrmEmail = pages.loginPage().readyEmail;
        String confirmEmail = cnfrmEmail.getAttribute("value");
        System.out.println(confirmEmail);
        Assert.assertEquals(email, confirmEmail);

        //        7. Fill out all the required steps
        extentLogger.info("Fill out all the required steps");
        int x = random.nextInt(2);

        switch (x){
            case 0: pages.loginPage().mr.click();
                break;
            case 1: pages.loginPage().mrs.click();
                break;
        }

        String rndmName = faker.name().firstName();
        pages.loginPage().firstName.sendKeys(rndmName);

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

        pages.loginPage().firstNameOnAddressSection.sendKeys(rndmName);
        pages.loginPage().lastNameOnAddressSection.sendKeys(rndmLastName);
        pages.loginPage().address.sendKeys(rndmAddress);
        pages.loginPage().city.sendKeys(rndmCity);

        Select selectState = new Select(pages.loginPage().state);
        int rndmState = random.nextInt(51)+1;
        selectState.selectByIndex(rndmState);

        String rndmZip = faker.address().zipCode().substring(0,5);
        pages.loginPage().zip.sendKeys(rndmZip);

        Select selectCountry = new Select(pages.loginPage().country);
        selectCountry.selectByValue("21");

        String rndmCellPhone = faker.phoneNumber().cellPhone();
        pages.loginPage().mobilePhone.sendKeys(rndmCellPhone);

        pages.loginPage().alias.sendKeys("My address: ");

//        8. Click on Register
        extentLogger.info("Click on Register");
        pages.loginPage().registerButton.click();

//        9. Verify that correct first and last name is displayed correctly on top right
        extentLogger.info("Verify that correct first and last name is displayed correctly on top right");
        String expectedFirstAndLast = rndmName + " " + rndmLastName;
        String actualFirstAndLast = pages.myAccountPage().fullNameOnMyAccountPage.getText();

        System.out.println("Expected first and last name: " + expectedFirstAndLast + " | Actual first and lastname: " + actualFirstAndLast);
        Assert.assertEquals(expectedFirstAndLast, actualFirstAndLast);

//        10. Click on My personal information
        extentLogger.info("Click on My personal information");
        pages.myAccountPage().myPersonalInformationButton.click();

//        11. Verify that personal information is displayed correctly
        extentLogger.info("Verify that personal information is displayed correctly");
            System.out.println("Mrs. is selected " + pages.personalInformationPage().socialTitle.isSelected());
            System.out.println("Is first name " + rndmName + "? " + pages.personalInformationPage().firstNamePersonalInfo.getAttribute("value").equals(rndmName));
            System.out.println("Is last name " + rndmLastName + "? " + pages.personalInformationPage().lastNamePersonalInfo.getAttribute("value").equals(rndmLastName));
            System.out.println("Is email " + email+ "? " + pages.personalInformationPage().emailPersonalInfo.getAttribute("value").equals(email));
            System.out.println("Is day selected? " + pages.personalInformationPage().dayPersonalInfo.isSelected());
            System.out.println("Is month selected? " + pages.personalInformationPage().monthPersonalInfo.isSelected());
            System.out.println("Is year selected? " + pages.personalInformationPage().yearPersonalInfo.isSelected());

//        12. Click on Back to your account
        extentLogger.info("Click on Back to your account");
            pages.personalInformationPage().backToYourAccountButton.click();

//        13. Click on My addresses verify that address information is displayed correctly
        extentLogger.info("Click on My addresses verify that address information is displayed correctly");
            pages.myAccountPage().myAddressesButton.click();

            String expectedAddress = rndmAddress;
            String actualAddress = pages.myAddressesPage().myAddressOnMyAddressesPage.getText();
            System.out.println("Expected address: " + expectedAddress + " | Actual address: " + actualAddress);
            Assert.assertEquals(expectedAddress, actualAddress);

//        14. Click on sign out link
        extentLogger.info("Click on sign out link");
            pages.myAddressesPage().signOutButtonOnMyAddressesPage.click();

//        15. Login using the email and password if the current user
        extentLogger.info("Login using the email and password if the current user");
            pages.homePage().emailAddressHomePage.sendKeys(email);
            pages.homePage().passwordHomePage.sendKeys(rndmPassword);
            pages.homePage().signInHomePage.click();

//        16. Verify that correct first and last name is displayed correctly on top right
        extentLogger.info("Verify that correct first and last name is displayed correctly on top right");
        String expectedFirstAndLastLoginPage = rndmName + " " + rndmLastName;
        String actualFirstAndLastLoginPage = pages.myAccountPage().fullNameOnMyAccountPage.getText();

        System.out.println("Expected first and last name: " + expectedFirstAndLastLoginPage + " | Actual first and lastname: " + actualFirstAndLastLoginPage);
        Assert.assertEquals(expectedFirstAndLastLoginPage, actualFirstAndLastLoginPage);

        extentLogger.pass("Completed: Registration Test");

//        NOTE: for the test case above you must to generate random information for email,
//                first name, last name, phone etc.
        }


    }
