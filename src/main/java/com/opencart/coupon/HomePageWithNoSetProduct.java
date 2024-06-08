package com.opencart.coupon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HomePageWithNoSetProduct extends BasePage {

    // elements
    @FindBy(xpath = "//*//input[@name='search']")
    private WebElement searchBarInput;

    @FindBy(xpath = "//*//button[@type='button']")
    private WebElement searchButtonClick;

    // A list of random search queries
    private static final List<String> RANDOM_QUERIES = Arrays.asList(
            "hp" , "iPod", "macbook", "HTC", "samsung", "sony", "apple", "canon", "nikon"
    );

    public HomePageWithNoSetProduct(WebDriver driver) {
        super(driver);
    }

    public void inputSearchQueryIntoSearchBar() {
        String randomQuery = getRandomSearchQuery();
        searchBarInput.sendKeys(randomQuery);
    }

    public void clickOnSearchButton() {
        searchButtonClick.click();
    }

    private String getRandomSearchQuery() {
        Random random = new Random();
        int index = random.nextInt(RANDOM_QUERIES.size());
        return RANDOM_QUERIES.get(index);
    }
}
