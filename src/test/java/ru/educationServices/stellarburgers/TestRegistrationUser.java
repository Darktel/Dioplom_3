package ru.educationServices.stellarburgers;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import ru.educationServices.stellarburgers.pageObject.LoginPage;
import ru.educationServices.stellarburgers.pageObject.MainPage;
import ru.educationServices.stellarburgers.pageObject.RegistrationPage;

public class TestRegistrationUser {


    @RegisterExtension
    private DriverExtension driverExtension = new DriverExtension();




    @Test
    @DisplayName("Регистрация нового пользователя")
    public void TestRegistrationClient(){
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get(mainPage.URL);
        mainPage.clickProfileButton();
        loginPage.ClickRegistrationLink();
        registrationPage.RegistrationClient(driverExtension.client);
        //Проверка редиректа на страницу логина, после регистрации.
        loginPage.assertRedirectLoginPage();
        // проверка через api что пользователь появился в системе
        Response response = driverExtension.apiClient.loginClient(driverExtension.client);
        driverExtension.checkClient.checkSuccessLoginUser(response, driverExtension.client);
    }

    @Test
    @DisplayName("Регистрация клиент с паролем менее 6 символов")
    public void TestRegistrationClientWrongPassword(){
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get(mainPage.URL);
        mainPage.clickLoginButton();
        loginPage.ClickRegistrationLink();
        registrationPage.RegistrationClient(driverExtension.brokenClient);
        //Проверка появления сообщения с ошибкой "Неверный пароль".
        registrationPage.AssertDisplayMessage();
        // проверка через api что пользователь не появился в системе
        Response response = driverExtension.apiClient.loginClient(driverExtension.brokenClient);
        driverExtension.checkClient.checkErrorLoginClient(response);
    }
}
