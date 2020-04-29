package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Wait;

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
        sleep(1000);
        WebElement dressLink = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a"));
        dressLink.click();
        sleep(1000);
        WebElement dressHeader = driver.findElement(By.cssSelector("#categories_block_left > h2"));
        assertEquals("DRESSES", dressHeader.getText());
        sleep(1000);
        WebElement dressPic = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[5]/div/div[1]/div/a[1]/img"));
        assertTrue(dressPic.isDisplayed());
        WebElement dressToBuy = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[4]/div/div[2]/h5/a"));
        // // *[@id=\"center_column\"]/ul/li[5]/div/div[1]/div/a[1] - original dress to buy link - goes to view
        // WebElement addToCart = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[7]/div/div[2]/div[2]/a[1]"));
        dressToBuy.click();
        sleep(1000);
        // on the website it redirects but in the test it's just a pop up! //*[@id="add_to_cart"]/button/span
        WebElement addToCartButton = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"add_to_cart\"]/button")));
        assertTrue(addToCartButton.isDisplayed());
        addToCartButton.click();
        sleep(1000);
        WebElement cartPopUp = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.id("layer_cart")));
        assertTrue(cartPopUp.isDisplayed());
        sleep(8000);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}


