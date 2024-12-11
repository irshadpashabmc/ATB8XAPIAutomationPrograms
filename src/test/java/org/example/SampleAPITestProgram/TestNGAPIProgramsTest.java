package org.example.SampleAPITestProgram;

import org.testng.annotations.Test;

public class TestNGAPIProgramsTest {

    @Test (groups = {"Sanity Testing","Smoke Testing", "Regression Testing"})
    public void TetCase1() {
        System.out.println("This is Test Case 1");
    }

    @Test (groups = {"Sanity Testing"})
    public void TetCase2() {
        System.out.println("This is Test Case 2");
    }
    @Test (groups = {"Sanity Testing","Smoke Testing", "Regression Testing"})
    public void TetCase3() {
        System.out.println("This is Test Case 2");
    }
    @Test (groups = {"Smoke Testing"})
    public void TetCase4() {
        System.out.println("This is Test Case 4");

    }
    @Test (groups = {"Regression Testing"})
    public void TetCase5() {
        System.out.println("This is Test Case 5");
    }


}

