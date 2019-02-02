package com.prestashop.utilities;

import com.prestashop.pages.*;

public class Pages {

        private CartDetailPage cartDetailPage;
        private HomePage homePage;
        private LoginPage loginPage;
        private MyAccountPage myAccountPage;
        private MyAddressesPage myAddressesPage;
        private PersonalInformationPage personalInformationPage;


        public CartDetailPage cartDetailPage() {
            if (cartDetailPage == null) {
                cartDetailPage = new CartDetailPage();
            }
            return cartDetailPage;
        }

        public HomePage homePage() {
            if (homePage == null) {
                homePage = new HomePage();
            }
            return homePage;
        }

        public LoginPage loginPage() {
            if (loginPage == null) {
                loginPage = new LoginPage();
            }
            return loginPage;
        }

        public MyAccountPage myAccountPage() {
            if (myAccountPage == null) {
                myAccountPage = new MyAccountPage();
            }
            return myAccountPage;
        }

        public MyAddressesPage myAddressesPage() {
            if (myAddressesPage == null) {
                myAddressesPage = new MyAddressesPage();
            }
            return myAddressesPage;
        }

        public PersonalInformationPage personalInformationPage() {
            if (personalInformationPage == null) {
                personalInformationPage = new PersonalInformationPage();
            }
            return personalInformationPage;
        }

    }
