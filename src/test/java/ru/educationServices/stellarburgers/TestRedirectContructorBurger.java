package ru.educationServices.stellarburgers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import ru.educationServices.stellarburgers.pageObject.AccountPage;
import ru.educationServices.stellarburgers.pageObject.LoginPage;
import ru.educationServices.stellarburgers.pageObject.MainPage;

public class TestRedirectContructorBurger {

    @RegisterExtension
    private DriverExtension driverExtension = new DriverExtension();


    @Test
    @DisplayName("Проверка после логина перехода на MinePage при клике по лого")
    public void TestLogOutClientProfile(){
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        driverExtension.apiClient.createClient(driverExtension.client);
        driver.get(LoginPage.URL);
        loginPage.LoginClient(driverExtension.client);
        loginPage.assertSuccessLoginClient(mainPage);
        mainPage.clickProfileButton();
        accountPage.clickLogo();
        mainPage.checkCorrectElementMainPage();
    }
}
