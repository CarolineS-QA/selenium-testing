package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DressTest {
    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
    }

/* Write a test that will search the website for an item of clothing (e.g ‘Dress’) and assert that a particular item was found.
There are multiple ways you can do this, do whatever you feel is most appropriate.
As a bonus task, take the found item through the checkout system. You will need to create an account to complete checkout */
    @Test
    public void selectDress() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        sleep(1000);
        WebElement woman = driver.findElement(By.cssSelector("#block_top_menu > ul > li:nth-child(1) > a"));
        woman.click();
        sleep(2000);
        WebElement dressLink = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a"));
        dressLink.click();
        sleep(2000);
        WebElement dressHeader = driver.findElement(By.cssSelector("#categories_block_left > h2"));
        assertEquals("DRESSES", dressHeader.getText());
        sleep(5000);
        WebElement dressPic = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[5]/div/div[1]/div/a[1]/img"));
        assertTrue(dressPic.isDisplayed());
    }

    @After
    public void tearDown(){
        driver.close();
    }
}


