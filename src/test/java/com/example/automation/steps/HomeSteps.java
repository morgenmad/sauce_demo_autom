package com.example.automation.steps;
import com.example.automation.configuration.ConfigReader;
import com.example.automation.pages.LoginPage;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import com.example.automation.configuration.DriverFactory;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class HomeSteps {
    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    ConfigReader settings = new ConfigReader();

    @Given("l'utilisateur est connecté à l'application SauceDemo")
    public void checkHomePage(){
        loginPage.open(settings.getProperty("url"));
        loginPage.login("standard_user", "secret_sauce");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("KO : utilisateur pas sur la page d'accueil",
                currentUrl.contains("inventory.html"));
    }
}
