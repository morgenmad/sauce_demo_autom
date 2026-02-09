package com.example.automation.configuration;

import com.example.automation.steps.importResultsToXray;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class Hooks {

    public static importResultsToXray importResultsToXray = new importResultsToXray();

    WebDriver driver = DriverFactory.getDriver();

    // trouver un moyen pour dl le jira pour avoir les features avant de lancer les tests : donc beforeall junit ?

    @Before
    public void setUp() {
        DriverFactory.getDriver();
    }


    @After //after cucumber
    public void tearDown(Scenario scenario) throws IOException{
        // prendre un screenshot lorsque le test est failed -> preuve de test
        // va dans target
        if(scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File("target/screenshots/"+scenario.getName()+".png");
            FileUtils.copyFile(source, destination);
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }

        // tant que cucumber n'est pas terminé on n'aura pas le .json
        DriverFactory.quitDriver();
    }

    @AfterClass
     public static void toXray() throws IOException, NoSuchAlgorithmException, KeyStoreException, InterruptedException, KeyManagementException {
        // appeler remonterToXray
        // importResultsToXray.remonterVersXray();
        System.out.println("je suis passé par là");
    }
}
