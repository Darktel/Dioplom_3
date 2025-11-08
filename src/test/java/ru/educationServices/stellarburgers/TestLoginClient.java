package ru.educationServices.stellarburgers;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import ru.educationServices.stellarburgers.pageObject.LoginPage;
import ru.educationServices.stellarburgers.pageObject.MainPage;
import ru.educationServices.stellarburgers.pageObject.RegistrationPage;

public class TestLoginClient {

    @RegisterExtension
    private DriverExtension driverExtension = new DriverExtension();

//    @BeforeEach
//    public void setup(){
//
//    }

    @Test
    @DisplayName("Авторизация пользователя по кнопке «Войти в аккаунт» на главной")
    public void TestLoginFromMinePage() {
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        driver.get(mainPage.URL);
        driverExtension.apiClient.createClient(driverExtension.client);
        mainPage.clickLoginButton();
        loginPage.LoginClient(driverExtension.client);
        loginPage.assertSuccessLoginClient(mainPage);
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку «Личный кабинет»")
    public void TestLoginClientFromLoginPage() {
        //TODO переделать на отдельную страницу https://stellarburgers.education-services.ru/account (Создав на нее pageObject)
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        driver.get(mainPage.URL);
        driverExtension.apiClient.createClient(driverExtension.client);
        mainPage.clickProfileButton();
        loginPage.LoginClient(driverExtension.client);
        loginPage.assertSuccessLoginClient(mainPage);

    }
    //вход через кнопку в форме регистрации,
    @Test
    @DisplayName("Авторизация пользователя ")
    public void Test() {
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        driverExtension.apiClient.createClient(driverExtension.client);
        driver.get(registrationPage.URL);
        registrationPage.clickSignInLink();
        loginPage.LoginClient(driverExtension.client);
        loginPage.assertSuccessLoginClient(mainPage);
    }

    //вход через кнопку в форме восстановления пароля.
    @Test
    @DisplayName("Авторизация пользователя ")
    public void Test2() {

    }

}
