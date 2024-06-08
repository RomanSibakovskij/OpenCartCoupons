package com.opencart.coupon;

import org.junit.jupiter.api.Test;

public class HomePageTest extends BaseTest {

    @Test
    void inputSearchQueryTest(){
        HomePage homePage = new HomePage(driver);

        homePage.inputSearchQueryIntoSearchBar();
        System.out.println("The search query was input");

        homePage.clickOnSearchButton();
        System.out.println("The button was clicked");
    }

}