package com.opencart.coupon;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchPageTest extends BaseTest{


    private void addToCartButtonClick() {
        HomePage homePage = new HomePage(driver);

        homePage.inputSearchQueryIntoSearchBar();
        System.out.println("The search query was input" + "\n");

        homePage.clickOnSearchButton();
        System.out.println("The button was clicked" + "\n");

        SearchPage searchPage = new SearchPage(driver);
        //assertion the 'add to cart' button is present
        assertTrue(searchPage.isAddToCartButtonPresent(), "Add to cart button is not present on the page." + "\n");

        searchPage.clickOntoAddToCartButton();
        System.out.println("The add to cart button was clicked" + "\n");

        searchPage.getSelectedProductName();
        // assertion the selected product name is displayed and matches the search query
        assertEquals("Palm Treo Pro", searchPage.getSelectedProductName());
        assertNotNull(searchPage.getSelectedProductName());
        System.out.println("The output product name is: " + searchPage.getSelectedProductName() + "\n");

        searchPage.getSelectedProductPrice();

        // assertion the selected product price is displayed and matches the visually expected price
        String expectedPrice = searchPage.getSelectedProductPrice();
        assertEquals("$337.99", expectedPrice); // -> different price than in the same page opened manually in browser(w/tax)
        System.out.println("The output product price is: " + expectedPrice + "\n");

        searchPage.getSelectedProductPriceWithNoTax();

        // assertion the selected product price(w/o tax) is displayed and matches the visually expected price
        String expectedNoTaxPrice = searchPage.getSelectedProductPriceWithNoTax();
        assertEquals("$279.99", expectedNoTaxPrice);
        System.out.println("The output product price w/o tax: " + expectedNoTaxPrice + "\n");

    }

    private void clickShoppingCartBtnAndViewCartLink() {
        SearchPage searchPage = new SearchPage(driver);

        addToCartButtonClick();

        searchPage.clickOnShoppingCartButton();
        System.out.println("The button was clicked" + "\n");

        searchPage.clickOnViewCartLinkInShoppingButton();
        System.out.println("The view cart link was clicked" + "\n");
    }
    private void nonFunctionalShoppingCartButton() {
        HomePage homePage = new HomePage(driver);

        homePage.inputSearchQueryIntoSearchBar();
        homePage.clickOnSearchButton();

        SearchPage searchPage = new SearchPage(driver);
        searchPage.clickOntoAddToCartButton();
        searchPage.simulateBrokenButton();
        System.out.println("The button was clicked" + "\n");
    }
    private void outOfStockSimulation() {
        HomePage homePage = new HomePage(driver);

        homePage.inputSearchQueryIntoSearchBar();
        homePage.clickOnSearchButton();

        SearchPage searchPage = new SearchPage(driver);
        searchPage.simulateOutOfStock();
        System.out.println("The 'add to cart' was clicked" + "\n");
    }
    private void nonExistingProductSearch() {
        HomePage homePage = new HomePage(driver);

        homePage.inputNonExistentSearchQueryIntoSearchBar();
        System.out.println("The search (for non-existent product) query was input" + "\n");

        homePage.clickOnSearchButton();
        System.out.println("The button was clicked" + "\n");

        SearchPage searchPage = new SearchPage(driver);

        //assertTrue(true, searchPage.isNoResultsMessageDisplayed());

        String noResultMessage = searchPage.noResultMessageContent();
        assertEquals("There is no product that matches the search criteria.", noResultMessage);
        System.out.println("The message displayed is: " + noResultMessage + "\n");
    }

    //positive test cases
    @Test
    void clickAddToCartButtonTest(){
        addToCartButtonClick();
    }

    @Test
    void clickOnShoppingCartButtonTest(){
        clickShoppingCartBtnAndViewCartLink();

    }
    //negative test cases
    @Test
    void nonExistingProductNameAddToCartTest(){

        nonExistingProductSearch();
    }

    @Test
    void outOfStockSimulationTest(){
        outOfStockSimulation();
    }

    @Test
    void nonFunctionalShoppingCartButtonTest(){
        nonFunctionalShoppingCartButton();
    }

}


