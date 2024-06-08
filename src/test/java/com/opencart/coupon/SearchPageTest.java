package com.opencart.coupon;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchPageTest extends BaseTest{


    private void addToCartButtonClick() {
        HomePage homePage = new HomePage(driver);

        homePage.inputSearchQueryIntoSearchBar();
        System.out.println("The search query was input");

        homePage.clickOnSearchButton();
        System.out.println("The button was clicked");

        SearchPage searchPage = new SearchPage(driver);

        searchPage.clickOntoAddToCartButton();
        System.out.println("The add to cart button was clicked");

        searchPage.getSelectedProductName();
        // assertion the selected product name is displayed and matches the search query
        assertEquals("Palm Treo Pro", searchPage.getSelectedProductName());
        assertNotNull(searchPage.getSelectedProductName());
        System.out.println("The output product name is: " + searchPage.getSelectedProductName());

        searchPage.getSelectedProductPrice();

        // assertion the selected product price is displayed and matches the visually expected price
        String expectedPrice = searchPage.getSelectedProductPrice();
        assertEquals("$337.99", expectedPrice); // -> different price than in the same page opened manually in browser(w/tax)
        System.out.println("The output product price is: " + expectedPrice);

        searchPage.getSelectedProductPriceWithNoTax();

        // assertion the selected product price(w/o tax) is displayed and matches the visually expected price
        String expectedNoTaxPrice = searchPage.getSelectedProductPriceWithNoTax();
        assertEquals("$279.99", expectedNoTaxPrice);
        System.out.println("The output product price w/o tax: " + expectedNoTaxPrice);

    }

    private void clickShoppingCartBtnAndViewCartLink() {
        SearchPage searchPage = new SearchPage(driver);

        addToCartButtonClick();

        searchPage.clickOnShoppingCartButton();
        System.out.println("The button was clicked");

        searchPage.clickOnViewCartLinkInShoppingButton();
        System.out.println("The view cart link was clicked");
    }
    @Test
    void clickAddToCartButtonTest(){
        addToCartButtonClick();
    }

    @Test
    void clickOnShoppingCartButtonTest(){
        clickShoppingCartBtnAndViewCartLink();

    }
}


