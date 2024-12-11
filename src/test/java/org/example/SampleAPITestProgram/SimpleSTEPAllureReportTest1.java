package org.example.SampleAPITestProgram;

import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class SimpleSTEPAllureReportTest1 {

    @Step("Open the application")
    @Test(priority = 1)
    public void openApplication() {
        System.out.println("Application opened");
    }

    @Step("Login with username: {0} and password: {1}")
    @Test (priority = 2)
    public void login(String username, String password) {
        System.out.println("Logging in with " + username);
    }

    @Step("Verify the dashboard is displayed")
    @Test (priority = 3)
    public void verifyDashboard() {
        System.out.println("Dashboard verified");
    }
}
