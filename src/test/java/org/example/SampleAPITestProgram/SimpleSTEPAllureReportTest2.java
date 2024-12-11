package org.example.SampleAPITestProgram;

import org.testng.annotations.Test;

public class SimpleSTEPAllureReportTest2 {

    SimpleSTEPAllureReportTest1 steps = new SimpleSTEPAllureReportTest1();

    @Test
    public void testLogin() {
        steps.openApplication();
        steps.login("testUser", "password123");
        steps.verifyDashboard();
    }
}
