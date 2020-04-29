package org.example;

import com.google.common.base.Function;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppTest
{
    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
    }

    @Ignore
    @Test
    public void seleniumExampleTest() throws InterruptedException {
        driver.manage().window().maximize();
        sleep(1000);
        driver.get("http://www.google.co.uk");
        sleep(1000);
        WebElement googleSearchBar = driver.findElement(By.name("q"));
        googleSearchBar.sendKeys("funny dog pics");
        sleep(1000);
        googleSearchBar.submit();
        sleep(1000);
        WebElement linkToPictures =  driver.findElement(By.partialLinkText("Images for funny dog"));
        linkToPictures.click();
        sleep(1000);
        WebElement imagesLink = driver.findElement(By.className("rQEFy"));
        assertTrue(imagesLink.isDisplayed());
    }
    @Ignore
    @Test
    public void exerciseDemoLoginSuccessTest() throws InterruptedException {
        driver.manage().window().maximize();
        sleep(1000);
        driver.get("http://thedemosite.co.uk/addauser.php");
        sleep(1000);
        WebElement userInput = driver.findElement(By.name("username"));
        WebElement passInput = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.name("FormsButton2"));
        userInput.sendKeys("noIdsForMez");
        passInput.sendKeys("lolthisisez");
        sleep(1000);
        button.click();
        sleep(400);
        WebElement toLogin = driver.findElement(By.partialLinkText("Login"));
        toLogin.click();
        sleep(400);
        WebElement userInputLogin = driver.findElement(By.name("username"));
        WebElement passInputLogin = driver.findElement(By.name("password"));
        WebElement buttonLogin = driver.findElement(By.name("FormsButton2"));
        userInputLogin.sendKeys("noIdsForMez");
        passInputLogin.sendKeys("lolthisisez");
        sleep(400);
        buttonLogin.click();
        sleep(2000);
        WebElement loginAttempt = driver.findElement(By.cssSelector("body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b"));
        assertEquals("**Successful Login**", loginAttempt.getText());
    }
// Note: inspect element and get selector to choose elements without identifiers!!
    @Ignore
    @Test
    public void exerciseDemoLoginWrongPasswordTest() throws InterruptedException {
        driver.manage().window().maximize();
        sleep(1000);
        driver.get("http://thedemosite.co.uk/addauser.php");
        sleep(1000);
        WebElement userInput = driver.findElement(By.name("username"));
        WebElement passInput = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.name("FormsButton2"));
        userInput.sendKeys("noIdsForMez");
        passInput.sendKeys("lolthisisez");
        sleep(1000);
        button.click();
        sleep(400);
        WebElement toLogin = driver.findElement(By.partialLinkText("Login"));
        toLogin.click();
        sleep(400);
        WebElement userInputLogin = driver.findElement(By.name("username"));
        WebElement passInputLogin = driver.findElement(By.name("password"));
        WebElement buttonLogin = driver.findElement(By.name("FormsButton2"));
        userInputLogin.sendKeys("noIdsForMez");
        passInputLogin.sendKeys("wrong");
        sleep(400);
        buttonLogin.click();
        sleep(2000);
        WebElement loginAttempt = driver.findElement(By.cssSelector("body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b"));
        assertEquals("**Failed Login**", loginAttempt.getText());
    }

    @Test
    public void explicitWaitExample() {
        driver.get("http://www.google.co.uk");
        WebElement searchBar = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
        assertTrue(searchBar.isDisplayed());
    }

    @Test
    public void fluentWaitExample() {
        driver.get("http://www.google.co.uk");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement searchBar = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.name("q"));
            }
        });
        assertTrue(searchBar.isDisplayed());
    }

    @After
    public void tearDown(){
        driver.close();
    }

}
