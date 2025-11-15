package ru.educationServices.stellarburgers.pageObject;

import models.Client;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
//    private static WebDriver driver;
//    private final WebDriverWait wait;

    public static final String URL = MainPage.URL + "account";
    private final WebDriver driver;
    private WebDriverWait wait;

    //раздел Профиль
    @FindBy(how = How.XPATH, using = "//a[@class='Account_link__2ETsJ text text_type_main-medium text_color_inactive Account_link_active__2opc9']")
    private WebElement profileBar;

    //Имя аккаунта
    @FindBy(how = How.XPATH, using = "//input[@name='Name']")
    private WebElement fieldName;

    //Логин (email) аккаунта.
    @FindBy(how = How.XPATH, using = "//label[contains(text(),'Логин')]/following-sibling::input[1]")
    private WebElement fieldEmail;

    //Пароль
    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Пароль')]/following-sibling::input[1]")
    private WebElement fieldPassword;

    //Кнопка Выход
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Выход')]")
    private WebElement buttonLogout;


    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void checkSuccessDataAccount(Client client) {
        wait.until(ExpectedConditions.visibilityOf(profileBar));
        Assertions.assertEquals(client.getName(), fieldName.getAttribute("Value"));
        Assertions.assertEquals(client.getEmail(), fieldEmail.getAttribute("Value"));
        wait.until(ExpectedConditions.visibilityOf(fieldPassword));
    }
}
