package org.example.SampleAPITestProgram;

import com.beust.jcommander.Parameter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGParameterTest {

 @Parameters("Browser")
    @Test
    public void TetCase(String value) {

     if (value.equalsIgnoreCase("Chrome Browser")) {
        System.out.println("This is:"+value);
    }

     if (value.equalsIgnoreCase("Firefox Browser")) {
         System.out.println("This is:"+value);
     }

    }
}
