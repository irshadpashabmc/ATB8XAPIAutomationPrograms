package org.example.SampleAPITestProgram;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGBeforeTest {


    @Test
    public void TetCase1() {
        System.out.println("This is Test Case 1");
    }

    @Test
    public void TetCase2() {
        System.out.println("This is Test Case 2");
    }


    @AfterTest
    public void TetCase3() {
        System.out.println("This is Test Case 3");
    }

    @BeforeTest
    public void TetCase4() {
        System.out.println("This is Test Case 4");
    }


}
