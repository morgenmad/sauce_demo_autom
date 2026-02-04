package com.example.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    public WebDriver driver;
    @FindBy(xpath= "//button[text()='Add to cart']")
    WebElement addToCart;
    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    WebElement notifcart;
    @FindBy(id="remove-sauce-labs-backpack")
    WebElement removeToCart;
    @FindBy(id="shopping_cart_container")
    WebElement cartIcon;
    @FindBy(id="checkout")
    WebElement checkout;

    // constructeur
    public CartPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    // methodes
/*    public void addToCart(){
/*        addToCart.click();
/*    }*/
    public String verifBadge(){
        return notifcart.getText();
    }
    public void removeToCart(){
        removeToCart.click();
    }
/*    public void accessToCart(){
/*        cartIcon.click();
/*    }*/

    public void clickCheckOut(){
        checkout.click();
    }
}
