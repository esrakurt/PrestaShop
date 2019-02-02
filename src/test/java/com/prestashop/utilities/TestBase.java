package com.prestashop.utilities;

        import com.aventstack.extentreports.ExtentReports;
        import com.aventstack.extentreports.ExtentTest;
        import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
        import com.github.javafaker.Faker;
        import io.github.bonigarcia.wdm.WebDriverManager;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.interactions.Actions;
        import org.openqa.selenium.support.ui.Select;
        import org.testng.Assert;
        import org.testng.ITestResult;
        import org.testng.annotations.*;
        import org.testng.asserts.SoftAssert;

        import java.io.IOException;
        import java.util.Random;
        import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    protected WebDriver driver;
    protected Pages pages;
    protected Actions actions;
    protected SoftAssert softAssert;
    protected Random random;

    protected static ExtentReports report;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest extentLogger;
    public WebElement enterRandomQuantity;

    @BeforeMethod
    public void setUpMethod(){
//        driver=new ChromeDriver() ;
        driver = Driver.getDriver();
//        driver.get("http://automationpractice.com/index.php");
        pages = new Pages();
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        driver.get(ConfigurationReader.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }


    @AfterMethod
    public void tearDownMethod(ITestResult result) throws IOException {
        // if any test fails, it can detect it, take a screenshot at the point and attach to the report
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotLocation = BrowserUtils.getScreenshot(result.getName());
            extentLogger.fail(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotLocation);
            extentLogger.fail(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            extentLogger.skip("Test Case Skipped: " + result.getName());
        }
        Driver.closeDriver();
    }

    @BeforeTest
    public void setUpTest() {
        report = new ExtentReports();
        // this is our custom location of the report that will be generated
        // report will be generated in the current project inside folder: test-output
        // report file name: report.html
        String filePath = System.getProperty("user.dir") + "/test-output/report.html";
        // initialize the htmlReporter with the path to the report
        htmlReporter = new ExtentHtmlReporter(filePath);

        // we attach the htmlreport to our report
        report.attachReporter(htmlReporter);

        report.setSystemInfo("Environment", "Staging");
        report.setSystemInfo("Browser", ConfigurationReader.getProperty("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));

        // we can add many information as we want, below is just an example
        report.setSystemInfo("QA Engineer", System.getProperty("Mary"));

        htmlReporter.config().setDocumentTitle("Prestashop Reports");
        htmlReporter.config().setReportName("Prestashop Automated Test Reports");
//        htmlReporter.config().setTheme(Theme.DARK);

    }

    @AfterTest
    public void tearDownTest() {
        report.flush();
    }




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


