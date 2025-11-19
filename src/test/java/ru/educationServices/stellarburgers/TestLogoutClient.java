package ru.educationServices.stellarburgers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import ru.educationServices.stellarburgers.pageObject.*;

public class TestLogoutClient {


    @RegisterExtension
    private final DriverExtension driverExtension = new DriverExtension();


    @Test
    @DisplayName("Проверка выхода пользователя из профиля")
    public void TestLogOutClientProfile(){
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
        accountPage.ckickButtonExit();
        loginPage.checkSuccessLogout();
    }

}
