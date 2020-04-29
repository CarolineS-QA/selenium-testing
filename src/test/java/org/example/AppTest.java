package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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

    @After
    public void tearDown(){
        driver.close();
    }

}
