package ru.educationServices.stellarburgers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import ru.educationServices.stellarburgers.pageObject.MainPage;

public class TestSectionNavigationIngredient {


    @RegisterExtension
    private DriverExtension driverExtension = new DriverExtension();

    @Test
    @DisplayName("Проверка корректности перехода к секции Начинки")
    public void testSectionNavigationFilling(){
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        driver.get(MainPage.URL);
        mainPage.checkCorrectElementMainPage()
                .clickFillingSection()
                .checkNavigationSectionFilling();
    }

    @Test
    @DisplayName("Проверка корректности перехода к секции Соусы")
    public void testSectionNavigationSauces(){
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        driver.get(MainPage.URL);
        mainPage.checkCorrectElementMainPage()
                .clickSaucesSection()
                .checkNavigationSectionSauces();
    }

    @Test
    @DisplayName("Проверка корректности перехода к секции Булки")
    public void testSectionNavigationBuns(){
        WebDriver driver = driverExtension.getDriver();
        MainPage mainPage = new MainPage(driver);
        driver.get(MainPage.URL);
        mainPage.checkCorrectElementMainPage()
        // Вызываем переход к секции Начинки, что бы секция Булок была не доступна (Избегаем ложно положительного теста)
        .clickFillingSection()
        .checkNavigationSectionFilling()
        .clickBunsSection()
        .checkNavigationSectionBuns();
    }
}
