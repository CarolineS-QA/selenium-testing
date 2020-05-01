package org.example;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ExtentReportExerciseTest {
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    @BeforeTest
    public void startReport(){
        report = new ExtentReports(
                System.getProperty("user.dir") + "/test-output/Report.html",
                true
        );
        report
                .addSystemInfo("Host Name", "QA")
                .addSystemInfo("Tester", "Caroline");
        report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-report.xml"));
    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
    }

    public void jsScroll(){
        if (driver instanceof JavascriptExecutor){
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
        test.log(LogStatus.INFO, "Scrolling down the page");
        } else {
        System.out.println("Can't execute JS");
        test.log(LogStatus.INFO, "Unable to execute JS to scroll");
        }
    }

    @Test
    public void latestStockPriceIndex() throws InterruptedException, IOException {
        test = report.startTest("Starting latestStockPriceIndex example test");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
        test.log(LogStatus.INFO, "Navigating to the stock market website");
        WebElement risers = driver.findElement(By.xpath("//*[@id=\"view-constituents\"]/ul/li[2]/a"));
        jsScroll();
        sleep(2000);
        risers.click();
        test.log(LogStatus.INFO, "Click on 'risers'");
        sleep(2000);
        WebElement header = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div/div[2]/h1"));
        assertEquals(header.getText(),"FTSE 100: TOP 20 RISERS");
        File riserPic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(riserPic, new File(System.getProperty("user.dir") + "/test-output/risersPage.jpg"));
        test.log(LogStatus.PASS, "'FTSE 100: TOP 20 RISERS' is shown in the header", "<img src=risersPage.jpg>");
        sleep(2000);
        WebElement fallers = driver.findElement(By.xpath("//*[@id=\"content_div_40583\"]/ul/li[3]/a"));
        jsScroll();
        WebElement riserTable = driver.findElement(By.xpath("//*[@id=\"view-constituents\"]/div[2]/table/tbody"));
        String[] risersArray = riserTable.getText().split("\n");
        test.log(LogStatus.INFO, "The highest riser is: " + risersArray[0]);
        File riserTablePic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(riserTablePic, new File(System.getProperty("user.dir") + "/test-output/risersTablePage.jpg"));
        test.log(LogStatus.INFO, "Risers table", "<img src=risersTablePage.jpg>");
        fallers.click();
        test.log(LogStatus.INFO, "Click on 'fallers'");
        sleep(2000);
        WebElement freshHeader = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div/div[2]/h1"));
        // Note: ^ this is the exact same header as line 64. But it wants a fresh reference.
        File fallerPic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(fallerPic, new File(System.getProperty("user.dir") + "/test-output/fallersPage.jpg"));
        assertEquals(freshHeader.getText(),"FTSE 100: TOP 20 FALLERS");
        test.log(LogStatus.PASS, "'FTSE 100: TOP 20 FALLERS' is shown in the header", "<img src=fallersPage.jpg>");
        sleep(5000);
        jsScroll();
        WebElement fallerTable = driver.findElement(By.xpath("//*[@id=\"view-constituents\"]/div[2]/table/tbody"));
        String[] fallersArray = fallerTable.getText().split("\n");
        test.log(LogStatus.INFO, "The highest faller is: " + fallersArray[0]);
        File fallersTablePic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(fallersTablePic, new File(System.getProperty("user.dir") + "/test-output/fallersTablePage.jpg"));
        test.log(LogStatus.INFO, "Fallers table", "<img src=fallersTablePage.jpg>");
    }

    @AfterMethod
    public void getResult(ITestResult result){
        driver.close();
        if(result.getStatus() == ITestResult.FAILURE){
            test.log(LogStatus.FAIL, "Test has failed " + result.getName());
            test.log(LogStatus.FAIL, "Test has failed " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, "Test has passed " + result.getName());
        }
        report.endTest(test);
    }

    @AfterTest
    public void endReport(){
        report.flush();
        report.close();
    }
}
