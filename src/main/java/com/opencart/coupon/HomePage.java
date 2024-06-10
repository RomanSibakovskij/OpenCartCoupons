package com.opencart.coupon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    //elements
    @FindBy(xpath = "//*//input[@name='search']")
    private WebElement searchBarInput;

    @FindBy(xpath = "//*//button[@type='button']")
    private WebElement searchButtonClick;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void inputSearchQueryIntoSearchBar(){
        searchBarInput.sendKeys("Palm Treo Pro");
    }

    public void inputNonExistentSearchQueryIntoSearchBar(){searchBarInput.sendKeys("Kalm");}

    public void clickOnSearchButton(){
        searchButtonClick.click();
    }






}
