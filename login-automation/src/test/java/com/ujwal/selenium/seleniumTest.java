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
        driver.quit();
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
        String actualResultP = driver.findElement(By.cssSelector(".icon-2x.icon-signout")).getText();
        String expectedResultP = "Logout";
        Assert.assertEquals(actualResultP.trim(), expectedResultP, "Verification failed: Logout text don't match twin");
    }


    @Test
    public void invalidLoginApplication() throws InterruptedException {
        Thread.sleep(2000);

        WebElement invalidUsername = driver.findElement(By.id("username"));
        invalidUsername.sendKeys("DeffoNotNoTomSmithBroNotGonnaLie");

        WebElement invalidPassword = driver.findElement(By.id("password"));
        invalidPassword.sendKeys("AlsoDeffoTheWrongPasswordNotGonnaLie");

        driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in")).click();
        Thread.sleep(2000);
        
        String actualResultF = driver.findElement(By.id("flash")).getText();
        String expectedResultF = "Your username is invalid!";
        Assert.assertTrue(actualResultF.contains(expectedResultF), "Failure test failed: Soo uh yea no error message shown");



    }

}
