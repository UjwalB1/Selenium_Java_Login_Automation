package com.ujwal.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class seleniumTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @AfterClass
    public void tearDown() {
        // driver.quit();
    }

    @Test
    public void loggingIntoApplication() throws InterruptedException {
        Thread.sleep(2000);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

        driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in")).click();
        Thread.sleep(2000);
        String actualResult = driver.findElement(By.cssSelector(".icon-2x.icon-signout")).getText();
        String expectedResult = "Logout";
        Assert.assertEquals(actualResult.trim(), expectedResult, "Verification failed: Logout text mismatch");
    }
}
