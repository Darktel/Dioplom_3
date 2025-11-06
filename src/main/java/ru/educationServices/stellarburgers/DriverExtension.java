package ru.educationServices.stellarburgers;


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
    private final Faker faker = new Faker();
    public Client client;


    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        driverFactory.initDriver();
        client = new Client(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password());
//        apiClient.createClient(client);
    }


    @Override
    public void afterEach(ExtensionContext context) {
        driverFactory.getDriver().quit();
        apiClient.deleteClient(apiClient.getTokenClient(client));

    }

    public WebDriver getDriver() {
        return driverFactory.getDriver();
    }
}