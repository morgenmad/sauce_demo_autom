package com.example.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    // bouton add to cart backpack
    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    WebElement addToCartButton;
    // icone panier
    @FindBy(css = "a.shopping_cart_link")
    WebElement cartIcon;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addBackpackToCart() {
        addToCartButton.click();
    }

    public String getCartBadgeCount() {
        return cartIcon.getText();
    }
}