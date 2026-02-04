package com.example.automation.steps;

import com.example.automation.configuration.ConfigReader;
import com.example.automation.configuration.DriverFactory;
import com.example.automation.pages.LoginPage;
import com.example.automation.pages.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Quand;
import io.cucumber.java.fr.Soit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    public ConfigReader settings = new ConfigReader();

    @Given("l'utilisateur est sur la page de connexion")
    public void openLogin() {
        String url = settings.getProperty("url");
        loginPage.open(url);
    }

    @When("il saisit le login {string} et le mot de passe {string}")
    public void login(String user, String pass) {
        loginPage.login(user, pass);
    }


    @Then("la connexion est échouée et un message s'affiche")
    public void verifierLoginErreur() {
        // présence de l'elt verif
        boolean isDisplayed = loginPage.afficheErreur();
        Assert.assertTrue("Pas de msg affiché : KO", isDisplayed);
        // texte contient le message d'erreur
        String errorText = loginPage.getErrorMessage();
        Assert.assertTrue("msg d'erreur incorrect", errorText.contains("Epic sadface"));
    }

    @Then("le statut de la connexion devrait être {string}")
    public void etatConnexion(String etat){
        if(etat.equals("success"))
        {
            //
        } else if (etat.equals("failed")) {
            //
            Assert.assertTrue("pas de msg erreur", loginPage.afficheErreur());
        }
    }

    // Initialisation de la nouvelle page
    ProductPage productPage = new ProductPage(driver);

    @When("il ajoute le produit {string} au panier")
    public void ajouterAuPanier(String nomProduit) {
        // click add to cart
        productPage.addBackpackToCart();
    }

    @Then("le bouton du panier affiche {string} article")
    public void verifierPanier(String qte_attendue) {
        String qte = productPage.getCartBadgeCount();
        Assert.assertEquals("KO, nombre article incorrect",
                qte_attendue, qte);
    }

}
