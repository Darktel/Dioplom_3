package ru.educationServices.stellarburgers.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage {
    private static WebDriver driver;
    private WebDriverWait wait;

    public static final String URL = "https://stellarburgers.education-services.ru/";

    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Личный Кабинет')]")
    private WebElement buttonProfileLink;

    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")
    private WebElement buttonSignUpMiddle;

    @FindBy(how = How.XPATH, using = "//h1[@class='text text_type_main-large mb-5 mt-10']")
    public WebElement headerPage;

    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")
    public WebElement buttonSendOrder;

    @FindBy(how = How.XPATH, using = "//h1[@class='text text_type_main-large mb-5 mt-10']")
    private WebElement mainTitlePage;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Булки')]")
    private WebElement sectionBuns;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Соусы')]")
    private WebElement sectionSauces;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Начинки')]")
    private WebElement sectionFillings;

    @FindBy(how = How.XPATH, using = "//section[@class='BurgerIngredients_ingredients__1N8v2']//ul[1]")
    private WebElement sectionIngredientBuns;

    @FindBy(how = How.XPATH, using = "//section[@class='BurgerIngredients_ingredients__1N8v2']//ul[2]")
    private WebElement sectionIngredientSauces;

    @FindBy(how = How.XPATH, using = "//section[@class='BurgerIngredients_ingredients__1N8v2']//ul[3]")
    private WebElement sectionIngredientFillings;

    @FindBy(xpath = "//p[contains(text(),'Говяжий метеорит (отбивная)')]")
    private WebElement fillingIngredient2;

    @FindBy(xpath = "//p[@class='BurgerIngredient_ingredient__text__yp3dH'][contains(text(),'Соус с шипами Антарианского плоскоходца')]")
    private WebElement SaucesIngredient4;

    @FindBy(xpath = "//p[contains(text(),'Краторная булка N-200i')]")
    private WebElement BunIngredient2;


    public MainPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    }

    public void clickProfileButton(){
        wait.until(ExpectedConditions.elementToBeClickable(buttonProfileLink));
        buttonProfileLink.click();
    }

    public void clickLoginButton(){
        wait.until(ExpectedConditions.visibilityOf(buttonSignUpMiddle));
        buttonSignUpMiddle.click();
    }

    public MainPage checkCorrectElementMainPage() {
        wait.until(ExpectedConditions.visibilityOf(mainTitlePage));
        assertTrue(mainTitlePage.isDisplayed());
        assertTrue(sectionBuns.isDisplayed());
        assertTrue(sectionSauces.isDisplayed());
        assertTrue(sectionFillings.isDisplayed());
        assertTrue(buttonSendOrder.isDisplayed());
        return this;
    }

    public MainPage checkNavigationSectionFilling() {
        wait.until(ExpectedConditions.visibilityOf(fillingIngredient2));
        assertTrue(sectionIngredientFillings.isDisplayed());
        return this;

    }

    public MainPage clickFillingSection() {
        wait.until(ExpectedConditions.elementToBeClickable(sectionFillings));
        sectionFillings.click();
        return this;
    }

    public MainPage clickSaucesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(sectionSauces));
        sectionSauces.click();
        return this;
    }

    public MainPage clickBunsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(sectionBuns));
        sectionBuns.click();
        return this;
    }

    public void checkNavigationSectionSauces() {
        wait.until(ExpectedConditions.visibilityOf(SaucesIngredient4));
        assertTrue(sectionIngredientSauces.isDisplayed());
    }

    public void checkNavigationSectionBuns() {
        wait.until(ExpectedConditions.visibilityOf(BunIngredient2));
        assertTrue(sectionIngredientBuns.isDisplayed());
    }
}
