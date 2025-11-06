package ru.educationServices.stellarburgers;

//Регистрация
//Проверь:
//Успешную регистрацию.
//Ошибку для некорректного пароля. Минимальный пароль — шесть символов.

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
    public void testRegistrationClient(){
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get(mainPage.URL);
        mainPage.clickProfileButton();
        loginPage.ClikRegistrationLink();
        registrationPage.RegistrationClient(driverExtension.client);


    }

}
