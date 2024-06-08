package com.opencart.coupon;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SearchPageWithRandomProductsTest extends BaseTest {

    // Define a method to provide random product test data
        /*
    static Stream<Arguments> productTestData() {
        return Products.productTestData(driver);
    }
    */
    private void addToCartButtonClick(String expectedProductName, String expectedProductPrice, String expectedProductPriceNoTax) {
        HomePageWithNoSetProduct homePage = new HomePageWithNoSetProduct(driver);

        homePage.inputSearchQueryIntoSearchBar();
        System.out.println("The search query was input");

        homePage.clickOnSearchButton();
        System.out.println("The button was clicked");

        SearchPageWithRandomProducts searchPageWithRandomProducts = new SearchPageWithRandomProducts(driver);

        String actualProductName = searchPageWithRandomProducts.getSelectedProductName();
        assertEquals(expectedProductName, actualProductName);
        String actualProductPrice = searchPageWithRandomProducts.getSelectedProductPrice();
        assertEquals(expectedProductPrice, actualProductPrice);
        String actualProductPriceNoTax = searchPageWithRandomProducts.getSelectedProductPriceWithNoTax();
        assertEquals(expectedProductPriceNoTax, actualProductPriceNoTax);
    }

    //@ParameterizedTest
    @MethodSource("productTestData")
    void clickAddToCartButtonTest(String expectedProductName, String expectedProductPrice, String expectedProductPriceNoTax) {
        addToCartButtonClick(expectedProductName, expectedProductPrice, expectedProductPriceNoTax);
    }


}
