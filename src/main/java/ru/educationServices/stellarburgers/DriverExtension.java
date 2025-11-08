package ru.educationServices.stellarburgers;


import checks.CheckClient;
import client.ClientClient;
import models.Client;
import net.datafaker.Faker;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;


public class DriverExtension implements BeforeEachCallback, AfterEachCallback {
    private final DriverFactory driverFactory = new DriverFactory();
    ClientClient apiClient = new ClientClient();
    CheckClient checkClient = new CheckClient();
    private final Faker faker = new Faker();
    public Client client;
    public Client brokenClient;


    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        driverFactory.initDriver();
        client = new Client(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password());
        brokenClient = new Client(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password().toString().substring(0,4));
    }


    @Override
    public void afterEach(ExtensionContext context) {
        driverFactory.getDriver().quit();
        apiClient.deleteClient(apiClient.getTokenClient(client));
        apiClient.deleteClient(apiClient.getTokenClient(brokenClient));

    }

    public WebDriver getDriver() {
        return driverFactory.getDriver();
    }
}