package ru.educationServices.stellarburgers.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private static WebDriver driver;
    private WebDriverWait wait;

    public static final String URL = "https://stellarburgers.education-services.ru/";

    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Личный Кабинет')]")
    private WebElement buttonProfileLink;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Зарегистрироваться')]")
    private WebElement buttonSignUp;

// Страница логина
    @FindBy(how = How.XPATH, using = "//div[@class='input pr-6 pl-6 input_type_text input_size_default input_status_active']//input[@name='name']")
    private WebElement inputName;

    @FindBy(how = How.XPATH, using = "//div[@class='input pr-6 pl-6 input_type_text input_size_default input_status_active']//input[@name='name']")
    private WebElement inputEmail;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private WebElement inputPassword;

    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private WebElement buttonRegistration;

    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")
    private WebElement buttonSignUpMiddle;

    @FindBy(how = How.XPATH, using = "//h1[@class='text text_type_main-large mb-5 mt-10']")
    public WebElement headerPage;

    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")
    public WebElement buttonSendOrder;

//    @FindBy(how = How.XPATH, using = "")


    public MainPage(){
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public MainPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    }

    public void clickProfileButton(){
        buttonProfileLink.click();
    }

    public void clickLoginButton(){
        wait.until(ExpectedConditions.visibilityOf(buttonSignUpMiddle));
        buttonSignUpMiddle.click();
    }

}
