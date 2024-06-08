package com.opencart.coupon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class Products extends BasePage {

    public Products(WebDriver driver) {
        super(driver);
    }

    // Elements
    @FindBy(css = "#product-list [class='col mb-3'] .product-thumb h4 > a")
    private List<WebElement> productNames;

    public List<String> getProductNames() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        List<String> names = new ArrayList<>();
        for (WebElement productName : productNames) {
            names.add(productName.getText());
        }
        return names;
    }

    // Method to retrieve list of products
    private List<Product> getProductList(WebDriver driver) {
        HomePageWithNoSetProduct homePage = new HomePageWithNoSetProduct(driver);

        homePage.inputSearchQueryIntoSearchBar();
        homePage.clickOnSearchButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));

        List<Product> productList = new ArrayList<>();
        for (String productName : getProductNames()) {
            productList.add(new Product(productName));
        }
        return productList;
    }

    // Define a method to provide random product test data
    static Stream<Arguments> productTestData(WebDriver driver) {
        Products productsPage = new Products(driver);
        List<Product> productList = productsPage.getProductList(driver);
        Random random = new Random();
        int randomIndex = random.nextInt(productList.size());
        Product randomProduct = productList.get(randomIndex);

        return Stream.of(
                Arguments.of(randomProduct.getName())
        );
    }

    // Product class definition
    private static class Product {
        private String name;

        public Product(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
