package com.prestashop.tests.functional_tests.login;

import com.github.javafaker.Faker;
import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTest_PrestaShop2POM extends TestBase {


    //        Registration Test
    @Test
    public void registrationTest() throws InterruptedException {

        Homepage homepage = new Homepage();
        LoginPage loginpage = new LoginPage();
        Random random = new Random();
        MyAccountPage myAccountPage = new MyAccountPage();
        MyAddressesPage myAddressesPage = new MyAddressesPage();

        PersonalInformationPage personalInformationPage = new PersonalInformationPage();

        //        1. Open browser
        //        2. Go to http://automationpractice.com/index.php
        //        3. Click Sign in link
        homepage.signIn.click();

        //        4. Enter new valid email to the email field
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        homepage.email.sendKeys(email);


        //        5. Click on Create Account
        homepage.createAccountButton.click();

        //        6. Verify that that email link displays current email
        WebElement cnfrmEmail = loginpage.readyEmail;
        String confirmEmail = cnfrmEmail.getAttribute("value");
        System.out.println(confirmEmail);
        Assert.assertEquals(email, confirmEmail);

        //        7. Fill out all the required steps
        int x = random.nextInt(2);

        switch (x){
            case 0: loginpage.mr.click();
                break;
            case 1: loginpage.mrs.click();
                break;
        }

        String rndmName = faker.name().firstName();
        loginpage.firstName.sendKeys(rndmName);

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

        loginpage.firstNameOnAddressSection.sendKeys(rndmName);
        loginpage.lastNameOnAddressSection.sendKeys(rndmLastName);
        loginpage.address.sendKeys(rndmAddress);
        loginpage.city.sendKeys(rndmCity);

        Select selectState = new Select(loginpage.state);
        int rndmState = random.nextInt(51)+1;
        selectState.selectByIndex(rndmState);

        String rndmZip = faker.address().zipCode().substring(0,5);
        loginpage.zip.sendKeys(rndmZip);

        Select selectCountry = new Select(loginpage.country);
        selectCountry.selectByValue("21");

        String rndmCellPhone = faker.phoneNumber().cellPhone();
        loginpage.mobilePhone.sendKeys(rndmCellPhone);

        loginpage.alias.sendKeys("My address: ");

//        8. Click on Register
        loginpage.registerButton.click();

//        9. Verify that correct first and last name is displayed correctly on top right

            String expectedFirstAndLast = rndmName + " " + rndmLastName;
            String actualFirstAndLast = myAccountPage.fullNameOnMyAccountPage.getText();

            System.out.println("Expected first and last name: " + expectedFirstAndLast + " | Actual first and lastname: " + actualFirstAndLast);
            Assert.assertEquals(expectedFirstAndLast, actualFirstAndLast);

//        10. Click on My personal information
            myAccountPage.myPersonalInformationButton.click();

//        11. Verify that personal information is displayed correctly
            System.out.println("Mrs. is selected " + personalInformationPage.socialTitle.isSelected());
            System.out.println("Is first name " + rndmName + "? " + personalInformationPage.firstNamePersonalInfo.getAttribute("value").equals(rndmName));
            System.out.println("Is last name " + rndmLastName + "? " + personalInformationPage.lastNamePersonalInfo.getAttribute("value").equals(rndmLastName));
            System.out.println("Is email " + email+ "? " + personalInformationPage.emailPersonalInfo.getAttribute("value").equals(email));
            System.out.println("Is day selected? " + personalInformationPage.dayPersonalInfo.isSelected());
            System.out.println("Is month selected? " + personalInformationPage.monthPersonalInfo.isSelected());
            System.out.println("Is year selected? " + personalInformationPage.yearPersonalInfo.isSelected());

//        12. Click on Back to your account
            personalInformationPage.backToYourAccountButton.click();

//        13. Click on My addresses verify that address information is displayed correctly
            myAccountPage.myAddressesButton.click();

            String expectedAddress = rndmAddress;
            String actualAddress = myAddressesPage.myAddressOnMyAddressesPage.getText();
            System.out.println("Expected address: " + expectedAddress + " | Actual address: " + actualAddress);
            Assert.assertEquals(expectedAddress, actualAddress);

//        14. Click on sign out link
            myAddressesPage.signOutButtonOnMyAddressesPage.click();

//        15. Login using the email and password if the current user

            homepage.emailAddressHomePage.sendKeys(email);
            homepage.passwordHomePage.sendKeys(rndmPassword);
            homepage.signInHomePage.click();

//        16. Verify that correct first and last name is displayed correctly on top right

        String expectedFirstAndLastLoginPage = rndmName + " " + rndmLastName;
        String actualFirstAndLastLoginPage = myAccountPage.fullNameOnMyAccountPage.getText();

        System.out.println("Expected first and last name: " + expectedFirstAndLastLoginPage + " | Actual first and lastname: " + actualFirstAndLastLoginPage);
        Assert.assertEquals(expectedFirstAndLastLoginPage, actualFirstAndLastLoginPage);

//        NOTE: for the test case above you must to generate random information for email,
//                first name, last name, phone etc.
        }


    }
