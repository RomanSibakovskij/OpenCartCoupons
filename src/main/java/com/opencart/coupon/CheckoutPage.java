package com.opencart.coupon;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePage{

    @FindBy(xpath = "//*//div[@id='accordion']/div[2]")
    private WebElement couponSelectorDropMenu;

    @FindBy(xpath = "//*//input[@id='input-coupon']")
    private WebElement inputCouponCode;

    @FindBy(xpath = "//form[@id='form-coupon']//button[@type='submit']")
    private WebElement applyCouponButton;

    @FindBy(css = "tfoot#checkout-total > tr:nth-of-type(2) > td:nth-of-type(2)")
    private WebElement couponCodeDiscountValue;

    @FindBy(xpath = "//tfoot[@id='checkout-total']/tr[3]/td[2]")
    private WebElement ecoTax;

    @FindBy(xpath = "//tfoot[@id='checkout-total']/tr[4]/td[2]")
    private WebElement vatTax;

    @FindBy(css = "tfoot#checkout-total > tr:nth-of-type(1) > td:nth-of-type(2)")
    private WebElement subTotalValue;

    @FindBy(css = "tfoot#checkout-total > tr:nth-of-type(5) > td:nth-of-type(2)")
    private WebElement totalValue;

    @FindBy(css = ".alert.alert-danger.alert-dismissible")
    private WebElement errorMessage;

    @FindBy(css = "#alert")
    private WebElement successMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCouponSelectorDropMenu(){
        couponSelectorDropMenu.click();
    }

    public void setInputCouponCodeIntoInputField(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(inputCouponCode));

        // Scroll into view if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", couponCodeDiscountValue);
        inputCouponCode.clear();
        inputCouponCode.sendKeys("1111");
    }

    public void setInputCouponCodeWith10PercentOffIntoInputField(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(inputCouponCode));

        // Scroll into view if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", couponCodeDiscountValue);
        inputCouponCode.clear();
        inputCouponCode.sendKeys("2222");
    }

    public void setInputExpiredCouponCodeIntoInputField(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(inputCouponCode));

        // Scroll into view if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", couponCodeDiscountValue);
        inputCouponCode.clear();
        inputCouponCode.sendKeys("5555");
    }

    public void setInputInvalidCouponCodeIntoInputField(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(inputCouponCode));

        // Scroll into view if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", couponCodeDiscountValue);
        inputCouponCode.clear();
        inputCouponCode.sendKeys("7777");
    }

    public void setEmptyInputCouponCodeIntoInputField(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(inputCouponCode));

        // Scroll into view if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", couponCodeDiscountValue);
        inputCouponCode.clear();
        inputCouponCode.sendKeys("");
    }

    public void clickOnApplyCouponButton(){
        applyCouponButton.click();
    }

    public String getCouponCodeNumberWith10DollarDiscount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
        WebElement couponCodeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*//tfoot[@id='checkout-total']/tr[2]/td[1]/strong[.='Coupon (1111)']")));
        System.out.println("Coupon code element found. Text: " + couponCodeElement.getText());
        return couponCodeElement.getText();
    }

    public String getCouponCodeNumberWith10PercDiscount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
        WebElement couponCodeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*//tfoot[@id='checkout-total']/tr[2]/td[1]/strong[.='Coupon (2222)']")));
        System.out.println("Coupon code element found. Text: " + couponCodeElement.getText());
        return couponCodeElement.getText();
    }

    public String getCouponCodeDiscountValue(){
        //wait for the annoying alert to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".alert.alert-success.alert-dismissible")));

        return couponCodeDiscountValue.getText();
    }

    public String getEcoTax(){
        //wait for the annoying alert to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".alert.alert-success.alert-dismissible")));

        return ecoTax.getText();
    }

    public String getVatTax(){
        //wait for the annoying alert to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".alert.alert-success.alert-dismissible")));

        return vatTax.getText();
    }

    public String getSubTotalValue() {
        return subTotalValue.getText();
    }

    public String getTotalValue() {
        return totalValue.getText();
    }
    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.getText();
    }
}
