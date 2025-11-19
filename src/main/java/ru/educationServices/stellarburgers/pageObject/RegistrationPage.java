package ru.educationServices.stellarburgers.pageObject;

import io.qameta.allure.Step;
import models.Client;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.Assertions.*;

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

    @FindBy(how = How.XPATH, using = "//p[@class='input__error text_type_main-default']")
    private WebElement errorMessageWrongPassword;

    @FindBy(how = How.XPATH, using = "//a[@class='Auth_link__1fOlj']")
    private WebElement signInLink;


    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    }

    @Step("Заполнение полей и регистрация клиента")
    public void RegistrationClient(Client client){
        inputNameField.sendKeys(client.getName());
        inputEmailField.sendKeys(client.getEmail());
        inputPasswordField.sendKeys(client.getPassword());
        registrationButton.click();
    }

    @Step("Проверка появления ошибки при не верно введенном пароле")
    public void AssertDisplayMessage(){
        wait.until(ExpectedConditions.visibilityOf(errorMessageWrongPassword));
        // Как только элемент стал видимым, возвращаем его текст
        Assertions.assertEquals("Некорректный пароль", errorMessageWrongPassword.getText());
    }

    @Step("Переход по ссылке авторизации")
    public void clickSignInLink() {
        wait.until(ExpectedConditions.visibilityOf(signInLink));
        signInLink.click();
    }
}
