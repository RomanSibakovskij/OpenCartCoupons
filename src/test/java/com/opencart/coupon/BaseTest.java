package com.opencart.coupon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    WebDriver driver;


    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://192.168.8.191/");
    }




    //close
    @AfterEach
    void close() throws InterruptedException {
        Thread.sleep(5500);
        driver.quit();
    }

}
