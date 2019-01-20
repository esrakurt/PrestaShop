package com.prestashop.utilities;

        import com.github.javafaker.Faker;
        import io.github.bonigarcia.wdm.WebDriverManager;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.interactions.Actions;
        import org.openqa.selenium.support.ui.Select;
        import org.testng.Assert;
        import org.testng.annotations.AfterMethod;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.BeforeMethod;
        import org.testng.asserts.SoftAssert;

        import java.util.Random;
        import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver driver;
    protected Actions actions;
    protected SoftAssert softAssert;
    protected Random random;
//    protected Faker faker;

//
//    @BeforeClass
//    public void setupClass(){
//
//        WebDriverManager.chromedriver().setup();
//    }

    @BeforeMethod
    public void setUpMethod(){
//        driver=new ChromeDriver() ;
        driver= Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
        actions = new Actions(driver);
        softAssert = new SoftAssert();

    }

//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//    }

    Faker faker = new Faker();

    public String rndmEmail = faker.internet().emailAddress();
    public String rndmName = faker.name().firstName();
    public String rndmLastName = faker.name().lastName();
    public String rndmPassword = faker.internet().password();
    public int rndmDay = faker.number().numberBetween(1, 31);
    public int rndmMonth = faker.number().numberBetween(1, 12);
    public String rndmAddress = faker.address().streetAddress();
    public String rndmCity = faker.address().city();
    public String rndmZip = faker.address().zipCode().substring(0,5);
    public String rndmCellPhone = faker.phoneNumber().cellPhone();


    public void signInCreateAccount(){
        //        1. Open browser
//        2. Go to http://automationpractice.com/index.php

//        3. Click Sign in link
        driver.findElement(By.linkText("Sign in")).click();

//        4. Enter new valid email to the email field
//        Faker faker = new Faker();
//        String email = faker.internet().emailAddress();
        driver.findElement(By.id("email_create")).sendKeys(rndmEmail);

//        5. Click on Create Account
        driver.findElement(By.xpath("//button[@class='btn btn-default button button-medium exclusive']//span")).click();
    }

    public void createAccount(){

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

    }


}

//    public class TestBase {
//        protected static WebDriver driver;
//        protected Actions actions;
//        protected SoftAssert softAssert;
//
//        Faker fakeGuy = new Faker();
//
//        public final String EMAIL = fakeGuy.internet().emailAddress();
//        public final String NAME = fakeGuy.name().firstName();
//        public final String LASTNAME = fakeGuy.name().lastName();
//        public final String PASSWORD = fakeGuy.internet().password();
//        public final int DAYS = fakeGuy.number().numberBetween(1, 31);
//        public final int MONTH = fakeGuy.number().numberBetween(1, 12);
//        public final int YEAR = fakeGuy.number().numberBetween(1930, 2000);
//        public final String ADDRESS1 = fakeGuy.address().streetAddress();
//        public final String ADDRESS2 = fakeGuy.address().secondaryAddress();
//        public final String CITY = fakeGuy.address().city();
//        public final String STATE = fakeGuy.address().state();
//        public final String ZIPCODE = fakeGuy.address().zipCode();
//        public final String STATEABBR = fakeGuy.address().stateAbbr();
//        public final String PHONENUMBER = fakeGuy.phoneNumber().cellPhone();
//        public final String COMPANY = fakeGuy.company().name();
//        public final double RANDOMNUMBER = Math.random();
//        public final String US = "United States";
//
//        public final String CREDITCARD = fakeGuy.finance().creditCard();
//
//
//
//
//        public final void INFO() {
//            System.out.println("Email: " + EMAIL);
//            System.out.println("Name: " + NAME);
//            System.out.println("Last Name: " + LASTNAME);
//            System.out.println("Password: " + PASSWORD);
//            System.out.println("Day: " + DAYS + ", Month: " + MONTH + ", Year: " + YEAR);
//            System.out.println("Address 1: " + ADDRESS1 + ", Address 2: " + ADDRESS2);
//            System.out.println("City: " + CITY);
//            System.out.println("State: " + STATE);
//            System.out.println("Zip code: " + ZIPCODE);
//            System.out.println("State abbreviation: " + STATEABBR);
//            System.out.println("Phone number: " + PHONENUMBER);
//            System.out.println("Company name: " + COMPANY);
//            System.out.println("Double Number: " + RANDOMNUMBER);
//            System.out.println("County: " + US);
//        }
//
//        @BeforeClass
//        public void setUpClass() {
//            driver = Driver.getDriver();
//            actions = new Actions(driver);
//            softAssert = new SoftAssert();
//            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//
//        }
//
//        public void timeWait2(){
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        public void timeWait4(){
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        @AfterMethod
//        public void setDownMethod(){
//            // Driver.closeDriver();
//            softAssert.assertAll();
//        }
//
//
//    }


//    public void fillOutRequirements(){
//        //        7. Fill out all the required steps
//        Random random = new Random();
//        int rndmGender = random.nextInt(2)+1;
//        driver.findElement(By.xpath("//div[@class='account_creation']/div[1]/div[rndmgender]/label/div[rndmGender]")).click(); // click on Mrs
//        String rndmName = faker.name().firstName();
//        driver.findElement(By.id("customer_firstname")).sendKeys(rndmName);
//        String rndmLastName = faker.name().lastName();
//        driver.findElement(By.id("customer_lastname")).sendKeys(rndmLastName);
//        String rndmPassword = faker.internet().password();
//        driver.findElement(By.id("passwd")).sendKeys(rndmPassword);
//        WebElement day = driver.findElement(By.id("days"));
//        day.click();
//        Select select1 = new Select(day);
//        int rndmDay = random.nextInt(30)+1;
//        select1.selectByIndex(rndmDay);
//
//        WebElement month = driver.findElement(By.id("months"));
//        month.click();
//        Select select2 = new Select(month);
//        int rndmMonth = random.nextInt(11)+1;
//        select2.selectByIndex(rndmMonth);
//
//        WebElement year = driver.findElement(By.id("years"));
//        year.click();
//        Select select3 = new Select(year);
//        int rndmYear = random.nextInt(120);
//        select3.selectByIndex(rndmYear);
//
//        String rndmAddress = faker.address().streetAddress();
//        String rndmCity = faker.address().city();
//
//        driver.findElement(By.id("firstname")).sendKeys(rndmName);
//        driver.findElement(By.id("lastname")).sendKeys(rndmLastName);
//        driver.findElement(By.id("address1")).sendKeys(rndmAddress);
//        driver.findElement(By.id("city")).sendKeys(rndmCity);
//
//        WebElement state = driver.findElement(By.id("id_state"));
//        Select selectState = new Select(state);
//        int rndmState = random.nextInt(51);
//        selectState.selectByIndex(rndmState);
//        String rndmZip = faker.address().zipCode();
//        driver.findElement(By.id("postcode")).sendKeys(rndmZip);
//
//        WebElement country = driver.findElement(By.id("id_country"));
//        Select selectCountry = new Select(country);
//        selectCountry.selectByValue("21");
//        String rndmCellPhone = faker.phoneNumber().cellPhone();
//        driver.findElement(By.id("phone_mobile")).sendKeys(rndmCellPhone);
//
//        driver.findElement(By.id("alias")).sendKeys("My address: ");
//
////        8. Click on Register
//        driver.findElement(By.xpath("//button[@id='submitAccount']//span")).click();
//
//    }

