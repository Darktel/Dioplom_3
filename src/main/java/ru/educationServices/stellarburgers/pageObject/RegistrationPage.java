package ru.educationServices.stellarburgers.pageObject;

import models.Client;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private static WebDriver driver;
    private WebDriverWait wait;

    public static final String URL = MainPage.URL + "register";

    @FindBy(how = How.XPATH, using = "//label[text()='Имя']//following-sibling::input")
    private WebElement inputNameField;


    @FindBy(how = How.XPATH, using = "//label[text()='Email']//following-sibling::input")
    private WebElement inputEmailField;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private WebElement inputPasswordField;

    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private WebElement registrationButton;

    public RegistrationPage(){
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    }

    public void RegistrationClient(Client client){
        inputNameField.sendKeys(client.getName());
        inputEmailField.sendKeys(client.getEmail());
        inputPasswordField.sendKeys(client.getPassword());
        registrationButton.click();
    }


}
