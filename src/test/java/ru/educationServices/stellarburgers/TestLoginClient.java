package ru.educationServices.stellarburgers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import ru.educationServices.stellarburgers.pageObject.AccountPage;
import ru.educationServices.stellarburgers.pageObject.LoginPage;
import ru.educationServices.stellarburgers.pageObject.MainPage;
import ru.educationServices.stellarburgers.pageObject.ForgotPasswordPage;
import ru.educationServices.stellarburgers.pageObject.RegistrationPage;

public class TestLoginClient {

    @RegisterExtension
    private DriverExtension driverExtension = new DriverExtension();

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
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        driverExtension.apiClient.createClient(driverExtension.client);
        driver.get(AccountPage.URL); // для не авторизованных пользователей выполняется редирект на /login
        mainPage.clickProfileButton();
        loginPage.LoginClient(driverExtension.client);
        loginPage.assertSuccessLoginClient(mainPage);
    }


    @Test
    @DisplayName("Авторизация пользователя через форму регистрации")
    public void TestLoginClientFromRegistrationPage() {
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

    @Test
    @DisplayName("Авторизация пользователя через вход с формы восстановления пароля")
    public void TestLoginClientFromForgotPassword() {
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        driverExtension.apiClient.createClient(driverExtension.client);
        driver.get(ForgotPasswordPage.URL);
        forgotPasswordPage.ClickSignInLink();
        loginPage.LoginClient(driverExtension.client);
        loginPage.assertSuccessLoginClient(mainPage);
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет после авторизации и корректности отображаемых там данных пользователя")
    public void TestClickAccountProfile(){
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        driverExtension.apiClient.createClient(driverExtension.client);
        driver.get(AccountPage.URL); // для не авторизованных пользователей выполняется редирект на /login
        mainPage.clickProfileButton();
        loginPage.LoginClient(driverExtension.client);
        loginPage.assertSuccessLoginClient(mainPage);
        mainPage.clickProfileButton();
        accountPage.checkSuccessDataAccount(driverExtension.client);
    }


}
