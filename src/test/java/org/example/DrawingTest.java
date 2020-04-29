package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import static java.lang.Thread.sleep;

public class DrawingTest {
    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
    }

    @Test
    public void drawHouse() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.youidraw.com/apps/painter/");
        sleep(1000);
        Actions action = new Actions(driver);
        action.moveByOffset(500,300).clickAndHold().moveByOffset(300,0).moveByOffset(0,300).moveByOffset(-300,0).moveByOffset(0,-300).perform();
        action.clickAndHold().moveByOffset(150,-150).moveByOffset(150, 150).perform();
        sleep(1000);
        action.release().moveByOffset(-140, 300).clickAndHold().moveByOffset(0, -80).moveByOffset(-50, 0).moveByOffset(0,80).perform();
        sleep(4000);
    }


    @After
    public void tearDown(){
        driver.close();
    }
}
