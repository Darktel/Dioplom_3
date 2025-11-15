package ru.educationServices.stellarburgers.pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private static WebDriver driver;
    private final WebDriverWait wait;

    public static String URL = MainPage.URL + "forgot-password";

    @FindBy(how = How.XPATH, using = ("//a[@class='Auth_link__1fOlj']"))
    private WebElement signInLink;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void ClickSignInLink(){
        signInLink.click();
    }
}
