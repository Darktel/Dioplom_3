package ru.educationServices.stellarburgers.pageObject;

import models.Client;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
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

    private final By hederLoginPage = By.xpath("//h2[contains(text(),'Вход')]");

    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private WebElement fieldEmail;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private WebElement fieldPassword;

    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private WebElement loginButton;

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

    public void assertRedirectLoginPage(){
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(hederLoginPage));
        assertTrue(element.isDisplayed());
    }

    public void LoginClient(Client client) {
        fieldEmail.sendKeys(client.getEmail());
        fieldPassword.sendKeys(client.getPassword());
        loginButton.click();
    }

    public void assertSuccessLoginClient(MainPage mainPage) {
        wait.until(ExpectedConditions.visibilityOf(mainPage.headerPage));
        wait.until(ExpectedConditions.visibilityOf(mainPage.buttonSendOrder));
        assertEquals("Оформить заказ", mainPage.buttonSendOrder.getText());
    }
}
