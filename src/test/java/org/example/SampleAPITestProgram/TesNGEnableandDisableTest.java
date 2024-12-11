package org.example.SampleAPITestProgram;

import org.testng.annotations.Test;

public class TesNGEnableandDisableTest {

    @Test
    public void TetCase1() {

            System.out.println("TestCase1 Method is Enabled");
        }

    @Test(enabled = false)
    public void TetCase2() {

        System.out.println("TestCase2 Method is Enabled");
    }

    @Test(alwaysRun = true)
    public void TetCase3() {

        System.out.println("TestCase2 Method is Enabled");
    }


}
