package ru.educationServices.stellarburgers.pageObject;

import io.qameta.allure.Step;
import models.Client;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage {
    private static WebDriver driver;
    private final WebDriverWait wait;

    public static final String URL = MainPage.URL + "login";

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Зарегистрироваться')]")
    private WebElement registrationLink;

    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private WebElement fieldEmail;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private WebElement fieldPassword;

    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//h2[contains(text(),'Вход')]")
    private WebElement titlePage;


    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Переход по ссылке регистрации")
    public void ClickRegistrationLink(){
        wait.until(ExpectedConditions.elementToBeClickable(registrationLink));
        registrationLink.click();
    }

    @Step("Проверка корректного перенаправления на главную страницу")
    public void assertRedirectLoginPage(){
        wait.until(ExpectedConditions.visibilityOf(titlePage));
        assertTrue(titlePage.isDisplayed());
    }

    @Step("Авторизация пользователя")
    public void LoginClient(Client client) {
        fieldEmail.sendKeys(client.getEmail());
        fieldPassword.sendKeys(client.getPassword());
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
    }

    @Step("Проверка корректной авторизации клиента")
    public void assertSuccessLoginClient(MainPage mainPage) {
        wait.until(ExpectedConditions.visibilityOf(mainPage.headerPage));
        wait.until(ExpectedConditions.visibilityOf(mainPage.buttonSendOrder));
        assertEquals("Оформить заказ", mainPage.buttonSendOrder.getText());
    }

    @Step("Проверка корректного выхода из система пользователя")
    public void checkSuccessLogout(){
        wait.until(ExpectedConditions.visibilityOf(titlePage));
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals(MainPage.URL+"login", currentURL);
        assertTrue(fieldEmail.isDisplayed());
        assertTrue(fieldPassword.isDisplayed());

    }
}
