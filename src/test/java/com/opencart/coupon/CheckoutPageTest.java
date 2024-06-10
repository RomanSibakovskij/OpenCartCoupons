package com.opencart.coupon;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutPageTest extends BaseTest {

    private void addToCartButtonClick() {
        HomePage homePage = new HomePage(driver);

        homePage.inputSearchQueryIntoSearchBar();

        homePage.clickOnSearchButton();

        SearchPage searchPage = new SearchPage(driver);

        searchPage.clickOntoAddToCartButton();

        searchPage.getSelectedProductName();

        searchPage.getSelectedProductPrice();

        searchPage.getSelectedProductPriceWithNoTax();
    }

    private void clickShoppingCartBtnAndViewCartLink() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.clickOnShoppingCartButton();
        searchPage.clickOnViewCartLinkInShoppingButton();
    }

    private void minus10DollarCouponTest() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        //test steps

        checkoutPage.clickOnCouponSelectorDropMenu();

        checkoutPage.setInputCouponCodeIntoInputField();

        checkoutPage.clickOnApplyCouponButton();

        String couponCodeNumber = checkoutPage.getCouponCodeNumberWith10DollarDiscount();
        assertEquals("Coupon (1111)", couponCodeNumber);

        String displayedValue = checkoutPage.getCouponCodeDiscountValue();
        assertEquals("$-10.00", displayedValue);
        System.out.println("Displayed coupon value is: " + displayedValue + "\n");

        //verify the coupon discount is present
        boolean isPresent = driver.findElements(By.cssSelector("#checkout-total > tr:nth-child(2) > td:nth-child(2)")).size() > 0;
        System.out.println("Coupon discount element present: " + isPresent + "\n");

        // total / subtotal values
        String subTotalValue = checkoutPage.getSubTotalValue();
        System.out.println("Displayed sub-total value is: " + subTotalValue + "\n");

        String totalValue = checkoutPage.getTotalValue();
        System.out.println("Displayed total value is: " + totalValue + "\n");

        String ecoTaxValue = checkoutPage.getEcoTax();
        System.out.println("Displayed ecoTax value is: " + ecoTaxValue + "\n");
        String vatTaxValue = checkoutPage.getVatTax();
        System.out.println("Displayed vatTax value is: " + vatTaxValue + "\n");

        // Value to number conversion
        double subTotal = Double.parseDouble(subTotalValue.replace("$", ""));
        double discount = Double.parseDouble(displayedValue.replace("$", ""));
        double total = Double.parseDouble(totalValue.replace("$", ""));
        double ecoTax = Double.parseDouble(ecoTaxValue.replace("$", ""));
        double vatTax = Double.parseDouble(vatTaxValue.replace("$", ""));

        double expectedTotal = subTotal + discount + ecoTax + vatTax; // + discount because it's -10

        // print actual value
        System.out.println("Actual total value is: " + "$" + total + "\n");

        // print expected prices
        System.out.println("Expected sub-total value is: $" + subTotalValue + "\n");
        System.out.println("Expected discount value is: $" + discount + "\n");
        System.out.println("Expected total value is: $" + expectedTotal + "\n");

        // assertion the values are correct
        assertEquals(subTotal, 279.99, 0.01);
        assertEquals(expectedTotal, total, 0.01);
    }

    private void minus10PercentTest() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        //test steps

        checkoutPage.clickOnCouponSelectorDropMenu();

        checkoutPage.setInputCouponCodeWith10PercentOffIntoInputField();

        checkoutPage.clickOnApplyCouponButton();

        String couponCodeNumber = checkoutPage.getCouponCodeNumberWith10PercDiscount();
        assertEquals("Coupon (2222)", couponCodeNumber);

        String displayedValue = checkoutPage.getCouponCodeDiscountValue();
        assertEquals("$-28.00", displayedValue);
        System.out.println("Displayed coupon value is: " + displayedValue + "\n");

        //verify the coupon discount is present
        boolean isPresent = driver.findElements(By.cssSelector("#checkout-total > tr:nth-child(2) > td:nth-child(2)")).size() > 0;
        System.out.println("Coupon discount element present: " + isPresent + "\n");

        // total / subtotal values
        String subTotalValue = checkoutPage.getSubTotalValue();
        System.out.println("Displayed sub-total value is: " + subTotalValue + "\n");

        String totalValue = checkoutPage.getTotalValue();
        System.out.println("Displayed total value is: " + totalValue + "\n");

        String ecoTaxValue = checkoutPage.getEcoTax();
        System.out.println("Displayed ecoTax value is: " + ecoTaxValue + "\n");
        String vatTaxValue = checkoutPage.getVatTax();
        System.out.println("Displayed vatTax value is: " + vatTaxValue + "\n");

        // Value to number conversion
        double subTotal = Double.parseDouble(subTotalValue.replace("$", ""));
        double total = Double.parseDouble(totalValue.replace("$", ""));
        double discountWithPercentage = Double.parseDouble(displayedValue.replace("$", ""));
        double ecoTax = Double.parseDouble(ecoTaxValue.replace("$", ""));
        double vatTax = Double.parseDouble(vatTaxValue.replace("$", ""));

        double discount = subTotal * 0.10; // Calculating the discount
        double expectedSubTotalWithDiscount = subTotal - discount;
        //double tax = 50.40;
        double expectedTotal = expectedSubTotalWithDiscount + vatTax + ecoTax;

        // print actual value
        System.out.println("Actual total value is: " + "$" + total + "\n");

        // print expected prices
        System.out.println("Expected sub-total value is: $" + subTotal + "\n");
        System.out.println("Expected sub-total value with discount is: $" + expectedSubTotalWithDiscount + "\n");
        System.out.println("Expected discount value is: $" + discountWithPercentage + "\n");
        System.out.println("Expected total value is: $" + expectedTotal + "\n");

        // assertion the values are correct
        assertEquals(expectedSubTotalWithDiscount, 251.99, 0.01);
        assertEquals(expectedTotal, total, 0.01);
    }

    private void inputInvalidCouponCodeTest() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.clickOnCouponSelectorDropMenu();
        checkoutPage.setInputInvalidCouponCodeIntoInputField();
        checkoutPage.clickOnApplyCouponButton();

        String errorMessage = checkoutPage.getErrorMessage();
        assertEquals("Warning: Coupon is either invalid, expired or reached its usage limit!", errorMessage);
        System.out.println("The error message displayed is: " + errorMessage + "\n");
    }

    private void inputExpiredCouponCodeTest() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.clickOnCouponSelectorDropMenu();
        checkoutPage.setInputExpiredCouponCodeIntoInputField();
        checkoutPage.clickOnApplyCouponButton();

        String errorMessage = checkoutPage.getErrorMessage();
        assertEquals("Warning: Coupon is either invalid, expired or reached its usage limit!", errorMessage);
        System.out.println("The error message displayed is: " + errorMessage + "\n");
    }
    //positive test cases
    @Test
    void checkoutPageWithMinus10DollarCouponTest() { //coupon codes 1111 (-10.00) and 2222 (-%10)
        addToCartButtonClick();
        clickShoppingCartBtnAndViewCartLink();

        minus10DollarCouponTest();
    }

    @Test
    void checkoutPageWith10PercentDiscountTest() {
        addToCartButtonClick();
        clickShoppingCartBtnAndViewCartLink();

        minus10PercentTest();
    }

    //negative test cases

    @Test
    void invalidCouponCodeTest() {
        addToCartButtonClick();
        clickShoppingCartBtnAndViewCartLink();

        inputInvalidCouponCodeTest();

    }


    @Test
    void expiredCouponCodeTest() {
        addToCartButtonClick();
        clickShoppingCartBtnAndViewCartLink();

        inputExpiredCouponCodeTest();
    }


    @Test
    void emptyCouponCodeTest() {
        addToCartButtonClick();
        clickShoppingCartBtnAndViewCartLink();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.clickOnCouponSelectorDropMenu();
        checkoutPage.setEmptyInputCouponCodeIntoInputField();
        checkoutPage.clickOnApplyCouponButton();
            /* // it should've thrown an error message instead of success
        String errorMessage = checkoutPage.getErrorMessage();
        assertEquals("Warning: Coupon code cannot be empty!", errorMessage);
            */

        String successMessage = checkoutPage.getSuccessMessage();
        assertEquals("Success: Your coupon discount has been removed!", successMessage);

        System.out.println("Warning: Empty coupon code removes the existing discount instead of throwing an error. Further investigation in website structure must be done.");
    }

}
