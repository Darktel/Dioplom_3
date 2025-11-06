package ru.educationServices.stellarburgers.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static WebDriver driver;
    private WebDriverWait wait;

    public static final String URL = MainPage.URL + "login";

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Зарегистрироваться')]")
    private WebElement registrationLink;

    public LoginPage(){
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void ClikRegistrationLink(){
        registrationLink.click();
    }
}
