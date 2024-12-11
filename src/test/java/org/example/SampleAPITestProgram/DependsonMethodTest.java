package org.example.SampleAPITestProgram;

import org.testng.annotations.Test;

public class DependsonMethodTest {


    @Test(dependsOnMethods = "TetCase2")
    public void TetCase1() {
        System.out.println("This is Test Case 1");
    }

    @Test ()
    public void TetCase2() {
        System.out.println("This is Test Case 2");
    }
}
